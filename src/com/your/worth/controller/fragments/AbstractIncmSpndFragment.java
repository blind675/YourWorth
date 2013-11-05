package com.your.worth.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.your.worth.R;
import com.your.worth.controller.adapters.CustomAdapter;
import com.your.worth.model.AppModel;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/5/13
 * Time: 10:51 PM
 */
public abstract class AbstractIncmSpndFragment extends Fragment {

    // The ListView
    private ListView mListView = null;
    // The tag to determent if it's income or spending fragment
    protected int mTag = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View incomeView = inflater.inflate(R.layout.part_adddata, container, false);

        // Change the title displayed on the fragment
        TextView titleTextView = (TextView) incomeView.findViewById(R.id.textView1);
        if (mTag == AppModel.INCOME){
            titleTextView.setText(R.string.income);
        } else if(mTag == AppModel.SPENDING) {
            titleTextView.setText(R.string.spending);
        }

        // Get ListView object from xml
        mListView = (ListView) incomeView.findViewById(R.id.listView1);

        // load or reload the listView
        reloadListView();

        // Get the add button and set an action listener
        Button addGoalButton = (Button) incomeView.findViewById(R.id.button1);
        addGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Call the executeAdd with the view parameter
                executeAdd(view);
            }
        });

        return incomeView;
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

        ArrayAdapter<String> adapter = new CustomAdapter(getActivity().getApplicationContext(),list,mTag);
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
        EditText valueTextField   = (EditText) getView().findViewById(R.id.value);
        EditText descriptionTextField   = (EditText) getView().findViewById(R.id.description);

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
}
