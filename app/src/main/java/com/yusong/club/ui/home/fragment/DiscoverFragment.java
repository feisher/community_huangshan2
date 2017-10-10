package com.yusong.club.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.activity.CommodityDetailsActivity;
import com.yusong.club.ui.shoppers.adapter.QianggouAdapter;
import com.yusong.club.ui.shoppers.adapter.TehuiAdapter;
import com.yusong.club.ui.shoppers.adapter.TuijianAdapter;
import com.yusong.club.ui.shoppers.bean.QiangGouBean;
import com.yusong.club.ui.shoppers.bean.TuiJianBean;
import com.yusong.club.ui.shoppers.mvp.implView.ImplQueryQiangouLeiView;
import com.yusong.club.ui.shoppers.mvp.implView.ImplQueryTuijianLeiView;
import com.yusong.club.ui.shoppers.mvp.implView.ImplQuerytuijianListView;
import com.yusong.club.ui.shoppers.mvp.implView.ImplRefershView;
import com.yusong.club.ui.shoppers.mvp.presenter.impl.IShopQueryImagePresenterImpl;
import com.yusong.club.ui.shoppers.mvp.presenter.impl.IShopQueryQianggouLeiPresenterImpl;
import com.yusong.club.ui.shoppers.mvp.presenter.impl.IShopQueryTuijianPresenterImpl;
import com.yusong.club.ui.shoppers.mvp.presenter.impl.ImplShopQuerytuijianListPresenterImpl;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         created at 2017/3/3 13:32.
 */

