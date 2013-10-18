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
 * Date: 10/5/13
 * Time: 11:05 AM
 */
public class IncomeActivity extends AbstractIncomeSendingControllerActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        // Get ListView object from xml
        mListView = (ListView) findViewById(R.id.listView1);

        //set the income tag
        mTag = AppModel.INCOME;

        // load or reload the listView
        reloadListView();

    }

}
