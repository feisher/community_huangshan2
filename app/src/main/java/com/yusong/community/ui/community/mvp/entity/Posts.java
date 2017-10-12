package com.yusong.community.ui.community.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by feisher on 2017/1/20.
 */

public class Posts implements Parcelable {


    /**
     * commentCount : 6
     * content : string 内容
     * createTime : 2015-01-01 01:01:01
     * customer : {"gender":1,"id":2,"portrait":"string 头像","realName":" "}
     * id : 1
     * imageList : ["http://www.s1ss.jpg","http://www.ss2s.jpg","http://www.sss.jpg"]
     * supportCount : 2
     * thankCount : 1
     */

    private int commentCount;
    private String content;
    private String createTime;
    private int supported;
    /**
     * gender : 1
     * id : 2
     * portrait : string 头像
     * realName :
     */

    private CustomerBean customer;
    private int id;
    private int supportCount;
    private int thankCount;
    private List<String> imageList;

    public int getSupported() {
        return supported;
    }

    public void setSupported(int supported) {
        this.supported = supported;
    }

    public static Creator<Posts> getCREATOR() {
        return CREATOR;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(int supportCount) {
        this.supportCount = supportCount;
    }

    public int getThankCount() {
        return thankCount;
    }

    public void setThankCount(int thankCount) {
        this.thankCount = thankCount;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public static class CustomerBean implements Parcelable {

        private int gender;
        private int id;
        private String portrait;
        private String realName;
        private String mobile;
        private String nickname;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        @Override
        public String toString() {
            return "CustomerBean{" +
                    "gender=" + gender +
                    ", id=" + id +
                    ", portrait='" + portrait + '\'' +
                    ", realName='" + realName + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.gender);
            dest.writeInt(this.id);
            dest.writeString(this.portrait);
            dest.writeString(this.realName);
            dest.writeString(this.nickname);
            dest.writeString(this.mobile);
        }

        public CustomerBean() {
        }

        protected CustomerBean(Parcel in) {
            this.gender = in.readInt();
            this.id = in.readInt();
            this.portrait = in.readString();
            this.realName = in.readString();
            this.nickname = in.readString();
            this.mobile = in.readString();
        }

        public static final Creator<CustomerBean> CREATOR = new Creator<CustomerBean>() {
            public CustomerBean createFromParcel(Parcel source) {
                return new CustomerBean(source);
            }

            public CustomerBean[] newArray(int size) {
                return new CustomerBean[size];
            }
        };
    }

    @Override
    public String toString() {
        return "Posts{" +
                "commentCount=" + commentCount +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", supported=" + supported +
                ", customer=" + customer +
                ", id=" + id +
                ", supportCount=" + supportCount +
                ", thankCount=" + thankCount +
                ", imageList=" + imageList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.commentCount);
        dest.writeString(this.content);
        dest.writeInt(this.supported);
        dest.writeString(this.createTime);
        dest.writeParcelable((Parcelable) this.customer, flags);
        dest.writeInt(this.id);
        dest.writeInt(this.supportCount);
        dest.writeInt(this.thankCount);
        dest.writeStringList(this.imageList);
    }

    public Posts() {
    }

    protected Posts(Parcel in) {
        this.commentCount = in.readInt();
        this.content = in.readString();
        this.supported = in.readInt();
        this.createTime = in.readString();
        this.customer = in.readParcelable(CustomerBean.class.getClassLoader());
        this.id = in.readInt();
        this.supportCount = in.readInt();
        this.thankCount = in.readInt();
        this.imageList = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Posts> CREATOR = new Parcelable.Creator<Posts>() {
        public Posts createFromParcel(Parcel source) {
            return new Posts(source);
        }

        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };
}
