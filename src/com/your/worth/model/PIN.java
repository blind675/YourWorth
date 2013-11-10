package com.your.worth.model;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/6/13
 * Time: 1:05 PM
 */
public class PIN {
    private static final Character[] mPIN = new Character[4];
    private static boolean mPINActive = false;

    public static boolean isPINActive() {
        return mPINActive;
    }

    public static void setPINActive(boolean state) {
        mPINActive = state;
        // IF YOU DEACTIVATE THE PIN IT ALSO GETS RESET
        if(!state){
            mPIN[0] = null;
            mPIN[1] = null;
            mPIN[2] = null;
            mPIN[3] = null;
        }
    }

    public static void setPIN(Character[] pin) {
        // because it's a static
        mPIN[0] = pin[0];
        mPIN[1] = pin[1];
        mPIN[2] = pin[2];
        mPIN[3] = pin[3];
    }

    public static boolean isInternalPINValid(){
        for (int i=0; i<4; i++) {
            if(mPIN[i] == null){
                return false;
            }
        }
        return true;
    }

    /**
     * Compare a given PIN with the one stored locally. Uses samePINs().
     * @param PIN
     * @return
     */
    public static boolean isPINOk(Character[] PIN){
        return samePINs(PIN,mPIN);
    }

    public static boolean isPINComplete(Character[] PIN){
        for (int i=0; i<4; i++) {
            if(PIN[i] == null){
                return false;
            }
        }
        return true;
    }

    /**
     * Helper methode to compare 2 given PINs
     * @param mPINnew first PIN
     * @param mPINconfirm second PIN
     * @return if they match or not
     */
    public  static boolean samePINs(Character[] mPINnew, Character[] mPINconfirm) {
        for (int i=0; i<4; i++) {
            if(mPINnew[i] != mPINconfirm[i]){
                return false;
            }
        }
        return true;
    }
}
