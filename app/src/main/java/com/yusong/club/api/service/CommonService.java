package com.yusong.club.api.service;


import com.yusong.club.api.HttpResult;
import com.yusong.club.pay.bean.WeiXinPayBean;
import com.yusong.club.pay.bean.ZhiFuBaoPayBean;
import com.yusong.club.ui.charge.bean.CreatOrderBean;
import com.yusong.club.ui.charge.bean.FetchMoneyBean;
import com.yusong.club.ui.charge.bean.MoveSosBean;
import com.yusong.club.ui.charge.bean.MyOrderBean;
import com.yusong.club.ui.charge.bean.NearbyBean;
import com.yusong.club.ui.charge.bean.OrderDetailsBean;
import com.yusong.club.ui.charge.bean.SancContentBean;
import com.yusong.club.ui.charge.bean.SettingBean;
import com.yusong.club.ui.community.mvp.entity.Community;
import com.yusong.club.ui.community.mvp.entity.HaveCommunityCity;
import com.yusong.club.ui.community.mvp.entity.PostComment;
import com.yusong.club.ui.community.mvp.entity.PostSupport;
import com.yusong.club.ui.community.mvp.entity.Posts;
import com.yusong.club.ui.community.mvp.entity.PostsCatogrey;
import com.yusong.club.ui.community.mvp.entity.SetCommunity;
import com.yusong.club.ui.community_notice.entity.NoticeBean;
import com.yusong.club.ui.community_service.entity.ServiceBean;
import com.yusong.club.ui.community_service.entity.ServiceDetailBean;
import com.yusong.club.ui.community_service.entity.ServiceOrderBean;
import com.yusong.club.ui.evaluate.EvaluateBean;
import com.yusong.club.ui.express.mvp.entity.CommitOrderResult;
import com.yusong.club.ui.express.mvp.entity.ConfigInfo;
import com.yusong.club.ui.express.mvp.entity.Contact;
import com.yusong.club.ui.express.mvp.entity.ContactGroup;
import com.yusong.club.ui.express.mvp.entity.DisclaimerBean;
import com.yusong.club.ui.express.mvp.entity.GetDetails;
import com.yusong.club.ui.express.mvp.entity.GetOrderInfo;
import com.yusong.club.ui.express.mvp.entity.NearbyBox;
import com.yusong.club.ui.express.mvp.entity.OpenBoxOrder;
import com.yusong.club.ui.express.mvp.entity.OrderLogistics;
import com.yusong.club.ui.express.mvp.entity.RatesInfo;
import com.yusong.club.ui.express.mvp.entity.SaveDetails;
import com.yusong.club.ui.express.mvp.entity.SaveOrderInfo;
import com.yusong.club.ui.express.mvp.entity.ScanOrder;
import com.yusong.club.ui.express.mvp.entity.SenderDetails;
import com.yusong.club.ui.express.mvp.entity.SenderOrderInfo;
import com.yusong.club.ui.home.mvp.entity.AuthCodeResult;
import com.yusong.club.ui.home.mvp.entity.ExistsResult;
import com.yusong.club.ui.home.mvp.entity.LoginResult;
import com.yusong.club.ui.home.mvp.entity.Protocol;
import com.yusong.club.ui.home.mvp.request.ExistsRequest;
import com.yusong.club.ui.me.mvp.entity.MoneyList;
import com.yusong.club.ui.me.mvp.entity.UserInfo;
import com.yusong.club.ui.repairs.RepairsHistoryBean;
import com.yusong.club.ui.school.mvp.entity.ActionDetail;
import com.yusong.club.ui.school.mvp.entity.ApplyRole;
import com.yusong.club.ui.school.mvp.entity.Clazz;
import com.yusong.club.ui.school.mvp.entity.DetailComment;
import com.yusong.club.ui.school.mvp.entity.EditSucessResult;
import com.yusong.club.ui.school.mvp.entity.GoodAction;
import com.yusong.club.ui.school.mvp.entity.JoinedResult;
import com.yusong.club.ui.school.mvp.entity.LatestPhoto;
import com.yusong.club.ui.school.mvp.entity.Notice;
import com.yusong.club.ui.school.mvp.entity.ReadCount;
import com.yusong.club.ui.school.mvp.entity.Role;
import com.yusong.club.ui.school.mvp.entity.School;
import com.yusong.club.ui.school.mvp.entity.SchoolIntro;
import com.yusong.club.ui.school.mvp.entity.SchoolManager;
import com.yusong.club.ui.school.mvp.entity.StuList;
import com.yusong.club.ui.school.mvp.entity.TeacherInfo;
import com.yusong.club.ui.school.mvp.entity.TeacherLeave;
import com.yusong.club.ui.school.mvp.entity.TelBookData;
import com.yusong.club.ui.school.mvp.entity.UserInfoDetails;
import com.yusong.club.ui.school.school.bean.ActivityBean;
import com.yusong.club.ui.school.school.bean.AddressBean;
import com.yusong.club.ui.school.school.bean.ApplyList;
import com.yusong.club.ui.school.school.bean.Comment;
import com.yusong.club.ui.school.school.bean.HuoType;
import com.yusong.club.ui.school.school.bean.StudentComment;
import com.yusong.club.ui.school.teacher.bean.AheadBean;
import com.yusong.club.ui.school.teacher.bean.AllTeacher;
import com.yusong.club.ui.school.teacher.bean.ChatGroup;
import com.yusong.club.ui.school.teacher.bean.ClassHomework;
import com.yusong.club.ui.school.teacher.bean.Course;
import com.yusong.club.ui.school.teacher.bean.MemberGroup;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.ui.school.teacher.bean.Result;
import com.yusong.club.ui.school.teacher.bean.StudentList;
import com.yusong.club.ui.school.teacher.bean.StudyVideo;
import com.yusong.club.ui.school.teacher.bean.SubjectTable;
import com.yusong.club.ui.school.teacher.bean.TelBook;
import com.yusong.club.ui.school.teacher.bean.VideoAlbum;
import com.yusong.club.ui.shoppers.bean.CanQGBean;
import com.yusong.club.ui.shoppers.bean.CommodityBean;
import com.yusong.club.ui.shoppers.bean.CreataOrderBean;
import com.yusong.club.ui.shoppers.bean.FenLeiBean;
import com.yusong.club.ui.shoppers.bean.OrderShopBean;
import com.yusong.club.ui.shoppers.bean.PinLunBean;
import com.yusong.club.ui.shoppers.bean.QiangGouBean;
import com.yusong.club.ui.shoppers.bean.QiangGouDaleiBean;
import com.yusong.club.ui.shoppers.bean.ShopDetailsBean;
import com.yusong.club.ui.shoppers.bean.SpecificationBean;
import com.yusong.club.ui.shoppers.bean.TuiJianBean;
import com.yusong.club.ui.shoppers.used.bean.MyUsedBean;
import com.yusong.club.ui.shoppers.used.bean.UsedBean;
import com.yusong.club.ui.shoppers.used.bean.UsedCommentBean;
import com.yusong.club.ui.supermarket.entity.SMCommodityBean;
import com.yusong.club.ui.supermarket.entity.SuperMarketDetailsBean;
import com.yusong.club.ui.tenement.entity.TenementBean;
import com.yusong.club.ui.visitor.entity.CardDetailsBean;
import com.yusong.club.ui.visitor.entity.CommuntitySetingBean;
import com.yusong.club.ui.visitor.entity.OwnerInfo;
import com.yusong.club.ui.visitor.entity.ThroughCardBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by quaner on 16/12/30.
 */
public interface CommonService {
    @GET
    Observable<ResponseBody> loadString(@Url String url);

    @GET
    @Streaming
    Observable<ResponseBody> download(@Url String url);

    // ----------------------------首页-------------------------------
    //登录
    @GET("/api/v1/basic/customer/login")
    Observable<HttpResult<LoginResult>> login(@Query("mobile") String mobile, @Query("password") String password,
                                              @Query("agentId") int agentId);

    //获取验证码
    @FormUrlEncoded
    @POST("/api/v1/basic/auth_code/get")
    Observable<HttpResult<AuthCodeResult>> getAuthCode(@Field("mobile") String mobile, @Field("agentId") int agentId);

    //检测客户手机号是否存在
    @POST("/api/v1/basic/customer/mobile_exist")
    Observable<HttpResult<ExistsResult>> exists(@Body ExistsRequest existsRequest);

    //忘记密码
    @FormUrlEncoded
    @POST("/api/v1/basic/customer/forget_password")
    Observable<HttpResult> revisePwd(@Field("mobile") String mobile, @Field("password") String password, @Field("authCode") String authCode,
                                     @Field("agentId") int agentId);

    //注册账号
    @FormUrlEncoded
    @POST("/api/v1/basic/customer/register")
    Observable<HttpResult> register(@Field("mobile") String mobile, @Field("password") String password, @Field("authCode") String authCode,
                                    @Field("agentId") int agentId);

    //获取轮播图
    @FormUrlEncoded
    @POST("/api/v1/basic/agent/image_list")
    Observable<HttpResult<List<String>>> getBanner(@Field("token") String token);


    //查询基本设置
    @GET("/api/v1/basic/agent/setting")
    Observable<HttpResult<Protocol>> queryProtocol(@Query("agentId") int agentId);

