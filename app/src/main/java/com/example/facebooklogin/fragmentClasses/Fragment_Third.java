package com.example.facebooklogin.fragmentClasses;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.facebooklogin.R;
import com.example.facebooklogin.tap3Activity.Tab3Main;

public class Fragment_Third extends Fragment {
    Boolean giveMoney = false;
    Button btn_chicken, button_on;
    public Fragment_Third(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_third, container, false);
        btn_chicken = (Button) view.findViewById(R.id.btn_chicken);
        button_on = (Button) view.findViewById(R.id.button);
        Intent intent = new Intent(getContext(), Tab3Main.class);

        btn_chicken.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                giveMoney = true;
                Toast.makeText(getActivity(), "당신의 가위바위보에 축복을!", Toast.LENGTH_SHORT).show();
            }
        });

        final Button button_on = (Button) view.findViewById(R.id.button);
        button_on.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                intent.putExtra("Money", giveMoney);
                startActivity(intent);
            }
        });


        return view;
    }

}