package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.School;

import java.util.List;

/**
 * Created by feisher on 2017/2/24.
 */

public interface IChooseActivityView extends BaseView{
   void schoolListCallback(List<School> data);
   void selectRoleCallback(int type);
}
