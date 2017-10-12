package com.yusong.community.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quaner on 16/12/27.
 * <p>
 * 用于放置全局常量
 */
public class ActivityConstants {

    //快递柜数据库名称
    public static final String EXPRESS_DB = "express.db";

    /**
     * 快递柜常量
     */
    public static List<Long> list_number = new ArrayList<>();//暂时用于存储用户搜索单号历史
    public static final int INFO_SENDER_REQUEST = 1; //寄件
    public static final int INFO_GET_REQUEST = 2; //收件
    public static final int INFO_SENDER_RESPONS = 3; //寄件
    public static final int INFO_GET_RESPONS = 4; //收件
    public static final String INFO_FILL = "info_fill";//寄件or收件
    public static final String INFO_PHONE = "info_phone";
    public static final String INFO_ADDRESS = "info_address";
    public static final String INFO_NAME = "info_name";
    public static final String INFO_ID = "info_id";
    public static final String INFO_IS_SAVE = "info_is_save";
    public static final String INFO_CITY = "info_city";
    public static final String CITY = "city";
    public static final String PROVINCE = "province";
    public static final String COUNY = "couny";
    public static final String TYPE = "type";//2 收件1 寄件
    public static final String CITY_CODE = "city_code";
    public static final String AUTH_CODE = "auth_code";
    public static final String MSG = "msg";
    public static final String PROVINCE_CODE = "province_code";
    public static final String DISTRICT_CODE = "district_code";
    public static final String CONTACT_ID = "id";
    public static final String ORDER_TYPE = "1";
    public static final String all = "all";//通用 全部
    public static final String timeout = "timeout";//通用超时
    public static final String wait_take = "wait_take";//收件 待签收
    public static final String taken = "taken";//通用  收件已签收  存包 已取
    public static final String wait_put = "wait_put";//存包代存
    public static final String wait_pay = "wait_pay";//寄件 待付款
    public static final String payed = "payed";//寄件 已付款
    public static final String ORDER_ID = "order_id";
    public static final String ShipperCode = "ShipperCode";
    public static final String ShipperName = "ShipperName";
    public static final String SENDER_CONID = "sender_id";
    public static final String GET_CONID = "get_id";
    public static final String SEND_OR_GET = "send_or_get";//联系人是收件还是寄件（编辑联系人使用）

    //----------------------------分割线---------------------------
    // #router
    public static final int ROUTER_ANIM_ENTER = Router.RES_NONE;
    public static final int ROUTER_ANIM_EXIT = Router.RES_NONE;

    // ---------------------- pay ----------------------------
    public static final int SENDER_ORDER = 1;
    public static final int SAVE_ORDER = 2;
    public static final int STORE_ORDER = 3;
    public static final int QUJIAN_OERDER = 4;
    public static final int SUPER_MARKET_OERDER = 5;
    public static final int SERVICE_OERDER = 6;
    public static final String CHARGE = "charge";
    public static final String COMMON_PAYTYPE = "common_paytype";

    //-------------------------- 学校 ------------------------------
    public static final String PORTRAIT = "Portrait";
    public static final String NAME = "name";
    public static final String USER_TYPE = "user_type";
    public static final String USER_ID = "user_id";
    public static final int Parent_TYPE = 3;
    public static final int Teachar_TYPE = 2;
    public static final int Student_TYPE = 4;
    public static final int Admin_TYPE = 1;
    public static final int NOTITY_CONGIRM = 5;//发布公告成功
    public static final int WORK_CODE = 6;//发布公告成功
    public static final int ADD_AFTER_SCHOOL = 7;//发布公告成功

    public static final int RESULT_CODE = 100;//商城acitvity标记
    public static final int REQUEST_CODE = 50;//商城acitvity标记
    public static final int REQUEST_CODE_ASK_CALL_PHONE = 1000;//拨号动态权限

}

