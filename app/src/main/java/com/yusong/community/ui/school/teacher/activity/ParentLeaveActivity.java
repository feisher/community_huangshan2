package com.yusong.community.ui.school.teacher.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joybar.librarycalendar.data.CalendarDate;
import com.joybar.librarycalendar.fragment.CalendarViewFragment;
import com.joybar.librarycalendar.fragment.CalendarViewPagerFragment;
import com.joybar.librarycalendar.utils.DateUtils;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.mvp.entity.TeacherLeave;
import com.yusong.community.ui.school.mvp.implView.IParentLeaveActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.IParentLeaveActivityPresenterImpl;
import com.yusong.community.ui.school.teacher.adapter.ShowLeaveAdapter;
import com.yusong.community.ui.school.teacher.view.MyRecyclerView;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.DateUtil;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.joybar.librarycalendar.adapter.CalendarViewPagerAdapter.NUM_ITEMS_CURRENT;

public class ParentLeaveActivity extends BaseActivity implements CalendarViewPagerFragment.OnPageChangeListener, CalendarViewFragment.OnDateClickListener,
        CalendarViewFragment.OnDateCancelListener, CalendarViewFragment.OnWeekChooseListener, View.OnClickListener, IParentLeaveActivityView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.rv_List)
    MyRecyclerView rvList;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private ShowLeaveAdapter mShowLeaveAdapter;
    private List<CalendarDate> mListDate = new ArrayList<>();
    private int mThisMonthPosition = DateUtils.getYear() * 12 + DateUtils.getMonth() - 1;//---100 -position
    private int number = mThisMonthPosition - NUM_ITEMS_CURRENT;
    private Context mContext;
    private List<TeacherLeave> datas;
    private boolean isRefresh = true;
    private IParentLeaveActivityPresenterImpl mPresenter;
    private int yearNew=Calendar.YEAR;
    private int monthNew=Calendar.MONTH;

    @Override
    protected int layoutId() {
        return R.layout.activity_parent_leave;
    }

    @Override
    public void initData() {
        mContext = ParentLeaveActivity.this;
        imMsg.setImageResource(R.mipmap.add);
        imMsg.setOnClickListener(this);
        linearBack.setOnClickListener(this);
        datas = new ArrayList<>();
        initFragment();
        if (getIntent() != null && getIntent().getStringExtra("SchoolName") != null) {
            tvTitle.setText(getIntent().getStringExtra("SchoolName"));
        }
        mShowLeaveAdapter = new ShowLeaveAdapter(datas, mContext);
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.setAdapter(mShowLeaveAdapter);
        initRefreshLayout();
        mPresenter = new IParentLeaveActivityPresenterImpl(this, this);
    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        mBGALayout.setDelegate(this);//设置代理
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        mBGALayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        Fragment fragment = CalendarViewPagerFragment.newInstance(true);
        tx.replace(R.id.fl_content, fragment);
        tx.commit();
    }

    @Override
    protected void onResume() {
        mPresenter.queryLeaveApllyByDate(CacheUtils.getToken(mContext), null, (DateUtil.getMonthStartAndEndDate())[0], (DateUtil.getMonthStartAndEndDate())[1], 0, 10);
        super.onResume();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.im_msg:
                startActivity(new Intent(mContext, CreateParentLeaveActivity.class));
                break;
            case R.id.linear_back:
                finish();
                break;
        }

    }

    @Override
    public void onDateCancel(CalendarDate calendarDate) {
        int count = mListDate.size();
        for (int i = 0; i < count; i++) {
            CalendarDate date = mListDate.get(i);
            if (date.getSolar().solarDay == calendarDate.getSolar().solarDay) {
                mListDate.remove(i);
                break;
            }
        }
    }

    @Override
    public void onDateClick(CalendarDate calendarDate) {
        int year = calendarDate.getSolar().solarYear;
        int month = calendarDate.getSolar().solarMonth;
        int day = calendarDate.getSolar().solarDay;
        String date = String.format("%02d-%02d-%02d", year, month, day);
        mListDate.add(calendarDate);
        isRefresh = true;
        mPresenter.queryLeaveApllyByDate(CacheUtils.getToken(mContext), null, date + " 00:00:00", date + " 23:59:59", 0, 10);

    }

    public void onPageChange(int year, int month) {
        mListDate.clear();
        isRefresh=true;
        yearNew=year;
        monthNew=month;
        if (year == Calendar.YEAR && month == Calendar.MONTH) {
            CalendarDate calendarDate = new CalendarDate();
            calendarDate.getSolar().solarYear = year;
            calendarDate.getSolar().solarMonth = month;
            calendarDate.getSolar().solarDay = Calendar.DAY_OF_MONTH;
            mListDate.add(calendarDate);
        }
        if (mListDate != null && mListDate.size() != 0) {
            int year1 = mListDate.get(0).getSolar().solarYear;
            int month1 = mListDate.get(0).getSolar().solarMonth;
            int day1 = mListDate.get(0).getSolar().solarDay;
            String date = year + "-" + month + "-" + day1 + " 00:00:00";
            String date1 = year + "-" + month + "-" + day1 + " 23:59:59";
            mPresenter.queryLeaveApllyByDate(CacheUtils.getToken(mContext), null, DateUtil.getLeaveDate(date), DateUtil.getLeaveDate(date1), 0, 10);
        } else {
            mPresenter.queryLeaveApllyByDate(CacheUtils.getToken(mContext), null, (DateUtil.getMonthStartAndEndDateNew(yearNew,monthNew))[0], (DateUtil.getMonthStartAndEndDateNew(yearNew,monthNew))[1], 0, 10);
        }
    }

    @Override
    public void onWeekChoose(List<CalendarDate> mListData) {

    }

    @Override
    public void getLeaveParentInfo(List<TeacherLeave> data) {
        if (isRefresh) {
            datas.clear();
            if (data != null && data.size() != 0) {
                notDataLayout.setVisibility(View.GONE);
                datas.addAll(data);
            } else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
        } else {
            notDataLayout.setVisibility(View.GONE);
            datas.addAll(data);
        }
        mShowLeaveAdapter.notifyDataSetChanged();

    }

    @Override
    public void closeRefreshing() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
            mBGALayout.endLoadingMore();
        }
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        if (mListDate != null && mListDate.size() != 0) {
            int year = mListDate.get(0).getSolar().solarYear;
            int month = mListDate.get(0).getSolar().solarMonth;
            int day = mListDate.get(0).getSolar().solarDay;
            String date = year + "-" + month + "-" + day + " 00:00:00";
            String date1 = year + "-" + month + "-" + day + " 23:59:59";
            mPresenter.queryLeaveApllyByDate(CacheUtils.getToken(mContext), null, DateUtil.getLeaveDate(date), DateUtil.getLeaveDate(date1), 0, 10);
        } else {
            mPresenter.queryLeaveApllyByDate(CacheUtils.getToken(mContext), null, (DateUtil.getMonthStartAndEndDateNew(yearNew,monthNew))[0], (DateUtil.getMonthStartAndEndDateNew(yearNew,monthNew))[1], 0, 10);
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        if (mListDate != null && mListDate.size() != 0) {
            int year = mListDate.get(0).getSolar().solarYear;
            int month = mListDate.get(0).getSolar().solarMonth;
            int day = mListDate.get(0).getSolar().solarDay;
            String date = year + "-" + month + "-" + day + " 00:00:00";
            String date1 = year + "-" + month + "-" + day + " 23:59:59";
            mPresenter.queryLeaveApllyByDate(CacheUtils.getToken(mContext), null, DateUtil.getLeaveDate(date), DateUtil.getLeaveDate(date1), datas.size(), 10);
        } else {
            mPresenter.queryLeaveApllyByDate(CacheUtils.getToken(mContext), null, (DateUtil.getMonthStartAndEndDateNew(yearNew,monthNew))[0], (DateUtil.getMonthStartAndEndDateNew(yearNew,monthNew))[1], datas.size(), 10);
        }
        return true;
    }
}
