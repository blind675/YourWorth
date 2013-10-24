package com.your.worth.controller;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import com.your.worth.R;
import com.your.worth.model.AppModel;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity = null;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    /**
     * test if the value displayed on the home screen are correct
     */
    public void testRefreshTheDisplay() {
        // Get the activity first
        mActivity = getActivity();

        // clear the singleton data, generally done by JUnit, now done explicitly
        AppModel.getInstance().clearLists(mActivity.getApplicationContext());

        // get all the labels
        TextView minuteTextView = (TextView) mActivity.findViewById(R.id.minuteWorth);
        TextView hourTextView = (TextView) mActivity.findViewById(R.id.hourWorth);
        TextView dayTextView = (TextView) mActivity.findViewById(R.id.dayWorth);
        TextView monthTextView = (TextView) mActivity.findViewById(R.id.monthWorth);
        TextView yearTextView = (TextView) mActivity.findViewById(R.id.yearWorth);
        // check if they all are 0, as it should

        assertEquals("The value of the year is nor 0.0","0.0",yearTextView.getText().toString());
        assertEquals("The value of the month is not 0.0","0.0",monthTextView.getText().toString());
        assertEquals("The value of the day is not 0","0.0",dayTextView.getText().toString());
        assertEquals("The value of the hour is not 0","0.0",hourTextView.getText().toString());
        assertEquals("The value of the minute is not 0","0.0",minuteTextView.getText().toString());

        // add 2 income and 1 spending record
        AppModel.getInstance().addRecordValueAndDescriptionByTag(1000,"Salary",AppModel.INCOME);
        AppModel.getInstance().addRecordValueAndDescriptionByTag(400,"Rent",AppModel.INCOME);
        AppModel.getInstance().addRecordValueAndDescriptionByTag(600,"Credit",AppModel.SPENDING);

        // get each new value to display
        // 800 * 12 = 9600 / year
        assertEquals("The value of the year is nor 9600","9600.0",mActivity.refreshTheDisplay(AppModel.Granularity.YEAR));
        // 1000 + 400 - 600 = 800 / month
        assertEquals("The value of the month is not 800","800.0",mActivity.refreshTheDisplay(AppModel.Granularity.MONTH));
        // 800 / 30 = 26.66 / day
        assertEquals("The value of the day is not 26.66","26.66",mActivity.refreshTheDisplay(AppModel.Granularity.DAY));
        // 26.67 / 24 = 1.11 / hour
        assertEquals("The value of the hour is not 1.11","1.11",mActivity.refreshTheDisplay(AppModel.Granularity.HOUR));
        // 1.11 / 60 = 0.01 / minute
        assertEquals("The value of the minute is not 0.01","0.01",mActivity.refreshTheDisplay(AppModel.Granularity.MINUTE));

        // cleanup
        AppModel.getInstance().clearLists(mActivity.getApplicationContext());
    }
}
