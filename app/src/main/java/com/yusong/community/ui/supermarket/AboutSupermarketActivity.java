package com.yusong.community.ui.supermarket;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.ui.supermarket.entity.SMCommodityBean;
import com.yusong.community.ui.supermarket.entity.SuperMarketDetailsBean;
import com.yusong.community.ui.supermarket.mvp.ImolView.QuerySMView;
import com.yusong.community.ui.supermarket.mvp.presenter.impl.QuerySMPresenterImpl;
import com.yusong.community.utils.ToastUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * @author Mr_Peng
 * @created at 2017-08-26.
 * @describe: 关于超市
 */

public class AboutSupermarketActivity extends BaseActivity implements QuerySMView {
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
    @InjectView(R.id.qibujia_tv)
    TextView qibujiaTv;
    @InjectView(R.id.yunfei_tv)
    TextView yunfeiTv;
    @InjectView(R.id.call_shop_layout)
    LinearLayout callShopLayout;
    @InjectView(R.id.service_tv)
    TextView serviceTv;

    private SuperMarketDetailsBean bean;

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
                        dialog.dismiss();
                        callDirectly(bean.getTel());
                    }
                });
                break;
        }

    }

    private QuerySMPresenterImpl presenter;


    @Override
    protected int layoutId() {
        return R.layout.activity_about_supermarket;
    }

    @Override
    public void initData() {
        presenter = new QuerySMPresenterImpl(this, this);
        presenter.querySuperMarket();
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
    public void querySMSucces(SuperMarketDetailsBean data) {

        if (data != null) {
            bean = data;
            shanjiaTv.setText(data.getShopName());
            shijianTv.setText(data.getOfficeTime());
            locationTv.setText(data.getAddress());
            callTv.setText(data.getTel());
            qibujiaTv.setText(data.getStartPrice());
            yunfeiTv.setText(data.getFreightPrice());
            serviceTv.setText(data.getIntroduction());
        }
    }

    @Override
    public void queryCommodityError() {

    }

    @Override
    public void querFenleiSucces(List<FenLeiBean> been) {

    }

    @Override
    public void queryCommoditySucces(List<SMCommodityBean> been) {

    }
}
