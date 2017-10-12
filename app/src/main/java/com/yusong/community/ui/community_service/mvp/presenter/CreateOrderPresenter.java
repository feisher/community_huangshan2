package com.yusong.community.ui.community_service.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: null
 */

public interface CreateOrderPresenter extends BasePresenter {

    void createServiceOrder(int deliverType,int itemId, double price, String province, String city,
                            String district, String street, String receiverMobile, String reciever, String serviceTime,
                            String leaveMessage);
}
