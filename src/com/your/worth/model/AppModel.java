package com.your.worth.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    // preferences identifiers
    private static SharedPreferences mSettings;

    // check if SQL data was loaded
    private static boolean mDataAlreadyLoaded = false;

    // list of the income records
    private List<Record> mIncomeList = null;

    // list of the spending records
    private List<Record> mSpendingList = null;

    // Database fields
    private SQLiteDatabase mDatabase;
    private SQLiteHelper mDbHelper;

    private final String[] mAllColumns = {
            SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_VALUE,
            SQLiteHelper.COLUMN_DESCRIPTION,
            SQLiteHelper.COLUMN_TYPE,
            SQLiteHelper.COLUMN_DATE,
            SQLiteHelper.COLUMN_MODIFIED};

    /**
     *   public constants for income and spending
     *   should be an enum
     */
    public static final int INCOME = 1;
    public static final int SPENDING = 2;

    public enum Granularity{HOUR,MINUTE,DAY,MONTH,YEAR}

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
     * It also looks for the PIN and loads it if it exists.
     * @param context the context of the caller
     */
    public void loadDataBase(Context context) {

        // only load the data once
        if(!mDataAlreadyLoaded) {
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
                // get the date of the record as string
                String date = cursor.getString(4);
                // get the modified field
                boolean modified = (cursor.getInt(5) == 1);

                // get the ID of the record ( don't need here .. or do i)
                cursor.getString(0);
                // create a record
                Record record = new Record(value,description,date,modified);

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

        // now get the PIN
        // Restore preferences
        int digit;
        mSettings  = context.getSharedPreferences("preference", Context.MODE_PRIVATE);

        Character[] pin = new Character[4];
        for (int i=0;i<4;i++) {
            digit = mSettings.getInt("digit"+(i+1), -1);
            if(digit != -1){
                pin[i] = Character.forDigit(digit, 10);
            }
        }

        if(PIN.isPINComplete(pin)){
            PIN.setPIN(pin);
            PIN.setPINActive(true);
        }
    }

    /**
     * Write the PIN to the property file
     * @param pin the PIN
     */
    public void setPIN(Character[] pin){

        if(PIN.isPINComplete(pin)) {
            // We need an Editor object to make preference changes.
            SharedPreferences.Editor editor = mSettings.edit();

            // Write the digits
            editor.putInt("digit1",Character.getNumericValue(pin[0]));
            editor.putInt("digit2",Character.getNumericValue(pin[1]));
            editor.putInt("digit3",Character.getNumericValue(pin[2]));
            editor.putInt("digit4",Character.getNumericValue(pin[3]));

            // Commit the edits!
            editor.commit();

            // Set the value of the pin in the static PIN class
            PIN.setPIN(pin);
        }
    }

    /**
     * It deactivates the PIN. Sets the PIN active flag to false and erases it from the property file
     */
    public void deactivatePIN(){
        // We need an Editor object to make preference changes.
        SharedPreferences.Editor editor = mSettings.edit();

        // Remove the values
        editor.remove("digit1");
        editor.remove("digit2");
        editor.remove("digit3");
        editor.remove("digit4");

        // Commit the edits!
        editor.commit();

        PIN.setPINActive(false);
    }

    /**
     * Method that adds a record to the list. It determines the list type based on the tag property.
     * @param value the value
     * @param description the description
     * @param tag 1=INCOME or 2=SPENDING
     */
    public void addRecordValueAndDescriptionByTag(int value, String description, int tag){
        Date today = new Date();

        // the string representation of date (month/day/year)
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        if(tag == INCOME){
            //Add an <B>Income</B> record to the income list of the model
            if(value!=0){
                Record record = new Record(value,description,dateFormat.format(today),false);
                mIncomeList.add(0,record);
                // now add the values to the DB
                if(mDatabase != null) {
                    ContentValues values = new ContentValues();
                    values.put(SQLiteHelper.COLUMN_ID,value+description+"IN");
                    values.put(SQLiteHelper.COLUMN_VALUE,value);
                    values.put(SQLiteHelper.COLUMN_DESCRIPTION,description);
                    values.put(SQLiteHelper.COLUMN_DATE,dateFormat.format(today));
                    values.put(SQLiteHelper.COLUMN_MODIFIED,false);
                    values.put(SQLiteHelper.COLUMN_TYPE,"IN");
                    mDatabase.insert(SQLiteHelper.TABLE_WORTH, null, values);
                }
            }
        } else if(tag == SPENDING) {
            //Add an <B>Spending</B> record to the spending list of the model
            if(value != 0) {
                Record record = new Record(value,description,dateFormat.format(today),false);
                mSpendingList.add(0,record);
                // now add the values to the DB
                if(mDatabase != null) {
                    ContentValues values = new ContentValues();
                    values.put(SQLiteHelper.COLUMN_ID,value+description+"SP" );
                    values.put(SQLiteHelper.COLUMN_VALUE,value);
                    values.put(SQLiteHelper.COLUMN_DESCRIPTION,description);
                    values.put(SQLiteHelper.COLUMN_DATE,dateFormat.format(today));
                    values.put(SQLiteHelper.COLUMN_MODIFIED,false);
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
                            SQLiteHelper.COLUMN_ID + " = \'" +
                                    mSpendingList.get(index).getValue()+
                                    mSpendingList.get(index).getDescription()+
                                    "SP\'", null);
                }
                mSpendingList.remove(index);
            }
        }
    }

