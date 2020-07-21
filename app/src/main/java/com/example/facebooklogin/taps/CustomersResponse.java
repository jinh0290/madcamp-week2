package com.example.facebooklogin.taps;

import java.util.ArrayList;
//////////////서버에서 받아오는 json 파일 자체를 파싱하는 클래스////////////
// contactList에 담겨있는 연락처 사람들의 리스트가 getContacts를 통해 반환될 수 있다.
public class CustomersResponse {
    private ArrayList<Customer> contactList;
    private String email, password, name;
    public CustomersResponse(ArrayList<Customer> contactList, String email, String password, String name ){
        this.contactList = contactList;
        this.email = email;
        this.password = password;
        this.name = name;
    }
    public ArrayList<Customer> getContacts(){
        return contactList;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }
}