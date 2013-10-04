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
	private List<Record> incomeList = null;
	
	/**
	 * list of the spending records
	 */
	private List<Record> spendingList = null;
	
	/**
	 * just initialize the 2 lists
	 */
	private AppModel() {
		incomeList = new ArrayList<Record>();
		spendingList = new ArrayList<Record>();
	}
	
	public static AppModel getInstance() {
        if (INSTANCE == null) {
        	INSTANCE = new AppModel();
        }
        return INSTANCE;
	}

    // This feels like a duplicate :(. I don't like that there are 2 lists and both have similar methods.

    /**
     * Method that adds an <B>Income</B> record to the income list of the model
     *
     * @param value value of the income
     * @param description description of the income
     */
	public void setIncomeValueAndDescription( int value, String  description){
		Record record = new Record(value,description);
        incomeList.add(record);
	}

    /**
     * Method that adds an <B>Spending</B> record to the spending list of the model
     *
     * @param value value of the spending
     * @param description description of the spending
     */
	public void setSpendingValueAndDescription( int value, String  description){
		Record record = new Record(value,description);
		spendingList.add(record);
	}

}
	
	