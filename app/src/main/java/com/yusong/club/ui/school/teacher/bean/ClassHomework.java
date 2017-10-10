package com.yusong.club.ui.school.teacher.bean;

/**
 * Created by ruanjian on 2017/3/6.
 */

public class ClassHomework {
    private String courseName; //: string 课程名称
    private String content; //: string 内容
    private String photoPath;//图标地址

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
