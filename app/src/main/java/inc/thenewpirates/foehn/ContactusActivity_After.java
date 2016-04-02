package inc.thenewpirates.foehn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class ContactusActivity_After extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus_after);
        Runtime.getRuntime().maxMemory();
    }


    public void feedbackprocess(View view) {

        FrameLayout fm = (FrameLayout) findViewById(R.id.nav_contactus);
        if (fm != null) {
            fm.removeAllViews();
        }
        FeedbackFragment feedbackFragment = new FeedbackFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_contactus, feedbackFragment)
                .addToBackStack("Feedback")
                .commit();


    }

    public void feedbackclicked(View v) {
        Toast.makeText(this, " Feedback submitted Successfully ", Toast.LENGTH_LONG).show();
    }

    public void supportprocess(View view) {
        int id = view.getId();
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
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.nav_contactus);
            View.inflate(this, R.layout.activity_contactus_after, frameLayout);
        }
    }
}

