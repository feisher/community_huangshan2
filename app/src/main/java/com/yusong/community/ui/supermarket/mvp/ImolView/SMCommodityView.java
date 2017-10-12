package com.yusong.community.ui.supermarket.mvp.ImolView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.PinLunBean;
import com.yusong.community.ui.shoppers.bean.SpecificationBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-05.
 * @describe: null
 */

public interface SMCommodityView extends BaseView {
    void queryCommoditySucceed(CommodityBean bean);

    void queryCommodityCommentSucced(List<PinLunBean> data);

    void queryCommodityCommentError();

    void querySMSpecificationSuccess(List<SpecificationBean> beanList);

}
