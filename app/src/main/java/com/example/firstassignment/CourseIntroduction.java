package com.example.firstassignment;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class CourseIntroduction extends Fragment {

    private TextView course_name,course_code,course_description,course_open_and_data;
    private Retrofit mRetrofit;
    private SQLiteDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.course_introduction,container,false);

        course_name=(TextView)contentView.findViewById(R.id.course_name);
        course_code=(TextView)contentView.findViewById(R.id.course_code);
        course_description=(TextView)contentView.findViewById(R.id.course_description);
        course_open_and_data=(TextView)contentView.findViewById(R.id.open_and_data);

        DatabaseHelper databaseHelper=new DatabaseHelper(getContext(),"courseIntroduction",null,1);
        db=databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("coursename","null");
        values.put("coursecode","null");
        values.put("coursestatus","null");
        db.insert("userbrowsecourse",null,values);

        try{
            Properties p=new Properties();
            p.load(getContext().getAssets().open("infor.properties"));
            initHttpBase(p.getProperty("baseurl"));
        }catch (Exception e){
            e.printStackTrace();
        }

        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public interface GetRequest{
        @GET("elearn/courses/")
        Call<List<Course>> getCall();

    }

    private void initHttpBase(String baseurl){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseurl)//baseUrl不能为空,且必需以 / 斜杠结尾
                .addConverterFactory(GsonConverterFactory.create())//使用Gson解析
                .build();

        // 创建 网络请求接口 的实例
        final GetRequest request = mRetrofit.create(GetRequest.class);

        //对 发送请求 进行封装
        Call<List<Course>> call = request.getCall();

        //发送网络请求(异步)
        call.enqueue(new Callback<List<Course>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                //response.body().show();
                Course c=response.body().get(0);
                course_name.setText("课程名称："+c.getName());
                course_code.setText("课程码："+c.getCode());
                course_description.setText("课程描述:"+c.getDescription());
                course_open_and_data.setText("课程状态："+c.getStatus()+"\n"+"开放时间："+c.getOpenDate()+"----"
                        +c.getLastUpdateOn()+"\nLEVEL:"+c.getLevel()+"\n证书："+c.getCertification());
//使用SQLite保存从网络获得的数据
                ContentValues valu = new ContentValues();
                valu.put("coursename",c.getName());
                valu.put("coursecode",c.getCode());
                valu.put("coursestatus",c.getStatus());
                db.update("userbrowsecourse",valu,"coursename=? and coursecode=? and coursestatus=?",
                        new String[]{c.getName(),c.getCode(),c.getStatus()});

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<List<Course>> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
                Intent intent1=new Intent("test");
                intent1.setComponent(new ComponentName("com.example.firstassignment","com.example.firstassignment.MyBroadCastReceiver"));
                getActivity().sendBroadcast(intent1);

                Cursor cursor=db.query("userbrowsecourse",null,null,null,null,null,null );
                boolean succeed = (cursor.moveToFirst());
                if  (succeed) {
                    do {
                        String name = cursor.getString
                                (cursor.getColumnIndex("coursename"));
                        String code = cursor.getString
                                (cursor.getColumnIndex("coursecode"));
                        String status= cursor.getString
                                (cursor.getColumnIndex("coursestatus"));

                        course_name.setText("课程名称："+name);
                        course_code.setText("课程码："+code);
                        course_open_and_data.setText("课程状态："+status);
                        //查完一条之后调用cursor.moveToNext()把cursor的位置移动到下一条
                    } while (cursor.moveToNext());

                }
//全部查完后  把cursor关闭
                cursor.close();
            }

        });
    }
}
