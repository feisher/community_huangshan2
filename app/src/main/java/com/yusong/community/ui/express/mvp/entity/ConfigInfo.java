package com.yusong.community.ui.express.mvp.entity;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class ConfigInfo {
    List<Express> companyList;
    List<Box> boxType;
    List<String> thingType;

    public List getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }

    public List getBoxType() {
        return boxType;
    }

    public void setBoxType(List boxType) {
        this.boxType = boxType;
    }

    public List<String> getThingType() {
        return thingType;
    }

    public void setThingType(List<String> thingType) {
        this.thingType = thingType;
    }

    public class Box{
        int id;
        String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public class Express{

        int id;
        String companyName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }

}
