package inc.thenewpirates.foehn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class StoreActivity_Before extends AppCompatActivity {
    ImageView iv;
    TextView storeText, storeText2;
    Context context;
    String c = "";
    ImageSwitcher sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_before);

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

        switch (c) {
            case "ltv1":
                callDialog("Girls T-Shirt");
                break;
            case "wbv1":
                callDialog("Wrist Band");
                break;
            case "cv1":
                callDialog("Cap");
                break;
            case "hkv1":
                callDialog("Handkerchief");
                break;
            case "hv1":
                callDialog("Hoody");
                break;
            case "tv1":
                callDialog("Boys T-Shirt");
                break;
        }
    }

    public void callDialog(String img) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle(" Buy Products ");
        ab
                .setMessage("To buy " + img + " you have to login , Do you want to login ? ")
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(" Yes ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(StoreActivity_Before.this, LoginActivity.class);
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

}
