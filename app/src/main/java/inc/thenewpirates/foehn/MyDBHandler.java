package inc.thenewpirates.foehn;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MyDBHandler extends AppCompatActivity {

    SignupActivity s1;
    Intent intent;
    int no = 1;

    private static final String DATABASE_NAME = "foehn.db";
    private static final String TABLE_NAME = "foehn";
    private static final String ID = "_id";
    private static final int DATABASE_VERSION = 1;

    private static final String FNAME = "fname";
    private static final String LNAME = "lname";

    private static final String MOBILE = "mobile";
    private static final String DOB = "dob";

    private static final String EMAIL = "email";
    private static final String PASS = "pass";

    private static final String CPASS = "cpass";



    private static final String TABLE_CREATION_QUERY =
            "create table "
                    + TABLE_NAME
                    + "("
                    + ID
                    + " integer "
                    + "primary key autoincrement,"
                    + FNAME
                    + " text,"
                    + LNAME
                    + " text, "
                    + MOBILE
                    + " text,"
                    + DOB
                    + " text, "
                    + EMAIL
                    + " text,"
                    + PASS
                    + " text ,"
                    + CPASS
                    + " text"
                    + ");";

    private EmployeeHelper employeeHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public MyDBHandler(Context context) {
        this.context = context;
        employeeHelper = new EmployeeHelper(context);
    }

    public void open() {
        sqLiteDatabase = employeeHelper.getWritableDatabase();
    }

    public void close() {
        sqLiteDatabase.close();
    }


    public int addProduct(Product p) {

        if (p.get_fname().equals("")) {
            //Toast.makeText(context, "First name is not there", Toast.LENGTH_LONG).show();
            no = 21;
        }
        else if (p.get_fname().length()<2) {
            //Toast.makeText(context, "Enter valid Firsr Name", Toast.LENGTH_LONG).show();
            no = 22;
        }
        else if (p.get_mobile().equals("")) {
            //Toast.makeText(context, "Mobile no is not there", Toast.LENGTH_LONG).show();
            no = 23;
        }
        else if (p.get_mobile().length()<10) {
            //Toast.makeText(context, "Invalid Mobile number", Toast.LENGTH_LONG).show();
            no = 231;
        }
        else if ( p.get_mobile().length()>12) {
            //Toast.makeText(context, "Invalid Mobile number", Toast.LENGTH_LONG).show();
            no = 232;
        }
        else if (p.get_dob().equals("")) {
            //Toast.makeText(context, "Date is not there", Toast.LENGTH_LONG).show();
            no = 24;
        }
        else if (p.get_dob().length()<8 ) {
            //Toast.makeText(context, "Enter valid date", Toast.LENGTH_LONG).show();
            no = 241;
        }
        else if ( p.get_dob().length()>10) {
            //Toast.makeText(context, "Enter valid date", Toast.LENGTH_LONG).show();
            no = 242;
        }
        else if (p.get_email().equals("")) {
            //Toast.makeText(context, "Email is not there", Toast.LENGTH_LONG).show();
            no = 25;
        }
        else if (p.get_pass().equals("")) {
            //Toast.makeText(context, "Password is not there", Toast.LENGTH_LONG).show();
            no = 26;
        }

        else if (p.get_cpass().equals("")) {
           // Toast.makeText(context, "Confirm Password is not there", Toast.LENGTH_LONG).show();
            no = 27;
        }
        else if(!p.get_pass().equals(p.get_cpass())){
            //Toast.makeText(context, "Password and Confirm Password are not same", Toast.LENGTH_LONG).show();
            no = 267;
        }
        else {

            if(checkEmail(p.get_email())) {
                ContentValues values = new ContentValues();

                values.put(FNAME, p.get_fname());
                values.put(LNAME, p.get_lname());

                values.put(MOBILE, p.get_mobile());
                values.put(DOB, p.get_dob());

                values.put(EMAIL, p.get_email());
                values.put(PASS, p.get_pass());

                values.put(CPASS, p.get_cpass());

                long rowId = sqLiteDatabase.insert(TABLE_NAME, null, values);
                if (rowId != -1) {
                    Toast.makeText(context, " Welcome To F.O.E.H.N : )", Toast.LENGTH_LONG).show();
                    no = 0;
                }
            }
            else {
                no = 1;
            }

        }
        return no;
    }

    public boolean checkEmail(String e){

        int n = 1;

        boolean b = true;
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do
            {

                String productname = cursor.getString(cursor.getColumnIndex("email"));

                if(productname.equals(e.toLowerCase())){
                    Toast.makeText(context, " Email Already Registered ", Toast.LENGTH_LONG).show();
                    b = false;
                    Toast.makeText(context, " Try to Login using your Email ", Toast.LENGTH_LONG).show();
                }

            }while(cursor.moveToNext());
            cursor.close();
        }


            //s1.switchToLogin();


        return b;
    }

    public String checkRecord(Product p){

        String test = "";

        if(p.get_email().equals("")){

            if(p.get_pass().equals("")){
                test = "abc";
            }
            else{
                test = "empty";
            }

        }
        else{
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                    null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do
                {
                    String productname1 = p.get_email();
                    String password1 = p.get_pass();
                    String productname = cursor.getString(cursor.getColumnIndex("email"));
                    String password = cursor.getString(cursor.getColumnIndex("pass"));

                     if(!productname.equals(productname1) && !password.equals(password1)){
                        test = "wc";

                    }
                    else if(!productname.equals(productname1) && password.equals(password1)){
                        test = "we";
                    }
                    else if(productname.equals(productname1) && !password.equals(password1)){
                        test = "wp";
                    }
                    else if(productname.equals(productname1) && password.equals(password1)){
                        Toast.makeText(context, " Welcome to F.O.E.H.N : )", Toast.LENGTH_LONG).show();
                        test = "yeah";
                        break;
                    }
                    else{
                        Toast.makeText(context, " TRY AGAIN ", Toast.LENGTH_LONG).show();

                    }

                }while(cursor.moveToNext());
                cursor.close();
            }
        }
        return test;
    }


    private class EmployeeHelper extends SQLiteOpenHelper {

        public EmployeeHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(TABLE_CREATION_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

}
