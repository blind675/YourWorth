package com.your.worth.model;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/6/13
 * Time: 1:05 PM
 */
public class PIN {
    private static Character[] mPIN = {'0','2','0','6'};
    private static boolean mPINActive = true;

    public static boolean isPINActive() {
        return mPINActive;
    }

    public static void setPINActive(boolean state) {
        mPINActive = state;
    }

    public static boolean isPINOk(Character[] PIN){

        for (int i=0; i<4; i++) {
            if(PIN[i] != mPIN[i]){
                return false;
            }
        }

        return true;
    }

    public static boolean isPINComplete(Character[] PIN){
        for (int i=0; i<4; i++) {
            if(PIN[i] == null){
                return false;
            }
        }

        return true;
    }
}
