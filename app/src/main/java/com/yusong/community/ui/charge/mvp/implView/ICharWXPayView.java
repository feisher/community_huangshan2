package com.yusong.community.ui.charge.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.pay.bean.WeiXinPayBean;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface ICharWXPayView extends BaseView {
    void weixinSucced(WeiXinPayBean data);
}
