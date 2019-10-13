package com.example.firstassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {
    private int resourceid;

    public CourseAdapter(Context context, int textid, List<Course> c){
        super(context,textid,c);
        resourceid=textid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Course course=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceid,parent,false);
        ImageView courseimage=(ImageView)view.findViewById(R.id.course_image);
        TextView coursename=(TextView)view.findViewById(R.id.course_name);
        courseimage.setImageResource(course.getImageid());
        coursename.setText(course.getName());

        return view;

    }
}
