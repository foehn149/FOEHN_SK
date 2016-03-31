package inc.thenewpirates.foehn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {


    MyDBHandler dbHandler;
    Context context;
    Intent intent;
    Product p;
    EditText fnameInput, lnameInput, mobileInput, dobInput, emailInput, passInput, cpassInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbHandler = new MyDBHandler(this);
        dbHandler.open();
        fnameInput = (EditText) findViewById(R.id.fnameInput);
        lnameInput = (EditText) findViewById(R.id.lnameInput);
        mobileInput = (EditText) findViewById(R.id.mobileInput);
        dobInput = (EditText) findViewById(R.id.dobInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        passInput = (EditText) findViewById(R.id.passInput);
        cpassInput = (EditText) findViewById(R.id.cpassInput);
        Runtime.getRuntime().maxMemory();
    }

    public void signupButtonClicked(View view) {
        String temp;

        String fname = fnameInput.getText().toString().trim();
        String lname = lnameInput.getText().toString().trim();
        String mobile = mobileInput.getText().toString().trim();
        String dob = dobInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String pass = passInput.getText().toString().trim();
        String cpass = cpassInput.getText().toString().trim();


        if (dbHandler.checkEmail(email)) {


            p = new Product(fname, lname, mobile, dob, email.toLowerCase(), pass, cpass);
            int c = dbHandler.addProduct(p);


            if (c == 0) {
                if (lname.equals("")) {
                    temp = String.valueOf(fname.charAt(1));
                } else {
                    temp = lname;
                }
                String id = dbHandler.genID1(fname, temp);
                SharedPreferences sp = getSharedPreferences("file", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("fname", fname);
                editor.putString("lname", lname);
                editor.putString("id", id);

                editor.apply();

                intent = new Intent(SignupActivity.this, HomeActivity_After.class);
                startActivity(intent);
            } else if (c == 21) {
                Snackbar.make(view, " Please enter first name .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fnameInput.setText("");
            } else if (c == 22) {
                Snackbar.make(view, " Enter valid Firsr Name .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fnameInput.setText("");
            } else if (c == 23) {
                Snackbar.make(view, " Please enter mobile number .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mobileInput.setText("");
            } else if (c == 231) {
                Snackbar.make(view, " Invalid Mobile number .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mobileInput.setText("");
            } else if (c == 232) {
                Snackbar.make(view, " Invalid Mobile number .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mobileInput.setText("");
            } else if (c == 24) {
                Snackbar.make(view, " Please enter date .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                dobInput.setText("");
            } else if (c == 241) {
                Snackbar.make(view, " Enter valid date .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                dobInput.setText("");
            } else if (c == 242) {
                Snackbar.make(view, " Enter valid date .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                dobInput.setText("");
            } else if (c == 25) {
                Snackbar.make(view, " Please enter email .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                emailInput.setText("");
            } else if (!isEmailValid(email.toLowerCase())) {
                Snackbar.make(view, "Please enter your valid Email .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                emailInput.setText("");
            } else if (c == 26) {
                Snackbar.make(view, " Please enter password .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                passInput.setText("");
            } else if (c == 27) {
                Snackbar.make(view, " Please enter confirm password .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                cpassInput.setText("");
            } else if (c == 267) {
                Snackbar.make(view, " Password and Confirm Password must be same .", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                passInput.setText("");
                cpassInput.setText("");
            }
        } else {
            intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            Snackbar.make(view, "Email already registered, Try to Login using your Email .", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }


    }


    private boolean isEmailValid(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @Override
    protected void onDestroy() {
        dbHandler.close();
        super.onDestroy();
    }
}
