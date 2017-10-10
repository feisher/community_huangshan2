package com.yusong.club.ui.me.activity;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yusong.club.R;
import com.yusong.club.pay.PayUtils;
import com.yusong.club.pay.bean.WeiXinPayBean;
import com.yusong.club.pay.bean.ZhiFuBaoPayBean;
import com.yusong.club.pay.zfb.PayResult;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.me.adapter.PreferenceAdapter;
import com.yusong.club.ui.me.mvp.entity.MoneyList;
import com.yusong.club.ui.me.mvp.implView.IRechargeView;
import com.yusong.club.ui.me.mvp.presenter.impl.RechargePresenterImpl;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.wxapi.WXPayEntryActivity;

import org.apache.commons.lang.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <ul>
 * <li>功能说明：充值界面</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/7 15:50 </li>
 * </ul>
 */
public class RechargeActivity extends WXPayEntryActivity implements IRechargeView, WXPayEntryActivity.OnPayResultListener {

    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.et_money)
    EditText mEtMoney;
    @InjectView(R.id.ll_wx)
    LinearLayout mLlWx;
    @InjectView(R.id.ll_zfb)
    LinearLayout mLlZfb;
    @InjectView(R.id.btn_pay)
    Button mBtnPay;
    @InjectView(R.id.iv_wx)
    ImageView mIvWx;
    @InjectView(R.id.iv_zfb)
    ImageView mIvZfb;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private int pay_type = WX_PAY; // 1 支付宝 2 微信

    public static final int ZFB_PAY = 1;
    public static final int WX_PAY = 2;
    private RechargePresenterImpl mPresenter;
    private String mMoney;
    private PreferenceAdapter mAdapter;
    private MoneyList mMoneyList;
    private final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, "wxd53fcae293b59597");

//    @Override
//    protected int layoutId() {
//        return R.layout.activity_me_recharge;
//    }


    @Override
    public void initLayout() {
        setContentView(R.layout.activity_me_recharge);
    }

    @Override
    public void setAdapter(List<MoneyList> data) {


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        mAdapter = new PreferenceAdapter(data, this);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addItemDecoration(new SpaceItemDecoration(0, 10));
        mRecycleView.setLayoutManager(gridLayoutManager);
        mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                mMoneyList = (MoneyList) data;
                mEtMoney.setText(mMoneyList.getMoney() / 100 + "");
                mAdapter.setSelectionItem(position);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        setOnPayListener(this);
        mTvTitle.setText("余额充值");
        mPresenter = new RechargePresenterImpl(this, this);
        mPresenter.queryPreferenceList();
    }


    @OnClick({R.id.ll_back, R.id.ll_wx, R.id.ll_zfb, R.id.btn_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_wx:
                pay_type = WX_PAY;
                mIvZfb.setBackgroundResource(R.mipmap.not_select);
                mIvWx.setBackgroundResource(R.mipmap.selected);

                break;
            case R.id.ll_zfb:
                pay_type = ZFB_PAY;
                mIvZfb.setBackgroundResource(R.mipmap.selected);
                mIvWx.setBackgroundResource(R.mipmap.not_select);

                break;
            case R.id.btn_pay:

                mMoney = mEtMoney.getText().toString().trim();

                if (StringUtils.isEmpty(mMoney)) {
                    ToastUtils.showShort(this, "请选择或输入充值金额");
                    return;
                }
                switch (pay_type) {

                    case ZFB_PAY:
                        mPresenter.zfbPay(CacheUtils.getToken(this), Integer.parseInt(mMoney) * 100);
                        break;
                    case WX_PAY:
                        mPresenter.wxPay(CacheUtils.getToken(this), Integer.parseInt(mMoney) * 100);
                        break;
                }

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

    //支付宝支付
    @Override
    public void zfbPay(ZhiFuBaoPayBean data, String subject, String body) {
        PayUtils.PARTNER = data.getAlipayPartner();
        PayUtils.SELLER = data.getAlipaySeller();
        PayUtils.RSA_PRIVATE = data.getAlipayAppRsaPrivate();
        PayUtils.ZFBOrderId = data.getOrderId();
        PayUtils.ZFBNotifyUrl = data.getNotifyUrl();
        String orderInfo = PayUtils.getOrderInfo(subject, body, mMoney);
//        String orderInfo = PayUtils.getOrderInfo(subject, body, "0.01");
        PayUtils.zfbPay(this, orderInfo);
    }


    //微信回调
    @Override
    public void onPayResult(BaseResp resp, Activity activity) {
        ToastUtils.showShort(this, "支付成功");
        activity.finish();
        finish();
    }

    //微信支付
    @Override
    public void wxPay(WeiXinPayBean data) {
        PayUtils.APP_ID = data.getAppId();
        PayReq req = new PayReq();
        req.appId = data.getAppId();
        req.partnerId = data.getPartnerId();
        req.nonceStr = data.getNonceStr();
        req.timeStamp = data.getTimeStamp();
        req.prepayId = data.getPrepayId();
        req.packageValue = data.getWeixinPackage();
        req.nonceStr = data.getNonceStr();
        req.sign = data.getSign();
        msgApi.registerApp(data.getAppId());
        msgApi.sendReq(req);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showProgressDialog() {

    }

}
