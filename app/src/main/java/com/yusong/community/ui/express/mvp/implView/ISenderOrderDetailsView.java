package com.yusong.community.ui.express.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.express.mvp.entity.SaveDetails;
import com.yusong.community.ui.express.mvp.entity.SenderDetails;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface ISenderOrderDetailsView extends BaseView {
    void updateUI(SenderDetails data);

    void setIcon(String code);

    void updateUI(SaveDetails data);
}
