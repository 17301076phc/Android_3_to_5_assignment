package com.example.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.firstassignment.ui.login.LoginActivity;

import java.util.concurrent.Executors;
//retrofit架构
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

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
        playvideo();

        courintroduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,courseIntroduction).show(courseIntroduction).commit();
            }
        });

        syllabuscourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,courseSyllabus).show(courseSyllabus).commit();
            }
        });
    }

    private void initFragment() {
        courseIntroduction=new CourseIntroduction();
        courseSyllabus=new CourseSyllabus();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,courseIntroduction).show(courseIntroduction).commit();
    }

    private void playvideo(){
        //网络视频
        String videoUrl2 = Utils.videoUrl ;

        JCVideoPlayerStandard player = (JCVideoPlayerStandard) findViewById(R.id.player_list_video);
        player.setUp(videoUrl2, JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");

    }

    class Utils {
        public static final String videoUrl = "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4" ;

    }

}
