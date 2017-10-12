package com.yusong.community.ui.visitor.mvp.ImplView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.visitor.entity.ThroughCardBean;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: null
 */

public interface CreateThroughView extends BaseView {

    void createSucces(ThroughCardBean data);
}
