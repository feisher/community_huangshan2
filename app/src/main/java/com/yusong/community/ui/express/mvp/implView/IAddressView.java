package com.yusong.community.ui.express.mvp.implView;

import android.content.Intent;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.express.mvp.entity.ContactGroup;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface IAddressView extends BaseView {


    void setContactAdapter(List<ContactGroup> data);

    void close();

    void result(int respons, Intent intent);

    void notifyAdapter();
}
