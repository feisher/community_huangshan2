package com.yusong.community.ui.express.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.express.mvp.entity.Order;

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
