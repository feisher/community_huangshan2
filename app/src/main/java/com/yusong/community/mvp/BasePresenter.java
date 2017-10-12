package com.yusong.community.mvp;

import rx.Subscription;

public interface BasePresenter {
    void onStart();

    void onDestroy();

    void unSubscribe(Subscription subscription);
}
