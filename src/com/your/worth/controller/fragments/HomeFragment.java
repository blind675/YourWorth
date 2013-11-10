package com.your.worth.controller.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.your.worth.R;
import com.your.worth.model.AppModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/5/13
 * Time: 7:44 PM
 */
public class HomeFragment extends Fragment {

    // Formatter
    private final NumberFormat mFormatter = new DecimalFormat("#0.00");

    // all the labels on the first view (main activity)
    private TextView mMinuteTextView = null;
    private TextView mHourTextView = null;
    private TextView mDayTextView = null;
    private TextView mMonthTextView = null;
    private TextView mYearTextView = null;

    /**
     * Called when the fragment is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View theHomeView = inflater.inflate(R.layout.part_home, container, false);

        //first get all the textView labels of the main activity
        mMinuteTextView = (TextView) theHomeView.findViewById(R.id.minuteWorth);
        mHourTextView = (TextView) theHomeView.findViewById(R.id.hourWorth);
        mDayTextView = (TextView) theHomeView.findViewById(R.id.dayWorth);
        mMonthTextView = (TextView) theHomeView.findViewById(R.id.monthWorth);
        mYearTextView = (TextView) theHomeView.findViewById(R.id.yearWorth);

        // now that i have the elements do a refresh
        refreshDisplay();

        return theHomeView;

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshDisplay();
    }


    /**
     * Private method that refreshes the display values
     */
    private void refreshDisplay() {

        // i already have the labels, now just update them.
        mMinuteTextView.setText(refreshTheDisplay(AppModel.Granularity.MINUTE));
        mHourTextView.setText(refreshTheDisplay(AppModel.Granularity.HOUR));
        mDayTextView.setText(refreshTheDisplay(AppModel.Granularity.DAY));
        mMonthTextView.setText(refreshTheDisplay(AppModel.Granularity.MONTH));
        mYearTextView.setText(refreshTheDisplay(AppModel.Granularity.YEAR));
    }

    /**
     * Method that returns the values of the displayed fields based on values from the AppModel.
     * Also formats with 2 decimal places.
     * @param granularity the label to display
     * @return value to display as string
     */
    String refreshTheDisplay(AppModel.Granularity granularity){

        return mFormatter.format(AppModel.getInstance().getTheWorthBasedOn(granularity));
    }




}
