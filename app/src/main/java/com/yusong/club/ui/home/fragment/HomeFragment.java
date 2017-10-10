package com.yusong.club.ui.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.yusong.chargersdk.entity.LocationBean;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.map.LocationService;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.base.BaseWebViewActivity;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.community_notice.NoticeActivity;
import com.yusong.club.ui.community_service.activity.CommunityServiceActivity;
import com.yusong.club.ui.evaluate.activity.EvaluateActivity;
import com.yusong.club.ui.express.activity.ExpressHomeActivity;
import com.yusong.club.ui.express.adapter.HomeItemsAdapter;
import com.yusong.club.ui.home.activity.GiraActivity;
import com.yusong.club.ui.home.activity.LoginActivity;
import com.yusong.club.ui.home.mvp.entity.Protocol;
import com.yusong.club.ui.home.mvp.implView.IHomeView;
import com.yusong.club.ui.home.mvp.implView.IRegisterView;
import com.yusong.club.ui.home.mvp.presenter.impl.IHomePresenterImpl;
import com.yusong.club.ui.home.mvp.presenter.impl.IRegisterPresenterImpl;
import com.yusong.club.ui.me.mvp.entity.UserInfo;
import com.yusong.club.ui.repairs.RepairsActivity;
import com.yusong.club.ui.school.mvp.entity.Role;
import com.yusong.club.ui.school.parent.activity.ParentActivity;
import com.yusong.club.ui.school.school.activity.ChooseActivity;
import com.yusong.club.ui.school.school.activity.SchoolActivity;
import com.yusong.club.ui.school.teacher.activity.TeacherActivity;
import com.yusong.club.ui.school.user.activity.CreateRoleCheckResultActivity;
import com.yusong.club.ui.school.user.activity.CreateRoleExplainActivity;
import com.yusong.club.ui.shoppers.activity.CommodityDetailsActivity;
import com.yusong.club.ui.shoppers.activity.DecorationActivity;
import com.yusong.club.ui.shoppers.adapter.TuijianAdapter;
import com.yusong.club.ui.shoppers.bean.PreBusiness;
import com.yusong.club.ui.shoppers.bean.TuiJianBean;
import com.yusong.club.ui.shoppers.mvp.implView.ImplQueryTuijianLeiView;
import com.yusong.club.ui.shoppers.mvp.implView.ImplQuerytuijianListView;
import com.yusong.club.ui.shoppers.mvp.presenter.impl.IShopQueryTuijianPresenterImpl;
import com.yusong.club.ui.shoppers.mvp.presenter.impl.ImplShopQuerytuijianListPresenterImpl;
import com.yusong.club.ui.shoppers.used.activity.UsedHomeActivity;
import com.yusong.club.ui.supermarket.SupermarketActivity;
import com.yusong.club.ui.tenement.TenementActivity;
import com.yusong.club.ui.visitor.ThroughActictiy;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.DialogParentTypeTool;
import com.yusong.club.utils.DialogTypeTool;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;
import com.yusong.club.utils.glide.GlideImgManager;
import com.yusong.club.utils.interfacecallback.SetMainActivityCurrentFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by quaner on 16/12/26.
 */