    //同步环信信息
    @GET("/api/v1/basic/customer/sync_account_to_im_server")
    Observable<HttpResult> syncIM(@Query("token") String token, @Query("password") String password);

    // ----------------------------快递柜-------------------------------

    //查询物品类型
    @GET("/api/v1/ykdg/dict_item/list")
    Observable<HttpResult<List<String>>> getType(@Query("token") String token,
                                                 @Query("dictType") int dictType);


    //添加联系人
    @FormUrlEncoded
    @POST("/api/v1/ykdg/customer/contact/create")
    Observable<HttpResult<Contact>> addContact(@Field("token") String token, @Field("contactName") String contactName,
                                               @Field("province") String province, @Field("city") String city,
                                               @Field("district") String district, @Field("mobile") String mobile,
                                               @Field("street") String street, @Field("favoriteSenderFlag") int favoriteSenderFlag,
                                               @Field("favoriteReceiverFlag") int favoriteReceiverFlag);

    //查询常用联系人
    @GET("/api/v1/ykdg/customer/contact/list")
    Observable<HttpResult<List<ContactGroup>>> queryContact(@Query("token") String token, @Query("type") int type);

    //修改常用联系人
    @FormUrlEncoded
    @POST("/api/v1/ykdg/customer/contact/update")
    Observable<HttpResult> updateContact(@Field("token") String token, @Field("id") int id, @Field("contactName") String contactName,
                                         @Field("province") String province, @Field("city") String city,
                                         @Field("district") String district, @Field("mobile") String mobile,
                                         @Field("street") String street, @Field("favoriteSenderFlag") int favoriteSenderFlag,
                                         @Field("favoriteReceiverFlag") int favoriteReceiverFlag);

    //删除常用联系人
    @GET("/api/v1/ykdg/customer/contact/delete")
    Observable<HttpResult> deleteContact(@Query("token") String token,
                                         @Query("id") int id);

    //默认联系人
    @GET("/api/v1/ykdg/customer/contact/favorite")
    Observable<HttpResult> dftContact(@Query("token") String token,
                                      @Query("id") int id, @Query("type") int type);

    //提交寄件订单
    @FormUrlEncoded
    @POST("/api/v1/ykdg/customer/send_order/create")
    Observable<HttpResult<CommitOrderResult>> commitSenderOrder
    (@Field("token") String token, @Field("companyId") int id, @Field("thing") String thing, @Field("sender") String sender,
     @Field("senderMobile") String senderMobile, @Field("senderProvince") String senderProvince, @Field("senderCity") String senderCity,
     @Field("senderDistrict") String senderDistrict, @Field("senderStreet") String senderStreet, @Field("receiver") String receiver,
     @Field("receiverMobile") String receiverMobile, @Field("receiverProvince") String receiverProvince,
     @Field("receiverCity") String receiverCity, @Field("receiverDistrict") String receiverDistrict,
     @Field("receiverStreet") String receiverStreet, @Field("thingAmount") int thingAmount, @Field("memo") String memo,
     @Field("charge") int charge, @Field("boxType") int boxType);

    //查询寄件订单
    @GET("/api/v1/ykdg/customer/send_order/list")
    Observable<HttpResult<List<SenderOrderInfo>>> querySenderOrder(@Query("token") String token, @Query("type") String type,
                                                                   @Query("offset") int offset, @Query("limit") int limit);

    //查询存包订单
    @GET("/api/v1/ykdg/customer/store_order/list")
    Observable<HttpResult<List<SaveOrderInfo>>> querySaveOrder(@Query("token") String token, @Query("type") String type,
                                                               @Query("offset") int offset, @Query("limit") int limit);

    //查询收件订单
    @GET("/api/v1/ykdg/customer/deliver_order/list")
    Observable<HttpResult<List<GetOrderInfo>>> queryGetOrder(@Query("token") String token, @Query("type") String type,
                                                             @Query("offset") int offset, @Query("limit") int limit);

    //提交存包订单
    @FormUrlEncoded
    @POST("/api/v1/ykdg/customer/store_order/create")
    Observable<HttpResult<CommitOrderResult>> commitSaveOrder(@Field("token") String token,
                                                              @Field("receiverMobile") String receiverMobile,
                                                              @Field("memo") String memo);

    //查询快递轨迹
    @FormUrlEncoded
    @POST("/Ebusiness/EbusinessOrderHandle.aspx")
    Observable<OrderLogistics> queryOrderLogistics(@FieldMap Map<String, String> params);

    //查询快递单号
    @FormUrlEncoded
    @POST("/Ebusiness/EbusinessOrderHandle.aspx")
    Observable<ScanOrder> scanOrder(@FieldMap Map<String, String> params);

    //查询寄件的配置信息
    @GET("/api/v1/basic/agent/config_info")
    Observable<HttpResult<ConfigInfo>> queryConfig(@Query("token") String token);

    //查询寄件资费
    @GET("/api/v1/ykdg/site/charge")
    Observable<HttpResult<RatesInfo>> queryRates(@Query("token") String token, @Query("companyId") int companyId,
                                                 @Query("fromProvince") int fromProvinceId, @Query("fromCity") int fromCityId,
                                                 @Query("toProvince") int toProvinceId, @Query("toCity") int toCityId, @Query("boxType") int boxType);

    //查询联系人明细
    @GET("/api/v1/ykdg/customer/contact/detail")
    Observable<HttpResult<ContactGroup>> queryContactDetail(@Query("token") String token,
                                                            @Query("id") int id);

    //二维码扫描查待取订单（ 开箱 ）
    @GET("/api/v1/ykdg/customer/order/find_wait_take_list")
    Observable<HttpResult<OpenBoxOrder>> queryTerminalCode(@Query("token") String token,
                                                           @Query("terminalCode") String terminalCode);

    //远程开箱 取件
    @GET("/api/v1/ykdg/customer/order/take_by_order_id")
    Observable<HttpResult> openBox(@Query("token") String token,
                                   @Query("type") int type, @Query("id") String id);

    //查询附近柜子
    @GET("/api/v1/ykdg/terminal/nearest")
    Observable<HttpResult<List<NearbyBox>>> queryNearestBox(@Query("token") String token,
                                                            @Query("lng") float lng, @Query("lat") float lat);

    //查询寄件详情
    @GET("/api/v1/ykdg/customer/send_order/detail")
    Observable<HttpResult<SenderDetails>> querySenderDetails(@Query("token") String token,
                                                             @Query("id") String id);

    //查询收件详情
    @GET("/api/v1/ykdg/customer/deliver_order/detail")
    Observable<HttpResult<GetDetails>> queryGetDetails(@Query("token") String token,
                                                       @Query("id") String id);

    //查询收件详情
    @GET("/api/v1/ykdg/customer/send_order/cancel")
    Observable<HttpResult> cancleOrder(@Query("token") String token,
                                       @Query("id") String id);

    //存包余额支付
    @FormUrlEncoded
    @POST("/api/v1/ykdg/customer/store_order/balance_pay")
    Observable<HttpResult> saveZlPay(@Field("token") String token,
                                     @Field("orderId") String id);

    //存包支付宝支付
    @FormUrlEncoded
    @POST("/api/v1/ykdg/customer/store_order/alipay_pay")
    Observable<HttpResult<ZhiFuBaoPayBean>> saveZfbPay(@Field("token") String token,
                                                       @Field("orderId") String id);

    //存包微信支付
    @FormUrlEncoded
    @POST("/api/v1/ykdg/customer/store_order/weixin_pay")
    Observable<HttpResult<WeiXinPayBean>> saveWxPay(@Field("token") String token,
                                                    @Field("orderId") String id);

    //寄件余额支付
    @FormUrlEncoded
    @POST("/api/v1/ykdg/customer/send_order/balance_pay")
    Observable<HttpResult> senderZlPay(@Field("token") String token,
                                       @Field("orderId") String id);

    //寄件支付宝支付
    @FormUrlEncoded
    @POST("/api/v1/ykdg/customer/send_order/alipay_pay")
    Observable<HttpResult<ZhiFuBaoPayBean>> senderZfbPay(@Field("token") String token,
                                                         @Field("orderId") String id);

    //寄件微信支付
    @FormUrlEncoded
    @POST("/api/v1/ykdg/customer/send_order/weixin_pay")
    Observable<HttpResult<WeiXinPayBean>> senderWxPay(@Field("token") String token,
                                                      @Field("orderId") String id);

    //微信支付超时费用
    @GET("/api/v1/ykdg/customer/order/weixin_pay_timeout_charge")
    Observable<HttpResult<WeiXinPayBean>> weixinPayTimeoutCharge(@Query("token") String token, @Query("type") int type, @Query("id") String id);

    //支付宝支付超时费用
    @GET("/api/v1/ykdg/customer/order/alipay_pay_timeout_charge")
    Observable<HttpResult<ZhiFuBaoPayBean>> zfbPayTimeoutCharge(@Query("token") String token, @Query("type") int type, @Query("id") String id);

    //订单超时费用
    @GET("/api/v1/ykdg/customer/order/query_timeout_charge_info")
    Observable<HttpResult<RatesInfo>> queryTimeoutCharge(@Query("token") String token, @Query("type") int type, @Query("id") String id);

