package com.yusong.club.ui.shoppers.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-18.
 * @describe: null
 */

public interface ImplFindCommodityPresenter extends BasePresenter {

    void findCommodity(String content);

    void findSMCommodity(String content);

    void findService(String content);
}
