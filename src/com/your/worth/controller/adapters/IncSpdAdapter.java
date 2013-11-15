package com.your.worth.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.your.worth.R;
import com.your.worth.model.Record;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/7/13
 * Time: 2:56 PM
  */
public class IncSpdAdapter extends ArrayAdapter<Record> {

    private List<Record> mValues;
    private final LayoutInflater mInflater;

    public IncSpdAdapter(Context context, List<Record> vales) {
        super(context, R.layout.row, vales);

        mValues = vales;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // override the method that gets the view of one row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.row,parent,false);
        }

        ((TextView) convertView.findViewById(android.R.id.text1)).setText(mValues.get(position).getValue()+" - " +
                                                        mValues.get(position).getDescription());
        if(mValues.get(position).getDate() != null) {
            String firstPart;
            if (mValues.get(position).isModified()){
                    firstPart = getContext().getApplicationContext().getString(R.string.modified_on);
                } else {
                    firstPart = getContext().getApplicationContext().getString(R.string.added_on);
                }

            ((TextView) convertView.findViewById(android.R.id.text2)).setText(
                    firstPart +" "+ mValues.get(position).getDate());
        }

        return convertView;
    }

}