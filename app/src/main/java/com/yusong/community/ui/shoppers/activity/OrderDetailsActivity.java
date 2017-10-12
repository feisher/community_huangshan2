package com.yusong.community.ui.shoppers.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.pay.CommonPayActivity;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.express.activity.LogisticsInfoActivity;
import com.yusong.community.ui.express.activity.SearchSuccessActivity;
import com.yusong.community.ui.express.mvp.entity.ScanOrder;
import com.yusong.community.ui.express.mvp.implView.IMailQueryView;
import com.yusong.community.ui.express.mvp.presenter.impl.IMailQueryPresenterIpml;
import com.yusong.community.ui.shoppers.bean.OrderShopBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplHandleOrderView;
import com.yusong.community.ui.shoppers.mvp.implView.ImplShangpinPinlunView;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplHandleOrderPersenterImpl;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplShangpinPinlunPresenterImpl;
import com.yusong.community.ui.shoppers.used.event.EventCancel;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.glide.GlideImgManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         created at 2017/3/14 13:40.
 *         商城订单详情
 */
public class OrderDetailsActivity extends BaseActivity implements ImplHandleOrderView,
        ImplShangpinPinlunView, IMailQueryView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
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
    @InjectView(R.id.commodity_num)
    TextView commodityNum;
    @InjectView(R.id.shangping_zongjia_tv)
    TextView shangpingZongjiaTv;
    @InjectView(R.id.kuaidi_price_tv)
    TextView kuaidiPriceTv;
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
    @InjectView(R.id.fahuo_time_tv)
    TextView fahuoTimeTv;
    @InjectView(R.id.cancel_shop_order)
    Button cancelShopOrder;
    @InjectView(R.id.look_wuliu)
    Button lookWuliu;
    @InjectView(R.id.shop_lijifukuan)
    Button shopLijifukuan;
    @InjectView(R.id.pinjia_button)
    Button pinjiaButton;
    @InjectView(R.id.fukuaishijian_layout)
    LinearLayout fukuaishijianLayout;
    @InjectView(R.id.fahuo_time_layout)
    LinearLayout fahuoTimeLayout;
    @InjectView(R.id.distributionTime_tv)
    TextView distributionTimeTv;
    @InjectView(R.id.affirm_receive_button)
    Button affirmReceiveButton;
    @InjectView(R.id.reimburse_button)
    Button reimburseButton;
    @InjectView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    @InjectView(R.id.shangping_guige_tv)
    TextView shangpingGuigeTv;
    private OrderShopBean shopBean;
    private int type = -1;
    private ImplHandleOrderPersenterImpl implHandleOrderPersenterImpl;//取消订单
    private ImplShangpinPinlunPresenterImpl pinlunPresenterImpl;//评论
    private IMailQueryPresenterIpml mPresenter;
    private long mLong;

    @OnClick({R.id.ll_back, R.id.cancel_shop_order, R.id.look_wuliu, R.id.shop_lijifukuan, R.id.pinjia_button, R.id.affirm_receive_button, R.id.dianhua_lianxi, R.id.reimburse_button})
    void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.cancel_shop_order:
                implHandleOrderPersenterImpl.cancelOreder(shopBean.getId());
                break;
            case R.id.look_wuliu:
                String number = shopBean.getExpressNo();
                if (!TextUtils.isEmpty(number)) {
                    mLong = Long.parseLong(number);
                    if (!ActivityConstants.list_number.contains(mLong)) {
                        ActivityConstants.list_number.add(mLong);
                    }
                    mPresenter.scanOrder(number);
                } else {
                    ToastUtils.showShort(this, "没有快单号");
                }

                break;
            case R.id.shop_lijifukuan:
                Intent intent = new Intent(this, CommonPayActivity.class);
                intent.putExtra(ActivityConstants.ORDER_ID, shopBean.getId());
                intent.putExtra(ActivityConstants.CHARGE, String.valueOf(shopBean.getTotalPrice()));
                intent.putExtra(ActivityConstants.COMMON_PAYTYPE, ActivityConstants.STORE_ORDER);
                startActivity(intent);
                break;
            case R.id.pinjia_button:
