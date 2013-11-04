package com.your.worth.controller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.your.worth.R;
import com.your.worth.controller.view.viewgroup.HomeViewContainer;
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

    private HomeViewContainer mRoot = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRoot = (HomeViewContainer) this.getLayoutInflater().inflate(R.layout.activity_home, null);
        this.setContentView(mRoot);
        setContentView(mRoot);

        //first get all the textView labels of the main activity
        mMinuteTextView = (TextView) findViewById(R.id.minuteWorth);
        mHourTextView = (TextView) findViewById(R.id.hourWorth);
        mDayTextView = (TextView) findViewById(R.id.dayWorth);
        mMonthTextView = (TextView) findViewById(R.id.monthWorth);
        mYearTextView = (TextView) findViewById(R.id.yearWorth);

        // load the data of the DB in the AppModel first
        // the AppModel works like a cache (I overcomplicated a little)
        AppModel.getInstance().loadDataBase(this);

        refreshDisplay();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshDisplay();
    }

    /** Called when the user pushes the show menu picture */
    public void toggleMenu(View view){
        mRoot.toggleMenu();
    }

    /** Called when the user clicks the Incomes tab */
    public void openIncome(View view) {
        //close the menu before you leave
        if(mRoot.getMenuState() == HomeViewContainer.MenuState.OPEN)  {
            mRoot.toggleMenu();
        }
        Intent intent = new Intent(this, AddDataActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //set the income tag
        intent.putExtra(Constants.EXTRA_TYPE,AppModel.INCOME);
        startActivity(intent);
    }

    /** Called when the user clicks the Expenses tab */
    public void openSpending(View view) {
        //close the menu before you leave
        if(mRoot.getMenuState() == HomeViewContainer.MenuState.OPEN)  {
            mRoot.toggleMenu();
        }
        Intent intent = new Intent(this, AddDataActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //set the spending tag
        intent.putExtra(Constants.EXTRA_TYPE,AppModel.SPENDING);
        startActivity(intent);
    }

    /** Called when the user clicks the Expenses tab */
    public void openAbout(View view) {
        //close the menu before you leave
        if(mRoot.getMenuState()== HomeViewContainer.MenuState.OPEN)  {
            mRoot.toggleMenu();
        }
        // the url for the web page of the application
        String aboutURL = "http://yourworth.herokuapp.com/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(aboutURL));

        // Start the activity
        startActivity(intent);
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
     * Method that returns the values of the displayed fields based on values from the AppModel
     * @param granularity the label to display
     * @return value to display as string
     */
    String refreshTheDisplay(AppModel.Granularity granularity){
        return ""+AppModel.getInstance().getTheWorthBasedOn(granularity);
    }

}