//    /**
//     * Method that gets value of a record from the list. It determines the list type based on the tag property.
//     * @param index index of the record to be removed
//     * @param tag 1=INCOME or 2=SPENDING
//     * @return the value of the record
//     */
//    public int getRecordValue(int index, int tag){
//        if(tag == INCOME){
//            // Returns the value of an income record given by an index
//            /* this method will never fail :D ( throw exception )
//            * 0 values are not relevant therefore ignored  */
//            if ( !mIncomeList.isEmpty() && index < mIncomeList.size()) {
//                return mIncomeList.get(index).getValue();
//            }
//            return 0;
//        } else if(tag == SPENDING) {
//            // Returns the value of an spending record given by an index
//            /* this method will never fail :D ( throw exception )
//            * 0 values are not relevant therefore ignored  */
//            if ( !mSpendingList.isEmpty() && index < mSpendingList.size()) {
//                return mSpendingList.get(index).getValue();
//            }
//            return 0;
//        }
//        return 0;
//    }
//
//    /**
//     * Method that gets description of a record from the list. It determines the list type based on the tag property.
//     * @param index index of the record to be removed
//     * @param tag 1=INCOME or 2=SPENDING
//     * @return the description of the record
//     */
//    public String getRecordDescription(int index, int tag) {
//        if(tag == INCOME){
//            // Returns the description of an income record given by an index
//            /* this method will never fail :D ( throw exception ) */
//            if ( !mIncomeList.isEmpty() && index < mIncomeList.size()) {
//                return mIncomeList.get(index).getDescription();
//            }
//            return null;
//        } else if(tag == SPENDING) {
//            // Returns the description of an spending record given by an index
//            /* this method will never fail :D ( throw exception ) */
//            if ( !mSpendingList.isEmpty() && index < mSpendingList.size()) {
//                return mSpendingList.get(index).getDescription();
//            }
//            return null;
//        }
//        return null;
//    }
//
//    /**
//     * Method that returns value of modified field of a record from the list.
//     * It determines the list type based on the tag property.
//     * @param index index of the record to be removed
//     * @param tag 1=INCOME or 2=SPENDING
//     * @return the value of modified field
//     */
//    public boolean isRecordModified(int index, int tag){
//        if(tag == INCOME){
//            if ( !mIncomeList.isEmpty() && index < mIncomeList.size()) {
//                return mIncomeList.get(index).isModified();
//            }
//            return false;
//        } else if(tag == SPENDING) {
//            if ( !mSpendingList.isEmpty() && index < mSpendingList.size()) {
//                return mSpendingList.get(index).isModified();
//            }
//            return false;
//        }
//        return false;
//    }
//
//    /**
//     * Method that gets the date of a record from the list. It determines the list type based on the tag property.
//     * @param index index of the record to be removed
//     * @param tag 1=INCOME or 2=SPENDING
//     * @return the date of the record
//     */
//    public String getRecordDate(int index, int tag) {
//        if(tag == INCOME){
//            // Returns the date of an income record given by an index
//            /* this method will never fail :D ( throw exception ) */
//            if ( !mIncomeList.isEmpty() && index < mIncomeList.size()) {
//                return mIncomeList.get(index).getDate();
//            }
//            return null;
//        } else if(tag == SPENDING) {
//            // Returns the date of an spending record given by an index
//            /* this method will never fail :D ( throw exception ) */
//            if ( !mSpendingList.isEmpty() && index < mSpendingList.size()) {
//                return mSpendingList.get(index).getDate();
//            }
//            return null;
//        }
//        return null;
//    }

    /**
     * Method that gets the record from the list. It determines the list type based on the tag property.
     * @param index index of the record to be removed
     * @param tag 1=INCOME or 2=SPENDING
     * @return the record
     */
    public Record getRecord(int index, int tag) {
        if(tag == INCOME){
            // Returns the description of an income record given by an index
            /* this method will never fail :D ( throw exception ) */
            if ( !mIncomeList.isEmpty() && index < mIncomeList.size()) {
                return mIncomeList.get(index);
            }
            return new Record();
        } else if(tag == SPENDING) {
            // Returns the description of an spending record given by an index
            /* this method will never fail :D ( throw exception ) */
            if ( !mSpendingList.isEmpty() && index < mSpendingList.size()) {
                return mSpendingList.get(index);
            }
            return new Record();
        }
        return new Record();
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
     * Method that erases the lists
     *@param context the context of the caller
     */
    public void clearLists(Context context) {
        // reset the lists
        mIncomeList = new ArrayList<Record>();
        mSpendingList = new ArrayList<Record>();

        // delete all data from the DB
        if (mDbHelper == null) {
            mDbHelper = new SQLiteHelper(context);
            mDatabase = mDbHelper.getWritableDatabase();
        }
        mDatabase.delete(SQLiteHelper.TABLE_WORTH, null, null);
    }

    /**
     * Method that erases the lists (used for test, for now needed because it's a singleton)
     */
    public void clearLists() {
        // reset the lists
        mIncomeList = new ArrayList<Record>();
        mSpendingList = new ArrayList<Record>();
    }

    /**
     * Method that returns the amount of money you make in the granularity period of time.
     * @param granularity the granularity (YEAR,MONTH,DAY,HOUR)
     * @return value of the worth with 2 decimal place
     */
    public float getTheWorthBasedOn(Granularity granularity){
        int monthWorth = 0;
        BigDecimal bigDecimal;
        float returnValue;

        // add the all the incomes
        for (Record income : mIncomeList){
            monthWorth += income.getValue();
        }

        // subtract all the spending
        for (Record income : mSpendingList){
            monthWorth -= income.getValue();
        }

        switch (granularity){
            case YEAR:      returnValue = monthWorth * 12;
                break;
            case MONTH:     returnValue = monthWorth;
                break;
            case DAY:       bigDecimal = new BigDecimal(Float.toString((float)monthWorth/30));
                // for simplicity there are 30 days in a month
                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
                // round down so it motivates users, a little
                returnValue = bigDecimal.floatValue();
                break;
            case HOUR:      bigDecimal = new BigDecimal(Float.toString(((float)monthWorth/30)/24));
                // for simplicity there are 30 days in a month and 24 hours in a day
                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
                // round down so it motivates users, a little
                returnValue = bigDecimal.floatValue();
                break;
            case MINUTE:    bigDecimal = new BigDecimal(Float.toString((((float)monthWorth/30)/24)/60));
                // for simplicity there are 30 days in a month , 24 hours in a day and 60 minutes in an hour
                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
                // round down so it motivates users, a little
                returnValue = bigDecimal.floatValue();
                break;
            default:        returnValue =  0;
        }
        return returnValue;
    }
}