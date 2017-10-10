package com.yusong.club.ui.express.mvp.implView;

import com.yusong.club.api.HttpResult;
import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.express.mvp.entity.OpenBoxOrder;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface IScanOpenBoxView extends BaseView {


    void jumpActivity(HttpResult<OpenBoxOrder> result);
}
