package com.yusong.community.ui.school.teacher.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.school.teacher.fragment.GroupFragment;
import com.yusong.community.ui.school.teacher.fragment.PersonageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 班讯录
 */
public class AddressBookActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.rbtn_one)
    RadioButton rbtnOne;
    @InjectView(R.id.rbtn_two)
    RadioButton rbtnTwo;
    @InjectView(R.id.radio_group)
    RadioGroup radioGroup;
    @InjectView(R.id.frame_layout)
    FrameLayout frameLayout;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    private List<BaseFragment> mFragments;
    private Fragment CURRENT_FRAGMENT;
    private FragmentTransaction ft;
    private int roleFlag = 0;

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
    protected void initListener() {
        linearBack.setOnClickListener(this);
        rbtnOne.setOnClickListener(this);
        rbtnTwo.setOnClickListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher_address_book;
    }

    @Override
    public void initView() {
        if (getIntent() != null && getIntent().getIntExtra("roleFlag", 0) != 0) {
            roleFlag = getIntent().getIntExtra("roleFlag", 0);
        }
        mFragments = new ArrayList<>();
        radioGroup.setOnCheckedChangeListener(this);
        initFragments();
        showFragment(0);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
                break;

        }
    }

    public void showFragment(int index) {
        ft = getSupportFragmentManager().beginTransaction();
        //判断当前的fragment是否为空
        if (null != CURRENT_FRAGMENT) {
            ft.hide(CURRENT_FRAGMENT);
        }
        //用缓存获取当前的fragment
        Fragment fragment = getSupportFragmentManager()
                .findFragmentByTag(mFragments.get(index).getClass().getName());
        if (fragment == null) {
            fragment = mFragments.get(index);
        }
        //为每一个fragment做标记
        CURRENT_FRAGMENT = fragment;
        //设置当前的fragment
        if (!fragment.isAdded()) {
            ft.add(R.id.frame_layout, fragment, fragment.getClass().getName());
        } else {
            ft.show(fragment);
        }
        //提交
        ft.commit();
    }

    /**
     * 初始化页面控件（主要是Fragment）
     * /**
     * 获取需要关联的fragment的数据
     *
     * @return ：关联的fragment的数据...
     */
    private void initFragments() {
        mFragments = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putInt("roleFlag", roleFlag);
        BaseFragment personageFragment = new PersonageFragment();
        personageFragment.setArguments(bundle);
        mFragments.add(personageFragment);
        BaseFragment groupFragment = new GroupFragment();
        groupFragment.setArguments(bundle);
        mFragments.add(groupFragment);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbtn_one:
                showFragment(0);
                break;
            case R.id.rbtn_two:
                showFragment(1);
                break;
        }
    }
}
