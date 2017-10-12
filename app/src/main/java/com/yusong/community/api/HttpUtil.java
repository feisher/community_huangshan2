
package com.yusong.community.api;

import com.yusong.community.api.factory.ServiceFactory;
import com.yusong.community.api.service.CommonService;
import com.yusong.community.pay.bean.WeiXinPayBean;
import com.yusong.community.pay.bean.ZhiFuBaoPayBean;
import com.yusong.community.ui.charge.bean.CreatOrderBean;
import com.yusong.community.ui.charge.bean.FetchMoneyBean;
import com.yusong.community.ui.charge.bean.MoveSosBean;
import com.yusong.community.ui.charge.bean.MyOrderBean;
import com.yusong.community.ui.charge.bean.NearbyBean;
import com.yusong.community.ui.charge.bean.OrderDetailsBean;
import com.yusong.community.ui.charge.bean.SancContentBean;
import com.yusong.community.ui.charge.bean.SettingBean;
import com.yusong.community.ui.community.mvp.entity.Community;
import com.yusong.community.ui.community.mvp.entity.HaveCommunityCity;
import com.yusong.community.ui.community.mvp.entity.PostComment;
import com.yusong.community.ui.community.mvp.entity.PostSupport;
import com.yusong.community.ui.community.mvp.entity.Posts;
import com.yusong.community.ui.community.mvp.entity.PostsCatogrey;
import com.yusong.community.ui.community.mvp.entity.SetCommunity;
import com.yusong.community.ui.community_notice.entity.NoticeBean;
import com.yusong.community.ui.community_service.entity.ServiceBean;
import com.yusong.community.ui.community_service.entity.ServiceDetailBean;
import com.yusong.community.ui.community_service.entity.ServiceOrderBean;
import com.yusong.community.ui.evaluate.EvaluateBean;
import com.yusong.community.ui.express.mvp.entity.CommitOrderResult;
import com.yusong.community.ui.express.mvp.entity.ConfigInfo;
import com.yusong.community.ui.express.mvp.entity.Contact;
import com.yusong.community.ui.express.mvp.entity.ContactGroup;
import com.yusong.community.ui.express.mvp.entity.DisclaimerBean;
import com.yusong.community.ui.express.mvp.entity.GetDetails;
import com.yusong.community.ui.express.mvp.entity.GetOrderInfo;
import com.yusong.community.ui.express.mvp.entity.NearbyBox;
import com.yusong.community.ui.express.mvp.entity.OpenBoxOrder;
import com.yusong.community.ui.express.mvp.entity.OrderLogistics;
import com.yusong.community.ui.express.mvp.entity.RatesInfo;
import com.yusong.community.ui.express.mvp.entity.SaveDetails;
import com.yusong.community.ui.express.mvp.entity.SaveOrderInfo;
import com.yusong.community.ui.express.mvp.entity.ScanOrder;
import com.yusong.community.ui.express.mvp.entity.SenderDetails;
import com.yusong.community.ui.express.mvp.entity.SenderOrderInfo;
import com.yusong.community.ui.home.mvp.entity.AuthCodeResult;
import com.yusong.community.ui.home.mvp.entity.ExistsResult;
import com.yusong.community.ui.home.mvp.entity.LoginResult;
import com.yusong.community.ui.home.mvp.entity.Protocol;
import com.yusong.community.ui.home.mvp.request.ExistsRequest;
import com.yusong.community.ui.me.mvp.entity.MoneyList;
import com.yusong.community.ui.me.mvp.entity.UserInfo;
import com.yusong.community.ui.repairs.RepairsHistoryBean;
import com.yusong.community.ui.school.mvp.entity.ActionDetail;
import com.yusong.community.ui.school.mvp.entity.ApplyRole;
import com.yusong.community.ui.school.mvp.entity.Clazz;
import com.yusong.community.ui.school.mvp.entity.DetailComment;
import com.yusong.community.ui.school.mvp.entity.EditSucessResult;
import com.yusong.community.ui.school.mvp.entity.GoodAction;
import com.yusong.community.ui.school.mvp.entity.JoinedResult;
import com.yusong.community.ui.school.mvp.entity.LatestPhoto;
import com.yusong.community.ui.school.mvp.entity.Notice;
import com.yusong.community.ui.school.mvp.entity.ReadCount;
import com.yusong.community.ui.school.mvp.entity.Role;
import com.yusong.community.ui.school.mvp.entity.School;
import com.yusong.community.ui.school.mvp.entity.SchoolIntro;
import com.yusong.community.ui.school.mvp.entity.SchoolManager;
import com.yusong.community.ui.school.mvp.entity.StuList;
import com.yusong.community.ui.school.mvp.entity.TeacherInfo;
import com.yusong.community.ui.school.mvp.entity.TeacherLeave;
import com.yusong.community.ui.school.mvp.entity.TelBookData;
import com.yusong.community.ui.school.mvp.entity.UserInfoDetails;
import com.yusong.community.ui.school.school.bean.ActivityBean;
import com.yusong.community.ui.school.school.bean.AddressBean;
import com.yusong.community.ui.school.school.bean.Comment;
import com.yusong.community.ui.school.school.bean.HuoType;
import com.yusong.community.ui.school.school.bean.StudentComment;
import com.yusong.community.ui.school.teacher.bean.AheadBean;
import com.yusong.community.ui.school.teacher.bean.AllTeacher;
import com.yusong.community.ui.school.teacher.bean.ChatGroup;
import com.yusong.community.ui.school.teacher.bean.ClassHomework;
import com.yusong.community.ui.school.teacher.bean.Course;
import com.yusong.community.ui.school.teacher.bean.MemberGroup;
import com.yusong.community.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.community.ui.school.teacher.bean.Result;
import com.yusong.community.ui.school.teacher.bean.StudyVideo;
import com.yusong.community.ui.school.teacher.bean.SubjectTable;
import com.yusong.community.ui.school.teacher.bean.TelBook;
import com.yusong.community.ui.school.teacher.bean.VideoAlbum;
import com.yusong.community.ui.shoppers.bean.CanQGBean;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.CreataOrderBean;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.ui.shoppers.bean.OrderShopBean;
import com.yusong.community.ui.shoppers.bean.PinLunBean;
import com.yusong.community.ui.shoppers.bean.QiangGouBean;
import com.yusong.community.ui.shoppers.bean.QiangGouDaleiBean;
import com.yusong.community.ui.shoppers.bean.ShopDetailsBean;
import com.yusong.community.ui.shoppers.bean.SpecificationBean;
import com.yusong.community.ui.shoppers.bean.TuiJianBean;
import com.yusong.community.ui.shoppers.used.bean.MyUsedBean;
import com.yusong.community.ui.shoppers.used.bean.UsedBean;
import com.yusong.community.ui.shoppers.used.bean.UsedCommentBean;
import com.yusong.community.ui.supermarket.entity.SMCommodityBean;
import com.yusong.community.ui.supermarket.entity.SuperMarketDetailsBean;
import com.yusong.community.ui.tenement.entity.TenementBean;
import com.yusong.community.ui.visitor.entity.CardDetailsBean;
import com.yusong.community.ui.visitor.entity.CommuntitySetingBean;
import com.yusong.community.ui.visitor.entity.OwnerInfo;
import com.yusong.community.ui.visitor.entity.ThroughCardBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by quaner on 16/12/30.
 */
public class HttpUtil {


    private static class DefaultHolder {
        private static HttpUtil INSTANCE = new HttpUtil();
    }

    private static class AgentHolder {
        private static HttpUtil INSTANCE = new HttpUtil(true);
    }

    private CommonService mCommonService;

    private HttpUtil() {
        mCommonService = ServiceFactory.getInstance().createService(CommonService.class);
    }


    private HttpUtil(boolean agent) {
        mCommonService = ServiceFactory.getInstance().createService(CommonService.class, agent);
    }


    public static HttpUtil getInstance(boolean agent) {
        if (agent) {
            return AgentHolder.INSTANCE;
        }
        return DefaultHolder.INSTANCE;
    }

    public static HttpUtil getInstance() {
        return DefaultHolder.INSTANCE;
    }


    //登录
    public Observable<HttpResult<LoginResult>> login(String mobile, String password,
                                                     int agentId) {
        return mCommonService.login(mobile, password, agentId);
    }

    //获取短信code
    public Observable<HttpResult<AuthCodeResult>> getAuthCode(@Field("mobile") String mobile, @Field("agentId") int agentId) {
        return mCommonService.getAuthCode(mobile, agentId);
    }

    //判断用户名是否存在
    public Observable<HttpResult<ExistsResult>> userExists(ExistsRequest existsRequest) {
        return mCommonService.exists(existsRequest);
    }

    //注册
    public Observable<HttpResult> register(@Field("mobile") String mobile, @Field("password") String password, @Field("authCode") String authCode,
                                           @Field("agentId") int agentId) {
        return mCommonService.register(mobile, password, authCode, agentId);
    }

