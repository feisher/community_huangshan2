package com.yusong.club.ui.charge.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface IChargeHomeView extends BaseView {
    void refreshView(List<String> data);

    void closeRefresh();
}
