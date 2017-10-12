package com.yusong.community.ui.express.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.express.mvp.entity.NearbyBox;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface INearbyCabinetView extends BaseView {
    void setAdapter(List<NearbyBox> data);
}
