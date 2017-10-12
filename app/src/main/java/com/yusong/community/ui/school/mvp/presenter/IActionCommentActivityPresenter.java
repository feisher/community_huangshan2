package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/16.
 */

public interface IActionCommentActivityPresenter extends BasePresenter {
    void searchCommentList(String token,int activityId,int offset,int limit);
    void createComment(String token, int activityId,String content);
}
