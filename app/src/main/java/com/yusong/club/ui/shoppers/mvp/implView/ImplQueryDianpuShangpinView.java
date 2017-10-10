package com.yusong.club.ui.shoppers.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.shoppers.bean.CommodityBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询店铺商品
 */

public interface ImplQueryDianpuShangpinView extends BaseView {
    void shangpinSucced(List<CommodityBean> data);
}
