package com.yusong.community.ui.charge.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.pay.bean.ZhiFuBaoPayBean;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface ICharZFBPayView extends BaseView {
    void zhifubaoSucced(ZhiFuBaoPayBean data);
}
