package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.school.bean.Comment;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/16.
 */

public interface IActionCommentActivityView extends BaseView{
    void getCommentList(List<Comment> data);
    void closeRefreshing();
    void createComment(String data);
}
