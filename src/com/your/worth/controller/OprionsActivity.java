package com.your.worth.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.your.worth.R;

public class OprionsActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
	}

    /** Called when the user clicks the Home tab */
    public void openHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Incomes tab */
    public void openIncome(View view) {

        Intent intent = new Intent(this, IncomeActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Expenses tab */
    public void openSpending(View view) {
        Intent intent = new Intent(this, SpendingActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Expenses tab */
    public void openAbout(View view) {

       // TODO: open a web page (find how)
    }


}
