package com.yusong.club.ui.shoppers.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询店铺商品
 */

public interface ImplQueryDianpuShangpinPresenter extends BasePresenter {
    void queryDianPuShangpin(int categoryId, int shopId,String orderBy, int offset, int limit);
}
