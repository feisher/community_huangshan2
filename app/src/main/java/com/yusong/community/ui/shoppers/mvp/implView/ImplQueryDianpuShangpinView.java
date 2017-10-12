package com.yusong.community.ui.shoppers.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.CommodityBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询店铺商品
 */

public interface ImplQueryDianpuShangpinView extends BaseView {
    void shangpinSucced(List<CommodityBean> data);
}
