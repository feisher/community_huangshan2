package com.yusong.club.ui.charge.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.youth.banner.Banner;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.pay.PayUtils;
import com.yusong.club.pay.bean.WeiXinPayBean;
import com.yusong.club.pay.bean.ZhiFuBaoPayBean;
import com.yusong.club.pay.zfb.PayResult;
import com.yusong.club.ui.charge.bean.FetchMoneyBean;
import com.yusong.club.ui.charge.bean.NearbyBean;
import com.yusong.club.ui.charge.mvp.implView.ICharWXPayView;
import com.yusong.club.ui.charge.mvp.implView.ICharZFBPayView;
import com.yusong.club.ui.charge.mvp.implView.IChargeCreateOrderView;
import com.yusong.club.ui.charge.mvp.implView.IChargeQueryChargeDetailsView;
import com.yusong.club.ui.charge.mvp.implView.IChargeYuEPayView;
import com.yusong.club.ui.charge.mvp.implView.IChargeYuyueDetalisView;
import com.yusong.club.ui.charge.mvp.presenter.impl.ICharWXPayPresenterImpl;
import com.yusong.club.ui.charge.mvp.presenter.impl.ICharZFBPayPresenterImpl;
import com.yusong.club.ui.charge.mvp.presenter.impl.IChargeCreateOrderPresenterImpl;
import com.yusong.club.ui.charge.mvp.presenter.impl.IChargeQueryChargeDetailsPresenterImpl;
import com.yusong.club.ui.charge.mvp.presenter.impl.IChargeYuEPayPresenterImpl;
import com.yusong.club.ui.charge.mvp.presenter.impl.IChargeYuyueDetailsPresenterImpl;
import com.yusong.club.ui.charge.view.BaseDialog;
import com.yusong.club.ui.charge.view.SelectMoneyLayout;
import com.yusong.club.ui.charge.view.SuccedDialog;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.glide.GlideImgManager;
import com.yusong.club.wxapi.WXPayEntryActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.yusong.club.pay.PayUtils.getOrderInfo;

/**
 * Created by Mr_Peng on 2016/12/26.
 */

