package com.yusong.community.mvp.implPresenter;

import android.content.Context;

import com.yusong.community.mvp.BasePresenter;
import com.yusong.community.mvp.implView.BaseView;

import org.greenrobot.eventbus.EventBus;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {
    protected final String TAG = this.getClass().getSimpleName();
    public Context mContext;
    protected CompositeSubscription mCompositeSubscription;

    public V mView;

    public BasePresenterImpl() {
        onStart();
    }

    public BasePresenterImpl(V view, Context context) {
        mView = view;
        mContext = context;
        onStart();
    }

    @Override
    public void onStart() {
        if (useEvenBus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }
    }

    /**
     * 使用EventBus则此方法返回true
     *
     * @return
     */
    public boolean useEvenBus() {
        return false;
    }

    @Override
    public void onDestroy() {
        if (useEvenBus()) {
            EventBus.getDefault().unregister(this);
        }
        unSubscribe();
        mView = null;
    }

    protected void addSubcribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);//将所有subscription放入,集中处理
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//保证activity结束时取消所有正在执行的订阅
        }
    }

    @Override
    public void unSubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


}