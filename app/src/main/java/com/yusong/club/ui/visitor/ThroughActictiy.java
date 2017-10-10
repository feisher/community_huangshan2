package com.yusong.club.ui.visitor;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.leaking.slideswitch.SlideSwitch;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.visitor.entity.ThroughCardBean;
import com.yusong.club.ui.visitor.mvp.ImplView.CreateThroughView;
import com.yusong.club.ui.visitor.mvp.prsenter.impl.CreateThroughCardPrsenterImpl;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.DateUtil;
import com.yusong.club.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-08-23.
 * @describe: 社区通行证
 */

public class ThroughActictiy extends BaseActivity implements CreateThroughView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.yezhu_name_tv)
    TextView yezhuNameTv;
    @InjectView(R.id.phone_number_tv)
    TextView phoneNumberTv;
    @InjectView(R.id.user_location_tv)
    TextView userLocationTv;
    @InjectView(R.id.input_fangke_name_et)
    EditText inputFangkeNameEt;
    @InjectView(R.id.radio_man)
    RadioButton radioMan;
    @InjectView(R.id.radio_woman)
    RadioButton radioWoman;
    @InjectView(R.id.through_time_tv)
    TextView throughTimeTv;
    @InjectView(R.id.slip_button)
    SlideSwitch slipButton;
    @InjectView(R.id.chepai_et)
    EditText chepaiEt;
    @InjectView(R.id.create_through_button)
    Button createThroughButton;
    @InjectView(R.id.selector_time_layout)
    LinearLayout selectorTimeLayout;
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
    @InjectView(R.id.chepai_layout)
    LinearLayout chepaiLayout;

    private CreateThroughCardPrsenterImpl createThroughCardPrsenterImpl;
    private int sex = 1;
    private int isDrive = 0;

    @OnClick({R.id.ll_back, R.id.radio_man, R.id.radio_woman, R.id.selector_time_layout, R.id.create_through_button})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.radio_man:
                radioMan.setChecked(true);
                radioWoman.setChecked(false);
                sex = 1;
                break;
            case R.id.radio_woman:
                radioWoman.setChecked(true);
                radioMan.setChecked(false);
                sex = 2;
                break;
            case R.id.selector_time_layout:
                DatePickDialog dialog = new DatePickDialog(this);
                dialog.setYearLimt(0);
                dialog.setTitle("选择时间");
                dialog.setType(DateType.TYPE_YMD);
                dialog.setMessageFormat("yyyy-MM-dd");
                dialog.setOnChangeLisener(null);
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        if (DateUtil.compareDate(formatter.format(date), formatter.format(new Date()))) {
                            fangkeTime = formatter.format(date);
                            throughTimeTv.setText(fangkeTime.substring(0, fangkeTime.length() - 9));
                        } else {
                            ToastUtils.showShort(ThroughActictiy.this, "到访日期不能小于今天");
                        }
                    }
                });
                dialog.show();
                break;
            case R.id.create_through_button:
                String visitorName = inputFangkeNameEt.getText().toString().trim();
                String plateNumber = chepaiEt.getText().toString().trim();
                if (TextUtils.isEmpty(visitorName)) {
                    Toast.makeText(ThroughActictiy.this, "请填写访客名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                createThroughCardPrsenterImpl.createThroughCard(visitorName, sex, fangkeTime, isDrive, plateNumber);

                break;
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_through;
    }

    private String fangkeTime = "";

    @Override
    public void initView() {
        tvTitle.setText("访客通行");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        fangkeTime = formatter.format(new Date());
        throughTimeTv.setText(fangkeTime.substring(0, fangkeTime.length() - 9));
        slipButton.setSlideListener(new SlideSwitch.SlideListener() {
            @Override
            public void open() {
                isDrive = 1;
                chepaiLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void close() {
                isDrive = 0;
                chepaiLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void initData() {
        yezhuNameTv.setText(CacheUtils.getProprietorName(this));
        phoneNumberTv.setText(CacheUtils.getMobile(this));
        userLocationTv.setText(CacheUtils.getAddress(this));
        createThroughCardPrsenterImpl = new CreateThroughCardPrsenterImpl(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void createSucces(ThroughCardBean data) {
        Intent intent = new Intent(ThroughActictiy.this, ThroughCardActivity.class);
        intent.putExtra("ThroughCardBean", data);
        startActivity(intent);
        Toast.makeText(ThroughActictiy.this, "成功" + data.getPermitCode(), Toast.LENGTH_SHORT).show();
    }
}
