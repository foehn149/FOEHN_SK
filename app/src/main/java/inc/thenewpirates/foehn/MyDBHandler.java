package inc.thenewpirates.foehn;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyDBHandler extends AppCompatActivity {

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
    Intent intent;
    int no = 1;
    String tfname, tlname;
    int tnum;
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
            no = 21;
        } else if (p.get_fname().length() == 1) {
            no = 22;
        } else if (p.get_mobile().equals("")) {
            no = 23;
        } else if (p.get_mobile().length() < 10) {
            no = 231;
        } else if (p.get_mobile().length() > 12) {
            no = 232;
        } else if (p.get_dob().equals("")) {
            no = 24;
        } else if (p.get_dob().length() < 8) {
            no = 241;
        } else if (p.get_dob().length() > 10) {
            no = 242;
        } else if (p.get_email().equals("")) {
            no = 25;
        }else if (!isEmailValid(p.get_email().toLowerCase())) {
            no  = 255;
        }
        else if (p.get_pass().equals("")) {
            no = 26;
        } else if (p.get_cpass().equals("")) {
            no = 27;
        } else if (!p.get_pass().equals(p.get_cpass())) {
            no = 267;
        } else {

            if (checkEmail(p.get_email())) {
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
            } else {
                no = 1;
            }

        }
        return no;
    }

    private boolean isEmailValid(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String returnFname() {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                cursor.getString(cursor.getColumnIndex("fname"));
            } while (cursor.moveToNext());
            cursor.close();


        }
        return tfname;
    }

    public String returnLname() {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                cursor.getString(cursor.getColumnIndex("lname"));
            } while (cursor.moveToNext());
            cursor.close();


        }
        return tlname;
    }


    public String genID(String fname, String lname) {
        String ny, nysn, fcc, lcc, nys = "";
        char fc, lc;
        String no2;
        int num;
        num = tnum;


        if (num < 10) {
            fc = fname.charAt(0);
            lc = lname.charAt(0);

            fcc = String.valueOf(fc);
            fcc = fcc.toUpperCase();

            lcc = String.valueOf(lc);
            lcc = lcc.toUpperCase();

            ny = "16";
            no2 = "000";
            fcc = fcc.concat(lcc);

            ny = fcc.concat(ny);
            nys = ny.concat(no2);

            nysn = String.valueOf(num);
            nys = nys.concat(nysn);

        } else if (num < 100) {
            fc = fname.charAt(0);
            lc = lname.charAt(0);

            fcc = String.valueOf(fc);
            fcc = fcc.toUpperCase();

            lcc = String.valueOf(lc);
            lcc = lcc.toUpperCase();

            ny = "16";
            no2 = "00";
            fcc = fcc.concat(lcc);

            ny = fcc.concat(ny);
            nys = ny.concat(no2);

            nysn = String.valueOf(num);
            nys = nys.concat(nysn);

        } else if (num < 1000) {
            fc = fname.charAt(0);
            lc = lname.charAt(0);

            fcc = String.valueOf(fc);
            fcc = fcc.toUpperCase();

            lcc = String.valueOf(lc);
            lcc = lcc.toUpperCase();

            ny = "16";
            no2 = "0";
            fcc = fcc.concat(lcc);

            ny = fcc.concat(ny);
            nys = ny.concat(no2);

            nysn = String.valueOf(num);
            nys = nys.concat(nysn);

        }

        return nys;
    }

    public String genID1(String fname, String lname) {
        String ny, nysn, fcc, lcc, nys = "";
        char fc, lc;
        String no2, test;
        int num = 0;


        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                test = cursor.getString(cursor.getColumnIndex("_id"));


            } while (cursor.moveToNext());
            cursor.close();

            num = Integer.parseInt(test);
        }


        if (num < 10) {
            fc = fname.charAt(0);
            lc = lname.charAt(0);

            fcc = String.valueOf(fc);
            fcc = fcc.toUpperCase();

            lcc = String.valueOf(lc);
            lcc = lcc.toUpperCase();

            ny = "16";
            no2 = "000";
            fcc = fcc.concat(lcc);

            ny = fcc.concat(ny);
            nys = ny.concat(no2);

            nysn = String.valueOf(num);
            nys = nys.concat(nysn);

        } else if (num < 100) {
            fc = fname.charAt(0);
            lc = lname.charAt(0);

            fcc = String.valueOf(fc);
            fcc = fcc.toUpperCase();

            lcc = String.valueOf(lc);
            lcc = lcc.toUpperCase();

            ny = "16";
            no2 = "00";
            fcc = fcc.concat(lcc);

            ny = fcc.concat(ny);
            nys = ny.concat(no2);

            nysn = String.valueOf(num);
            nys = nys.concat(nysn);

        } else if (num < 1000) {
            fc = fname.charAt(0);
            lc = lname.charAt(0);

            fcc = String.valueOf(fc);
            fcc = fcc.toUpperCase();

            lcc = String.valueOf(lc);
            lcc = lcc.toUpperCase();

            ny = "16";
            no2 = "0";
            fcc = fcc.concat(lcc);

            ny = fcc.concat(ny);
            nys = ny.concat(no2);

            nysn = String.valueOf(num);
            nys = nys.concat(nysn);

        }

        return nys;
    }


    public boolean checkEmail(String e) {

        boolean b = true;
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                String productname = cursor.getString(cursor.getColumnIndex("email"));

                if (productname.equals(e.toLowerCase())) {
                    Toast.makeText(context, " Email Already Registered ", Toast.LENGTH_LONG).show();
                    b = false;
                    Toast.makeText(context, " Try to Login using your Email ", Toast.LENGTH_LONG).show();
                }

            } while (cursor.moveToNext());
            cursor.close();
        }
        return b;
    }


    public String checkRecord(Product p) {

        String test = "";

        if (p.get_email().equals("")) {

            if (p.get_pass().equals("")) {
                test = "abc";
            } else {
                test = "empty";
            }

        } else {
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                    null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String productname1 = p.get_email();
                    String password1 = p.get_pass();
                    String productname = cursor.getString(cursor.getColumnIndex("email"));
                    String password = cursor.getString(cursor.getColumnIndex("pass"));
                    String fname = cursor.getString(cursor.getColumnIndex("fname"));
                    String lname = cursor.getString(cursor.getColumnIndex("lname"));
                    String tid = cursor.getString(cursor.getColumnIndex("_id"));

                    if (!productname.equals(productname1) && !password.equals(password1)) {
                        test = "wc";

                    } else if (!productname.equals(productname1) && password.equals(password1)) {
                        test = "we";
                    } else if (productname.equals(productname1) && !password.equals(password1)) {
                        test = "wp";
                    } else if (productname.equals(productname1) && password.equals(password1)) {
                        Toast.makeText(context, " Welcome to F.O.E.H.N : )", Toast.LENGTH_LONG).show();
                        test = "yeah";
                        tfname = fname;
                        tlname = lname;
                        tnum = Integer.parseInt(tid);
                        break;
                    } else {
                        Toast.makeText(context, " TRY AGAIN ", Toast.LENGTH_LONG).show();

                    }

                } while (cursor.moveToNext());
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
