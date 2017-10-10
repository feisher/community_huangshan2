package com.yusong.club.ui.evaluate.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-02.
 * @describe: null
 */

public interface EvaluateComentPresenter extends BasePresenter {
    void queryEvaluateComent(int offset,int limit);
}
