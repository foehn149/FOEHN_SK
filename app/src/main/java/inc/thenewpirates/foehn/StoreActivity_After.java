package inc.thenewpirates.foehn;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class StoreActivity_After extends AppCompatActivity {
    ImageView iv;
    TextView storeText, storeText2;
    Context context;
    String c = "";
    ImageSwitcher sw;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_after);

        storeText = (TextView) findViewById(R.id.storeText);
        storeText2 = (TextView) findViewById(R.id.storeText2);

        sw = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        sw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                iv = new ImageView(getApplicationContext());
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                iv.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return iv;
            }
        });
        sw.setImageResource(R.drawable.tv1);
        c = "tv1";
        storeText.setText("Boys T-Shirt");
        storeText2.setText("Rs. 100");
        Runtime.getRuntime().maxMemory();
    }

    public void imageClicked(View view) {

        FrameLayout fm = (FrameLayout) findViewById(R.id.nav_store);
        if (fm != null) {
            fm.removeAllViews();
        }
        switch (c) {
            case "ltv1":
                sizemethod();
                break;
            case "wbv1":
                sizemethod();
                break;
            case "cv1":
                sizemethod();
                break;
            case "hkv1":
                sizemethod();
                break;
            case "hv1":
                sizemethod();
                break;
            case "tv1":
                sizemethod();
                break;
        }
    }

    public void leftClicked(View view) {

        switch (c) {
            case "ltv1":
                sw.setImageResource(R.drawable.tv1);
                c = "tv1";
                storeText.setText("Boys T-Shirt");
                storeText2.setText("Rs. 100");
                break;
            case "wbv1":
                sw.setImageResource(R.drawable.cv1);
                c = "cv1";
                storeText.setText("Cap");
                storeText2.setText("Rs. 70");
                break;
            case "cv1":
                sw.setImageResource(R.drawable.hkv1);
                c = "hkv1";
                storeText.setText("Handkerchief");
                storeText2.setText("Rs. 25");
                break;
            case "hkv1":
                sw.setImageResource(R.drawable.hv1);
                c = "hv1";
                storeText.setText("Hoody");
                storeText2.setText("Rs. 300");
                break;
            case "hv1":
                sw.setImageResource(R.drawable.ltv1);
                c = "ltv1";
                storeText.setText("Girls T-Shirt");
                storeText2.setText("Rs. 150");
                break;
            case "tv1":
                sw.setImageResource(R.drawable.wbv1);
                c = "wbv1";
                storeText.setText("WristBand");
                storeText2.setText("Rs. 30");
                break;
        }

    }

    public void rightClicked(View view) {

        switch (c) {
            case "tv1":
                sw.setImageResource(R.drawable.ltv1);
                c = "ltv1";
                storeText.setText("Girls T-Shirt");
                storeText2.setText("Rs. 150");
                break;
            case "ltv1":
                sw.setImageResource(R.drawable.hv1);
                c = "hv1";
                storeText.setText("Hoody");
                storeText2.setText("Rs. 300");
                break;
            case "hv1":
                sw.setImageResource(R.drawable.hkv1);
                c = "hkv1";
                storeText.setText("Handkerchief");
                storeText2.setText("Rs. 25");
                break;
            case "hkv1":
                sw.setImageResource(R.drawable.cv1);
                c = "cv1";
                storeText.setText("Cap");
                storeText2.setText("Rs. 70");
                break;
            case "cv1":
                sw.setImageResource(R.drawable.wbv1);
                c = "wbv1";
                storeText.setText("Wrist Band");
                storeText2.setText("Rs. 30");
                break;
            case "wbv1":
                sw.setImageResource(R.drawable.tv1);
                c = "tv1";
                storeText.setText("Boys T-Shirt");
                storeText2.setText("Rs. 100");
                break;
        }

    }

    public void printstoreprocess(View v) {
        intent = new Intent(this, PrintStoreActivity.class);
        startActivity(intent);
    }

    public void sizemethod() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_store, new SizeFragment());
        transaction.addToBackStack("Size");
        transaction.commit();
    }

    public void paymentmethod(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_store, new PaymentMethodStoreFragment());
        transaction.addToBackStack("Payment Method");
        transaction.commit();
    }

    public void cardpaymentprocess(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_store, new CardPaymentStoreFragment());
        transaction.addToBackStack("Card Payment");
        transaction.commit();
    }

    public void netbankingprocess(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_store, new NetBankingStoreFragment());
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
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.nav_store);
            View.inflate(this, R.layout.activity_store_after, frameLayout);
            storeText = (TextView) findViewById(R.id.storeText);
            storeText2 = (TextView) findViewById(R.id.storeText2);

            sw = (ImageSwitcher) findViewById(R.id.imageSwitcher);
            sw.setFactory(new ViewSwitcher.ViewFactory() {
                @Override
                public View makeView() {
                    iv = new ImageView(getApplicationContext());
                    iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    iv.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    return iv;
                }
            });
            sw.setImageResource(R.drawable.tv1);
            c = "tv1";
            storeText.setText("Boys T-Shirt");
            storeText2.setText("Rs. 100");
        }
    }
}

