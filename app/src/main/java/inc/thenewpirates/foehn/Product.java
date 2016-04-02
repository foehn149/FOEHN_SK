package inc.thenewpirates.foehn;

public class Product {

    private int _id;
    private String _fname;
    private String _lname;
    private String _mobile;
    private String _dob;
    private String _email;
    private String _pass;
    private String _cpass;

    public Product(String fname, String lname, String mobile, String dob, String email, String pass, String cpass) {
        this._fname = fname;
        this._lname = lname;
        this._mobile = mobile;
        this._dob = dob;
        this._email = email;
        this._pass = pass;
        this._cpass = cpass;

    }

    public Product(String email, String pass) {
        this._email = email;
        this._pass = pass;
    }

    public Product(String Product) {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public String get_fname() {
        return _fname;
    }

    public void set_fname(String fname) {
        this._fname = fname;
    }

    public String get_lname() {
        return _lname;
    }

    public void set_lname(String lname) {
        this._lname = lname;
    }

    public String get_mobile() {
        return _mobile;
    }

    public void set_mobile(String mobile) {
        this._mobile = mobile;
    }

    public String get_dob() {
        return _dob;
    }

    public void set_dob(String dob) {
        this._dob = dob;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String email) {
        this._email = email;
    }

    public String get_pass() {
        return _pass;
    }

    public void set_pass(String pass) {
        this._pass = pass;
    }

    public String get_cpass() {
        return _cpass;
    }

    public void set_cpass(String cpass) {
        this._cpass = cpass;
    }

}

