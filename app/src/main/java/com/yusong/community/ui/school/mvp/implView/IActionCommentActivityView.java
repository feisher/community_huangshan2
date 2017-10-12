package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.school.bean.Comment;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/16.
 */

public interface IActionCommentActivityView extends BaseView{
    void getCommentList(List<Comment> data);
    void closeRefreshing();
    void createComment(String data);
}
