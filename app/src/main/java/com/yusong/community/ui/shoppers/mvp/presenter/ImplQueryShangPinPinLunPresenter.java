package com.yusong.community.ui.shoppers.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询商品评论
 */

public interface ImplQueryShangPinPinLunPresenter extends BasePresenter {
    void queryShangPinPinLun(int itemId, int offset, int limit);
}
