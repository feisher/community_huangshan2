package com.yusong.club.ui.shoppers.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.shoppers.bean.CommodityBean;
import com.yusong.club.ui.shoppers.bean.FenLeiBean;

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
