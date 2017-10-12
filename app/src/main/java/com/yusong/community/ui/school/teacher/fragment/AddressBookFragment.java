package com.yusong.community.ui.school.teacher.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;

import butterknife.InjectView;

/**
 * 老师端通讯录
 */
public class AddressBookFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.im_back)
    ImageView imBack;
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

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_teacher_address_book, null);
    }

    @Override
    public void initData() {
        radioGroup.check(R.id.rbtn_one);
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new PersonageFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void initListener() {
        linearBack.setOnClickListener(this);
        linearBack.setVisibility(View.GONE);
        rbtnOne.setOnClickListener(this);
        rbtnTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.linear_back:
                getActivity().finish();
                break;
            case R.id.rbtn_one:
                fragmentTransaction.replace(R.id.frame_layout, new PersonageFragment());
                fragmentTransaction.commit();
                break;
            case R.id.rbtn_two:
                fragmentTransaction.replace(R.id.frame_layout, new GroupFragment());
                fragmentTransaction.commit();
                break;
        }
    }
}
