package com.example.facebooklogin.fragmentClasses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.facebooklogin.taps.Dummy;
import com.example.facebooklogin.taps.MyAdapter;
import com.example.facebooklogin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Fragment_First extends Fragment {

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

        ListView listView = (ListView)view.findViewById(R.id.thelist);
        get_json();
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



        return view;
    }

    public void get_json()
    {
        String json;
        try
        {
            InputStream is = getActivity().getAssets().open("a.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i =0; i<jsonArray.length();i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                String Name = jsonArray.getJSONObject(i).getString("name");
                String Phonenum = jsonArray.getJSONObject(i).getString("phone");
                String Email = jsonArray.getJSONObject(i).getString("email");

                dummyDataList.add(new Dummy(Name,Phonenum,Email));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }



    }


}