public class DiscoverFragment extends BaseFragment implements ImplRefershView,
        ImplQueryQiangouLeiView, ImplQueryTuijianLeiView, ImplQuerytuijianListView,
        BGARefreshLayout.BGARefreshLayoutDelegate {

    @InjectView(R.id.linear_top)
    LinearLayout linearTop;
    @InjectView(R.id.not_network_refresh)
    Button notNetworkRefresh;
    @InjectView(R.id.not_netword_layout)
    RelativeLayout notNetwordLayout;
    @InjectView(R.id.tuijian_image)
    ImageView tuijianImage;
    private List<Integer> list = new ArrayList<>();
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.banner_business)
    Banner bannerBusiness;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.qianggou_list)
    RecyclerView qianggouList;
    @InjectView(R.id.youhui_list)
    RecyclerView youhuiList;
    @InjectView(R.id.tuijian_list)
    RecyclerView tuijianList;
    @InjectView(R.id.shop_fragment_refrsh)
    BGARefreshLayout shopFragmentRefrsh;
    @InjectView(R.id.tuijian_layout)
    LinearLayout tuijianLayout;
    @InjectView(R.id.tuijian_name_tv)
    TextView tuijianNameTv;
    private List<TuiJianBean.Commodity> tuiJianList = new ArrayList<TuiJianBean.Commodity>();
    private int categoryId = -1;
    private TuijianAdapter tuijianAdapter;

    private IShopQueryImagePresenterImpl imagePresenterImpl;//查询轮播图
    private IShopQueryQianggouLeiPresenterImpl queryQianggouLeiImpl;//抢购商品分类
    private IShopQueryTuijianPresenterImpl shopQueryTuijianPresenterImpl;//推荐商品
    private ImplShopQuerytuijianListPresenterImpl shopQuerytuijianListPresenterImpl;//推荐商品列表

    @OnClick(R.id.not_network_refresh)
    void refresh() {//断网刷新
        imagePresenterImpl.queryImageList();//轮播图
        queryQianggouLeiImpl.queryQianggouDalei();//抢购
        shopQueryTuijianPresenterImpl.queryTuijianLei(2);//推荐商品
        tuiJianList.clear();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        imagePresenterImpl.queryImageList();//轮播图
        queryQianggouLeiImpl.queryQianggouDalei();//抢购
        shopQueryTuijianPresenterImpl.queryTuijianLei(2);//推荐商品
        tuiJianList.clear();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (categoryId == -1) {
            imagePresenterImpl.queryImageList();//轮播图
        } else {
            shopQuerytuijianListPresenterImpl.queryTuijianList(categoryId, tuiJianList.size(), 10);
        }
        return true;
    }


    @Override
    public void refreshTuiJianList(List<TuiJianBean.Commodity> data) {//推荐商品
        closeRefresh();
        if (data.size() > 0) {
            tuiJianList.addAll(data);
            tuijianAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshTuijian(List<TuiJianBean> data) {//特惠商品
        closeRefresh();
        if (data.size() > 1) {//推荐商品
            categoryId = data.get(data.size() - 1).getId();
            data.remove(data.size() - 1);
            youhuiList.setAdapter(new TehuiAdapter(data, getActivity()));
            tuijianNameTv.setText(data.get(data.size() - 1).getCategoryName());
            shopQuerytuijianListPresenterImpl.queryTuijianList(categoryId, 0, 10);
        } else {
            if (data.size() > 0) {
                tuijianLayout.setVisibility(View.VISIBLE);
                tuijianNameTv.setText(data.get(0).getCategoryName());
                GlideImgManager.loadImage(mContext,data.get(0).getIconPath(),tuijianImage);
                categoryId = data.get(data.size() - 1).getId();
                shopQuerytuijianListPresenterImpl.queryTuijianList(categoryId, 0, 10);
            }
        }
    }

    @Override
    public void referImageList(List<String> listStr) {//轮播图
        closeRefresh();
        bannerBusiness.update(listStr);
        shopFragmentRefrsh.setVisibility(View.VISIBLE);
        notNetwordLayout.setVisibility(View.GONE);
    }

    @Override
    public void refreshQianggou(List<QiangGouBean> data) {//抢购
        closeRefresh();
        qianggouList.setAdapter(new QianggouAdapter(data, getActivity()));
    }

    @Override
    public void showProgressDialog() {
        closeRefresh();
    }


    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        shopFragmentRefrsh.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        shopFragmentRefrsh.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.activity_shop_business, null);
    }

    @Override
    public void initData() {
        linearTop.setVisibility(View.GONE);
        list.add(R.mipmap.ic_launcher);
        bannerBusiness.setImageLoader(new GlideImgManager());
        bannerBusiness.setImages(list);
        bannerBusiness.start();
        initRefreshLayout();
        imagePresenterImpl = new IShopQueryImagePresenterImpl(this, getActivity());
        imagePresenterImpl.queryImageList();//轮播图

        queryQianggouLeiImpl = new IShopQueryQianggouLeiPresenterImpl(this, getActivity());
        queryQianggouLeiImpl.queryQianggouDalei();//抢购

        shopQueryTuijianPresenterImpl = new IShopQueryTuijianPresenterImpl(this, getActivity());
        shopQueryTuijianPresenterImpl.queryTuijianLei(2);//推荐商品

        shopQuerytuijianListPresenterImpl = new ImplShopQuerytuijianListPresenterImpl(this, getActivity());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        qianggouList.setLayoutManager(gridLayoutManager);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(), 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        youhuiList.setLayoutManager(gridLayoutManager2);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        tuijianAdapter = new TuijianAdapter(tuiJianList, mContext);
        tuijianList.setLayoutManager(gridLayoutManager3);
        tuijianList.setAdapter(tuijianAdapter);
        tuijianList.addItemDecoration(new SpaceItemDecoration(2, 2));
        tuijianAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(mContext, CommodityDetailsActivity.class);
                intent.putExtra("itemId", ((TuiJianBean.Commodity) data).getId());
                intent.putExtra("isQG", 1);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void initListener() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        imagePresenterImpl.onDestroy();
        queryQianggouLeiImpl.onDestroy();
        shopQueryTuijianPresenterImpl.onDestroy();
        shopQuerytuijianListPresenterImpl.onDestroy();
        ButterKnife.reset(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void leiClose() {
        closeRefresh();
    }

    @Override
    public void tuijianClose() {
        closeRefresh();
    }

    @Override
    public void refreshuituijianClose() {
        closeRefresh();
    }

    @Override
    public void imageListClose() {
        closeRefresh();
        shopFragmentRefrsh.setVisibility(View.GONE);
        notNetwordLayout.setVisibility(View.VISIBLE);
    }

    public void closeRefresh() {
        shopFragmentRefrsh.endRefreshing();
        shopFragmentRefrsh.endLoadingMore();
    }
}
