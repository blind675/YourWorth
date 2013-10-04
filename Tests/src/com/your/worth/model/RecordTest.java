package com.your.worth.model;

import android.test.AndroidTestCase;

/**
 * Created by Catalin BORA on 9/29/13.
 */
public class RecordTest extends AndroidTestCase {

    public void testCreateRecordEmpty() {

        // create empty record
        Record newRecord = new Record();

        assertNull("Description is not null", newRecord.getDescription());
        assertEquals("Value is not 0", 0, newRecord.getValue());

    }

    public void testCreateRecord() {

        // create record
        Record newRecord = new Record(5,"Loan");

        assertEquals("The description does not match","Loan",newRecord.getDescription());
        assertEquals("Value does not match", 5, newRecord.getValue());
    }
}
