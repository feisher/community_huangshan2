package com.yusong.club.ui.repairs.mvp.persenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-01.
 * @describe: null
 */

public interface RepairsHistoryPresenter extends BasePresenter {
    void queryRepairsHistory(int proprietorId, int offset, int limit);
}
