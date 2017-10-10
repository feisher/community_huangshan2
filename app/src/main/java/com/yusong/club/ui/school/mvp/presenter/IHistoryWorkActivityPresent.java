package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/15.
 */

public interface IHistoryWorkActivityPresent extends BasePresenter {
    void searchHistoryHomework(String token,String day);
}
