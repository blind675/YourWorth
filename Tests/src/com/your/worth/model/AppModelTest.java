package com.your.worth.model;

import android.test.AndroidTestCase;

/**
 * Created by Catalin BORA on 9/29/13.
 */
public class AppModelTest extends AndroidTestCase {

    private final int incomeTag = AppModel.INCOME;
    private final int spendingTag = AppModel.SPENDING;

    public void testAppModelSingleton() {

        assertNotNull("The value of the AppModel instance is null", AppModel.getInstance());
        AppModel firstModel = AppModel.getInstance();
        assertSame("Singleton failed",firstModel,AppModel.getInstance());

        AppModel.getInstance().loadDataBase(mContext);
    }

    public void testIncomePartOfModel() {

        // clear all data
        AppModel.getInstance().clearLists();

        //get an empty income first record value
        assertEquals("The value of the returned empty record is not 0",0, AppModel.getInstance().getRecord(0,incomeTag).getValue());
        //get an empty income first record description
        assertNull("The description of the returned empty record is not null",AppModel.getInstance().getRecord(0,incomeTag).getDescription());

        // get the size of the income list
        // should be 0 (empty)
        assertEquals("The size of the income list is not 0",0 ,AppModel.getInstance().getRecordSize(incomeTag));

        // add an income recording
        AppModel.getInstance().addRecordValueAndDescriptionByTag(400,"Salary",incomeTag);
        // test if ok
        assertEquals("Wrong value of record",400,AppModel.getInstance().getRecord(0,incomeTag).getValue());
        assertEquals("Wrong description of record","Salary",AppModel.getInstance().getRecord(0,incomeTag).getDescription());
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
        assertEquals("The value of the wrong record is not 0",0, AppModel.getInstance().getRecord(
                AppModel.getInstance().getRecordSize(incomeTag),incomeTag).getValue());


        // create an income record so there are 2
        // 400 and 200
        AppModel.getInstance().addRecordValueAndDescriptionByTag(200,"Free coding",incomeTag);
        // delete the last one
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(incomeTag)-1,incomeTag);
        // see if it still exists
        // it should me 400 not 200
        assertEquals("Wrong value of record",400,AppModel.getInstance().getRecord(AppModel.getInstance().getRecordSize(incomeTag)-1,incomeTag).getValue());
        // remove another one
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(incomeTag)-1,incomeTag);
        // read the list again
        // should be 0
        assertEquals("Wrong value of record",0,AppModel.getInstance().getRecord(AppModel.getInstance().getRecordSize(incomeTag)-1,incomeTag).getValue());
        // remove again to see it doesn't fail
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(incomeTag)-1,incomeTag);

        // clear all data
        AppModel.getInstance().clearLists();

    }

    public void testSpendingPartOfModel() {

        // clear all data
        AppModel.getInstance().clearLists();

        //get an empty spending first record value
        assertEquals("The value of the returned empty record is not 0",0, AppModel.getInstance().getRecord(0,spendingTag).getValue());
        //get an empty spending first record description
        assertNull("The description of the returned empty record is not null",AppModel.getInstance().getRecord(0,spendingTag).getDescription());

        // get the size of the spending list
        // should be 0 (empty)
        assertEquals("The size of the income list is not 0",0 ,AppModel.getInstance().getRecordSize(spendingTag));

        // add an spending recording
        AppModel.getInstance().addRecordValueAndDescriptionByTag(300,"Loan",spendingTag);
        // test if ok
        assertEquals("Wrong value of record",300,AppModel.getInstance().getRecord(0,spendingTag).getValue());
        assertEquals("Wrong description of record","Loan",AppModel.getInstance().getRecord(0,spendingTag).getDescription());
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
        assertEquals("The value of the wrong record is not 0",0, AppModel.getInstance().getRecord(
                AppModel.getInstance().getRecordSize(spendingTag),spendingTag).getValue());

        // create an spending record so there are 2
        // 300 and 500
        AppModel.getInstance().addRecordValueAndDescriptionByTag(500,"Rent",spendingTag);
        // delete the last one
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(spendingTag)-1,spendingTag);
        // see if it still exists
        // it should be 300 not 500
        assertEquals("Wrong value of record",300,AppModel.getInstance().getRecord(AppModel.getInstance().getRecordSize(spendingTag)-1,spendingTag).getValue());
        // remove another one
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(spendingTag)-1,spendingTag);
        // read the list again
        // should be 0
        assertEquals("Wrong value of record",0,AppModel.getInstance().getRecord(AppModel.getInstance().getRecordSize(spendingTag)-1,spendingTag).getValue());
        // remove again to see it doesn't fail
        AppModel.getInstance().removeRecordByTag(AppModel.getInstance().getRecordSize(spendingTag)-1,spendingTag);

        // clear all data
        AppModel.getInstance().clearLists();
    }

    public void testClearLists() {

        // clear all data  ( ironic :) )
        AppModel.getInstance().clearLists();

        // add some data
        AppModel.getInstance().addRecordValueAndDescriptionByTag(400,"Salary",incomeTag);
        // test if ok
        assertEquals("Wrong value of record",400,AppModel.getInstance().getRecord(0,incomeTag).getValue());
        assertEquals("Wrong description of record","Salary",AppModel.getInstance().getRecord(0,incomeTag).getDescription());
        // size of list should increment
        assertEquals("Wrong list size",1,AppModel.getInstance().getRecordSize(incomeTag));
        // test if it's ok

        // add an spending recording
        AppModel.getInstance().addRecordValueAndDescriptionByTag(300,"Loan",spendingTag);
        // test if ok
        assertEquals("Wrong value of record",300,AppModel.getInstance().getRecord(0,spendingTag).getValue());
        assertEquals("Wrong description of record","Loan",AppModel.getInstance().getRecord(0,spendingTag).getDescription());
        // size of list should increment
        assertEquals("Wrong list size",1,AppModel.getInstance().getRecordSize(spendingTag));

        // clear all data
        AppModel.getInstance().clearLists();

        // test if it worked

        //get an empty income first record value
        assertEquals("The value of the returned empty record is not 0",0, AppModel.getInstance().getRecord(0,incomeTag).getValue());
        //get an empty income first record description
        assertNull("The description of the returned empty record is not null",AppModel.getInstance().getRecord(0,incomeTag).getDescription());
        // get the size of the income list
        // should be 0 (empty)
        assertEquals("The size of the income list is not 0",0 ,AppModel.getInstance().getRecordSize(incomeTag));

        //get an empty spending first record value
        assertEquals("The value of the returned empty record is not 0",0, AppModel.getInstance().getRecord(0,spendingTag).getValue());
        //get an empty spending first record description
        assertNull("The description of the returned empty record is not null",AppModel.getInstance().getRecord(0,spendingTag).getDescription());
        // get the size of the spending list
        // should be 0 (empty)
        assertEquals("The size of the income list is not 0",0 ,AppModel.getInstance().getRecordSize(spendingTag));

        // clear all data
        AppModel.getInstance().clearLists();
    }

    public void testGetTheWorthBasedOn() {
        // clear all data
        AppModel.getInstance().clearLists();

        // test if it returns 0 for everything
        assertEquals("The value of the year is nor 0",0.0f,AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.YEAR));
        assertEquals("The value of the month is not 0",0.0f,AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.MONTH));
        assertEquals("The value of the day is not 0",0.0f,AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.DAY));
        assertEquals("The value of the hour is not 0",0.0f,AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.HOUR));
        assertEquals("The value of the minute is not 0",0.0f,AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.MINUTE));

        // add 2 income and 1 spending record
        AppModel.getInstance().addRecordValueAndDescriptionByTag(1000,"Salary",incomeTag);
        AppModel.getInstance().addRecordValueAndDescriptionByTag(400,"Rent",incomeTag);
        AppModel.getInstance().addRecordValueAndDescriptionByTag(600,"Credit",spendingTag);

        // 1000 + 400 - 600 = 800 / month
        assertEquals("The value of the month is not 800",800.0f,AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.MONTH));
        // 800 * 12 = 9600 / year
        assertEquals("The value of the year is not 9600",9600.0f,AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.YEAR));
        // 800 / 30 = 26.66 / day
        assertEquals("The value of the day is not 26.66",26.66f,AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.DAY));
        // 26.67 / 24 = 1.11 / hour
        assertEquals("The value of the hour is not 1.11",1.11f,AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.HOUR));
        // 1.11 / 60 = 0.01 / minute
        assertEquals("The value of the minute is not 0.01",0.01f,AppModel.getInstance().getTheWorthBasedOn(AppModel.Granularity.MINUTE));

        // clear all data
        AppModel.getInstance().clearLists(getContext());

    }

}
