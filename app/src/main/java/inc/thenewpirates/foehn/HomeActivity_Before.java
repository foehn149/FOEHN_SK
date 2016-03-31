package inc.thenewpirates.foehn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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

import java.util.Locale;

public class HomeActivity_Before extends AppCompatActivity {

    DrawerLayout mdrawerlayout;
    Toolbar toolbar;
    String abc;
    TextToSpeech t1;
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
        setContentView(R.layout.activity_home_before);

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
            mViewFlipper.setFlipInterval(3000);
        }

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

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
                imageView = new ImageView(HomeActivity_Before.this);
                imageView.setImageResource(resource);
                mViewFlipper.addView(imageView);
            }
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.HomeActivity_Before");
            startActivity(intent);

        } else if (id == R.id.nav_donation) {
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.DonationActivity_Before");
            startActivity(intent);

        } else if (id == R.id.nav_store) {
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.StoreActivity_Before");
            startActivity(intent);

        } else if (id == R.id.nav_contactus) {
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.ContactusActivity_Before");
            startActivity(intent);

        } else if (id == R.id.nav_login) {
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.LoginActivity");
            startActivity(intent);
        }

    }

    @Override
    protected void onPause() {

        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
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
        // The action bar home_before/up action should open or close the drawer.
        int id = item.getItemId();
        if (id == R.id.home) {
            mdrawerlayout.openDrawer(GravityCompat.START);
        } else if (id == R.id.aboutus) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.AboutusActivity_Before");
            startActivity(intent);
        }
        else if (id == R.id.elizabeth){
            speakBaby();
        }
        return super.onOptionsItemSelected(item);
    }

    public void speakBaby(){

        abc=" Hi , I am Elizabeth . welcome to phonn. I am a donation application created by the new pirates . i am here to help u exploring the application . Here you can either donate money for diffrent categories like food, orphans, education, health, natural calamities, or u can buy our products. please sign up or  login to Buy the products or donating the money. For help u can convey by sending queries to e mail given in contact us. dont forget to give feed back. have a good day, enjoy the application.";
        t1.speak(abc, TextToSpeech.QUEUE_FLUSH, null);
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
        getMenuInflater().inflate(R.menu.home_before, menu);
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

    @Override
    protected void onResume() {
        super.onResume();
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        SharedPreferences sp = getSharedPreferences("file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (sp.contains("fname")) {
            Intent i = new Intent(HomeActivity_Before.this, HomeActivity_After.class);
            startActivity(i);

        }

    }
}
