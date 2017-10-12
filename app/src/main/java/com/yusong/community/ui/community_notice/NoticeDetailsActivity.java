package com.yusong.community.ui.community_notice;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.community_notice.entity.NoticeBean;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-09-21.
 * @describe: null
 */

public class NoticeDetailsActivity extends BaseActivity {
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
    @InjectView(R.id.notice_title_tv)
    TextView noticeTitleTv;
    @InjectView(R.id.notice_date_tv)
    TextView noticeDateTv;
    @InjectView(R.id.notice_content_tv)
    TextView noticeContentTv;

    @OnClick(R.id.ll_back)
    void blackClick() {
        this.finish();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_notice_details;
    }

    @Override
    public void initView() {
        tvTitle.setText("公告详情");
    }

    @Override
    public void initData() {
        NoticeBean noticeBean = (NoticeBean) getIntent().getSerializableExtra("NoticeBean");
        if (noticeBean != null) {
            noticeTitleTv.setText(noticeBean.getTitle());
            noticeDateTv.setText(noticeBean.getCreateTime());
            noticeContentTv.setText(noticeBean.getContent());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
