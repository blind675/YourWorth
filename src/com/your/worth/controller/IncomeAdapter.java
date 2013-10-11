package com.your.worth.controller;

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
public class IncomeAdapter extends ArrayAdapter<String> {

    private final Context mContext;
    private final ArrayList<String> mValues;

    public IncomeAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.row, values);
        mContext = context;
        mValues = values;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgIcon);
        textView.setText(mValues.get(position).toString());

        imageView.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) {
                mValues.remove(position);
                AppModel.getInstance().removeIncomeRecord(position);
            }
        });

        return rowView;
    }
}