//                final EditText editText = new EditText(this);
//                editText.setHint("请填写您的评论");
//                new AlertDialog.Builder(this).setTitle("评论").setIcon(
//                        android.R.drawable.ic_dialog_info).setView(editText).setPositiveButton("提交",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                String content = editText.getText().toString().trim();
//                                if (content.length() > 0) {
//                                    pinlunPresenterImpl.pinLun(shopBean.getId(), content);
//                                } else {
//                                    ToastUtils.showShort(OrderDetailsActivity.this, "评论未填写");
//                                }
//                            }
//                        }).setNegativeButton("取消", null).show();

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
                                        pinlunPresenterImpl.pinLun(shopBean.getId(), content);
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
                dialog.setMessage("确认收货后订单完成后你只能通过申请退货。");
                dialog.setNegativeButton("再想想", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        implHandleOrderPersenterImpl.affirmOrder(shopBean.getId());
                        dialog.dismiss();
                    }
                });

                break;
            case R.id.reimburse_button:
                LayoutInflater factory2 = LayoutInflater.from(this);//提示框
                final View view2 = factory2.inflate(R.layout.layout_with_edittext_dialog, null);//这里必须是final的
                final EditText editText1 = (EditText) view2.findViewById(R.id.editText);//获得输入框对象
                editText1.setHint("请填写退货原因");
                editText1.setBackgroundResource(R.drawable.shape_editor_ll);
                new AlertDialog.Builder(this)
                        .setTitle("退货原因")//提示框标题
                        .setView(view2)
                        .setPositiveButton("提交",//提示框的两个按钮
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        String content = editText1.getText().toString().trim();
                                        implHandleOrderPersenterImpl.reimburseOrder(shopBean.getId(), content);
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                break;
            default:
                break;
        }
    }

    private void showBaseDialog() {
        final BaseDialog baseDialog = new BaseDialog(this);
        baseDialog.setTitle("联系卖家");
        baseDialog.setMessage("确定拨打电话:" + shopBean.getShopMobile() + "吗?");
        baseDialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
            }
        });
        baseDialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + shopBean.getShopMobile()));
                startActivity(phoneIntent);
                baseDialog.dismiss();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_shop_order_details;
    }

    @Override
    public void initData() {
        tvTitle.setText("订单详情");
        Intent intent = getIntent();
        shopBean = (OrderShopBean) intent.getSerializableExtra("OrderShopBean");
        int orderStatus = shopBean.getOrderStatus();
        int kuaidiType = shopBean.getDeliverType();
        if (kuaidiType == 1) {
            kuaidiPriceTv.setText("快递柜");
        } else {
            kuaidiPriceTv.setText("上门自取");
        }
        if (!TextUtils.isEmpty(shopBean.getProperty())) {
            shangpingGuigeTv.setText(shopBean.getProperty());
        }
        distributionTimeTv.setText(shopBean.getDistributionTime());
        mPresenter = new IMailQueryPresenterIpml(this, OrderDetailsActivity.this);
        switch (orderStatus) {//状态 1 "未付款")2"已付款"3"卖家取消"4"买家取消"5"已发货"6 "已过期"7、已收货8、申请退货 9、已退货
            case 1:
                shopLijifukuan.setVisibility(View.VISIBLE);
                cancelShopOrder.setVisibility(View.VISIBLE);
                createTimeTv.setText(shopBean.getCreateTime());
                wuliuxianqing.setVisibility(View.GONE);
                fukuaishijianLayout.setVisibility(View.GONE);
                fahuoTimeLayout.setVisibility(View.GONE);
                affirmReceiveButton.setVisibility(View.GONE);
                reimburseButton.setVisibility(View.GONE);
                break;
            case 2:
                cancelShopOrder.setVisibility(View.VISIBLE);
                fukuaishijianLayout.setVisibility(View.VISIBLE);
                fahuoTimeLayout.setVisibility(View.GONE);
                createTimeTv.setText(shopBean.getCreateTime());
                payTimeTv.setText(shopBean.getPayTime());
                affirmReceiveButton.setVisibility(View.VISIBLE);
                reimburseButton.setVisibility(View.GONE);
                break;
            case 5:
                lookWuliu.setVisibility(View.VISIBLE);
                pinjiaButton.setVisibility(View.GONE);
                createTimeTv.setText(shopBean.getCreateTime());
                payTimeTv.setText(shopBean.getPayTime());
                fahuoTimeTv.setText(shopBean.getPayTime());
                affirmReceiveButton.setVisibility(View.VISIBLE);
                reimburseButton.setVisibility(View.GONE);
                break;
            case 7:
                lookWuliu.setVisibility(View.GONE);
                pinjiaButton.setVisibility(View.VISIBLE);
                createTimeTv.setText(shopBean.getCreateTime());
                payTimeTv.setText(shopBean.getPayTime());
                fahuoTimeTv.setText(shopBean.getPayTime());
                affirmReceiveButton.setVisibility(View.GONE);
                reimburseButton.setVisibility(View.VISIBLE);
                break;
            default:
                bottomLayout.setVisibility(View.GONE);
                createTimeTv.setText(shopBean.getCreateTime());
                fukuaishijianLayout.setVisibility(View.GONE);
                fahuoTimeLayout.setVisibility(View.GONE);
                break;
        }

        nameAndPhoneTv.setText("收货人:" + shopBean.getReciever() + "         " + shopBean.getReceiverMobile());
        shouhuoAddress.setText("收货地址:" + shopBean.getAddress());
        dianPuNameTv.setText(shopBean.getShopName());
        if (shopBean.getItemImageList().size() > 0) {
            GlideImgManager.loadImage(this, shopBean.getItemImageList().get(0), commodityImage);
        } else {
            GlideImgManager.loadImage(this, "", commodityImage);
        }
        commodityDetailsTv.setText(shopBean.getItemName());
        commodityPrice.setText("￥" + shopBean.getPrice());
        commodityNum.setText("x" + shopBean.getAmount());
        shangpingZongjiaTv.setText("￥" + shopBean.getTotalPrice());
        shifukuanTv.setText("￥" + shopBean.getTotalPrice());
        dindanBianhao.setText(shopBean.getId());
        implHandleOrderPersenterImpl = new ImplHandleOrderPersenterImpl(this, OrderDetailsActivity.this);
        pinlunPresenterImpl = new ImplShangpinPinlunPresenterImpl(this, OrderDetailsActivity.this);
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
    public void cancelSucced() {
        ToastUtils.showShort(this, "取消成功");
        EventBus.getDefault().post(new EventCancel());
        cancelShopOrder.setVisibility(View.GONE);
        this.finish();
    }

    @Override
    public void affirmSucced() {//确认收货回调
        ToastUtils.showShort(this, "收货成功");
        affirmReceiveButton.setVisibility(View.GONE);
        cancelShopOrder.setVisibility(View.GONE);
        this.finish();
    }

    @Override
    public void reimburseSucced() {//退货回调
        ToastUtils.showShort(this, "退货申请成功");
        reimburseButton.setVisibility(View.GONE);
        this.finish();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void pinLunSucced() {
        ToastUtils.showShort(this, "评论已提交");
    }

    @Override
    public void jumpActivity(ScanOrder.ShipperInfo order) {
        Intent mIntent = new Intent(this, LogisticsInfoActivity.class);
        mIntent.putExtra(ActivityConstants.ORDER_ID, mLong);
        mIntent.putExtra(ActivityConstants.ShipperCode, order.getShipperCode());
        mIntent.putExtra(ActivityConstants.ShipperName, order.getShipperName());
        startActivity(mIntent);
    }

    @Override
    public void jump() {
        MyApplication.closeProgressDialog();
        Intent mIntent = new Intent(this, SearchSuccessActivity.class);
        mIntent.putExtra(ActivityConstants.ORDER_ID, mLong);
        startActivity(mIntent);
    }
}
