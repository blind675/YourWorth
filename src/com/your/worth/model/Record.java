package com.your.worth.model;

/**
 * @author catalinbora
 *
 * This is like a DTO .. i could probably be generated
 */
public class Record {
	
	private final int mValue;
	private final String mDescription;
    private final String mDate;
    private final boolean mModified;

    /**
     * Constructor for the Record.
     * @param value value of the record
     * @param description description of the record
     */
	public Record(int value, String description,String date, boolean modified) {
		mValue = value;
		mDescription = description;
        mDate = date;
        mModified = modified;
	}

    /**
     * Empty constructor for the Record.
     */
	public Record() {
		this(0,null,null,false);
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
    public String getDate() {
        return mDate;
    }
	public boolean isModified() {
        return mModified;
    }
}
