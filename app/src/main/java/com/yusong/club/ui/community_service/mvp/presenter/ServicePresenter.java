package com.yusong.club.ui.community_service.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: null
 */

public interface ServicePresenter extends BasePresenter {
    void queryServiceDetail();

    void queryServiceCategory();

    void queryServiceList(int category, int offset, int limit);
}
