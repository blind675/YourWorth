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

        // i already have the labels, now just update them.
        mMinuteTextView.setText(refreshTheDisplay(AppModel.Granularity.MINUTE));
        mHourTextView.setText(refreshTheDisplay(AppModel.Granularity.HOUR));
        mDayTextView.setText(refreshTheDisplay(AppModel.Granularity.DAY));
        mMonthTextView.setText(refreshTheDisplay(AppModel.Granularity.MONTH));
        mYearTextView.setText(refreshTheDisplay(AppModel.Granularity.YEAR));
    }

    /** Called when the user clicks the Options tab */
    public void openOptions(View view) {

        Intent intent = new Intent(this, OptionsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    /**
     * Method that returns the values of the displayed fields based on values from the AppModel
     * @param granularity the label to display
     * @return value to display as string
     */
    String refreshTheDisplay(AppModel.Granularity granularity){
        return ""+AppModel.getInstance().getTheWorthBasedOn(granularity);
    }

}
