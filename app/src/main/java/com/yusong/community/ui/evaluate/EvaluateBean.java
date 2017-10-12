package com.yusong.community.ui.evaluate;

/**
 * @author Mr_Peng
 * @created at 2017-09-02.
 * @describe: null
 */

public class EvaluateBean {

    /**
     * image3 : http://192.9.198.181:8080/static/evaluate_image/17/251277f2ae891a4b5ea90faa5e759a5e7961.jpeg
     * image4 : null
     * createTime : 2017-09-02 10:32:45
     * evaluateCount : 17
     * proprietor : {"profilePath":"http://192.9.198.181:8080/static/basic/customer_portrait/2017-05-24/fc4c565ca8f55a4c1eaafa0a3b65c18bcf33.jpeg","proprietorName":"18170580598"}
     * image1 : http://192.9.198.181:8080/static/evaluate_image/17/d5ffa9fea3191a4590a8d2eae6205ab611f0.jpeg
     * image2 : http://192.9.198.181:8080/static/evaluate_image/17/0ef3b2d8a631ea4f84aa168ad893ec83d2a9.jpeg
     * content : 哦老婆咯啊jobs男萨博抛弃kiss哦婆婆肉末头痛老婆哦老婆咯嗖嗖嗖
     */

    private String image3;
    private String image4;
    private String createTime;
    private int evaluateCount;
    private ProprietorBean proprietor;
    private String image1;
    private String image2;
    private String content;

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getEvaluateCount() {
        return evaluateCount;
    }

    public void setEvaluateCount(int evaluateCount) {
        this.evaluateCount = evaluateCount;
    }

    public ProprietorBean getProprietor() {
        return proprietor;
    }

    public void setProprietor(ProprietorBean proprietor) {
        this.proprietor = proprietor;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class ProprietorBean {
        /**
         * profilePath : http://192.9.198.181:8080/static/basic/customer_portrait/2017-05-24/fc4c565ca8f55a4c1eaafa0a3b65c18bcf33.jpeg
         * proprietorName : 18170580598
         */

        private String profilePath;
        private String proprietorName;

        public String getProfilePath() {
            return profilePath;
        }

        public void setProfilePath(String profilePath) {
            this.profilePath = profilePath;
        }

        public String getProprietorName() {
            return proprietorName;
        }

        public void setProprietorName(String proprietorName) {
            this.proprietorName = proprietorName;
        }
    }
}
