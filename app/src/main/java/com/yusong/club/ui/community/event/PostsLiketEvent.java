package com.yusong.club.ui.community.event;

/**
 * Created by feisher on 2017/2/17.
 */

public class PostsLiketEvent {
    public int postsId;

    public PostsLiketEvent(int postsId) {
        this.postsId = postsId;
    }

    public int getPostsId() {
        return postsId;
    }

    public void setPostsId(int postsId) {
        this.postsId = postsId;
    }
}
