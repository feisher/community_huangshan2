package com.yusong.club.ui.shoppers.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.shoppers.bean.TuiJianBean;

import java.util.List;

/**
 * @author Mr_Peng
 *         created at 2017/3/6 15:08.
 */

public interface ImplQuerytuijianListView extends BaseView {
    void refreshTuiJianList(List<TuiJianBean.Commodity> data);

    void refreshuituijianClose();
}
