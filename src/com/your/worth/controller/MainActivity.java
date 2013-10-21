package com.your.worth.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.your.worth.R;
import com.your.worth.model.AppModel;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    //TODO implement (prio 3);
    // get the display labels and update them one by one

    }

}
