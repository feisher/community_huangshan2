package com.yusong.community.ui.community_service.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.yusong.community.pay.CommonPayActivity;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.community_service.entity.ServiceOrderBean;
import com.yusong.community.ui.community_service.mvp.ImplView.ServiceOrderView;
import com.yusong.community.ui.community_service.mvp.presenter.ImplPresenter.ServiceOrderPresenterImpl;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-09-23.
 * @describe: null
 */

public class ServiceOrderDetailsActivity extends BaseActivity implements ServiceOrderView {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
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
    @InjectView(R.id.commodity_status_tv)
    TextView commodityStatusTv;
    @InjectView(R.id.refresh_time_tv)
    TextView refreshTimeTv;
    @InjectView(R.id.wuliuxianqing)
    RelativeLayout wuliuxianqing;
    @InjectView(R.id.name_and_phone_tv)
    TextView nameAndPhoneTv;
    @InjectView(R.id.shouhuo_address)
    TextView shouhuoAddress;
    @InjectView(R.id.dian_pu_name_tv)
    TextView dianPuNameTv;
    @InjectView(R.id.commodity_image)
    ImageView commodityImage;
    @InjectView(R.id.commodity_details_tv)
    TextView commodityDetailsTv;
    @InjectView(R.id.commodity_price)
    TextView commodityPrice;
    @InjectView(R.id.shangping_guige_tv)
    TextView shangpingGuigeTv;
    @InjectView(R.id.shangping_zongjia_tv)
    TextView shangpingZongjiaTv;
    @InjectView(R.id.kuaidi_price_tv)
    TextView kuaidiPriceTv;
    @InjectView(R.id.distributionTime_tv)
    TextView distributionTimeTv;
    @InjectView(R.id.shifukuan_tv)
    TextView shifukuanTv;
    @InjectView(R.id.zaixian_gouton)
    RelativeLayout zaixianGouton;
    @InjectView(R.id.dianhua_lianxi)
    RelativeLayout dianhuaLianxi;
    @InjectView(R.id.dindan_bianhao)
    TextView dindanBianhao;
    @InjectView(R.id.create_time_tv)
    TextView createTimeTv;
    @InjectView(R.id.pay_time_tv)
    TextView payTimeTv;
    @InjectView(R.id.fukuaishijian_layout)
    LinearLayout fukuaishijianLayout;
    @InjectView(R.id.fahuo_time_tv)
    TextView fahuoTimeTv;
    @InjectView(R.id.fahuo_time_layout)
    LinearLayout fahuoTimeLayout;
    @InjectView(R.id.cancel_shop_order)
    Button cancelShopOrder;
    @InjectView(R.id.affirm_receive_button)
    Button affirmReceiveButton;
    @InjectView(R.id.pinjia_button)
    Button pinjiaButton;
    @InjectView(R.id.shop_lijifukuan)
    Button shopLijifukuan;
    @InjectView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    private ServiceOrderBean serviceOrderBean;
    private ServiceOrderPresenterImpl mPresenter;

