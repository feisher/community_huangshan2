package com.yusong.community.ui.shoppers.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.SpecificationBean;

import java.util.List;


/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询商品详情
 */

public interface ImplQueryShangpingDetailsView extends BaseView {
    void refreshCommundityDetails(CommodityBean data);

    void querySpecificationSuccess(List<SpecificationBean> beanList);
}
