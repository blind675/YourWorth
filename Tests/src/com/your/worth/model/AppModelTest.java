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
        assertEquals("The value of the returned empty record is not 0",0, AppModel.getInstance().getIncomeRecordValue(0));
        //get an empty income first record description
        assertNull("The description of the returned empty record is not null",AppModel.getInstance().getIncomeRecordDescription(0));

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
        int oldListSize = AppModel.getInstance().getIncomeListSize();
        AppModel.getInstance().addIncomeRecordValueAndDescription(0,"test");
        assertEquals("List size increased",oldListSize,AppModel.getInstance().getIncomeListSize());

        // ask for an income record bigger than tha size of the income list
        // should give 0 as value and not throw out of bound exception.
        assertEquals("The value of the wrong record is not 0",0, AppModel.getInstance().getIncomeRecordValue(
                AppModel.getInstance().getIncomeListSize()));


        // create an income record so there are 2
        AppModel.getInstance().addIncomeRecordValueAndDescription(200,"Free coding");
        // delete the last one
        AppModel.getInstance().removeIncomeRecord(AppModel.getInstance().getIncomeListSize()-1);
        // see if it still exists
        assertEquals("Wrong value of record",200,AppModel.getInstance().getIncomeRecordValue(AppModel.getInstance().getIncomeListSize()-1));

    }

    public void testSpendingPartOfModel() {

        //get an empty spending first record value
        assertEquals("The value of the returned empty record is not 0",0, AppModel.getInstance().getSpendingRecordValue(0));
        //get an empty spending first record description
        assertNull("The description of the returned empty record is not null",AppModel.getInstance().getSpendingRecordDescription(0));

        // get the size of the spending list
        // should be 0 (empty)
        assertEquals("The size of the income list is not 0",0 ,AppModel.getInstance().getSpendingListSize());

        // add an spending recording
        AppModel.getInstance().addSpendingRecordValueAndDescription(300,"Loan");
        // test if ok
        assertEquals("Wrong value of record",300,AppModel.getInstance().getSpendingRecordValue(0));
        assertEquals("Wrong description of record","Loan",AppModel.getInstance().getSpendingRecordDescription(0));
        // size of list should increment
        assertEquals("Wrong list size",1,AppModel.getInstance().getSpendingListSize());

        // add an empty spending recording ( value is 0 description dose not matter)
        // should be ignored
        // size of list should not change
        int oldListSize = AppModel.getInstance().getSpendingListSize();
        AppModel.getInstance().addSpendingRecordValueAndDescription(0,"test");
        assertEquals("List size increased",oldListSize,AppModel.getInstance().getSpendingListSize());

        // ask for an spending record bigger than tha size of the spending list
        // should give 0 as value and not throw out of bound exception.
        assertEquals("The value of the wrong record is not 0",0, AppModel.getInstance().getSpendingRecordValue(
                AppModel.getInstance().getSpendingListSize()));

    }
}
