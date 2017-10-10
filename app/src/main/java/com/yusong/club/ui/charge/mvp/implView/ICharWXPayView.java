package com.yusong.club.ui.charge.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.pay.bean.WeiXinPayBean;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface ICharWXPayView extends BaseView {
    void weixinSucced(WeiXinPayBean data);
}
