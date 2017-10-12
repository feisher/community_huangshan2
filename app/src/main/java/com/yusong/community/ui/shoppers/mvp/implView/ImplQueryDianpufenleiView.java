package com.yusong.community.ui.shoppers.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.ui.shoppers.bean.ShopDetailsBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询店铺商品分类
 */

public interface ImplQueryDianpufenleiView extends BaseView {
    void dianpuFenleiSucced(List<FenLeiBean> data);

    void dianpuDetailsSucced(ShopDetailsBean bean);
}
