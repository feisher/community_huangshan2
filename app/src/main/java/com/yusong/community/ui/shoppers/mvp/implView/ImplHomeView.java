package com.yusong.community.ui.shoppers.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;

import java.util.List;

/**
 * @author Mr_Peng
 *         created at 2017/3/15 14:45.
 */

public interface ImplHomeView extends BaseView {
    void close();

    void HomeFlSucced(List<FenLeiBean> datas);

    void HomeListSucced(List<CommodityBean> datas);
}
