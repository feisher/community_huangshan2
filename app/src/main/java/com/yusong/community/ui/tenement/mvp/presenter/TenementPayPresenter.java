package com.yusong.community.ui.tenement.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-13.
 * @describe: null
 */

public interface TenementPayPresenter extends BasePresenter {
    void zlTenementPay(String orderId);

    void wxTenementPay(String orderId);

    void zfbTenementPay(String orderId);
}
