package com.yusong.community.ui.school.teacher.activity;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.teacher.adapter.PhotoAdapter;
import com.yusong.community.ui.school.teacher.fragment.AllPhotoFragment;
import com.yusong.community.ui.school.teacher.fragment.AllVideoFragment;
import com.yusong.community.ui.school.teacher.fragment.LatestPhotoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * 班级相册
 */
public class ClassPhotoActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    private PhotoAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private List<String> menuList;
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    private int roleTag = 0;
    private int mediaType = 0;
    private int choosePos = -1;


    @Override
    protected void initListener() {
        linearBack.setOnClickListener(this);
        imMsg.setOnClickListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher_class_photo;
    }

    @Override
    public void initView() {
        tvTitle.setText("班级相册");
        imMsg.setImageResource(R.mipmap.add);
        if (getIntent() != null && getIntent().getIntExtra("roleTag", 0) != 0) {
            roleTag = getIntent().getIntExtra("roleTag", 0);
        }
        if (getIntent() != null && getIntent().getIntExtra("mediaType", 0) != 0) {
            mediaType = getIntent().getIntExtra("mediaType", 0);
        }
        switch (roleTag) {
            case 1:
                imMsg.setVisibility(View.VISIBLE);
                break;
            case 2:
                imMsg.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void initData() {
        //菜单集合
        fragmentList = new ArrayList<>();
        fragmentList.add(new LatestPhotoFragment());
        fragmentList.add(new AllPhotoFragment());
        fragmentList.add(new AllVideoFragment());

        //将名称加载tab名字列表
        titleList = new ArrayList<>();
        titleList.add("最新照片");
        titleList.add("相册");
        titleList.add("视频册");

        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(2)));

        adapter = new PhotoAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    imMsg.setVisibility(View.GONE);
                } else if (position == 1) {
                    imMsg.setVisibility(View.VISIBLE);
                    tvTitle.setText("班级相册");
                    choosePos = 1;
                } else if (position == 2) {
                    imMsg.setVisibility(View.VISIBLE);
                    tvTitle.setText("班级视频册");
                    choosePos = 2;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        switch (mediaType) {
            case 1:
                tvTitle.setText("班级相册");
                choosePos = 1;
                break;
            case 2:
                tvTitle.setText("班级视频册");
                choosePos = 2;
                break;
        }
        if (mediaType == 1) {//相册
            imMsg.setVisibility(View.VISIBLE);
            viewPager.setCurrentItem(1);
        } else if (mediaType == 2) {//视频册
            imMsg.setVisibility(View.VISIBLE);
            viewPager.setCurrentItem(2);
        } else {
            imMsg.setVisibility(View.GONE);
            viewPager.setCurrentItem(0);
        }
    }

    @Override
    protected void adaptiveSystemVersions() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.im_msg:
                if (choosePos == 1) {
                    intent = new Intent(ClassPhotoActivity.this, CreatePhotoActivity.class);
                    startActivity(intent);
                } else if (choosePos == 2) {
                    intent = new Intent(ClassPhotoActivity.this, CreateVideoActivity.class);
                    startActivity(intent);
                }
                break;

        }
    }


}
