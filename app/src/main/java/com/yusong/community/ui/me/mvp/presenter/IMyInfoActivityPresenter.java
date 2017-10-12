package com.yusong.community.ui.me.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by feisher on 2017/1/21.
 */

public interface IMyInfoActivityPresenter extends BasePresenter {
    void upDateUserInfo(RequestBody nickname,RequestBody  realName, RequestBody  gender, RequestBody mail, MultipartBody.Part  file);
    void updatePassword(String oldPassword,String newPassword);
    void queryRoleList(String token);
}
