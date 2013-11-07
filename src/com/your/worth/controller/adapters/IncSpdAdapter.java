package com.your.worth.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.your.worth.R;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/7/13
 * Time: 2:56 PM
  */
public class IncSpdAdapter extends ArrayAdapter<String> {

    private List<String> mValues;
    private List<Date> mDates;
    private LayoutInflater mInflater;

    public IncSpdAdapter(Context context, List<String> vales, List<Date> dates) {
        super(context, R.layout.row, vales);

        mValues = vales;
        mDates = dates;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // override the method that gets the view of one row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.row,parent,false);
        }

        ((TextView) convertView.findViewById(android.R.id.text1)).setText(mValues.get(position));
        if(mDates != null) {
            ((TextView) convertView.findViewById(android.R.id.text2)).setText(R.string.added_on + mDates.get(position).toString());
        }

        return convertView;
    }
}
