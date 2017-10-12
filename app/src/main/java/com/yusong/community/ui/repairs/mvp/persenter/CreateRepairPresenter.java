package com.yusong.community.ui.repairs.mvp.persenter;

import com.yusong.community.mvp.BasePresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author Mr_Peng
 * @created at 2017-09-01.
 * @describe: null
 */

public interface CreateRepairPresenter extends BasePresenter {
    void createRepairs(
            RequestBody token,
            RequestBody categoryId,
            RequestBody proprietorId, RequestBody proprietorName,
            RequestBody proprietorMobile,
            RequestBody proprietorAddress,
            RequestBody content, MultipartBody.Part image1,
            MultipartBody.Part image2, MultipartBody.Part image3,
            MultipartBody.Part image4);

    void createRepairs(
            RequestBody token,
            RequestBody categoryId,
            RequestBody proprietorId, RequestBody proprietorName,
            RequestBody proprietorMobile,
            RequestBody proprietorAddress,
            RequestBody content, MultipartBody.Part image1,
            MultipartBody.Part image2, MultipartBody.Part image3);

    void createRepairs(
            RequestBody token,
            RequestBody categoryId,
            RequestBody proprietorId, RequestBody proprietorName,
            RequestBody proprietorMobile,
            RequestBody proprietorAddress,
            RequestBody content, MultipartBody.Part image1,
            MultipartBody.Part image2);

    void createRepairs(
            RequestBody token,
            RequestBody categoryId,
            RequestBody proprietorId, RequestBody proprietorName,
            RequestBody proprietorMobile,
            RequestBody proprietorAddress,
            RequestBody content, MultipartBody.Part image1);

    void createRepairs(
            RequestBody token,
            RequestBody categoryId,
            RequestBody proprietorId, RequestBody proprietorName,
            RequestBody proprietorMobile,
            RequestBody proprietorAddress,
            RequestBody content);
}
