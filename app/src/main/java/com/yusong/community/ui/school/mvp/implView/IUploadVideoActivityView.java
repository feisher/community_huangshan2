package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;

/**
 * Created by ruanjian on 2017/3/11.
 */

public interface IUploadVideoActivityView extends BaseView {
    void upLoadVideoSucess(String data);
    void onError();
}
