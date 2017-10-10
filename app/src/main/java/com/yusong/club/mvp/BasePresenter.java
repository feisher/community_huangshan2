package com.yusong.club.mvp;

import rx.Subscription;

public interface BasePresenter {
    void onStart();

    void onDestroy();

    void unSubscribe(Subscription subscription);
}
