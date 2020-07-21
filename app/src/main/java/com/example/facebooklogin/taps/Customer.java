package com.example.facebooklogin.taps;

import com.google.gson.annotations.SerializedName;

////////////json 파일을 파싱한 후 받아오는 실제 데이터들이 저장된 꼴////////////
// 연락처에 있는 개개인의 이름과 전화번호를 담은 객체
public class Customer {
    @SerializedName("_id")
    String id;
    String nameContact;
    String phoneNum;

    Customer(String nameContact, String phoneNum, String id){
        this.id = id;
        this.nameContact = nameContact;
        this.phoneNum = phoneNum;
    }
    public String getNameContact(){
        return nameContact;
    }
    public String getPhoneNum(){
        return phoneNum;
    }
    public String getId(){
        return id;
    }
}
