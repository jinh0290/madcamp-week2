package com.example.facebooklogin.taps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.facebooklogin.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Dummy> sample;

    public MyAdapter(Context context, ArrayList<Dummy> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Dummy getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(final int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.listview_custom, null);

        TextView Name = (TextView)view.findViewById(R.id.Name);
        TextView PhoneNum = (TextView)view.findViewById(R.id.PhoneNum);
        TextView Email = (TextView)view.findViewById(R.id.Email);

        Name.setText(sample.get(position).getName());
        PhoneNum.setText(sample.get(position).getPhoneNum());
        Email.setText(sample.get(position).getEmail());

        Button button = (Button)view.findViewById(R.id.call);

        final String phone_number = sample.get(position).getPhoneNum();


        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println("REACHED CLCICK stop");
                System.out.println(phone_number);

//                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:12345"));
//                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000")));
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+ phone_number)));

            }
        });




        return view;
    }
}