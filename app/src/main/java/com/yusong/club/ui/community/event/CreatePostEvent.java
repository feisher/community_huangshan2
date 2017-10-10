package com.yusong.club.ui.community.event;

/**
 * create by feisher on 2017/3/29
 * Email：458079442@qq.com
 */
public class CreatePostEvent {
    public int PostTypeIndex;
    public int catogreyId;

    public void setPostTypeIndex(int postTypeIndex) {
        PostTypeIndex = postTypeIndex;
    }

    public int getCatogreyId() {
        return catogreyId;
    }

    public void setCatogreyId(int catogreyId) {
        this.catogreyId = catogreyId;
    }

    public CreatePostEvent(int postTypeIndex, int catogreyId) {
        PostTypeIndex = postTypeIndex;
        this.catogreyId = catogreyId;
    }

    public int getPostTypeIndex() {
        return PostTypeIndex;
    }


    public CreatePostEvent(int mPostTypeIndex) {
        this.PostTypeIndex = mPostTypeIndex;
    }
}