    //余额支付超时费用
    @GET("/api/v1/ykdg/customer/order/balance_pay_timeout_charge")
    Observable<HttpResult<RatesInfo>> balancePayTimeoutCharge(@Query("token") String token, @Query("type") int type, @Query("id") String id);


    //存包详情
    @GET("/api/v1/ykdg/customer/store_order/detail")
    Observable<HttpResult<SaveDetails>> saveDetail(@Query("token") String token,
                                                   @Query("id") String id);

    // 查询轮播图片
    @FormUrlEncoded
    @POST("/api/v1/ykdg/ad_image/list")
    Observable<HttpResult<List<String>>> queryKdgBanner(@Field("token") String token);
    // ----------------------------充电桩---------------------------------

    // 查询轮播图片
    @FormUrlEncoded
    @POST("/api/v1/charger/ad_image/list")
    Observable<HttpResult<List<String>>> queryBanner(@Field("token") String token);

    // 查询附近桩点
    @FormUrlEncoded
    @POST("/api/v1/charger/charger/nearest")
    Observable<HttpResult<List<NearbyBean>>> queryNearby(@Field("lng") double lng, @Field("lat") double lat, @Field("type") String type, @Field("keyword") String keyword, @Field("token") String token);

    //查询充电收费标准
    @FormUrlEncoded
    @POST("/api/v1/charger/charger_price/list")
    Observable<HttpResult<List<FetchMoneyBean>>> queryFetchMoney(@Field("token") String token, @Field("type") int type);

    // 查询移动救援   orderBy string "price" or "star"
    @FormUrlEncoded
    @POST("/api/v1/charger/rescuer/list")
    Observable<HttpResult<List<MoveSosBean>>> queryMoveSos(@Field("token") String token, @Field("orderBy") String orderBy);

    // 扫描充电桩
    @FormUrlEncoded
    @POST("/api/v1/charger/charger/scan_code")
    Observable<HttpResult<List<SancContentBean>>> querSancContent(@Field("token") String token, @Field("code") String code);

    // 开始充电
    @FormUrlEncoded
    @POST("/api/v1/charger/charger_order/start")
    Observable<HttpResult> startCharge(@Field("token") String token, @Field("chargerId") String chargerId, @Field("orderId") String orderId);

    // 查询订单明细
    @FormUrlEncoded
    @POST("/api/v1/charger/charger_order/detail")
    Observable<HttpResult<OrderDetailsBean>> queryOrderDetails(@Field("token") String token, @Field("orderId") String orderId);

    // 订单余额支付
    @FormUrlEncoded
    @POST("/api/v1/charger/charger_order/balance_pay")
    Observable<HttpResult> yuEPay(@Field("token") String token, @Field("orderId") String orderId, @Field("price") float price, @Field("volume") int volume);

    // 支付宝支付
    @FormUrlEncoded
    @POST("/api/v1/charger/charger_order/alipay_pay")
    Observable<HttpResult<ZhiFuBaoPayBean>> zhiFuBaoPay(@Field("token") String token, @Field("orderId") String orderId, @Field("price") float price, @Field("volume") int volume);

    // 微信支付
    @FormUrlEncoded
    @POST("/api/v1/charger/charger_order/weixin_pay")
    Observable<HttpResult<WeiXinPayBean>> weiXinPay(@Field("token") String token, @Field("orderId") String orderId, @Field("price") float price, @Field("volume") int volume);

    //我的订单
    @FormUrlEncoded
    @POST("/api/v1/charger/charger_order/my")
    Observable<HttpResult<List<MyOrderBean>>> queryMyOrder(@Field("token") String token, @Field("type") String type, @Field("offset") int offset,
                                                           @Field("limit") int limit);


    //预约订单
    @FormUrlEncoded
    @POST("/api/v1/charger/charger_order/bespeak")
    Observable<HttpResult<String>> queryBespeak(@Field("token") String token, @Field("chargerId") String chargerId,
                                                @Field("duration") int duration, @Field("price") float price, @Field("volume") int volume);

    //创建订单
    @FormUrlEncoded
    @POST("/api/v1/charger/charger_order/create")
    Observable<HttpResult<CreatOrderBean>> createOrder(@Field("token") String token, @Field("chargerId") String chargerId,
                                                       @Field("duration") int duration, @Field("price") float price, @Field("volume") int volume);

    //扫码查询充电桩详情
    @FormUrlEncoded
    @POST("/api/v1/charger/charger/detail")
    Observable<HttpResult<List<NearbyBean>>> scanQueryChargeDetails(@Field("token") String token, @Field("chargerId") String chargerId);

    //取消订单
    @FormUrlEncoded
    @POST("/api/v1/charger/charger_order/cancel")
    Observable<HttpResult> cancelOrder(@Field("token") String token, @Field("orderId") String orderId);

    //查询设置信息
    @FormUrlEncoded
    @POST("/api/v1/charger/charger_setting/info")
    Observable<HttpResult<SettingBean>> querySeting(@Field("token") String token);

    //查询桩点详情
    @FormUrlEncoded
    @POST("/api/v1/charger/charger/detail")
    Observable<HttpResult<NearbyBean>> queryChargeDetails(@Field("token") String token, @Field("chargerId") String chargerId);

    //********************************社区模块********************************************************
    //查询含有社区城市列表
    @FormUrlEncoded
    @POST("/api/v1/club/club/city_list")
    Observable<HttpResult<List<HaveCommunityCity>>> queryCityList(@Field("token") String token);

    //查询附近的社区
    @FormUrlEncoded
    @POST("/api/v1/club/club/nearest")
    Observable<HttpResult<List<Community>>> queryNearbyCommuity(@Field("token") String token,
                                                                @Field("areaId") int areaId,
                                                                @Field("baiduAreaId") int baiduAreaId,
                                                                @Field("lat") double lat,
                                                                @Field("lng") double lng);

    //设置当前社区
    @FormUrlEncoded
    @POST("/api/v1/club/customer/update_community")
    Observable<HttpResult<SetCommunity>> setCurrentCommunity(@Field("token") String token,
                                                             @Field("communityId") int communityId
    );

    //查询帖子分类
    @FormUrlEncoded
    @POST("/api/v1/club/post_category/list")
    Observable<HttpResult<List<PostsCatogrey>>> queryPostsCategory(@Field("token") String token);

    //查询帖子列表
    @FormUrlEncoded
    @POST("/api/v1/club/post/list")
    Observable<HttpResult<List<Posts>>> queryPostsList(@Field("token") String token,
                                                       @Field("categoryId") int categoryId,
                                                       @Field("limit") int limit,
                                                       @Field("offset") int offset);//查询帖子列表

    //查询帖子评论
    @FormUrlEncoded
    @POST("/api/v1/club/post_comment/list")
    Observable<HttpResult<List<PostComment>>> queryPostComment(@Field("token") String token, @Field("postId") int postId,
                                                               @Field("offset") int offset, @Field("limit") int limit);

    //点赞
    @FormUrlEncoded
    @POST("/api/v1/club/post/incr_decr_support")
    Observable<HttpResult<PostSupport>> likePost(@Field("token") String token,
                                                 @Field("postId") int postId);


    //查询新闻列表
    @FormUrlEncoded
    @POST("/api/v1/club/news/list")
    Observable<HttpResult<Contact>> queryNewsList(@Field("token") String token,
                                                  @Field("offset") int offset,
                                                  @Field("limit") int limit);

    //查询新闻内容
    @FormUrlEncoded
    @POST("/api/v1/post/club/incr_decr_support")
    Observable<HttpResult<Contact>> queryNewsDetail(@Field("token") String token, @Field("newsId") int newsId);

    //提交(创建)帖子评论
    @FormUrlEncoded
    @POST("/api/v1/club/post_comment/create")
    Observable<HttpResult<Contact>> createPostComment(@Field("token") String token,
                                                      @Field("postId") int postId, @Field("content") String content);


    //新建帖子
    @Multipart
    @POST("/api/v1/club/post/create")
    Observable<HttpResult<Contact>> createPost(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                               @Part("content") RequestBody content,
                                               @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                               @Part MultipartBody.Part image3, @Part MultipartBody.Part image4);    //新建帖子

    @Multipart
    @POST("/api/v1/club/post/create")
    Observable<HttpResult<Contact>> createPost(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                               @Part("content") RequestBody content,
                                               @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                               @Part MultipartBody.Part image3);

    @Multipart
    @POST("/api/v1/club/post/create")
    Observable<HttpResult<Contact>> createPost(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                               @Part("content") RequestBody content,
                                               @Part MultipartBody.Part image1, @Part MultipartBody.Part image2);

    @Multipart
    @POST("/api/v1/club/post/create")
    Observable<HttpResult<Contact>> createPost(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                               @Part("content") RequestBody content,
                                               @Part MultipartBody.Part image1);

    @Multipart
    @POST("/api/v1/club/post/create")
    Observable<HttpResult<Contact>> createPost(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                               @Part("content") RequestBody content);

    // ********************************我的模块(meFregment)********************************************************
    //查询个人信息
    @FormUrlEncoded
    @POST("/api/v1/basic/customer/info")
    Observable<HttpResult<UserInfo>> queryUserInfo(@Field("token") String token);

