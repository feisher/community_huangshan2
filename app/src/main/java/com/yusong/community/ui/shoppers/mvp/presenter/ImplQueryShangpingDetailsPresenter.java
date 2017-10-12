package com.yusong.community.ui.shoppers.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询商品详情
 */

public interface ImplQueryShangpingDetailsPresenter extends BasePresenter {
    void queryShangpinDetails(int itemId);

    void querySpecification(int itemId);
}
