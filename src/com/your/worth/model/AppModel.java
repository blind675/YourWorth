package com.your.worth.model;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author catalinbora
 *
 *	singleton class that holds the data of the application
 *	the singleton part can be argued is useless but it seemed a nice exercise 
 */
//TODO: unit tests
//TODO: TDD - test driven development
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
	
	//This feels like a duplicate :(
	//TODO: add java doc
	
	public void setIncomeValueAndDescription( int value, String  description){
		Record record = new Record(value,description);
		incomeList.add(record);
	}
	
	public void setIncomeValueAndDescription(Record record){
		incomeList.add(record);
	}
	
	public void setSpendingValueAndDescription( int value, String  description){
		Record record = new Record(value,description);
		spendingList.add(record);
	}
	
	public void setSpendingValueAndDescription(Record record){
		spendingList.add(record);
	}
}
	
	