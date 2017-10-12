package com.yusong.community.ui.school.teacher.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bigkoo.pickerview.TimePickerView;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.mvp.implView.ICreateAheadView;
import com.yusong.community.ui.school.mvp.presenter.impl.ICreateAheadPresenterImpl;
import com.yusong.community.ui.school.teacher.view.SlipButton;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/29.
 *         描述: 添加提前放学
 */

public class AddAfterSchoolActivity extends BaseActivity implements ICreateAheadView {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.after_time_tv)
    TextView afterTimeTv;
    @InjectView(R.id.after_time_layout)
    RelativeLayout afterTimeLayout;
    @InjectView(R.id.week_tv)
    TextView weekTv;
    @InjectView(R.id.select_week_layout)
    RelativeLayout selectWeekLayout;
    @InjectView(R.id.slip_button)
    SlipButton slipButton;
    private TimePicker timePicker;
    private Dialog dialog;
    private int[] weekList;
    private String weekContent = "";
    private Context mContext;
    private ICreateAheadPresenterImpl createAheadPresenterImpl;
    private TimePickerView pvTime;


    @OnClick({R.id.ll_back, R.id.after_time_layout, R.id.select_week_layout, R.id.rl_txt, R.id.slip_button})
    void allClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.after_time_layout://放学时间
                pvTime.show();
                break;
            case R.id.select_week_layout://星期选择
                Intent intent = new Intent(this, SelectWeekSchoolActivity.class);
                startActivityForResult(intent, ActivityConstants.ADD_AFTER_SCHOOL);
                break;
            case R.id.rl_txt://完成
                String overTime = afterTimeTv.getText().toString();
                String weekContent = weekTv.getText().toString();
                if (TextUtils.isEmpty(overTime)) {
                    ToastUtils.showShort(mContext, "放学时间未选择");
                    return;
                }
                if (TextUtils.isEmpty(weekContent)) {
                    ToastUtils.showShort(mContext, "日期未选择");
                    return;
                }
                createAheadPresenterImpl.createAhead(overTime, weekList);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_add_after_school;
    }

    @Override
    public void initView() {
        mContext = AddAfterSchoolActivity.this;

        tvTitle.setText("新建");
        rlTxt.setVisibility(View.VISIBLE);
        tvText.setText("完成");
        createAheadPresenterImpl = new ICreateAheadPresenterImpl(this, AddAfterSchoolActivity.this);
        slipButton.SetOnChangedListener(new SlipButton.OnChangedListener() {
            @Override
            public void OnChanged(boolean CheckState) {//开关

            }
        });
        // TODO: 2017/3/29
    }

    @Override
    public void initData() {
        Calendar selectedDate = Calendar.getInstance();
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                afterTimeTv.setText(getTime(date));
            }
        })
                .setType(TimePickerView.Type.HOURS_MINS)
                .setLabel("", "", "", "时", "分", "秒") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentSize(20)
                .setDate(selectedDate)
                .build();

    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityConstants.ADD_AFTER_SCHOOL &&
                resultCode == ActivityConstants.ADD_AFTER_SCHOOL) {
            weekList = ((Bundle) data.getBundleExtra("week")).getIntArray("weekList");
            weekContent = "";
            for (int i = 0; i < weekList.length; i++) {
                if (weekList[i] == 1) {
                    switch (i) {
                        case 0:
                            weekContent += "周日、";
                            break;
                        case 1:
                            weekContent += "周一、";
                            break;
                        case 2:
                            weekContent += "周二、";
                            break;
                        case 3:
                            weekContent += "周三、";
                            break;
                        case 4:
                            weekContent += "周四、";
                            break;
                        case 5:
                            weekContent += "周五、";
                            break;
                        case 6:
                            weekContent += "周六、";
                            break;
                    }
                }
            }
            if (TextUtils.isEmpty(weekContent)) {
                weekTv.setText("");
            } else {
                weekTv.setText(weekContent.substring(0, weekContent.length() - 1));
            }
        }
    }


    @Override
    public void createSucced() {//创建完成

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
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
}
