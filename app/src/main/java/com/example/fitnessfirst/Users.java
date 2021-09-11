package com.example.fitnessfirst;

public class Users {
    private String uid;
    private String name;
    private String phonenumber;
    private String profileimage;


    public Users(String uid, String name, String profileimage) {
        this.uid = uid;
        this.name = name;
        this.profileimage = profileimage;
    }





    public String getProfileimage() {
        return profileimage;
    }

    public Users() {
    }

    public Users(String uid, String name, String phonenumber, String profileimage) {
        this.uid = uid;
        this.name = name;
        this.phonenumber = phonenumber;
        this.profileimage = profileimage;

    }





    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }
}
