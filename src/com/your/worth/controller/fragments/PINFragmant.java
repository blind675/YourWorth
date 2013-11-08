package com.your.worth.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.your.worth.R;
import com.your.worth.controller.listeners.TextChangeListener;
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

    private Character[] mPINold = new Character[4];
    private Character[] mPINnew = new Character[4];
    private Character[] mPINconfirm = new Character[4];

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

        // TODO:seriously must find a better solution for this
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
        mPINold1.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    validatePIN(s.charAt(0), 0);
                } else {
                    validatePIN(null, 0);
                }
                mPINold1.selectAll();
            }
        });
        mPINold2.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    validatePIN(s.charAt(0), 1);
                } else {
                    validatePIN(null, 1);
                }
                mPINold2.selectAll();
            }
        });
        mPINold3.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    validatePIN(s.charAt(0), 2);
                } else {
                    validatePIN(null, 2);
                }
                mPINold3.selectAll();
            }
        });
        mPINold4.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    validatePIN(s.charAt(0), 3);
                } else {
                    validatePIN(null, 3);
                }
                mPINold4.selectAll();
            }
        });

        // now set event listeners for the new PIN fields
        mPINnew1.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    validateNewPIN(s.charAt(0), 0, true);
                } else {
                    validateNewPIN(null, 0, true);
                }
                mPINnew1.selectAll();
            }
        });
        mPINnew2.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    validateNewPIN(s.charAt(0), 1, true);
                } else {
                    validateNewPIN(null, 1, true);
                }
                mPINnew2.selectAll();
            }
        });
        mPINnew3.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    validateNewPIN(s.charAt(0), 2, true);
                } else {
                    validateNewPIN(null, 2, true);
                }
                mPINnew3.selectAll();
            }
        });
        mPINnew4.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    validateNewPIN(s.charAt(0), 3, true);
                } else {
                    validateNewPIN(null, 3, true);
                }
                mPINnew4.selectAll();
            }
        });

        mPINconfirm1.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    validateNewPIN(s.charAt(0), 0, false);
                } else {
                    validateNewPIN(null, 0, false);
                }
                mPINconfirm1.selectAll();
            }
        });
        mPINconfirm2.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    validateNewPIN(s.charAt(0), 1, false);
                } else {
                    validateNewPIN(null, 1, false);
                }
                mPINconfirm2.selectAll();
            }
        });
        mPINconfirm3.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    validateNewPIN(s.charAt(0), 2, false);
                } else {
                    validateNewPIN(null, 2, false);
                }
                mPINconfirm3.selectAll();
            }
        });
        mPINconfirm4.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    validateNewPIN(s.charAt(0), 3, false);
                } else {
                    validateNewPIN(null, 3, false);
                }
                mPINconfirm4.selectAll();
            }
        });

        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Call the changePIN with the view parameter
                changePin();
            }
        });

        if(!PIN.isPINSet()) {
            mOldPIN.setTextColor(R.color.gray);

            mPINold1.setEnabled(false);
            mPINold2.setEnabled(false);
            mPINold3.setEnabled(false);
            mPINold4.setEnabled(false);

            mOldPINOk = true;
        }

        return thePINView;
    }

    private void changePin() {

        if(mOldPINOk && mNewPINOk) {
            //TODO: change PIN
            Toast.makeText(getActivity().getApplicationContext(),getString(R.string.pin_changed),
                    Toast.LENGTH_LONG).show();
        }

        mOldPIN.setTextColor(R.color.black);
        mPINold1.setEnabled(true);
        mPINold2.setEnabled(true);
        mPINold3.setEnabled(true);
        mPINold4.setEnabled(true);

        PIN.setPINSet(true);

        clearFields();
    }

    private void validateNewPIN(Character value, int place, boolean firstPINField) {
        if(firstPINField) {
            mPINnew[place]=value;
        } else {
            mPINconfirm[place]=value;
        }

        if(PIN.isPINComplete(mPINnew) && PIN.isPINComplete(mPINconfirm) ){
            mOKIconNew.setVisibility(View.VISIBLE);
            mOKIconConf.setVisibility(View.VISIBLE);
            if(samePINs(mPINnew,mPINconfirm)){
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

    private boolean samePINs(Character[] mPINnew, Character[] mPINconfirm) {
        for (int i=0; i<4; i++) {
            if(mPINnew[i] != mPINconfirm[i]){
                return false;
            }
        }
        return true;
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

        if(!PIN.isPINSet()) {
            mOldPIN.setTextColor(R.color.gray);

            mPINold1.setEnabled(false);
            mPINold2.setEnabled(false);
            mPINold3.setEnabled(false);
            mPINold4.setEnabled(false);
            mOldPINOk = true;
        }


    }
}
