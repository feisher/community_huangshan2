package com.yusong.community.ui.express.event;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/2/14 14:00 </li>
 * </ul>
 */
public class OpenBox {
    String token;
    int type;
    String id;

    public OpenBox(String token, int type, String id) {
        this.token = token;
        this.type = type;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
