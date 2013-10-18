package com.your.worth.model;

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
	private static AppModel INSTANCE = null;
	
	/**
	 * list of the income records
	 */
	private List<Record> mIncomeList = null;

    /**
     * size of the income list
     */
    private int mIncomeListSize = 0;

	/**
	 * list of the spending records
	 */
	private List<Record> mSpendingList = null;

    /**
     *  size of the spending list
     */
    private int mSpendingListSize = 0;

    /**
     *   public constants for income and spending
     *   should be an enum
     */
    public static final int INCOME = 1;
    public static final int SPENDING = 2;

	/**
	 * just initialize the 2 lists
	 */
	private AppModel() {
		mIncomeList = new ArrayList<Record>();
		mSpendingList = new ArrayList<Record>();
	}
	
	public static AppModel getInstance() {
        if (INSTANCE == null) {
        	INSTANCE = new AppModel();
        }
        return INSTANCE;
	}

    // TODO: way to many methods.. i'm refactoring so it's temporary

    /**
     * Method that units the 2 add methods into one.
     * @param value the value
     * @param description the description
     * @param tag 1=INCOME or 2=SPENDING
     */
    public void addRecordValueAndDescriptionByTag(int value, String description, int tag){
        if(tag == INCOME){
            addIncomeRecordValueAndDescription(value, description);
        } else if(tag == SPENDING) {
            addSpendingRecordValueAndDescription(value, description);
        }
    }

    /**
     * Method that units the 2 remove methods into one.
     * @param index index of the record to be removed
     * @param tag 1=INCOME or 2=SPENDING
     */
    public void removeRecordByTag(int index, int tag){
        if(tag == INCOME){
            removeIncomeRecord(index);
        } else if(tag == SPENDING) {
            removeSpendingRecord(index);
        }
    }

    /**
     * Method that units the 2 get value methods into one.
     * @param index index of the record to be removed
     * @param tag 1=INCOME or 2=SPENDING
     * @return the value of the record
     */
    public int getRecordValue(int index, int tag){
        if(tag == INCOME){
            return getIncomeRecordValue(index);
        } else if(tag == SPENDING) {
            return getSpendingRecordValue(index);
        }
        return 0;
    }

    /**
     * Method that units the 2 get description methods into one.
     * @param index index of the record to be removed
     * @param tag 1=INCOME or 2=SPENDING
     * @return the description of the record
     */
    public String getRecordDescription(int index, int tag) {
        if(tag == INCOME){
            return getIncomeRecordDescription(index);
        } else if(tag == SPENDING) {
            return getSpendingRecordDescription(index);
        }
        return null;
    }

    /**
     * Method that units the 2 get size of the list methods into one.
     * @param tag 1=INCOME or 2=SPENDING
     * @return the size the list
     */
    public int getRecordSize(int tag){
        if(tag == INCOME){
            return getIncomeListSize();
        } else if(tag == SPENDING) {
            return getSpendingListSize();
        }
        return 0;
    }

    // This feels like a duplicate :(. I don't like that there are 2 lists and both have similar methods.

    /**
     * Method that adds an <B>Income</B> record to the income list of the model
     *
     * @param value value of the income
     * @param description description of the income
     */
	public void addIncomeRecordValueAndDescription( int value, String  description){
        if(value!=0){
            Record record = new Record(value,description);
            mIncomeList.add(record);
            mIncomeListSize++;
        }
	}
    /**
     * Method that removes an <B>Income</B> record of the income list of the model
     * @param index the index of the record to be removed
     */
    public void removeIncomeRecord(int index) {
        /* this method will not fail */
        if ( !mIncomeList.isEmpty() && index < mIncomeListSize) {
            mIncomeList.remove(index);
            mIncomeListSize--;
        }
    }
    /**
     * Method that returns the value of an income record given by an index
     * @param index the index of the record
     * @return the value of the record
     */
    public int getIncomeRecordValue(int index) {
       /* this method will never fail :D ( throw exception )
        * 0 values are not relevant therefore ignored  */
       if ( !mIncomeList.isEmpty() && index < mIncomeListSize) {
           return mIncomeList.get(index).getValue();
       }
       return 0;
    }
    /**
     * Method that returns the description of an income record given by an index
     * @param index the index of the record
     * @return the description of the record
     */
    public String getIncomeRecordDescription(int index) {
       /* this method will never fail :D ( throw exception ) */
        if ( !mIncomeList.isEmpty() && index < mIncomeListSize) {
            return mIncomeList.get(index).getDescription();
        }
        return null;
    }
    /**
     * Method that returns the number of income records
     * @return number of income records
     */
    public int getIncomeListSize() {
        return mIncomeListSize;
    }


    /**
     * Method that adds an <B>Spending</B> record to the spending list of the model
     *
     * @param value value of the spending
     * @param description description of the spending
     */
    public void addSpendingRecordValueAndDescription( int value, String  description){
        if(value != 0) {
            Record record = new Record(value,description);
            mSpendingList.add(record);
            mSpendingListSize++;
        }
    }
    /**
     * Method that removes an <B>Spending</B> record of the spending list of the model
     * @param index the index of the record to be removed
     */
    public void removeSpendingRecord(int index) {
        /* this method will not fail */
        if ( !mSpendingList.isEmpty() && index < mSpendingListSize) {
            mSpendingList.remove(index);
            mSpendingListSize--;
        }
    }
    /**
     * Method that returns the value of an spending record given by an index
     * @param index the index of the record
     * @return the value of the record
     */
    public int getSpendingRecordValue(int index) {
       /* this method will never fail :D ( throw exception )
        * 0 values are not relevant therefore ignored  */
        if ( !mSpendingList.isEmpty() && index < mSpendingListSize) {
            return mSpendingList.get(index).getValue();
        }
        return 0;
    }
    /**
     * Method that returns the description of an spending record given by an index
     * @param index the index of the record
     * @return the description of the record
     */
    public String getSpendingRecordDescription(int index) {
       /* this method will never fail :D ( throw exception ) */
        if ( !mSpendingList.isEmpty() && index < mSpendingListSize) {
            return mSpendingList.get(index).getDescription();
        }
        return null;
    }
    /**
     * Method that returns the number of spending records
     * @return number of spending records
     */
    public int getSpendingListSize() {
        return mSpendingListSize;
    }
    /**
     * Method that erases the lists (used for test, for now needed because it's a singleton)
     */
     public void clearLists() {
         //make the list sizes o
         mIncomeListSize = 0;
         mSpendingListSize = 0;
         //reset the lists
         mIncomeList = new ArrayList<Record>();
         mSpendingList = new ArrayList<Record>();
     }
}
	