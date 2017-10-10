package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.UserInfoDetails;

/**
 * Created by ruanjian on 2017/3/28.
 */

public interface IParentMineFragmentView extends BaseView{
    void getParentInfo(UserInfoDetails data);
}
