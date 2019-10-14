package com.example.firstassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Course> courseList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.home_fragment,container,false);
        Toast.makeText(getActivity(), "欢迎来到主界面", Toast.LENGTH_SHORT).show();
        courseList=new ArrayList<>();
        initCourse();
        CourseAdapter courseAdapter=new CourseAdapter(getContext(),R.layout.course_item,courseList);
        final ListView listView=(ListView)contentView.findViewById(R.id.list_view);
        listView.setAdapter(courseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course c=courseList.get(position);
                Toast.makeText(getActivity(), "detail message of course", Toast.LENGTH_SHORT).show();
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
        Course english=new Course(" 英语",R.mipmap.englishpic_icon);
        courseList.add(english);
        Course gailvlun=new Course(" 概率论",R.mipmap.gailvlun_icon);
        courseList.add(gailvlun);

        Course english1=new Course(" 英语",R.mipmap.englishpic_icon);
        courseList.add(english);
        Course gailvlun1=new Course(" 概率论",R.mipmap.gailvlun_icon);
        courseList.add(gailvlun);
    }
}
