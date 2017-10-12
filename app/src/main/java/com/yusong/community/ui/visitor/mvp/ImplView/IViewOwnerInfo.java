package com.yusong.community.ui.visitor.mvp.ImplView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.visitor.entity.CommuntitySetingBean;
import com.yusong.community.ui.visitor.entity.OwnerInfo;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: null
 */

public interface IViewOwnerInfo extends BaseView {
    void queryOwnerInfoSucces(OwnerInfo data);

    void queryCommuntitySucces(CommuntitySetingBean communtitySetingBean);
}
