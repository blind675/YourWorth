package com.your.worth.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author catalinbora
 *
 *	singleton class that holds the data of the application
 *	the singleton part can be argued is useless but it seemed a nice exercise 
 */

public class AppModel {

	/**
	 * singleton instance
	 */
	private static final AppModel INSTANCE = new AppModel();

	// list of the income records
	private List<Record> mIncomeList = null;

	// list of the spending records
	private List<Record> mSpendingList = null;

    // Database fields
    private SQLiteDatabase mDatabase;
    private SQLiteHelper mDbHelper;

    private String[] mAllColumns = {
        SQLiteHelper.COLUMN_ID,
        SQLiteHelper.COLUMN_VALUE,
        SQLiteHelper.COLUMN_DESCRIPTION,
        SQLiteHelper.COLUMN_TYPE};

    /**
     *   public constants for income and spending
     *   should be an enum
     */
    public static final int INCOME = 1;
    public static final int SPENDING = 2;

    public enum Granularity{HOUR,MINUTE,DAY,MONTH,YEAR};

	/**
	 * just initialize the 2 lists
	 */
	private AppModel() {
		mIncomeList = new ArrayList<Record>();
		mSpendingList = new ArrayList<Record>();
	}
	
	public static AppModel getInstance() {
        return INSTANCE;
	}

