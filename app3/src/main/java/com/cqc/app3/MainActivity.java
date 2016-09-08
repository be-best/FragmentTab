package com.cqc.app3;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton tab1;
    private RadioButton tab2;
    private RadioButton tab3;
    private RadioButton tab4;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;
    private ViewPager viewPager;
//    private NoScrollViewPager viewPager;
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        findViews();
        initData();
        initViews();
        //默认点击第一个
        viewPager.setCurrentItem(0);
        tab1.setChecked(true);
    }

    private void initData() {
        list.clear();
        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();
        frag4 = new Frag4();

        list.add(frag1);
        list.add(frag2);
        list.add(frag3);
        list.add(frag4);
    }

    private void initViews() {
        //RadioGroup点击的时让viewpager跟着切换
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

                switch (checkId) {
                    case R.id.tab1:
                        viewPager.setCurrentItem(0);//选中第一页
                        break;
                    case R.id.tab2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.tab3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.tab4:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);

        //viewpager滑动的时候让RadioGroup跟着切换
//        viewPager.setOnPageChangeListener(OnPageChangeListener mOnPageChangeListener);//过时方法，页可以用
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //正在滑动时
                Log.d("tag", "position=" + position + "---positionOffset" + positionOffset + "---positionOffsetPixels" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                //滑动完成
                switch (position) {
                    case 0:
                        tab1.setChecked(true);
                        break;
                    case 1:
                        tab2.setChecked(true);
                        break;
                    case 2:
                        tab3.setChecked(true);
                        break;
                    case 3:
                        tab4.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //滑动状态改变，默认值有0/1,2，也可以在这里设置tab为true（先要判断state == 2）
                //0:没有滑动，静止状态
                //1:正在滑动
                //2:滑动完成
            }
        });
    }


    private void findViews() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        tab1 = (RadioButton) findViewById(R.id.tab1);
        tab2 = (RadioButton) findViewById(R.id.tab2);
        tab3 = (RadioButton) findViewById(R.id.tab3);
        tab4 = (RadioButton) findViewById(R.id.tab4);
    }
}
