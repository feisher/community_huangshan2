package com.yusong.community.ui.tenement.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-04.
 * @describe: null
 */

public interface TenementPresenter extends BasePresenter {
    void queryTenementPay(int communityId,int proprietorId,int isPay);
}
