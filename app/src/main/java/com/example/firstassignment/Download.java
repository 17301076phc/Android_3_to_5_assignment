package com.example.firstassignment;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;

public class Download {
    private Retrofit mRetrofit;
    File filepic;
    public interface download{
        @Streaming
        @GET("elearn/courses/001/photo")
        Call<ResponseBody> downloadpic();

    }

    public void initDownloadBase(String baseurl){

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        download getdownload=mRetrofit.create(download.class);
        //对 发送请求 进行封装
        Call<ResponseBody> call = getdownload.downloadpic();

        //发送网络请求(异步)
        call.enqueue(new Callback<ResponseBody>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                filepic=new File("downloadpicture.jpg");
                InputStream in=response.body().byteStream();
                try {
                    FileOutputStream fileout=new FileOutputStream(filepic);
                    BufferedInputStream buffin=new BufferedInputStream(in);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len=buffin.read(buffer)) != -1) {
                        DownloadThread dt=new DownloadThread(fileout,len);
                        len=buffin.read(buffer);
                        DownloadThread dt1=new DownloadThread(fileout,len);
                        len=buffin.read(buffer);
                        DownloadThread dt2=new DownloadThread(fileout,len);
                        dt.run();
                        dt1.run();
                        dt2.run();
                    }
                    fileout.close();
                    buffin.close();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }


                Log.i("download","下载成功");
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
    }


}
