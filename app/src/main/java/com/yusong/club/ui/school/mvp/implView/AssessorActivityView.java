package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.ApplyRole;

import java.util.List;

/**
 * create by feisher on 2017/3/9
 * Email：458079442@qq.com
 */
public interface AssessorActivityView extends BaseView {
    void closeRefreshing();
    void queryRoleApplyListCallback(List<ApplyRole> data);
}