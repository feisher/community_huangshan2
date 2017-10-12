package com.yusong.community.ui.community_service.mvp.ImplView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.PinLunBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: null
 */

public interface ServiceDetailView extends BaseView {
    void queryServiceDetailSucces(CommodityBean bean);

    void queryServiceCommentSucces(List<PinLunBean> datas);

    void queryServiceCommentError();
}
