package com.yusong.club.ui.visitor.mvp.ImplView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.visitor.entity.CardDetailsBean;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: null
 */

public interface ICheckPermitView extends BaseView {
    void checkPermitSucces(CardDetailsBean data);
}
