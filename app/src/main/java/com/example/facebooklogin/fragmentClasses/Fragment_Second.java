package com.example.facebooklogin.fragmentClasses;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.facebooklogin.taps.FullImageActivity;
import com.example.facebooklogin.taps.ImageAdapter;
import com.example.facebooklogin.R;
import com.example.facebooklogin.taps.uploadpicture;

public class Fragment_Second extends Fragment {

    public ViewPager viewPager;

    public Fragment_Second(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(getActivity()));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i = new Intent(getActivity().getApplicationContext(), FullImageActivity.class);
                i.putExtra("id",position);
                startActivity(i);
            }
        });

        FloatingActionButton button = (FloatingActionButton)view.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getActivity().getApplicationContext(),uploadpicture.class);
                startActivity(i);
            }
        });

        return view;
    }

}