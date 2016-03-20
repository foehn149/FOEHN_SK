package inc.thenewpirates.foehn;

import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Stack;


public class HomeActivity extends AppCompatActivity {
    DrawerLayout mdrawerlayout;
    Toolbar toolbar;
    NavigationView navView;
    ActionBarDrawerToggle drawerToggle;
    Intent intent = new Intent();
    private GoogleApiClient client;
    TextView textview;
    View view1;
    CharSequence mtitle;
    String[] fragmenttitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState == null) {
            setContentView(R.layout.activity_home);
        }

        mtitle = getTitle();
        fragmenttitles = getResources().getStringArray(R.array.fragmenttitles);
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
                setTitle(mtitle);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(mtitle);
            }
        };
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        getSupportFragmentManager().addOnBackStackChangedListener(new android.support.v4.app.FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    String name = getSupportFragmentManager()
                            .getBackStackEntryAt((getSupportFragmentManager().getBackStackEntryCount() - 1))
                            .getName();
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(name);
                    }
                }
            }

        });
    }

    public void setTitle(CharSequence title) {

        mtitle = title;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(mtitle);
        }
    }

    public void supportprocess(View view) {

        int id = view.getId();
        if (id == R.id.cusupport) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {"support@foehn.comli.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.setType("message/rfc822");
            intent = Intent.createChooser(intent, "Send Email");
            startActivity(intent);

        }

    }

    @Override
    public void onBackPressed() {

        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mdrawerlayout != null && mdrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void foodprocess(View v) {
        setTitle(fragmenttitles[0]);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction
                .replace(R.id.nav_home, new FoodDonateFragment())
                .addToBackStack("Food")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);

    }

    public void orphanprocess(View v) {
        setTitle(fragmenttitles[1]);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction
                .replace(R.id.nav_home, new OrphanDonateFragment())
                .addToBackStack("Orphan")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);

    }

    public void educationprocess(View v) {
        setTitle(fragmenttitles[2]);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction
                .replace(R.id.nav_home, new EducationDonateFragment())
                .addToBackStack("Education")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);

    }

    public void healthprocess(View v) {
        setTitle(fragmenttitles[3]);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction
                .replace(R.id.nav_home, new HealthDonateFragment())
                .addToBackStack("Health")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);

    }

    public void naturalcalamitiesprocess(View v) {
        setTitle(fragmenttitles[4]);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction
                .replace(R.id.nav_home, new NaturalCalamitiesDonateFragment())
                .addToBackStack("Natural Calamities")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);

    }

    public void aboutusprocess(View v1) {

        setTitle(fragmenttitles[5]);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction
                .replace(R.id.nav_home, new AboutusFragment())
                .addToBackStack("About us")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);

    }

    public void feedbackprocess(View view) {

        setTitle(fragmenttitles[6]);
        int id = view.getId();
        if (id == R.id.cuimagebutton) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_home, new FeedbackFragment())
                    .addToBackStack("Feedback")
                    .commit();
            mdrawerlayout.closeDrawer(GravityCompat.START);
        }
    }


    public void setNavigationDrawer() throws NullPointerException {
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.nav_home) {
                    home(view1);
                    return true;
                } else if (id == R.id.nav_donation) {
                    donation(view1);
                    return true;
                } else if (id == R.id.nav_store) {
                    store(view1);
                    return true;
                } else if (id == R.id.nav_contactus) {
                    contactus(view1);
                    return true;
                } else if (id == R.id.nav_login) {
                    login(view1);
                    return true;
                }
                return false;
            }
        });
    }

    public void home(View v) {
        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.HomeActivity");
        startActivity(intent);
        mdrawerlayout.closeDrawer(GravityCompat.START);
    }

    public void donation(View v) {
        setTitle(fragmenttitles[7]);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_home, new DonationFragment())
                .addToBackStack("Donation")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);
    }

    public void store(View v) {
        setTitle(fragmenttitles[8]);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_home, new StoreFragment())
                .addToBackStack("Store")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);
    }

    public void contactus(View v) {
        setTitle(fragmenttitles[9]);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_home, new ContactusFragment())
                .addToBackStack("Contact us")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);
    }

    public void login(View v) {
        intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.LoginActivity");
        startActivity(intent);
        mdrawerlayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) throws NullPointerException {
        // The action bar home/up action should open or close the drawer.

        int id = item.getItemId();
        if (id == R.id.home) {
            intent = NavUtils.getParentActivityIntent(this);
            if (NavUtils.shouldUpRecreateTask(this, intent)) {
                // This activity is NOT part of this app's task, so create a new task
                // when navigating up, with a synthesized back stack.
                TaskStackBuilder.create(this)
                        // Add all of this activity's parents to the back stack
                        .addNextIntentWithParentStack(intent)
                                // Navigate up to the closest parent
                        .startActivities();
            } else {
                // This activity is part of this app's task, so simply
                // navigate up to the logical parent activity.
                NavUtils.navigateUpTo(this, intent);
            }
            return true;
        } else if (id == R.id.visit_foehn) {
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.WebviewActivity");
            startActivity(intent);
        } else if (id == R.id.aboutus) {
            aboutusprocess(view1);
        }

        return super.onOptionsItemSelected(item);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
// NOTE! Make sure to override the method with only a single `Bundle` argument
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
        setTitle(mtitle);
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