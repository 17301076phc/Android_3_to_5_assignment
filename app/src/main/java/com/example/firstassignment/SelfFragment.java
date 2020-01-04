package com.example.firstassignment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SelfFragment extends Fragment {

    private ArrayList<ArrayList<CourseIndro>> iData;
    private ExpandableListView exlist_lol;
    private CourselistAdapter myAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.self_fragment,container,false);

        exlist_lol = (ExpandableListView)contentView.findViewById(R.id.courselist);

        ArrayList<Coursegroup> gData = new ArrayList<Coursegroup>();
        gData.add(new Coursegroup("正在进行的课程"));
        gData.add(new Coursegroup("即将开始的课程"));
        gData.add(new Coursegroup("已经结束的课程"));

        iData = new ArrayList<ArrayList<CourseIndro>>();
//正在进行的课程
        ArrayList<CourseIndro> lData = new ArrayList<CourseIndro>();
        lData.add(new CourseIndro(1,"英语",R.mipmap.english_icon));
        lData.add(new CourseIndro(2,"概率论",R.mipmap.gailvlun_icon));
        iData.add(lData);
        //即将开始的课程
        ArrayList<CourseIndro> Data2 = new ArrayList<CourseIndro>();
        Data2.add(new CourseIndro(1,"python",R.mipmap.python_icon));
        iData.add(Data2);
//已经结束的课程
        ArrayList<CourseIndro> Data3=new ArrayList<>();
        Data3.add(new CourseIndro(1,"java",R.mipmap.java_icon));
        iData.add(Data3);

        myAdapter = new CourselistAdapter(gData,iData,getContext());
        exlist_lol.setAdapter(myAdapter);

        exlist_lol.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getContext(), "你点击了：" + iData.get(groupPosition).get(childPosition).getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
