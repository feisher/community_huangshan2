package com.yusong.community.ui.charge.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.charge.bean.SancContentBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface IChargeScanView extends BaseView {
    void jumpActivity(List<SancContentBean> data);
    void queryNewCharge();
}
