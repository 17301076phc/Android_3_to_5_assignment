package com.example.firstassignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class CourseIntroduction extends Fragment {

    private TextView course_name_teacher,textv4,textv5,textv6;
    private Retrofit mRetrofit;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.course_introduction,container,false);

        course_name_teacher=(TextView)contentView.findViewById(R.id.course_name_teacher);
        textv4=(TextView)contentView.findViewById(R.id.textView4);
        textv5=(TextView)contentView.findViewById(R.id.textView5);
        textv6=(TextView)contentView.findViewById(R.id.textView6);

        initHttpBase();
        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public interface GetRequest{
        @GET("http://172.24.51.35:7788/")
            // @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world") //网络请求路径
        Call<CourseIndro> getCall();

    }

    private void initHttpBase() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://172.24.51.35:7788/")//base的网络地址  baseUrl不能为空,且强制要求必需以 / 斜杠结尾
                .addConverterFactory(GsonConverterFactory.create())//使用Gson解析
                .build();

        // 创建 网络请求接口 的实例
        final GetRequest request = mRetrofit.create(GetRequest.class);

        //对 发送请求 进行封装
        Call<CourseIndro> call = request.getCall();

        //发送网络请求(异步)
        call.enqueue(new Callback<CourseIndro>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<CourseIndro> call, Response<CourseIndro> response) {
                // 步骤7：处理返回的数据结果
                //response.body().show();
                CharSequence cs;
                cs = response.body().getCourse_name_teacher();
                course_name_teacher.setText(cs);
                cs = response.body().getCourse_illustrate();
                textv4.setText(cs);
                cs = response.body().getCourse_time();
                textv5.setText(cs);
                cs = response.body().getTime();
                textv6.setText(cs);
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<CourseIndro> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
    }
}
