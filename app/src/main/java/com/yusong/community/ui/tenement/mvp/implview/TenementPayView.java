package com.yusong.community.ui.tenement.mvp.implview;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.pay.bean.WeiXinPayBean;
import com.yusong.community.pay.bean.ZhiFuBaoPayBean;

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