    //修改密码
    public Observable<HttpResult> revisePwd(@Field("mobile") String mobile, @Field("password") String password, @Field("authCode") String authCode,
                                            @Field("agentId") int agentId) {
        return mCommonService.revisePwd(mobile, password, authCode, agentId);
    }

    public Observable<HttpResult<Protocol>> queryProtocol(@Query("agentId") int agentId) {

        return mCommonService.queryProtocol(agentId);
    }

    public Observable<HttpResult> syncIM(@Query("token") String token, @Query("password") String password) {

        return mCommonService.syncIM(token, password);
    }

    //获取轮播图
    public Observable<HttpResult<List<String>>> getBanner(@Field("token") final String token) {

        return mCommonService.getBanner(token);
    }

    //查询物品类型
    public Observable<HttpResult<List<String>>> getType(final String token, final int dictType) {

        return mCommonService.getType(token, dictType);
    }

    //添加联系人
    public Observable<HttpResult<Contact>> addContact(@Field("token") String token, @Field("contactName") final String contactName,
                                                      @Field("province") final String province, @Field("city") final String city,
                                                      @Field("district") final String district, @Field("mobile") final String mobile,
                                                      @Field("street") final String street, @Field("favoriteSenderFlag") final int favoriteSenderFlag,
                                                      @Field("favoriteReceiverFlag") final int favoriteReceiverFlag) {

        return mCommonService.addContact(token, contactName, province, city, district, mobile, street, favoriteSenderFlag, favoriteReceiverFlag);
    }

    //查询
    public Observable<HttpResult<List<ContactGroup>>> queryContact(String token, int type) {
        return mCommonService.queryContact(token, type);
    }

    //修改
    public Observable<HttpResult> updateContact(@Field("token") String token, @Field("id") int id, @Field("contactName") String contactName,
                                                @Field("province") String province, @Field("city") String city,
                                                @Field("district") String district, @Field("mobile") String mobile,
                                                @Field("street") String street, @Field("favoriteSenderFlag") int favoriteSenderFlag,
                                                @Field("favoriteReceiverFlag") int favoriteReceiverFlag) {
        return mCommonService.updateContact(token, id, contactName, province, city, district, mobile, street, favoriteSenderFlag, favoriteReceiverFlag);
    }

    //删除
    public Observable<HttpResult> deleteContact(String token, int id) {
        return mCommonService.deleteContact(token, id);
    }

    //设置默认联系人
    public Observable<HttpResult> dftContact(String token, int id, int type) {
        return mCommonService.dftContact(token, id, type);
    }

    //提交寄件订单
    public Observable<HttpResult<CommitOrderResult>> commitSenderOrder
    (String token, int id, String thing, String senderName, String senderPhone, String senderProvince,
     String senderCity, String senderDistrict, String senderAddress,
     String name, String phone, String receiverProvince, String receiverCity,
     String receiverDistrict, String address, int thingAmount, String memo, int charge, int boxType) {
        return mCommonService.commitSenderOrder(token, id, thing, senderName, senderPhone
                , senderProvince, senderCity, senderDistrict, senderAddress, name, phone, receiverProvince, receiverCity, receiverDistrict
                , address, thingAmount, memo, charge, boxType);
    }

    //提交存包订单
    public Observable<HttpResult<CommitOrderResult>> commitSaveOrder(@Field("token") final String token,
                                                                     @Field("receiverMobile") final String receiverMobile,
                                                                     @Field("memo") final String memo) {
        return mCommonService.commitSaveOrder(token, receiverMobile, memo);
    }

    //查询收件订单
    public Observable<HttpResult<List<GetOrderInfo>>> queryGetOrder(@Query("token") String token, @Query("type") String type,
                                                                    @Query("offset") int offset, @Query("limit") int limit) {
        return mCommonService.queryGetOrder(token, type, offset, limit);
    }

    //查询寄件订单
    public Observable<HttpResult<List<SenderOrderInfo>>> querySenderOrder(@Query("token") String token, @Query("type") String type,
                                                                          @Query("offset") int offset, @Query("limit") int limit) {
        return mCommonService.querySenderOrder(token, type, offset, limit);
    }

    //查询存包订单
    public Observable<HttpResult<List<SaveOrderInfo>>> querySaveOrder(@Query("token") String token, @Query("type") String type,
                                                                      @Query("offset") int offset, @Query("limit") int limit) {
        return mCommonService.querySaveOrder(token, type, offset, limit);
    }

    //查询快递轨迹
    public Observable<OrderLogistics> queryOrderLogistics
    (@FieldMap Map<String, String> params) {
        return mCommonService.queryOrderLogistics(params);
    }

    //查询快递单号
    public Observable<ScanOrder> scanOrder(@FieldMap Map<String, String> params) {
        return mCommonService.scanOrder(params);
    }

    //查询寄件的配置信息
    public Observable<HttpResult<ConfigInfo>> queryConfig(@Query("token") String token) {
        return mCommonService.queryConfig(token);
    }

    //查询寄件资费
    public Observable<HttpResult<RatesInfo>> queryRates(@Query("token") String token, @Query("companyId") int companyId,
                                                        @Query("fromProvince") int fromProvinceId, @Query("fromCity") int fromCityId,
                                                        @Query("toProvince") int toProvinceId, @Query("toCity") int toCityId, @Query("boxType") int boxType) {
        return mCommonService.queryRates(token, companyId, fromProvinceId, fromCityId, toProvinceId, toCityId, boxType);
    }

    //查询联系人详细
    public Observable<HttpResult<ContactGroup>> queryContactDetail(@Query("token") String token,
                                                                   @Query("id") int id) {

        return mCommonService.queryContactDetail(token, id);
    }

    //二维码扫描查待取订单（ 开箱 ）
    public Observable<HttpResult<OpenBoxOrder>> queryTerminalCode(@Query("token") String token,
                                                                  @Query("terminalCode") String terminalCode) {
        return mCommonService.queryTerminalCode(token, terminalCode);
    }

    //远程开箱
    public Observable<HttpResult> openBox(@Query("token") String token,
                                          @Query("type") int type, @Query("id") String id) {
        return mCommonService.openBox(token, type, id);
    }

    //查询附近快柜
    public Observable<HttpResult<List<NearbyBox>>> queryNearestBox(@Query("token") String token,
                                                                   @Query("lng") float lng, @Query("lat") float lat) {
        return mCommonService.queryNearestBox(token, lng, lat);
    }

    //查询寄件详情
    public Observable<HttpResult<SenderDetails>> querySenderDetails(@Query("token") String token,
                                                                    @Query("id") String id) {
        return mCommonService.querySenderDetails(token, id);
    }

    //查询收件详情
    public Observable<HttpResult<GetDetails>> queryGetDetails(@Query("token") String token,
                                                              @Query("id") String id) {
        return mCommonService.queryGetDetails(token, id);
    }

    //查询收件详情
    public Observable<HttpResult> cancleOrder(@Query("token") String token,
                                              @Query("id") String id) {
        return mCommonService.cancleOrder(token, id);
    }

    public Observable<HttpResult> saveZlPay(@Field("token") String token,
                                            @Field("orderId") String id) {
        return mCommonService.saveZlPay(token, id);
    }

    public Observable<HttpResult<ZhiFuBaoPayBean>> saveZfbPay(@Field("token") String token,
                                                              @Field("orderId") String id) {
        return mCommonService.saveZfbPay(token, id);
    }

    public Observable<HttpResult<WeiXinPayBean>> saveWxPay(@Field("token") String token,
                                                           @Field("orderId") String id) {
        return mCommonService.saveWxPay(token, id);
    }

    public Observable<HttpResult> senderZlPay(@Field("token") String token,
                                              @Field("orderId") String id) {
        return mCommonService.senderZlPay(token, id);
    }

    public Observable<HttpResult<ZhiFuBaoPayBean>> senderZfbPay(@Field("token") String token,
                                                                @Field("orderId") String id) {
        return mCommonService.senderZfbPay(token, id);
    }

    public Observable<HttpResult<WeiXinPayBean>> senderWxPay(@Field("token") String token,
                                                             @Field("orderId") String id) {
        return mCommonService.senderWxPay(token, id);
    }


    public Observable<HttpResult<SaveDetails>> saveDetail(@Query("token") String token,
                                                          @Query("id") String id) {

        return mCommonService.saveDetail(token, id);

    }

    public Observable<HttpResult<List<String>>> queryKdgBanner(@Field("token") String token) {
        return mCommonService.queryKdgBanner(token);
    }


    //---------------------------------- 充电桩----------------------------------

    // 查询轮播图片
    public Observable<HttpResult<List<String>>> queryBanner(@Field("token") String token) {
        return mCommonService.queryBanner(token);
    }

    // 查询附近桩点
    public Observable<HttpResult<List<NearbyBean>>> queryNearby(@Field("lng") double lng, @Field("lat") double lat
            , @Field("token") String type, @Field("keyword") String keyword, @Field("token") String token) {
        return mCommonService.queryNearby(lng, lat, type, keyword, token);
    }

