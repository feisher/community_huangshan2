package com.yusong.club.ui.visitor.mvp.ImplView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.visitor.entity.CommuntitySetingBean;
import com.yusong.club.ui.visitor.entity.OwnerInfo;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: null
 */

public interface IViewOwnerInfo extends BaseView {
    void queryOwnerInfoSucces(OwnerInfo data);

    void queryCommuntitySucces(CommuntitySetingBean communtitySetingBean);
}
