package com.yusong.club.ui.charge.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.charge.bean.NearbyBean;

/**
 * Created by ruanjian4 on 2017/2/20.
 */

public interface IChargeQueryChargeDetailsView extends BaseView{
    void refreshCharge(NearbyBean nearbyBean);
}
