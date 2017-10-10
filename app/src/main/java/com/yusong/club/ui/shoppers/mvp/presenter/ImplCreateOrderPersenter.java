package com.yusong.club.ui.shoppers.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 下单
 */

public interface ImplCreateOrderPersenter extends BasePresenter {
    void createOrder(Integer deliverType,Integer grabItemId, int itemId, int amount, double price, String province, String city,
                     String district, String street, String receiverMobile, String reciever, String distributionTime,
                     String leaveMessage,String specitication);
}
