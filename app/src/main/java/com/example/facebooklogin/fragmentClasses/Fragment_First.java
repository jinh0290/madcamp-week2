package com.example.facebooklogin.fragmentClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.facebooklogin.LoginResult2;
import com.example.facebooklogin.MainActivity;
import com.example.facebooklogin.RetrofitInterface;
import com.example.facebooklogin.taps.Customer;
import com.example.facebooklogin.taps.CustomersResponse;
import com.example.facebooklogin.taps.Dummy;
import com.example.facebooklogin.taps.MainActivity2;
import com.example.facebooklogin.taps.MyAdapter;
import com.example.facebooklogin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_First extends Fragment {

    private Button btn_insert_complete;
    private FloatingActionButton btn_insert;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.249.19.242:8380";
    private String email;

    public Fragment_First(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    ArrayList<Dummy> dummyDataList= new ArrayList<Dummy>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        View view = inflater.inflate(R.layout.fragment_first, container, false);
        email = getArguments().getString("email");

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        ListView listView = (ListView)view.findViewById(R.id.thelist);
        get_phones();

        final MyAdapter myAdapter = new MyAdapter(getActivity(),dummyDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getActivity().getApplicationContext(),
                        myAdapter.getItem(position).getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

        btn_insert = (FloatingActionButton) view.findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInsertPhone();
            }

        });


        return view;
    }

    private void handleInsertPhone() {
        View view = getLayoutInflater().inflate(R.layout.insert_phone_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).show();

        btn_insert_complete = (Button) view.findViewById(R.id.btn_insert_complete);
        final TextView nameEdit = view.findViewById(R.id.nameEdit);
        final TextView phoneEdit = view.findViewById(R.id.phoneEdit);
        btn_insert_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();

                map.put("nameContact", nameEdit.getText().toString());
                map.put("phoneNum", phoneEdit.getText().toString());
                map.put("email", email);
                Call<CustomersResponse> call = retrofitInterface.insertPhone(map);

                call.enqueue(new Callback<CustomersResponse>() {
                    @Override
                    public void onResponse(Call<CustomersResponse> call, Response<CustomersResponse> response) {
                        if (response.code() == 200) {

                            CustomersResponse result = response.body();
                            ArrayList<Customer> CustomerList = result.getContacts();
                            dummyDataList.clear();
                            for (int i=0; i < CustomerList.size(); i++) {
                                 Customer customer = CustomerList.get(i); //error 떠
                                 String nameContact = customer.getNameContact();
                                 String phoneNum = customer.getPhoneNum();
                                dummyDataList.add(new Dummy(nameContact, phoneNum,""));
                            }




                        } else if (response.code() == 404) {
                            Toast.makeText(getActivity(), "Error 404",
                                    Toast.LENGTH_LONG).show();
                        } else if (response.code() == 400) {
                            Toast.makeText(getActivity(), "Error 400",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<CustomersResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(),
                                Toast.LENGTH_LONG).show();

                    }

                });

            }
        });
    }

    //   dummyDataList에 추가
    public void get_phones()
    {

        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);

        Call<CustomersResponse> call = retrofitInterface.initializePhone(map);

        call.enqueue(new Callback<CustomersResponse>() {
            @Override
            public void onResponse(Call<CustomersResponse> call, Response<CustomersResponse> response) {
                if (response.code() == 200) {

                    CustomersResponse result = response.body();
                    ArrayList<Customer> CustomerList = result.getContacts();
                    dummyDataList.clear();
                    for (int i=0; i < CustomerList.size(); i++) {
                        Customer customer = CustomerList.get(i); //error 떠
                        String nameContact = customer.getNameContact();
                        String phoneNum = customer.getPhoneNum();
                        dummyDataList.add(new Dummy(nameContact, phoneNum,""));
                    }


                } else if (response.code() == 404) {
                    Toast.makeText(getActivity(), "Error 404",
                            Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(getActivity(), "Error 400",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<CustomersResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }

        });



    }


}