package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.mvp.entity.SchoolManager;

/**
 * create by feisher on 2017/3/16
 * Email：458079442@qq.com
 */
public interface IMineFragmentView extends BaseView {
    void querySchoolManagerInfoCallback(SchoolManager mSchoolManager);
}