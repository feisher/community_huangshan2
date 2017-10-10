package com.yusong.club.ui.shoppers.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.shoppers.bean.CommodityBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询抢购商品列表
 */
public interface ImplQueryQianggouListView extends BaseView {
    void refreshQiangGouList(List<CommodityBean> data);
}
