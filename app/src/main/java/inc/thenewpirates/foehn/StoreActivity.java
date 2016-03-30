package inc.thenewpirates.foehn;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class StoreActivity extends AppCompatActivity {
    ImageView iv;
    TextView storeText;
    Context context;
    Button left,right;
    String c="";
    ImageSwitcher sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        storeText =(TextView)findViewById(R.id.storeText);

        sw = (ImageSwitcher)findViewById(R.id.imageSwitcher);
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
        c="tv1";
        storeText.setText("Boys T-Shirt");
    }

    public void imageClicked(View view){

        /*int id=view.getId();
        if(id==R.id.storeText)*/

        switch (c) {
            case "ltv1":
                storeText.setText(" Buy Girls T-Shirt ");
                break;
            case "wbv1":
                storeText.setText(" Buy Wrist Band ");
                break;
            case "cv1":
                storeText.setText(" Buy Cap ");
                break;
            case "hkv1":
                storeText.setText(" Buy Handkerchief ");
                break;
            case "hv1":
                storeText.setText(" Buy Hoody ");
                break;
            case "tv1":
                storeText.setText(" Buy Boys T-Shirt");
                break;
        }
    }

    public void leftClicked(View view){

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

    public void rightClicked(View view){

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

}
