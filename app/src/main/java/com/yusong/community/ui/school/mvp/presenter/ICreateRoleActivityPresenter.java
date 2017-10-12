package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by feisher on 2017/2/24.
 */

public interface ICreateRoleActivityPresenter extends BasePresenter{
    void queryClazzList(String token, int schoolId,int offset, int limit);
    void teacherApply(RequestBody token,
                      RequestBody schoolId,
                      RequestBody clazzId,
                      RequestBody userName,
                       RequestBody applyRole,
                       RequestBody idCard,
                       RequestBody applyMemo,
                      MultipartBody.Part photo);
    void guardianApply(RequestBody token,
                       RequestBody schoolId,
                      RequestBody clazzId,
                       RequestBody userName,
                       RequestBody applyRole,
                      RequestBody idCard,
                       RequestBody studentName,
                      RequestBody studentNo,
                      RequestBody relation,
                       RequestBody applyMemo,
                      MultipartBody.Part photo);
}