    //查询充电收费标准
    public Observable<HttpResult<List<FetchMoneyBean>>> queryFetchMoney(@Field("token") String token, @Field("type") int type) {
        return mCommonService.queryFetchMoney(token, type);
    }

    // 查询移动救援   orderBy string "price" or "star"
    public Observable<HttpResult<List<MoveSosBean>>> queryMoveSos(@Field("token") String token, @Field("orderBy") String orderBy) {
        return mCommonService.queryMoveSos(token, orderBy);
    }

    // 扫描充电桩
    public Observable<HttpResult<List<SancContentBean>>> querSancContent(@Field("token") String token, @Field("code") String code) {
        return mCommonService.querSancContent(token, code);
    }

    // 开始充电

    public Observable<HttpResult> startCharge(@Field("token") String token, @Field("chargerId") String chargerId, @Field("orderId") String orderId) {
        return mCommonService.startCharge(token, chargerId, orderId);
    }

    // 查询订单明细
    public Observable<HttpResult<OrderDetailsBean>> queryOrderDetails(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.queryOrderDetails(token, orderId);
    }

    // 订单余额支付
    public Observable<HttpResult> yuEPay(@Field("token") String token, @Field("orderId") String orderId, @Field("price") float price, @Field("volume") int volume) {
        return mCommonService.yuEPay(token, orderId, price, volume);
    }

    // 支付宝支付
    public Observable<HttpResult<ZhiFuBaoPayBean>> zhiFuBaoPay(@Field("token") String token, @Field("orderId") String orderId, @Field("price") float price, @Field("volume") int volume) {
        return mCommonService.zhiFuBaoPay(token, orderId, price, volume);
    }

    // 微信支付
    public Observable<HttpResult<WeiXinPayBean>> weiXinPay(@Field("token") String token, @Field("orderId") String orderId, @Field("price") float price, @Field("volume") int volume) {
        return mCommonService.weiXinPay(token, orderId, price, volume);
    }

    //我的订单
    public Observable<HttpResult<List<MyOrderBean>>> queryMyOrder(@Field("token") String token, @Field("type") String type, @Field("offset") int offset,
                                                                  @Field("limit") int limit) {
        return mCommonService.queryMyOrder(token, type, offset, limit);
    }


    //预约订单volume
    public Observable<HttpResult<String>> queryBespeak(@Field("token") String token, @Field("chargerId") String chargerId,
                                                       @Field("duration") int duration, @Field("price") float price, @Field("volume") int volume) {
        return mCommonService.queryBespeak(token, chargerId, duration, price, volume);
    }

    //创建订单
    public Observable<HttpResult<CreatOrderBean>> createOrder(@Field("token") String token, @Field("terminalId") String terminalId,
                                                              @Field("duration") int duration, @Field("price") float price, @Field("volume") int volume) {
        return mCommonService.createOrder(token, terminalId, duration, price, volume);
    }

    //扫码查询桩点详情
    public Observable<HttpResult<List<NearbyBean>>> scanQueryChargeDetails(@Field("token") String token,
                                                                           @Field("chargerId") String chargerId) {
        return mCommonService.scanQueryChargeDetails(token, chargerId);
    }

    //取消订单
    public Observable<HttpResult> cancelOrder(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.cancelOrder(token, orderId);
    }

    //查询设置
    public Observable<HttpResult<SettingBean>> querySeting(@Field("token") String token) {
        return mCommonService.querySeting(token);
    }

    //查询充电桩详情
    public Observable<HttpResult<NearbyBean>> queryChargeDetails(@Field("token") String token, @Field("chargerId") String chargerId) {
        return mCommonService.queryChargeDetails(token, chargerId);
    }

    //---------------------------------    社区模块    -------------------------

    //查询含有社区城市列表
    public Observable<HttpResult<List<HaveCommunityCity>>> queryCityList(@Field("token") String token) {
        return mCommonService.queryCityList(token);
    }


    //查询附近的社区
    public Observable<HttpResult<List<Community>>> queryNearbyCommuity(@Field("token") String token,
                                                                       @Field("areaId") int areaId,
                                                                       @Field("baiduAreaId") int baiduAreaId,
                                                                       @Field("lat") double lat,
                                                                       @Field("lng") double lng) {
        return mCommonService.queryNearbyCommuity(token, areaId, baiduAreaId, lat, lng);
    }

    //设置当前社区
    public Observable<HttpResult<SetCommunity>> setCurrentCommunity(@Field("token") String token,
                                                                    @Field("communityId") int communityId) {
        return mCommonService.setCurrentCommunity(token, communityId);
    }

    //查询帖子分类
    public Observable<HttpResult<List<PostsCatogrey>>> queryPostsCategory(@Field("token") String token) {
        return mCommonService.queryPostsCategory(token);
    }

    //查询帖子列表
    public Observable<HttpResult<List<Posts>>> queryPostsList(@Field("token") String token,
                                                              @Field("categoryId") int categoryId, @Field("limit") int limit, @Field("offset") int offset) {
        return mCommonService.queryPostsList(token, categoryId, limit, offset);
    }

    //查询帖子评论
    public Observable<HttpResult<List<PostComment>>> queryPostComment(@Field("token") String token, @Field("postId") int postId,
                                                                      @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryPostComment(token, postId, offset, limit);
    }

    //点赞
    public Observable<HttpResult<PostSupport>> likePost(@Field("token") String token,
                                                        @Field("postId") int postId) {
        return mCommonService.likePost(token, postId);
    }

    //查询新闻列表
    public Observable<HttpResult<Contact>> queryNewsList(@Field("token") String token,
                                                         @Field("offset") int offset,
                                                         @Field("limit") int limit) {
        return mCommonService.queryNewsList(token, offset, limit);
    }

    //查询新闻内容
    public Observable<HttpResult<Contact>> queryNewsDetail(@Field("token") String token, @Field("newsId") int newsId) {
        return mCommonService.queryNewsDetail(token, newsId);
    }

    //提交(创建)帖子评论
    public Observable<HttpResult<Contact>> createPostComment(@Field("token") String token,
                                                             @Field("postId") int postId,
                                                             @Field("content") String content) {
        return mCommonService.createPostComment(token, postId, content);
    }

    //提交(创建)帖子 4图
    public Observable<HttpResult<Contact>> createPost(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                                      @Part("content") RequestBody content,
                                                      @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                                      @Part MultipartBody.Part image3, @Part MultipartBody.Part image4) {
        return mCommonService.createPost(token, categoryId, content, image1, image2, image3, image4);
    }

    //提交(创建)帖子  3图
    public Observable<HttpResult<Contact>> createPost(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                                      @Part("content") RequestBody content,
                                                      @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                                      @Part MultipartBody.Part image3) {
        return mCommonService.createPost(token, categoryId, content, image1, image2, image3);
    }

    //提交(创建)帖子 2 图
    public Observable<HttpResult<Contact>> createPost(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                                      @Part("content") RequestBody content,
                                                      @Part MultipartBody.Part image1, @Part MultipartBody.Part image2) {
        return mCommonService.createPost(token, categoryId, content, image1, image2);
    }

    //提交(创建)帖子 1 图
    public Observable<HttpResult<Contact>> createPost(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                                      @Part("content") RequestBody content,
                                                      @Part MultipartBody.Part image1) {
        return mCommonService.createPost(token, categoryId, content, image1);
    }

    //提交(创建)帖子 无图
    public Observable<HttpResult<Contact>> createPost(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                                      @Part("content") RequestBody content) {
        return mCommonService.createPost(token, categoryId, content);
    }

    // ---------------------------------    我的界面    -------------------------

    //查询个人信息
    public Observable<HttpResult<UserInfo>> queryUserInfo(@Field("token") String token) {
        return mCommonService.queryUserInfo(token);
    }

    //修改个人信息
    public Observable<HttpResult<UserInfo>> updateUserInfo(@Part("token") RequestBody token,
                                                           @Part("nickname") RequestBody nickname,
                                                           @Part("realName") RequestBody realName,
                                                           @Part("gender") RequestBody gender,
                                                           @Part("mail") RequestBody mail,
                                                           @Part MultipartBody.Part portrait) {
        return mCommonService.updateUserInfo(token, nickname, realName, gender, mail, portrait);
    }

    //修改密码
    public Observable<HttpResult<String>> updatePassword(@Field("token") String token,
                                                         @Field("oldPassword") String oldPassword,
                                                         @Field("newPassword") String newPassword) {
        return mCommonService.updatePassword(token, oldPassword, newPassword);
    }

    //意见反馈
    public Observable<HttpResult<String>> feedbackCreate(@Field("token") String token,
                                                         @Field("content") String content) {
        return mCommonService.feedbackCreate(token, content);
    }

    //应用评分
    public Observable<HttpResult<String>> app_gradeCreate(@Field("token") String token,
                                                          @Field("grade") float grade) {
        return mCommonService.app_gradeCreate(token, grade);
    }

