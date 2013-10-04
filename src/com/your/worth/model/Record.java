package com.your.worth.model;

/**
 * @author catalinbora
 *
 * This is like a DTO .. i could probably be generated
 */
public class Record {
	
	private int mValue;
	private String mDescription;

    /**
     * Constructor for the Record.
     * @param value value of the record
     * @param description description of the record
     */
	public Record(int value, String description) {
		mValue = value;
		mDescription = description;
	}

    /**
     * Empty constructor for the Record.
     */
	public Record() {
		this(0,null);
	}
	
	/*
	 * this methods are generated, thank God for IDEs :D
	 */
	public int getValue() {
		return mValue;
	}
	public String getDescription() {
		return mDescription;
	}
	
}
