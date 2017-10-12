package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.mvp.entity.ActionDetail;
import com.yusong.community.ui.school.mvp.entity.DetailComment;
import com.yusong.community.ui.school.mvp.entity.JoinedResult;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/25.
 */

public interface IActionDetailActivityView extends BaseView {
    void getActionDetail(ActionDetail data);
   void getDetailCommentList(List<DetailComment>detailComments);
    void partAction(JoinedResult data);
}
