package com.yusong.community.ui.community.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by feisher on 2017/1/10.
 */

public interface ICreatePostActivityPresenter extends BasePresenter {
    void createPost(RequestBody token, RequestBody categoryId,
                    RequestBody content,
                    MultipartBody.Part image1, MultipartBody.Part image2,
                    MultipartBody.Part image3, MultipartBody.Part image4);
    void createPost(RequestBody token, RequestBody categoryId,
                    RequestBody content,
                    MultipartBody.Part image1,  MultipartBody.Part image2,
                    MultipartBody.Part image3);
    void createPost(RequestBody token, RequestBody categoryId,
                    RequestBody content,
                    MultipartBody.Part image1,  MultipartBody.Part image2);
    void createPost(RequestBody token, RequestBody categoryId,
                    RequestBody content,
                    MultipartBody.Part image1);
    void createPost(RequestBody token, RequestBody categoryId,
                    RequestBody content);

}
