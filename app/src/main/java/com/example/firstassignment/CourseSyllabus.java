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

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class CourseSyllabus extends Fragment {

    private TextView teacher;
    private Retrofit mRetrofit;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.course_syllabus,container,false);

        teacher=(TextView) contentView.findViewById(R.id.teacher);

        DatabaseHelper databaseHelper=new DatabaseHelper(getContext(),"teacherIntro",null,1);
        db=databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("teachername","null");
        db.insert("userbrowseteacher",null,values);

        try {
            Properties p=new Properties();
            p.load(getContext().getAssets().open("infor.properties"));
            getTeacher(p.getProperty("baseurl"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public interface GetRequest{
        @GET("elearn/courses/001/teachers")
        Call<List<Teacher>> getCall();

    }
    private void getTeacher(String baseurl){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseurl)//base的网络地址  baseUrl不能为空,且强制要求必需以 / 斜杠结尾
                .addConverterFactory(GsonConverterFactory.create())//使用Gson解析
                .build();

        // 创建 网络请求接口 的实例
        final GetRequest request = mRetrofit.create(GetRequest.class);

        //对 发送请求 进行封装
        Call<List<Teacher>> call = request.getCall();

        //发送网络请求(异步)
        call.enqueue(new Callback<List<Teacher>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                Teacher t=response.body().get(0);
                teacher.setText("教师姓名"+t.getName()+"\n联系方式："+t.getTelephone()+"\n电子邮件："+t.getEmail()+"\n岗位描述："+t.getDescription());

                //使用SQLite保存从网络获得的数据
                ContentValues valu = new ContentValues();
                valu.put("teachername",t.getName());
                db.update("userbrowseteacher",valu,"teachername=? ",
                        new String[]{t.getName()});

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
                Intent intent1=new Intent("test");
                intent1.setComponent(new ComponentName("com.example.firstassignment","com.example.firstassignment.MyBroadCastReceiver"));
                getActivity().sendBroadcast(intent1);


                Cursor cursor=db.query("userbrowsetaecher",null,null,null,null,null,null );
                boolean succeed = (cursor.moveToFirst());
                if  (succeed) {
                    do {
                        String name = cursor.getString
                                (cursor.getColumnIndex("taechername"));

                        teacher.setText("课程名称："+name);
                        //查完一条之后调用cursor.moveToNext()把cursor的位置移动到下一条
                    } while (cursor.moveToNext());

                }
                cursor.close();
            }

        });
    }
}