    /**
     * Method that initializes the database and loads all its records in the AppModel internal arrays.
     * @param context the context of the caller
     */
    public void loadDataBase(Context context) {
        if (mDbHelper == null) {
            mDbHelper = new SQLiteHelper(context);
            mDatabase = mDbHelper.getWritableDatabase();
        }

        Cursor cursor = mDatabase.query(SQLiteHelper.TABLE_WORTH,
                mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            // get the description of the record
            String description = cursor.getString(2);
            // get the value of the record
            int value = cursor.getInt(1);
            // get the type of the record
            String type = cursor.getString(3);
            // get the ID of the record ( don't need here .. or do i)
            cursor.getString(0);
            // create a record
            Record record = new Record(value,description);

            // test the type of the record and store it accordingly
            if(type.equals("IN")) {
                // add the to the income array since type is IN
                mIncomeList.add(record);
            } else if(type.equals("SP")) {
                // add the to the income array since type is SP
                mSpendingList.add(record);
            }
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
    }

    /**
     * Method that adds a record to the list. It determines the list type based on the tag property.
     * @param value the value
     * @param description the description
     * @param tag 1=INCOME or 2=SPENDING
     */
    public void addRecordValueAndDescriptionByTag(int value, String description, int tag){
        if(tag == INCOME){
            //Add an <B>Income</B> record to the income list of the model
            if(value!=0){
                Record record = new Record(value,description);
                mIncomeList.add(record);
                // now add the values to the DB
                if(mDatabase != null) {
                    ContentValues values = new ContentValues();
                    values.put(SQLiteHelper.COLUMN_ID,value+description+"IN");
                    values.put(SQLiteHelper.COLUMN_VALUE,value);
                    values.put(SQLiteHelper.COLUMN_DESCRIPTION,description);
                    values.put(SQLiteHelper.COLUMN_TYPE,"IN");
                    mDatabase.insert(SQLiteHelper.TABLE_WORTH, null, values);
                }
            }
        } else if(tag == SPENDING) {
             //Add an <B>Spending</B> record to the spending list of the model
            if(value != 0) {
                Record record = new Record(value,description);
                mSpendingList.add(record);
                // now add the values to the DB
                if(mDatabase != null) {
                    ContentValues values = new ContentValues();
                    values.put(SQLiteHelper.COLUMN_ID,value+description+"SP" );
                    values.put(SQLiteHelper.COLUMN_VALUE,value);
                    values.put(SQLiteHelper.COLUMN_DESCRIPTION,description);
                    values.put(SQLiteHelper.COLUMN_TYPE,"SP");
                    mDatabase.insert(SQLiteHelper.TABLE_WORTH, null, values);
                }
            }
        }
    }

    /**
     * Method that remove a record from the list. It determines the list type based on the tag property.
     * @param index index of the record to be removed
     * @param tag 1=INCOME or 2=SPENDING
     */
    public void removeRecordByTag(int index, int tag){
        if(tag == INCOME){
            //Remove an <B>Income</B> record of the income list of the model
            /* this method will not fail */
            if ( !mIncomeList.isEmpty() && index < mIncomeList.size()) {
                // first add the values to the DB
                if(mDatabase != null) {
                    mDatabase.delete(SQLiteHelper.TABLE_WORTH,
                        SQLiteHelper.COLUMN_ID + " = \'" +
                            mIncomeList.get(index).getValue()+
                            mIncomeList.get(index).getDescription()+
                            "IN\'", null);
                }
                mIncomeList.remove(index);
            }
        } else if(tag == SPENDING) {
            //Remove an <B>Spending</B> record of the spending list of the model
            /* this method will not fail */
            if ( !mSpendingList.isEmpty() && index < mSpendingList.size()) {
                // now add the values to the DB
                if(mDatabase != null) {
                    mDatabase.delete(SQLiteHelper.TABLE_WORTH,
                            SQLiteHelper.COLUMN_ID + " = " +
                                    mIncomeList.get(index).getValue()+
                                    mIncomeList.get(index).getDescription()+
                                    "SP", null);
                }
                mSpendingList.remove(index);
            }
        }
    }

    /**
     * Method that gets value of a record from the list. It determines the list type based on the tag property.
     * @param index index of the record to be removed
     * @param tag 1=INCOME or 2=SPENDING
     * @return the value of the record
     */
    public int getRecordValue(int index, int tag){
        if(tag == INCOME){
            // Returns the value of an income record given by an index
            /* this method will never fail :D ( throw exception )
            * 0 values are not relevant therefore ignored  */
            if ( !mIncomeList.isEmpty() && index < mIncomeList.size()) {
                return mIncomeList.get(index).getValue();
            }
            return 0;
        } else if(tag == SPENDING) {
            // Returns the value of an spending record given by an index
            /* this method will never fail :D ( throw exception )
            * 0 values are not relevant therefore ignored  */
            if ( !mSpendingList.isEmpty() && index < mSpendingList.size()) {
                return mSpendingList.get(index).getValue();
            }
            return 0;
        }
        return 0;
    }

    /**
     * Method that gets description of a record from the list. It determines the list type based on the tag property.
     * @param index index of the record to be removed
     * @param tag 1=INCOME or 2=SPENDING
     * @return the description of the record
     */
    public String getRecordDescription(int index, int tag) {
        if(tag == INCOME){
            // Returns the description of an income record given by an index
            /* this method will never fail :D ( throw exception ) */
            if ( !mIncomeList.isEmpty() && index < mIncomeList.size()) {
                return mIncomeList.get(index).getDescription();
            }
            return null;
        } else if(tag == SPENDING) {
            // Returns the description of an spending record given by an index
            /* this method will never fail :D ( throw exception ) */
            if ( !mSpendingList.isEmpty() && index < mSpendingList.size()) {
                return mSpendingList.get(index).getDescription();
            }
            return null;
        }
        return null;
    }

    /**
     * Method that gets size of the list. It determines the list type based on the tag property.
     * @param tag 1=INCOME or 2=SPENDING
     * @return the size the list
     */
    public int getRecordSize(int tag){
        if(tag == INCOME){
            // Returns the number of income records
            return mIncomeList.size();
        } else if(tag == SPENDING) {
            // Returns the number of spending records
            return mSpendingList.size();
        }
        return 0;
    }

    /**
     * Method that erases the lists (used for test, for now needed because it's a singleton)
     */
     public void clearLists() {
         //reset the lists
         mIncomeList = new ArrayList<Record>();
         mSpendingList = new ArrayList<Record>();
     }

     // TODO: Javadoc and implementation but first create test
     public float getTheWorthBasedOn(Granularity granularity){
         return 0;
     }
}
	