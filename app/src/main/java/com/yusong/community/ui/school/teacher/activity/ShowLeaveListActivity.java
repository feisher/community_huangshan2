package com.yusong.community.ui.school.teacher.activity;

import android.content.Context;
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
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.mvp.entity.TeacherLeave;
import com.yusong.community.ui.school.mvp.implView.IShowLeaveListActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.IShowLeaveListActivityPresenterImpl;
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

public class ShowLeaveListActivity extends BaseActivity implements CalendarViewPagerFragment.OnPageChangeListener, CalendarViewFragment.OnDateClickListener,
        CalendarViewFragment.OnDateCancelListener, CalendarViewFragment.OnWeekChooseListener, View.OnClickListener, IShowLeaveListActivityView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.rv_List)
    MyRecyclerView rvList;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    private List<CalendarDate> mListDate = new ArrayList<>();
    private ShowLeaveAdapter mShowLeaveAdapter;
    private Context mContext;
    private List<TeacherLeave> datas;
    private IShowLeaveListActivityPresenterImpl mPresenter;
    private boolean isRefresh = true;
    private int yearNew=Calendar.YEAR;
    private int monthNew=Calendar.MONTH;
    @Override
    protected int layoutId() {
        return R.layout.activity_show_leave_list;
    }

    @Override
    public void initData() {
        datas = new ArrayList<>();
        imMsg.setVisibility(View.GONE);
        mContext = ShowLeaveListActivity.this;
        linearBack.setOnClickListener(this);
        initRefreshLayout();
        mPresenter = new IShowLeaveListActivityPresenterImpl(this, mContext);
        initFragment();
        mShowLeaveAdapter = new ShowLeaveAdapter(datas, mContext);
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.setAdapter(mShowLeaveAdapter);
        tvTitle.setText("请假");
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
    public void onClick(View view) {
        switch (view.getId()) {
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
        mListDate.clear();
        int year = calendarDate.getSolar().solarYear;
        int month = calendarDate.getSolar().solarMonth;
        int day = calendarDate.getSolar().solarDay;
        String date = year + "-" + month + "-" + day + " 00:00:00";
        String date1 = year + "-" + month + "-" + day + " 23:59:59";
        mListDate.add(calendarDate);
        isRefresh = true;
        mPresenter.queryLeaveApllyByDate(CacheUtils.getToken(mContext), null, DateUtil.getLeaveDate(date), DateUtil.getLeaveDate(date1), 0, 10);

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void onWeekChoose(List<CalendarDate> mListData) {


    }

    @Override
    public void getLeaveParentInfo(List<TeacherLeave> data) {
        if (isRefresh) {
            if (data != null && data.size() != 0) {
                notDataLayout.setVisibility(View.GONE);
            } else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
            datas.clear();
            datas.addAll(data);
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