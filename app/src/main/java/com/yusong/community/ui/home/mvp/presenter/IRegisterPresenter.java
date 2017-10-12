package com.yusong.community.ui.home.mvp.presenter;

import android.widget.EditText;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by quaner on 17/1/3.
 */

public interface IRegisterPresenter extends BasePresenter {

    void getCode(EditText phone);

    void startCountdown(int time);

    void isStartCountdown();

    void register(EditText phone, EditText code, EditText pswd, EditText okpwd) throws Exception;

    void revisePwd(EditText phone, EditText code, EditText pswd, EditText okpwd);
}