    //修改个人信息
    @Multipart
    @POST("/api/v1/basic/customer/update_info")
    Observable<HttpResult<UserInfo>> updateUserInfo(@Part("token") RequestBody token,
                                                    @Part("nickname") RequestBody nickname,
                                                    @Part("realName") RequestBody realName,
                                                    @Part("gender") RequestBody gender,
                                                    @Part("mail") RequestBody mail,
                                                    @Part MultipartBody.Part portrait);

    //修改密码
    @FormUrlEncoded
    @POST("/api/v1/basic/customer/update_password")
    Observable<HttpResult<String>> updatePassword(@Field("token") String token,
                                                  @Field("oldPassword") String oldPassword,
                                                  @Field("newPassword") String newPassword);

    //意见反馈
    @FormUrlEncoded
    @POST("/api/v1/basic/feedback/create")
    Observable<HttpResult<String>> feedbackCreate(@Field("token") String token,
                                                  @Field("content") String content);

    //应用评分
    @FormUrlEncoded
    @POST("/api/v1/basic/app_grade/create")
    Observable<HttpResult<String>> app_gradeCreate(@Field("token") String token,
                                                   @Field("grade") float grade);

    //签到
    @FormUrlEncoded
    @POST("/api/v1/basic/customer/sign_in")
    Observable<HttpResult<String>> signIn(@Field("token") String token);


    //查询余额
    @GET("/api/v1/basic/customer/balance")
    Observable<HttpResult<MoneyList>> queryBalance(@Query("token") String token, @Query("customerId") String customerId);


    @GET("/api/v1/basic/customer/in_out_money/list")
    Observable<HttpResult<List<MoneyList>>> queryMoneyList(@Query("token") String token, @Query("customerId") int customerId,
                                                           @Query("offset") int offset, @Query("limit") int limit);

    //用户充值 支付宝
    @GET("/api/v1/basic/customer_deposit_order/create_alipay")
    Observable<HttpResult<ZhiFuBaoPayBean>> zfbRecharge(@Query("token") String token, @Query("money") int money);

    //用户充值 微信
    @GET("/api/v1/basic/customer_deposit_order/create_weixin")
    Observable<HttpResult<WeiXinPayBean>> wxRecharge(@Query("token") String token, @Query("money") int money);

    //充值优惠列表
    @GET("/api/v1/basic/customer_deposit_gift/list")
    Observable<HttpResult<List<MoneyList>>> queryPreferenceList(@Query("token") String token);

    // ********************************推送功能********************************************************
//    //提交百度推送绑定设备
//    @FormUrlEncoded
//    @POST("/api/v1/basic/customer/baidu_push")
//    Observable<HttpResult<String>> baidu_pushBind(@Field("token") String token,
//                                                  @Field("userId") String userId,
//                                                  @Field("channelId") String channelId,
//                                                  @Field("clientType") int clientType);

    // ********************************学校模块********************************************************

    //查询角色列表
    @FormUrlEncoded
    @POST("/api/v1/school/role/list")
    Observable<HttpResult<Role>> roleList(@Field("token") String token);

    //查询学校列表
    @FormUrlEncoded
    @POST("/api/v1/school/school/list")
    Observable<HttpResult<List<School>>> schoolList(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit);

    //查询班级列表
    @FormUrlEncoded
    @POST("/api/v1/school/clazz/list")
    Observable<HttpResult<List<Clazz>>> clazzList(@Field("token") String token, @Field("schoolId") int schoolId, @Field("offset") int offset, @Field("limit") int limit);

    //查询学校简介（设置）
    @FormUrlEncoded
    @POST("/api/v1/school/school/setting")
    Observable<HttpResult<SchoolIntro>> querySchoolIntro(@Field("token") String token);

    //广告轮播图
    @FormUrlEncoded
    @POST("/api/v1/school/ad_image/list")
    Observable<HttpResult<List<String>>> bannerList(@Field("token") String token);

    //进入角色
    @FormUrlEncoded
    @POST("/api/v1/school/customer/select_role")
    Observable<HttpResult<String>> selectRole(@Field("token") String token, @Field("schoolId") int schoolId, @Field("id") int roleId, @Field("type") int type);

    //查询角色信息(管理员)
    @FormUrlEncoded
    @POST("/api/v1/school/manager/info")
    Observable<HttpResult<SchoolManager>> querySchoolManagerInfo(@Field("token") String token, @Field("roleId") Integer roleId);

    //查询学校公告(管理员)
    @FormUrlEncoded
    @POST("/api/v1/school/manager_public_notice/list")
    Observable<HttpResult<List<Notice>>> public_noticeList(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit);//查询学校公告(管理员)

    //删除学s校公告(管理员)
    @FormUrlEncoded
    @POST("/api/v1/school/manager_public_notice/delete")
    Observable<HttpResult<String>> deleteNotice(@Field("token") String token, @Field("id") int id); //删除学s校公告(管理员)

    //新建公告(管理员)
    @FormUrlEncoded
    @POST("/api/v1/school/manager_public_notice/create")
    Observable<HttpResult<String>> addNotice(@Field("token") String token, @Field("title") String title,
                                             @Field("content") String content, @Field("noticeType") int noticeType, @Field("publishRange") int publishRange);

    //发送通知公告阅读次数
    @FormUrlEncoded
    @POST("/api/v1/school/public_notice/add_read_count")
    Observable<HttpResult<ReadCount>> addReadCount(@Field("token") String token, @Field("noticeId") int noticeId);

    //查询角色申请列表(管理员)
    @FormUrlEncoded
    @POST("/api/v1/school/manager_account_apply/list")
    Observable<HttpResult<List<ApplyRole>>> queryRoleApplyList(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit);

    //审核老师家长申请（管理员）
    @FormUrlEncoded
    @POST("/api/v1/school/manager_account_apply/audit")
    Observable<HttpResult<String>> auditRoleApply(@Field("token") String token, @Field("applyId") int applyId, @Field("auditMemo") String auditMemo, @Field("status") int status);

    //提醒管理员审核(申请人)
    @FormUrlEncoded
    @POST("/api/v1/school/manager/notice_audit")
    Observable<HttpResult<String>> noticAudit(@Field("token") String token, @Field("applyId") int applyId);

    //查询学校通讯录
    @FormUrlEncoded
    @POST("/api/v1/school/school_tel_book/list")
    Observable<HttpResult<List<TelBookData>>> schoolTelBookList(@Field("token") String token);

    //查询学校通讯录
    @FormUrlEncoded
    @POST("/api/v1/school/school_tel_book/list2")
    Observable<HttpResult<List<AddressBean>>> schoolTelBookList2(@Field("token") String token);

    //申请老师
    @Multipart
    @POST("/api/v1/school/account_apply/create_teacher_apply")
    Observable<HttpResult<String>> teacherApply(@Part("token") RequestBody token,
                                                @Part("schoolId") RequestBody schoolId,
                                                @Part("clazzId") RequestBody clazzId,
                                                @Part("userName") RequestBody userName,
                                                @Part("applyRole") RequestBody applyRole,
                                                @Part("idCard") RequestBody idCard,
                                                @Part("applyMemo") RequestBody applyMemo,
                                                @Part MultipartBody.Part photo);

    //申请家长
    @Multipart
    @POST("/api/v1/school/account_apply/create_guardian_apply")
    Observable<HttpResult<String>> guardianApply(@Part("token") RequestBody token,
                                                 @Part("schoolId") RequestBody schoolId,
                                                 @Part("clazzId") RequestBody clazzId,
                                                 @Part("userName") RequestBody userName,
                                                 @Part("applyRole") RequestBody applyRole,
                                                 @Part("idCard") RequestBody idCard,
                                                 @Part("studentName") RequestBody studentName,
                                                 @Part("studentNo") RequestBody studentNo,
                                                 @Part("relation") RequestBody relation,
                                                 @Part("applyMemo") RequestBody applyMemo,
                                                 @Part MultipartBody.Part photo);


    //查询课程表
    @FormUrlEncoded
    @POST("/api/v1/school/timetable/detail")
    Observable<HttpResult<List<SubjectTable>>> timeTableList(@Field("token") String token);


    //14查询老师列表
    @FormUrlEncoded
    @POST("/api/v1/school/clazz/teacher_list")
    Observable<HttpResult<List<AllTeacher>>> searchTeacherList(@Field("token") String token);

    //查询当日班级作业
    @FormUrlEncoded
    @POST("/api/v1/school/home_work/day")
    Observable<HttpResult<List<ClassHomework>>> searchClassHomework(@Field("token") String token, @Field("day") String day);

    //查询历史班级作业
    @FormUrlEncoded
    @POST("/api/v1/school/home_work/day")
    Observable<HttpResult<List<ClassHomework>>> searchHistoryHomework(@Field("token") String token, @Field("day") String day);

    //查询科目列表
    @FormUrlEncoded
    @POST("/api/v1/school/course/list")
    Observable<HttpResult<List<Course>>> searchCourseList(@Field("token") String token);

    //   查询班级相册列表(老师,家长)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_photo_album/list")
    Observable<HttpResult<List<PhotoAlbum>>> queryPhotoAlbumList(@Field("token") String token,
                                                                 @Field("offset") int offset, @Field("limit") int limit);

    //   查询班级视册列表
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_video_album/list")
    Observable<HttpResult<List<VideoAlbum>>> queryVideoAlbumList(@Field("token") String token,
                                                                 @Field("offset") int offset, @Field("limit") int limit);

