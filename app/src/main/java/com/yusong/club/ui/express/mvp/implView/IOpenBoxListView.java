package com.yusong.club.ui.express.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.express.mvp.entity.Order;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public interface IOpenBoxListView extends BaseView {

    void setOrderAdapter(List<Order> all);

    void OrderEmpty();

    void close();
}
