package com.yusong.club.ui.shoppers.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * @author Mr_Peng
 *         created at 2017/3/6 15:10.
 */

public interface ImplQuerytuijianListPresenter extends BasePresenter {
    void queryTuijianList(int categoryId, int offset, int limit);
}