    public Observable<HttpResult<String>> signIn(@Field("token") String token) {
        return mCommonService.signIn(token);
    }
    // ********************************推送功能********************************************************
    //提交百度推送绑定设备
//
//    public Observable<HttpResult<String>> baidu_pushBind(@Field("token") String token,
//                                                         @Field("userId") String userId,
//                                                         @Field("channelId") String channelId,
//                                                         @Field("clientType") int clientType) {
//        return mCommonService.baidu_pushBind(token, userId, channelId, clientType);
//    }

    //查询余额
    public Observable<HttpResult<MoneyList>> queryBalance(@Query("token") String token, @Query("customerId") String customerId) {

        return mCommonService.queryBalance(token, customerId);
    }

    //用户查询账单
    public Observable<HttpResult<List<MoneyList>>> queryMoneyList(@Query("token") String token, @Query("customerId") int customerId,
                                                                  @Query("offset") int offset, @Query("limit") int limit) {
        return mCommonService.queryMoneyList(token, customerId, offset, limit);
    }

    //微信充值
    public Observable<HttpResult<WeiXinPayBean>> wxRecharge(@Query("token") String token, @Query("money") int money) {
        return mCommonService.wxRecharge(token, money);
    }

    //支付宝充值
    public Observable<HttpResult<ZhiFuBaoPayBean>> zfbRecharge(@Query("token") String token, @Query("money") int money) {
        return mCommonService.zfbRecharge(token, money);
    }

    public Observable<HttpResult<List<MoneyList>>> queryPreferenceList(@Query("token") String token) {
        return mCommonService.queryPreferenceList(token);
    }

    public Observable<HttpResult<WeiXinPayBean>> weixinPayTimeoutCharge(@Query("token") String token, @Query("type") int type, @Query("id") String id) {

        return mCommonService.weixinPayTimeoutCharge(token, type, id);
    }

    public Observable<HttpResult<ZhiFuBaoPayBean>> zfbPayTimeoutCharge(@Query("token") String token, @Query("type") int type, @Query("id") String id) {

        return mCommonService.zfbPayTimeoutCharge(token, type, id);
    }


    public Observable<HttpResult<RatesInfo>> queryTimeoutCharge(@Query("token") String token, @Query("type") int type, @Query("id") String id) {
        return mCommonService.queryTimeoutCharge(token, type, id);
    }

    public Observable<HttpResult<RatesInfo>> balancePayTimeoutCharge(@Query("token") String token, @Query("type") int type, @Query("id") String id) {
        return mCommonService.balancePayTimeoutCharge(token, type, id);
    }
    // ********************************学校模块********************************************************

    //查询角色列表
    public Observable<HttpResult<Role>> roleList(@Field("token") String token) {
        return mCommonService.roleList(token);
    }

    //查询学校列表
    public Observable<HttpResult<List<School>>> schoolList(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.schoolList(token, offset, limit);
    }

    //查询班级列表
    public Observable<HttpResult<List<Clazz>>> clazzList(@Field("token") String token, @Field("schoolId") int schoolId, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.clazzList(token, schoolId, offset, limit);
    }

    public Observable<HttpResult<SchoolIntro>> querySchoolIntro(@Field("token") String token) {
        return mCommonService.querySchoolIntro(token);
    }

    //广告轮播图
    public Observable<HttpResult<List<String>>> bannerList(@Field("token") String token) {
        return mCommonService.bannerList(token);
    }

    //进入角色
    public Observable<HttpResult<String>> selectRole(@Field("token") String token, @Field("schoolId") int schoolId, @Field("id") int roleId, @Field("type") int type) {
        return mCommonService.selectRole(token, schoolId, roleId, type);
    }

    //查询角色信息(管理员)
    public Observable<HttpResult<SchoolManager>> querySchoolManagerInfo(@Field("token") String token, @Field("roleId") Integer roleId) {
        return mCommonService.querySchoolManagerInfo(token, roleId);
    }

    //查询学校公告(管理员)
    public Observable<HttpResult<List<Notice>>> public_noticeList(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.public_noticeList(token, offset, limit);
    }

    //删除学校公告(管理员)
    public Observable<HttpResult<String>> deleteNotice(@Field("token") String token, @Field("id") int id) {
        return mCommonService.deleteNotice(token, id);
    }

    //新建公告(管理员)
    public Observable<HttpResult<String>> addNotice(@Field("token") String token, @Field("title") String title,
                                                    @Field("content") String content, @Field("noticeType") int noticeType, @Field("publishRange") int publishRange) {
        return mCommonService.addNotice(token, title, content, noticeType, publishRange);
    }

    //发送通知公告阅读次数
    public Observable<HttpResult<ReadCount>> addReadCount(@Field("token") String token, @Field("noticeId") int noticeId) {
        return mCommonService.addReadCount(token, noticeId);
    }

    //查询角色申请列表(管理员)
    public Observable<HttpResult<List<ApplyRole>>> queryRoleApplyList(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryRoleApplyList(token, offset, limit);
    }

    //审核家长老师申请
    public Observable<HttpResult<String>> auditRoleApply(@Field("token") String token, @Field("applyId") int applyId, @Field("auditMemo") String auditMemo, @Field("status") int status) {
        return mCommonService.auditRoleApply(token, applyId, auditMemo, status);
    }

    //提醒管理员审核(申请人)
    public Observable<HttpResult<String>> noticAudit(@Field("token") String token, @Field("applyId") int applyId) {
        return mCommonService.noticAudit(token, applyId);
    }

    //查询学校通讯录
    public Observable<HttpResult<List<TelBookData>>> schoolTelBookList(@Field("token") String token) {
        return mCommonService.schoolTelBookList(token);
    }

    //查询学校通讯录2
    public Observable<HttpResult<List<AddressBean>>> schoolTelBookList2(@Field("token") String token) {
        return mCommonService.schoolTelBookList2(token);
    }

    //申请老师
    public Observable<HttpResult<String>> teacherApply(@Part("token") RequestBody token,
                                                       @Part("schoolId") RequestBody schoolId,
                                                       @Part("clazzId") RequestBody clazzId,
                                                       @Part("userName") RequestBody userName,
                                                       @Part("applyRole") RequestBody applyRole,
                                                       @Part("idCard") RequestBody idCard,
                                                       @Part("applyMemo") RequestBody applyMemo,
                                                       @Part MultipartBody.Part photo) {
        return mCommonService.teacherApply(token, schoolId, clazzId, userName, applyRole, idCard, applyMemo, photo);
    }

    //申请家长
    public Observable<HttpResult<String>> guardianApply(@Part("token") RequestBody token,
                                                        @Part("schoolId") RequestBody schoolId,
                                                        @Part("clazzId") RequestBody clazzId,
                                                        @Part("userName") RequestBody userName,
                                                        @Part("applyRole") RequestBody applyRole,
                                                        @Part("idCard") RequestBody idCard,
                                                        @Part("studentName") RequestBody studentName,
                                                        @Part("studentNo") RequestBody studentNo,
                                                        @Part("relation") RequestBody relation,
                                                        @Part("applyMemo") RequestBody applyMemo,
                                                        @Part MultipartBody.Part photo) {
        return mCommonService.guardianApply(token, schoolId, clazzId, userName, applyRole, idCard, studentName, studentNo, relation, applyMemo, photo);
    }

    //查询课程表
    public Observable<HttpResult<List<SubjectTable>>> timeTableList(@Field("token") String token) {
        return mCommonService.timeTableList(token);
    }

    //查询所有老师的列表
    public Observable<HttpResult<List<AllTeacher>>> searchTeacherList(@Field("token") String token) {
        return mCommonService.searchTeacherList(token);
    }

    //查询当日班级作业
    public Observable<HttpResult<List<ClassHomework>>> searchClassHomework(@Field("token") String token, @Field("day") String day) {
        return mCommonService.searchClassHomework(token, day);
    }

    //查询历史班级作业
    public Observable<HttpResult<List<ClassHomework>>> searchHistoryHomework(@Field("token") String token, @Field("day") String day) {
        return mCommonService.searchHistoryHomework(token, day);
    }

    //查询科目列表
    public Observable<HttpResult<List<Course>>> searchCourseList(@Field("token") String token) {
        return mCommonService.searchCourseList(token);
    }

    //查询班级相册列表(老师,家长)
    public Observable<HttpResult<List<PhotoAlbum>>> queryPhotoAlbumList(@Field("token") String token,
                                                                        @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryPhotoAlbumList(token, offset, limit);
    }

    //查询班级视册列表
    public Observable<HttpResult<List<VideoAlbum>>> queryVideoAlbumList(@Field("token") String token,
                                                                        @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryVideoAlbumList(token, offset, limit);
    }

    //新建相册(老师,家长)
    public Observable<HttpResult<Result>> createPhoto_album(@Field("token") String token, @Field("albumName") String albumName, @Field("memo") String memo) {
        return mCommonService.createPhoto_album(token, albumName, memo);
    }

