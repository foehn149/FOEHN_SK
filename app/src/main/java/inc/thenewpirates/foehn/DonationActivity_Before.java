package inc.thenewpirates.foehn;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class DonationActivity_Before extends AppCompatActivity {
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
        Toast.makeText(this, " Please Login for Donation ", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBackPressed() {
        //getSupportFragmentManager().popBackStack();
        super.onBackPressed();
        FrameLayout fm = (FrameLayout) findViewById(R.id.nav_donation);
        if (fm != null) {
            fm.removeAllViews();
        }
        View.inflate(this, R.layout.activity_donation_after, fm);
    }

}
