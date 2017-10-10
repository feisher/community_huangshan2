package com.yusong.club.ui.express.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;
import com.yusong.club.ui.express.mvp.entity.ContactGroup;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface IAddressPresenter extends BasePresenter {
    void queryContact(int type);

    void onItemClick(ContactGroup data, int mInfo_fill);

    void deleteContact(int position);

    void setDftContact(int id, int type);
}
