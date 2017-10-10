package com.yusong.club.ui.charge.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.charge.bean.NearbyBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/1/17.
 */

public interface IChargeQueryDetailsView extends BaseView {
    void toChareDetails(List<NearbyBean> data);
    void QueryDetailsMessage(String message);
}
