package com.yusong.community.ui.tenement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.tenement.adapter.TenementHistoryAdapter;
import com.yusong.community.ui.tenement.entity.TenementBean;
import com.yusong.community.ui.tenement.mvp.presenter.impl.TenementPresenterImpl;
import com.yusong.community.ui.tenement.mvp.implview.TenementView;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Mr_Peng
 * @created at 2017-08-25.
 * @describe: 物业缴费历史
 */

public class TenementPayHistoryFragment extends BaseFragment implements TenementView {
    @InjectView(R.id.tenement_payhistory_list)
    RecyclerView tenementPayhistoryList;
    private TenementPresenterImpl presenter;
    private List<TenementBean> beanList = new ArrayList<TenementBean>();
    private TenementHistoryAdapter adapter;

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_tenement_payhistory, null);
    }

    @Override
    public void initData() {
        adapter = new TenementHistoryAdapter(beanList, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        tenementPayhistoryList.setAdapter(adapter);
        tenementPayhistoryList.setLayoutManager(layoutManager);
        tenementPayhistoryList.addItemDecoration(new SpaceItemDecoration(5, 0));
        presenter = new TenementPresenterImpl(this, getActivity());
    }

    @Override
    public void onStart() {
        if (beanList.size() > 0) {
            beanList.clear();
        }
        presenter.queryTenementPay(CacheUtils.getCommunityId(getActivity()), CacheUtils.getProprietorId(getActivity()), 1);
        super.onStart();
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
        beanList.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
