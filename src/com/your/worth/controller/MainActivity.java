package com.your.worth.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.your.worth.R;
import com.your.worth.controller.adapters.MenuAdapter;
import com.your.worth.controller.fragments.HomeFragment;
import com.your.worth.controller.fragments.IncomeFragment;
import com.your.worth.controller.fragments.SpendingFragment;


public class MainActivity extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String[] menuItemsTitles = getResources().getStringArray(R.array.menu_items);
        String[] menuItemsDescriptions = getResources().getStringArray(R.array.menu_description);
        int[] menuItemType = getResources().getIntArray(R.array.menu_row_type);
        TypedArray menuIcons =  getResources().obtainTypedArray(R.array.menu_images);
        int switchPosition = 7;
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new MenuAdapter(this,menuItemsTitles,menuItemsDescriptions,menuIcons,menuItemType,switchPosition));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.indicator,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(3);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle action buttons

        return super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {
        // get the frame manager
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = null;

        switch(position){
            case 1: /** Called when the user clicks the Sign Out tab */
                    break;
            case 4: /** Called when the user clicks the Incomes tab */
                    setTitle(R.string.incomeButtonText);
                    // get the fragment for the home
                    fragment = new IncomeFragment();
                    break;
            case 5: /** Called when the user clicks the Expenses tab */
                    setTitle(R.string.spendingButtonText);
                    // get the fragment for the home
                    fragment = new SpendingFragment();
                    break;
            case 7: /** Called when the user clicks the PIN tab */
                    break;
            case 8: /** Called when the user clicks the About tab */
                    // the url for the web page of the application
                    String aboutURL = "http://yourworth.herokuapp.com/";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(aboutURL));

                    // Start the activity
                    startActivity(intent);
                    break;

            default:/* Called when the user clicks the Home tab
                       the default is home fragment also the same as case 3 */
                    setTitle(R.string.homeButtonText);
                    // get the fragment for the home
                    fragment = new HomeFragment();
                    break;
        }

        if(fragment != null){

            // update the main content by replacing fragments
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggle
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
}