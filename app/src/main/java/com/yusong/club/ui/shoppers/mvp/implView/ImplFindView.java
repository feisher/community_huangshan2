package com.yusong.club.ui.shoppers.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.shoppers.bean.CommodityBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-18.
 * @describe: null
 */

public interface ImplFindView extends BaseView {
    //商城
    void findCommoditySucced(List<CommodityBean> data);

    void findCommodityError();

    //超市
    void findSMCommoditySucced(List<CommodityBean> data);

    void findSMCommodityError();

    //服务
    void findServiceSucced(List<CommodityBean> data);

    void findServiceError();
}
