package com.yusong.community.ui.express.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface IOrderPresenter extends BasePresenter {
    void requestGetOrder(String type, int offset, int limit);

    void requestSenderOrder(String type, int offset, int limit);

    void requestSaveOrder(String type, int offset, int limit);
}
