package com.yusong.community.ui.community.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feisher on 2017/2/20.
 */

public class HaveCommunityCity implements Parcelable {

    /**
     * letter : c
     * list : [{"areaName":"杭州市","id":1007}]
     */

    private String letter;
    /**
     * areaName : 杭州市
     * id : 1007
     */

    private List<ListBean> list;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String areaName;
        private int id;

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.letter);
        dest.writeList(this.list);
    }

    public HaveCommunityCity() {
    }

    @Override
    public String toString() {
        return "HaveCommunityCity{" +
                "letter='" + letter + '\'' +
                ", list=" + list +
                '}';
    }

    protected HaveCommunityCity(Parcel in) {
        this.letter = in.readString();
        this.list = new ArrayList<ListBean>();
        in.readList(this.list, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<HaveCommunityCity> CREATOR = new Parcelable.Creator<HaveCommunityCity>() {
        public HaveCommunityCity createFromParcel(Parcel source) {
            return new HaveCommunityCity(source);
        }

        public HaveCommunityCity[] newArray(int size) {
            return new HaveCommunityCity[size];
        }
    };
}
