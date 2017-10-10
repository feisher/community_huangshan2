package com.yusong.club.ui.evaluate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.yusong.club.ui.charge.view.BaseDialog;
import com.yusong.club.ui.evaluate.EvaluateBean;
import com.yusong.club.ui.evaluate.adpter.EvaluateAdapter;
import com.yusong.club.ui.evaluate.mvp.implview.EvaluateComentView;
import com.yusong.club.ui.evaluate.mvp.presenter.impl.EvaluateComentPresenterImpl;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-08-25.
 * @describe: 表扬批评详情
 */

public class EvaluateDeatailsActivity extends BaseActivity implements EvaluateComentView {

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
    @InjectView(R.id.time_tv)
    TextView timeTv;
    @InjectView(R.id.tel_tv)
    TextView telTv;
    @InjectView(R.id.tel_layout)
    LinearLayout telLayout;
    @InjectView(R.id.comment_size_tv)
    TextView commentSizeTv;
    @InjectView(R.id.all_comment_layout)
    LinearLayout allCommentLayout;
    @InjectView(R.id.evaluate_recyclerview)
    RecyclerView evaluateRecyclerview;
    private EvaluateComentPresenterImpl presenter;
    private EvaluateAdapter adapter;
    private List<EvaluateBean> list = new ArrayList<EvaluateBean>();

    @OnClick({R.id.ll_back, R.id.all_comment_layout, R.id.tel_layout})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.all_comment_layout:
                startActivity(new Intent(this, EvaluateAllCommentActivity.class));
                break;
            case R.id.tel_layout:
                final BaseDialog dialog = new BaseDialog(this);
                dialog.setTitle("拨打电话");
                dialog.setMessage(String.format("你确定拨打：%s物业电话？", CacheUtils.getTenementTel(this)));
                dialog.setNegativeButton("不了", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("拨打", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        callDirectly(CacheUtils.getTenementTel(EvaluateDeatailsActivity.this));

                    }
                });
                break;
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_evaluate_deatails;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onStart() {
        presenter.queryEvaluateComent(0, 5);
        super.onStart();
    }

    @Override
    public void initView() {
        tvTitle.setText("服务介绍");
        telTv.setText(CacheUtils.getTenementTel(this));
        timeTv.setText(CacheUtils.getServiceTime(this));
        presenter = new EvaluateComentPresenterImpl(this, this);
        adapter = new EvaluateAdapter(list, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        evaluateRecyclerview.setAdapter(adapter);
        evaluateRecyclerview.setLayoutManager(gridLayoutManager);
        evaluateRecyclerview.addItemDecoration(new SpaceItemDecoration(10, 0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void querySucces(List<EvaluateBean> data) {
        if (list.size() > 0) {
            list.clear();
        }
        list.addAll(data);
        if (list.size() > 0) {
            commentSizeTv.setText(String.format("查看所有评论（%d）", list.get(0).getEvaluateCount()));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void queryError() {

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
