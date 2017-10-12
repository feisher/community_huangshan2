package com.yusong.community.ui.school.teacher.bean;

/**
 * Created by ruanjian on 2017/3/22.
 */

public class EventMsg {
    public String msg;
    public int id;

    public EventMsg(String msg, int id) {
        this.msg = msg;
        this.id = id;
    }

    public EventMsg(String msg) {
        this.msg = msg;
    }

    public static final String REFRESH_PHOT_DETAIL="REFRESH_PHOT_DETAIL";
}
