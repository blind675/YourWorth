package com.your.worth.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.your.worth.R;
import com.your.worth.model.AppModel;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 10/11/13
 * Time: 4:43 PM
 */
public class CustomAdapter extends ArrayAdapter<String> {

    private final ArrayList<String> mValues;
    private final LayoutInflater mInflated;
    private final int mTag;

    // create a constructor for my custom adapter
    public CustomAdapter(Context context, ArrayList<String> values,int tag) {
        super(context, R.layout.row, values);
        mValues = values;
        mInflated = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mTag = tag;
    }
    
    // override the method that gets the view of one row
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // inflate the layout XML only if the convertView is null
        // the version suggested by google
        if (convertView == null){
            convertView = mInflated.inflate(R.layout.row, parent, false);
        }

        TextView mTextView = (TextView) convertView.findViewById(android.R.id.text1);
        ImageView mImageView = (ImageView) convertView.findViewById(R.id.imgIcon);
        mTextView.setText(mValues.get(position));

        // remove the row if the x icon was clicked
        mImageView.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) {
                mValues.remove(position);
                AppModel.getInstance().removeRecordByTag(position,mTag);

                // reset the views
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
