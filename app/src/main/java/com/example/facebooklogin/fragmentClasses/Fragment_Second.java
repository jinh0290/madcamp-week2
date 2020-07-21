package com.example.facebooklogin.fragmentClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.facebooklogin.ApiService_img;
import com.example.facebooklogin.FullImageActivity;
import com.example.facebooklogin.GridViewAdapter;
import com.example.facebooklogin.Image;
import com.example.facebooklogin.R;
import com.example.facebooklogin.imageReceived;
import com.example.facebooklogin.uploadPicture;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Fragment_Second extends Fragment {

    public ViewPager viewPager;
    //private List<String> imageNames;
    private GridViewAdapter gridViewAdapter;
    private GridView gridView;

    public Fragment_Second(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_second, container, false);

         gridView = (GridView) view.findViewById(R.id.gridView);

//        gridView.setAdapter(new ImageAdapter(getActivity()));

        //그리드뷰
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i = new Intent(getActivity().getApplicationContext(), FullImageActivity.class);
                i.putExtra("id",position);
                startActivity(i);

            }
        });

        //카메라 버튼
        FloatingActionButton button = (FloatingActionButton)view.findViewById(R.id.fab_camera);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getActivity().getApplicationContext(), uploadPicture.class);
                startActivity(i);
                getNamesFromServer();

            }
        });

        getNamesFromServer();

        return view;
    }

    //서버의 사진들의 이름을 받아서 List<String>으로 저장하는 함수
    private List<String> getNamesFromServer() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.249.19.241:5680")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ApiService_img apiService = retrofit.create(ApiService_img.class);
        Call<imageReceived> call = apiService.getNames();

        final ArrayList<String> imageNames = new ArrayList<String>();

        gridViewAdapter = new GridViewAdapter(getActivity().getApplicationContext());


        call.enqueue(new Callback<imageReceived>() {
            @Override
            public void onResponse(Call<imageReceived> call, final Response<imageReceived> response) {

                if (response.code() == 200) {
                    ArrayList<Image> images= response.body().getImages();
//                    Toast.makeText(getApplicationContext(), "HI", Toast.LENGTH_SHORT).show();
//                    System.out.println("HIHIHIHIHIHHIHIHIHIHHHIHI  " + imageNames.size());
                    for (int i = 0; i < images.size(); i++) {
                        //배열에있는 오브젝트를 가져와 이미지 이름 추출
                        imageNames.add(images.get(i).getName());
//                        Toast.makeText(getApplicationContext(), "l"+imageNames.get(0)+ "l", Toast.LENGTH_SHORT).show();
                    }
///////////////////////////////////////////////////////////////////////////////////////
                    gridViewAdapter.setImageNames(imageNames);
                    gridViewAdapter.notifyDataSetChanged();
                    gridView.setAdapter(gridViewAdapter);



                }else{
                    Toast.makeText(getApplicationContext(), "RESPONSE_ELSE", Toast.LENGTH_SHORT).show();
                }
            }
            //에러
            @Override
            public void onFailure(Call<imageReceived> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "no :(", Toast.LENGTH_SHORT).show();
            }
        });

        return imageNames;
    }

}

