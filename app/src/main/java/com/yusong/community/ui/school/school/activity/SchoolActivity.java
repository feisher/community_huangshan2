package com.yusong.community.ui.school.school.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.home.fragment.Msg1Fragment;
import com.yusong.community.ui.school.school.fragment.AddressFragment;
import com.yusong.community.ui.school.school.fragment.FirstFragment;
import com.yusong.community.ui.school.school.fragment.MineFragment;

import butterknife.InjectView;

public class SchoolActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    Fragment fragment = null;
    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.rb_home)
    RadioButton rbHome;
    @InjectView(R.id.rb_msg)
    RadioButton rbMsg;
    @InjectView(R.id.rb_class)
    RadioButton rbClass;
    @InjectView(R.id.rb_me)
    RadioButton rbMe;
    @InjectView(R.id.rg_group)
    RadioGroup rgGroup;


    @Override
    protected void initListener() {
        rgGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_school_school;
    }

    @Override
    public void initView() {
        //默认第一个为选中
        rgGroup.check(R.id.rb_home);
        //默认跳入首页
        fragment = new FirstFragment();
        jumpFragment();
    }

    @Override
    public void initData() {

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home://主页
                fragment = new FirstFragment();
                break;
            case R.id.rb_msg://消息
//                fragment = new MessageFragment();
                fragment = new Msg1Fragment();
                break;
            case R.id.rb_class://校讯通
                fragment = new AddressFragment();
                break;
            case R.id.rb_me://我的
                fragment = new MineFragment();
                break;
        }
        //使用指定的fragme       jumpFragment();
        jumpFragment();
    }


    private void jumpFragment() {
        //使用指定的fragment切换当前页面
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
