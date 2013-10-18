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
 * Date: 10/5/13
 * Time: 6:18 PM
 */
public class IncomeActivityTest extends ActivityInstrumentationTestCase2<IncomeActivity> {

    // externalize for the Android UI thread.
    EditText valueTextField = null;
    EditText descriptionTextField = null;

    // method that test the add of an income record
    public void testAddIncomeRecording() throws Exception {
        // clear the singleton data, generally done by JUnit, now done explicitly
        AppModel.getInstance().clearLists();

        IncomeActivity activity = getActivity();
        activity.addRecord(200,"Salary");

        /* Check if it got in the AppModel */
        assertEquals("The value of the income record is wrong",200, AppModel.getInstance().getRecordValue(
                AppModel.getInstance().getRecordSize(AppModel.INCOME)-1,AppModel.INCOME));
        assertEquals("The description of the income record is wrong","Salary", AppModel.getInstance().getRecordDescription(
                AppModel.getInstance().getRecordSize(AppModel.INCOME)-1,AppModel.INCOME));

    }

    // method that test the UI functionality of the Add mechanics
    public void testExecuteAdd(){
        // clear the singleton data, generally done by JUnit, now done explicitly
        AppModel.getInstance().clearLists();

        IncomeActivity activity = getActivity();

        // get the textViews and the Add button
        valueTextField   = (EditText)activity.findViewById(R.id.value);
        descriptionTextField   = (EditText)activity.findViewById(R.id.description);
        Button addButton = (Button) activity.findViewById(R.id.button1);

        // set some values in the text fields
        // has to run in the Android UI thread
        // next project i'm going with Robotium
        activity.runOnUiThread(new Runnable() {
            public void run() {
                valueTextField.setText("200");
                descriptionTextField.setText("Salary");
            }
        }) ;

        // press the Add button
        TouchUtils.clickView(this,addButton);

        // check if the data was inserted correctly in the list
        ListView listView = (ListView) activity.findViewById(R.id.listView1);
        assertEquals("The display list contains something else",
                "200 - Salary",
                listView.getItemAtPosition(listView.getLastVisiblePosition()));
        // check if the fields were cleared
        assertTrue("The value field was not cleared",valueTextField.getText().toString().isEmpty());
        assertTrue("The description field was not cleared",descriptionTextField.getText().toString().isEmpty());

        // just press the Add button not setting field values first
        // nothing should have happened last recording still 200 - Salary

        // press the Add button
        TouchUtils.clickView(this,addButton);

        // check if the data was inserted correctly in the list
        ListView listView2 = (ListView) activity.findViewById(R.id.listView1);
        assertEquals("The display list contains something else",
                "200 - Salary",
                listView2.getItemAtPosition(listView2.getLastVisiblePosition()));
        // check if the fields were cleared
        assertTrue("The value field was not cleared",valueTextField.getText().toString().isEmpty());
        assertTrue("The description field was not cleared",descriptionTextField.getText().toString().isEmpty());

    }

    // method that tries to test a removal of a income record and all it's implications
    public void testRemoveRecord() {
        // clear the singleton data, generally done by JUnit, now done explicitly
        AppModel.getInstance().clearLists();

        IncomeActivity activity = getActivity();

        // get the textViews and the Add button
        valueTextField   = (EditText)activity.findViewById(R.id.value);
        descriptionTextField   = (EditText)activity.findViewById(R.id.description);
        Button addButton = (Button) activity.findViewById(R.id.button1);

        // set some values in the text fields
        // has to run in the Android UI thread
        activity.runOnUiThread(new Runnable() {
            public void run() {
                valueTextField.setText("300");
                descriptionTextField.setText("Test Salary");
            }
        }) ;

        // press the Add button
        TouchUtils.clickView(this,addButton);

        //get the listView
        ListView listView = (ListView) activity.findViewById(R.id.listView1);

        // find a way to finish the test

    }


    public IncomeActivityTest() {
        super("com.your.worth.controller",IncomeActivity.class);
    }
}
