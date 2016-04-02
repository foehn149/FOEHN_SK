package inc.thenewpirates.foehn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class DonationActivity_After extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_after);
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

    public void printdonationprocess(View v) {

        intent = new Intent(this, PrintDonationActivity.class);
        startActivity(intent);
    }

    public void amountprocess(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_donation, new AmountFragment());
        transaction.addToBackStack("Amount");
        transaction.commit();
    }

    public void paymentmethod(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_donation, new PaymentMethodDonationFragment());
        transaction.addToBackStack("Payment Method");
        transaction.commit();
    }

    public void cardpaymentprocess(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_donation, new CardPaymentDonationFragment());
        transaction.addToBackStack("Card Payment");
        transaction.commit();
    }

    public void netbankingprocess(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_donation, new NetBankingDonationFragment());
        transaction.addToBackStack("Net Banking");
        transaction.commit();
    }

    public void netbanking(View view) {
        int id = view.getId();
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
        if (id == R.id.paypal) {
            Uri uriUrl = Uri.parse("https://www.paypal.com/signin/?country.x=IN&locale.x=en_IN");
            intent = new Intent(Intent.ACTION_VIEW, uriUrl);
            intent = Intent.createChooser(intent, "Paypal");
            startActivity(intent);

        }

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
            View.inflate(this, R.layout.activity_donation_after, frameLayout);
        }
    }
}

