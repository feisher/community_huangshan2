package com.yusong.club.ui.express.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.express.mvp.entity.OrderLogistics;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface ILogisticsInfoView extends BaseView {
    void errEmpty();

    void showInfo(OrderLogistics logistics);
}
