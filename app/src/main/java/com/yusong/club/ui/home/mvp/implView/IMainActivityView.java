package com.yusong.club.ui.home.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.me.mvp.entity.UserInfo;

/**
 * Created by feisher on 2017/1/21.
 */
public interface IMainActivityView extends BaseView{
    void   getUserInfo(UserInfo data);
}
