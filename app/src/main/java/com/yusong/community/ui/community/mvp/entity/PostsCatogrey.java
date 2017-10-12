package com.yusong.community.ui.community.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by feisher on 2017/1/14.
 */
public class PostsCatogrey implements Parcelable {
    int id;//分类id,
    String categoryName;//分类名称
    String memo;// 描述

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "PostsCatogrey{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.categoryName);
        dest.writeString(this.memo);
    }

    public PostsCatogrey() {
    }

    protected PostsCatogrey(Parcel in) {
        this.id = in.readInt();
        this.categoryName = in.readString();
        this.memo = in.readString();
    }

    public static final Parcelable.Creator<PostsCatogrey> CREATOR = new Parcelable.Creator<PostsCatogrey>() {
        public PostsCatogrey createFromParcel(Parcel source) {
            return new PostsCatogrey(source);
        }

        public PostsCatogrey[] newArray(int size) {
            return new PostsCatogrey[size];
        }
    };
}
