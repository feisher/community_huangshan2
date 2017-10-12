package com.yusong.community.pay.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.pay.bean.WeiXinPayBean;
import com.yusong.community.pay.bean.ZhiFuBaoPayBean;
import com.yusong.community.pay.mvp.presenter.ICommonPayPresenter;
import com.yusong.community.pay.mvp.view.ICommonPayView;
import com.yusong.community.ui.express.mvp.entity.RatesInfo;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/3 15:23 </li>
 * </ul>
 */
public class CommonPayPresenterImpl extends BasePresenterImpl<ICommonPayView> implements ICommonPayPresenter {


    public CommonPayPresenterImpl(ICommonPayView view, Context context) {
        super(view, context);
    }

    @Override
    public void zlPay(String token, String id, int paytype) {

        if (paytype == ActivityConstants.SENDER_ORDER) {
            senderZlPay(token, id);
        } else if (paytype == ActivityConstants.SAVE_ORDER) {
            saveZlPay(token, id);
        } else if (paytype == ActivityConstants.STORE_ORDER) {
            shopZLPay(token, id);
        } else if (paytype == ActivityConstants.QUJIAN_OERDER) {
            qujianZlPay(token, id);
        } else if (paytype == ActivityConstants.SUPER_MARKET_OERDER) {
            balanceSmPay(id);
        }else if (paytype == ActivityConstants.SERVICE_OERDER) {
            balanceServicePay(id);
        }

    }

    @Override
    public void zfbPay(String token, String id, int paytype) {

        if (paytype == ActivityConstants.SENDER_ORDER) {

            senderZfbPay(token, id);
        } else if (paytype == ActivityConstants.SAVE_ORDER) {
            saveZfbPay(token, id);
        } else if (paytype == ActivityConstants.STORE_ORDER) {
            shopZFBPay(token, id);
        } else if (paytype == ActivityConstants.QUJIAN_OERDER) {
            qujianZfbPay(token, id);
        } else if (paytype == ActivityConstants.SUPER_MARKET_OERDER) {
            zfbSmPay(id);
        }else if (paytype == ActivityConstants.SERVICE_OERDER) {
            zfbServicePay(id);
        }
    }

    @Override
    public void wxPay(String token, String id, int paytype) {

        if (paytype == ActivityConstants.SENDER_ORDER) {
            senderWxPay(token, id);

        } else if (paytype == ActivityConstants.SAVE_ORDER) {
            saveWxPay(token, id);

        } else if (paytype == ActivityConstants.STORE_ORDER) {
            shopWXPay(token, id);
        } else if (paytype == ActivityConstants.QUJIAN_OERDER) {
            qujianWxPay(token, id);
        } else if (paytype == ActivityConstants.SUPER_MARKET_OERDER) {
            weixinSmPay(id);
        }else if (paytype == ActivityConstants.SERVICE_OERDER) {
            weixinServicePay(id);
        }
    }

