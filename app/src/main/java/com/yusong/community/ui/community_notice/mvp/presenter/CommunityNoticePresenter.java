package com.yusong.community.ui.community_notice.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-06.
 * @describe: null
 */

public interface CommunityNoticePresenter extends BasePresenter {
    void queryNotic(int offset,int limit);
}