    //新建相册(老师,家长)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_photo_album/create")
    Observable<HttpResult<Result>> createPhoto_album(@Field("token") String token, @Field("albumName") String albumName, @Field("memo") String memo);

    //新建视册(老师,家长)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_video_album/create")
    Observable<HttpResult<Result>> createVideo_album(@Field("token") String token, @Field("albumName") String albumName, @Field("memo") String memo);

    //上传图片到图册(老师,家长)
    @Multipart
    @POST("/api/v1/school/clazz_photo_album/upload_file")
    Observable<HttpResult<String>> uploadPhotoFile(@Part("token") RequestBody token,
                                                   @Part("albumId ") RequestBody albumId,
                                                   @Part MultipartBody.Part image1);

    //上传视频到视册(老师,家长)
    @Multipart
    @POST("/api/v1/school/clazz_video_album/upload_file")
    Observable<HttpResult<String>> uploadVideoFile(@Part("token") RequestBody token,
                                                   @Part("albumId ") RequestBody albumId,
                                                   @Part MultipartBody.Part image1);

    //删除图册到图片(老师,家长)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_photo_album/delete_photo")
    Observable<HttpResult<String>> deletePhoto(@Field("token") String token, @Field("photoIds") String photoIds, @Field("albumId") int albumId);

    //删除视册到视频(老师,家长)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_video_album/delete_video")
    Observable<HttpResult<String>> deleteVideo(@Field("token") String token, @Field("videoIds") String videoIds, @Field("albumId") int albumId);

    // 删除图册
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_photo_album/delete")
    Observable<HttpResult<String>> deleteAllPhoto(@Field("token") String token, @Field("albumId") int albumId);

    // 删除视册
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_video_album/delete")
    Observable<HttpResult<String>> deleteAllVideo(@Field("token") String token, @Field("albumId") int albumId);

    //查询学习视频(家长,老师)
    @FormUrlEncoded
    @POST("/api/v1/school/study_video/list")
    Observable<HttpResult<List<StudyVideo>>> studyVideoList(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit);

    //    30-查询班训录(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_tel_book/list")
    Observable<HttpResult<TelBook>> telBookList(@Field("token") String token);

    //   31-查询群组(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_chat_group/list")
    Observable<HttpResult<ChatGroup>> chatGroupList(@Field("token") String token);

    //   32-查询群组成员(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_chat_group/member_list")
    Observable<HttpResult<List<MemberGroup>>> memberGroupList(@Field("token") String token, @Field("groupId") int groupId);

    //    33-查询活动分类(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_activity_category/list")
    Observable<HttpResult<List<HuoType>>> categoryList(@Field("token") String token);

    //     34-查询活动列表(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_activity/list")
    Observable<HttpResult<List<ActivityBean>>> activityList(@Field("token") String token, @Field("categoryId") int categoryId, @Field("offset") int offset, @Field("limit") int limit);

    //    35-新建活动(家长,老师)
    @Multipart
    @POST("/api/v1/school/clazz_activity/create")
    Observable<HttpResult<String>> createAction(@Part("token") RequestBody token,
                                                @Part("categoryId") RequestBody categoryId,
                                                @Part("activityName") RequestBody activityName,
                                                @Part("beginTime") RequestBody beginTime,
                                                @Part("endTime") RequestBody endTime,
                                                @Part("memo") RequestBody memo,
                                                @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                                @Part MultipartBody.Part image3, @Part MultipartBody.Part image4);

    //36-参与活动(家长,老师)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_activity/join")
    Observable<HttpResult<JoinedResult>> joinActy(@Field("token") String token, @Field("activityId") int activityId);

    //36-查询活动评论(所有)/api/v1/school/clazz_activity_comment/list
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_activity_comment/list")
    Observable<HttpResult<List<Comment>>> searchCommentList(@Field("token") String token, @Field("activityId") int activityId, @Field("offset") int offset, @Field("limit") int limit);

    //37-创建活动评论(家长,老师)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_activity_comment/create")
    Observable<HttpResult<String>> createComment(@Field("token") String token, @Field("activityId") int activityId, @Field("content") String content);

    //38-查询学生评价列表(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/student_period_comment/list")
    Observable<HttpResult<List<StudentComment>>> searchStuCommentList(@Field("token") String token, @Field("period") int period, @Field("offset") int offset, @Field("limit") int limit);

    //  40-查询学生列表(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/student/list")
    Observable<HttpResult<List<StudentList>>> searchStudentList(@Field("token") String token, @Field("clazzId") int clazzId, @Field("offset") int offset, @Field("limit") int limit);

    //  46-查询我的角色审批列表(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/customer/my_apply_list")
    Observable<HttpResult<List<ApplyList>>> searchApplyList(@Field("token") String token, @Field("schoolId") int schoolId, @Field("offset") int offset, @Field("limit") int limit);

    //    51-活动新建或者取消点赞(所有人)

    @FormUrlEncoded
    @POST("/api/v1/school/clazz_activity_support/create_or_delete")
    Observable<HttpResult<GoodAction>> getGoodAction(@Field("token") String token, @Field("activityId") int activityId);

    @FormUrlEncoded
    @POST("/api/v1/school/teacher/info")
    Observable<HttpResult<UserInfoDetails>> queryTeacharInfo(@Field("token") String token, @Field("roleId") int id);

    @FormUrlEncoded
    @POST("/api/v1/school/student/info")
    Observable<HttpResult<UserInfoDetails>> queryStudentInfo(@Field("token") String token, @Field("roleId") int id);

    @FormUrlEncoded
    @POST("/api/v1/school/guardian/info")
    Observable<HttpResult<UserInfoDetails>> queryParentInfo(@Field("token") String token, @Field("roleId") Integer id);


    //    56-查询相册所有照片(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_photo_album/photo_list")
    Observable<HttpResult<PhotoAlbum>> getClazzPhotoList(@Field("token") String token, @Field("albumId") int albumId);

    //   57-查询视册所有视频(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_video_album/video_list")
    Observable<HttpResult<VideoAlbum>> getClazzVideoList(@Field("token") String token, @Field("albumId") int albumId);

    //    58-查询活动详情(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_activity/detail_main")
    Observable<HttpResult<ActionDetail>> getActionDeatail(@Field("token") String token, @Field("activityId") int activityId);

    //    59-查询活动详情评论(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_activity/detail_comment")
    Observable<HttpResult<List<DetailComment>>> getDetailComment(@Field("token") String token, @Field("activityId") int activityId, @Field("offset") int offset, @Field("limit") int limit);

    //    65-编辑相册(有权限的人)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_photo_album/edit")
    Observable<HttpResult<EditSucessResult>> editPhoto(@Field("token") String token, @Field("albumId") int albumId, @Field("memo") String memo, @Field("albumName") String albumName);

    //    66-相册最新照片
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_photo_album/newest_photo_list")
    Observable<HttpResult<List<LatestPhoto>>> getLatestPhto(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit);


    //<-------------------------------------------------老师--------------------------------------->
    //查询公告列表模块(老师)
    @FormUrlEncoded
    @POST("/api/v1/school/teacher_public_notice/list")
    Observable<HttpResult<List<Notice>>> teacherNoticeList(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit);

    //设置值班老师
    @FormUrlEncoded
    @POST("/api/v1/school/clazz/set_duty_teacher")
    Observable<HttpResult<String>> setOndutyTeacher(@Field("token") String token, @Field("teacherId") int teacherId);

    //新增班级作业
    @FormUrlEncoded
    @POST("/api/v1/school/teacher_home_work/create_or_update")
    Observable<HttpResult<String>> createClasswork(@Field("token") String token, @Field("courseId") int courseId, @Field("content") String content, @Field("workDate") String workDate);

    // 删除学习视频(老师)
    @FormUrlEncoded
    @POST("/api/v1/school/study_video/delete")
    Observable<HttpResult<String>> deleteStudyVideo(@Field("token") String token, @Field("videoId") int videoId);

    // 39-新建学生评价(老师)
    @FormUrlEncoded
    @POST("/api/v1/school/student_period_comment/create")
    Observable<HttpResult<String>> StuCommentCreate(@Field("token") String token,
                                                    @Field("studentId") int studentId,
                                                    @Field("period") int period,
                                                    @Field("star1") int star1,
                                                    @Field("star2") int star2,
                                                    @Field("star3") int star3,
                                                    @Field("star4") int star4,
                                                    @Field("content") String content);

    //    53-查询老师信息
    @FormUrlEncoded
    @POST("/api/v1/school/teacher/info")
    Observable<HttpResult<TeacherInfo>> queryTeacherInfo(@Field("token") String token, @Field("roleId") Integer roleId);


    //    54-查询班级学生列表(所有)
    @FormUrlEncoded
    @POST("/api/v1/school/clazz/student_list")
    Observable<HttpResult<List<StuList>>> queryStuList(@Field("token") String token, @Field("studentName") String studentName, @Field("offset") int offset, @Field("limit") int limit);

    //61-查询学生请假列表(家长、老师内容不同)
    @FormUrlEncoded
    @POST("/api/v1/school/leave_apply/list")
    Observable<HttpResult<List<TeacherLeave>>> queryLeaveAplly(@Field("token") String token, @Field("status") Integer status, @Field("offset") int offset, @Field("limit") int limit);

