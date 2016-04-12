package inc.thenewpirates.foehn;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    private static final int uid = 1235;
    EditText fnameInput, lnameInput, mobileInput, dobInput, emailInput, passInput, cpassInput;
    FloatingActionButton fab;
    Intent i;
    MyDBHandler dbHandler;
    NotificationCompat.Builder n;
    Product p;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHandler = new MyDBHandler(this);
        dbHandler.open();
        fnameInput = (EditText) findViewById(R.id.fnameInput);
        lnameInput = (EditText) findViewById(R.id.lnameInput);
        mobileInput = (EditText) findViewById(R.id.mobileInput);
        dobInput = (EditText) findViewById(R.id.dobInput);
        emailInput = (EditText) findViewById(R.id.email);
        passInput = (EditText) findViewById(R.id.password);
        cpassInput = (EditText) findViewById(R.id.cpassInput);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);

            }
        });
        n = new NotificationCompat.Builder(this);
        n.setAutoCancel(true);
        Runtime.getRuntime().maxMemory();
    }

    public void checkButtonClicked(View view) {
        String temp;

        String email = emailInput.getText().toString().trim();
        String pass = passInput.getText().toString().trim();

        p = new Product(email.toLowerCase(), pass);
        String abc = dbHandler.checkRecord(p);
        String abc1 = "yeah";
        String abc2 = "abc";

        if (abc.equals(abc2)) {
            Snackbar.make(view, "Please enter Email and Password .", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            emailInput.setText("");
            passInput.setText("");
        } else if (abc.equals("empty")) {
            Snackbar.make(view, "Please enter email .", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (!isEmailValid(email.toLowerCase())) {
            Snackbar.make(view, "Please enter your Registered Email .", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            emailInput.setText("");
            passInput.setText("");
        } else if (abc.equals("wc")) {
            Snackbar.make(view, "Invalid Entry, Please enter proper details .", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            emailInput.setText("");
            passInput.setText("");
        } else if (abc.equals("we")) {
            Snackbar.make(view, "Invalid EMAIL, Please enter valid EMAIL .", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            emailInput.setText("");

        } else if (abc.equals("wp")) {
            Snackbar.make(view, "Invalid PASSWORD, Please enter valid PASSWORD .", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            passInput.setText("");
        } else if (abc.equals(abc1)) {

            String fname = dbHandler.returnFname();
            String lname = dbHandler.returnLname();


            if (lname.equals("")) {
                temp = String.valueOf(fname.charAt(1));
            } else {
                temp = lname;
            }

            String id = dbHandler.genID(fname, temp);
            SharedPreferences sp = getSharedPreferences("file", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("fname", fname);
            editor.putString("lname", lname);
            editor.putString("id", id);

            editor.apply();
            notifyMe(id);
            i = new Intent(LoginActivity.this, HomeActivity_After.class);
            startActivity(i);
        } else {
            Snackbar.make(view, "Your are not Registered , Create new Account . ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            passInput.setText("");
            emailInput.setText("");

        }
    }

    public void notifyMe(String id){
        n.setSmallIcon(R.mipmap.ic_launcher);
        n.setTicker("Successfully Logged In");
        n.setWhen(System.currentTimeMillis());
        n.setContentTitle("Welcome to F.O.E.H.N.");
        n.setContentText(" You have successfully logged in");

        Intent i = new Intent(this,HomeActivity_Before.class);
        PendingIntent p = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        n.setContentIntent(p);

        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uid,n.build());

    }


    private boolean isEmailValid(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private boolean isPasswordValid(String password) {
        boolean a = false;
        String as = "";
        if (!password.equals(as))
            a = true;
        return a;
    }


    @Override
    protected void onDestroy() {
        dbHandler.close();
        super.onDestroy();
    }
}


