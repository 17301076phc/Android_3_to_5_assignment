package com.example.firstassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    ViewPager mViewPager;
    //导航页图片资源
    public int[] guides = new int[] { R.drawable.guide1,
            R.drawable.guide2, R.drawable.guide3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mViewPager = (ViewPager) findViewById(R.id.guideviewpager);
        initWithPageGuideMode();

    }

    public void initWithPageGuideMode() {
        List<View> mList = new ArrayList<View>();
        LayoutInflater inflat = LayoutInflater.from(this);

        View item = inflat.inflate(R.layout.pageguide, null);
        mList.add(item);
        for (int index : guides) {
            item = inflat.inflate(R.layout.pageguide, null);
            item.setBackgroundResource(index);
            mList.add(item);
        }

        TextView btn = (TextView) item.findViewById(R.id.textskip);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this,listcourse.class);
                startActivity(intent);
            }
        });
        //再添加一个最右侧空的view
        item = inflat.inflate(R.layout.pageguide, null);
        mList.add(item);
        //ViewPager设置Adapter
        MViewPageAdapter adapter = new MViewPageAdapter(mList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(adapter);
        mViewPager.setCurrentItem(1);
    }

    class MViewPageAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
        private List<View> views;

        public MViewPageAdapter(List<View> v){
            views=v;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position), 0);
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                mViewPager.setCurrentItem(1);
            } else if (position == views.size() - 1) {
                mViewPager.setCurrentItem(position - 1);
                Intent intent=new Intent(GuideActivity.this,listcourse.class);
                startActivity(intent);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
