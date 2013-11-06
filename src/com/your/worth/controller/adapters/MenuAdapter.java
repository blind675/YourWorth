package com.your.worth.controller.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.your.worth.R;

/**
 * Created with IntelliJ IDEA.
 * User: blind675
 * Date: 11/6/13
 * Time: 12:52 AM
 */
public class MenuAdapter extends ArrayAdapter<String> {

    private String[] mTitles;
    private final TypedArray mIconNames;
    private final LayoutInflater mInflated;

    // create a constructor for my menu adapter
    public MenuAdapter(Context context, String[] titles, TypedArray iconNames ) {
        super(context, R.layout.menu_row, titles);
        mTitles = titles;
        mIconNames = iconNames;
        mInflated = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // override the method that gets the view of one row
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // inflate the layout XML only if the convertView is null
        // the version suggested by google
        convertView = mInflated.inflate(R.layout.menu_row, parent, false);

        TextView textView = (TextView) convertView.findViewById(android.R.id.title);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.menuIcon);
        textView.setText(mTitles[position]);
        imageView.setImageResource(mIconNames.getResourceId(position,-1));

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
}
