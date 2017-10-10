package com.yusong.club.ui.express.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.express.mvp.entity.CommitOrderResult;
import com.yusong.club.ui.express.mvp.entity.DisclaimerBean;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface ISaveBagsView extends BaseView {
    void commitSuccess(CommitOrderResult data);

    void queryUrlSuccess(DisclaimerBean bean);
}
