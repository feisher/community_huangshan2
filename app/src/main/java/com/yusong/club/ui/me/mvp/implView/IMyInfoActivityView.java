package com.yusong.club.ui.me.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.me.mvp.entity.UserInfo;
import com.yusong.club.ui.school.mvp.entity.Role;

/**
 * Created by feisher on 2017/1/21.
 */
public interface IMyInfoActivityView extends BaseView{
    void   updateUserInfoCallback(UserInfo userInfo);
    void updatePasswordCallback();
    void roleListCallback(Role data);
}
