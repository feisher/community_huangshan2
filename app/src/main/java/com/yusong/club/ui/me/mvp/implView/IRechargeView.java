package com.yusong.club.ui.me.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.pay.bean.WeiXinPayBean;
import com.yusong.club.pay.bean.ZhiFuBaoPayBean;
import com.yusong.club.ui.me.mvp.entity.MoneyList;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/7 16:32 </li>
 * </ul>
 */
public interface IRechargeView extends BaseView {

    void zfbPay(ZhiFuBaoPayBean data, String subject, String body);

    void wxPay(WeiXinPayBean data);

    void setAdapter(List<MoneyList> data);
}