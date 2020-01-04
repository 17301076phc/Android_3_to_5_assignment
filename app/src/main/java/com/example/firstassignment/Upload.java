package com.example.firstassignment;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class Upload {
    private Retrofit mRetrofit;
    private List<Teacher> teacher;


    private void initTeacher(){
        Teacher t=new Teacher();
        teacher=new ArrayList<>();
        t.setUserid("3");
        t.setCourseId("001");
        t.setDescription("good");
        t.setEmail("11@123.com");
        t.setName("皮");
        t.setPhoto("001/photo.jpg");
        t.setTelephone("123123123");
        teacher.add(t);
    }

    public interface upload{
        @POST("elearn/courses/001/teachers")
        Call<List<Teacher>> uploadjosn(@Body List<Teacher> t);

    }

    public void initUploadBase(String baseurl){
        initTeacher();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        upload getupload=mRetrofit.create(upload.class);
        //对 发送请求 进行封装
        Call<List<Teacher>> call = getupload.uploadjosn(teacher);

        //发送网络请求(异步)
        call.enqueue(new Callback<List<Teacher>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                Log.i("s0","上传成功");
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
    }

}
