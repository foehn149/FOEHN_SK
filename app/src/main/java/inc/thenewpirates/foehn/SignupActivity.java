package inc.thenewpirates.foehn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

    }

    public void signupButtonClicked(View view) {

        String fname = fnameInput.getText().toString();
        String lname = lnameInput.getText().toString();
        String mobile = mobileInput.getText().toString();
        String dob = dobInput.getText().toString();
        String email = emailInput.getText().toString();
        String pass = passInput.getText().toString();
        String cpass = cpassInput.getText().toString();

        if (dbHandler.checkEmail(email)) {
            p = new Product(fname, lname, mobile, dob, email.toLowerCase(), pass, cpass);
            int c = dbHandler.addProduct(p);
            if (c == 0) {
                intent = new Intent(SignupActivity.this, Mypage.class);
                startActivity(intent);
            }
        } else {
            intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }
       /* if(!(dbHandler.checkEmail(email))) {
                intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

        }*/

        /*else{
            intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(context, " Please Login ", Toast.LENGTH_LONG).show();
        }*/

    }

    public void switchToLogin() {
        /*intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);*/
        Toast.makeText(context, " Please Login ", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        dbHandler.close();
        super.onDestroy();
    }
}
