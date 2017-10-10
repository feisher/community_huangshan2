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
import android.widget.TextView;
import android.widget.Toast;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.implView.IEditEvaluateActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.IEditEvaluateActivityPresenterImpl;
import com.yusong.club.ui.school.teacher.view.StarLayout;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 编辑评语
 */
public class EditEvaluateActivity extends BaseActivity implements View.OnClickListener, IEditEvaluateActivityView {
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
    @InjectView(R.id.btn_next)
    Button btnNext;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.star_jilv)
    StarLayout starJilv;
    @InjectView(R.id.star_speaker)
    StarLayout starSpeaker;
    @InjectView(R.id.star_know)
    StarLayout starKnow;
    @InjectView(R.id.star_study)
    StarLayout starStudy;
    @InjectView(R.id.lin_choose_xue)
    LinearLayout linChooseXue;
    @InjectView(R.id.tv_xue)
    TextView tvXue;
    @InjectView(R.id.lin_evalute)
    LinearLayout linEvalute;
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.lin_choose_stu)
    LinearLayout linChooseStu;
    @InjectView(R.id.tv_user_name)
    TextView tvUserName;
    private List<String> datas = new ArrayList<>();
    private IEditEvaluateActivityPresenterImpl mPresenter;
    private Context mContext;
    private int star1 = 3;
    private int star2 = 3;
    private int star3 = 3;
    private int star4 = 3;
    private int pos = -1;
    private int studentId = -1;

    @Override
    protected void initListener() {
        linearBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        linChooseXue.setOnClickListener(this);
        linChooseStu.setOnClickListener(this);
        starJilv.setListener(new StarLayout.OnClickBackListener() {
            @Override
            public void onClick(float point) {
                star1 = (int) point;
            }
        });
        starSpeaker.setListener(new StarLayout.OnClickBackListener() {
            @Override
            public void onClick(float point) {
                star2 = (int) point;
            }
        });
        starKnow.setListener(new StarLayout.OnClickBackListener() {
            @Override
            public void onClick(float point) {
                star3 = (int) point;
            }
        });
        starStudy.setListener(new StarLayout.OnClickBackListener() {
            @Override
            public void onClick(float point) {
                star4= (int) point;
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher_edit_evaluate;
    }

    @Override
    public void initView() {
        mContext = EditEvaluateActivity.this;
        tvTitle.setText("发表评语");
        imMsg.setVisibility(View.GONE);
        mPresenter = new IEditEvaluateActivityPresenterImpl(this, this);


    }

    @Override
    public void initData() {
        datas.add("第一学期");
        datas.add("第二学期");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            studentId = data.getIntExtra("studentId", -1);
            tvUserName.setText(data.getStringExtra("studentName"));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.btn_next:
                if (pos != -1) {
                    mPresenter.StuCommentCreate(CacheUtils.getToken(mContext), studentId, (pos + 1), star1, star2, star3, star4, etContent.getText().toString());
                } else {
                    ToastUtils.showShort(this, "学期还没选择");
                }
                break;
            case R.id.lin_choose_xue:
                showPopupWindow(datas);
                break;
            case R.id.lin_choose_stu:
                Intent intent = new Intent(mContext, ChooseStudentActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
    }

    private void showPopupWindow(final List<String> data) {
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
                pos = wheelView.getCurrentPosition();
                tvXue.setText(datas.get(wheelView.getCurrentPosition()));
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
        window.showAtLocation(linEvalute, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void StuCommentCreate(String data) {

        ToastUtils.show(mContext, "评论成功", Toast.LENGTH_LONG);
        finish();


    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }
}
