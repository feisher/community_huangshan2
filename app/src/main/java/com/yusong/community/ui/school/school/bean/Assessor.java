package com.yusong.community.ui.school.school.bean;

/**
 * 家长审核类.
 */
public class Assessor {
    private String day;
    private String month;
    private String title;
    private String assessorContent;

    public Assessor(String day, String month, String title, String assessorContent) {
        this.day = day;
        this.month = month;
        this.title = title;
        this.assessorContent = assessorContent;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssessorContent() {
        return assessorContent;
    }

    public void setAssessorContent(String assessorContent) {
        this.assessorContent = assessorContent;
    }
}