    //
    //61-查询学生请假列表(家长、老师内容不同)
    @FormUrlEncoded
    @POST("/api/v1/school/leave_apply/list")
    Observable<HttpResult<List<TeacherLeave>>> queryLeaveApllyByDate(@Field("token") String token, @Field("status") Integer status, @Field("leaveBeginTime") String leaveBeginTime, @Field("leaveEndTime") String leaveEndTime, @Field("offset") int offset, @Field("limit") int limit);

    //    62-学生请假审批(教师)
    @FormUrlEncoded
    @POST("/api/v1/school/leave_apply/audit")
    Observable<HttpResult<String>> judgeLeave(@Field("token") String token, @Field("applyId") int applyId, @Field("auditMemo") String auditMemo, @Field("status") int status);


    //<---------------------------------------------------------------------------------------->
    //查询公告列表模块(家长)
    @FormUrlEncoded
    @POST("/api/v1/school/guardian_public_notice/list")
    Observable<HttpResult<List<Notice>>> guardianNoticeList(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit);

    //    60-新建学生请假(家长)
    @FormUrlEncoded
    @POST("/api/v1/school/leave_apply/create")
    Observable<HttpResult<String>> CreateLeaveApply(@Field("token") String token, @Field("studentName") String studentName,
                                                    @Field("reason") String reason, @Field("beginTime") String beginTime,
                                                    @Field("endTime") String endTime);

    //新建提前放学
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_over_remind/create")
    Observable<HttpResult<String>> CreateAheadAfterSchool(@Field("token") String token, @Field("overTime") String overTime
            , @Field("sunday") int sunday, @Field("monday") int monday, @Field("tuesday") int tuesday, @Field("wednesday") int wednesday
            , @Field("thursday") int thursday, @Field("friday") int friday, @Field("saturday") int saturday
    );

    //查询提前放学（家长/老师）
    @FormUrlEncoded
    @POST("/api/v1/school/clazz_over_remind/detail")
    Observable<HttpResult<List<AheadBean>>> QueryAheadAfterSchool(@Field("token") String token);

    // ********************************商城模块********************************************************

    //商城轮播图
    @FormUrlEncoded
    @POST("/api/v1/mall/ad_image/list")
    Observable<HttpResult<List<String>>> shopImageList(@Field("token") String token);

    //查询抢购大类
    @FormUrlEncoded
    @POST("/api/v1/mall/grab_item_category/list")
    Observable<HttpResult<List<QiangGouBean>>> queryQiangGou(@Field("token") String token);

    //查询推荐商品分类
    @FormUrlEncoded
    @POST("/api/v1/mall/item_recommend_category/list")
    Observable<HttpResult<List<TuiJianBean>>> queryTuiJian(@Field("token") String token, @Field("type") int type);

    //查询抢购分类
    @FormUrlEncoded
    @POST("/api/v1/mall/grab_item_subcategory/list")
    Observable<HttpResult<List<QiangGouDaleiBean>>> queryQiangGouFenLei(@Field("token") String token, @Field("categoryId") int categoryId);

    //查询抢购商品列表
    @FormUrlEncoded
    @POST("/api/v1/mall/grab_item/list")
    Observable<HttpResult<List<CommodityBean>>> queryQiangGouLieBiao(@Field("token") String token, @Field("grabCategoryId") int grabCategoryId,
                                                                     @Field("itemCategoryId") int itemCategoryId,
                                                                     @Field("offset") int offset, @Field("limit") int limit);

    //查询商品明细
    @FormUrlEncoded
    @POST("/api/v1/mall/item/detail")
    Observable<HttpResult<CommodityBean>> queryShangPinDetails(@Field("token") String token, @Field("itemId") int itemId);

    //查询商品评论
    @FormUrlEncoded
    @POST("/api/v1/mall/item_comment/list")
    Observable<HttpResult<List<PinLunBean>>> queryShangPinPinLun(@Field("token") String token, @Field("itemId") int itemId,
                                                                 @Field("offset") int offset, @Field("limit") int limit);

    //查询店铺商品
    @FormUrlEncoded
    @POST("/api/v1/mall/shop/item_list")
    Observable<HttpResult<List<CommodityBean>>> queryDianPuShangPin(@Field("token") String token, @Field("categoryId") int categoryId,
                                                                    @Field("shopId") int shopId, @Field("orderBy") String orderBy, @Field("offset") int offset, @Field("limit") int limit);

    //查询店铺商品分类
    @FormUrlEncoded
    @POST("/api/v1/mall/shop/category_list")
    Observable<HttpResult<List<FenLeiBean>>> queryDianPuShangPinFenLei(@Field("token") String token, @Field("shopId") int shopId);

    //下单
    @FormUrlEncoded
    @POST("/api/v1/mall/item_order/create")
    Observable<HttpResult<CreataOrderBean>> shopCreateOrder(@Field("token") String token, @Field("deliverType") Integer deliverType, @Field("grabItemId") Integer grabItemId,
                                                            @Field("itemId") int itemId, @Field("amount") int amount,
                                                            @Field("price") double price, @Field("province") String province,
                                                            @Field("city") String city, @Field("district") String district,
                                                            @Field("street") String street, @Field("receiverMobile") String receiverMobile,
                                                            @Field("receiver") String receiver, @Field("distributionTime") String distributionTime,
                                                            @Field("leaveMessage") String leaveMessage, @Field("property") String specitication);

    //商城余额支付
    @FormUrlEncoded
    @POST("/api/v1/mall/item_order/balance_pay")
    Observable<HttpResult<String>> shopYuEPay(@Field("token") String token, @Field("orderId") String orderId);

    //商城微信支付
    @FormUrlEncoded
    @POST("/api/v1/mall/item_order/weixin_pay")
    Observable<HttpResult<WeiXinPayBean>> shopWeiXinPay(@Field("token") String token, @Field("orderId") String orderId);

    //商城支付宝支付
    @FormUrlEncoded
    @POST("/api/v1/mall/item_order/alipay_pay")
    Observable<HttpResult<ZhiFuBaoPayBean>> shopZFBPay(@Field("token") String token, @Field("orderId") String orderId);

    //商城订单
    @FormUrlEncoded
    @POST("/api/v1/mall/item_order/my")
    Observable<HttpResult<List<OrderShopBean>>> MyShopOrder(@Field("token") String token,
                                                            @Field("type") String type, @Field("offset") int offset,
                                                            @Field("limit") int limit);

    //商品评论
    @FormUrlEncoded
    @POST("/api/v1/mall/item_comment/create")
    Observable<HttpResult<String>> addPingLun(@Field("token") String token, @Field("orderId") String orderId,
                                              @Field("content") String content);

    //取消订单
    @FormUrlEncoded
    @POST("/api/v1/mall/item_order/cancel")
    Observable<HttpResult<String>> shopCancelOrder(@Field("token") String token, @Field("orderId") String orderId);

    //查询推荐商品列表
    @FormUrlEncoded
    @POST("/api/v1/mall/item_recommend/list")
    Observable<HttpResult<List<TuiJianBean.Commodity>>> queryTuijianList(@Field("token") String token,
                                                                         @Field("categoryId") int categoryId, @Field("offset") int offset, @Field("limit") int limit);

    //查询抢购商品明细
    @FormUrlEncoded
    @POST("/api/v1/mall/grab_item/detail")
    Observable<HttpResult<CommodityBean>> queryQgCommdityDetails(@Field("token") String token,
                                                                 @Field("grabItemId") int grabItemId);

    //查询可抢购商品数量
    @FormUrlEncoded
    @POST("/api/v1/mall/grab_item/limit_rest_amount")
    Observable<HttpResult<CanQGBean>> canQGSum(@Field("token") String token,
                                               @Field("grabItemId") int grabItemId);

    //查询家装分类
    @FormUrlEncoded
    @POST("/api/v1/mall/item_category/list")
    Observable<HttpResult<List<FenLeiBean>>> queryHomeFeilei(@Field("token") String token,
                                                             @Field("categoryType") int categoryType);

    //查询家装列表
    @FormUrlEncoded
    @POST("/api/v1/mall/item_category/item_list")
    Observable<HttpResult<List<CommodityBean>>> queryHomeList(@Field("token") String token,
                                                              @Field("categoryId") int categoryId,
                                                              @Field("offset") int offset, @Field("limit") int limit);

    //查询店铺详情
    @FormUrlEncoded
    @POST("/api/v1/mall/shop/detail")
    Observable<HttpResult<ShopDetailsBean>> queryShopDetails(@Field("token") String token, @Field("shopId") int shopId);

    //确认收货
    @FormUrlEncoded
    @POST("/api/v1/mall/item_order/take")
    Observable<HttpResult> affirmReceive(@Field("token") String token, @Field("orderId") String orderId);

    //申请退货
    @FormUrlEncoded
    @POST("/api/v1/mall/item_order/apply_return")
    Observable<HttpResult> reimburse(@Field("token") String token, @Field("orderId") String orderId, @Field("returnReason") String returnReason);

    //搜索商品
    @FormUrlEncoded
    @POST("/api/v1/mall/item/list")
    Observable<HttpResult<List<CommodityBean>>> findCommodity(@Field("token") String token, @Field("itemName") String itemName);

    // ******************************** 二手 ********************************************************

    //查询二手分类
    @FormUrlEncoded
    @POST("/api/v1/mall/used_goods_category/list")
    Observable<HttpResult<List<FenLeiBean>>> queryUsedFenlei(@Field("token") String token);

