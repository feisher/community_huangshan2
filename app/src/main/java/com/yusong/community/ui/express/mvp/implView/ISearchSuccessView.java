package com.yusong.community.ui.express.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.express.mvp.entity.ScanOrder;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface ISearchSuccessView extends BaseView {
    void errEmpty();
    void showInfo(ScanOrder order);
}
