package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ruanjian on 2017/3/28.
 */

public interface IUpLoadStudyVideoActivityPresenter extends BasePresenter {
    void uploadVideoFile(RequestBody token, RequestBody albumId, MultipartBody.Part image1) ;

}