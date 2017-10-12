package com.yusong.community.ui.supermarket.mvp.ImolView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.ui.supermarket.entity.SMCommodityBean;
import com.yusong.community.ui.supermarket.entity.SuperMarketDetailsBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-04.
 * @describe: null
 */

public interface QuerySMView extends BaseView {
    void querySMSucces(SuperMarketDetailsBean data);

    void querFenleiSucces(List<FenLeiBean> been);

    void queryCommoditySucces(List<SMCommodityBean> been);

    void queryCommodityError();
}
