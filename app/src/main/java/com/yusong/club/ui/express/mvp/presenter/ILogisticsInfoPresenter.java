package com.yusong.club.ui.express.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface ILogisticsInfoPresenter extends BasePresenter {
    void queryDetailed(long id, String code);
}