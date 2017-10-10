package com.yusong.club.ui.community_service.mvp.ImplView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.community_service.entity.ServiceOrderBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-23.
 * @describe: null
 */

public interface ServiceOrderView extends BaseView {
    void queryOrderSucces(List<ServiceOrderBean> beanList);
    void queryOrderError();

    void commitServiceCommentSucces();

    void cancelServiceOrderSucces();

    void confirmOrderSucces();

}