    @OnClick({R.id.ll_back, R.id.cancel_shop_order, R.id.shop_lijifukuan, R.id.pinjia_button, R.id.affirm_receive_button, R.id.dianhua_lianxi})
    void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.cancel_shop_order:
                mPresenter.cancelServiceOrder(serviceOrderBean.getId());
                break;
            case R.id.shop_lijifukuan:
                Intent intent = new Intent(this, CommonPayActivity.class);
                intent.putExtra(ActivityConstants.ORDER_ID, serviceOrderBean.getId());
                intent.putExtra(ActivityConstants.CHARGE, String.valueOf(serviceOrderBean.getPrice()));
                intent.putExtra(ActivityConstants.COMMON_PAYTYPE, ActivityConstants.SERVICE_OERDER);
                startActivity(intent);
                break;
            case R.id.pinjia_button:
                LayoutInflater factory1 = LayoutInflater.from(this);//提示框
                final View view1 = factory1.inflate(R.layout.layout_with_edittext_dialog, null);//这里必须是final的
                final EditText editText = (EditText) view1.findViewById(R.id.editText);//获得输入框对象
                editText.setHint("请填写您的评论");
                editText.setBackgroundResource(R.drawable.shape_editor_ll);
                new AlertDialog.Builder(this)
                        .setTitle("评论")//提示框标题
                        .setView(view1)
                        .setPositiveButton("提交",//提示框的两个按钮
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        String content = editText.getText().toString().trim();
                                        mPresenter.commitServiceComment(serviceOrderBean.getId(), content);
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                break;
            case R.id.dianhua_lianxi:
                showBaseDialog();
                break;
            case R.id.affirm_receive_button:
                final BaseDialog dialog = new BaseDialog(this);
                dialog.setTitle("警告");
                dialog.setMessage("确认订单后，交易完成。");
                dialog.setNegativeButton("再想想", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.confirmOrder(serviceOrderBean.getId());
                        dialog.dismiss();
                    }
                });

                break;
            default:
                break;
        }
    }

    private void showBaseDialog() {
        final BaseDialog baseDialog = new BaseDialog(this);
        baseDialog.setTitle("联系服务商");
        baseDialog.setMessage("确定拨打电话:" + serviceOrderBean.getShopMobile() + "吗?");
        baseDialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
            }
        });
        baseDialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + serviceOrderBean.getShopMobile()));
                startActivity(phoneIntent);
                baseDialog.dismiss();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_service_order_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("订单详情");
        Intent intent = getIntent();
        serviceOrderBean = (ServiceOrderBean) intent.getSerializableExtra("ServiceOrderBean");
        int orderStatus = serviceOrderBean.getOrderStatus();
        int kuaidiType = serviceOrderBean.getDeliverType();
        if (kuaidiType == 1) {
            kuaidiPriceTv.setText("上门服务");
        } else {
            kuaidiPriceTv.setText("无");
        }
        distributionTimeTv.setText(serviceOrderBean.getServiceTime());
        switch (orderStatus) {
            case 1:
                shopLijifukuan.setVisibility(View.VISIBLE);
                cancelShopOrder.setVisibility(View.VISIBLE);
                createTimeTv.setText(serviceOrderBean.getCreateTime());
                wuliuxianqing.setVisibility(View.GONE);
                fukuaishijianLayout.setVisibility(View.GONE);
                fahuoTimeLayout.setVisibility(View.GONE);
                affirmReceiveButton.setVisibility(View.GONE);
                break;
            case 2:
                cancelShopOrder.setVisibility(View.VISIBLE);
                fukuaishijianLayout.setVisibility(View.VISIBLE);
                fahuoTimeLayout.setVisibility(View.GONE);
                createTimeTv.setText(serviceOrderBean.getCreateTime());
                payTimeTv.setText(serviceOrderBean.getPayTime());
                affirmReceiveButton.setVisibility(View.VISIBLE);
                break;
            case 5:
                pinjiaButton.setVisibility(View.VISIBLE);
                createTimeTv.setText(serviceOrderBean.getCreateTime());
                payTimeTv.setText(serviceOrderBean.getPayTime());
                fahuoTimeTv.setText(serviceOrderBean.getPayTime());
                affirmReceiveButton.setVisibility(View.GONE);
                break;
            case 7:
                pinjiaButton.setVisibility(View.GONE);
                createTimeTv.setText(serviceOrderBean.getCreateTime());
                payTimeTv.setText(serviceOrderBean.getPayTime());
                fahuoTimeTv.setText(serviceOrderBean.getPayTime());
                affirmReceiveButton.setVisibility(View.GONE);
                break;
            default:
                bottomLayout.setVisibility(View.GONE);
                createTimeTv.setText(serviceOrderBean.getCreateTime());
                fukuaishijianLayout.setVisibility(View.GONE);
                fahuoTimeLayout.setVisibility(View.GONE);
                break;
        }
        nameAndPhoneTv.setText("收货人:" + serviceOrderBean.getReciever() + "         " + serviceOrderBean.getReceiverMobile());
        shouhuoAddress.setText("收货地址:" + serviceOrderBean.getAddress());
        dianPuNameTv.setText(serviceOrderBean.getShopName());
        if (serviceOrderBean.getItemImageList().size() > 0) {
            GlideImgManager.loadImage(this, serviceOrderBean.getItemImageList().get(0), commodityImage);
        } else {
            GlideImgManager.loadImage(this, "", commodityImage);
        }
        commodityDetailsTv.setText(serviceOrderBean.getItemName());
        commodityPrice.setText("￥" + serviceOrderBean.getPrice());
        shangpingZongjiaTv.setText("￥" + serviceOrderBean.getPrice());
        shifukuanTv.setText("￥" + serviceOrderBean.getPrice());
        dindanBianhao.setText(serviceOrderBean.getId());
        mPresenter = new ServiceOrderPresenterImpl(this, this);
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

    @Override
    public void showProgressDialog() {

    }


    @Override
    public void queryOrderSucces(List<ServiceOrderBean> beanList) {

    }

    @Override
    public void queryOrderError() {

    }

    @Override
    public void commitServiceCommentSucces() {
        ToastUtils.showShort(this, "评论已提交");
    }

    @Override
    public void cancelServiceOrderSucces() {
        ToastUtils.showShort(this, "取消成功");
        cancelShopOrder.setVisibility(View.GONE);
        this.finish();
    }

    @Override
    public void confirmOrderSucces() {
        ToastUtils.showShort(this, "交易完成");
        affirmReceiveButton.setVisibility(View.GONE);
        cancelShopOrder.setVisibility(View.GONE);
        this.finish();
    }
}
