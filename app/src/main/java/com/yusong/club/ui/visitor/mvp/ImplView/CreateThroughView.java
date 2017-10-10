package com.yusong.club.ui.visitor.mvp.ImplView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.visitor.entity.ThroughCardBean;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: null
 */

public interface CreateThroughView extends BaseView {

    void createSucces(ThroughCardBean data);
}
