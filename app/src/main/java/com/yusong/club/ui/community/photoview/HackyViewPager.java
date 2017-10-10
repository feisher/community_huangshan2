package com.yusong.club.ui.community.photoview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * create by feisher on 2017/3/24 14:30
 * Email：458079442@qq.com
 */
public class HackyViewPager extends ViewPager {


	private static final String TAG = "HackyViewPager";

	public HackyViewPager(Context context) {
		super(context);
	}
	
	public HackyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException e) {
			Log.e(TAG,"hacky viewpager error1");
			return false;
		}catch(ArrayIndexOutOfBoundsException e ){
			Log.e(TAG,"hacky viewpager error2");
			return false;
		}
	}

}
