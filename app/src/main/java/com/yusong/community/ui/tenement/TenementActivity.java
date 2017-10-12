package com.yusong.community.ui.tenement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.teacher.adapter.PhotoAdapter;
import com.yusong.community.ui.tenement.entity.TenementBean;
import com.yusong.community.ui.tenement.mvp.presenter.impl.TenementPresenterImpl;
import com.yusong.community.ui.tenement.mvp.implview.TenementView;
import com.yusong.community.utils.ArithUtil;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-08-25.
 * @describe: 物业缴费
 */

public class TenementActivity extends BaseActivity implements TenementView {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.address_tv)
    TextView addressTv;
    @InjectView(R.id.user_name_tv)
    TextView userNameTv;
    @InjectView(R.id.tl_tenement_fragment)
    TabLayout tlTenementFragment;
    @InjectView(R.id.vp_tenement_fragment)
    ViewPager vpTenementFragment;
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
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.select_all_btn)
    ImageView selectAllBtn;
    @InjectView(R.id.all_money_tv)
    TextView allMoneyTv;
    @InjectView(R.id.go_pay_btn)
    Button goPayBtn;
    @InjectView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    private List<String> tabNameList = new ArrayList<String>();
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    private TenementPayHistoryFragment historyFragment;
    private TenementPayInfoFragment infoFragment;

    private TenementBean tenementBean = null;

    private TenementPresenterImpl presenter;

    private boolean isSelectAll = false;

    @OnClick({R.id.ll_back, R.id.go_pay_btn, R.id.select_all_btn})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.go_pay_btn:
                if (!TextUtils.isEmpty(orderId)) {
                    Intent intent = new Intent(this, TenementPayActivity.class);
                    intent.putExtra("orderId", orderId.substring(0, orderId.length() - 1));
                    intent.putExtra("price", price);
                    startActivity(intent);
                } else {
                    ToastUtils.showShort(this, "数据异常，不能支付");
                }
                break;
            case R.id.select_all_btn:
                if (!isSelectAll) {
                    isSelectAll = true;
                    selectAllBtn.setImageResource(R.mipmap.selected);
                    for (TenementBean bean : infoFragment.getBeanList()) {
                        bean.setIsSelect(1);
                    }
                } else {
                    isSelectAll = false;
                    for (TenementBean bean : infoFragment.getBeanList()) {
                        bean.setIsSelect(0);
                    }
                    selectAllBtn.setImageResource(R.mipmap.not_select);
                }
                infoFragment.refreshView();
                break;
            default:
                break;
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_tenement;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        tvTitle.setText("物业缴费");
        addressTv.setText(CacheUtils.getAddress(this));
        userNameTv.setText(CacheUtils.getProprietorName(this));

        tabNameList.add("待缴费信息");
        tabNameList.add("缴费记录");
        infoFragment = new TenementPayInfoFragment();
        historyFragment = new TenementPayHistoryFragment();
        fragmentList.add(infoFragment);
        fragmentList.add(historyFragment);
        tlTenementFragment.setTabMode(TabLayout.MODE_FIXED);//设置TabLayout的模式
        vpTenementFragment.setAdapter(new PhotoAdapter(getSupportFragmentManager(), fragmentList, tabNameList));
        tlTenementFragment.setupWithViewPager(vpTenementFragment);//TabLayout加载viewpager
        vpTenementFragment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    bottomLayout.setVisibility(View.VISIBLE);
                } else {
                    bottomLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private double price = 0.0;
    private String orderId = "";

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventSelect(EventTenement tenement) {
        price = 0.0;
        orderId = "";
        for (TenementBean bean : infoFragment.getBeanList()) {
            if (bean.getIsSelect() == 1) {
                price = ArithUtil.add(price, bean.getAmount());
                orderId += bean.getOrderId() + ",";
            }
        }
        tenementBean = tenement.getBean();
        allMoneyTv.setText("￥" + price);
    }


    @Override
    public void initData() {
        presenter = new TenementPresenterImpl(this, this);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void queryTenementSucces(List<TenementBean> data) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
