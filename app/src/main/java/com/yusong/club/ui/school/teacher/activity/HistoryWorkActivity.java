package com.yusong.club.ui.school.teacher.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.implView.IHistoryWorkActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.IHistoryWorkActivityPresentImpl;
import com.yusong.club.ui.school.teacher.adapter.HomeworkAdapter;
import com.yusong.club.ui.school.teacher.bean.ClassHomework;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.DateUtil;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HistoryWorkActivity extends BaseActivity implements View.OnClickListener, IHistoryWorkActivityView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.lin_date)
    LinearLayout linDate;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private List<ClassHomework> data;
    private HomeworkAdapter adapter;
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.lv_history)
    ListView lvHistory;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    private Context mContext;
    private IHistoryWorkActivityPresentImpl mPresenter;
    private String date = "";
    private String upDate = "";
    private TimePickerView pvTime;

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
    protected void initListener() {
        linearBack.setOnClickListener(this);
        imMsg.setOnClickListener(this);
        linDate.setOnClickListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher_history_work;
    }

    @Override
    public void initView() {
        mContext = HistoryWorkActivity.this;
        initRefreshLayout();
        upDate = DateUtil.getDate();
        tvDate.setText(DateUtil.getHidate(upDate));
        //隐藏软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(getWindow().getDecorView(), InputMethodManager.SHOW_FORCED);
        tvTitle.setText("历史作业");
        imMsg.setImageResource(R.mipmap.teacher_date);
        mPresenter = new IHistoryWorkActivityPresentImpl(this, this);
        mPresenter.searchHistoryHomework(CacheUtils.getToken(mContext), DateUtil.getDate());

    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        mBGALayout.setDelegate(this);//设置代理
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        mBGALayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void initData() {
        data = new ArrayList<>();
        adapter = new HomeworkAdapter(data, this);
        lvHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        initDateInfo();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_msg:
                pvTime.show();
//                showDate();//显示日历
                break;
            case R.id.linear_back:
                finish();
                break;
            case R.id.lin_date:
                upDate = DateUtil.getLastDate(upDate);
                tvDate.setText(DateUtil.getHidate(upDate));
                mPresenter.searchHistoryHomework(CacheUtils.getToken(mContext), upDate);
                break;
        }
    }

    private void initDateInfo() {
        Calendar selectedDate = Calendar.getInstance();
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvDate.setText(getTime(date));
                upDate = getTimeNew(date);
                mPresenter.searchHistoryHomework(CacheUtils.getToken(mContext), upDate);

            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentSize(20)
                .setDate(selectedDate)
                .setRange(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.YEAR))//默认是1900-2100年
                .build();
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    public static String getTimeNew(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    public void getClassHomework(List<ClassHomework> datas) {
        if (datas != null && datas.size() != 0) {
            data.clear();
            data.addAll(datas);
            notDataLayout.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        } else {
            notDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void closeRefresh() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
        }
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.searchHistoryHomework(CacheUtils.getToken(mContext), upDate);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
