package com.example.facebooklogin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    private Context context;


    private ArrayList<String> imageNames;

    public Integer[] images = {
            R.drawable.pic_1, R.drawable.pic_2,
            R.drawable.pic_3, R.drawable.pic_4,
            R.drawable.pic_5, R.drawable.pic_6,
            R.drawable.pic_7, R.drawable.pic_8,
            R.drawable.pic_9, R.drawable.pic_10,
            R.drawable.pic_11, R.drawable.pic_12,
            R.drawable.pic_13, R.drawable.pic_14,
            R.drawable.pic_15, R.drawable.pic_16,
            R.drawable.pic_17, R.drawable.pic_18,
            R.drawable.pic_19, R.drawable.pic_20,
            R.drawable.pic_21



    };

    public GridViewAdapter(Context c){context = c;}

    public GridViewAdapter(Context c, ArrayList<String> imageNames){
        context = c;
        this.imageNames = imageNames;
    }

    @Override
    public int getCount() {
        return imageNames.size();
//        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setImageNames(ArrayList<String> imageNames) {
        this.imageNames = imageNames;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);

//        Toast.makeText(getApplicationContext(), "imageNames size: " + imageNames.size(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Position: " + position, Toast.LENGTH_SHORT).show();

        String img_name = imageNames.get(position);

        if (position> imageNames.size())
        {int a = position - imageNames.size();
            img_name = imageNames.get(a);};

//        Toast.makeText(getApplicationContext(), img_name, Toast.LENGTH_SHORT).show();
        Glide
                .with(context)
                .load("http://192.249.19.241:5680/uploads/" + img_name)
//                .load("http://192.249.19.241:5680/uploads/5bfe745c31ab2231eb34f21d737d8a7e.png")
                .apply(new RequestOptions().override(500,500))
                .into(imageView);

       // imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,350));


        return imageView;
    }



}