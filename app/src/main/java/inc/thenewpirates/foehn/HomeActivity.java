package inc.thenewpirates.foehn;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
    CharSequence mtitle;
    String[] fragmenttitles;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mtitle = getTitle();
        fragmenttitles = getResources().getStringArray(R.array.fragmenttitles);
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Home");
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


    public void paymentmethod(View view) {
        setTitle(fragmenttitles[10]);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_home, new PaymentMethodFragment())
                .addToBackStack("Payment Method")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);
    }

    public void cardpaymentprocess(View view) {
        setTitle(fragmenttitles[11]);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_home, new CardPaymentFragment())
                .addToBackStack("Card Payment")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);
    }

    public void netbankingprocess(View view) {
        setTitle(fragmenttitles[12]);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_home, new NetBankingFragment())
                .addToBackStack("Net Banking")
                .commit();
        mdrawerlayout.closeDrawer(GravityCompat.START);
    }

    public void netbanking(View view) {
        int id = view.getId();
        mdrawerlayout.closeDrawer(GravityCompat.START);
        if (id == R.id.hdfcbank) {
            Uri uriUrl = Uri.parse("http://www.hdfcbank.com/");
            intent = new Intent(Intent.ACTION_VIEW, uriUrl);
            intent = Intent.createChooser(intent, "HDFC Bank");
            startActivity(intent);

        } else if (id == R.id.icicibank) {
            Uri uriUrl = Uri.parse("http://www.icicibank.com/");
            intent = new Intent(Intent.ACTION_VIEW, uriUrl);
            intent = Intent.createChooser(intent, "ICICI Bank");
            startActivity(intent);
        } else if (id == R.id.citibank) {
            Uri uriUrl = Uri.parse("https://www.online.citibank.co.in/");
            intent = new Intent(Intent.ACTION_VIEW, uriUrl);
            intent = Intent.createChooser(intent, "Citi Bank");
            startActivity(intent);
        } else if (id == R.id.sbibank) {
            Uri uriUrl = Uri.parse("https://www.sbi.co.in/");
            intent = new Intent(Intent.ACTION_VIEW, uriUrl);
            intent = Intent.createChooser(intent, "SBI Bank");
            startActivity(intent);
        } else if (id == R.id.axisbank) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
            Uri uriUrl = Uri.parse("http://www.axisbank.com/");
            intent = new Intent(Intent.ACTION_VIEW, uriUrl);
            intent = Intent.createChooser(intent, "AXIS Bank");
            startActivity(intent);
        } else if (id == R.id.kotakbank) {
            Uri uriUrl = Uri.parse("http://www.kotak.com/");
            intent = new Intent(Intent.ACTION_VIEW, uriUrl);
            intent = Intent.createChooser(intent, "Kotak Bank");
            startActivity(intent);
        }
    }


    public void calltopaypal(View view) {
        int id = view.getId();
        mdrawerlayout.closeDrawer(GravityCompat.START);
        if (id == R.id.paypal) {
            Uri uriUrl = Uri.parse("https://www.paypal.com/signin/?country.x=IN&locale.x=en_IN");
            intent = new Intent(Intent.ACTION_VIEW, uriUrl);
            intent = Intent.createChooser(intent, "Paypal");
            startActivity(intent);

        }

    }

    public void supportprocess(View view) {

        int id = view.getId();
        mdrawerlayout.closeDrawer(GravityCompat.START);
        if (id == R.id.cusupport) {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {"support@foehn.comli.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.setType("message/rfc822");
            intent = Intent.createChooser(intent, "Send Email");
            startActivity(intent);

        }

    }

    public void donateprocess(View view) {
        mdrawerlayout.closeDrawer(GravityCompat.START);
        int id = view.getId();
        if (id == R.id.food) {
            setTitle(fragmenttitles[0]);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_home, new FoodDonateFragment())
                    .addToBackStack("Food")
                    .commit();

        } else if (id == R.id.orphan) {
            setTitle(fragmenttitles[1]);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_home, new OrphanDonateFragment())
                    .addToBackStack("Orphan")
                    .commit();
        } else if (id == R.id.education) {
            setTitle(fragmenttitles[2]);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_home, new EducationDonateFragment())
                    .addToBackStack("Education")
                    .commit();
        } else if (id == R.id.health) {
            setTitle(fragmenttitles[3]);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_home, new HealthDonateFragment())
                    .addToBackStack("Health")
                    .commit();
        } else if (id == R.id.naturalcalamities) {
            setTitle(fragmenttitles[4]);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_home, new NaturalCalamitiesDonateFragment())
                    .addToBackStack("Natural Calamities")
                    .commit();
        }
    }

    public void feedbackprocess(View view) {

        setTitle(fragmenttitles[6]);
        int id = view.getId();
        mdrawerlayout.closeDrawer(GravityCompat.START);
        if (id == R.id.cuimagebutton) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_home, new FeedbackFragment())
                    .addToBackStack("Feedback")
                    .commit();
        }
    }

    public void setNavigationDrawer() {
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                getSupportFragmentManager().popBackStack();
                mdrawerlayout.closeDrawer(GravityCompat.START);
                if (id == R.id.nav_home) {
                    setTitle("Home");
                    // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.HomeActivity");
                    startActivity(intent);
                    return true;
                } else if (id == R.id.nav_donation) {
                    setTitle(fragmenttitles[7]);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.nav_home, new DonationFragment())
                            .addToBackStack("Donation")
                            .commit();
                    return true;
                } else if (id == R.id.nav_store) {
                    setTitle(fragmenttitles[8]);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.nav_home, new StoreFragment())
                            .addToBackStack("Store")
                            .commit();
                    return true;
                } else if (id == R.id.nav_contactus) {
                    setTitle(fragmenttitles[9]);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.nav_home, new ContactusFragment())
                            .addToBackStack("Contact us")
                            .commit();
                    return true;
                } else if (id == R.id.nav_login) {
                    intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.LoginActivity");
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {

        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mdrawerlayout != null && mdrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
        } else {
            setTitle("Home");
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
            intent = new Intent(this, WebviewActivity.class);
            startActivity(intent);
        } else if (id == R.id.aboutus) {

            mdrawerlayout.closeDrawer(GravityCompat.START);
            setTitle(fragmenttitles[5]);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_home, new AboutusFragment())
                    .addToBackStack("About us")
                    .commit();
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
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        setTitle("Home");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        setTitle("Home");
        super.onRestart();
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
