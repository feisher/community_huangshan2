package com.yusong.club.ui.supermarket.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-04.
 * @describe: null
 */

public interface QuerySMViewPresenter extends BasePresenter {
    void querySuperMarket();

    void queryFenlei();

    void queryCommodity(int categoryId, int offset, int limit);
}
