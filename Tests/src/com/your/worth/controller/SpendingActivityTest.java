package com.your.worth.controller;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.your.worth.R;
import com.your.worth.model.AppModel;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 10/16/13
 * Time: 9:39 PM
 */
public class SpendingActivityTest extends ActivityInstrumentationTestCase2<SpendingActivity> {

    // externalize for the Android UI thread.
    EditText valueTextField = null;
    EditText descriptionTextField = null;

    // method that test the add of an spending record
    public void testAddSpendingRecording() throws Exception {
        // clear the singleton data, generally done by JUnit, now done explicitly
        AppModel.getInstance().clearLists();

        SpendingActivity activity = getActivity();
        activity.addSpendingRecord(400, "Rent");

        /* Check if it got in the AppModel */
        assertEquals("The value of the income record is wrong",400, AppModel.getInstance().getSpendingRecordValue(
                AppModel.getInstance().getSpendingListSize()-1));
        assertEquals("The description of the income record is wrong","Rent", AppModel.getInstance().getSpendingRecordDescription(
                AppModel.getInstance().getSpendingListSize()-1));
    }

    // method that test the UI functionality of the Add mechanics
    public void testExecuteAdd(){
        // clear the singleton data, generally done by JUnit, now done explicitly
        AppModel.getInstance().clearLists();

        SpendingActivity activity = getActivity();

        // get the textViews and the Add button
        valueTextField   = (EditText)activity.findViewById(R.id.value);
        descriptionTextField   = (EditText)activity.findViewById(R.id.description);
        Button addButton = (Button) activity.findViewById(R.id.button1);

        // set some values in the text fields
        // has to run in the Android UI thread
        activity.runOnUiThread(new Runnable() {
            public void run() {
                valueTextField.setText("400");
                descriptionTextField.setText("Rent");
            }
        }) ;

        // press the Add button
        TouchUtils.clickView(this, addButton);

        // check if the data was inserted correctly in the list
        ListView listView = (ListView) activity.findViewById(R.id.listView1);
        assertEquals("The display list contains something else",
                "400 - Rent",
                listView.getItemAtPosition(listView.getLastVisiblePosition()));
        // check if the fields were cleared
        assertTrue("The value field was not cleared",valueTextField.getText().toString().isEmpty());
        assertTrue("The description field was not cleared",descriptionTextField.getText().toString().isEmpty());

        // just press the Add button not setting field values first
        // nothing should have happened last recording still 400 - Rent

        // press the Add button
        TouchUtils.clickView(this,addButton);

        // check if the data was inserted correctly in the list
        ListView listView2 = (ListView) activity.findViewById(R.id.listView1);
        assertEquals("The display list contains something else",
                "400 - Rent",
                listView2.getItemAtPosition(listView2.getLastVisiblePosition()));
        // check if the fields were cleared
        assertTrue("The value field was not cleared",valueTextField.getText().toString().isEmpty());
        assertTrue("The description field was not cleared",descriptionTextField.getText().toString().isEmpty());

    }


    public SpendingActivityTest() {
        super("com.your.worth.controller",SpendingActivity.class);
    }
}