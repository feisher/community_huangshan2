package com.yusong.community.pay.mvp.view;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.pay.bean.WeiXinPayBean;
import com.yusong.community.pay.bean.ZhiFuBaoPayBean;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/3 15:22 </li>
 * </ul>
 */
public interface ICommonPayView extends BaseView {


    void zfbPay(ZhiFuBaoPayBean data, String subject, String body);

    void wxPay(WeiXinPayBean data);

    void jumpActivity();

}
