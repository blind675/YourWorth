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

    public MainActivityTest() {
        super("com.your.worth.controller", MainActivity.class);
    }

    /**
     * test if the value displayed on the home screen are correct
     */
    public void testRefreshTheDisplay() {
        // clear the singleton data, generally done by JUnit, now done explicitly
        AppModel.getInstance().clearLists();

        MainActivity activity = getActivity();

        // get all the labels
        TextView minuteTextView = (TextView) activity.findViewById(R.id.minuteWorth);
        TextView hourTextView = (TextView) activity.findViewById(R.id.hourWorth);
        TextView dayTextView = (TextView) activity.findViewById(R.id.dayWorth);
        TextView monthTextView = (TextView) activity.findViewById(R.id.monthWorth);
        //TODO: put an extra label for year (prio 2)
        //TextView yearTextView = (TextView) activity.findViewById(R.id.yearWorth);
        // check if they all are 0, as it should

        //assertEquals("The value of the year is nor 0","0",yearTextView.);
        assertEquals("The value of the month is not 0","0",monthTextView.getText().toString());
        assertEquals("The value of the day is not 0","0",dayTextView.getText().toString());
        assertEquals("The value of the hour is not 0","0",hourTextView.getText().toString());
        assertEquals("The value of the minute is not 0","0",minuteTextView.getText().toString());

        // add 2 income and 1 spending record
        AppModel.getInstance().addRecordValueAndDescriptionByTag(1000,"Salary",AppModel.INCOME);
        AppModel.getInstance().addRecordValueAndDescriptionByTag(400,"Rent",AppModel.INCOME);
        AppModel.getInstance().addRecordValueAndDescriptionByTag(600,"Credit",AppModel.SPENDING);

        // refresh the view
        activity.refreshTheDisplay();

        // check if the values got set correctly

        // 800 * 12 = 9600 / year
        //assertEquals("The value of the year is nor 9600","9600",yearTextView.getText().toString());
        // 1000 + 400 - 600 = 800 / month
        assertEquals("The value of the month is not 800","800",monthTextView.getText().toString());
        // 800 / 30 = 26.66 / day
        assertEquals("The value of the day is not 26.66","26.66",dayTextView.getText().toString());
        // 26.67 / 24 = 1.11 / hour
        assertEquals("The value of the hour is not 1.11","1.11",hourTextView.getText().toString());
        // 1.11 / 60 = 0.01 / minute
        assertEquals("The value of the minute is not 0.01","0.01",minuteTextView.getText().toString());
        //TODO: implement minute functionality ( prio 1)
    }
}
