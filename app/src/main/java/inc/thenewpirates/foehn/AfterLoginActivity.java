package inc.thenewpirates.foehn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AfterLoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView AfterLoginId,AfterLoginId2;
    Intent intent = new Intent();



    @Override
    protected void onCreate(Bundle savedInstanceState)throws NullPointerException{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

            assert navigationView != null;
            navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sp = getSharedPreferences("file", Context.MODE_PRIVATE);
        AfterLoginId = (TextView)findViewById(R.id.AfterLoginId);
        AfterLoginId2 = (TextView)findViewById(R.id.AfterLoginId2);
       // AfterLoginId.setText("Yeah");
        if(sp.contains("fname")){
            String fname = sp.getString("fname","");
            String lname = sp.getString("lname","");
            String id = sp.getString("id","");

            AfterLoginId.setText("Welcome "+fname+" "+lname);
            AfterLoginId2.setText(" Your ID >> "+id);

        }
        else{
            AfterLoginId.setText("Empty");
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.after_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {


        } else if (id == R.id.nav_donation) {
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.DonationActivity");
            startActivity(intent);

        } else if (id == R.id.nav_store) {
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.StoreActivity");
            startActivity(intent);

        } else if (id == R.id.nav_contactus) {
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.ContactusActivity");
            startActivity(intent);

        } else if (id == R.id.nav_login) {
            SharedPreferences sp = getSharedPreferences("file", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.apply();

            AfterLoginId.setText("logging out...");
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.HomeActivity");
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
