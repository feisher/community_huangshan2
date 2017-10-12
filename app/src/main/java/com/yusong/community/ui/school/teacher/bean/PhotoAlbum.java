package com.yusong.community.ui.school.teacher.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ruanjian on 2017/3/6.
 */

public class PhotoAlbum  implements Serializable{
    /**
     * albumName : 考虑考虑
     * amount : 2
     * createTime : 2017-03-25 08:43:24
     * canEdit : 1
     * uploadUserName : 18457148548
     * memo : 看见了
     * id : 7
     * imageList : [{"imageId":69,"address":null,"createTime":"2017-03-27 14:50:46","imagePath":"http://122.224.164.50:8100//static/school/clazz_photo_portrait/69/90ebb7d7a0813a457aaad5eab63ddbfb6c78.jpeg"},{"imageId":70,"address":null,"createTime":"2017-03-27 14:50:46","imagePath":"http://122.224.164.50:8100//static/school/clazz_photo_portrait/70/1d42fd3ea74e8a4c72ab404a0cac188ad7f4.jpeg"}]
     */
    private String albumName;
    private int amount;
    private String createTime;
    private int canEdit;
    private String uploadUserName;
    private String memo;
    private int id;
    private List<ImageListBean> imageList;

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

    public List<ImageListBean> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageListBean> imageList) {
        this.imageList = imageList;
    }

    public static class ImageListBean implements Serializable{
        /**
         * imageId : 69
         * address : null
         * createTime : 2017-03-27 14:50:46
         * imagePath : http://122.224.164.50:8100//static/school/clazz_photo_portrait/69/90ebb7d7a0813a457aaad5eab63ddbfb6c78.jpeg
         */

        private int imageId;
        private String address;
        private String createTime;
        private String imagePath;
        private boolean isCheck=false;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
    }

  /*  private String id;//: string 相册id
    private String albumName;//: string 相册名称
    private int amount;//: int 数量
    private String uploadUserName;//: string 上传人员
    private String memo;//: string 备注
    private String address;//: string 地址
    private String createTime;//: string 创建时间
    private List<ImageListBean> imageList;
    private int canEdit;//: 1 有修改权限 0 没有修改权限
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<ImageListBean> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageListBean> imageList) {
        this.imageList = imageList;
    }

    public int getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(int canEdit) {
        this.canEdit = canEdit;
    }

    public class ImageListBean implements Serializable{
        private int id;//: int id,
        private String imagePath;//: string "http://192.168.1.100:8080/sdfsfdsdf.jpg" 图片路径
        private String createTime;//: string 上传时间
         private boolean isCheck=false;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }*/

}
