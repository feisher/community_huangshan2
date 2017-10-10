package com.yusong.club.ui.community.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.community.mvp.entity.PostComment;

import java.util.List;

/**
 * Created by feisher on 2017/1/14.
 */
public  interface PostDetailActivityView extends BaseView {
    void setCommentsAdapter(List<PostComment> data);
    void commentSucceedCallback();
    void closeRefresh();
}
