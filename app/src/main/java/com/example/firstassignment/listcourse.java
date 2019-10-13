package com.example.firstassignment;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.firstassignment.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class listcourse extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private  HomeFragment hfragment;
    private InforFragment inforfragment;
    private SelfFragment selfFragment;
    private Fragment[] fragments;
    private DrawerLayout mdrawlayout;
    private NavigationView mViewNavigation;
    private View headview;
    private TextView textview;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcourse);
        initFragment();
        //获得ID
        mdrawlayout = (DrawerLayout) findViewById(R.id.draw_layout);
        mViewNavigation=(NavigationView)findViewById(R.id.navigation_view);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar);
        headview=mViewNavigation.getHeaderView(0);
        textview =headview.findViewById(R.id.logandreg);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mdrawlayout, toolbar, R.string.open, R.string.close);
        mdrawlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(listcourse.this, "导航按钮点击事件", Toast.LENGTH_SHORT).show();
                mdrawlayout.openDrawer(GravityCompat.START);

                mViewNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.nv_build:
                                Toast.makeText(listcourse.this, "设置", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.nv_msg:
                                Toast.makeText(listcourse.this, "信息", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.nv_course:
                                Toast.makeText(listcourse.this, "课程", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
            }
        });

        //登录注册界面
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(listcourse.this, LoginActivity.class);
               startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        //返回按钮的监听
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.draw_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //初始化fragment和fragment数组
    private void initFragment() {

        hfragment = new HomeFragment();
        inforfragment = new InforFragment();
        selfFragment=new SelfFragment();
        fragments = new Fragment[]{hfragment,inforfragment,selfFragment};
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,hfragment).show(hfragment).commit();
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);
    }
    //判断选择的菜单
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId())
            {
                case R.id.home:
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, hfragment).commit();
                    return true;
                }
                case R.id.course_classify:
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, inforfragment).commit();
                    return true;
                }
                case R.id.item_3:
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, selfFragment).commit();
                    return true;
                }


            }
            return false;
        }
    };

}
