package com.yusong.club.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by quaner on 16/12/24.
 */
public abstract class BaseFragment extends Fragment {

    public Context mContext;
    public Activity mActivity;
    public View mFragmentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = getContext();
        mActivity = getActivity();
        if (null == mFragmentView) {
            mFragmentView = initView();
            ButterKnife.inject(this, mFragmentView);
            initData();
            initListener();
        } else {
            ButterKnife.inject(this, mFragmentView);
        }
        return mFragmentView;
    }

    public abstract View initView();

    public abstract void initData();

    public void initListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        if (null != mFragmentView) {
            ((ViewGroup) mFragmentView.getParent()).removeView(mFragmentView);
        }
    }
}
