package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/25.
 */

public interface IActionDetailActivityPresenter extends BasePresenter {
    void getActionDeatail(String token, int activityId);

    void getDetailComment(String token, int activityId, int offset, int limit);

    void joinActy(String token, int activityId);
}