    //查询二手商品列表
    @FormUrlEncoded
    @POST("/api/v1/mall/used_goods_item/list")
    Observable<HttpResult<List<UsedBean>>> queryUsedList(@Field("token") String token, @Field("categoryId") int categoryId,
                                                         @Field("lng") double lng, @Field("lat") double lat,
                                                         @Field("offset") int offset, @Field("limit") int limit);

    //查询二手商品评论
    @FormUrlEncoded
    @POST("/api/v1/mall/used_goods_comment/list")
    Observable<HttpResult<List<UsedCommentBean>>> queryUsedPinlun(@Field("token") String token, @Field("itemId") int itemId,
                                                                  @Field("offset") int offset, @Field("limit") int limit);

    //查询我的二手商品
    @FormUrlEncoded
    @POST("/api/v1/mall/used_goods_item/my")
    Observable<HttpResult<List<MyUsedBean>>> queryMyUsed(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit);

    //发布二手商品
    @Multipart
    @POST("/api/v1/mall/used_goods_item/create")
    Observable<HttpResult> issueUsed(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                     @Part("introduction") RequestBody introduction,
                                     @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                     @Part MultipartBody.Part image3, @Part MultipartBody.Part image4,
                                     @Part("price") RequestBody price, @Part("showPrice") RequestBody showPrice,
                                     @Part("address") RequestBody address, @Part("lng") RequestBody lng, @Part("lat") RequestBody lat);

    //修改二手商品
    @Multipart
    @POST("/api/v1/mall/used_goods_item/update")
    Observable<HttpResult> editUsed(@Part("token") RequestBody token, @Part("id") RequestBody id,
                                    @Part("categoryId") RequestBody categoryId,
                                    @Part("introduction") RequestBody introduction,
                                    @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                    @Part MultipartBody.Part image3, @Part MultipartBody.Part image4,
                                    @Part("price") RequestBody price, @Part("showPrice") RequestBody showPrice,
                                    @Part("address") RequestBody address, @Part("lng") RequestBody lng, @Part("lat") RequestBody lat);

    //下架二手商品
    @FormUrlEncoded
    @POST("/api/v1/mall/used_goods_item/close")
    Observable<HttpResult> outUsed(@Field("token") String token, @Field("id") int id);

    //发布商品评论
    @FormUrlEncoded
    @POST("/api/v1/mall/used_goods_comment/create")
    Observable<HttpResult> usedComment(@Field("token") String token, @Field("itemId") int itemId, @Field("content") String content);

    //评论回复
    @FormUrlEncoded
    @POST("/api/v1/mall/used_goods_reply/create")
    Observable<HttpResult> replyComment(@Field("token") String token, @Field("commentId") int commentId, @Field("content") String content);

    //删除
    @FormUrlEncoded
    @POST("/api/v1/mall/used_goods_item/delete")
    Observable<HttpResult> deleteUsed(@Field("token") String token, @Field("id") int id);

    //*************************** 二期新加协议 ****************************
    //查询业主信息
    @FormUrlEncoded
    @POST("/api/v1/club/proprietor/info")
    Observable<HttpResult<OwnerInfo>> queryOwnerInfo(@Field("token") String token);

    //生成通行证
    @FormUrlEncoded
    @POST("/api/v1/club/visitor/createPermit")
    Observable<HttpResult<ThroughCardBean>> createThroughCrad(@Field("token") String token,
                                                              @Field("visitorName") String visitorName,
                                                              @Field("sex") int sex,
                                                              @Field("visitorTime") String visitorTime,
                                                              @Field("isDrive") int isDrive,
                                                              @Field("plateNumber") String plateNumber);

    //通行校验
    @FormUrlEncoded
    @POST("/api/v1/club/visitor/checkPermit")
    Observable<HttpResult<CardDetailsBean>> checkPermit(@Field("token") String token, @Field("permitCode") String permitCode);

    //报修报事发布
    @Multipart
    @POST("/api/v1/club/repair_order/create")
    Observable<HttpResult> createRepairs(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                         @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                         @Part("proprietorMobile") RequestBody proprietorMobile,
                                         @Part("proprietorAddress") RequestBody proprietorAddress,
                                         @Part("content") RequestBody content,
                                         @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                         @Part MultipartBody.Part image3, @Part MultipartBody.Part image4);

    @Multipart
    @POST("/api/v1/club/repair_order/create")
    Observable<HttpResult> createRepairs(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                         @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                         @Part("proprietorMobile") RequestBody proprietorMobile,
                                         @Part("proprietorAddress") RequestBody proprietorAddress,
                                         @Part("content") RequestBody content,
                                         @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                         @Part MultipartBody.Part image3);

    @Multipart
    @POST("/api/v1/club/repair_order/create")
    Observable<HttpResult> createRepairs(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                         @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                         @Part("proprietorMobile") RequestBody proprietorMobile,
                                         @Part("proprietorAddress") RequestBody proprietorAddress,
                                         @Part("content") RequestBody content,
                                         @Part MultipartBody.Part image1, @Part MultipartBody.Part image2);

    @Multipart
    @POST("/api/v1/club/repair_order/create")
    Observable<HttpResult> createRepairs(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                         @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                         @Part("proprietorMobile") RequestBody proprietorMobile,
                                         @Part("proprietorAddress") RequestBody proprietorAddress,
                                         @Part("content") RequestBody content,
                                         @Part MultipartBody.Part image1);

    @Multipart
    @POST("/api/v1/club/repair_order/create")
    Observable<HttpResult> createRepairs(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                         @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                         @Part("proprietorMobile") RequestBody proprietorMobile,
                                         @Part("proprietorAddress") RequestBody proprietorAddress,
                                         @Part("content") RequestBody content);

    //查询物业电话
    @FormUrlEncoded
    @POST("/api/v1/club/community_setting/info")
    Observable<HttpResult<CommuntitySetingBean>> queryCommuntiySeting(@Field("token") String token);

    //查询申报历史
    @FormUrlEncoded
    @POST("/api/v1/club/repair_order/list")
    Observable<HttpResult<List<RepairsHistoryBean>>> queryRepairsHistory(@Field("token") String token,
                                                                         @Field("proprietorId") int proprietorId,
                                                                         @Field("offset") int offset,
                                                                         @Field("limit") int limit);

    //提交表扬
    @Multipart
    @POST("/api/v1/club/evaluate/create")
    Observable<HttpResult> commitEvaluate(@Part("token") RequestBody token, @Part("categoryType") RequestBody categoryType,
                                          @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                          @Part("proprietorMobile") RequestBody proprietorMobile,
                                          @Part("proprietorAddress") RequestBody proprietorAddress,
                                          @Part("content") RequestBody content,
                                          @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                          @Part MultipartBody.Part image3, @Part MultipartBody.Part image4);

    @Multipart
    @POST("/api/v1/club/evaluate/create")
    Observable<HttpResult> commitEvaluate(@Part("token") RequestBody token, @Part("categoryType") RequestBody categoryType,
                                          @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                          @Part("proprietorMobile") RequestBody proprietorMobile,
                                          @Part("proprietorAddress") RequestBody proprietorAddress,
                                          @Part("content") RequestBody content,
                                          @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                          @Part MultipartBody.Part image3);

    @Multipart
    @POST("/api/v1/club/evaluate/create")
    Observable<HttpResult> commitEvaluate(@Part("token") RequestBody token, @Part("categoryType") RequestBody categoryType,
                                          @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                          @Part("proprietorMobile") RequestBody proprietorMobile,
                                          @Part("proprietorAddress") RequestBody proprietorAddress,
                                          @Part("content") RequestBody content,
                                          @Part MultipartBody.Part image1, @Part MultipartBody.Part image2);

    @Multipart
    @POST("/api/v1/club/evaluate/create")
    Observable<HttpResult> commitEvaluate(@Part("token") RequestBody token, @Part("categoryType") RequestBody categoryType,
                                          @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                          @Part("proprietorMobile") RequestBody proprietorMobile,
                                          @Part("proprietorAddress") RequestBody proprietorAddress,
                                          @Part("content") RequestBody content,
                                          @Part MultipartBody.Part image1);

    @Multipart
    @POST("/api/v1/club/evaluate/create")
    Observable<HttpResult> commitEvaluate(@Part("token") RequestBody token, @Part("categoryType") RequestBody categoryType,
                                          @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                          @Part("proprietorMobile") RequestBody proprietorMobile,
                                          @Part("proprietorAddress") RequestBody proprietorAddress,
                                          @Part("content") RequestBody content);

    //查询表扬历史
    @FormUrlEncoded
    @POST("/api/v1/club/evaluate/list")
    Observable<HttpResult<List<EvaluateBean>>> queryEvaluateHistory(@Field("token") String token,
                                                                    @Field("offset") int offset,
                                                                    @Field("limit") int limit);

    //物业缴费查询
    @FormUrlEncoded
    @POST("/api/v1/club/proprietor_payment/list")
    Observable<HttpResult<List<TenementBean>>> queryTenementPay(@Field("token") String token,
                                                                @Field("communityId") int communityId,
                                                                @Field("proprietorId") int proprietorId,
                                                                @Field("isPay") int isPay,
                                                                @Field("offset") int offset,
                                                                @Field("limit") int limit);

