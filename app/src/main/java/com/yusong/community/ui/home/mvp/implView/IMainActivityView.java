package com.yusong.community.ui.home.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.me.mvp.entity.UserInfo;

/**
 * Created by feisher on 2017/1/21.
 */
public interface IMainActivityView extends BaseView{
    void   getUserInfo(UserInfo data);
}
