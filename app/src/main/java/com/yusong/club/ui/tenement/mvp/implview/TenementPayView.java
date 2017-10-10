package com.yusong.club.ui.tenement.mvp.implview;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.pay.bean.WeiXinPayBean;
import com.yusong.club.pay.bean.ZhiFuBaoPayBean;

/**
 * @author Mr_Peng
 * @created at 2017-09-13.
 * @describe: null
 */

public interface TenementPayView extends BaseView {
    void zhilianPaySucced();

    void ZFBPaySucced(ZhiFuBaoPayBean bean);

    void WXPaySucced(WeiXinPayBean bean);
}
