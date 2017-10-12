package com.yusong.community.ui.express.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface ISendMailPresenter extends BasePresenter {

    boolean isEmpty(String senderAddress, String address, String thing, String amount, String tvCorporation, String tvBox);

    void commitOrder(int id, String thing, String senderName, String senderPhone, String senderProvince, String senderCity,
                     String senderDistrict, String senderAddress, String name, String phone, String receiverProvince,
                     String receiverCity, String receiverDistrict, String address, int thingAmount,int charge,int boxType);

    void queryConfig();

    void queryRates(int companyId, int senderProvince, int senderCity, int receiverProvince, int receiverCity, int boxType);

    void queryContact(int type);

}
