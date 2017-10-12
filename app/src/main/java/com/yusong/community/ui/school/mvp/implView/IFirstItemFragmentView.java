package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.school.bean.StudentComment;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/21.
 */

public interface IFirstItemFragmentView extends BaseView {
    void getStuComment(List<StudentComment> data);
    void closeRefresh();
}
