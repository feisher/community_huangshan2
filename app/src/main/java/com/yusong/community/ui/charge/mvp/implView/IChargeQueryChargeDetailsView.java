package com.yusong.community.ui.charge.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.charge.bean.NearbyBean;

/**
 * Created by ruanjian4 on 2017/2/20.
 */

public interface IChargeQueryChargeDetailsView extends BaseView{
    void refreshCharge(NearbyBean nearbyBean);
}
