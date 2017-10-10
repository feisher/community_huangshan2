package com.yusong.club.ui.community.event;

/**
 * create by feisher on 2017/3/30
 * Email：458079442@qq.com
 */
public class CreatePostRefashEvent {
    public int catogreyId;

    public CreatePostRefashEvent(int catogreyId) {
        this.catogreyId = catogreyId;
    }

    public int getCatogreyId() {

        return catogreyId;
    }

    public void setCatogreyId(int catogreyId) {
        this.catogreyId = catogreyId;
    }

    public CreatePostRefashEvent() {
    }
}