    //新建视册(老师,家长)
    public Observable<HttpResult<Result>> createVideo_album(@Field("token") String token, @Field("albumName") String albumName, @Field("memo") String memo) {
        return mCommonService.createVideo_album(token, albumName, memo);
    }

    //上传图片到图册(老师,家长)
    public Observable<HttpResult<String>> uploadPhotoFile(@Part("token") RequestBody token,
                                                          @Part("albumId ") RequestBody albumId,
                                                          @Part MultipartBody.Part image1) {
        return mCommonService.uploadPhotoFile(token, albumId, image1);
    }

    //上传视频到视册(老师,家长)
    public Observable<HttpResult<String>> uploadVideoFile(@Part("token") RequestBody token,
                                                          @Part("albumId ") RequestBody albumId,
                                                          @Part MultipartBody.Part image1) {
        return mCommonService.uploadVideoFile(token, albumId, image1);
    }

    //删除图册到图片(老师,家长)
    public Observable<HttpResult<String>> deletePhoto(@Field("token") String token, @Field("photoIds") String photoIds, @Field("albumId") int albumId) {
        return mCommonService.deletePhoto(token, photoIds, albumId);
    }

    //删除视册到视频(老师,家长)
    public Observable<HttpResult<String>> deleteVideo(@Field("token") String token, @Field("videoIds") String videoIds, @Field("albumId") int albumId) {
        return mCommonService.deleteVideo(token, videoIds, albumId);
    }

    //删除图册到图片(老师,家长)
    public Observable<HttpResult<String>> deleteAllPhoto(@Field("token") String token, @Field("albumId") int albumId) {
        return mCommonService.deleteAllPhoto(token, albumId);
    }

    //删除视册(老师,家长)
    public Observable<HttpResult<String>> deleteAllVideo(@Field("token") String token, @Field("albumId") int albumId) {
        return mCommonService.deleteAllVideo(token, albumId);
    }

    //  查询学习视频(家长,老师)
    public Observable<HttpResult<List<StudyVideo>>> studyVideoList(@Field("token") String token,
                                                                   @Field("offset") int offset,
                                                                   @Field("limit") int limit) {
        return mCommonService.studyVideoList(token, offset, limit);
    }

    // 30-查询班训录(所有)
    public Observable<HttpResult<TelBook>> telBookList(@Field("token") String token) {
        return mCommonService.telBookList(token);
    }

    // 31-查询班训录(所有)
    public Observable<HttpResult<ChatGroup>> chatGroupList(@Field("token") String token) {
        return mCommonService.chatGroupList(token);
    }

    //   32-查询群组成员(所有)
    public Observable<HttpResult<List<MemberGroup>>> memberGroupList(@Field("token") String token, @Field("groupId") int groupId) {
        return mCommonService.memberGroupList(token, groupId);
    }

    //查询活动分类(所有)
    public Observable<HttpResult<List<HuoType>>> categoryList(@Field("token") String token) {
        return mCommonService.categoryList(token);
    }

    //查询活动分类(所有)
    public Observable<HttpResult<List<ActivityBean>>> activityList(@Field("token") String token, @Field("categoryId") int categoryId, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.activityList(token, categoryId, offset, limit);
    }

    //35-新建活动(家长,老师)
    public Observable<HttpResult<String>> createAction(@Part("token") RequestBody token,
                                                       @Part("categoryId") RequestBody categoryId,
                                                       @Part("activityName") RequestBody activityName,
                                                       @Part("beginTime") RequestBody beginTime,
                                                       @Part("endTime") RequestBody endTime,
                                                       @Part("memo") RequestBody memo,
                                                       @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                                       @Part MultipartBody.Part image3, @Part MultipartBody.Part image4) {
        return mCommonService.createAction(token, categoryId, activityName, beginTime, endTime, memo, image1, image2, image3, image4);
    }

    //36-参与活动(家长,老师)
    public Observable<HttpResult<JoinedResult>> joinActy(@Field("token") String token, @Field("activityId") int activityId) {
        return mCommonService.joinActy(token, activityId);
    }

    //38-查询学生评价列表(所有)
    public Observable<HttpResult<List<Comment>>> searchCommentList(String token, int activityId, int offset, int limit) {
        return mCommonService.searchCommentList(token, activityId, offset, limit);
    }

    //38-查询学生评价列表(所有)
    public Observable<HttpResult<String>> createComment(@Field("token") String token, @Field("activityId") int activityId, @Field("content") String content) {
        return mCommonService.createComment(token, activityId, content);
    }


    //38-查询学生评价列表(所有)
    public Observable<HttpResult<List<StudentComment>>> searchStuCommentList(@Field("token") String token, @Field("period") int period, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.searchStuCommentList(token, period, offset, limit);
    }

    // 51-活动新建或者取消点赞(所有人)
    public Observable<HttpResult<GoodAction>> getGoodAction(@Field("token") String token, @Field("activityId") int activityId) {
        return mCommonService.getGoodAction(token, activityId);
    }

    // 56-查询相册所有照片(所有)
    public Observable<HttpResult<PhotoAlbum>> getClazzPhotoList(@Field("token") String token, @Field("albumId") int albumId) {
        return mCommonService.getClazzPhotoList(token, albumId);
    }

    // 57-查询视册所有视频(所有)
    public Observable<HttpResult<VideoAlbum>> getClazzVideoList(@Field("token") String token, @Field("albumId") int albumId) {
        return mCommonService.getClazzVideoList(token, albumId);
    }

    // 58-查询活动详情(所有)
    public Observable<HttpResult<ActionDetail>> getActionDeatail(@Field("token") String token, @Field("activityId") int activityId) {
        return mCommonService.getActionDeatail(token, activityId);
    }

    //查询公告列表模块(老师)
    public Observable<HttpResult<List<DetailComment>>> getDetailComment(@Field("token") String token, @Field("activityId") int activityId, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.getDetailComment(token, activityId, offset, limit);
    }

    // 65-编辑相册(有权限的人)
    public Observable<HttpResult<EditSucessResult>> editPhoto(@Field("token") String token, @Field("albumId") int albumId, @Field("memo") String memo, @Field("albumName") String albumName) {
        return mCommonService.editPhoto(token, albumId, memo, albumName);
    }

    //   查询公告列表模块(老师)
    public Observable<HttpResult<List<Notice>>> teacherNoticeList(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.teacherNoticeList(token, offset, limit);
    }

    //   66-相册最新照片
    public Observable<HttpResult<List<LatestPhoto>>> getLatestPhto(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.getLatestPhto(token, offset, limit);
    }

    //设置值班老师
    public Observable<HttpResult<String>> setOndutyTeacher(@Field("token") String token, @Field("teacherId") int teacherId) {
        return mCommonService.setOndutyTeacher(token, teacherId);
    }

    //新增班级作业
    public Observable<HttpResult<String>> createClasswork(@Field("token") String token, @Field("courseId") int courseId, @Field("content") String content, @Field("workDate") String workDate) {
        return mCommonService.createClasswork(token, courseId, content, workDate);
    }

    //删除学习视频(老师)
    public Observable<HttpResult<String>> deleteStudyVideo(@Field("token") String token, @Field("videoId") int videoId) {
        return mCommonService.deleteStudyVideo(token, videoId);
    }

    //新建学生评价(老师)
    public Observable<HttpResult<String>> StuCommentCreate(@Field("token") String token,
                                                           @Field("studentId") int studentId,
                                                           @Field("period") int period,
                                                           @Field("star1") int star1,
                                                           @Field("star2") int star2,
                                                           @Field("star3") int star3,
                                                           @Field("star4") int star4,
                                                           @Field("content") String content) {
        return mCommonService.StuCommentCreate(token, studentId, period, star1, star2, star3, star4, content);
    }

    //
    //  53-查询老师信息
    public Observable<HttpResult<TeacherInfo>> queryTeacherInfo(@Field("token") String token, @Field("roleId") Integer roleId) {
        return mCommonService.queryTeacherInfo(token, roleId);
    }

    //  54-查询班级学生列表(所有)
    public Observable<HttpResult<List<StuList>>> queryStuList(@Field("token") String token, @Field("studentName") String studentName, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryStuList(token, studentName, offset, limit);
    }

    ///61-查询学生请假列表(家长、老师内容不同)
    public Observable<HttpResult<List<TeacherLeave>>> queryLeaveAplly(@Field("token") String token, @Field("status") Integer status, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryLeaveAplly(token, status, offset, limit);
    }

    ///61-查询学生请假列表(家长、老师内容不同)
    public Observable<HttpResult<List<TeacherLeave>>> queryLeaveApllyByDate(@Field("token") String token, @Field("status") Integer status, @Field("leaveBeginTime") String leaveBeginTime, @Field("leaveEndTime") String leaveEndTime, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryLeaveApllyByDate(token, status, leaveBeginTime, leaveEndTime, offset, limit);
    }

