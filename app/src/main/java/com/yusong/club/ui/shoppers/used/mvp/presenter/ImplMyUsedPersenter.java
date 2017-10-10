package com.yusong.club.ui.shoppers.used.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/18.
 *         描述: null
 */

public interface ImplMyUsedPersenter extends BasePresenter {
    void queryMyUsed(int offset, int limit);

    void outUsed(int id);

    void deleteUsed(int id);

}
