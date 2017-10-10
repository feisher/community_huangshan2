package com.yusong.club.ui.community.photoview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * 封装大图浏览功能
 * create by feisher on 2017/3/24 14:22
 * Email：458079442@qq.com
 */
public class PhotoViewActivity extends BaseActivity {

    private static final String STATE_POSITION = "state_position";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";
    @InjectView(R.id.pager)
    HackyViewPager pager;
    @InjectView(R.id.indicator)
    TextView indicator;
    @InjectView(R.id.activity_photo_view)
    FrameLayout activityPhotoView;
    public int pagerPosition;
    public ArrayList<String> urls;
    @InjectView(R.id.iv_back)
    ImageView ivBack;

    @Override
    protected int layoutId() {
        return R.layout.activity_photo_view;
    }

    //    intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,urls);
//    intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
    @Override
    public void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.fade_in, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, R.anim.fade_out);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        urls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(
                getSupportFragmentManager(), urls);
        pager.setAdapter(mAdapter);
        pager.setCurrentItem(pagerPosition);
        CharSequence text = getString(R.string.viewpager_indicator,
                pagerPosition+1, pager.getAdapter().getCount());
        indicator.setText(text);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator,
                        arg0 + 1, pager.getAdapter().getCount());
                indicator.setText(text);
            }

        });
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }

        pager.setCurrentItem(pagerPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, pager.getCurrentItem());
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public ArrayList<String>  fileList;

        public ImagePagerAdapter(FragmentManager fm, ArrayList<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position);
            return ImageDetailFragment.newInstance(url);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
