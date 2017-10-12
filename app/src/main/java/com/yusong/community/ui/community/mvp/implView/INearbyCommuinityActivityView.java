package com.yusong.community.ui.community.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.community.mvp.entity.Community;
import com.yusong.community.ui.community.mvp.entity.SetCommunity;

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
