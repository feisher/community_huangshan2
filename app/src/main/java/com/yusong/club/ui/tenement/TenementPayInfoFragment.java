package com.yusong.club.ui.tenement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.tenement.adapter.TenementInfoAdapter;
import com.yusong.club.ui.tenement.entity.TenementBean;
import com.yusong.club.ui.tenement.mvp.presenter.impl.TenementPresenterImpl;
import com.yusong.club.ui.tenement.mvp.implview.TenementView;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Mr_Peng
 * @created at 2017-08-25.
 * @describe: 代缴物业信息
 */

public class TenementPayInfoFragment extends BaseFragment implements TenementView {
    @InjectView(R.id.info_recycler_view)
    RecyclerView infoRecyclerView;

    private TenementPresenterImpl presenter;
    private List<TenementBean> beanList = new ArrayList<TenementBean>();
    private TenementInfoAdapter adapter;

    public List<TenementBean> getBeanList() {
        return beanList;
    }

    public void refreshView() {
        EventBus.getDefault().post(new EventTenement());
        adapter.notifyDataSetChanged();
    }

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_tenement_pay_info, null);
    }

    @Override
    public void onStart() {
        if (beanList.size() > 0) {
            beanList.clear();
        }
        presenter.queryTenementPay(CacheUtils.getCommunityId(getActivity()), CacheUtils.getProprietorId(getActivity()), 0);
        super.onStart();
    }

    @Override
    public void initData() {
        adapter = new TenementInfoAdapter(beanList, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        infoRecyclerView.setAdapter(adapter);
        infoRecyclerView.setLayoutManager(layoutManager);
        presenter = new TenementPresenterImpl(this, getActivity());
        infoRecyclerView.addItemDecoration(new SpaceItemDecoration(5, 0));
    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                TenementBean bean = beanList.get(position);
                if (bean.getIsSelect() == 0) {
                    bean.setIsSelect(1);
                } else {
                    bean.setIsSelect(0);
                }
                EventBus.getDefault().post(new EventTenement());
                adapter.notifyDataSetChanged();
            }
        });
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
        beanList.clear();
        presenter.onDestroy();
        ButterKnife.reset(this);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void queryTenementSucces(List<TenementBean> data) {
        if (data.size() > 0) {
            data.get(0).setIsSelect(1);
        }
        beanList.addAll(data);
        adapter.notifyDataSetChanged();
        EventBus.getDefault().post(new EventTenement());
    }
}
