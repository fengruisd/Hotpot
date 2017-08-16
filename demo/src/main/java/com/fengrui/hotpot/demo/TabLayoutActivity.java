package com.fengrui.hotpot.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.fengrui.hotpot.R;
import com.fengrui.hotpot.widget.PPDTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengrui on 2017/8/16.
 */

public class TabLayoutActivity extends AppCompatActivity {


    private MyAdapter myAdapter;
    private ViewPager viewPager;
    private PPDTabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        tabLayout = (PPDTabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        initView();
    }

    private void initView() {
        List<View> lists = new ArrayList<>(3);
        for(int i = 0; i < 3; i++) {
            lists.add(createPageView(i));
        }

        myAdapter = new MyAdapter(lists);
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private TextView createPageView(int position) {
        TextView textView = new TextView(this);
        textView.setText(String.valueOf(position));
        textView.setGravity(Gravity.CENTER);
        return textView;
    }


    class MyAdapter extends PagerAdapter {

        List<View> viewLists;

        public MyAdapter(List<View> lists)
        {
            viewLists = lists;
        }

        @Override
        public int getCount() {
            return viewLists.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View view, int position, Object object) {
            ((ViewPager) view).removeView(viewLists.get(position));
        }

        @Override
        public Object instantiateItem(View view, int position) {
            ((ViewPager) view).addView(viewLists.get(position), 0);
            return viewLists.get(position);
        }

    }
}
