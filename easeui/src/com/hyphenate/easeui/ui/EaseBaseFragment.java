package com.hyphenate.easeui.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.hyphenate.easeui.R;
import com.hyphenate.easeui.widget.EaseTitleBar;

public abstract class EaseBaseFragment extends Fragment {
    protected EaseTitleBar titleBar;
    protected InputMethodManager inputMethodManager;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        //noinspection ConstantConditions
        titleBar = (EaseTitleBar) getView().findViewById(R.id.title_bar);
        adaptiveSystemVersions();
        initView();
        setUpView();

    }

    //用于适配titlebar
    protected void adaptiveSystemVersions() {
        if (titleBar == null){
            return;
        }
        ImageView iv = (ImageView) titleBar.findViewById(getResources().getIdentifier("iv_adaptive_down_api18",
                "id", getActivity().getPackageName()));
        if (iv == null){
            return;
        }
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            iv.setVisibility(View.VISIBLE);
        } else {
            iv.setVisibility(View.GONE);
        }
    }


    public void showTitleBar() {
        if (titleBar != null) {
            titleBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideTitleBar() {
        if (titleBar != null) {
            titleBar.setVisibility(View.GONE);
        }
    }

    protected void hideSoftKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected abstract void initView();

    protected abstract void setUpView();


}
