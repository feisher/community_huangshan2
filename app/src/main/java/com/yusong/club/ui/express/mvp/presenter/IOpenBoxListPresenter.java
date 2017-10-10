package com.yusong.club.ui.express.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;
import com.yusong.club.ui.express.mvp.entity.Order;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface IOpenBoxListPresenter extends BasePresenter {
    void isEmpty(List<Order> mDeliverList, List<Order> mSendOrderList, List<Order> mStoreOrderList);
    void openBox(List<Order> mDeliverList, List<Order> mSendOrderList, List<Order> mStoreOrderList, Order order);
}
