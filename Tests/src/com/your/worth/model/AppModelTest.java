package com.your.worth.model;

import android.test.AndroidTestCase;

/**
 * Created by Catalin BORA on 9/29/13.
 */
public class AppModelTest extends AndroidTestCase {

    private int incomeTag = AppModel.INCOME;
    private int spendingTag = AppModel.SPENDING;

    public void testAppModelSingleton() {

        assertNotNull("The value of the AppModel instance is null", AppModel.getInstance());
        AppModel firstModel = AppModel.getInstance();
        assertSame("Singleton failed",firstModel,AppModel.getInstance());
    }

    public void testIncomePartOfModel() {

        // clear all data
        AppModel.getInstance().clearLists();

        //get an empty income first record value
        assertEquals("The value of the returned empty record is not 0",0, AppModel.getInstance().getRecordValue(0,incomeTag));
        //get an empty income first record description
        assertNull("The description of the returned empty record is not null",AppModel.getInstance().getRecordDescription(0,incomeTag));

        // get the size of the income list
        // should be 0 (empty)
        assertEquals("The size of the income list is not 0",0 ,AppModel.getInstance().getRecordSize(incomeTag));

        // add an income recording
        AppModel.getInstance().addRecordValueAndDescriptionByTag(400,"Salary",incomeTag);
        // test if ok
        assertEquals("Wrong value of record",400,AppModel.getInstance().getRecordValue(0,incomeTag));
        assertEquals("Wrong description of record","Salary",AppModel.getInstance().getRecordDescription(0,incomeTag));
        // size of list should increment
        assertEquals("Wrong list size",1,AppModel.getInstance().getRecordSize(incomeTag));

        // add an empty income recording ( value is 0 description dose not matter)
        // should be ignored
        // size of list should not change
        int oldListSize = AppModel.getInstance().getRecordSize(incomeTag);
        AppModel.getInstance().addRecordValueAndDescriptionByTag(0,"test",incomeTag);
        assertEquals("List size increased",oldListSize,AppModel.getInstance().getRecordSize(incomeTag));

        // ask for an income record bigger than tha size of the income list
        // should give 0 as value and not throw out of bound exception.
        assertEquals("The value of the wrong record is not 0",0, AppModel.getInstance().getRecordValue(
                AppModel.getInstance().getRecordSize(incomeTag),incomeTag));


        // create an income record so there are 2
        // 400 and 200
        AppModel.getInstance().addRecordValueAndDescriptionByTag(200,"Free coding",incomeTag);
        // delete the last one
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(incomeTag)-1,incomeTag);
        // see if it still exists
        // it should me 400 not 200
        assertEquals("Wrong value of record",400,AppModel.getInstance().getRecordValue(AppModel.getInstance().getRecordSize(incomeTag)-1,incomeTag));
        // remove another one
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(incomeTag)-1,incomeTag);
        // read the list again
        // should be 0
        assertEquals("Wrong value of record",0,AppModel.getInstance().getRecordValue(AppModel.getInstance().getRecordSize(incomeTag)-1,incomeTag));
        // remove again to see it doesn't fail
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(incomeTag)-1,incomeTag);

    }

    public void testSpendingPartOfModel() {

        // clear all data
        AppModel.getInstance().clearLists();

        //get an empty spending first record value
        assertEquals("The value of the returned empty record is not 0",0, AppModel.getInstance().getRecordValue(0,spendingTag));
        //get an empty spending first record description
        assertNull("The description of the returned empty record is not null",AppModel.getInstance().getRecordDescription(0,spendingTag));

        // get the size of the spending list
        // should be 0 (empty)
        assertEquals("The size of the income list is not 0",0 ,AppModel.getInstance().getRecordSize(spendingTag));

        // add an spending recording
        AppModel.getInstance().addRecordValueAndDescriptionByTag(300,"Loan",spendingTag);
        // test if ok
        assertEquals("Wrong value of record",300,AppModel.getInstance().getRecordValue(0,spendingTag));
        assertEquals("Wrong description of record","Loan",AppModel.getInstance().getRecordDescription(0,spendingTag));
        // size of list should increment
        assertEquals("Wrong list size",1,AppModel.getInstance().getRecordSize(spendingTag));

        // add an empty spending recording ( value is 0 description dose not matter)
        // should be ignored
        // size of list should not change
        int oldListSize = AppModel.getInstance().getRecordSize(spendingTag);
        AppModel.getInstance().addRecordValueAndDescriptionByTag(0,"test",spendingTag);
        assertEquals("List size increased",oldListSize,AppModel.getInstance().getRecordSize(spendingTag));

        // ask for an spending record bigger than tha size of the spending list
        // should give 0 as value and not throw out of bound exception.
        assertEquals("The value of the wrong record is not 0",0, AppModel.getInstance().getRecordValue(
                AppModel.getInstance().getRecordSize(spendingTag),spendingTag));

        // create an spending record so there are 2
        // 300 and 500
        AppModel.getInstance().addRecordValueAndDescriptionByTag(500,"Rent",spendingTag);
        // delete the last one
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(spendingTag)-1,spendingTag);
        // see if it still exists
        // it should me 400 not 200
        assertEquals("Wrong value of record",300,AppModel.getInstance().getRecordValue(AppModel.getInstance().getRecordSize(spendingTag)-1,spendingTag));
        // remove another one
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(spendingTag)-1,spendingTag);
        // read the list again
        // should be 0
        assertEquals("Wrong value of record",0,AppModel.getInstance().getRecordValue(AppModel.getInstance().getRecordSize(spendingTag)-1,spendingTag));
        // remove again to see it doesn't fail
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(spendingTag)-1,spendingTag);
    }

    public void testClearLists() {

        // clear all data  ( ironic :) )
        AppModel.getInstance().clearLists();

        // add some data
        AppModel.getInstance().addRecordValueAndDescriptionByTag(400,"Salary",incomeTag);
        // test if ok
        assertEquals("Wrong value of record",400,AppModel.getInstance().getRecordValue(0,incomeTag));
        assertEquals("Wrong description of record","Salary",AppModel.getInstance().getRecordDescription(0,incomeTag));
        // size of list should increment
        assertEquals("Wrong list size",1,AppModel.getInstance().getRecordSize(incomeTag));
        // test if it's ok

        // add an spending recording
        AppModel.getInstance().addRecordValueAndDescriptionByTag(300,"Loan",spendingTag);
        // test if ok
        assertEquals("Wrong value of record",300,AppModel.getInstance().getRecordValue(0,spendingTag));
        assertEquals("Wrong description of record","Loan",AppModel.getInstance().getRecordDescription(0,spendingTag));
        // size of list should increment
        assertEquals("Wrong list size",1,AppModel.getInstance().getRecordSize(spendingTag));

        // clear all data
        AppModel.getInstance().clearLists();

        // test if it worked

        //get an empty income first record value
        assertEquals("The value of the returned empty record is not 0",0, AppModel.getInstance().getRecordValue(0,incomeTag));
        //get an empty income first record description
        assertNull("The description of the returned empty record is not null",AppModel.getInstance().getRecordDescription(0,incomeTag));
        // get the size of the income list
        // should be 0 (empty)
        assertEquals("The size of the income list is not 0",0 ,AppModel.getInstance().getRecordSize(incomeTag));

        //get an empty spending first record value
        assertEquals("The value of the returned empty record is not 0",0, AppModel.getInstance().getRecordValue(0,spendingTag));
        //get an empty spending first record description
        assertNull("The description of the returned empty record is not null",AppModel.getInstance().getRecordDescription(0,spendingTag));
        // get the size of the spending list
        // should be 0 (empty)
        assertEquals("The size of the income list is not 0",0 ,AppModel.getInstance().getRecordSize(spendingTag));
    }
}
