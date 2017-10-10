package com.yusong.club.ui.shoppers.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询抢购商品列表
 */

public interface ImplQueryQianggouListPresenter extends BasePresenter {
    void queryQianggouList(int grabCategoryId,int itemCategoryId, int offset, int limit);
}
