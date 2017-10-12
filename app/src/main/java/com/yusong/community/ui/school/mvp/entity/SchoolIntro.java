package com.yusong.community.ui.school.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by feisher on 2017/3/6.
 */

public class SchoolIntro implements Parcelable {

    /**
     * schoolDescriptionUrl : http://xxxx.html
     */

    @SerializedName("schoolDescriptionUrl")
    private String schoolDescriptionUrl;

    public String getSchoolDescriptionUrl() {
        return schoolDescriptionUrl;
    }

    public void setSchoolDescriptionUrl(String schoolDescriptionUrl) {
        this.schoolDescriptionUrl = schoolDescriptionUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.schoolDescriptionUrl);
    }

    public SchoolIntro() {
    }

    protected SchoolIntro(Parcel in) {
        this.schoolDescriptionUrl = in.readString();
    }

    public static final Parcelable.Creator<SchoolIntro> CREATOR = new Parcelable.Creator<SchoolIntro>() {
        public SchoolIntro createFromParcel(Parcel source) {
            return new SchoolIntro(source);
        }

        public SchoolIntro[] newArray(int size) {
            return new SchoolIntro[size];
        }
    };
}
