package com.yusong.club.ui.evaluate.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.evaluate.EvaluateBean;
import com.yusong.club.ui.evaluate.adpter.EvaluateAdapter;
import com.yusong.club.ui.evaluate.mvp.implview.EvaluateComentView;
import com.yusong.club.ui.evaluate.mvp.presenter.impl.EvaluateComentPresenterImpl;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-09-02.
 * @describe: 所有评论
 */

public class EvaluateAllCommentActivity extends BaseActivity implements EvaluateComentView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.find_et)
    EditText findEt;
    @InjectView(R.id.find_layout)
    RelativeLayout findLayout;
    @InjectView(R.id.left_radio)
    RadioButton leftRadio;
    @InjectView(R.id.center_radio)
    RadioButton centerRadio;
    @InjectView(R.id.right_radio)
    RadioButton rightRadio;
    @InjectView(R.id.radio_group)
    RadioGroup radioGroup;
    @InjectView(R.id.left_radio_address)
    RadioButton leftRadioAddress;
    @InjectView(R.id.right_radio_address)
    RadioButton rightRadioAddress;
    @InjectView(R.id.radio_group_address)
    RadioGroup radioGroupAddress;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.comment_recycler_view)
    RecyclerView commentRecyclerView;
    @InjectView(R.id.comment_refresh_layout)
    BGARefreshLayout commentRefreshLayout;
    private EvaluateComentPresenterImpl presenter;
    private EvaluateAdapter adapter;
    private List<EvaluateBean> list = new ArrayList<EvaluateBean>();
    private boolean isClear = true;

    @OnClick({R.id.ll_back})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_evaluate_comment;
    }

    @Override
    public void initView() {
        tvTitle.setText("所有评论");
        initRefreshLayout();
        presenter = new EvaluateComentPresenterImpl(this, this);
        adapter = new EvaluateAdapter(list, this);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        commentRecyclerView.setAdapter(adapter);
        commentRecyclerView.setLayoutManager(linearLayoutManager);
        commentRecyclerView.addItemDecoration(new SpaceItemDecoration(10, 0));
        commentRefreshLayout.setLinearLayoutManager(list,linearLayoutManager);
    }

    private void initRefreshLayout() {
        //设置代理
        commentRefreshLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        commentRefreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    protected void onStart() {
        presenter.queryEvaluateComent(0, 10);
        super.onStart();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void querySucces(List<EvaluateBean> data) {
        if (isClear) {
            if (list.size() > 0) {
                list.clear();
            }
        }
        list.addAll(data);
        adapter.notifyDataSetChanged();
        commentRefreshLayout.endRefreshing();
        commentRefreshLayout.endLoadingMore();
    }

    @Override
    public void queryError() {
        commentRefreshLayout.endRefreshing();
        commentRefreshLayout.endLoadingMore();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isClear = true;
        presenter.queryEvaluateComent(0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isClear = false;
        presenter.queryEvaluateComent(list.size(), 10);
        return true;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
