package com.yusong.club.ui.shoppers.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询店铺商品分类
 */

public interface ImplQueryDianpufenleipresenter extends BasePresenter {
    void queryDianpufenlei(int shopId);
    void queryDianpuDetails(int shopId);
}
