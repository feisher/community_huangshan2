package com.yusong.community.ui.community.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.community.mvp.entity.Posts;

import java.util.List;

public interface ICommunityItemView extends BaseView {
//    void setFragmentItemAdapter(List<CommunityRececlerViewItemInfo> data);
    void setFragmentItemAdapter(List<Posts> data);
    void postsCommentCallback();
    void commitLikePostsCallback();
    void closeRefreshing();

}
