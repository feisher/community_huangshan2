package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/4/1.
 */

public interface IShowLeaveListActivityPresenter extends BasePresenter {
    void  queryLeaveApllyByDate(String token,Integer status,String leaveBeginTime,String leaveEndTime, int offset, int limit);

}
