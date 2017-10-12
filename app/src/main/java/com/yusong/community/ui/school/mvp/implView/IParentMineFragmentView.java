package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.mvp.entity.UserInfoDetails;

/**
 * Created by ruanjian on 2017/3/28.
 */

public interface IParentMineFragmentView extends BaseView{
    void getParentInfo(UserInfoDetails data);
}
