package com.yusong.community.ui.shoppers.used.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/18.
 *         描述: null
 */

public interface ImplUsedPresenter extends BasePresenter {
    void queryUsedFenlei();

    void queryUsedList(int categoryId, double lng, double lat, int offset, int limit);
}
