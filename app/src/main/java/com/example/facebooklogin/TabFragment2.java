package com.example.facebooklogin;//package com.example.facebooklogin;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//
//import java.util.ArrayList;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class TabFragment2 implements View.OnClickListener{
//    /** Define Variables */
//    View view;
//    Button upload_button;
//    Button download_button;
//    private static final int MY_PERMISSION_CAMERA = 5555;
//    ListView listView;
//    ImageAdapter.ViewHolder viewHolder;
//    ImageAdapter imageAdapter = new ImageAdapter();
//    ImageView imageView;
//    public static ArrayList<String> response_array_gallery = new ArrayList<String>();
//    public static final String url = "http://192.249.19.243:8680/uploads/";
//    RecyclerView recyclerView;
//    Retrofit retrofit;
//    ApiService retrofitInterface;
//    ArrayList<String> mImageIds = new ArrayList<>();
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        mImageIds.add( "http://192.249.19.243:8680/uploads/b0d9e6708fefef3e4f70e61799b3ccc3.png");
//        mImageIds.add( "http://192.249.19.243:8680/uploads/359eb38478b8245696aec9522fa317c7.png");
//        mImageIds.add( "http://192.249.19.243:8680/uploads/e79d6af0076eeb35c3dac588cb17fcb6.png");
//        view = inflater.inflate(R.layout.fragment_object2, container, false);
//        upload_button = (Button) view.findViewById(R.id.upload_btn);
//        upload_button.setOnClickListener(this);
//        download_button = (Button) view.findViewById(R.id.download_btn);
//        download_button.setOnClickListener(this);
//        imageView = view.findViewById(R.id.show_image);
//        recyclerView = view.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(imageAdapter);
//        retrofit = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        retrofitInterface = retrofit.create(ApiService.class);
//        //imageAdapter.setItems(mImageIds);
//        return view;
//    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.upload_btn:
//                Intent intent = new Intent(getActivity(), UploadActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.download_btn:
//                System.out.println("Hello????????????????????????????????????");
//                Call<Result> call = retrofitInterface.getImage();
//                call.enqueue(new Callback<Result>() {
//                    @Override
//                    public void onResponse(Call<Result> call, Response<Result> response) {
//                        System.out.println(response.code());
//                        if(response.code() == 200){
//                            ArrayList<Result> response_array = response.body().getList();
//                            int len = response_array.size();
//                            for(int i = 0; i < len; i++){
//                                response_array_gallery.add("http://192.249.19.243:8680/uploads/" + response_array.get(i).getName());
//                                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + response_array_gallery.get(i));
//                                Glide
//                                        .with(getActivity())
//                                        .load(response_array_gallery.toArray(new String[0])[i])
//                                        .into(imageView);
//                            }
//                            System.out.println("SIZESIZESIZE" + response_array_gallery.size());
//                            imageAdapter.setItems(response_array_gallery);
//                            imageAdapter.notifyDataSetChanged();
//                        } else if (response.code() == 400){
//                            //Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<Result> call, Throwable t) {
//                        //Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                break;
//        }
//    }
//}