    public Observable<HttpResult<String>> judgeLeave(@Field("token") String token, @Field("applyId") int applyId, @Field("auditMemo") String auditMemo, @Field("status") int status) {
        return mCommonService.judgeLeave(token, applyId, auditMemo, status);
    }

    //查询公告列表模块(家长)
    public Observable<HttpResult<List<Notice>>> guardianNoticeList(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.guardianNoticeList(token, offset, limit);
    }

    //    60-新建学生请假(家长)
    public Observable<HttpResult<String>> CreateLeaveApply(@Field("token") String token, @Field("studentName") String studentName, @Field("reason") String reason, @Field("beginTime") String beginTime, @Field("endTime") String endTime) {
        return mCommonService.CreateLeaveApply(token, studentName, reason, beginTime, endTime);
    }


    //查询老师信息
    public Observable<HttpResult<UserInfoDetails>> queryTeacharInfo(@Field("token") String token, @Field("roleId") int id) {

        return mCommonService.queryTeacharInfo(token, id);
    }

    //查询学生信息
    public Observable<HttpResult<UserInfoDetails>> queryStudentInfo(@Field("token") String token, @Field("roleId") int id) {

        return mCommonService.queryStudentInfo(token, id);
    }

    //查询家长信息
    public Observable<HttpResult<UserInfoDetails>> queryParentInfo(@Field("token") String token, @Field("roleId") Integer id) {
        return mCommonService.queryParentInfo(token, id);
    }

    //新建提前放学
    public Observable<HttpResult<String>> CreateAheadAfterSchool(@Field("token") String token,
                                                                 @Field("overTime") String overTime,
                                                                 @Field("sunday") int sunday,
                                                                 @Field("monday") int monday,
                                                                 @Field("tuesday") int tuesday,
                                                                 @Field("wednesday") int wednesday,
                                                                 @Field("thursday") int thursday,
                                                                 @Field("friday") int friday,
                                                                 @Field("saturday") int saturday
    ) {

        return mCommonService.CreateAheadAfterSchool(token, overTime, sunday, monday, tuesday, wednesday, thursday, friday, saturday);
    }

    //查询提前放学（家长/老师）
    public Observable<HttpResult<List<AheadBean>>> QueryAheadAfterSchool(@Field("token") String token) {
        return mCommonService.QueryAheadAfterSchool(token);
    }


    // ********************************商城模块********************************************************

    //商城轮播图
    public Observable<HttpResult<List<String>>> shopImageList(@Field("token") String token) {
        return mCommonService.shopImageList(token);
    }

    //查询抢购大类
    public Observable<HttpResult<List<QiangGouBean>>> queryQiangGou(@Field("token") String token) {
        return mCommonService.queryQiangGou(token);
    }

    //查询推荐商品分类
    public Observable<HttpResult<List<TuiJianBean>>> queryTuiJian(@Field("token") String token, @Field("type") int type) {
        return mCommonService.queryTuiJian(token, type);
    }

    //查询抢购分类
    public Observable<HttpResult<List<QiangGouDaleiBean>>> queryQiangGouFenLei(@Field("token") String token,
                                                                               @Field("categoryId") int categoryId) {
        return mCommonService.queryQiangGouFenLei(token, categoryId);
    }

    //查询抢购商品列表
    public Observable<HttpResult<List<CommodityBean>>> queryQiangGouLieBiao(@Field("token") String token, @Field("grabCategoryId") int grabCategoryId, @Field("itemCategoryId") int itemCategoryId,
                                                                            @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryQiangGouLieBiao(token, grabCategoryId, itemCategoryId, offset, limit);
    }

    //查询商品明细
    public Observable<HttpResult<CommodityBean>> queryShangPinDetails(@Field("token") String token, @Field("itemId") int itemId) {
        return mCommonService.queryShangPinDetails(token, itemId);
    }

    //查询商品评论
    public Observable<HttpResult<List<PinLunBean>>> queryShangPinPinLun(@Field("token") String token, @Field("itemId") int itemId,
                                                                        @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryShangPinPinLun(token, itemId, offset, limit);
    }

    //查询店铺商品
    public Observable<HttpResult<List<CommodityBean>>> queryDianPuShangPin(@Field("token") String token, @Field("categoryId") int categoryId,
                                                                           @Field("shopId") int shopId, @Field("orderBy") String orderBy, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryDianPuShangPin(token, categoryId, shopId, orderBy, offset, limit);
    }

    //查询店铺商品分类
    public Observable<HttpResult<List<FenLeiBean>>> queryDianPuShangPinFenLei(@Field("token") String token, @Field("shopId") int shopId) {
        return mCommonService.queryDianPuShangPinFenLei(token, shopId);
    }

    //下单
    public Observable<HttpResult<CreataOrderBean>> shopCreateOrder(@Field("token") String token, @Field("deliverType") Integer deliverType, @Field("grabItemId") Integer grabItemId,
                                                                   @Field("itemId") int itemId, @Field("amount") int amount,
                                                                   @Field("price") double price, @Field("province") String province,
                                                                   @Field("city") String city, @Field("district") String district,
                                                                   @Field("street") String street, @Field("receiverMobile") String receiverMobile,
                                                                   @Field("receiver") String receiver, @Field("distributionTime") String distributionTime,
                                                                   @Field("leaveMessage") String leaveMessage, @Field("property") String specitication) {
        return mCommonService.shopCreateOrder(token, deliverType, grabItemId, itemId, amount, price, province, city, district, street, receiverMobile, receiver, distributionTime, leaveMessage, specitication);
    }