public class YuYueDetailsActivity extends WXPayEntryActivity implements IChargeYuyueDetalisView, IChargeCreateOrderView,
        IChargeYuEPayView, IChargeQueryChargeDetailsView, ICharZFBPayView, ICharWXPayView, WXPayEntryActivity.OnPayResultListener {
    private List<FetchMoneyBean> beenList = new ArrayList<FetchMoneyBean>();
    private int payType = 0;//支付类型
    private int chargeType = 0;//充电类型
    public SelectMoneyLayout selectMoneyLayout;
    private IChargeYuyueDetailsPresenterImpl presenter;
    private IChargeCreateOrderPresenterImpl orderPresenter;
    private IChargeYuEPayPresenterImpl yuEPayPresenterImpl;
    private IChargeQueryChargeDetailsPresenterImpl queryChargeDetailsPresenterImpl;
    private ICharZFBPayPresenterImpl iCharZFBPayPresenter;
    private ICharWXPayPresenterImpl iCharWXPayPresenterImpl;
    private double lng;
    private double lat;
    private Intent jumpIntent;
    private String chargeId = null;
    private int orderType = 0;//创建订单类型(预约订单/普通订单)
    private String orderNumber = null;//订单号


    @InjectView(R.id.yuyue_details_banner)
    Banner yuyueDetailsBanner;
    @InjectView(R.id.details_address_tv)
    TextView detailsAddressTv;
    @InjectView(R.id.juli_details_tv)
    TextView juliDetailsTv;
    @InjectView(R.id.kaifang_time_tv)
    TextView kaifangTimeTv;
    @InjectView(R.id.shiyongzhong_tv)
    TextView shiyongzhongTv;
    @InjectView(R.id.kongxian_details_tv)
    TextView kongxianDetailsTv;
    @InjectView(R.id.pay_one)
    SelectMoneyLayout payOne;
    @InjectView(R.id.pay_two)
    SelectMoneyLayout payTwo;
    @InjectView(R.id.pay_three)
    SelectMoneyLayout payThree;
    @InjectView(R.id.zhilian_press)
    ImageView zhilianPress;
    @InjectView(R.id.zhilian_layout_btn)
    RelativeLayout zhilianLayoutBtn;
    @InjectView(R.id.weixin_press)
    ImageView weixinPress;
    @InjectView(R.id.weixin_layout_btn)
    RelativeLayout weixinLayoutBtn;
    @InjectView(R.id.zhifubao_press)
    ImageView zhifubaoPress;
    @InjectView(R.id.zhifubao_layout_btn)
    RelativeLayout zhifubaoLayoutBtn;
    @InjectView(R.id.liji_pay)
    Button lijiPay;
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
    private String string;

    @OnClick(R.id.ll_back)
    void backClick() {
        this.finish();
    }

    @OnClick(R.id.ll_img)
    void toMyOrderClick() {
        startActivity(new Intent(this, ChargeMyOrderActivity.class));
    }

    public RelativeLayout oldRelative;

    @OnClick(R.id.zhilian_layout_btn)
    void zhilianPayClick(View view) {
        payType = 0;
        oldRelative.setBackgroundResource(R.drawable.shape_of_pay_putdown);
        zhifubaoPress.setVisibility(View.GONE);
        weixinPress.setVisibility(View.GONE);
        zhilianLayoutBtn.setBackgroundResource(R.drawable.shape_of_pay_press);
        zhilianPress.setVisibility(View.VISIBLE);
        oldRelative = (RelativeLayout) view;
    }

    @OnClick(R.id.weixin_layout_btn)
    void weixinPayClick(View view) {
        payType = 1;
        oldRelative.setBackgroundResource(R.drawable.shape_of_pay_putdown);
        zhilianPress.setVisibility(View.GONE);
        zhifubaoPress.setVisibility(View.GONE);
        weixinLayoutBtn.setBackgroundResource(R.drawable.shape_of_pay_press);
        weixinPress.setVisibility(View.VISIBLE);
        oldRelative = (RelativeLayout) view;
    }

    @OnClick(R.id.zhifubao_layout_btn)
    void zhifubaoPayClick(View view) {
        payType = 2;
        oldRelative.setBackgroundResource(R.drawable.shape_of_pay_putdown);
        zhilianPress.setVisibility(View.GONE);
        weixinPress.setVisibility(View.GONE);
        zhifubaoLayoutBtn.setBackgroundResource(R.drawable.shape_of_pay_press);
        zhifubaoPress.setVisibility(View.VISIBLE);
        oldRelative = (RelativeLayout) view;
    }

    @OnClick({R.id.pay_one, R.id.pay_two, R.id.pay_three})
    void chargeTypeClick(View view) {
        switch (view.getId()) {
            case R.id.pay_one:
                chargeType = 0;
                selectMoneyLayout.setLayoutBackColor();
                selectMoneyLayout.setBackgroundResource(R.drawable.shape_of_pay_putdown);
                payOne.setLayoutBuluColor();
                payOne.setBackgroundResource(R.drawable.shape_of_pay_press);
                break;
            case R.id.pay_two:
                chargeType = 1;
                selectMoneyLayout.setLayoutBackColor();
                selectMoneyLayout.setBackgroundResource(R.drawable.shape_of_pay_putdown);
                payTwo.setLayoutBuluColor();
                payTwo.setBackgroundResource(R.drawable.shape_of_pay_press);
                break;
            case R.id.pay_three:
                chargeType = 2;
                selectMoneyLayout.setLayoutBackColor();
                selectMoneyLayout.setBackgroundResource(R.drawable.shape_of_pay_putdown);
                payThree.setLayoutBuluColor();
                payThree.setBackgroundResource(R.drawable.shape_of_pay_press);
                break;
            default:
                break;
        }
        selectMoneyLayout = (SelectMoneyLayout) view;
    }

    //立即支付
    @OnClick(R.id.liji_pay)
    void lijiPayClick() {
        if (!TextUtils.isEmpty(orderNumber)) {
            orderPay(orderNumber);
        } else {
            if (beenList.size() > 0) {
                FetchMoneyBean fetchMoneyBean = beenList.get(chargeType);
                //创建订单
                orderPresenter = new IChargeCreateOrderPresenterImpl(YuYueDetailsActivity.this, YuYueDetailsActivity.this);
                // orderPresenter.createOrder(chargeId, fetchMoneyBean.getTime(), fetchMoneyBean.getMoney(), fetchMoneyBean.getVolume());
                orderPresenter.createOrder(chargeId, fetchMoneyBean.getTime(), 0.1f, fetchMoneyBean.getVolume());
            } else {
                ToastUtils.showShort(this, "下单异常");
            }
        }
    }


    @Override
    protected void initListener() {

    }

//    @Override
//    protected int layoutId() {
//        return R.layout.activity_charge_yuyue_details;
//    }


    @Override
    public void initLayout() {
        super.initLayout();
        setContentView(R.layout.activity_charge_yuyue_details);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        jumpIntent = getIntent();
        chargeId = jumpIntent.getStringExtra("chargeId");
        orderType = jumpIntent.getIntExtra("orderType", 0);
        orderNumber = jumpIntent.getStringExtra("orderId");
        setOnPayListener(this);
        if (!TextUtils.isEmpty(orderNumber)) {
            tvTitle.setText("订单详情");
        } else {
            tvTitle.setText("预约信息");
        }

        ivImg.setBackgroundResource(R.mipmap.wode);
        llImg.setVisibility(View.VISIBLE);
        selectMoneyLayout = payOne;
        payOne.setLayoutBuluColor();
        payOne.setBackgroundResource(R.drawable.shape_of_pay_press);
        oldRelative = zhilianLayoutBtn;
        zhilianLayoutBtn.setBackgroundResource(R.drawable.shape_of_pay_press);
        zhilianPress.setVisibility(View.VISIBLE);
        //查询充电桩详情
        queryChargeDetailsPresenterImpl = new IChargeQueryChargeDetailsPresenterImpl(this, YuYueDetailsActivity.this);
        queryChargeDetailsPresenterImpl.queryChargeDetails(chargeId);
    }

    @Override
    public void refreshView(List<FetchMoneyBean> data) {
        if (beenList.size() > 0) {
            beenList.clear();
        }
        beenList.addAll(data);
        if (beenList.size() > 0) {
            FetchMoneyBean bean = null;
            bean = beenList.get(0);
            payOne.setLayoutText(String.valueOf(bean.getMoney()), String.valueOf(bean.getTime()), String.valueOf(bean.getVolume()));
            bean = beenList.get(1);
            payTwo.setLayoutText(String.valueOf(bean.getMoney()), String.valueOf(bean.getTime()), String.valueOf(bean.getVolume()));
            bean = beenList.get(2);
            payThree.setLayoutText(String.valueOf(bean.getMoney()), String.valueOf(bean.getTime()), String.valueOf(bean.getVolume()));
        } else {
            ToastUtils.showShort(this, "充电价格异常");
        }
    }


    @Override
    public void zhifubaoSucced(ZhiFuBaoPayBean data) {
        PayUtils.PARTNER = data.getAlipayPartner();
        PayUtils.SELLER = data.getAlipaySeller();
        PayUtils.RSA_PRIVATE = data.getAlipayAppRsaPrivate();
        PayUtils.ZFBOrderId = data.getOrderId();
        PayUtils.ZFBNotifyUrl = data.getNotifyUrl();
        FetchMoneyBean fetchMoneyBean = beenList.get(chargeType);

//        String.valueOf(fetchMoneyBean.getMoney()); //替换下面第三个参数

        PayUtils.zfbPay(this, getOrderInfo("充电桩", "充电付款", "0.01"));
    }

    @Override
    public void weixinSucced(WeiXinPayBean data) {
        PayUtils.APP_ID = data.getAppId();
        PayReq req = new PayReq();
        req.appId = data.getAppId();
        req.partnerId = data.getPartnerId();
        req.nonceStr = data.getNonceStr();
        req.prepayId = data.getPrepayId();
        req.timeStamp = data.getTimeStamp();
        req.packageValue = data.getWeixinPackage();
        req.nonceStr = data.getNonceStr();
        req.sign = data.getSign();
        api.registerApp(data.getAppId());
        api.sendReq(req);
    }

    @Override
    public void yuEMessage(String message) {
        if (message.equals("支付成功")) {
            SuccedDialog succedDialog = new SuccedDialog(this, "支付成功");
            succedDialog.setCloseDialogDoListner(new SuccedDialog.CloseDialogDo() {
                @Override
                public void doThings() {
                    paySuccedJump();
                }
            });
        } else {
            ToastUtils.showShort(getApplicationContext(), message);
        }
    }


    //支付宝结果通知
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void zfbPayEvent(PayResult payResult) {
        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
        // String resultInfo = payResult.getResult();
        String resultStatus = payResult.getResultStatus();
        Log.e("okhttp 支付码", resultStatus);
        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
        if (TextUtils.equals(resultStatus, "9000")) {
            ToastUtils.showShort(this, "支付成功");
            paySuccedJump();
        } else {
            // 判断resultStatus 为非“9000”则代表可能支付失败
            // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
            if (TextUtils.equals(resultStatus, "8000")) {
                ToastUtils.showShort(this, "支付结果确认中");
            } else {
                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                ToastUtils.showShort(this, "支付失败");
            }
        }
    }


    @Override
    public void notify(final String orderId) {
        orderNumber = orderId;
        final BaseDialog dialog = new BaseDialog(this);
        dialog.setTitle("温馨提示");
        dialog.setMessage("是否立即支付");
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderPay(orderId);
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void orderPay(final String orderId) {
        final FetchMoneyBean fetchMoneyBean = beenList.get(chargeType);
        switch (payType) {
            case 0://余额支付
                yuEPayPresenterImpl = new IChargeYuEPayPresenterImpl(YuYueDetailsActivity.this
                        , YuYueDetailsActivity.this);
                yuEPayPresenterImpl.yuEPay(orderId, fetchMoneyBean.getMoney(), fetchMoneyBean.getVolume());
                break;
            case 1://微信支付
                iCharWXPayPresenterImpl = new ICharWXPayPresenterImpl(YuYueDetailsActivity.this, YuYueDetailsActivity.this);
//                iCharWXPayPresenterImpl.WXPay(orderId, fetchMoneyBean.getMoney(), fetchMoneyBean.getVolume());
                iCharWXPayPresenterImpl.WXPay(orderId, 0.1f, fetchMoneyBean.getVolume());
                break;
            case 2://支付宝支付
                iCharZFBPayPresenter = new ICharZFBPayPresenterImpl(YuYueDetailsActivity.this, YuYueDetailsActivity.this);
                iCharZFBPayPresenter.ZFBPay(orderId, fetchMoneyBean.getMoney(), fetchMoneyBean.getVolume());
                break;
            default:
                break;
        }
    }


    @Override
    public void refreshView() {

    }

    //更新充电桩信息
    @Override
    public void refreshCharge(NearbyBean nearbyBean) {
        if (nearbyBean == null) {
            return;
        }
        lng = nearbyBean.getLng();
        lat = nearbyBean.getLat();
        presenter = new IChargeYuyueDetailsPresenterImpl(this, YuYueDetailsActivity.this);
        presenter.queryFetchMoney(nearbyBean.getTypeName().equals("快充") ? 1 : 2);
        detailsAddressTv.setText(nearbyBean.getChargerName());
        juliDetailsTv.setText(nearbyBean.getDistance() + "km");
        kaifangTimeTv.setText(nearbyBean.getOpenTime());
        shiyongzhongTv.setText(String.valueOf(nearbyBean.getBusyPoint()));
        kongxianDetailsTv.setText(String.valueOf(nearbyBean.getFreePoint()));

        List<String> list = new ArrayList<String>();
        list.addAll(nearbyBean.getImageList());
        yuyueDetailsBanner.setImageLoader(new GlideImgManager());
        yuyueDetailsBanner.setImages(list);
        yuyueDetailsBanner.start();
    }

    /**
     * 支付成功后跳转
     */
    private void paySuccedJump() {
        Intent intent;
        if (orderType == 1) {
            intent = new Intent(YuYueDetailsActivity.this, PaySuccesActivity.class);
            intent.putExtra("lng", lng);
            intent.putExtra("lat", lat);
        } else {
            intent = new Intent(YuYueDetailsActivity.this, StartChargeActivity.class);
            intent.putExtra("chargerId", chargeId);
            intent.putExtra("orderId", orderNumber);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }

    @Override
    public void onPayResult(BaseResp resp, Activity activity) {//微信支付
        paySuccedJump();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
