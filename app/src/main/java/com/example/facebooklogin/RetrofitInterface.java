package com.example.facebooklogin;

import com.example.facebooklogin.taps.CustomersResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    //post request
    //login 하면 이름 보내줌
    //para로 email, password 보내줌
    @POST("/login")
    Call<LoginResult2> executeLogin(@Body HashMap<String, String> map); // email, password
    //signup 하면 보내는 정보 없음.
    //para로 email, name, password 보내줌
    @POST("/signup")
    Call<Void> executeSignup(@Body HashMap<String, String> map); // email, password, name

    @POST("/insert")
    Call<CustomersResponse> insertPhone(@Body HashMap<String, String> map); // email, nameContact, phoneNum, password, name

}