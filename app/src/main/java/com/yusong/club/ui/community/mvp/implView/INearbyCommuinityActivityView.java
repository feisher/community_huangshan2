package com.yusong.club.ui.community.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.community.mvp.entity.Community;
import com.yusong.club.ui.community.mvp.entity.SetCommunity;

import java.util.List;

/**
 * Created by feisher on 2017/1/14.
 */
public  interface INearbyCommuinityActivityView extends BaseView {
    void setCommunitysAdapter(List<Community> mCommunityLists);
    void setCurrentCommunitySucceedCallback(SetCommunity mSetCommunity);
    void setCurrentCommunityFailCallback();
    void closeRefresh();

}
