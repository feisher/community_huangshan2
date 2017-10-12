package com.yusong.community.ui.shoppers.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 *         created at 2017/3/15 16:38.
 *         家装
 */

public interface ImplJiaZhuanPersenter extends BasePresenter {
    void queryHomeFenlei(int categoryType);//查询分类

    void queryHomeList(int categoryId,int offset,int limit);//查询列表
}
