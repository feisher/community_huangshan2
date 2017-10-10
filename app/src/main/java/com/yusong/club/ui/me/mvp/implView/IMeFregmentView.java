package com.yusong.club.ui.me.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.me.mvp.entity.UserInfo;

/**
 * Created by feisher on 2017/1/21.
 */
public interface IMeFregmentView extends BaseView{
    void   getUserInfoCallback(UserInfo data);
    void   signInCallback();
    void closeLoading();


}
