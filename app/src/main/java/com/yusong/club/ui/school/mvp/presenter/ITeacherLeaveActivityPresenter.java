package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/27.
 */

public interface ITeacherLeaveActivityPresenter extends BasePresenter {
    void queryLeaveAplly(String token, Integer status, int offset, int limit);
    void judgeLeave(String token,int applyId,String auditMemo,int status);
}
