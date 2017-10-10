package com.yusong.club.ui.charge.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface ICharegeYuYueOrderPresenter extends BasePresenter {
    void queryBespeak(String chargerId, int duration, float price , int volume);
}
