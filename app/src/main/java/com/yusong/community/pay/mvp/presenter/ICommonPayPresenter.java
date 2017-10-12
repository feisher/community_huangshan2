package com.yusong.community.pay.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/3 15:21 </li>
 * </ul>
 */
public interface ICommonPayPresenter extends BasePresenter {

    void zlPay(String token, String id, int paytype);
    void zfbPay(String token, String id, int paytype);
    void wxPay(String token, String id, int paytype);

}
