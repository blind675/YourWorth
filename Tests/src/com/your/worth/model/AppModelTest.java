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
        //TODO: create getIncomeRecordDescription() method
        assertEquals("The value of the returned empty record value is not 0",0, AppModel.getInstance().getIncomeRecordDescription(0));
    }
}
