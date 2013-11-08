package com.your.worth.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.your.worth.R;
import com.your.worth.controller.listeners.TextChangeListener;
import com.your.worth.model.PIN;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 11/7/13
 * Time: 10:49 PM
 */
public class LoginActivity extends Activity {

    private ImageView mOKIcon = null;
    private Button mSignIn = null;
    private final Character[] mPIN = new Character[4];
    private EditText mText1 = null;
    private EditText mText2 = null;
    private EditText mText3 = null;
    private EditText mText4 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mOKIcon = (ImageView) findViewById(R.id.approve_icon);
        mSignIn = (Button) findViewById(R.id.loginButton);

        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        final ImageView imageBig = (ImageView) findViewById(R.id.icon_image);
        imageBig.startAnimation(animationFadeIn);

        mText1 = (EditText) findViewById(R.id.digit_one);
        mText1.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    validatePIN(s.charAt(0), 0);
                } else {
                    validatePIN(null, 0);
                }
                mText1.selectAll();
            }
        });

        mText2 = (EditText) findViewById(R.id.digit_two);
        mText2.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    validatePIN(s.charAt(0), 1);
                } else {
                    validatePIN(null, 1);
                }
                mText2.selectAll();
            }
        });

        mText3 = (EditText) findViewById(R.id.digit_tree);
        mText3.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    validatePIN(s.charAt(0), 2);
                } else {
                    validatePIN(null, 2);
                }
                mText3.selectAll();
            }
        });
        mText4 = (EditText) findViewById(R.id.digit_four);
        mText4.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    validatePIN(s.charAt(0), 3);
                } else {
                    validatePIN(null, 3);
                }
                mText4.selectAll();
            }
        });
    }

    /**
     * Deactivate the back functionality
     */
    @Override
    public void onBackPressed() {
       // do nothing
    }

    public void signIn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Validate the PIN entry
     * @param value the digit in a specific position
     * @param place the position of the value
     */
    private void validatePIN(Character value, int place) {
        mPIN[place]=value;
        if(PIN.isPINComplete(mPIN)){
            if(PIN.isPINOk(mPIN)){
                mOKIcon.setVisibility(View.VISIBLE);
                mOKIcon.setImageResource(R.drawable.pin_good);
                mSignIn.setVisibility(View.VISIBLE);
            } else {
                mOKIcon.setVisibility(View.VISIBLE);
                mOKIcon.setImageResource(R.drawable.pin_bad);
                mSignIn.setVisibility(View.GONE);
            }
        } else {
            mOKIcon.setVisibility(View.GONE);
            mSignIn.setVisibility(View.GONE);
        }

    }
}
