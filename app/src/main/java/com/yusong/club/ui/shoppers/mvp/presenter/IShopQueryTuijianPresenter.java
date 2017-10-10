package com.yusong.club.ui.shoppers.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询推荐商品分类
 */


public interface IShopQueryTuijianPresenter extends BasePresenter {
    void queryTuijianLei(int type);
}
