package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/27.
 */

public interface IEducationActivityPresenter extends BasePresenter {

    void deleteStudyVideo(String token,int videoId);
    void studyVideoList(String token,int offset, int limit);
}
