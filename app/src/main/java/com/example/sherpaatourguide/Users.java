package com.example.sherpaatourguide;

public class Users {

    private String Uid;
    private String Email;
    private String Password;
    private  int usertype;

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public Users() {

    }

    public Users( String uid, String email, String password, int usertype) {
        Uid = uid;
        Email = email;
        Password = password;
        usertype = usertype;

    }
    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
