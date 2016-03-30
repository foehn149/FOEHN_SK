package inc.thenewpirates.foehn;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class StoreActivity_After extends AppCompatActivity {
    ImageView iv;
    TextView storeText;
    Context context;
    String c = "";
    ImageSwitcher sw;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_after);

        storeText = (TextView) findViewById(R.id.storeText);

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
        //sw.setImageResource(R.drawable.tv1);
        sw.setImageResource(R.drawable.tv1);
        c = "tv1";
        storeText.setText("Boys T-Shirt");
        Runtime.getRuntime().maxMemory();
    }

    public void imageClicked(View view) {

        /*int id=view.getId();
        if(id==R.id.storeText)*/
        FrameLayout fm = (FrameLayout) findViewById(R.id.nav_store);
        if (fm != null) {
            fm.removeAllViews();
        }
        switch (c) {
            case "ltv1":
                //storeText.setText(" Buy Girls T-Shirt ");
                sizemethod();
                break;
            case "wbv1":
                //storeText.setText(" Buy Wrist Band ");
                sizemethod();
                break;
            case "cv1":
                //storeText.setText(" Buy Cap ");
                sizemethod();
                break;
            case "hkv1":
                //storeText.setText(" Buy Handkerchief ");
                sizemethod();
                break;
            case "hv1":
                //storeText.setText(" Buy Hoody ");
                sizemethod();
                break;
            case "tv1":
                //storeText.setText(" Buy Boys T-Shirt");
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
                break;
            case "wbv1":
                sw.setImageResource(R.drawable.cv1);
                c = "cv1";
                storeText.setText("Cap");
                break;
            case "cv1":
                sw.setImageResource(R.drawable.hkv1);
                c = "hkv1";
                storeText.setText("Handkerchief");
                break;
            case "hkv1":
                sw.setImageResource(R.drawable.hv1);
                c = "hv1";
                storeText.setText("Hoody");

                break;
            case "hv1":
                sw.setImageResource(R.drawable.ltv1);
                c = "ltv1";
                storeText.setText("Girls T-Shirt");
                break;
            case "tv1":
                sw.setImageResource(R.drawable.wbv1);
                c = "wbv1";
                storeText.setText("Wrist Band");

                break;
        }

    }

    public void rightClicked(View view) {

        switch (c) {
            case "tv1":
                sw.setImageResource(R.drawable.ltv1);
                c = "ltv1";
                storeText.setText("Girls T-Shirt");
                break;
            case "ltv1":
                sw.setImageResource(R.drawable.hv1);
                c = "hv1";
                storeText.setText("Hoody");
                break;
            case "hv1":
                sw.setImageResource(R.drawable.hkv1);
                c = "hkv1";
                storeText.setText("Handkerchief");
                break;
            case "hkv1":
                sw.setImageResource(R.drawable.cv1);
                c = "cv1";
                storeText.setText("Cap");
                break;
            case "cv1":
                sw.setImageResource(R.drawable.wbv1);
                c = "wbv1";
                storeText.setText("Wrist Band");
                break;
            case "wbv1":
                sw.setImageResource(R.drawable.tv1);
                c = "tv1";
                storeText.setText("Boys T-Shirt");
                break;
        }

    }

    public void furtherprocess(View v) {
        Toast.makeText(this, " Have Patience ..Work is Under Progress ", Toast.LENGTH_LONG).show();

    }

    public void sizemethod() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_store, new SizeFragment());
        transaction.addToBackStack("Size");
        transaction.commit();
    }

    public void paymentmethod(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_store, new PaymentMethod1Fragment());
        transaction.addToBackStack("Payment Method");
        transaction.commit();
    }

    public void cardpaymentprocess(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_store, new CardPayment1Fragment());
        transaction.addToBackStack("Card Payment");
        transaction.commit();
    }

    public void netbankingprocess(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_store, new NetBanking1Fragment());
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
        getSupportFragmentManager().popBackStack();
        super.onBackPressed();
        FrameLayout fm = (FrameLayout) findViewById(R.id.nav_store);
        View.inflate(this, R.layout.activity_store_after, fm);
        if (fm != null) {
            fm.removeAllViews();
            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
        }

    }
}

