package com.your.worth.model;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/6/13
 * Time: 1:05 PM
 */
public class PIN {
    private static int mPIN = 0;
    private static boolean mPINActive = false;

    public static boolean isPINActive() {
        return mPINActive;
    }

    public static void setPINActive(boolean state) {
        mPINActive = state;
    }
}
