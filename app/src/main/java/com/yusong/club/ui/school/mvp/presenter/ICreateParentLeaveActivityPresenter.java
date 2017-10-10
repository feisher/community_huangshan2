package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/30.
 */

public interface ICreateParentLeaveActivityPresenter extends BasePresenter{
    void CreateLeaveApply(String token,String studentName, String reason,String beginTime,String endTime);

}
