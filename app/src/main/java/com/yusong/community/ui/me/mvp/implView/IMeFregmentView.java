package com.yusong.community.ui.me.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.me.mvp.entity.UserInfo;

/**
 * Created by feisher on 2017/1/21.
 */
public interface IMeFregmentView extends BaseView{
    void   getUserInfoCallback(UserInfo data);
    void   signInCallback();
    void closeLoading();


}
