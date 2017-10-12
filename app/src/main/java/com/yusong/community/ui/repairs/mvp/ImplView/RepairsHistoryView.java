package com.yusong.community.ui.repairs.mvp.ImplView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.repairs.RepairsHistoryBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-01.
 * @describe: null
 */

public interface RepairsHistoryView extends BaseView {
    void querySucces(List<RepairsHistoryBean> list);
    void queryError();
}
