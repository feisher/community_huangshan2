package com.yusong.community.ui.charge.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.charge.bean.NearbyBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/1/17.
 */

public interface IChargeQueryDetailsView extends BaseView {
    void toChareDetails(List<NearbyBean> data);
    void QueryDetailsMessage(String message);
}
