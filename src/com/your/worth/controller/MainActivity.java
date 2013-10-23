package com.your.worth.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.your.worth.R;
import com.your.worth.model.AppModel;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    // all the labels on the first view (main activity)
    private TextView mMinuteTextView = null;
    private TextView mHourTextView = null;
    private TextView mDayTextView = null;
    private TextView mMonthTextView = null;
    private TextView mYearTextView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //first get all the textView labels of the main activity
        mMinuteTextView = (TextView) findViewById(R.id.minuteWorth);
        mHourTextView = (TextView) findViewById(R.id.hourWorth);
        mDayTextView = (TextView) findViewById(R.id.dayWorth);
        mMonthTextView = (TextView) findViewById(R.id.monthWorth);
        mYearTextView = (TextView) findViewById(R.id.yearWorth);

        // load the data of the DB in the AppModel first
        // the AppModel works like a cache (I overcomplicated a little)
        AppModel.getInstance().loadDataBase(this);

        refreshTheDisplay();
    }

    /** Called when the user clicks the Options tab */
    public void openOptions(View view) {

        Intent intent = new Intent(this, OprionsActivity.class);
        startActivity(intent);
    }

    /**
     * Method that refreshes the values of the displayed fields based on values from the AppModel
     */
    void refreshTheDisplay(){

    // i already have the labels, now just update them.
    mMinuteTextView.setText(""+AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.MINUTE));
    mHourTextView.setText(""+AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.HOUR));
    mDayTextView.setText(""+AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.DAY));
    mMonthTextView.setText(""+AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.MONTH));
    mYearTextView.setText(""+AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.YEAR));
    }

}
