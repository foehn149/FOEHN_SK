package inc.thenewpirates.foehn;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class HomeActivity_After extends AppCompatActivity {

    private static final int uid = 1236;
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
    NotificationCompat.Builder n;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_after);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //int densityDpi = dm.densityDpi;
        //Log.e("Height","height="+height);
        //Log.e("Width","width="+width);
        //Log.e("Dpi","Dpi="+densityDpi);

        for (int resource : resources) {
            Bitmap bMap = BitmapFactory.decodeResource(getResources(), resource);
            Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, width, height, true);
            imageView = new ImageView(HomeActivity_After.this);
            imageView.setImageBitmap(bMapScaled);
            mViewFlipper.addView(imageView);
        }

        if (mViewFlipper != null) {
            mViewFlipper.setAutoStart(true);
            mViewFlipper.setFlipInterval(3000);
        }

        SharedPreferences sp = getSharedPreferences("file", Context.MODE_PRIVATE);
        AfterLoginId = (TextView) findViewById(R.id.AfterLoginId);
        AfterLoginId2 = (TextView) findViewById(R.id.AfterLoginId2);
        if (sp.contains("fname")) {
            String fname = sp.getString("fname", "");
            String lname = sp.getString("lname", "");
            String id = sp.getString("id", "");

            AfterLoginId.setText(" Logged in as " + fname.toUpperCase() + " " + lname.toUpperCase());
            AfterLoginId2.setText(" ID : "+" [ "+id+" ] ");

        } else {
            AfterLoginId.setText("Empty");
        }
        n = new NotificationCompat.Builder(this);
        n.setAutoCancel(true);
        Runtime.getRuntime().maxMemory();
    }

    public void setNavigationDrawer() {
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectdraweritem(menuItem);
                return true;
            }

        });
    }

    public void selectdraweritem(MenuItem menuItem) {
        int id = menuItem.getItemId();
        getSupportFragmentManager().popBackStack();
        mdrawerlayout.closeDrawer(GravityCompat.START);
        if (id == R.id.nav_home) {
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.HomeActivity_After");
            startActivity(intent);

        } else if (id == R.id.nav_donation) {
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.DonationActivity_After");
            startActivity(intent);

        } else if (id == R.id.nav_store) {
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.StoreActivity_After");
            startActivity(intent);

        } else if (id == R.id.nav_contactus) {
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.ContactusActivity_After");
            startActivity(intent);

        } else if (id == R.id.nav_login) {
            SharedPreferences sp = getSharedPreferences("file", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.apply();
            notifyMe();
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.HomeActivity_Before");
            startActivity(intent);
        }

    }

    public void notifyMe(){
        n.setSmallIcon(R.mipmap.ic_launcher);
        n.setTicker("Successfully Logged out.");
        n.setWhen(System.currentTimeMillis());
        n.setContentTitle("Thank You !");
        n.setContentText("Successfully logged out.");

        Intent i = new Intent(this,HomeActivity_Before.class);
        PendingIntent p = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        n.setContentIntent(p);

        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uid, n.build());

    }


    @Override
    public void onBackPressed() {
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mdrawerlayout != null && mdrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
        }
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            mdrawerlayout.openDrawer(GravityCompat.START);
        } else if (id == R.id.aboutus) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.AboutusActivity_After");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_after, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
