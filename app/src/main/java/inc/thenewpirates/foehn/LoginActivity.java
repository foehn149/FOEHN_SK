package inc.thenewpirates.foehn;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText emailedittext, passedittext;
    View focusView;
    Button signinbutton;
    FloatingActionButton fab;
    Intent i = new Intent();
    String email, password;
    Toast t;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailedittext = (EditText) findViewById(R.id.email);
        passedittext = (EditText) findViewById(R.id.password);
        signinbutton = (Button) findViewById(R.id.sign_in_button);
        signinbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                emailedittext.setError(null);
                passedittext.setError(null);

                email = emailedittext.getText().toString();
                password = passedittext.getText().toString();

                if (!isEmailValid(email)) {
                    emailedittext.setError(getString(R.string.error_invalid_email));
                    focusView = emailedittext;
                    focusView.requestFocus();
                } else if (!isPasswordValid(password)) {
                    passedittext.setError(getString(R.string.error_invalid_password));
                    focusView = passedittext;
                    focusView.requestFocus();
                } else if (isEmailValid(email) && isPasswordValid(password)) {
                    i.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.HomeActivity");
                    startActivity(i);
                }

            }
        });
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                i.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.SignupActivity");
                startActivity(i);
                ;
            }
        });
    }


    private boolean isEmailValid(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    private boolean isPasswordValid(String password) {

        if (password != null && password.length() >= 4) {
            return true;
        }
        return false;
    }


}


