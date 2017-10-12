package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/31.
 *         描述: 新建提前放学
 */

public interface ICreateAheadPresenter extends BasePresenter {
    void createAhead(String overTime, int[] weekArray);
}
