package com.yusong.club.ui.community_service.mvp.ImplView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.community_service.entity.ServiceBean;
import com.yusong.club.ui.community_service.entity.ServiceDetailBean;
import com.yusong.club.ui.shoppers.bean.FenLeiBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: null
 */

public interface ServiceView extends BaseView {
    void queryServiceSucces(ServiceDetailBean bean);

    void queryServiceCategorySucces(List<FenLeiBean> datas);

    void queryServiceListSucces(List<ServiceBean> datas);

    void queryServiceListError();
}
