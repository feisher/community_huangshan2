package com.yusong.community.ui.community_service.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: null
 */

public interface ServiceDetailPresenter extends BasePresenter {
    void queryServiceDetails(int itemId);

    void queryServiceComments(int itemId, int offset, int limit);
}
