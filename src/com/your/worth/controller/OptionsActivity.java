package com.your.worth.controller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.your.worth.R;

public class OptionsActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
	}

    /** Called when the user clicks the Home tab */
    public void openHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    /** Called when the user clicks the Incomes tab */
    public void openIncome(View view) {

        Intent intent = new Intent(this, IncomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    /** Called when the user clicks the Expenses tab */
    public void openSpending(View view) {
        Intent intent = new Intent(this, SpendingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    /** Called when the user clicks the Expenses tab */
    public void openAbout(View view) {
        // TODO: change URL
        String aboutURL = "http://www.google.com";
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(aboutURL));

        // Start the activity
        startActivity(intent);
    }
}
