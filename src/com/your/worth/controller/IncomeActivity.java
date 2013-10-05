package com.your.worth.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.your.worth.R;
import com.your.worth.model.AppModel;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 10/5/13
 * Time: 11:05 AM
 */
public class IncomeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
    }

    /** Called when the user clicks the Add button*/
    public void executeAdd(View view) {

        int value;
        String description;

        // get the values from the fields
        EditText valueTextField   = (EditText)findViewById(R.id.value);
        EditText descriptionTextField   = (EditText)findViewById(R.id.description);

        // convert the values
        description = descriptionTextField.getText().toString();
        value = Integer.parseInt(valueTextField.getText().toString());

        // externalize this code so it can be tested automatically
        addIncomeRecord(value,description);

        // clear the fields
        valueTextField.getText().clear();
        descriptionTextField.getText().clear();
    }

    /**
     * Method that adds an income record to the AppModel and updates the ListView
     * @param value the value from the Amount field
     * @param description the description from the Description field
     */
    public void addIncomeRecord(int value, String description) {

        AppModel.getInstance().addIncomeRecordValueAndDescription(value,description);

        //TODO update the List View
    }
}
