package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ruanjian on 2017/3/15.
 */

public interface ICreateActionActivityPresenter extends BasePresenter {
   void createAction(RequestBody token, RequestBody categoryId, RequestBody activityName, RequestBody beginTime, RequestBody endTime, RequestBody memo, MultipartBody.Part image1, MultipartBody.Part image2,
                     MultipartBody.Part image3, MultipartBody.Part image4);
   void categoryList(String token);

}
