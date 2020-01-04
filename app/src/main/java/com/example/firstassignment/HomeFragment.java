package com.example.firstassignment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstassignment.connectDB.CourseDB;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    private List<CourseIndro> courseList;
    private List<Images> imagepath;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.home_fragment,container,false);
        initCourse();
        initPicture();
        CourseAdapter adapter = new CourseAdapter(courseList,imagepath);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CourseIndro c=courseList.get(position);
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
        courseList=new ArrayList<>();
        for (int i=0;i<6;i++)
        {
            CourseIndro english=new CourseIndro(i," 英语",R.drawable.english_icon_web);
            courseList.add(english);
            //CourseIndro gailvlun=new CourseIndro(" 概率论",R.mipmap.gailvlun_icon);
           // courseList.add(gailvlun);
            update(english);
        }

    }

    private void initPicture(){
        imagepath=new ArrayList<>();
        imagepath.add(new Images(R.mipmap.cppcourse));
        imagepath.add(new Images(R.mipmap.javacourse));
        imagepath.add(new Images(R.mipmap.pythoncourse));

    }

    //增删改查
    private void insert(CourseIndro ci) {
        CourseDB.getInstance(this.getContext()).getCourseDao().insert(ci);

    }
    private void update(CourseIndro ci) {
        CourseDB.getInstance(this.getContext()).getCourseDao().update(ci);
    }
    private void delete(CourseIndro ci){
        CourseDB.getInstance(this.getContext()).getCourseDao().delete(ci);
    }
    private List<CourseIndro> query(){
        return CourseDB.getInstance(this.getContext()).getCourseDao().getCourseIndro();
    }
}
