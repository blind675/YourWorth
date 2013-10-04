package com.your.worth.model;

import android.test.AndroidTestCase;

/**
 * Created by Catalin BORA on 9/29/13.
 */
public class AppModelTest extends AndroidTestCase {

    public void testAppModelSingleton() {

        assertNotNull("The value of the AppModel instance is null", AppModel.getInstance());
        AppModel firstModel = AppModel.getInstance();
        assertSame("Singleton failed",firstModel,AppModel.getInstance());
    }

    public void testIncomePartOfModel() {

        //get an empty income first record value
        assertEquals("The value of the returned empty record value is not 0",0, AppModel.getInstance().getIncomeRecordValue(0));
        //get an empty income first record description
        assertNull("The description of the returned empty record value is not null",AppModel.getInstance().getIncomeRecordDescription(0));

        // get the size of the income list
        // should be 0 (empty)
        assertEquals("The size of the income list is not 0",0 ,AppModel.getInstance().getIncomeListSize());

        // add an income recording
        AppModel.getInstance().addIncomeRecordValueAndDescription(400,"Salary");
        // test if ok
        assertEquals("Wrong value of record",400,AppModel.getInstance().getIncomeRecordValue(0));
        assertEquals("Wrong description of record","Salary",AppModel.getInstance().getIncomeRecordDescription(0));
        // size of list should increment
        assertEquals("Wrong list size",1,AppModel.getInstance().getIncomeListSize());

        // add an empty income recording ( value is 0 description dose not matter)
        // should be ignored
        // size of list should not change
        //FiXME: make the model ignore empty recordings.
        int oldListSize = AppModel.getInstance().getIncomeListSize();
        AppModel.getInstance().addIncomeRecordValueAndDescription(0,"test");
        assertEquals("List size increased",oldListSize,AppModel.getInstance().getIncomeListSize());

        // ask for an income record bigger than tha size of the income list
        // should give 0 as value and not throw out of bound exception.
    }
}
