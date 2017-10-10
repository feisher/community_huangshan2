package com.yusong.club.ui.school.mvp.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * create by feisher on 2017/3/9
 * Email：458079442@qq.com
 */
public class ApplyRole implements Serializable {

    /**
     * applyMemo : 申请
     * applyRole : 家长
     * clazzName : 三年二班
     * createTime : 2017-03-09 13:58:11
     * id : 22
     * idCard : 1514546515486451
     * photoPath : https://icon.jpg
     * status : 2
     * tel : 1514415154
     * type : 1
     * userName : 李先生
     */

    @SerializedName("applyMemo")
    private String applyMemo;
    @SerializedName("applyRole")
    private String applyRole;
    @SerializedName("clazzName")
    private String clazzName;
    @SerializedName("createTime")
    private String createTime;
    @SerializedName("id")
    private int id;
    @SerializedName("idCard")
    private String idCard;
    @SerializedName("photoPath")
    private String photoPath;
    @SerializedName("status")
    private int status;
    @SerializedName("tel")
    private String tel;
    @SerializedName("type")
    private int type;
    @SerializedName("userName")
    private String userName;
    @SerializedName("studentName")
    private String studentName;
    @SerializedName("studentNo")
    private String studentNo;

    public String getStduentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStduentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getApplyMemo() {
        return applyMemo;
    }

    public void setApplyMemo(String applyMemo) {
        this.applyMemo = applyMemo;
    }

    public String getApplyRole() {
        return applyRole;
    }

    public void setApplyRole(String applyRole) {
        this.applyRole = applyRole;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
