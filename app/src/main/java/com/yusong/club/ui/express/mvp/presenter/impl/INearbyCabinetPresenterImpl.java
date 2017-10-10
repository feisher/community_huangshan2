package com.yusong.club.ui.express.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.express.mvp.entity.NearbyBox;
import com.yusong.club.ui.express.mvp.implView.INearbyCabinetView;
import com.yusong.club.ui.express.mvp.presenter.INearbyCabinetPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class INearbyCabinetPresenterImpl extends BasePresenterImpl<INearbyCabinetView> implements INearbyCabinetPresenter {

    public INearbyCabinetPresenterImpl(INearbyCabinetView view, Context context) {
        super(view, context);
    }


    //查询附近柜子
    @Override
    public void queryNearestBox(float longitude, float latitude) {
//         BaseActivity.showProgressDialog();
        Subscription subscription = HttpUtil.getInstance().queryNearestBox(CacheUtils.getToken(mContext), longitude, latitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<NearbyBox>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<NearbyBox>> result) {

                        if (result.data != null && result.data.size() > 0)
                            mView.setAdapter(result.data);
                    }
                });
        addSubcribe(subscription);
    }
}