    //商城余额支付
    public Observable<HttpResult<String>> shopYuEPay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.shopYuEPay(token, orderId);
    }

    //商城微信支付
    public Observable<HttpResult<WeiXinPayBean>> shopWeiXinPay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.shopWeiXinPay(token, orderId);
    }

    //商城支付宝支付
    public Observable<HttpResult<ZhiFuBaoPayBean>> shopZFBPay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.shopZFBPay(token, orderId);
    }

    //商城订单
    public Observable<HttpResult<List<OrderShopBean>>> MyShopOrder(@Field("token") String token,
                                                                   @Field("type") String type, @Field("offset") int offset,
                                                                   @Field("limit") int limit) {
        return mCommonService.MyShopOrder(token, type, offset, limit);
    }

    //商品评论
    public Observable<HttpResult<String>> addPingLun(@Field("token") String token, @Field("orderId") String orderId,
                                                     @Field("content") String content) {
        return mCommonService.addPingLun(token, orderId, content);
    }

    //取消订单
    public Observable<HttpResult<String>> shopCancelOrder(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.shopCancelOrder(token, orderId);
    }

    //查询推荐商品列表
    public Observable<HttpResult<List<TuiJianBean.Commodity>>> queryTuijianList(@Field("token") String token,
                                                                                @Field("categoryId") int categoryId,
                                                                                @Field("offset") int offset,
                                                                                @Field("limit") int limit) {
        return mCommonService.queryTuijianList(token, categoryId, offset, limit);
    }

    //查询抢购商品明细
    public Observable<HttpResult<CommodityBean>> queryQgCommdityDetails(@Field("token") String token,
                                                                        @Field("grabItemId") int grabItemId) {
        return mCommonService.queryQgCommdityDetails(token, grabItemId);
    }

    //能抢数量
    public Observable<HttpResult<CanQGBean>> canQGSum(@Field("token") String token,
                                                      @Field("grabItemId") int grabItemId) {
        return mCommonService.canQGSum(token, grabItemId);
    }

    //查询家装分类
    public Observable<HttpResult<List<FenLeiBean>>> queryHomeFeilei(@Field("token") String token,
                                                                    @Field("categoryType") int categoryType) {
        return mCommonService.queryHomeFeilei(token, categoryType);
    }

    //查询家装列表
    public Observable<HttpResult<List<CommodityBean>>> queryHomeList(@Field("token") String token,
                                                                     @Field("categoryId") int categoryId,
                                                                     @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryHomeList(token, categoryId, offset, limit);
    }

    public Observable<HttpResult<ShopDetailsBean>> queryShopDetails(@Field("token") String token, @Field("shopId") int shopId) {
        return mCommonService.queryShopDetails(token, shopId);
    }

    //确认收货
    public Observable<HttpResult> affirmReceive(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.affirmReceive(token, orderId);
    }

    //申请退货
    public Observable<HttpResult> reimburse(@Field("token") String token, @Field("orderId") String orderId, @Field("returnReason") String returnReason) {
        return mCommonService.reimburse(token, orderId, returnReason);
    }

    //************************   二手市场  **************************

    //查询二手分类
    public Observable<HttpResult<List<FenLeiBean>>> queryUsedFenlei(@Field("token") String token) {
        return mCommonService.queryUsedFenlei(token);
    }

    //查询二手商品列表
    public Observable<HttpResult<List<UsedBean>>> queryUsedList(@Field("token") String token, @Field("categoryId") int categoryId,
                                                                @Field("lng") double lng, @Field("lat") double lat,
                                                                @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryUsedList(token, categoryId, lng, lat, offset, limit);
    }

    //查询二手商品评论
    public Observable<HttpResult<List<UsedCommentBean>>> queryUsedPinlun(@Field("token") String token, @Field("itemId") int itemId,
                                                                         @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryUsedPinlun(token, itemId, offset, limit);
    }

    //查询我的二手商品
    public Observable<HttpResult<List<MyUsedBean>>> queryMyUsed(@Field("token") String token, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryMyUsed(token, offset, limit);
    }

    //发布二手商品
    public Observable<HttpResult> issueUsed(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                            @Part("introduction") RequestBody introduction,
                                            @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                            @Part MultipartBody.Part image3, @Part MultipartBody.Part image4,
                                            @Part("price") RequestBody price, @Part("showPrice") RequestBody showPrice,
                                            @Part("address") RequestBody address, @Part("lng") RequestBody lng, @Part("lat") RequestBody lat) {
        return mCommonService.issueUsed(token, categoryId, introduction, image1, image2, image3, image4, price, showPrice, address, lng, lat);
    }

    //修改二手商品
    public Observable<HttpResult> editUsed(@Part("token") RequestBody token, @Part("id") RequestBody id,
                                           @Part("categoryId") RequestBody categoryId,
                                           @Part("introduction") RequestBody introduction,
                                           @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                           @Part MultipartBody.Part image3, @Part MultipartBody.Part image4,
                                           @Part("price") RequestBody price, @Part("showPrice") RequestBody showPrice,
                                           @Part("address") RequestBody address, @Part("lng") RequestBody lng, @Part("lat") RequestBody lat) {
        return mCommonService.editUsed(token, id, categoryId, introduction, image1, image2, image3, image4, price, showPrice, address, lng, lat);
    }

    //下架二手商品
    public Observable<HttpResult> outUsed(@Field("token") String token, @Field("id") int id) {
        return mCommonService.outUsed(token, id);
    }

    //发布商品评论
    public Observable<HttpResult> usedComment(@Field("token") String token, @Field("itemId") int itemId, @Field("content") String content) {
        return mCommonService.usedComment(token, itemId, content);
    }

    //评论回复
    public Observable<HttpResult> replyComment(@Field("token") String token, @Field("commentId") int commentId, @Field("content") String content) {
        return mCommonService.replyComment(token, commentId, content);
    }

    //删除
    public Observable<HttpResult> deleteUsed(@Field("token") String token, @Field("id") int id) {
        return mCommonService.deleteUsed(token, id);
    }


    //***************************访客通行****************************
    //查询业主信息
    public Observable<HttpResult<OwnerInfo>> queryOwnerInfo(@Field("token") String token) {
        return mCommonService.queryOwnerInfo(token);
    }

    //生成通行证

    public Observable<HttpResult<ThroughCardBean>> createThroughCrad(@Field("token") String token,
                                                                     @Field("visitorName") String visitorName,
                                                                     @Field("sex") int sex,
                                                                     @Field("visitorTime") String visitorTime,
                                                                     @Field("isDrive") int isDrive,
                                                                     @Field("plateNumber") String plateNumber) {
        return mCommonService.createThroughCrad(token, visitorName, sex, visitorTime, isDrive, plateNumber);
    }

    //通行校验 @FormUrlEncoded
    public Observable<HttpResult<CardDetailsBean>> checkPermit(@Field("token") String token, @Field("permitCode") String permitCode) {
        return mCommonService.checkPermit(token, permitCode);
    }

    //报修报事提交
    public Observable<HttpResult> createRepairs(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                                @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                                @Part("proprietorMobile") RequestBody proprietorMobile,
                                                @Part("proprietorAddress") RequestBody proprietorAddress,
                                                @Part("content") RequestBody content,
                                                @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                                @Part MultipartBody.Part image3, @Part MultipartBody.Part image4) {
        return mCommonService.createRepairs(token, categoryId, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2, image3, image4);
    }


    public Observable<HttpResult> createRepairs(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                                @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                                @Part("proprietorMobile") RequestBody proprietorMobile,
                                                @Part("proprietorAddress") RequestBody proprietorAddress,
                                                @Part("content") RequestBody content,
                                                @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                                @Part MultipartBody.Part image3) {
        return mCommonService.createRepairs(token, categoryId, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2, image3);
    }

    public Observable<HttpResult> createRepairs(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                                @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                                @Part("proprietorMobile") RequestBody proprietorMobile,
                                                @Part("proprietorAddress") RequestBody proprietorAddress,
                                                @Part("content") RequestBody content,
                                                @Part MultipartBody.Part image1, @Part MultipartBody.Part image2) {
        return mCommonService.createRepairs(token, categoryId, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2);
    }

    public Observable<HttpResult> createRepairs(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                                @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                                @Part("proprietorMobile") RequestBody proprietorMobile,
                                                @Part("proprietorAddress") RequestBody proprietorAddress,
                                                @Part("content") RequestBody content,
                                                @Part MultipartBody.Part image1) {
        return mCommonService.createRepairs(token, categoryId, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1);
    }

    public Observable<HttpResult> createRepairs(@Part("token") RequestBody token, @Part("categoryId") RequestBody categoryId,
                                                @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                                @Part("proprietorMobile") RequestBody proprietorMobile,
                                                @Part("proprietorAddress") RequestBody proprietorAddress,
                                                @Part("content") RequestBody content) {
        return mCommonService.createRepairs(token, categoryId, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content);
    }


    //查询社区设置
    public Observable<HttpResult<CommuntitySetingBean>> queryCommuntiySeting(@Field("token") String token) {
        return mCommonService.queryCommuntiySeting(token);
    }

    //查询申报历史
    public Observable<HttpResult<List<RepairsHistoryBean>>> queryRepairsHistory(@Field("token") String token,
                                                                                @Field("proprietorId") int proprietorId,
                                                                                @Field("offset") int offset,
                                                                                @Field("limit") int limit) {
        return mCommonService.queryRepairsHistory(token, proprietorId, offset, limit);
    }


    //提交表扬
    public Observable<HttpResult> commitEvaluate(@Part("token") RequestBody token, @Part("categoryType") RequestBody categoryType,
                                                 @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                                 @Part("proprietorMobile") RequestBody proprietorMobile,
                                                 @Part("proprietorAddress") RequestBody proprietorAddress,
                                                 @Part("content") RequestBody content,
                                                 @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                                 @Part MultipartBody.Part image3, @Part MultipartBody.Part image4) {
        return mCommonService.commitEvaluate(token, categoryType, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2, image3, image4);
    }


    public Observable<HttpResult> commitEvaluate(@Part("token") RequestBody token, @Part("categoryType") RequestBody categoryType,
                                                 @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                                 @Part("proprietorMobile") RequestBody proprietorMobile,
                                                 @Part("proprietorAddress") RequestBody proprietorAddress,
                                                 @Part("content") RequestBody content,
                                                 @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                                 @Part MultipartBody.Part image3) {
        return mCommonService.commitEvaluate(token, categoryType, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2, image3);
    }

    public Observable<HttpResult> commitEvaluate(@Part("token") RequestBody token, @Part("categoryType") RequestBody categoryType,
                                                 @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                                 @Part("proprietorMobile") RequestBody proprietorMobile,
                                                 @Part("proprietorAddress") RequestBody proprietorAddress,
                                                 @Part("content") RequestBody content,
                                                 @Part MultipartBody.Part image1, @Part MultipartBody.Part image2) {
        return mCommonService.commitEvaluate(token, categoryType, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2);
    }

    public Observable<HttpResult> commitEvaluate(@Part("token") RequestBody token, @Part("categoryType") RequestBody categoryType,
                                                 @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                                 @Part("proprietorMobile") RequestBody proprietorMobile,
                                                 @Part("proprietorAddress") RequestBody proprietorAddress,
                                                 @Part("content") RequestBody content,
                                                 @Part MultipartBody.Part image1) {
        return mCommonService.commitEvaluate(token, categoryType, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1);
    }

    public Observable<HttpResult> commitEvaluate(@Part("token") RequestBody token, @Part("categoryType") RequestBody categoryType,
                                                 @Part("proprietorId") RequestBody proprietorId, @Part("proprietorName") RequestBody proprietorName,
                                                 @Part("proprietorMobile") RequestBody proprietorMobile,
                                                 @Part("proprietorAddress") RequestBody proprietorAddress,
                                                 @Part("content") RequestBody content) {
        return mCommonService.commitEvaluate(token, categoryType, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content);
    }

    public Observable<HttpResult<List<EvaluateBean>>> queryEvaluateHistory(String token, int offset, int limit) {
        return mCommonService.queryEvaluateHistory(token, offset, limit);
    }

    //物业缴费查询
    public Observable<HttpResult<List<TenementBean>>> queryTenementPay(@Field("token") String token,
                                                                       @Field("communityId") int communityId,
                                                                       @Field("proprietorId") int proprietorId,
                                                                       @Field("isPay") int isPay,
                                                                       @Field("offset") int offset,
                                                                       @Field("limit") int limit) {
        return mCommonService.queryTenementPay(token, communityId, proprietorId, isPay, offset, limit);
    }

    //查询超市详情
    public Observable<HttpResult<SuperMarketDetailsBean>> querySuperMarket(@Field("token") String token) {
        return mCommonService.querySuperMarket(token);
    }

    //查询超市分类
    public Observable<HttpResult<List<FenLeiBean>>> querySuperMarketCategory(@Field("token") String token) {
        return mCommonService.querySuperMarketCategory(token);
    }

    //超市分类查询商品
    public Observable<HttpResult<List<SMCommodityBean>>> querySMCommodity(@Field("token") String token,
                                                                          @Field("categoryId") int categoryId,
                                                                          @Field("offset") int offset,
                                                                          @Field("limit") int limit) {
        return mCommonService.querySMCommodity(token, categoryId, offset, limit);
    }

    //超市分类查询商品
    public Observable<HttpResult<CommodityBean>> querySMCommodityDetalis(@Field("token") String token,
                                                                         @Field("itemId") int itemId) {
        return mCommonService.querySMCommodityDetalis(token, itemId);
    }

    //超市商品评论
    public Observable<HttpResult<List<PinLunBean>>> querySMCommodityComment(@Field("token") String token,
                                                                            @Field("itemId") int itemId,
                                                                            @Field("offset") int offset,
                                                                            @Field("limit") int limit) {
        return mCommonService.querySMCommodityComment(token, itemId, offset, limit);
    }

    //提交超市商品评论
    public Observable<HttpResult> commitSMComment(@Field("token") String token, @Field("orderId") String orderId,
                                                  @Field("content") String content) {
        return mCommonService.commitSMComment(token, orderId, content);
    }

    //超市下单
    public Observable<HttpResult<CreataOrderBean>> smCreateOrder(@Field("token") String token, @Field("deliverType") Integer deliverType,
                                                                 @Field("itemId") int itemId, @Field("amount") int amount,
                                                                 @Field("price") double price, @Field("province") String province,
                                                                 @Field("city") String city, @Field("district") String district,
                                                                 @Field("street") String street, @Field("receiverMobile") String receiverMobile,
                                                                 @Field("receiver") String receiver, @Field("distributionTime") String distributionTime,
                                                                 @Field("leaveMessage") String leaveMessage, @Field("property") String property) {
        return mCommonService.smCreateOrder(token, deliverType, itemId, amount, price, province, city, district, street, receiverMobile, receiver, distributionTime, leaveMessage, property);
    }

    //查询社区公告
    public Observable<HttpResult<List<NoticeBean>>> queryCommunityNotice(@Field("token") String token, @Field("offset") int offset,
                                                                         @Field("limit") int limit) {
        return mCommonService.queryCommunityNotice(token, offset, limit);
    }

    //我的超市订单
    public Observable<HttpResult<List<OrderShopBean>>> querySuperMarketOrder(@Field("token") String token, @Field("type") String type, @Field("offset") int offset,
                                                                             @Field("limit") int limit) {
        return mCommonService.querySuperMarketOrder(token, type, offset, limit);
    }

    //超市确认收货
    public Observable<HttpResult> confirmReceipt(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.confirmReceipt(token, orderId);
    }

    //超市确认收货
    public Observable<HttpResult> salesReturn(@Field("token") String token, @Field("orderId") String orderId, @Field("returnReason") String returnReason) {
        return mCommonService.salesReturn(token, orderId, returnReason);
    }

    //超市余额支付
    public Observable<HttpResult> smBalancePay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.smBalancePay(token, orderId);
    }

    //超市微信支付
    public Observable<HttpResult<WeiXinPayBean>> smWeixinPay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.smWeixinPay(token, orderId);
    }

    //超市支付宝支付
    public Observable<HttpResult<ZhiFuBaoPayBean>> smZhifubaoPay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.smZhifubaoPay(token, orderId);
    }

    //超市订单取消
    public Observable<HttpResult> smCancelOrder(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.smCancelOrder(token, orderId);
    }

    //物业余额支付
    public Observable<HttpResult> tenementBalancePay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.tenementBalancePay(token, orderId);
    }

    //物业微信支付
    public Observable<HttpResult<WeiXinPayBean>> tenementWeixinPay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.tenementWeixinPay(token, orderId);
    }

    //物业支付宝支付
    public Observable<HttpResult<ZhiFuBaoPayBean>> tenementZhifubaoPay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.tenementZhifubaoPay(token, orderId);
    }

    //商城搜索
    public Observable<HttpResult<List<CommodityBean>>> findCommodity(@Field("token") String token, @Field("itemName") String itemName) {
        return mCommonService.findCommodity(token, itemName);
    }

    //超市搜索
    public Observable<HttpResult<List<CommodityBean>>> findSMCommodity(@Field("token") String token, @Field("itemName") String itemName) {
        return mCommonService.findSMCommodity(token, itemName);
    }

    //免责声明
    public Observable<HttpResult<DisclaimerBean>> queryDisclaimer(@Field("token") String token) {
        return mCommonService.queryDisclaimer(token);
    }

    //查询商品规格
    public Observable<HttpResult<List<SpecificationBean>>> queryItemSpecification(@Field("token") String token, @Field("itemId") int itemId) {
        return mCommonService.queryItemSpecification(token, itemId);
    }

    //查询商品规格(超市)
    public Observable<HttpResult<List<SpecificationBean>>> querySMItemSpecification(@Field("token") String token, @Field("itemId") int itemId) {
        return mCommonService.querySMItemSpecification(token, itemId);
    }

    //查询服务详情
    public Observable<HttpResult<ServiceDetailBean>> queryServiceInfo(@Field("token") String token) {
        return mCommonService.queryServiceInfo(token);
    }

    //查询服务分类
    public Observable<HttpResult<List<FenLeiBean>>> queryServiceCategory(@Field("token") String token) {
        return mCommonService.queryServiceCategory(token);
    }

    //查询社区服务列表
    public Observable<HttpResult<List<ServiceBean>>> queryServiceList(@Field("token") String token, @Field("categoryId") int categoryId, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryServiceList(token, categoryId, offset, limit);
    }

    //查询社区服务详情
    public Observable<HttpResult<CommodityBean>> queryServiceDetails(@Field("token") String token, @Field("itemId") int itemId) {
        return mCommonService.queryServiceDetails(token, itemId);
    }

    //查询社区服务评论
    public Observable<HttpResult<List<PinLunBean>>> queryServiceComment(@Field("token") String token, @Field("itemId") int itemId, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryServiceComment(token, itemId, offset, limit);
    }

    //社区服务下单
    public Observable<HttpResult<CreataOrderBean>> serviceCreateOrder(@Field("token") String token,
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
                                                                      @Field("leaveMessage") String leaveMessage) {
        return mCommonService.serviceCreateOrder(token, deliverType, itemId, price, province, city, district,
                street, receiverMobile, receiver, serviceTime, leaveMessage);
    }

    //社区服务订单余额支付
    public Observable<HttpResult> serviceBalancePay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.serviceBalancePay(token, orderId);
    }

    //社区服务订单微信支付
    public Observable<HttpResult<WeiXinPayBean>> serviceWeixinPay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.serviceWeixinPay(token, orderId);
    }

    //社区服务订单支付宝支付
    public Observable<HttpResult<ZhiFuBaoPayBean>> serviceZhifubaoPay(@Field("token") String token, @Field("orderId") String orderId) {
        return mCommonService.serviceZhifubaoPay(token, orderId);
    }

    //服务搜素
    public Observable<HttpResult<List<CommodityBean>>> findService(@Field("token") String token, @Field("itemName") String itemName) {
        return mCommonService.findService(token, itemName);
    }

    //服务订单查询
    public Observable<HttpResult<List<ServiceOrderBean>>> queryServiceOrder(@Field("token") String token, @Field("type") String type, @Field("offset") int offset, @Field("limit") int limit) {
        return mCommonService.queryServiceOrder(token, type, offset, limit);
    }

    //取消服务订单
    public Observable<HttpResult> cancelServiceOrder(@Field("token") String token,
                                                     @Field("orderId") String orderId) {
        return mCommonService.cancelServiceOrder(token, orderId);
    }

    //评论服务订单
    public Observable<HttpResult> commentServiceOrder(@Field("token") String token,
                                                      @Field("orderId") String orderId, @Field("content") String content) {
        return mCommonService.commentServiceOrder(token, orderId, content);
    }

    //确认服务订单
    public Observable<HttpResult> confirmServiceOrder(@Field("token") String token,
                                                      @Field("orderId") String orderId) {
        return mCommonService.confirmServiceOrder(token, orderId);
    }

}
