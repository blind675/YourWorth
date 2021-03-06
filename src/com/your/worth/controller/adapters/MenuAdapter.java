package com.your.worth.controller.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.your.worth.R;
import com.your.worth.model.AppModel;
import com.your.worth.model.PIN;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/6/13
 * Time: 12:52 AM
 */
public class MenuAdapter extends ArrayAdapter<String> {

    private final String[] mTitles;
    private final String[] mDescriptions;
    private final int[] mRowType;
    private final TypedArray mIconNames;
    private final LayoutInflater mInflated;
    private final int mSwitchPosition;
    private ToggleButton mToggleButton = null;
    private TextView mDescriptionView = null;
    private int mSignoutResource = R.color.black;

    // create a constructor for my menu adapter
    public MenuAdapter(Context context, String[] titles, String[] descriptions, TypedArray iconNames ,int[] rowType,int switchPosition) {
        super(context, R.layout.menu_row, titles);
        mTitles = titles;
        mDescriptions = descriptions;
        mIconNames = iconNames;
        mSwitchPosition = switchPosition;
        mRowType = rowType;
        mInflated = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // override the method that gets the view of one row
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        // Treat headers and normal rows separately
        // mRowType[position] == 1 means it's a header
        if(mRowType[position] == 1){
            // inflate the layout XML for header
            convertView = mInflated.inflate(R.layout.menu_header, parent, false);
        } else {
            // inflate the layout XML for row
            convertView = mInflated.inflate(R.layout.menu_row, parent, false);

            mDescriptionView = (TextView) convertView.findViewById(R.id.menuDescription);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.menuIcon);
            imageView.setImageResource(mIconNames.getResourceId(position,-1));

            if(mDescriptions[position] != null) {
                mDescriptionView.setText(mDescriptions[position]);
            }

            /*
            I use the fact that if an element in the list row is focusable the click on that row is disabled as a
            feature not a bug. Sp o set the focusable a lot.
            */

            mToggleButton = (ToggleButton) convertView.findViewById(R.id.toggleButton);
            if(position == mSwitchPosition) {
                mToggleButton.setVisibility(View.VISIBLE);
                if(!PIN.isPINActive()) {
                    mToggleButton.setFocusable(true);
                    mToggleButton.setChecked(false);
                    mDescriptionView.setText(R.string.pin_off);
                } else {
                    mToggleButton.setFocusable(false);
                    mToggleButton.setChecked(true);
                    mDescriptionView.setText(R.string.pin_on);
                }
            }

            mToggleButton.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean newState) {
                    if(newState) {
                        /*
                        for some reason that i don't understand yet, the setFocusable has to be last
                        else the other instructions in the if won't get executed.
                        */
                        PIN.setPINActive(true);
                        mDescriptionView.setText(R.string.pin_on);
                        mToggleButton.setFocusable(false);
                        mSignoutResource = R.color.black;
                    } else {
                        /*
                        for some reason that i don't understand yet, the setFocusable has to be last
                        else the other instructions in the if won't get executed.
                        */
                        AppModel.getInstance().deactivatePIN();
                        //PIN.setPINActive(false);
                        mDescriptionView.setText(R.string.pin_off);
                        mToggleButton.setFocusable(true);
                        mSignoutResource = R.color.gray;
                    }
                    // reset the views
                    notifyDataSetChanged();
                }
            });
        }

        /*
         I can use this generally because in both XML the testified has the same id
          */
        TextView titleView = (TextView) convertView.findViewById(R.id.menuTitle);
        titleView.setText(mTitles[position]);

        // treat sign out text color different
        if(!PIN.isInternalPINValid()){
            mSignoutResource = R.color.gray;
        }else {
            mSignoutResource = R.color.black;
        }

        if(position == 1 ){
            titleView.setTextColor(getContext().getResources().getColor(mSignoutResource));
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public String getItem(int position) {
        return mTitles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Method that makes specific rows not clickable
     *
     */
    @Override
    public boolean isEnabled(int position) {
        /*
         special check for the Sign Out tab because it's enabled property is dynamically calculated
         */
        return !(!PIN.isInternalPINValid() && position == 1) && mRowType[position] != 1;
    }
}