package com.your.worth.controller.listeners;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/8/13
 * Time: 12:55 AM
 */

/**
 * Just added this listener so i don't have to implement all the methods in the activity class
 */
public class TextChangeListener implements TextWatcher{

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

}
