package com.yusong.community.ui.shoppers.used.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.school.teacher.adapter.PhotoAdapter;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.ui.shoppers.used.bean.UsedBean;
import com.yusong.community.ui.shoppers.used.mvp.implView.ImplUsedView;
import com.yusong.community.ui.shoppers.used.mvp.presenter.impl.ImplUsedPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         created at 2017/3/16 19:14.
 *         二手市场页面
 */

public class UsedFragment extends BaseFragment implements ImplUsedView {
    @InjectView(R.id.ershou_tablayout)
    TabLayout ershouTablayout;
    @InjectView(R.id.ershou_page)
    ViewPager ershouPage;
    @InjectView(R.id.not_data_message)
    TextView notDataMessage;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    @InjectView(R.id.not_network_message)
    TextView notNetworkMessage;
    @InjectView(R.id.not_network_refresh)
    Button notNetworkRefresh;
    @InjectView(R.id.not_netword_layout)
    RelativeLayout notNetwordLayout;

    @OnClick(R.id.not_network_refresh)
    void click() {
        fragmentList.clear();
        titleList.clear();
        implUsedPresenterImpl.queryUsedFenlei();
    }

    private PhotoAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private ImplUsedPresenterImpl implUsedPresenterImpl;

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_ershou, null);
    }

    @Override
    public void initData() {
        fragmentList = new ArrayList<>();//页面列表
        titleList = new ArrayList<>();  //将名称加载tab名字列表
        ershouTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置TabLayout的模式
        implUsedPresenterImpl = new ImplUsedPresenterImpl(this, getActivity());
        implUsedPresenterImpl.queryUsedFenlei();
    }


    @Override
    public void colse() {
        notNetwordLayout.setVisibility(View.VISIBLE);
        notDataLayout.setVisibility(View.GONE);
        ershouTablayout.setVisibility(View.GONE);
        ershouPage.setVisibility(View.GONE);
    }

    @Override
    public void queryFenleiSucced(List<FenLeiBean> data) {
        notNetwordLayout.setVisibility(View.GONE);
        notDataLayout.setVisibility(View.GONE);
        ershouTablayout.setVisibility(View.VISIBLE);
        ershouPage.setVisibility(View.VISIBLE);
        FenLeiBean bean = new FenLeiBean();
        bean.setCategoryName("全部");
        bean.setId(0);
        data.add(0, bean);
        if (data.size() >=4) {
            ershouTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            ershouTablayout.setTabMode(TabLayout.MODE_FIXED);
        }
        if (data.size() > 1) {
            for (int i = 0; i < data.size(); i++) {
                FenLeiBean fenLeiBean = data.get(i);
                UsedItemFragment usedItemFragment = new UsedItemFragment();
                usedItemFragment.setCategoryId(fenLeiBean.getId());
                fragmentList.add(usedItemFragment);
                titleList.add(fenLeiBean.getCategoryName());
                ershouTablayout.addTab(ershouTablayout.newTab().setText(fenLeiBean.getCategoryName()));
            }
            adapter = new PhotoAdapter(getChildFragmentManager(), fragmentList, titleList);
            ershouPage.setAdapter(adapter);
            ershouTablayout.setupWithViewPager(ershouPage);//TabLayout加载viewpager
            ershouTablayout.setVisibility(View.VISIBLE);
        } else if (data.size() == 1) {
            FenLeiBean fenLeiBean = data.get(0);
            UsedItemFragment usedItemFragment = new UsedItemFragment();
            usedItemFragment.setCategoryId(fenLeiBean.getId());
            fragmentList.add(usedItemFragment);
            titleList.add(fenLeiBean.getCategoryName());
            ershouTablayout.addTab(ershouTablayout.newTab().setText(fenLeiBean.getCategoryName()));
            adapter = new PhotoAdapter(getActivity().getSupportFragmentManager(), fragmentList, titleList);
            ershouPage.setAdapter(adapter);
            ershouTablayout.setupWithViewPager(ershouPage);//TabLayout加载viewpager
            ershouTablayout.setVisibility(View.GONE);
        } else {
            MyApplication.showMessage("暂无分类");
        }
    }

    @Override
    public void quertListSucced(List<UsedBean> data) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }
}
