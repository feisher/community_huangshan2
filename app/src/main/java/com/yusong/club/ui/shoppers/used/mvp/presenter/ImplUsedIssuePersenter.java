package com.yusong.club.ui.shoppers.used.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/18.
 *         描述: null
 */

public interface ImplUsedIssuePersenter extends BasePresenter {
    void issueUsed(RequestBody token,RequestBody categoryId , RequestBody introduction, MultipartBody.Part image1, MultipartBody.Part image2,
                   MultipartBody.Part image3, MultipartBody.Part image4, RequestBody price, RequestBody showPrice, RequestBody address,
                   RequestBody lng, RequestBody lat);

    void editUsed(RequestBody token, RequestBody id, RequestBody categoryId ,RequestBody introduction, MultipartBody.Part image1, MultipartBody.Part image2,
                  MultipartBody.Part image3, MultipartBody.Part image4, RequestBody price, RequestBody showPrice, RequestBody address,
                  RequestBody lng, RequestBody lat);

}