    //查询超市详情
    @FormUrlEncoded
    @POST("/api/v1/club/market_shop/detail")
    Observable<HttpResult<SuperMarketDetailsBean>> querySuperMarket(@Field("token") String token);

    //查询超市分类
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_category/list")
    Observable<HttpResult<List<FenLeiBean>>> querySuperMarketCategory(@Field("token") String token);

    //根据分类查询商品列表
    @FormUrlEncoded
    @POST("/api/v1/club/market_item/item_list")
    Observable<HttpResult<List<SMCommodityBean>>> querySMCommodity(@Field("token") String token, @Field("categoryId") int categoryId, @Field("offset") int offset, @Field("limit") int limit);


    //超市商品详情
    @FormUrlEncoded
    @POST("/api/v1/club/market_item/detail")
    Observable<HttpResult<CommodityBean>> querySMCommodityDetalis(@Field("token") String token,
                                                                  @Field("itemId") int itemId);

    //超市商品评论
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_comment/list")
    Observable<HttpResult<List<PinLunBean>>> querySMCommodityComment(@Field("token") String token,
                                                                     @Field("itemId") int itemId,
                                                                     @Field("offset") int offset,
                                                                     @Field("limit") int limit);

    //提交超市商品评论
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_comment/create")
    Observable<HttpResult> commitSMComment(@Field("token") String token, @Field("orderId") String orderId,
                                           @Field("content") String content);

    //超市下单
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_order/create")
    Observable<HttpResult<CreataOrderBean>> smCreateOrder(@Field("token") String token, @Field("deliverType") Integer deliverType,
                                                          @Field("itemId") int itemId, @Field("amount") int amount,
                                                          @Field("price") double price, @Field("province") String province,
                                                          @Field("city") String city, @Field("district") String district,
                                                          @Field("street") String street, @Field("receiverMobile") String receiverMobile,
                                                          @Field("receiver") String receiver, @Field("distributionTime") String distributionTime,
                                                          @Field("leaveMessage") String leaveMessage, @Field("property") String property);

    //查询社区公告
    @FormUrlEncoded
    @POST("/api/v1/club/community_notice/list")
    Observable<HttpResult<List<NoticeBean>>> queryCommunityNotice(@Field("token") String token, @Field("offset") int offset,
                                                                  @Field("limit") int limit);


    //查询超市订单
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_order/my")
    Observable<HttpResult<List<OrderShopBean>>> querySuperMarketOrder(@Field("token") String token, @Field("type") String type, @Field("offset") int offset,
                                                                      @Field("limit") int limit);

    //超市确认收货
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_order/take")
    Observable<HttpResult> confirmReceipt(@Field("token") String token, @Field("orderId") String orderId);

    //超市确认收货
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_order/apply_return")
    Observable<HttpResult> salesReturn(@Field("token") String token, @Field("orderId") String orderId, @Field("returnReason") String returnReason);

    //超市余额支付
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_order/balance_pay")
    Observable<HttpResult> smBalancePay(@Field("token") String token, @Field("orderId") String orderId);

    //超市微信支付
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_order/weixin_pay")
    Observable<HttpResult<WeiXinPayBean>> smWeixinPay(@Field("token") String token, @Field("orderId") String orderId);

    //超市支付宝支付
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_order/alipay_pay")
    Observable<HttpResult<ZhiFuBaoPayBean>> smZhifubaoPay(@Field("token") String token, @Field("orderId") String orderId);

    //超市取消订单
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_order/cancel")
    Observable<HttpResult> smCancelOrder(@Field("token") String token, @Field("orderId") String orderId);

    //物业缴费（智联支付)
    @FormUrlEncoded
    @POST("/api/v1/club/proprietor_payment/balance_pay")
    Observable<HttpResult> tenementBalancePay(@Field("token") String token, @Field("orderId") String orderId);

    //物业缴费(微信支付)
    @FormUrlEncoded
    @POST("/api/v1/club/proprietor_payment/weixin_pay")
    Observable<HttpResult<WeiXinPayBean>> tenementWeixinPay(@Field("token") String token, @Field("orderId") String orderId);

    //物业缴费(支付宝支付)
    @FormUrlEncoded
    @POST("/api/v1/club/proprietor_payment/alipay_pay")
    Observable<HttpResult<ZhiFuBaoPayBean>> tenementZhifubaoPay(@Field("token") String token, @Field("orderId") String orderId);


    //搜索商品
    @FormUrlEncoded
    @POST("/api/v1/club/market_item/list")
    Observable<HttpResult<List<CommodityBean>>> findSMCommodity(@Field("token") String token, @Field("itemName") String itemName);

    //免责声明
    @FormUrlEncoded
    @POST("/api/v1/basic/agent/disclaimer_info")
    Observable<HttpResult<DisclaimerBean>> queryDisclaimer(@Field("token") String token);

    //查询商品规格
    @FormUrlEncoded
    @POST("/api/v1/mall/item_property/list")
    Observable<HttpResult<List<SpecificationBean>>> queryItemSpecification(@Field("token") String token, @Field("itemId") int itemId);

    //查询商品规格（超市）
    @FormUrlEncoded
    @POST("/api/v1/club/market_item_property/list")
    Observable<HttpResult<List<SpecificationBean>>> querySMItemSpecification(@Field("token") String token, @Field("itemId") int itemId);

    //社区服务信息
    @FormUrlEncoded
    @POST("/api/v1/club/service_shop/detail")
    Observable<HttpResult<ServiceDetailBean>> queryServiceInfo(@Field("token") String token);

    //社区服务分类
    @FormUrlEncoded
    @POST("/api/v1/club/service_item_category/list")
    Observable<HttpResult<List<FenLeiBean>>> queryServiceCategory(@Field("token") String token);

    //社区服务查询
    @FormUrlEncoded
    @POST("/api/v1/club/service_item/item_list")
    Observable<HttpResult<List<ServiceBean>>> queryServiceList(@Field("token") String token, @Field("categoryId") int categoryId, @Field("offset") int offset, @Field("limit") int limit);

    //社区服务详情查询
    @FormUrlEncoded
    @POST("/api/v1/club/service_item/detail")
    Observable<HttpResult<CommodityBean>> queryServiceDetails(@Field("token") String token, @Field("itemId") int itemId);

    //社区服务查询
    @FormUrlEncoded
    @POST("/api/v1/club/service_item_comment/list")
    Observable<HttpResult<List<PinLunBean>>> queryServiceComment(@Field("token") String token, @Field("itemId") int itemId, @Field("offset") int offset, @Field("limit") int limit);

    //社区服务下单
    @FormUrlEncoded
    @POST("/api/v1/club/service_item_order/create")
    Observable<HttpResult<CreataOrderBean>> serviceCreateOrder(@Field("token") String token,
                                                               @Field("deliverType") int deliverType,
                                                               @Field("itemId") int itemId,
                                                               @Field("price") double price,
                                                               @Field("province") String province,
                                                               @Field("city") String city,
                                                               @Field("district") String district,
                                                               @Field("street") String street,
                                                               @Field("receiverMobile") String receiverMobile,
                                                               @Field("receiver") String receiver,
                                                               @Field("serviceTime") String serviceTime,
                                                               @Field("leaveMessage") String leaveMessage);

    //社区服务订单（智联支付)
    @FormUrlEncoded
    @POST("/api/v1/club/service_item_order/balance_pay")
    Observable<HttpResult> serviceBalancePay(@Field("token") String token, @Field("orderId") String orderId);

    //社区服务订单(微信支付)
    @FormUrlEncoded
    @POST("/api/v1/club/service_item_order/weixin_pay")
    Observable<HttpResult<WeiXinPayBean>> serviceWeixinPay(@Field("token") String token, @Field("orderId") String orderId);

    //社区服务订单(支付宝支付)
    @FormUrlEncoded
    @POST("/api/v1/club/service_item_order/alipay_pay")
    Observable<HttpResult<ZhiFuBaoPayBean>> serviceZhifubaoPay(@Field("token") String token, @Field("orderId") String orderId);

    //搜索商品
    @FormUrlEncoded
    @POST("/api/v1/club/service_item/list")
    Observable<HttpResult<List<CommodityBean>>> findService(@Field("token") String token,
                                                            @Field("itemName") String itemName);

    //查询服务订单
    @FormUrlEncoded
    @POST("/api/v1/club/service_item_order/my")
    Observable<HttpResult<List<ServiceOrderBean>>> queryServiceOrder(@Field("token") String token,
                                                                     @Field("type") String type,
                                                                     @Field("offset") int offset,
                                                                     @Field("limit") int limit);

    //取消服务订单
    @FormUrlEncoded
    @POST("/api/v1/club/service_item_order/cancel")
    Observable<HttpResult> cancelServiceOrder(@Field("token") String token,
                                              @Field("orderId") String orderId);

    //评论服务订单
    @FormUrlEncoded
    @POST("/api/v1/club/service_item_comment/create")
    Observable<HttpResult> commentServiceOrder(@Field("token") String token,
                                               @Field("orderId") String orderId, @Field("content") String content);

    //确认服务订单
    @FormUrlEncoded
    @POST("/api/v1/club/service_item_order/take")
    Observable<HttpResult> confirmServiceOrder(@Field("token") String token,
                                              @Field("orderId") String orderId);


}
