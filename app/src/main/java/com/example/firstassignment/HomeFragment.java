package com.example.firstassignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    private List<Course> courseList=new ArrayList<>();
    private ArrayList imagepath;
    private MyImageLoader myloader;
    private Banner banner;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.home_fragment,container,false);
        banner=(Banner) contentView.findViewById(R.id.banner);
        initCourse();
        initPicture();
        initBanner();

        CourseAdapter adapter = new CourseAdapter(getActivity(),courseList);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Course c=courseList.get(position);
                //Toast.makeText(getActivity(), "detail message of course", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity().getApplicationContext(),DetailCourse.class);
                startActivity(intent);

            }
        });

        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initCourse(){
        for (int i=0;i<6;i++)
        {
            Course english=new Course(" 英语",R.mipmap.englishpic_icon);
            courseList.add(english);
            Course gailvlun=new Course(" 概率论",R.mipmap.gailvlun_icon);
            courseList.add(gailvlun);
        }
    }

    private void initBanner(){
        myloader=new MyImageLoader();
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(myloader);
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        banner.setDelayTime(5000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);

        banner.setImages(imagepath).setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        }).start();
    }

    private void initPicture(){
        imagepath=new ArrayList();
        imagepath.add(R.mipmap.cppcourse);
        imagepath.add(R.mipmap.javacourse);
        imagepath.add(R.mipmap.pythoncourse);

    }
    class MyImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext()).load(path).into(imageView);

        }
    }
}
