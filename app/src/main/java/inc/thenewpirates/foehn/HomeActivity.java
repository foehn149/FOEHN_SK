package inc.thenewpirates.foehn;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class HomeActivity extends AppCompatActivity {
    DrawerLayout mdrawerlayout;
    Toolbar toolbar;
    NavigationView navView;
    ActionBarDrawerToggle drawerToggle;
    Intent intent = new Intent();
    TextView textview;
    int[] resources = {
            R.drawable.food_c,
            R.drawable.orphan_c,
            R.drawable.education_c,
            R.drawable.health_c,
            R.drawable.naturalcalamities_c
    };
    ViewFlipper mViewFlipper;
    ImageView imageView;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textview = (TextView) findViewById(R.id.cusupport);
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
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mdrawerlayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        // Get the ViewFlipper
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
            mViewFlipper.setFlipInterval(4000);
        }

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
                imageView = new ImageView(HomeActivity.this);
                imageView.setImageResource(resource);
                mViewFlipper.addView(imageView);
            }
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.HomeActivity");
            startActivity(intent);

        } else if (id == R.id.nav_donation) {
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.DonationActivity");
            startActivity(intent);

        } else if (id == R.id.nav_store) {
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.StoreActivity");
            startActivity(intent);

        } else if (id == R.id.nav_contactus) {
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.ContactusActivity");
            startActivity(intent);

        } else if (id == R.id.nav_login) {
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.LoginActivity");
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mdrawerlayout != null && mdrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
        } else {
            navView.getMenu().findItem(R.id.nav_home).setChecked(true);
            super.onBackPressed();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.

        int id = item.getItemId();
        if (id == R.id.home) {
            mdrawerlayout.openDrawer(GravityCompat.START);
        } else if (id == R.id.aboutus) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.AboutusActivity");
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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "Home Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://inc.thenewpirates.foehn/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }


    @Override
    public void onStop() {
        super.onStop();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "Home Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://inc.thenewpirates.foehn/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);

        client.disconnect();
    }


}
