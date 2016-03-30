package inc.thenewpirates.foehn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class HomeActivity_After extends AppCompatActivity {

    TextView AfterLoginId, AfterLoginId2;
    Intent intent = new Intent();
    DrawerLayout mdrawerlayout;
    Toolbar toolbar;
    NavigationView navView;
    ActionBarDrawerToggle drawerToggle;
    int[] resources = {
            R.drawable.food_c,
            R.drawable.orphan_c,
            R.drawable.education_c,
            R.drawable.health_c,
            R.drawable.naturalcalamities_c
    };
    ViewFlipper mViewFlipper;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_after);
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Find our drawer view
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Setup drawer toggle
        setNavigationDrawer();
        drawerToggle = new ActionBarDrawerToggle(
                this,
                mdrawerlayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                drawerToggle.syncState();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerToggle.syncState();
            }
        };
        mdrawerlayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        // Add all the images to the ViewFlipper
        for (int resource : resources) {
            imageView = new ImageView(this);
            imageView.setImageResource(resource);
            mViewFlipper.addView(imageView);
        }
        // Set in/out flipping animations
        // flip every 2 seconds (2000ms)
        if (mViewFlipper != null) {
            mViewFlipper.setAutoStart(true);
            mViewFlipper.setFlipInterval(3000);
        }

        SharedPreferences sp = getSharedPreferences("file", Context.MODE_PRIVATE);
        AfterLoginId = (TextView) findViewById(R.id.AfterLoginId);
        AfterLoginId2 = (TextView) findViewById(R.id.AfterLoginId2);
        // AfterLoginId.setText("Yeah");
        if (sp.contains("fname")) {
            String fname = sp.getString("fname", "");
            String lname = sp.getString("lname", "");
            String id = sp.getString("id", "");

            AfterLoginId.setText(" Welcome " + fname + " " + lname);
            AfterLoginId2.setText(" ID:- " + id);

        } else {
            AfterLoginId.setText("Empty");
        }
        Runtime.getRuntime().maxMemory();
    }

    public void setNavigationDrawer() {
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectdraweritem(menuItem);
                navView.getMenu().findItem(R.id.nav_home).setChecked(true);
                return true;
            }

        });
    }

    public void selectdraweritem(MenuItem menuItem) {
        int id = menuItem.getItemId();
        getSupportFragmentManager().popBackStack();
        mdrawerlayout.closeDrawer(GravityCompat.START);
        if (id == R.id.nav_home) {
            for (int resource : resources) {
                imageView = new ImageView(HomeActivity_After.this);
                imageView.setImageResource(resource);
                mViewFlipper.addView(imageView);
            }
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.HomeActivity_After");
            startActivity(intent);

        } else if (id == R.id.nav_donation) {
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.DonationActivity_After");
            startActivity(intent);

        } else if (id == R.id.nav_store) {
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.StoreActivity_After");
            startActivity(intent);

        } else if (id == R.id.nav_contactus) {
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.ContactusActivity_After");
            startActivity(intent);

        } else if (id == R.id.nav_login) {
            SharedPreferences sp = getSharedPreferences("file", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.apply();
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.HomeActivity_Before");
            startActivity(intent);
        }

    }


    @Override
    public void onBackPressed() {
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mdrawerlayout != null && mdrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
        }
        navView.getMenu().findItem(R.id.nav_home).setChecked(true);
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home_before/up action should open or close the drawer.

        int id = item.getItemId();
        if (id == R.id.home) {
            mdrawerlayout.openDrawer(GravityCompat.START);
        } else if (id == R.id.aboutus) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.AboutusActivity_After");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_after, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
