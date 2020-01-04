package com.example.firstassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.firstassignment.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;
import java.util.Properties;

public class listcourse extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private  HomeFragment hfragment;
    private InforFragment inforfragment;
    private SelfFragment selfFragment;
    private DrawerLayout mdrawlayout;
    private NavigationView mViewNavigation;
    private View headview;
    private TextView textview,uname;
    private SharedPreferences getinfor;
    private String baseurl;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS} , 1);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade);

        //退出时使用
        getWindow().setExitTransition(fade);
//第一次进入时使用
        getWindow().setEnterTransition(fade);
//再次进入时使用
        getWindow().setReenterTransition(fade);

        setContentView(R.layout.activity_listcourse);
        initFragment();
        //获得用户信息
        getinfor = getSharedPreferences("userinformation", MODE_PRIVATE);
        //获得ID
        mdrawlayout = (DrawerLayout) findViewById(R.id.draw_layout);
        mViewNavigation=(NavigationView)findViewById(R.id.navigation_view);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar);
        headview=mViewNavigation.getHeaderView(0);
        textview =headview.findViewById(R.id.logandreg);
        uname=headview.findViewById(R.id.text1);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mdrawlayout, toolbar, R.string.open, R.string.close);
        mdrawlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        try {
            Properties p=new Properties();
            p.load(getApplicationContext().getAssets().open("infor.properties"));
            baseurl=p.getProperty("baseurl");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(listcourse.this, "导航按钮点击事件", Toast.LENGTH_SHORT).show();
                mdrawlayout.openDrawer(GravityCompat.START);
                textview.setText(getinfor.getString("state", "登录/注册"));
                uname.setText(getinfor.getString("username", "个人中心"));
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
                            case R.id.nv_logout:
                                SharedPreferences.Editor del=getinfor.edit();
                                del.clear();
                                del.commit();
                                textview.setText(getinfor.getString("state", "登录/注册"));
                                uname.setText(getinfor.getString("username", "个人中心"));
                                Intent intent = new Intent(listcourse.this, LoginActivity.class);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(listcourse.this).toBundle());
                                return true;
                        }
                        return false;
                    }
                });
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_calendar:
                        showPopupMenu(findViewById(R.id.action_settings));
                        break;
                    case R.id.action_settings:
                        break;
                    case R.id.action_upload:
                        Upload up=new Upload();
                        up.initUploadBase(baseurl);
                        break;
                    case R.id.action_download:
                        Download dl=new Download();
                        dl.initDownloadBase(baseurl);
                        break;
                }
                return false;
            }
        });
        //登录注册界面
        if (getinfor.getBoolean("islogin",false)){
            textview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"你已经登录过了",Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            textview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(listcourse.this, LoginActivity.class);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(listcourse.this).toBundle());
                }
            });
        }

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
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.in,R.animator.out).replace(R.id.fragment_content, hfragment).commit();
                    return true;
                }
                case R.id.course_classify:
                {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.course_in,R.animator.course_out).replace(R.id.fragment_content, inforfragment).commit();
                    return true;
                }
                case R.id.item_3:
                {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.mystudy_in,R.animator.mystudy_out).replace(R.id.fragment_content, selfFragment).commit();
                    return true;
                }


            }
            return false;
        }
    };

    private void showPopupMenu(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.inflate(R.menu.up_and_download);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_d:
                        Toast.makeText(getApplicationContext(),"日历",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_u:
                        Toast.makeText(getApplicationContext(),"教学日程",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_dd:
                        Toast.makeText(getApplicationContext(),"学习日程",Toast.LENGTH_SHORT).show();
                    default:
                        //do nothing
                }

                return false;
            }
        });
        popupMenu.show();
    }


}
