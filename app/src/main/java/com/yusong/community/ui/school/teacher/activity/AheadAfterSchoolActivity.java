package com.yusong.community.ui.school.teacher.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joybar.librarycalendar.data.CalendarDate;
import com.joybar.librarycalendar.fragment.CalendarViewFragment;
import com.joybar.librarycalendar.fragment.CalendarViewPagerFragment;
import com.joybar.librarycalendar.utils.DateUtils;
import com.loonggg.lib.alarmmanager.clock.AlarmManagerUtil;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.clock.ClockRecords;
import com.yusong.community.ui.clock.MyDataBaseHelper;
import com.yusong.community.ui.school.mvp.implView.IQueryAheadView;
import com.yusong.community.ui.school.mvp.presenter.impl.IQueryAheadPresenterImpl;
import com.yusong.community.ui.school.teacher.bean.AheadBean;
import com.yusong.community.ui.school.teacher.view.SlipButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.joybar.librarycalendar.adapter.CalendarViewPagerAdapter.NUM_ITEMS_CURRENT;

public class AheadAfterSchoolActivity extends BaseActivity implements CalendarViewPagerFragment.OnPageChangeListener, CalendarViewFragment.OnDateClickListener,
        CalendarViewFragment.OnDateCancelListener, CalendarViewFragment.OnWeekChooseListener, IQueryAheadView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.after_school_time_tv)
    TextView afterSchoolTimeTv;
    @InjectView(R.id.after_school_week_tv)
    TextView afterSchoolWeekTv;
    @InjectView(R.id.slip_button)
    SlipButton slipButton;
    private String time = "";
    private List<CalendarDate> mListDate = new ArrayList<>();
    private int mThisMonthPosition = DateUtils.getYear() * 12 + DateUtils.getMonth() - 1;//---100 -position
    private int number = mThisMonthPosition - NUM_ITEMS_CURRENT;
    private Context mContext;
    private IQueryAheadPresenterImpl queryAheadPresenterImpl;//查询提前放学
    private boolean isRemind = false;
    private String weeksStr = "";
    private String weeks[] = new String[7];
    private MyDataBaseHelper mDataBaseHelper;
    private String roleId = "";

    @OnClick({R.id.ll_back, R.id.ll_img})
    void allClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.ll_img:
                startActivity(new Intent(this, AddAfterSchoolActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        queryAheadPresenterImpl.queryAhead();
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
    protected int layoutId() {
        return R.layout.activity_school_ahead_after;
    }

    @Override
    public void initView() {
        mContext = AheadAfterSchoolActivity.this;
        mDataBaseHelper = MyDataBaseHelper.getSingleton(mContext);
        if (getIntent() != null && getIntent().getSerializableExtra("SchoolId") != null) {
            roleId = getIntent().getStringExtra("SchoolId");
        }
        if (mDataBaseHelper.recordNotExists(roleId)) {
            slipButton.setCheck(false);
        } else {
            ClockRecords clockRecords = mDataBaseHelper.readRecord(roleId);
            if (clockRecords.getIsOpen().equals("1")) {
                slipButton.setCheck(true);
            } else {
                slipButton.setCheck(false);
            }
        }
        tvTitle.setText("放学提醒");
        llImg.setVisibility(View.VISIBLE);
        queryAheadPresenterImpl = new IQueryAheadPresenterImpl(this, AheadAfterSchoolActivity.this);
    }

    @Override
    public void initData() {
        if (slipButton.isChecked()) {
            isRemind = true;
        }
        slipButton.SetOnChangedListener(new SlipButton.OnChangedListener() {
            @Override
            public void OnChanged(boolean CheckState) {
                isRemind = CheckState;
                if (CheckState) {
                    setClock();
                } else {
                    int lastSt = weeksStr.lastIndexOf(",");
                    if (lastSt != -1) {
                        weeksStr = weeksStr.substring(0, weeksStr.length() - 1);
                    }
                    String[] weeks = weeksStr.split(",");
                    for (int i = 0; i < weeks.length; i++) {
                        AlarmManagerUtil.cancelAlarm(AheadAfterSchoolActivity.this, AlarmManagerUtil.ALARM_ACTION, i);
                    }
                    ClockRecords clockRecords = new ClockRecords();
                    clockRecords.setRoleId(roleId);
                    clockRecords.setIsOpen("0");
                    clockRecords.setRoleType("2");
                    clockRecords.setDate(time);
                    if (mDataBaseHelper.recordNotExists(roleId)) {
                        mDataBaseHelper.insertRecord(clockRecords);
                    } else {
                        mDataBaseHelper.updateRecord(roleId, clockRecords);
                    }
                }
            }
        });
    }

    private void setClock() {
        if (time != null && time.length() > 0) {
            String[] times = time.split(":");
            //多选，周几的闹钟
            int lastSt = weeksStr.lastIndexOf(",");
            if (lastSt != -1) {
                weeksStr = weeksStr.substring(0, weeksStr.length() - 1);
            }
            String[] weeks = weeksStr.split(",");
            for (int i = 0; i < weeks.length; i++) {
                AlarmManagerUtil.setAlarm(this, 2, Integer.parseInt(times[0]), Integer
                        .parseInt(times[1]), i, Integer.parseInt(weeks[i]), "提前放学", 1);
            }
            ClockRecords clockRecords = new ClockRecords();
            clockRecords.setRoleId(roleId);
            clockRecords.setIsOpen("1");
            clockRecords.setRoleType("2");
            clockRecords.setDate(time);
            if (mDataBaseHelper.recordNotExists(roleId)) {
                mDataBaseHelper.insertRecord(clockRecords);
            } else {
                mDataBaseHelper.updateRecord(roleId, clockRecords);
            }
        }
    }

    private void initFragment(List<Integer> weekList) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        Fragment fragment = CalendarViewPagerFragment.newInstance(true, weekList);
        tx.replace(R.id.fl_content, fragment);
        tx.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
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
        mListDate.add(calendarDate);

    }

    public void onPageChange(int year, int month) {
        mListDate.clear();
    }

    @Override
    public void queryAheadSucced(List<AheadBean> data) {//查询成功
        List<Integer> weekList = new ArrayList<>();
        if (data != null && data.size() != 0) {
            time = data.get(0).getOverTime();
            afterSchoolTimeTv.setText(data.get(0).getOverTime() + "放学");
            weekList.add(data.get(0).getSunday());
            weekList.add(data.get(0).getMonday());
            weekList.add(data.get(0).getTuesday());
            weekList.add(data.get(0).getWednesday());
            weekList.add(data.get(0).getThursday());
            weekList.add(data.get(0).getFriday());
            weekList.add(data.get(0).getSaturday());
        } else {
            weekList.add(0);
            weekList.add(0);
            weekList.add(0);
            weekList.add(0);
            weekList.add(0);
            weekList.add(0);
            weekList.add(0);
        }
        String weekContent = "";
        for (int j = 0; j < weekList.size(); j++) {
            if (weekList.get(j) == 1) {
                switch (j) {
                    case 0:
                        weeksStr += "7,";
                        weekContent += "周日、";
                        break;
                    case 1:
                        weeksStr += "1,";
                        weekContent += "周一、";
                        break;
                    case 2:
                        weeksStr += "2,";
                        weekContent += "周二、";
                        break;
                    case 3:
                        weeksStr += "3,";
                        weekContent += "周三、";
                        break;
                    case 4:
                        weeksStr += "4,";
                        weekContent += "周四、";
                        break;
                    case 5:
                        weeksStr += "5,";
                        weekContent += "周五、";
                        break;
                    case 6:
                        weeksStr += "6,";
                        weekContent += "周六、";
                        break;
                }
            }
        }
        if (!weekContent.equals("")) {
            weekContent = weekContent.substring(0, weekContent.length() - 1);
        }
        afterSchoolWeekTv.setText(weekContent);
        if (slipButton.isChecked()) {
            setClock();
        }
        initFragment(weekList);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }

    @Override
    public void onWeekChoose(List<CalendarDate> mListData) {


    }
}