    //存包支付宝支付
    private void saveZfbPay(String token, String id) {

        Subscription subscription = HttpUtil.getInstance().saveZfbPay(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ZhiFuBaoPayBean>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ZhiFuBaoPayBean> result) {
                        mView.zfbPay(result.data, "存包订单", "存包订单");
                    }
                });
        addSubcribe(subscription);


    }

    //存包微信支付
    private void saveWxPay(String token, String id) {

        Subscription subscription = HttpUtil.getInstance().saveWxPay(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<WeiXinPayBean>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<WeiXinPayBean> result) {
                        mView.wxPay(result.data);
                    }
                });
        addSubcribe(subscription);
    }


    //寄件支付宝支付
    private void senderZfbPay(String token, String id) {

        Subscription subscription = HttpUtil.getInstance().senderZfbPay(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ZhiFuBaoPayBean>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ZhiFuBaoPayBean> result) {
                        mView.zfbPay(result.data, "寄件订单", "寄件订单");
                    }
                });
        addSubcribe(subscription);

    }

    //寄件微信支付
    private void senderWxPay(String token, String id) {

        Subscription subscription = HttpUtil.getInstance().senderWxPay(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<WeiXinPayBean>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<WeiXinPayBean> result) {

                        mView.wxPay(result.data);
                    }
                });
        addSubcribe(subscription);
    }


    //存包智联支付
    private void saveZlPay(String token, String id) {
        Subscription subscription = HttpUtil.getInstance().saveZlPay(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult result) {

                        mView.jumpActivity();
                    }
                });
        addSubcribe(subscription);

    }

    //寄件智联支付
    private void senderZlPay(String token, String id) {
        Subscription subscription = HttpUtil.getInstance().senderZlPay(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult result) {

                        mView.jumpActivity();
                    }
                });
        addSubcribe(subscription);

    }

    //商城智联支付
    private void shopZLPay(String token, String id) {
        Subscription subscription = HttpUtil.getInstance().shopYuEPay(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult result) {
                        mView.jumpActivity();
                    }
                });
        addSubcribe(subscription);
    }

    //商城微信支付
    private void shopWXPay(String token, String id) {
        Subscription subscription = HttpUtil.getInstance().shopWeiXinPay(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<WeiXinPayBean>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<WeiXinPayBean> result) {
                        mView.wxPay(result.data);
                    }
                });
        addSubcribe(subscription);
    }

    //商城支付宝支付
    private void shopZFBPay(String token, String id) {
        Subscription subscription = HttpUtil.getInstance().shopZFBPay(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ZhiFuBaoPayBean>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ZhiFuBaoPayBean> result) {
                        mView.zfbPay(result.data, "商城订单", "商城订单");
                    }
                });
        addSubcribe(subscription);
    }


    private void qujianZlPay(String token, String id) {
        Subscription subscription = HttpUtil.getInstance().balancePayTimeoutCharge(token, 2, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<RatesInfo>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<RatesInfo> result) {
                        MyApplication.showMessage(result.message);
                        mView.jumpActivity();
                    }
                });
        addSubcribe(subscription);

    }

    private void qujianZfbPay(String token, String id) {

        Subscription subscription = HttpUtil.getInstance().zfbPayTimeoutCharge(token, 2, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ZhiFuBaoPayBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ZhiFuBaoPayBean> result) {
                        mView.zfbPay(result.data, "超时订单", "超时订单");
                    }
                });
        addSubcribe(subscription);


    }

    private void qujianWxPay(String token, String id) {

        Subscription subscription = HttpUtil.getInstance().weixinPayTimeoutCharge(token, 2, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<WeiXinPayBean>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<WeiXinPayBean> result) {

                        mView.wxPay(result.data);
                    }
                });
        addSubcribe(subscription);
    }


    /**
     * 超市支付
     */
    public void balanceSmPay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().smBalancePay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.jumpActivity();
                    }
                });
        addSubcribe(subscription);
    }

    public void weixinSmPay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().smWeixinPay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<WeiXinPayBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<WeiXinPayBean> weiXinPayBeanHttpResult) {
                        mView.wxPay(weiXinPayBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }


    public void zfbSmPay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().smZhifubaoPay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ZhiFuBaoPayBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ZhiFuBaoPayBean> zhiFuBaoPayBeanHttpResult) {
                        mView.zfbPay(zhiFuBaoPayBeanHttpResult.data, "超市订单", "超市订单");
                    }
                });
        addSubcribe(subscription);
    }

    /**
     * 社区服务支付
     */

    public void balanceServicePay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().serviceBalancePay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.jumpActivity();
                    }
                });
        addSubcribe(subscription);
    }

    public void weixinServicePay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().serviceWeixinPay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<WeiXinPayBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<WeiXinPayBean> weiXinPayBeanHttpResult) {
                        mView.wxPay(weiXinPayBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }


    public void zfbServicePay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().serviceZhifubaoPay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ZhiFuBaoPayBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ZhiFuBaoPayBean> zhiFuBaoPayBeanHttpResult) {
                        mView.zfbPay(zhiFuBaoPayBeanHttpResult.data, "社区服务订单", "社区服务订单");
                    }
                });
        addSubcribe(subscription);
    }
}
