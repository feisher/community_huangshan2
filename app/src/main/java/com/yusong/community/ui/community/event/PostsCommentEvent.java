package com.yusong.community.ui.community.event;

/**
 * Created by feisher on 2017/2/17.
 */

public class PostsCommentEvent {
    public int postsId;

    public PostsCommentEvent(int postsId) {
        this.postsId = postsId;
    }

    public int getPostsId() {
        return postsId;
    }

    public void setPostsId(int postsId) {
        this.postsId = postsId;
    }
}
