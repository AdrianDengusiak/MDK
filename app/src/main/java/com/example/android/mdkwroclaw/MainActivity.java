/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.navigationdrawerexample.R;

import java.util.List;

public class MainActivity extends Activity implements FragmentMain.OnFragmentInteractionListener, FragmentGallery.OnFragmentInteractionListener, FragmentTeachers.OnFragmentInteractionListener, FragmentNewsDetails.OnFragmentInteractionListener, FragmentNews.OnFragmentInteractionListener, FragmentSchedule.OnFragmentInteractionListener, FragmentSearchSchedule.OnFragmentInteractionListener, FragmentDownloadPDFSchedules.OnFragmentInteractionListener, FragmentChooseSchedule.OnFragmentInteractionListener, FragmentEvents.OnFragmentInteractionListener, FragmentCulturalCenters.OnFragmentInteractionListener, FragmentCulturalCenterInformation.OnFragmentInteractionListener, FragmentAbout.OnFragmentInteractionListener, FragmentESK.OnFragmentInteractionListener {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mMenuOptionsTitles;

    private int currentFragmentPosition = -1;
    private boolean exitFlag = false;

    public static boolean load = false;

    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mMenuOptionsTitles = getResources().getStringArray(R.array.main_menu_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the FragmentMain content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mMenuOptionsTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                if (getActionBar().getTitle().equals("Strona główna"))
                    getActionBar().setTitle("MDK Wrocław");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                if (getActionBar().getTitle().equals("Strona główna"))
                    getActionBar().setTitle("MDK Wrocław");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0, -1, null, null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_go_to_main_menu).setVisible(!drawerOpen);
        if(currentFragmentPosition == 0)
            menu.findItem(R.id.action_go_to_main_menu).setVisible(false);
        else
            menu.findItem(R.id.action_go_to_main_menu).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.action_go_to_main_menu:
                Fragment fragment = FragmentMain.newInstance(this);
                currentFragmentPosition = 0;
                invalidateOptionsMenu();
                exitFlag = false;

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(0, true);
                setTitle(mMenuOptionsTitles[0]);
                mDrawerLayout.closeDrawer(mDrawerList);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position, -1, null, null);
        }
    }

    public void selectItem(int position, int cultural_center_id, List<ScheduleRow> scheduleList, NewsRow news) {
        invalidateOptionsMenu();

        if(position != currentFragmentPosition) {
            Fragment fragment = FragmentMain.newInstance(this);

            if (position == 0) {
                fragment = FragmentMain.newInstance(this);
                currentFragmentPosition = 0;
            }
            if (position == 1) { //Domy Kultury
                fragment = FragmentCulturalCenters.newInstance(this);
                currentFragmentPosition = 1;
            }
            if (position == 11) { //Informacje o wybranym Domu Kultury
                if(!isOnline()) {
                    Toast.makeText(this, "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                }
                fragment = FragmentCulturalCenterInformation.newInstance(cultural_center_id, this);
                currentFragmentPosition = 11;
            }
            if (position == 111) { //Informacje o wybranym Domu Kultury
                if(!isOnline()) {
                    Toast.makeText(this, "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                }
                fragment = FragmentTeachers.newInstance(cultural_center_id, this);
                currentFragmentPosition = 111;
            }
            if (position == 2) {
                if(!isOnline()) {
                    Toast.makeText(this, "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                }
                fragment = FragmentNews.newInstance(this);
                currentFragmentPosition = 2;
            }
            if (position == 21) {
                if(!isOnline()) {
                    Toast.makeText(this, "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                }
                fragment = FragmentNewsDetails.newInstance(this, news);
                currentFragmentPosition = 21;
            }
            if (position == 3) {
                fragment = FragmentChooseSchedule.newInstance(this);
                currentFragmentPosition = 3;
            }
            if (position == 31) {
                fragment = FragmentDownloadPDFSchedules.newInstance(this);
                currentFragmentPosition = 31;
            }
            if (position == 32) {
                fragment = FragmentSearchSchedule.newInstance(this);
                currentFragmentPosition = 32;
            }
            if (position == 321) {
                if(!isOnline()) {
                    Toast.makeText(this, "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                }
                fragment = FragmentSchedule.newInstance(this, scheduleList);
                currentFragmentPosition = 321;
            }
            if (position == 4) {
                if(!isOnline()) {
                    Toast.makeText(this, "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                }
                fragment = FragmentEvents.newInstance(this);
                currentFragmentPosition = 4;
            }
            if (position == 5) {
                if(!isOnline()) {
                    Toast.makeText(this, "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                }
                fragment = FragmentGallery.newInstance(this);
                currentFragmentPosition = 5;
            }
            if (position == 6) {
                fragment = new FragmentAbout();
                currentFragmentPosition = 6;
            }
            if (position == 7) {
                fragment = new FragmentESK();
                currentFragmentPosition = 7;
            }

            exitFlag = false;

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            // update selected item and title, then close the drawer
            int current_item = position;
            if(current_item == 11)
                current_item = 1;
            if(current_item == 111)
                current_item = 1;
            if(current_item == 21)
                current_item = 2;
            if(current_item == 31)
                current_item = 3;
            if(current_item == 32)
                current_item = 3;
            if(current_item == 321)
                current_item = 3;

            mDrawerList.setItemChecked(current_item, true);
            setTitle(mMenuOptionsTitles[current_item]);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
        else {
            mDrawerLayout.closeDrawers();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
        if (getActionBar().getTitle().equals("Strona główna"))
            getActionBar().setTitle("MDK Wrocław");
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
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawers();
            } else {
                if (currentFragmentPosition == 0 && exitFlag == false) {
                    Toast.makeText(this, R.string.exit_alert, Toast.LENGTH_SHORT).show();
                    exitFlag = true;
                } else if (currentFragmentPosition == 0 && exitFlag == true) {
                    finish();
                } else if (currentFragmentPosition == 1 || currentFragmentPosition == 2 || currentFragmentPosition == 3 || currentFragmentPosition == 4 || currentFragmentPosition == 5 || currentFragmentPosition == 6 || currentFragmentPosition == 7) {
                    if (currentFragmentPosition == 2) {
                        SharedPreferences.Editor editor = FragmentNews.sharedPref.edit();
                        editor.putInt("mdk_position", 0);
                        editor.commit();
                    }
                    selectItem(0, -1, null, null);
                    currentFragmentPosition = 0;
                    exitFlag = false;
                } else if (currentFragmentPosition == 11) {
                    selectItem(1, -1, null, null);
                    currentFragmentPosition = 1;
                    exitFlag = false;
                } else if (currentFragmentPosition == 111) {
                    SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                    selectItem(11, sharedPref.getInt("mdk_position", 1), null, null);
                    currentFragmentPosition = 11;
                    exitFlag = false;
                } else if (currentFragmentPosition == 21) {
                    selectItem(2, -1, null, null);
                    currentFragmentPosition = 2;
                    exitFlag = false;
                } else if (currentFragmentPosition == 31) {
                    selectItem(3, -1, null, null);
                    currentFragmentPosition = 3;
                    exitFlag = false;
                } else if (currentFragmentPosition == 32) {
                    SharedPreferences.Editor editor = FragmentSearchSchedule.sharedPref.edit();
                    editor.putInt("mdk_position", 0);
                    editor.putInt("category_position", 0);
                    editor.putInt("day_position", 0);
                    editor.commit();

                    selectItem(3, -1, null, null);
                    currentFragmentPosition = 3;
                    exitFlag = false;
                } else if (currentFragmentPosition == 321) {
                    selectItem(32, -1, null, null);
                    currentFragmentPosition = 32;
                    exitFlag = false;
                }
            }
    }
}