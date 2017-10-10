package com.yusong.club.ui.school.teacher.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.implView.ICreateParentLeaveActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.ICreateParentLeaveActivityPresenterImpl;
import com.yusong.club.ui.school.teacher.view.flowlayout.FlowLayout;
import com.yusong.club.ui.school.teacher.view.flowlayout.TagAdapter;
import com.yusong.club.ui.school.teacher.view.flowlayout.TagFlowLayout;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.ToastUtils;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class CreateParentLeaveActivity extends BaseActivity implements View.OnClickListener, ICreateParentLeaveActivityView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_msg)
    TextView tvMsg;
    @InjectView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.et_student)
    EditText etStudent;
    @InjectView(R.id.lin_stu_name)
    LinearLayout linStuName;
    @InjectView(R.id.tv_start_time)
    TextView tvStartTime;
    @InjectView(R.id.lin_start_time)
    LinearLayout linStartTime;
    @InjectView(R.id.tv_end_time)
    TextView tvEndTime;
    @InjectView(R.id.lin_end_time)
    LinearLayout linEndTime;
    @InjectView(R.id.et_day)
    EditText etDay;
    private int flagTime = -1;
    private int isChooseType = -1;
    private ICreateParentLeaveActivityPresenterImpl mPresenter;
    private String[] mVals = new String[]
            {"身体不舒服", "家里有事", "亲人生病", "交通意外事故", "车子抛锚", "搬家",
                    "亲人结婚", "丧假", "天灾"};
    private TimePickerView pvTime;
    private Context mContext;
    private String typeName = "";

    @Override
    protected int layoutId() {
        return R.layout.activity_create_parent_leave;
    }

    @Override
    public void initData() {
        mContext = CreateParentLeaveActivity.this;
        linearBack.setOnClickListener(this);
        linStartTime.setOnClickListener(this);
        linEndTime.setOnClickListener(this);
        tvMsg.setOnClickListener(this);
        tvTitle.setText("申请请假");
        initDateInfo();
        mPresenter = new ICreateParentLeaveActivityPresenterImpl(this, this);

    }

    private void initDateInfo() {
        Calendar selectedDate = Calendar.getInstance();
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (flagTime == 1) {
                    tvStartTime.setText(getTime(date));
                } else if (flagTime == 2) {
                    tvEndTime.setText(getTime(date));
                }
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("", "", "", "", "", "") //设置空字符串 以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentSize(20)
                .setDate(selectedDate)
                .setRange(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.YEAR))//默认是1900-2100年
                .build();
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    protected void initListener() {
        idFlowlayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.tv,
                        idFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        });
        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                isChooseType = position;
                typeName = mVals[position];
                return true;
            }
        });


        idFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                CreateParentLeaveActivity.this.setTitle("choose:" + selectPosSet.toString());
            }
        });

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
            case R.id.linear_back:
                finish();
                break;
            case R.id.lin_start_time:
                flagTime = 1;
                pvTime.show();
                break;
            case R.id.lin_end_time:
                flagTime = 2;
                pvTime.show();
                break;
            case R.id.tv_msg:
                if (!StringUtils.isEmpty(etContent.getText().toString())) {
                    if (isChooseType != -1) {
                        String reason = typeName + "-" + etContent.getText().toString();
                        mPresenter.CreateLeaveApply(CacheUtils.getToken(mContext), etStudent.getText().toString(), reason, tvStartTime.getText().toString(), tvEndTime.getText().toString());
                    } else {
                        ToastUtils.showShort(mContext, "还没选择请假的类型");
                    }
                } else {
                    ToastUtils.showShort(mContext, "请假描述不能为空  ");
                }
                break;
        }
    }


    @Override
    public void createParentLeaveSucess(String data) {
        finish();

    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }
}
