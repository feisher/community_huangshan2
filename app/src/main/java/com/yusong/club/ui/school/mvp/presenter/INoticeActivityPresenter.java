package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by feisher on 2017/2/24.
 */

public interface INoticeActivityPresenter extends BasePresenter{
    void queryPublicNoticeList(String token, int offset, int limit,boolean isRefresh);
    void queryTeacherNoticeList(String token, int offset, int limit);
    void queryGuardianNoticeList(String token, int offset, int limit);
    void deleteNotice(String token,int id, int position);
}
