package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ds on 2017/3/11.
 */

public interface IUploadPhotoActivityPresenter extends BasePresenter {
     void uploadPhotoFile(RequestBody token, RequestBody albumId, MultipartBody.Part image1) ;

    }
