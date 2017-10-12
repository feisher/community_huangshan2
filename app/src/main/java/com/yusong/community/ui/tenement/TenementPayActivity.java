package com.yusong.community.ui.tenement;

import android.app.Activity;
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

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yusong.community.R;
import com.yusong.community.pay.PayUtils;
import com.yusong.community.pay.bean.WeiXinPayBean;
import com.yusong.community.pay.bean.ZhiFuBaoPayBean;
import com.yusong.community.pay.zfb.PayResult;
import com.yusong.community.ui.tenement.mvp.implview.TenementPayView;
import com.yusong.community.ui.tenement.mvp.presenter.impl.TenementPayPresenterImpl;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.wxapi.WXPayEntryActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-09-13.
 * @describe: null
 */

public class TenementPayActivity extends WXPayEntryActivity implements WXPayEntryActivity.OnPayResultListener, TenementPayView {
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
    @InjectView(R.id.tv_address)
    TextView tvAddress;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.iv_zhilian)
    ImageView ivZhilian;
    @InjectView(R.id.ll_zhilian)
    LinearLayout llZhilian;
    @InjectView(R.id.iv_wx)
    ImageView ivWx;
    @InjectView(R.id.ll_wx)
    LinearLayout llWx;
    @InjectView(R.id.iv_zfb)
    ImageView ivZfb;
    @InjectView(R.id.ll_zfb)
    LinearLayout llZfb;
    @InjectView(R.id.btn_pay)
    Button btnPay;


    private int pay_type = ZHILIAN_PAY; // 1 支付宝 2 微信
    public static final int ZHILIAN_PAY = 0;
    public static final int ZFB_PAY = 1;
    public static final int WX_PAY = 2;
    private String mMoney;
    private final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, "wxd53fcae293b59597");

    private TenementPayPresenterImpl tenementPayPresenterImpl;
    private String orderId;
    private double price;

    @Override
    public void onPayResult(BaseResp resp, Activity activity) {
        ToastUtils.showShort(this, "支付成功");
        activity.finish();
        finish();
    }

    @Override
    public void initLayout() {
        setContentView(R.layout.activity_tenement_pay);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        orderId = getIntent().getStringExtra("orderId");
        price = getIntent().getDoubleExtra("price", 0);
        setOnPayListener(this);
        tvTitle.setText("余额充值");
        tenementPayPresenterImpl = new TenementPayPresenterImpl(this, this);
        tvAddress.setText(CacheUtils.getAddress(this));
        tvPrice.setText(String.format("￥ %s", price));
    }

    @OnClick({R.id.ll_back, R.id.ll_wx, R.id.ll_zfb, R.id.btn_pay, R.id.ll_zhilian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_zhilian:
                pay_type = ZHILIAN_PAY;
                ivZhilian.setBackgroundResource(R.mipmap.selected);
                ivZfb.setBackgroundResource(R.mipmap.not_select);
                ivWx.setBackgroundResource(R.mipmap.not_select);
                break;
            case R.id.ll_wx:
                pay_type = WX_PAY;
                ivZfb.setBackgroundResource(R.mipmap.not_select);
                ivWx.setBackgroundResource(R.mipmap.selected);
                ivZhilian.setBackgroundResource(R.mipmap.not_select);
                break;
            case R.id.ll_zfb:
                pay_type = ZFB_PAY;
                ivZfb.setBackgroundResource(R.mipmap.selected);
                ivWx.setBackgroundResource(R.mipmap.not_select);
                ivZhilian.setBackgroundResource(R.mipmap.not_select);
                break;
            case R.id.btn_pay:
                switch (pay_type) {
                    case ZHILIAN_PAY:
                        tenementPayPresenterImpl.zlTenementPay(orderId);
                        break;
                    case ZFB_PAY:
                        tenementPayPresenterImpl.zfbTenementPay(orderId);
                        break;
                    case WX_PAY:
                        tenementPayPresenterImpl.wxTenementPay(orderId);
                        break;
                }
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void zfbPayEvent(PayResult payResult) {
        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
        String resultInfo = payResult.getResult();
        String resultStatus = payResult.getResultStatus();
        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
        if (TextUtils.equals(resultStatus, "9000")) {
            ToastUtils.showShort(this, "支付成功");
            finish();
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
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        tenementPayPresenterImpl.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void zhilianPaySucced() {
        ToastUtils.showShort(this, "支付成功");
        this.finish();
    }

    @Override
    public void ZFBPaySucced(ZhiFuBaoPayBean bean) {
        PayUtils.PARTNER = bean.getAlipayPartner();
        PayUtils.SELLER = bean.getAlipaySeller();
        PayUtils.RSA_PRIVATE = bean.getAlipayAppRsaPrivate();
        PayUtils.ZFBOrderId = bean.getOrderId();
        PayUtils.ZFBNotifyUrl = bean.getNotifyUrl();
        String orderInfo = PayUtils.getOrderInfo("物业缴费", "物业缴费", price + "");
//        String orderInfo = PayUtils.getOrderInfo(subject, body, "0.01");
        PayUtils.zfbPay(this, orderInfo);
    }

    @Override
    public void WXPaySucced(WeiXinPayBean bean) {
        PayUtils.APP_ID = bean.getAppId();
        PayReq req = new PayReq();
        req.appId = bean.getAppId();
        req.partnerId = bean.getPartnerId();
        req.nonceStr = bean.getNonceStr();
        req.timeStamp = bean.getTimeStamp();
        req.prepayId = bean.getPrepayId();
        req.packageValue = bean.getWeixinPackage();
        req.nonceStr = bean.getNonceStr();
        req.sign = bean.getSign();
        msgApi.registerApp(bean.getAppId());
        msgApi.sendReq(req);
    }
}