public class HomeFragment extends BaseFragment implements IRegisterView, BGARefreshLayout.BGARefreshLayoutDelegate,
        View.OnClickListener, IHomeView, ImplQueryTuijianLeiView, DialogTypeTool.OnChooseTypeLinster,
        DialogParentTypeTool.OnChooseTypeLinster, ImplQuerytuijianListView {
    @InjectView(R.id.lin_all_views)
    LinearLayout linAllViews;
    private List<PreBusiness> data;
    @InjectView(R.id.banner)
    Banner mBanner;
    @InjectView(R.id.rl_modulename_refresh)
    BGARefreshLayout mRlModulenameRefresh;
    @InjectView(R.id.rl_items)
    RecyclerView mRlItems;
    @InjectView(R.id.supermarket_layout)
    LinearLayout supermarketLayout;
    @InjectView(R.id.services_layout)
    LinearLayout servicesLayout;
    @InjectView(R.id.used_layout)
    LinearLayout usedLayout;
    @InjectView(R.id.violations_layout)
    LinearLayout violationsLayout;
    @InjectView(R.id.furniture_layout)
    LinearLayout furnitureLayout;
    @InjectView(R.id.rl_event)
    RelativeLayout mRlEvent;
    @InjectView(R.id.rl_store)
    RecyclerView mRlStore;
    private int clazzSelect = 0;
    private int parentSelect = 0;
    private HomeItemsAdapter mItemsAdapter;
    public SetMainActivityCurrentFragment currentFragment;
    private int[] items_img = {
            R.mipmap.home_1, R.mipmap.home_2, R.mipmap.home_3,
            R.mipmap.home_4, R.mipmap.home_5, R.mipmap.home_6,
            R.mipmap.home_7, R.mipmap.home_8,
            R.mipmap.home_9, R.mipmap.home_10
    };
    private int[] items_name = {
            R.string.xiaoqu_gonggao, R.string.fangke_tongxing, R.string.baoxiu_baoshi,
            R.string.wuye_jiaofei, R.string.tousu_biaoyang, R.string.kuaidigui,
            R.string.chongdianzhuang, R.string.xiaoyuantong, R.string.jipiao_huochepiao, R.string.lvyou_jiudian
    };
    private IHomePresenterImpl mPresenter;
    private List<Role.RoleListBean> roleDatas = new ArrayList<>();
    public String auditMemo;
    public int auditStatus;
    public String auditTime;
    public boolean isRoleGet = false;
    public Role.RoleListBean roleListBean;
    public int applyId;
    private IRegisterPresenterImpl IRegisterPresenterImpl;//用来查询基本设置

    private IShopQueryTuijianPresenterImpl shopQueryTuijianPresenterImpl;//推荐商品
    private List<TuiJianBean> list = new ArrayList<TuiJianBean>();
    private List<TuiJianBean.Commodity> commodityList = new ArrayList<TuiJianBean.Commodity>();
    private ImplShopQuerytuijianListPresenterImpl querytuijianListPresenter;
    private TuijianAdapter tuijianAdapter;
    private boolean isClear = true;
    private int queryId = -1;//推荐商品分类id

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        currentFragment = (SetMainActivityCurrentFragment) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
        mService = new LocationService(getActivity());//todo 配合测试充电桩hybrid,定位默认直接开启了的
    }

    private LocationService mService;

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mService.stop();
        EventBus.getDefault().unregister(this);
    }

    //首页列表点击事件
    private void itemsClick() {
        mItemsAdapter.setOnItemClickListener(new HomeItemsAdapter.OnRecyclerViewItemClickListener() {

            public int size2;
            public int size1;
            public int size;

            @Override
            public void onItemClick(int position) {
                Intent mIntent = null;
                switch (position) {
                    case 0://小区公告
                        startActivity(new Intent(mContext, NoticeActivity.class));
                        break;
                    case 1://访客通行
                        startActivity(new Intent(mContext, ThroughActictiy.class));
                        break;
                    case 2://报修报事
                        startActivity(new Intent(mContext, RepairsActivity.class));
                        break;
                    case 3://物业缴费
                        startActivity(new Intent(mContext, TenementActivity.class));
                        break;
                    case 4://投诉表扬
                        startActivity(new Intent(mContext, EvaluateActivity.class));
                        break;
                    case 5://智能快柜
                        startActivity(new Intent(mContext, ExpressHomeActivity.class));
                        break;
                    case 6://充电桩
//                        startActivity(new Intent(mContext, ChargeHomeActivity.class));
                        gotoCharger();
                        break;
                    case 7://学校管理
                        if (isRoleGet == false) {
                            MyApplication.showMessage("获取角色失败");
                            return;
                        }
                        if (roleDatas.size() <= 0) {//没有角色
                            if (auditStatus == 1) {//待审核
                                mIntent = new Intent(mContext, CreateRoleExplainActivity.class);
                                mIntent.putExtra("applyId", applyId);
                            } else if (auditStatus == 3) {//审核不通过
                                mIntent = new Intent(mContext, CreateRoleCheckResultActivity.class);
                                mIntent.putExtra("auditMemo", auditMemo);//不通过原因
                                mIntent.putExtra("auditTime", auditTime);//审核时间
                            } else if (auditStatus == 0) {// == 0 的情况，2的情况不存在，通过审核了那就必然有角色
                                mIntent = new Intent(mContext, ChooseActivity.class);
                                mIntent.putExtra("type", "chooseCreate");
                            }
                        } else if (roleDatas.size() == 1) {//一所学校
                            mIntent = new Intent(mContext, ChooseActivity.class);
                            roleListBean = roleDatas.get(0);
                            if (roleListBean.getManagerList() != null) {
                                size = roleListBean.getManagerList().size();
                            }
                            if (roleListBean.getTeacherList() != null) {
                                size1 = roleListBean.getTeacherList().size();
                            }
                            if (roleListBean.getGuardianList() != null) {
                                size2 = roleListBean.getGuardianList().size();
                            }
                            if (size + size1 + size2 == 0) {
                                return;
                            }
                            StringBuffer sb = new StringBuffer();
                            if (roleListBean.getManagerList().size() > 0) {
                                sb.append("1");
                            }
                            if (roleListBean.getTeacherList().size() > 0) {
                                sb.append("2");
                            }
                            if (roleListBean.getGuardianList().size() > 0) {
                                sb.append("3");
                            }
                            mIntent.putExtra("type", "chooseIn");
                            mIntent.putExtra("roleTypeList", sb.toString());
                            mIntent.putExtra("SchoolName", roleListBean.getSchoolName());
                            mIntent.putExtra("SchoolId", roleListBean.getId() + "");
                            mIntent.putExtra("SingleSchoolRoleList", (Serializable) roleListBean);

                            //进入角色选择页面需要做如下判断
                            //TODO sb.toString():1 or 2 or 3 or 12 or 13 or 23 or 123
                            if (sb.toString().equals("1")) {//管理员
                                mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getManagerList().get(0).getId(), 1);
                                return;
                            } else if (sb.toString().equals("2")) {//老师
                                if (roleListBean.getTeacherList().size() > 1) {
                                    showTeacherChooseRole();
                                } else {
                                    mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getTeacherList().get(0).getId(), 2);
                                }
                                return;
                            } else if (sb.toString().equals("3")) {//家长

                                if (roleListBean.getGuardianList().size() > 1) {
                                    showParentChooseRole();
                                } else {
                                    mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getGuardianList().get(0).getId(), 3);
                                }
                                return;
                            } else {//同一个学校多个角色
                                mIntent.putExtra("type", "chooseIn");
                                mIntent.putExtra("roleTypeList", sb.toString());
                            }
                        } else {//　多所学校
                            mIntent = new Intent(mContext, ChooseActivity.class);
                            mIntent.putExtra("type", "chooseInMultiSchool");
                            mIntent.putExtra("multiSchoolRoleDatas", (Serializable) roleDatas);
                        }
                        startActivity(mIntent);
                        break;
                    case 8://机票火车票
                        mIntent = new Intent(mContext, BaseWebViewActivity.class);
                        mIntent.putExtra("url", protocol == null ? "http://hszh.infohs.cn/m2c/?order_cust_id=246810" : protocol.getTicketUrl());
                        mIntent.putExtra("title", "订票服务");
                        startActivity(mIntent);
                        break;
                    case 9://旅游酒店
                        mIntent = new Intent(mContext, GiraActivity.class);
                        mIntent.putExtra("scenicTicketUrl", protocol == null ? "https://h5.m.taobao.com/trip/ticket/search/index.html?spm=181.7474825.1998613473.8&ttid=&_preProjVer=0.1.90&_projVer=2.0.86" : protocol.getScenicTicketUrl());
                        mIntent.putExtra("hotelUrl", protocol == null ? "https://h5.m.taobao.com/trip/hotel/search/index.html" : protocol.getHotelUrl());
                        startActivity(mIntent);
                        break;
                }
            }
        });
    }

    private void gotoCharger() {
        UserInfo userInfo = CacheUtils.getUserInfo(mContext.getApplicationContext());
        //todo 跳转jar主页面 手机号& 经纬度为必传项目，请做好判断
        Bundle bundle = new Bundle();
//                        bundle.putString("mobile","17130044331");
        bundle.putString("mobile", userInfo != null ? userInfo.getMobile() : "17130044331");
        bundle.putString("nikeName", userInfo != null ? userInfo.getNickname() : "feisher");
//                        bundle.putString("portraitUrl","https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
        bundle.putString("portraitUrl", userInfo != null ? userInfo.getPortrait() : "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
        bundle.putString("addrStr", addrStr);
        bundle.putInt("cityCode", cityCode);
        bundle.putString("province", province);
        bundle.putString("city", city);
        bundle.putString("district", district);
        bundle.putDouble("latitude", latitude);
        bundle.putDouble("longitude", longitude);
        Intent intent0 = new Intent();
        intent0.setClassName(mContext.getPackageName(), "com.yusong.chargersdk.ui.ChargerMainActivity");
        intent0.putExtras(bundle);
        startActivity(intent0);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND) //
    public void onLocationBeanEvent(LocationBean event) {
        addrStr = TextUtils.isEmpty(event.getAddress()) ? addrStr : event.getAddress();
        cityCode = event.getBaiduCityCode();
        city = TextUtils.isEmpty(event.getCity()) ? city : event.getCity();
        district = TextUtils.isEmpty(event.getDistrict()) ? district : event.getDistrict();
        province = TextUtils.isEmpty(event.getProvince()) ? province : event.getProvince();
        latitude = event.getLat();
        longitude = event.getLng();
        Log.d("feisher", "接收到结果：" + new Gson().toJson(event));
    }

    public String addrStr;
    public int cityCode;
    public String province;
    public String city;
    public String district;
    public double latitude;
    public double longitude;

    private void showTeacherChooseRole() {
        List<Role.RoleListBean.TeacherListBean> teachersListBean = new ArrayList<>();
        teachersListBean = roleListBean.getTeacherList();
        DialogTypeTool.getInstance().initDialog(mContext, linAllViews, teachersListBean);
        DialogTypeTool.getInstance().setmOnChooseTypeLinster(this);
    }

    private void showParentChooseRole() {
        List<Role.RoleListBean.GuardianListBean> guardianListBean = new ArrayList<>();
        guardianListBean = roleListBean.getGuardianList();
        DialogParentTypeTool.getInstance().initDialog(mContext, linAllViews, guardianListBean);
        DialogParentTypeTool.getInstance().setmOnChooseTypeLinster(this);
    }

    /**
     * 功能事件处理 跳转
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent mIntent = null;
        switch (view.getId()) {
            case R.id.supermarket_layout://园区超市
                mIntent = new Intent(mContext, SupermarketActivity.class);
                startActivity(mIntent);
                break;
            case R.id.services_layout://社区服务
                mIntent = new Intent(mContext, CommunityServiceActivity.class);
                startActivity(mIntent);
                break;
            case R.id.used_layout://二手
                mIntent = new Intent(mContext, UsedHomeActivity.class);
                startActivity(mIntent);
//                mIntent = new Intent(mContext, BaseWebViewActivity.class);
//                mIntent.putExtra("title", "生活缴费");
//                mIntent.putExtra("url", protocol == null ? "" : protocol.getPaymentUrl());
//                startActivity(mIntent);
                break;
            case R.id.violations_layout://违章查询
                mIntent = new Intent(mContext, BaseWebViewActivity.class);
                mIntent.putExtra("url", protocol == null ? "http://app.huangshan.gov.cn/hsgov/appfront/moto_search.jsp" : protocol.getPeccancyUrl());
                mIntent.putExtra("title", "违章查询");
                startActivity(mIntent);
                break;
            case R.id.furniture_layout: //家具市场
                mIntent = new Intent(mContext, DecorationActivity.class);
                startActivity(mIntent);
                break;
            case R.id.rl_event://特惠活动
                currentFragment.CurrentFragment(R.id.rb_commerce);
                break;
            //            case R.id.ll_money_query://公积金查询http://www.hsgjjw.com/Home/Gjj_Default.aspx
//                mIntent = new Intent(mContext, BaseWebViewActivity.class);
//                mIntent.putExtra("url", protocol == null ? "http://app.huangshan.gov.cn/hsgov/appfront/fun_search.jsp" : protocol.getPublicAccumulationFundsUrl());
//                mIntent.putExtra("title", "公积金查询");
//                startActivity(mIntent);
//                break;
        }
    }

    /**
     * 进行下拉刷新联网操作
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshayout) {
        isClear = true;
        //请求轮播图
        mPresenter.requestBanner();
        IRegisterPresenterImpl.queryProtocol();//查询基本配置信息
        mPresenter.queryRoleList(CacheUtils.getToken(mContext));
        if (queryId > 0) {
            querytuijianListPresenter.queryTuijianList(queryId, 0, 10);
        } else {
            shopQueryTuijianPresenterImpl.queryTuijianLei(1);
        }
    }

    /**
     * 进行上拉加载联网操作
     *
     * @param refreshLayout
     * @return
     */
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(final BGARefreshLayout refreshLayout) {
        isClear = false;
        //请求商城数据
        mPresenter.requestBanner();

        if (queryId > 0) {
            querytuijianListPresenter.queryTuijianList(queryId, commodityList.size(), 10);
        } else {
            if (shopQueryTuijianPresenterImpl != null) {
                shopQueryTuijianPresenterImpl.queryTuijianLei(1);
            }
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.queryRoleList(CacheUtils.getToken(mContext));
    }

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_home, null);
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        mPresenter = new IHomePresenterImpl(this, mContext);
        IRegisterPresenterImpl = new IRegisterPresenterImpl(this, mContext);
        querytuijianListPresenter = new ImplShopQuerytuijianListPresenterImpl(this, mContext);
        IRegisterPresenterImpl.queryProtocol();
        //初始化上拉刷新 下拉加载
        initRefreshLayout();
        //初始化首页列表
        initItems();
        //模拟商城数据
        initStortData();
        //轮播图数据图片
        beginRefreshing();
    }

    /**
     * 初始化首页列表
     */
    private void initItems() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 5) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mItemsAdapter = new HomeItemsAdapter(mContext, items_img, items_name);
        mRlItems.setAdapter(mItemsAdapter);
        mRlItems.addItemDecoration(new SpaceItemDecoration(0, 0));
        mRlItems.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void refreshTuijian(List<TuiJianBean> data) {//推荐商品
//        if (list.size() > 0) {
//            list.clear();
//        }
//        if (commodityList.size() > 0) {
//            commodityList.clear();
//        }
//        if (data.size() > 0) {//推荐商品
//            for (TuiJianBean bean : data) {
//                commodityList.addAll(bean.getItemList());
//            }
//            tuijianAdapter.notifyDataSetChanged();
//        }

        if (list.size() > 0) {
            list.clear();
        }
        if (data.size() > 0) {
            isClear = true;
            queryId = data.get(data.size() - 1).getId();
            querytuijianListPresenter.queryTuijianList(queryId, 0, 10);
        }
    }

    /**
     * 模拟商城数据
     */
    private void initStortData() {
        //设置数据显示
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        tuijianAdapter = new TuijianAdapter(commodityList, getActivity());
        mRlStore.setAdapter(tuijianAdapter);
        mRlStore.setLayoutManager(gridLayoutManager);
        mRlStore.addItemDecoration(new SpaceItemDecoration(10, 5));
        shopQueryTuijianPresenterImpl = new IShopQueryTuijianPresenterImpl(this, getActivity());
        shopQueryTuijianPresenterImpl.queryTuijianLei(1);//推荐商品
        tuijianAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                intent.putExtra("itemId", commodityList.get(position).getId());
                intent.putExtra("isQG", 1);
                startActivity(intent);
            }
        });
    }

    /**
     * 显示Banner
     *
     * @param data
     */
    @Override
    public void showBanner(List<String> data) {
        closeLoading();
        mBanner.setImageLoader(new GlideImgManager());
        //设置图片集合
        mBanner.setImages(data);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void closeLoading() {
        mRlModulenameRefresh.endRefreshing();
        mRlModulenameRefresh.endLoadingMore();
    }

    @Override
    public void logOut() {
        MyApplication.exit();
        CacheUtils.clearSP(mContext);
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    public void roleListCallback(Role data) {
        isRoleGet = true;
        roleDatas.clear();
        roleDatas.addAll(data.getRoleList());
        auditMemo = data.getAuditMemo();
        auditStatus = data.getAuditStatus();
        auditTime = data.getAuditTime();

    }

    @Override
    public void selectRoleCallback(int type) {
        Intent mIntent = null;
        switch (type) {
            case 1:
                mIntent = new Intent(mContext, SchoolActivity.class);//进入学校界面
                mIntent.putExtra("SchoolName", roleListBean.getSchoolName());
                mIntent.putExtra("SchoolId", roleListBean.getId() + "");
                break;
            case 2:
                mIntent = new Intent(mContext, TeacherActivity.class);//进入老师界面
                mIntent.putExtra("SchoolName", roleListBean.getSchoolName());
                mIntent.putExtra("SchoolId", roleListBean.getId() + "");
                break;
            case 3:
                mIntent = new Intent(mContext, ParentActivity.class);//进入家长界面
                mIntent.putExtra("SchoolName", roleListBean.getSchoolName());
                mIntent.putExtra("SchoolId", roleListBean.getId() + "");
                break;
        }
        startActivity(mIntent);
    }


    @Override
    public void showProgressDialog() {

    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        mRlModulenameRefresh.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        mRlModulenameRefresh.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void tuijianClose() {
        closeLoading();
    }

    @Override
    public void initListener() {
        usedLayout.setOnClickListener(this);
        servicesLayout.setOnClickListener(this);
        mRlEvent.setOnClickListener(this);
        supermarketLayout.setOnClickListener(this);
        violationsLayout.setOnClickListener(this);
        furnitureLayout.setOnClickListener(this);
        //首页列表点击事件
        itemsClick();
    }


    // 通过代码方式控制进入正在刷新状态。应用场景: 在activity 的 onStart 方法中调用，自动进入正在刷新状态获取最新数据
    public void beginRefreshing() {
        mRlModulenameRefresh.beginRefreshing();
    }

    // 通过代码方式控制进入加载更多状态
    public void beginLoadingMore() {
        mRlModulenameRefresh.beginLoadingMore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void getChooseParentType(int pos) {
        parentSelect = pos;
        mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getGuardianList().get(pos).getId(), 3);

    }

    @Override
    public void getChooseType(int pos) {
        clazzSelect = pos;
        mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getTeacherList().get(pos).getId(), 2);

    }

    @Override
    public void recovery() {

    }

    @Override
    public void start() {

    }

    @Override
    public void upDataTime(Long aLong) {

    }

    @Override
    public void close() {

    }

    private Protocol protocol = null;

    @Override
    public void jumpActivity(Protocol data) {
        protocol = data;
    }

    @Override
    public void refreshTuiJianList(List<TuiJianBean.Commodity> data) {
        if (isClear) {
            if (commodityList.size() > 0) {
                commodityList.clear();
            }
        }
        commodityList.addAll(data);
        tuijianAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshuituijianClose() {

    }
}
