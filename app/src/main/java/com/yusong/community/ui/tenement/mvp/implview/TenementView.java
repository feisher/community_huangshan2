package com.yusong.community.ui.tenement.mvp.implview;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.tenement.entity.TenementBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-04.
 * @describe: null
 */

public interface TenementView extends BaseView {
    void queryTenementSucces(List<TenementBean> data);
}
