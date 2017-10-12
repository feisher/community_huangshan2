package com.yusong.community.ui.me.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.me.mvp.entity.UserInfo;
import com.yusong.community.ui.school.mvp.entity.Role;

/**
 * Created by feisher on 2017/1/21.
 */
public interface IMyInfoActivityView extends BaseView{
    void   updateUserInfoCallback(UserInfo userInfo);
    void updatePasswordCallback();
    void roleListCallback(Role data);
}
