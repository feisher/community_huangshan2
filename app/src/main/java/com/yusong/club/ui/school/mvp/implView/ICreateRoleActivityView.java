package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.Clazz;

import java.util.List;

/**
 * Created by feisher on 2017/3/3.
 */

public interface ICreateRoleActivityView extends BaseView {
    void clazzCallback(List<Clazz> data);
    void teacherApplyCallback();
    void guardianApplyCallback();

}
