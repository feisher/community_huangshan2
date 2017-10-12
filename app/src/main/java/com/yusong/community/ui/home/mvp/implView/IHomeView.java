package com.yusong.community.ui.home.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.mvp.entity.Role;

import java.util.List;

/**
 * Created by quaner on 17/1/3.
 */

public interface IHomeView extends BaseView {


    void showBanner(List<String> data);

    void closeLoading();

    void logOut();

    void roleListCallback(Role data);

    void selectRoleCallback(int type);
}
