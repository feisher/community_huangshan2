package com.yusong.club.ui.charge.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.charge.bean.SancContentBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface IChargeScanView extends BaseView {
    void jumpActivity(List<SancContentBean> data);
    void queryNewCharge();
}
