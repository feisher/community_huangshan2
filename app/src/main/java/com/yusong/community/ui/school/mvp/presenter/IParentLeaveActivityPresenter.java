package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/4/1.
 */

public interface IParentLeaveActivityPresenter extends BasePresenter {
    void  queryLeaveApllyByDate(String token,Integer status,String leaveBeginTime,String leaveEndTime, int offset, int limit);

}
