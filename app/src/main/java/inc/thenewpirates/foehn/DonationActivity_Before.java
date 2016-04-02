package inc.thenewpirates.foehn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class DonationActivity_Before extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_before);
        Runtime.getRuntime().maxMemory();
    }

    public void donateprocess(View view) {
        int id = view.getId();
        FrameLayout fm = (FrameLayout) findViewById(R.id.nav_donation);
        if (fm != null) {
            fm.removeAllViews();
        }
        if (id == R.id.food) {
            FoodDonateFragment foodDonateFragment = new FoodDonateFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_donation, foodDonateFragment);
            transaction.addToBackStack("Food");
            transaction.commit();
        } else if (id == R.id.orphan) {
            OrphanDonateFragment orphanDonateFragment = new OrphanDonateFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_donation, orphanDonateFragment);
            transaction.addToBackStack("Orphan");
            transaction.commit();
        } else if (id == R.id.education) {
            EducationDonateFragment educationDonateFragment = new EducationDonateFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_donation, educationDonateFragment);
            transaction.addToBackStack("Education");
            transaction.commit();
        } else if (id == R.id.health) {
            HealthDonateFragment healthDonateFragment = new HealthDonateFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_donation, healthDonateFragment);
            transaction.addToBackStack("Health");
            transaction.commit();
        } else if (id == R.id.naturalcalamities) {
            NaturalCalamitiesDonateFragment naturalCalamitiesDonateFragment = new NaturalCalamitiesDonateFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_donation, naturalCalamitiesDonateFragment);
            transaction.addToBackStack("Natural Calamities");
            transaction.commit();
        }

    }


    public void amountprocess(View view) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle(" Donate ");
        ab
                .setMessage("To donate you have to login , Do you want to login ? ")
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(" Yes ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                        Intent i = new Intent(DonationActivity_Before.this, LoginActivity.class);
                        startActivity(i);

                    }
                })
                .setNegativeButton(" No ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


        AlertDialog ad = ab.create();
        ad.show();
    }


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();

        if (count > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
        if (count == 1) {
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.nav_donation);
            View.inflate(this, R.layout.activity_donation_before, frameLayout);
        }
    }

}
