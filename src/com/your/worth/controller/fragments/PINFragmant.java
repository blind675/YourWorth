package com.your.worth.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.your.worth.R;
import com.your.worth.model.AppModel;
import com.your.worth.model.PIN;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/8/13
 * Time: 3:18 AM
 */
public class PINFragmant extends Fragment {

    private Button mChange = null;
    private ImageView mOKIconOld = null;
    private ImageView mOKIconNew = null;
    private ImageView mOKIconConf = null;
    private TextView mOldPIN = null;

    private final Character[] mPINold = new Character[4];
    private final Character[] mPINnew = new Character[4];
    private final Character[] mPINconfirm = new Character[4];

    private EditText mPINold1 = null;
    private EditText mPINold2 = null;
    private EditText mPINold3 = null;
    private EditText mPINold4 = null;

    private EditText mPINnew1 = null;
    private EditText mPINnew2 = null;
    private EditText mPINnew3 = null;
    private EditText mPINnew4 = null;

    private EditText mPINconfirm1 = null;
    private EditText mPINconfirm2 = null;
    private EditText mPINconfirm3 = null;
    private EditText mPINconfirm4 = null;

    private boolean mOldPINOk = false;
    private boolean mNewPINOk = false;

    /**
     * Called when the fragment is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View thePINView = inflater.inflate(R.layout.part_pin, container, false);

        mOKIconOld = (ImageView) thePINView.findViewById(R.id.approve_icon_old);
        mOKIconNew = (ImageView) thePINView.findViewById(R.id.approve_icon_new);
        mOKIconConf = (ImageView) thePINView.findViewById(R.id.approve_icon_conf);
        mChange = (Button) thePINView.findViewById(R.id.loginButton);
        mOldPIN = (TextView) thePINView.findViewById(R.id.oldPINLable);

        // get the text fields
        mPINold1 = (EditText) thePINView.findViewById(R.id.digit_one);
        mPINold2 = (EditText) thePINView.findViewById(R.id.digit_two);
        mPINold3 = (EditText) thePINView.findViewById(R.id.digit_tree);
        mPINold4 = (EditText) thePINView.findViewById(R.id.digit_four);

        mPINnew1 = (EditText) thePINView.findViewById(R.id.digit_one_new);
        mPINnew2 = (EditText) thePINView.findViewById(R.id.digit_two_new);
        mPINnew3 = (EditText) thePINView.findViewById(R.id.digit_tree_new);
        mPINnew4 = (EditText) thePINView.findViewById(R.id.digit_four_new);

        mPINconfirm1 = (EditText) thePINView.findViewById(R.id.digit_one_conf);
        mPINconfirm2 = (EditText) thePINView.findViewById(R.id.digit_two_conf);
        mPINconfirm3 = (EditText) thePINView.findViewById(R.id.digit_tree_conf);
        mPINconfirm4 = (EditText) thePINView.findViewById(R.id.digit_four_conf);

        // set event listeners on all the fields - for the old text boxes first
        mPINold1.addTextChangedListener(new TextChangeListener(0,false,true,mPINold2));
        mPINold2.addTextChangedListener(new TextChangeListener(1,false,true,mPINold3));
        mPINold3.addTextChangedListener(new TextChangeListener(2,false,true,mPINold4));
        mPINold4.addTextChangedListener(new TextChangeListener(3,false,true,mPINold4));

        // now set event listeners for the new PIN fields
        mPINnew1.addTextChangedListener(new TextChangeListener(0,true,false,mPINnew2));
        mPINnew2.addTextChangedListener(new TextChangeListener(1,true,false,mPINnew3));
        mPINnew3.addTextChangedListener(new TextChangeListener(2,true,false,mPINnew4));
        mPINnew4.addTextChangedListener(new TextChangeListener(3,true,false,mPINconfirm1));

        mPINconfirm1.addTextChangedListener(new TextChangeListener(0,false,false,mPINconfirm2));
        mPINconfirm2.addTextChangedListener(new TextChangeListener(1,false,false,mPINconfirm3));
        mPINconfirm3.addTextChangedListener(new TextChangeListener(2,false,false,mPINconfirm4));
        mPINconfirm4.addTextChangedListener(new TextChangeListener(3,false,false,null));

        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Call the changePIN with the view parameter
                changePin();
            }
        });

        return thePINView;
    }

    private void changePin() {

        if(mOldPINOk && mNewPINOk) {
            AppModel.getInstance().setPIN(mPINnew);
            Toast.makeText(getActivity().getApplicationContext(),getString(R.string.pin_changed),
                    Toast.LENGTH_LONG).show();
        }
        // just setTexColor don't work (bug or lack of documentation)
        mOldPIN.setTextColor(getResources().getColor(R.color.black));
        mPINold1.setEnabled(true);
        mPINold2.setEnabled(true);
        mPINold3.setEnabled(true);
        mPINold4.setEnabled(true);

        mPINold1.setBackgroundResource(R.drawable.back);
        mPINold2.setBackgroundResource(R.drawable.back);
        mPINold3.setBackgroundResource(R.drawable.back);
        mPINold4.setBackgroundResource(R.drawable.back);

        clearFields();
    }

    /**
     * Selects if the PIN is checked with the one stored in PIN static class or the one entered before.
     * Something like a dispatcher.
     * @param value the digit in a specific position
     * @param place the position of the value
     * @param firstPINField if the digit belongs to the first or second PIN array
     * @param compareWithOldPIN compare with one stored or not
     */
    private void selectPINChecker(Character value, int place, boolean firstPINField, boolean compareWithOldPIN){
        if(compareWithOldPIN){
            validatePIN(value,place);
        } else {
            validateNewPIN(value,place,firstPINField);
        }
    }

