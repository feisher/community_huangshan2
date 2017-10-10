package com.yusong.club.ui.supermarket.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-05.
 * @describe: null
 */

public interface SMCommodityPresenter extends BasePresenter {
    void querySMCommodityDetails(int itemId);

    void querySMComment(int itemId, int offset, int limit);

    void querySMSpecification(int itemId);
}
