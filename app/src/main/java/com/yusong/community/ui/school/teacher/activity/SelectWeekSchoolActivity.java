package com.yusong.community.ui.school.teacher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.utils.ActivityConstants;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/29.
 *         描述: 提前放学星期选择
 */

public class SelectWeekSchoolActivity extends BaseActivity {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.week_0)
    ImageView week0;
    @InjectView(R.id.layout_week_0)
    RelativeLayout layoutWeek0;
    @InjectView(R.id.week_1)
    ImageView week1;
    @InjectView(R.id.layout_week_1)
    RelativeLayout layoutWeek1;
    @InjectView(R.id.week_2)
    ImageView week2;
    @InjectView(R.id.layout_week_2)
    RelativeLayout layoutWeek2;
    @InjectView(R.id.week_3)
    ImageView week3;
    @InjectView(R.id.layout_week_3)
    RelativeLayout layoutWeek3;
    @InjectView(R.id.week_4)
    ImageView week4;
    @InjectView(R.id.layout_week_4)
    RelativeLayout layoutWeek4;
    @InjectView(R.id.week_5)
    ImageView week5;
    @InjectView(R.id.layout_week_5)
    RelativeLayout layoutWeek5;
    @InjectView(R.id.week_6)
    ImageView week6;
    @InjectView(R.id.layout_week_6)
    RelativeLayout layoutWeek6;
    //星期标记
    private boolean isWeek0 = false;
    private boolean isWeek1 = false;
    private boolean isWeek2 = false;
    private boolean isWeek3 = false;
    private boolean isWeek4 = false;
    private boolean isWeek5 = false;
    private boolean isWeek6 = false;

    private int[] weekList = {0, 0, 0, 0, 0, 0, 0};// O or 1(未选中/选中)

    @OnClick({R.id.ll_back, R.id.layout_week_0, R.id.layout_week_1, R.id.layout_week_2, R.id.layout_week_3,
            R.id.layout_week_4, R.id.layout_week_5, R.id.layout_week_6})
    void allClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                onBackPressed();
                break;
            case R.id.layout_week_0:
                if (isWeek0) {
                    week0.setVisibility(View.GONE);
                    isWeek0 = false;
                    weekList[0] = 0;
                } else {
                    week0.setVisibility(View.VISIBLE);
                    isWeek0 = true;
                    weekList[0] = 1;
                }
                break;
            case R.id.layout_week_1:
                if (isWeek1) {
                    isWeek1 = false;
                    weekList[1] = 0;
                    week1.setVisibility(View.GONE);
                } else {
                    week1.setVisibility(View.VISIBLE);
                    isWeek1 = true;
                    weekList[1] = 1;
                }
                break;
            case R.id.layout_week_2:
                if (isWeek2) {
                    isWeek2 = false;
                    weekList[2] = 0;
                    week2.setVisibility(View.GONE);
                } else {
                    week2.setVisibility(View.VISIBLE);
                    isWeek2 = true;
                    weekList[2] = 1;
                }
                break;
            case R.id.layout_week_3:
                if (isWeek3) {
                    isWeek3 = false;
                    weekList[3] = 0;
                    week3.setVisibility(View.GONE);
                } else {
                    week3.setVisibility(View.VISIBLE);
                    isWeek3 = true;
                    weekList[3] = 1;
                }
                break;
            case R.id.layout_week_4:
                if (isWeek4) {
                    isWeek4 = false;
                    weekList[4] = 0;
                    week4.setVisibility(View.GONE);
                } else {
                    isWeek4 = true;
                    weekList[4] = 1;
                    week4.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.layout_week_5:
                if (isWeek5) {
                    isWeek5 = false;
                    weekList[5] = 0;
                    week5.setVisibility(View.GONE);
                } else {
                    isWeek5 = true;
                    week5.setVisibility(View.VISIBLE);
                    weekList[5] = 1;
                }
                break;
            case R.id.layout_week_6:
                if (isWeek6) {
                    isWeek6 = false;
                    weekList[6] = 0;
                    week6.setVisibility(View.GONE);
                } else {
                    isWeek6 = true;
                    weekList[6] = 1;
                    week6.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_school_week_select;
    }

    @Override
    public void initView() {
        tvTitle.setText("重复");
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
    public void onBackPressed() {
        Intent intent = new Intent();
        Bundle b = new Bundle();
        b.putIntArray("weekList", weekList);
        intent.putExtra("week", b);
        setResult(ActivityConstants.ADD_AFTER_SCHOOL, intent);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
