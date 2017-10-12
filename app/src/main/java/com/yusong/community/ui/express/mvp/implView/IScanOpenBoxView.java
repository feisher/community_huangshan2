package com.yusong.community.ui.express.mvp.implView;

import com.yusong.community.api.HttpResult;
import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.express.mvp.entity.OpenBoxOrder;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface IScanOpenBoxView extends BaseView {


    void jumpActivity(HttpResult<OpenBoxOrder> result);
}
