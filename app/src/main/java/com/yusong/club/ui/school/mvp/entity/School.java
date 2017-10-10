package com.yusong.club.ui.school.mvp.entity;


import java.io.Serializable;

/**
 * Created by feisher on 2017/2/28.
 */

public class School implements Serializable {

    /**
     * id : 11
     * schoolName : 中心小学
     */

    private int id;
    private String schoolName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

}
