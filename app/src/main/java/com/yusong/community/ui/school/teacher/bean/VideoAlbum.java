package com.yusong.community.ui.school.teacher.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ruanjian on 2017/3/6.
 */

public class VideoAlbum implements Serializable{
    /**
     * albumName : aaa
     * amount : 0
     * createTime : 2017-03-14 15:27:22
     * videoList : [{"duration":10,"size":3406,"createTime":"2017-03-23 13:32:38","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg","id":3},{"duration":10,"size":3187,"createTime":"2017-03-23 13:32:38","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg","id":4},{"duration":10,"size":4360,"createTime":"2017-03-23 13:32:38","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg","id":5},{"duration":10,"size":1597,"createTime":"2017-03-23 13:32:38","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg","id":6},{"duration":10,"size":10832,"createTime":"2017-03-23 13:32:38","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg","id":7},{"duration":10,"size":3187,"createTime":"2017-03-23 14:34:34","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323143437_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323143437_.jpg","id":8},{"duration":10,"size":3406,"createTime":"2017-03-23 14:34:34","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323143437_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323143437_.jpg","id":9},{"duration":10,"size":3187,"createTime":"2017-03-23 14:35:29","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323143531_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323143531_.jpg","id":10},{"duration":10,"size":3187,"createTime":"2017-03-23 14:38:48","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323143850_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323143850_.jpg","id":11},{"duration":10,"size":3406,"createTime":"2017-03-23 14:38:48","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323143850_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323143850_.jpg","id":12},{"duration":10,"size":3772,"createTime":"2017-03-23 14:38:55","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323143850_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323143850_.jpg","id":13},{"duration":10,"size":3406,"createTime":"2017-03-23 14:39:25","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323143928_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323143928_.jpg","id":14},{"duration":10,"size":3187,"createTime":"2017-03-23 14:39:25","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323143928_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323143928_.jpg","id":15},{"duration":10,"size":3772,"createTime":"2017-03-23 14:39:59","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323144002_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323144002_.jpg","id":16},{"duration":10,"size":3406,"createTime":"2017-03-23 14:39:59","imagePath":"http://122.224.164.50:8100//static/download/2/program/20170323144002_.jpg","filePath":"http://122.224.164.50:8100//static/download/2/program/20170323144002_.jpg","id":17}]
     * canEdit : 1
     * uploadUserName : 18457148548
     * memo : bbbb
     * id : 1
     */

    private String albumName;
    private int amount;
    private String createTime;
    private int canEdit;
    private String uploadUserName;
    private String memo;
    private int id;
    private List<VideoListBean> videoList;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(int canEdit) {
        this.canEdit = canEdit;
    }

    public String getUploadUserName() {
        return uploadUserName;
    }

    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<VideoListBean> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoListBean> videoList) {
        this.videoList = videoList;
    }

    public static class VideoListBean implements Serializable{
        /**
         * duration : 10
         * size : 3406
         * createTime : 2017-03-23 13:32:38
         * imagePath : http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg
         * filePath : http://122.224.164.50:8100//static/download/2/program/20170323133241_.jpg
         * id : 3
         */

        private int duration;
        private int size;
        private String createTime;
        private String imagePath;
        private String filePath;
        private int videoId;
        private boolean isCheck=false;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }
    }

//    private String id;//: string 相册id
//    private String albumName;//: string 相册名称
//    private int amount;//: int 数量
//    private String uploadUserName;//: string 上传人员
//    private String memo;//: string 备注
//    private String createTime;//: string 创建时间
//    private List<VideoListBean> videoListBeanList;
//    private int canEdit;//: 1 有修改权限 0 没有修改权限
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getAlbumName() {
//        return albumName;
//    }
//
//    public void setAlbumName(String albumName) {
//        this.albumName = albumName;
//    }
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//
//    public String getUploadUserName() {
//        return uploadUserName;
//    }
//
//    public void setUploadUserName(String uploadUserName) {
//        this.uploadUserName = uploadUserName;
//    }
//
//    public String getMemo() {
//        return memo;
//    }
//
//    public void setMemo(String memo) {
//        this.memo = memo;
//    }
//
//
//    public String getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(String createTime) {
//        this.createTime = createTime;
//    }
//
//
//    public List<VideoListBean> getVideoListBeanList() {
//        return videoListBeanList;
//    }
//
//    public void setVideoListBeanList(List<VideoListBean> videoListBeanList) {
//        this.videoListBeanList = videoListBeanList;
//    }
//
//    public int getCanEdit() {
//        return canEdit;
//    }
//
//    public void setCanEdit(int canEdit) {
//        this.canEdit = canEdit;
//    }
//
//    public class VideoListBean implements Serializable{
//        private int id;//: int id,
//        private String createTime;//: string 上传时间
//        private String imagePath;//: string "http://192.168.1.100:8080/sdfsfdsdf.jpg" 封面图片路径
//        private int duration ;//int 时长
//        private int size ;//int 大小
//        private String filePath;//: "http://192.168.1.100:8080/sdfsfdsdf.jpg" 视频路径
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getImagePath() {
//            return imagePath;
//        }
//
//        public void setImagePath(String imagePath) {
//            this.imagePath = imagePath;
//        }
//
//        public String getCreateTime() {
//            return createTime;
//        }
//
//        public void setCreateTime(String createTime) {
//            this.createTime = createTime;
//        }
//    }

}
