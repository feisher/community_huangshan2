package com.yusong.club.ui.community.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.community.mvp.entity.HaveCommunityCity;

import java.util.List;

/**
 * Created by feisher on 2017/1/14.
 */
public  interface HaveCommunityCityActivityView extends BaseView {
    void setAdapter4ListDataCallback(List<HaveCommunityCity> data);

}
