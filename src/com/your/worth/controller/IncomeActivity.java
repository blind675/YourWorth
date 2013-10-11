package com.your.worth.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.your.worth.R;
import com.your.worth.model.AppModel;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 10/5/13
 * Time: 11:05 AM
 */
public class IncomeActivity extends Activity {

    ListView mListView = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        // Get ListView object from xml
        mListView = (ListView) findViewById(R.id.listView1);

        // load or reload the listView
        reloadListView();

    }

    /** Called when the user clicks the Options tab */
    public void openOptions(View view) {

        Intent intent = new Intent(this, OprionsActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Home tab */
    public void openHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Method that loads or reloads the LisView from data from the AppModel
     */
    public void reloadListView() {

        // get the values from the AppModel
        // exclude 0 value
        final ArrayList<String> list = new ArrayList<String>();

        for (int i=0; i<AppModel.getInstance().getIncomeListSize(); i++) {
            if (AppModel.getInstance().getIncomeRecordValue(i) != 0 ) {
               list.add(
                    AppModel.getInstance().getIncomeRecordValue(i)+" - "+
                    AppModel.getInstance().getIncomeRecordDescription(i));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row, android.R.id.text1, list);

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
        addIncomeRecord(value,description);

        // clear the fields
        valueTextField.getText().clear();
        descriptionTextField.getText().clear();

        // reload the List View
        reloadListView();
    }

    public void deleteRow(View view){
      Log.d("IMG CLICKED", "" + view.getId());
    }

    /**
     * Method that adds an income record to the AppModel and updates the ListView
     * @param value the value from the Amount field
     * @param description the description from the Description field
     */
    public void addIncomeRecord(int value, String description) {

        AppModel.getInstance().addIncomeRecordValueAndDescription(value,description);

    }
}
