package com.example.facebooklogin.fragmentClasses;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.facebooklogin.taps.MyBroadcastReceiver;
import com.example.facebooklogin.R;

import java.util.Calendar;
import java.util.Date;

public class Fragment_Third extends Fragment {
    TimePicker timePicker;
    TextView textView;
    Button button1;
    int mHour, mMin;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    AlarmManager.OnAlarmListener onAlarmListener;
    Intent i;
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

        timePicker = (TimePicker)view.findViewById(R.id.timePicker);
        i = new Intent(getActivity(), MyBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(),0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hour_now, int min_now) {
                mHour = hour_now;
                mMin = min_now;
            }
        });


        final Button button_on = (Button) view.findViewById(R.id.button);
        button_on.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View view)
            {
                System.out.println("REACHED CLCICK");
                System.out.println("pendingIntent for start" + pendingIntent);
                System.out.println("Intent for start" + i);

//                i = new Intent(getActivity(), MyBroadcastReceiver.class);
//                pendingIntent = PendingIntent.getBroadcast(getActivity(),0,i,PendingIntent.FLAG_UPDATE_CURRENT);

                Date date = new Date();

                Calendar cal_alarm = Calendar.getInstance();
                Calendar cal_now = Calendar.getInstance();

                cal_now.setTime(date);
                cal_alarm.setTime(date);

                cal_alarm.set(Calendar.HOUR_OF_DAY,mHour);
                cal_alarm.set(Calendar.MINUTE,mMin);
                cal_alarm.set(Calendar.SECOND,0);

                if(cal_alarm.before(cal_now))
                {
                    cal_alarm.add(Calendar.DATE,1);
                }

                System.out.println(pendingIntent);
                alarmManager.set(AlarmManager.RTC,cal_alarm.getTimeInMillis(), pendingIntent);
            }
        });


        final Button alarm_off = view.findViewById(R.id.btn_stop);
        final TextView text_pwhint = (TextView) view.findViewById(R.id.passwordhint);
        text_pwhint.setText(randomAlphaNumeric(1));
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text_pw = (EditText) view.findViewById(R.id.password);
                String pw = text_pw.getText().toString();

                String pw_hint = text_pwhint.getText().toString();
                System.out.println(pw_hint);
                System.out.println(pendingIntent);
                alarmManager.cancel(pendingIntent);
//                i.putExtra("state", "alarm off");
//                getActivity().sendBroadcast(i);
                if (pendingIntent == null){
                    Toast.makeText(getActivity(), "No alarm has been set", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pw.equals(pw_hint)) {
                        Toast.makeText(getActivity(), "Password correct! Alarm Off", Toast.LENGTH_SHORT).show();

                        System.out.println("REACHED CLCICK stop");
//                        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

//                        i = new Intent(getActivity(), NextActivity.class);
//                        pendingIntent = PendingIntent.getService(getActivity(),0,i,PendingIntent.FLAG_UPDATE_CURRENT);

//                        System.out.println(pendingIntent);


                        System.out.println("pendingIntent for stop" + pendingIntent);
                        System.out.println("Intent for stop" + i);
                       alarmManager.cancel(pendingIntent);
                        alarmManager.cancel(pendingIntent);
                        alarmManager.cancel(pendingIntent);
                        alarmManager.cancel(pendingIntent);
                        alarmManager.cancel(pendingIntent);


//                        i.putExtra("state", "alarm off");
//                        getActivity().sendBroadcast(i);
                    }
                    else {
                        Toast.makeText(getActivity(), "password wrong try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }




    public void setTimer (View v)
    {
        System.out.println("REACHED setTimer");

        Date date = new Date();

        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();

        cal_now.setTime(date);
        cal_alarm.setTime(date);

        cal_alarm.set(Calendar.HOUR_OF_DAY,mHour);
        cal_alarm.set(Calendar.MINUTE,mMin);
        cal_alarm.set(Calendar.SECOND,0);

        if(cal_alarm.before(cal_now))
        {
            cal_alarm.add(Calendar.DATE,1);
        }
        alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pendingIntent);
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&**()_+=-;/?<>,.{}[]";
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}