package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/17.
 */

public interface IEditEvaluateActivityPresenter extends BasePresenter {
    void  StuCommentCreate(String token,int studentId, int period,int star1,int star2,int star3,int star4,String content);

}
