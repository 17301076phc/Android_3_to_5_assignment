package com.example.firstassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstassignment.ui.login.LoginActivity;

public class DetailCourse extends AppCompatActivity {

    private CourseIntroduction courseIntroduction;
    private CourseSyllabus courseSyllabus;
    private TextView courintroduction;
    private TextView syllabuscourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);
        initFragment();
        syllabuscourse=(TextView)findViewById(R.id.syllabus);
        courintroduction=(TextView)findViewById(R.id.introduction);

        courintroduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,courseIntroduction).show(courseIntroduction).commit();
            }
        });

        syllabuscourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(DetailCourse.this, "dianlil", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,courseSyllabus).show(courseSyllabus).commit();
            }
        });
    }

    private void initFragment() {
        courseIntroduction=new CourseIntroduction();
        courseSyllabus=new CourseSyllabus();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,courseIntroduction).show(courseIntroduction).commit();
    }
}
