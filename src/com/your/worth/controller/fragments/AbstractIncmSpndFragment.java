package com.your.worth.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.your.worth.R;
import com.your.worth.controller.adapters.IncSpdAdapter;
import com.your.worth.model.AppModel;
import com.your.worth.controller.listeners.SwipeDismissListViewTouchListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/5/13
 * Time: 10:51 PM
 */
public abstract class AbstractIncmSpndFragment extends Fragment {

    private ArrayAdapter<String> mAdapter = null;
    // The tag to determent if it's income or spending fragment
    protected int mTag = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // The ListView
        ListView mListView = null;

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

        // I used the SwipeDismissListViewTouchListener from Roman Nurik
        // Gist: https://github.com/romannurik/android-swipetodismiss
        // Google+: https://plus.google.com/113735310430199015092/posts/Fgo1p5uWZLu
        mAdapter = new IncSpdAdapter(getActivity().getApplicationContext(),getDataFromModel(),null);
        mListView.setAdapter(mAdapter);

        // Create a ListView-specific touch listener. ListViews are given special treatment because
        // by default they handle touches for their list items... i.e. they're in charge of drawing
        // the pressed state (the list selector), handling list item clicks, etc.
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        mListView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mAdapter.remove(mAdapter.getItem(position));
                                    AppModel.getInstance().removeRecordByTag(position,mTag);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });
        mListView.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        mListView.setOnScrollListener(touchListener.makeScrollListener());


        // load or reload the listView
        reloadListView();

        // Get the add button and set an action listener
        Button addGoalButton = (Button) incomeView.findViewById(R.id.button1);
        addGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Call the executeAdd with the view parameter
                executeAdd();
            }
        });

        return incomeView;
    }

    /**
     * Get the list of string 'value - description' from te model
     * @return the list of strings
     */
    List<String> getDataFromModel(){

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

        return list;
    }

    /**
     * Method that loads or reloads the LisView from data from the AppModel
     */
    void reloadListView() {



        // clear the adapter
        // set the list and call the adapter to update
        mAdapter.clear();
        mAdapter.addAll(getDataFromModel());
        mAdapter.notifyDataSetChanged();
    }

    /** Called when the user clicks the Add button*/
    public void executeAdd() {

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
