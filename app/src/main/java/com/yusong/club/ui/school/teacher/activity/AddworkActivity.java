package com.yusong.club.ui.school.teacher.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.implView.IAddworkActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.IAddworkActivityPresenterImpl;
import com.yusong.club.ui.school.teacher.bean.Course;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.yusong.club.R.id.btn_issue;

/**
 * 布置作业
 */
public class AddworkActivity extends BaseActivity implements View.OnClickListener, IAddworkActivityView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.after_time_layout)
    RelativeLayout afterTimeLayout;
    private List<String> subjectList;
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_last)
    TextView tvLast;
    @InjectView(R.id.tv_subject)
    TextView tvSubject;
    @InjectView(R.id.linear_subject)
    LinearLayout linearSubject;
    @InjectView(btn_issue)
    Button btnIssue;
    private Context mContext;
    private IAddworkActivityPresenterImpl mPresenter;
    private List<String> ids;
    private int pos = -1;
    private TimePickerView pvTime;

    @Override
    protected void initListener() {
        linearBack.setOnClickListener(this);
        linearSubject.setOnClickListener(this);
        tvLast.setOnClickListener(this);
        btnIssue.setOnClickListener(this);
        afterTimeLayout.setOnClickListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher_addwork;
    }

    @Override
    public void initView() {
        tvTitle.setText("布置作业");
        tvLast.setText("历史作业");
        mContext = AddworkActivity.this;
        mPresenter = new IAddworkActivityPresenterImpl(this, this);
        mPresenter.searchCourseList(CacheUtils.getToken(mContext));
    }

    @Override
    public void initData() {
        subjectList = new ArrayList<>();
        ids = new ArrayList<>();
        initDateInfo();
    }

    private void initDateInfo() {
        Calendar selectedDate = Calendar.getInstance();
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvTime.setText(getTime(date));
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
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

    private void showPopupWindow(AddworkActivity activity, List<String> data) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow_express_layout, null);
        contentView.getBackground().setAlpha(100);
        final PopupWindow window;
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        window.setContentView(contentView);
        final WheelView wheelView = (WheelView) contentView.findViewById(R.id.wheelview);
        wheelView.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        wheelView.setWheelData(data);  // 数据集合
        wheelView.setSkin(WheelView.Skin.Holo);
        wheelView.setWheelSize(5);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.textColor = Color.parseColor("#888888");
        style.selectedTextColor = Color.parseColor("#333333");
        wheelView.setStyle(style);
        TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSubject.setText((CharSequence) wheelView.getSelectionItem());
                pos = wheelView.getCurrentPosition();
                window.dismiss();
            }
        });

        TextView tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.showAtLocation(btnIssue, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.linear_subject:
                showPopupWindow(this, subjectList);
                break;
            case R.id.tv_last:
                Intent intent = new Intent(this, HistoryWorkActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_issue:
                if (pos != -1) {
                    createHomeWork();
                } else {
                    ToastUtils.show(mContext, "必须选择一个科目", Toast.LENGTH_LONG);
                }
                break;
            case R.id.after_time_layout:
                pvTime.show();
                break;

        }
    }

    public void createHomeWork() {
        mPresenter.createClasswork(CacheUtils.getToken(mContext), Integer.parseInt(ids.get(pos)), etContent.getText().toString(), tvTime.getText().toString());

    }

    @Override
    public void createClassWork(String data) {
        ToastUtils.show(mContext, "作业布置完成", Toast.LENGTH_LONG);
        setResult(ActivityConstants.WORK_CODE);
        AddworkActivity.this.finish();
    }

    @Override
    public void getCourseList(List<Course> data) {
        for (Course course : data) {
            subjectList.add(course.getCourseName());
            ids.add(course.getId());
        }
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }
}
