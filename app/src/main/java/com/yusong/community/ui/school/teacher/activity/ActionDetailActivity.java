package com.yusong.community.ui.school.teacher.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.mvp.entity.ActionDetail;
import com.yusong.community.ui.school.mvp.entity.DetailComment;
import com.yusong.community.ui.school.mvp.entity.JoinedResult;
import com.yusong.community.ui.school.mvp.implView.IActionDetailActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.IActionDetailActivityPresenterImpl;
import com.yusong.community.ui.school.teacher.adapter.ActionDetailCommentAdapter;
import com.yusong.community.ui.school.teacher.adapter.DetailImageAdapter;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.DateUtil;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ActionDetailActivity extends BaseActivity implements View.OnClickListener, IActionDetailActivityView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.iv_header)
    ImageView ivHeader;
    @InjectView(R.id.item_name)
    TextView itemName;
    @InjectView(R.id.item_state)
    TextView itemState;
    @InjectView(R.id.item_time)
    TextView itemTime;
    @InjectView(R.id.item_surplus)
    TextView itemSurplus;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.tv_introduce)
    TextView tvIntroduce;
    @InjectView(R.id.gv_List)
    RecyclerView gvList;
    @InjectView(R.id.tv_part_people)
    TextView tvPartPeople;
    @InjectView(R.id.rv_List)
    RecyclerView rvList;
    @InjectView(R.id.item_join)
    TextView itemJoin;
    @InjectView(R.id.linear_good)
    LinearLayout linearGood;
    @InjectView(R.id.item_talk)
    TextView itemTalk;
    @InjectView(R.id.linear_talk)
    LinearLayout linearTalk;
    @InjectView(R.id.activity_action_detail)
    LinearLayout activityActionDetail;
    @InjectView(R.id.item_action_name)
    TextView itemActionName;
    @InjectView(R.id.iv_part)
    ImageView ivPart;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private Context mContext;
    private IActionDetailActivityPresenterImpl mPresenter;
    private int activityId = 0;
    private List<DetailComment> datas;
    private ActionDetailCommentAdapter mActionDetailCommentAdapter;
    private DetailImageAdapter mDetailImageAdapter;
    private List<String> images;
    private int SchoolId;
    private boolean flagPart = false;
    private int roleTag = 0;

    @Override
    protected int layoutId() {
        return R.layout.activity_action_detail;
    }

    @Override
    protected void adaptiveSystemVersions() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        mContext = ActionDetailActivity.this;
//        tvNoData.setVisibility(View.GONE);
        datas = new ArrayList<>();
        images = new ArrayList<>();
        linearGood.setOnClickListener(this);
        linearTalk.setOnClickListener(this);
        if (getIntent() != null && getIntent().getIntExtra("activityId", 0) != 0) {
            activityId = getIntent().getIntExtra("activityId", 0);
        }
        if (getIntent() != null && getIntent().getIntExtra("SchoolId", 0) != 0) {
            SchoolId = getIntent().getIntExtra("SchoolId", 0);
        }
        if (getIntent() != null && getIntent().getIntExtra("roleTag", 0) != 0) {
            roleTag = getIntent().getIntExtra("roleTag", 0);
        }
        mPresenter = new IActionDetailActivityPresenterImpl(this, this);
        imMsg.setVisibility(View.GONE);
        linearBack.setOnClickListener(this);
        tvTitle.setText("活动");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(linearLayoutManager);
        mActionDetailCommentAdapter = new ActionDetailCommentAdapter(datas, this);
        rvList.setAdapter(mActionDetailCommentAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        gvList.setLayoutManager(linearLayoutManager1);
        mDetailImageAdapter = new DetailImageAdapter(images, mContext);
        gvList.setAdapter(mDetailImageAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.linear_good:
                switch (roleTag) {
                    case 1:
                        mPresenter.joinActy(CacheUtils.getToken(mContext), activityId);
                        break;
                    case 2:
                        mPresenter.joinActy(CacheUtils.getToken(mContext), activityId);
                        break;
                }
                break;
            case R.id.linear_talk:
                Intent intent = new Intent(mContext, ActionCommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ActionId", activityId);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getDetailComment(CacheUtils.getToken(mContext), activityId, 0, 5);
        mPresenter.getActionDeatail(CacheUtils.getToken(mContext), activityId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    public void getActionDetail(ActionDetail data) {
        switch (data.getStatus()) {
            case 1:
                itemState.setText("未开始");
                itemSurplus.setVisibility(View.VISIBLE);
                itemSurplus.setText("剩余:" + DateUtil.getMinusToString(data.getBeginTime()));
                break;
            case 2:
                itemState.setText("进行中");
                itemSurplus.setVisibility(View.VISIBLE);
                itemSurplus.setText("");
                break;
            case 3:
                itemSurplus.setVisibility(View.INVISIBLE);
                itemSurplus.setText("");
                itemState.setText("已结束");
                break;
        }
        int isJoined = data.getIsJoined();
        switch (isJoined) {
            case 0:
                ivPart.setImageResource(R.mipmap.ic_undian_zan);
                itemJoin.setTextColor(getResources().getColor(R.color.font_color_4));
                break;
            case 1:
                ivPart.setImageResource(R.mipmap.ic_dian_zan);
                itemJoin.setTextColor(getResources().getColor(R.color.font_color_orange));
        }
        if (data.getMembersName() != null && !data.getMembersName().equals("")) {
            tvPartPeople.setText("参加人员:\t" + data.getMembersName());
        } else {
            tvPartPeople.setText("参加人员:\t" + "暂无");
        }
        itemTalk.setText("评论 (" + data.getCommentAmount() + ")");
        itemName.setText(data.getCreateUserName());
        itemTime.setText(data.getCreateTime());
        itemActionName.setText(data.getActivityName());
        tvTime.setText(DateUtil.getDateYearNewToString(data.getBeginTime()) + "-" + DateUtil.getDateYearNewToString(data.getEndTime()));
        tvIntroduce.setText("活动简介:\t" + data.getMemo());
        if (data.getCreateUserIconPath() != null && !(data.getCreateUserIconPath().equals(""))) {
            GlideImgManager.loadCircleImage(mContext, data.getCreateUserIconPath(), ivHeader);
        } else {
            ivHeader.setImageResource(R.mipmap.school_four);
        }
        images.clear();
        if (data.getImagelist() != null && data.getImagelist().size() != 0) {
            for (String path : data.getImagelist()) {
                if (path != null && !path.equals("")) {
                    images.add(path);
                }
            }
        }
        mDetailImageAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDetailCommentList(List<DetailComment> detailComments) {
        if (detailComments != null && detailComments.size() != 0) {
            datas.clear();
            datas.addAll(detailComments);
            mActionDetailCommentAdapter.notifyDataSetChanged();
            notDataLayout.setVisibility(View.GONE);
        } else {
            notDataLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void partAction(JoinedResult data) {
        if (data.getIsJoined() == 0) {
            ivPart.setImageResource(R.mipmap.ic_undian_zan);
            itemJoin.setTextColor(getResources().getColor(R.color.font_color_4));
        } else if (data.getIsJoined() == 1) {
            ivPart.setImageResource(R.mipmap.ic_dian_zan);
            itemJoin.setTextColor(getResources().getColor(R.color.font_color_orange));
        }
        mPresenter.getActionDeatail(CacheUtils.getToken(mContext), activityId);
    }


    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }
}
