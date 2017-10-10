package com.yusong.club.ui.shoppers.used.event;

import com.yusong.club.ui.shoppers.used.bean.MyUsedBean;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/22.
 *         描述: item点击事件透传
 */

public class EventMyUsed {
    private MyUsedBean bean;
    private int type;//按钮标识

    public MyUsedBean getBean() {
        return bean;
    }

    public void setBean(MyUsedBean bean) {
        this.bean = bean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
