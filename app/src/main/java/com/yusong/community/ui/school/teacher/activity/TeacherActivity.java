package com.yusong.community.ui.school.teacher.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.home.fragment.Msg1Fragment;
import com.yusong.community.ui.school.teacher.fragment.AddressBookFragment;
import com.yusong.community.ui.school.teacher.fragment.ClassActionFragment;
import com.yusong.community.ui.school.teacher.fragment.TeacherHomeFragment;
import com.yusong.community.ui.school.teacher.fragment.TeacherMineFragment;

import butterknife.InjectView;

/**
 * 老师端首页
 */
public class TeacherActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.rb_home)
    RadioButton rbHome;
    @InjectView(R.id.rb_msg)
    RadioButton rbMsg;
    @InjectView(R.id.rb_action)
    RadioButton rbAction;
    @InjectView(R.id.rb_class)
    RadioButton rbClass;
    @InjectView(R.id.rb_me)
    RadioButton rbMe;
    @InjectView(R.id.rg_group)
    RadioGroup rgGroup;
    Fragment fragment = null;
    private String SchoolName;
    private String SchoolId;

    public RadioGroup getRgGroup() {
        return rgGroup;
    }

    public void setRgGroup(RadioGroup rgGroup) {
        this.rgGroup = rgGroup;
    }

    @Override
    protected void initListener() {
        rgGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher;
    }

    @Override
    public void initView() {
        if (getIntent() != null) {
            SchoolName = getIntent().getStringExtra("SchoolName");
            SchoolId = getIntent().getStringExtra("SchoolId");
        }
        //默认第一个为选中
        rgGroup.check(R.id.rb_home);
        //默认跳入首页
        fragment = new TeacherHomeFragment();
        Bundle bundle = null;
        bundle = new Bundle();
        bundle.putString("SchoolName", SchoolName);
        bundle.putString("SchoolId", SchoolId);
        fragment.setArguments(bundle);
        jumpFragment();
    }

    private void jumpFragment() {
        //使用指定的fragment切换当前页面
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void adaptiveSystemVersions() {



    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Bundle bundle = null;
        bundle = new Bundle();
        bundle.putString("SchoolName", SchoolName);
        bundle.putString("SchoolId", SchoolId);
        bundle.putString("role","2");
        bundle.putInt("roleTag",1);;
        switch (checkedId) {
            case R.id.rb_home://主页
                fragment = new TeacherHomeFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.rb_msg://消息
                fragment = new Msg1Fragment();
                fragment.setArguments(bundle);
                break;
            case R.id.rb_action://活动
                fragment = new ClassActionFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.rb_class://校讯通
                fragment = new AddressBookFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.rb_me://我的
                fragment = new TeacherMineFragment();//学校端的碎片
                fragment.setArguments(bundle);
                break;
        }
        //使用指定的fragment切换当前页面
        jumpFragment();
    }
}
