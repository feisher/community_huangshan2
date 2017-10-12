package com.yusong.community.ui.school.school.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.event.ReadNoticeEvent;
import com.yusong.community.ui.school.mvp.implView.NoticeDetailActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.NoticeDetailActivityPresenterImpl;
import com.yusong.community.utils.CacheUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.InjectView;

public class NoticeDetailActivity extends BaseActivity implements NoticeDetailActivityView {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.tv_notice_title)
    TextView tvNoticeTitle;
    @InjectView(R.id.tv_notice_time)
    TextView tvNoticeTime;
    @InjectView(R.id.tv_notice_content)
    TextView tvNoticeContent;
    @InjectView(R.id.activity_notice_detail)
    LinearLayout activityNoticeDetail;
    public String noticeTitle;
    public int noticeType;
    public String noticeContent;
    public String noticeCreateTime;
    public int noticeId;
    public NoticeDetailActivityPresenterImpl mPresenter;

    @Override
    protected int layoutId() {
        return R.layout.activity_notice_detail;
    }

    @Override
    public void initView() {
        tvTitle.setText("公告详情");
        llBack.setVisibility(View.VISIBLE);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
//    mIntent.putExtra("noticeType",noticeType);
//    mIntent.putExtra("noticeTitle",notice.getTitle());
//    mIntent.putExtra("noticeContent",notice.getContent());
//    mIntent.putExtra("noticeCreateTime",createTime);
//    mIntent.putExtra("noticeId",notice.getId());
    @Override
    public void initData() {
        noticeTitle = getIntent().getStringExtra("noticeTitle");
        noticeContent = getIntent().getStringExtra("noticeContent");
        noticeCreateTime = getIntent().getStringExtra("noticeCreateTime");
        noticeId = getIntent().getIntExtra("noticeId", -1);
        noticeType = getIntent().getIntExtra("noticeType",0);
        if (noticeType == 1) {//通知
            tvTitle.setText("通知详情");
        }else {//2公告
            tvTitle.setText("公告详情");
        }
        tvNoticeTitle.setText(noticeTitle);
        tvNoticeTime.setText("发布时间："+noticeCreateTime);
        tvNoticeContent.setText(noticeContent);
        mPresenter = new NoticeDetailActivityPresenterImpl(this, this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.commit4AddReadCount(CacheUtils.getToken(this),noticeId);
    }



    @Override
    public void showProgressDialog() {

    }

    @Override
    public void noticeReadedCallback() {
        EventBus.getDefault().post(new ReadNoticeEvent());
    }
}
