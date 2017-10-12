package com.yusong.community.ui.evaluate.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author Mr_Peng
 * @created at 2017-09-02.
 * @describe: null
 */

public interface CommitEvaluatePresenter extends BasePresenter {
    void commitEvaluate(
            RequestBody token,
            RequestBody categoryType,
            RequestBody proprietorId, RequestBody proprietorName,
            RequestBody proprietorMobile,
            RequestBody proprietorAddress,
            RequestBody content, MultipartBody.Part image1,
            MultipartBody.Part image2, MultipartBody.Part image3,
            MultipartBody.Part image4);

    void commitEvaluate(
            RequestBody token,
            RequestBody categoryType,
            RequestBody proprietorId, RequestBody proprietorName,
            RequestBody proprietorMobile,
            RequestBody proprietorAddress,
            RequestBody content, MultipartBody.Part image1,
            MultipartBody.Part image2, MultipartBody.Part image3);

    void commitEvaluate(
            RequestBody token,
            RequestBody categoryType,
            RequestBody proprietorId, RequestBody proprietorName,
            RequestBody proprietorMobile,
            RequestBody proprietorAddress,
            RequestBody content, MultipartBody.Part image1,
            MultipartBody.Part image2);

    void commitEvaluate(
            RequestBody token,
            RequestBody categoryType,
            RequestBody proprietorId, RequestBody proprietorName,
            RequestBody proprietorMobile,
            RequestBody proprietorAddress,
            RequestBody content, MultipartBody.Part image1);

    void commitEvaluate(
            RequestBody token,
            RequestBody categoryType,
            RequestBody proprietorId, RequestBody proprietorName,
            RequestBody proprietorMobile,
            RequestBody proprietorAddress,
            RequestBody content);
}
