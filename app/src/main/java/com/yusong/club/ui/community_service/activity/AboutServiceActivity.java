package com.yusong.club.ui.community_service.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.charge.view.BaseDialog;
import com.yusong.club.ui.community_service.entity.ServiceBean;
import com.yusong.club.ui.community_service.entity.ServiceDetailBean;
import com.yusong.club.ui.community_service.mvp.ImplView.ServiceView;
import com.yusong.club.ui.community_service.mvp.presenter.ImplPresenter.ServicePresenterImpl;
import com.yusong.club.ui.shoppers.bean.FenLeiBean;
import com.yusong.club.utils.ToastUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * @author Mr_Peng
 * @created at 2017-08-26.
 * @describe: 关于社区服务
 */

public class AboutServiceActivity extends BaseActivity implements ServiceView {


    @InjectView(R.id.beijin_2)
    ImageView beijin2;
    @InjectView(R.id.beijin_1)
    ImageView beijin1;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.find_commodity_layout)
    RelativeLayout findCommodityLayout;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
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
    @InjectView(R.id.shanjia_tv)
    TextView shanjiaTv;
    @InjectView(R.id.shijian_tv)
    TextView shijianTv;
    @InjectView(R.id.location_tv)
    TextView locationTv;
    @InjectView(R.id.call_tv)
    TextView callTv;
    @InjectView(R.id.call_shop_layout)
    LinearLayout callShopLayout;
    @InjectView(R.id.service_tv)
    TextView serviceTv;
    private ServiceDetailBean bean;

    @OnClick({R.id.ll_back, R.id.call_shop_layout})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.call_shop_layout:
                if (bean == null) {
                    ToastUtils.showShort(this, "暂无联系信息");
                    return;
                }
                final BaseDialog dialog = new BaseDialog(this);
                dialog.setTitle("拨打电话");
                dialog.setMessage(String.format("你确定拨打：%s超市电话？", bean.getTel()));
                dialog.setNegativeButton("不了", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("拨打", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callDirectly(bean.getTel());
                        dialog.dismiss();
                    }
                });
                break;
        }

    }

    private ServicePresenterImpl presenter;


    @Override
    protected int layoutId() {
        return R.layout.activity_about_service;
    }

    @Override
    public void initData() {
        presenter = new ServicePresenterImpl(this, this);
        presenter.queryServiceDetail();
    }

    @Override
    public void initView() {
        tvTitle.setText("关于");

    }

    @Override
    protected void adaptiveSystemVersions() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
            beijin1.setVisibility(View.VISIBLE);
            beijin2.setVisibility(View.GONE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
            beijin1.setVisibility(View.GONE);
            beijin2.setVisibility(View.VISIBLE);
        }
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
    public void queryServiceSucces(ServiceDetailBean bean) {
        if (bean != null) {
            this.bean = bean;
            shanjiaTv.setText(bean.getShopName());
            shijianTv.setText(bean.getOfficeTime());
            locationTv.setText(bean.getAddress());
            callTv.setText(bean.getTel());
            serviceTv.setText(bean.getIntroduction());
        }
    }

    @Override
    public void queryServiceCategorySucces(List<FenLeiBean> datas) {

    }

    @Override
    public void queryServiceListSucces(List<ServiceBean> datas) {

    }

    @Override
    public void queryServiceListError() {

    }
}
