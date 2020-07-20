package com.example.facebooklogin.taps;

public class Dummy {
    private String Name;
    private String PhoneNum;
    private String Email;

    public Dummy( String name, String phonenum, String email){
        this.Name = name;
        this.PhoneNum = phonenum;
        this.Email = email;
    }

    public String getName()
    {
        return this.Name;
    }

    public String getPhoneNum()
    {
        return this.PhoneNum;
    }

    public String getEmail()
    {
        return this.Email;
    }
}