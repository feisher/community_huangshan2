package com.yusong.club.ui.charge.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.charge.bean.MyOrderBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface ICharMyOrderView extends BaseView {
    void refreshView(List<MyOrderBean> data);
}