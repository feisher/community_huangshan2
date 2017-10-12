package com.yusong.community.ui.evaluate.mvp.implview;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.evaluate.EvaluateBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-02.
 * @describe: null
 */

public interface EvaluateComentView extends BaseView {
    void querySucces(List<EvaluateBean> data);

    void queryError();
}
