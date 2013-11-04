package com.your.worth.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.your.worth.R;
import com.your.worth.model.AppModel;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 10/18/13
 * Time: 10:35 AM
 * An abstract class that holds the general methods for the income and spending activities.
 */
public class AddDataActivity extends Activity {

    // The ListView
    private ListView mListView = null;

    // The tag to determent if it's income or spending window
    private int mTag = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mTag = intent.getIntExtra(Constants.EXTRA_TYPE,0);

        if(mTag == AppModel.INCOME){
            //set the income tag
            mTag = AppModel.INCOME;

            // get the income layout
            setContentView(R.layout.activity_income);
        } else if(mTag == AppModel.SPENDING){
            //set the spending tag
            mTag = AppModel.SPENDING;

            // get the spending layout
            setContentView(R.layout.activity_spending);
        }

        // Get ListView object from xml
        mListView = (ListView) findViewById(R.id.listView1);

        // load or reload the listView
        reloadListView();

    }

    /**
     * Method that loads or reloads the LisView from data from the AppModel
     */
    void reloadListView() {

        // get the values from the AppModel
        // exclude 0 value
        final ArrayList<String> list = new ArrayList<String>();

        for (int i=0; i< AppModel.getInstance().getRecordSize(mTag); i++) {
            if (AppModel.getInstance().getRecordValue(i,mTag) != 0 ) {
                list.add(
                        AppModel.getInstance().getRecordValue(i,mTag)+" - "+
                                AppModel.getInstance().getRecordDescription(i,mTag));
            }
        }

        ArrayAdapter<String> adapter = new CustomAdapter(this,list,mTag);
        // do i need to erase the content of the List View first ?
        // Nop.. this seams to be the default way to work with adapters ListView

        // Assign adapter to ListView
        mListView.setAdapter(adapter);
    }

    /** Called when the user clicks the Add button*/
    public void executeAdd(View view) {

        int value=0;
        String description;

        // get the values from the fields
        EditText valueTextField   = (EditText)findViewById(R.id.value);
        EditText descriptionTextField   = (EditText)findViewById(R.id.description);

        // convert the values
        description = descriptionTextField.getText().toString();
        if (!valueTextField.getText().toString().isEmpty()) {
            value = Integer.parseInt(valueTextField.getText().toString());
        }

        // externalize this code so it can be tested automatically
        addRecord(value,description);

        // clear the fields
        valueTextField.getText().clear();
        descriptionTextField.getText().clear();

        // reload the List View
        reloadListView();
    }

    /**
     * Method that adds an record to the AppModel and updates the ListView
     * @param value the value from the Amount field
     * @param description the description from the Description field
     */
    public void addRecord(int value, String description) {

        AppModel.getInstance().addRecordValueAndDescriptionByTag(value,description,mTag);

    }

    //TODO: Add menu to this windows (prio 1)
    //TODO: Add popup to this window (prio 2)
    //TODO: Finish FaceLift menu (prio 3)
}
