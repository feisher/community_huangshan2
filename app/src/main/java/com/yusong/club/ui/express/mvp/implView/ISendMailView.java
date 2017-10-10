package com.yusong.club.ui.express.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.express.mvp.entity.CommitOrderResult;
import com.yusong.club.ui.express.mvp.entity.ConfigInfo;
import com.yusong.club.ui.express.mvp.entity.ContactGroup;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface ISendMailView extends BaseView {

    void commitSuccess(CommitOrderResult data);

    void saveConfig(ConfigInfo data);

    void showRates(int charge);

    void setDftInfo(ContactGroup group);

}
