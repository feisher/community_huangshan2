package com.yusong.community.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.yusong.community.R;
import com.yusong.community.pay.bean.WeiXinPayBean;
import com.yusong.community.pay.bean.ZhiFuBaoPayBean;
import com.yusong.community.pay.mvp.presenter.impl.CommonPayPresenterImpl;
import com.yusong.community.pay.mvp.view.ICommonPayView;
import com.yusong.community.pay.zfb.PayResult;
import com.yusong.community.ui.express.activity.CommitSuccessActivity;
import com.yusong.community.ui.shoppers.activity.ShopPaySuccedActivity;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.wxapi.WXPayEntryActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/3 14:57 </li>
 * </ul>
 */
public class CommonPayActivity extends WXPayEntryActivity implements ICommonPayView,
        WXPayEntryActivity.OnPayResultListener {

    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_money)
    TextView mTvMoney;
    @InjectView(R.id.ll_zl)
    LinearLayout mLlZl;
    @InjectView(R.id.ll_zfb)
    LinearLayout mLlZfb;
    @InjectView(R.id.ll_wx)
    LinearLayout mLlWx;
    @InjectView(R.id.btn_pay)
    Button mBtnPay;
    @InjectView(R.id.iv_zl)
    ImageView mIvZl;
    @InjectView(R.id.iv_zfb)
    ImageView mIvZfb;
    @InjectView(R.id.iv_wx)
    ImageView mIvWx;
    private String mOrder_id;
    private String mCharge;

    private int pay_type = ZL_PAY; //0 智联 1 支付宝 2 微信

    public static final int ZL_PAY = 0;
    public static final int ZFB_PAY = 1;
    public static final int WX_PAY = 2;
    private int mCommon_paytype;
    private CommonPayPresenterImpl mPresenter;


    @OnClick({R.id.ll_zl, R.id.ll_zfb, R.id.ll_wx, R.id.btn_pay, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pay:
                switch (pay_type) {
                    case ZL_PAY:
                        mPresenter.zlPay(CacheUtils.getTokenInfo(this).getToken(), mOrder_id, mCommon_paytype);
                        break;
                    case ZFB_PAY:
                        mPresenter.zfbPay(CacheUtils.getTokenInfo(this).getToken(), mOrder_id, mCommon_paytype);
                        break;
                    case WX_PAY:
                        mPresenter.wxPay(CacheUtils.getTokenInfo(this).getToken(), mOrder_id, mCommon_paytype);
                        break;
                }
                break;
            case R.id.ll_zl:
                pay_type = ZL_PAY;
                mIvZl.setBackgroundResource(R.mipmap.selected);
                mIvZfb.setBackgroundResource(R.mipmap.not_select);
                mIvWx.setBackgroundResource(R.mipmap.not_select);
                break;
            case R.id.ll_zfb:
                pay_type = ZFB_PAY;
                mIvZl.setBackgroundResource(R.mipmap.not_select);
                mIvZfb.setBackgroundResource(R.mipmap.selected);
                mIvWx.setBackgroundResource(R.mipmap.not_select);
                break;
            case R.id.ll_wx:
                pay_type = WX_PAY;
                mIvZl.setBackgroundResource(R.mipmap.not_select);
                mIvZfb.setBackgroundResource(R.mipmap.not_select);
                mIvWx.setBackgroundResource(R.mipmap.selected);
                break;
            case R.id.ll_back:
                finish();
                break;
        }
    }


    //支付宝结果通知
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void zfbPayEvent(PayResult payResult) {
        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
        String resultInfo = payResult.getResult();
        String resultStatus = payResult.getResultStatus();
        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
        if (TextUtils.equals(resultStatus, "9000")) {
            ToastUtils.showShort(this, "支付成功");

            if (mCommon_paytype == ActivityConstants.SENDER_ORDER) {
                //寄件跳转
                Intent intent = new Intent(CommonPayActivity.this, CommitSuccessActivity.class);
                intent.putExtra(ActivityConstants.ORDER_TYPE, 1);
                intent.putExtra(ActivityConstants.ORDER_ID, mOrder_id);
                startActivity(intent);
                finish();
            } else if (mCommon_paytype == ActivityConstants.SAVE_ORDER) {
                //存包跳转
                Intent intent = new Intent(CommonPayActivity.this, CommitSuccessActivity.class);
                intent.putExtra(ActivityConstants.ORDER_TYPE, 2);
                intent.putExtra(ActivityConstants.ORDER_ID, mOrder_id);
                startActivity(intent);
                finish();
            } else if (mCommon_paytype == ActivityConstants.STORE_ORDER) {
                //商城跳转
                Intent intent = new Intent(CommonPayActivity.this, ShopPaySuccedActivity.class);
                intent.putExtra("price", mCharge + "元");
                intent.putExtra("payType", pay_type);
                startActivity(intent);
                setResult(ActivityConstants.RESULT_CODE);
                this.finish();
            } else if (mCommon_paytype == ActivityConstants.SUPER_MARKET_OERDER) {
                //超市收银台
                Intent intent = new Intent(CommonPayActivity.this, ShopPaySuccedActivity.class);
                intent.putExtra("price", mCharge + "元");
                intent.putExtra("payType", pay_type);
                intent.putExtra("shopType", 2);
                startActivity(intent);
                setResult(ActivityConstants.RESULT_CODE);
                finish();
            } else if (mCommon_paytype == ActivityConstants.SERVICE_OERDER) {
                //社区服务
                Intent intent = new Intent(CommonPayActivity.this, ShopPaySuccedActivity.class);
                intent.putExtra("price", mCharge + "元");
                intent.putExtra("payType", pay_type);
                intent.putExtra("shopType", 3);
                startActivity(intent);
                setResult(ActivityConstants.RESULT_CODE);
                finish();
            } else {
                finish();
            }
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

    //支付宝支付
    @Override
    public void zfbPay(ZhiFuBaoPayBean data, String subject, String body) {
        PayUtils.PARTNER = data.getAlipayPartner();
        PayUtils.SELLER = data.getAlipaySeller();
        PayUtils.RSA_PRIVATE = data.getAlipayAppRsaPrivate();
        PayUtils.ZFBOrderId = data.getOrderId();
        PayUtils.ZFBNotifyUrl = data.getNotifyUrl();
        String orderInfo = PayUtils.getOrderInfo(subject, body, mCharge);
        PayUtils.zfbPay(this, orderInfo);
    }


    //微信回调
    @Override
    public void onPayResult(BaseResp resp, Activity activity) {
        if (resp.errCode == 0) {
            ToastUtils.showShort(this, "支付成功");
            if (mCommon_paytype == ActivityConstants.SENDER_ORDER) {
                //寄件跳转
                Intent intent = new Intent(CommonPayActivity.this, CommitSuccessActivity.class);
                intent.putExtra(ActivityConstants.ORDER_TYPE, 1);
                intent.putExtra(ActivityConstants.ORDER_ID, mOrder_id);
                startActivity(intent);
                finish();
            } else if (mCommon_paytype == ActivityConstants.SAVE_ORDER) {
                //存包跳转
                Intent intent = new Intent(CommonPayActivity.this, CommitSuccessActivity.class);
                intent.putExtra(ActivityConstants.ORDER_TYPE, 2);
                intent.putExtra(ActivityConstants.ORDER_ID, mOrder_id);
                startActivity(intent);
                finish();
            } else if (mCommon_paytype == ActivityConstants.STORE_ORDER) {
                //商城跳转
                Intent intent = new Intent(CommonPayActivity.this, ShopPaySuccedActivity.class);
                intent.putExtra("price", mCharge + "元");
                intent.putExtra("payType", pay_type);
                startActivity(intent);
                setResult(ActivityConstants.RESULT_CODE);
                this.finish();
            } else if (mCommon_paytype == ActivityConstants.SUPER_MARKET_OERDER) {
                //超市收银台
                Intent intent = new Intent(CommonPayActivity.this, ShopPaySuccedActivity.class);
                intent.putExtra("price", mCharge + "元");
                intent.putExtra("payType", pay_type);
                intent.putExtra("shopType", 2);
                startActivity(intent);
                setResult(ActivityConstants.RESULT_CODE);
                finish();
            } else if (mCommon_paytype == ActivityConstants.SERVICE_OERDER) {
                //社区服务
                Intent intent = new Intent(CommonPayActivity.this, ShopPaySuccedActivity.class);
                intent.putExtra("price", mCharge + "元");
                intent.putExtra("payType", pay_type);
                intent.putExtra("shopType", 3);
                startActivity(intent);
                setResult(ActivityConstants.RESULT_CODE);
                finish();
            } else {
                finish();
            }
        }

    }

    //微信支付
    @Override
    public void wxPay(WeiXinPayBean data) {
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


    //余额支付成功 跳转成功界面
    @Override
    public void jumpActivity() {
        if (mCommon_paytype == ActivityConstants.SENDER_ORDER) {
            //寄件跳转
            Intent intent = new Intent(CommonPayActivity.this, CommitSuccessActivity.class);
            intent.putExtra(ActivityConstants.ORDER_TYPE, 1);
            intent.putExtra(ActivityConstants.ORDER_ID, mOrder_id);
            startActivity(intent);
            finish();
        } else if (mCommon_paytype == ActivityConstants.SAVE_ORDER) {
            //存包跳转
            Intent intent = new Intent(CommonPayActivity.this, CommitSuccessActivity.class);
            intent.putExtra(ActivityConstants.ORDER_TYPE, 2);
            intent.putExtra(ActivityConstants.ORDER_ID, mOrder_id);
            startActivity(intent);
            finish();
        } else if (mCommon_paytype == ActivityConstants.STORE_ORDER) {
            Intent intent = new Intent(CommonPayActivity.this, ShopPaySuccedActivity.class);
            intent.putExtra("price", mCharge + "元");
            intent.putExtra("payType", pay_type);
            intent.putExtra("shopType", 1);
            startActivity(intent);
            setResult(ActivityConstants.RESULT_CODE);
            finish();
        } else if (mCommon_paytype == ActivityConstants.SUPER_MARKET_OERDER) {
            //超市收银台
            Intent intent = new Intent(CommonPayActivity.this, ShopPaySuccedActivity.class);
            intent.putExtra("price", mCharge + "元");
            intent.putExtra("payType", pay_type);
            intent.putExtra("shopType", 2);
            startActivity(intent);
            setResult(ActivityConstants.RESULT_CODE);
            finish();
        } else if (mCommon_paytype == ActivityConstants.SERVICE_OERDER) {
            //社区服务
            Intent intent = new Intent(CommonPayActivity.this, ShopPaySuccedActivity.class);
            intent.putExtra("price", mCharge + "元");
            intent.putExtra("payType", pay_type);
            intent.putExtra("shopType", 3);
            startActivity(intent);
            setResult(ActivityConstants.RESULT_CODE);
            finish();
        } else {
            finish();
        }
    }


    private void setTitle() {
        Intent intent = getIntent();
        mOrder_id = intent.getStringExtra(ActivityConstants.ORDER_ID);
        mCharge = intent.getStringExtra(ActivityConstants.CHARGE);
        mCommon_paytype = intent.getIntExtra(ActivityConstants.COMMON_PAYTYPE, 1);
        if (mCommon_paytype == ActivityConstants.SENDER_ORDER) {
            mTvTitle.setText("寄件支付");
        } else if (mCommon_paytype == ActivityConstants.SAVE_ORDER) {
            mTvTitle.setText("存包支付");
        } else if (mCommon_paytype == ActivityConstants.STORE_ORDER) {
            mTvTitle.setText("商城支付");
        } else if (mCommon_paytype == ActivityConstants.QUJIAN_OERDER) {
            mTvTitle.setText("超时支付");
        } else if (mCommon_paytype == ActivityConstants.SUPER_MARKET_OERDER) {
            mTvTitle.setText("超市收银台");
        }
        mTvMoney.setText(mCharge + "元");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle();
        EventBus.getDefault().register(this);
        mPresenter = new CommonPayPresenterImpl(this, this);
        super.setOnPayListener(this);
    }


    @Override
    public void initLayout() {
        setContentView(R.layout.activity_common_pay);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showProgressDialog() {

    }
}
