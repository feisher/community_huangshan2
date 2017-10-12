package com.yusong.community.ui.express.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface INearbyCabinetPresenter extends BasePresenter {
    void queryNearestBox(float longitude, float latitude);
}
