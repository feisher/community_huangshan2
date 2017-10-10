package com.yusong.club.ui.visitor.mvp.prsenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: null
 */

public interface CreateThroughPrsenter extends BasePresenter {

    void createThroughCard(String visitorName, int sex, String visitorTime, int isDrive, String plateNumber);
}
