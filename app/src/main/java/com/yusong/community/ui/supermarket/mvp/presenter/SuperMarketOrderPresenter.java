package com.yusong.community.ui.supermarket.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-06.
 * @describe: null
 */

public interface SuperMarketOrderPresenter extends BasePresenter {
    void createSMOrder(Integer deliverType, int itemId, int amount, double price, String province, String city,
                       String district, String street, String receiverMobile, String reciever, String distributionTime, String leaveMessage,String specitication);


    void querySMOrder(String type, int offset, int limit);
}
