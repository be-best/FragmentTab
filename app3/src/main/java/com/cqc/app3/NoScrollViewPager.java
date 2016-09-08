package com.cqc.app3;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ${cqc} on 2016/9/8.
 * 禁止viewpager的滑动
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //是否拦截事件向内部传递，true:拦截; false：不拦截.
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    //viewpager本身是否处理事件，true:处理; false：不处理.
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
