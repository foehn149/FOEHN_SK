package inc.thenewpirates.foehn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class ContactusActivity extends AppCompatActivity {
    Intent intent;
    CharSequence mtitle;
    String[] fragmenttitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        mtitle = getTitle();
        fragmenttitles = getResources().getStringArray(R.array.fragmenttitles);
        getSupportFragmentManager().addOnBackStackChangedListener(new android.support.v4.app.FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    String name = getSupportFragmentManager()
                            .getBackStackEntryAt((getSupportFragmentManager().getBackStackEntryCount() - 1))
                            .getName();
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(name);
                    }
                }
            }

        });
    }

    public void setTitle(CharSequence title) {

        mtitle = title;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(mtitle);
        }
    }

    public void feedbackprocess(View view) {

        FrameLayout fm = (FrameLayout) findViewById(R.id.nav_contactus);
        if (fm != null) {
            fm.removeAllViews();
        }
        setTitle(fragmenttitles[0]);
        FeedbackFragment feedbackFragment = new FeedbackFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_contactus, feedbackFragment)
                .addToBackStack("Feedback")
                .commit();


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
        getSupportFragmentManager().popBackStack();
        super.onBackPressed();
    }
}
