package com.yusong.community.ui.supermarket.mvp.ImolView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.pay.bean.WeiXinPayBean;
import com.yusong.community.pay.bean.ZhiFuBaoPayBean;

/**
 * @author Mr_Peng
 * @created at 2017-09-08.
 * @describe: null
 */

public interface SmPayView extends BaseView {
    void smBalancePaySucced(String message);

    void smBalancePayError();

    void smWeixinPaySucced(WeiXinPayBean bean);

    void smWeixinPayError();

    void smZFBPaySucced(ZhiFuBaoPayBean bean);

    void smZFBPayError();
}
