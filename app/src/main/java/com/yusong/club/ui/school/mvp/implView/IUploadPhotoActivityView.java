package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;

/**
 * Created by ds on 2017/3/11.
 *
 */

public interface IUploadPhotoActivityView extends BaseView{
    void upLoadPhotoSucess(String data);
    void onError();
}