    /**
     * Validate the new PINs (compare one with each other)
     * @param value the digit in a specific position
     * @param place the position of the value
     * @param firstPINField if the digit belongs to the first or second array
     */
    private void validateNewPIN(Character value, int place, boolean firstPINField) {
        if(firstPINField) {
            mPINnew[place]=value;
        } else {
            mPINconfirm[place]=value;
        }

        if(PIN.isPINComplete(mPINnew) && PIN.isPINComplete(mPINconfirm) ){
            mOKIconNew.setVisibility(View.VISIBLE);
            mOKIconConf.setVisibility(View.VISIBLE);
            if(PIN.samePINs(mPINnew,mPINconfirm)){
                mOKIconNew.setImageResource(R.drawable.pin_good);
                mOKIconConf.setImageResource(R.drawable.pin_good);
                mNewPINOk = true;
            } else {
                mOKIconNew.setImageResource(R.drawable.pin_bad);
                mOKIconConf.setImageResource(R.drawable.pin_bad);
                mNewPINOk = false;
            }
        } else {
            mOKIconNew.setVisibility(View.GONE);
            mOKIconConf.setVisibility(View.GONE);
            mNewPINOk = false;
        }

        if(mOldPINOk && mNewPINOk) {
            mChange.setEnabled(true);
        } else {
            mChange.setEnabled(false);
        }
    }

    /**
     * Validate the PIN entry
     * @param value the digit in a specific position
     * @param place the position of the value
     */
    private void validatePIN(Character value, int place) {
        mPINold[place]=value;
        if(PIN.isPINComplete(mPINold)){
            mOKIconOld.setVisibility(View.VISIBLE);
            if(PIN.isPINOk(mPINold)){
                mOKIconOld.setImageResource(R.drawable.pin_good);
                mOldPINOk = true;
            } else {
                mOKIconOld.setImageResource(R.drawable.pin_bad);
                mOldPINOk = false;
            }
        } else {
            mOKIconOld.setVisibility(View.GONE);
            mOldPINOk = false;
        }

        if(mOldPINOk && mNewPINOk) {
            mChange.setEnabled(true);
        } else {
            mChange.setEnabled(false);
        }
    }

    /**
     * Refresh the text fields
     */
    @Override
    public void onResume() {
        super.onResume();
        clearFields();
    }

    private void clearFields() {

        mOldPINOk = false;
        mNewPINOk = false;

        mPINnew1.setText("");
        mPINnew2.setText("");
        mPINnew3.setText("");
        mPINnew4.setText("");

        mPINold1.setText("");
        mPINold2.setText("");
        mPINold3.setText("");
        mPINold4.setText("");

        mPINconfirm1.setText("");
        mPINconfirm2.setText("");
        mPINconfirm3.setText("");
        mPINconfirm4.setText("");

        mChange.setEnabled(false);

        if(!PIN.isInternalPINValid()) {
            mOldPIN.setTextColor(getResources().getColor(R.color.lightGray));

            mPINold1.setEnabled(false);
            mPINold1.setBackgroundResource(R.drawable.grayback);
            mPINold2.setEnabled(false);
            mPINold2.setBackgroundResource(R.drawable.grayback);
            mPINold3.setEnabled(false);
            mPINold3.setBackgroundResource(R.drawable.grayback);
            mPINold4.setEnabled(false);
            mPINold4.setBackgroundResource(R.drawable.grayback);
            mOldPINOk = true;
        }
    }

    /**
     * Listener for all the text fields
     */
    public class TextChangeListener implements TextWatcher {

        private int mPos;
        private boolean mFirstPINField;
        private boolean mCompareWithOldPIN;
        private EditText mText;

        private TextChangeListener(int pos, boolean firstPINField, boolean compareWithOldPIN, EditText textToSelectNext){
            mPos = pos;
            mCompareWithOldPIN = compareWithOldPIN;
            mFirstPINField = firstPINField;
            mText = textToSelectNext;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if(charSequence.length()!=0){
                selectPINChecker(charSequence.charAt(0),mPos,mFirstPINField,mCompareWithOldPIN);
                if(mText != null) {
                    mText.requestFocus();
                    mText.selectAll();
                }
            } else {
                selectPINChecker(null,mPos,mFirstPINField,mCompareWithOldPIN);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }
}
