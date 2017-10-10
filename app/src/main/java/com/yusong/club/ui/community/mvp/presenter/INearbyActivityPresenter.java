package com.yusong.club.ui.community.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by feisher on 2017/1/10.
 */

public interface INearbyActivityPresenter extends BasePresenter {
    void queryNearbyCommuity(int areaId, int baiduAreaId, double lat,double lng);
    void setCurrentCommunity(int communityId);

}
