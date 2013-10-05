package com.your.worth.controller;

import android.test.ActivityInstrumentationTestCase2;
import com.your.worth.model.AppModel;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 10/5/13
 * Time: 6:18 PM
 */
public class IncomeActivityTest extends ActivityInstrumentationTestCase2<IncomeActivity> {

    public void testAddIncomeRecording() throws Exception {

        IncomeActivity activity = getActivity();
        activity.addIncomeRecord(200,"Salary");

        /* Check if it got in the AppModel */
        assertEquals("The value of the income record is wrong",200, AppModel.getInstance().getIncomeRecordValue(
                AppModel.getInstance().getIncomeListSize()-1));
        assertEquals("The description of the income record is wrong","Salary", AppModel.getInstance().getIncomeRecordDescription(
                AppModel.getInstance().getIncomeListSize()-1));

        //TODO check if the List View changed

    }

    public IncomeActivityTest() {
        super("com.your.worth.controller",IncomeActivity.class);
    }
}
