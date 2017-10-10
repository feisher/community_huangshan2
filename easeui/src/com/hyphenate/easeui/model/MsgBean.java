package com.hyphenate.easeui.model;

import java.io.Serializable;

/**
 * ***********************************************
 * **                ┏┓   ┏┓                    **
 * **                ┏┛┻━━━┛┻┓                  **
 * **                ┃       ┃                  **
 * **                ┃   ━   ┃                  **
 * **                ┃ ┳┛ ┗┳ ┃                  **
 * **                ┃       ┃                  **
 * **                ┃   ┻   ┃                  **
 * **                ┃       ┃                  **
 * **                ┗━┓   ┏━┛                  **
 * **                  ┃   ┃                    **
 * **                  ┃   ┃                    **
 * **                  ┃   ┗━━━┓                **
 * **                  ┃       ┣┓               **
 * **                  ┃       ┏┛               **
 * **                  ┗┓┓┏━┳┓┏┛                **
 * **                   ┃┫┫ ┃┫┫                 **
 * **                  ┗┻┛ ┗┻┛                  **
 * ***********************************************
 * **               神兽助我 扬我神威              **
 * ***********************************************
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/29 16:37 </li>
 * </ul>
 */
public class MsgBean implements Serializable {


    /**
     * data : {"ext":null,"title":"推送消息","type":1,"content":"你好啊，杨文新！"}
     * type : 1
     */

    private Notify data;
    private int type;
    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Notify getData() {
        return data;
    }

    public void setData(Notify data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MsgBean{" +
                "data=" + data +
                ", type=" + type +
                ", json='" + json + '\'' +
                '}';
    }

}
