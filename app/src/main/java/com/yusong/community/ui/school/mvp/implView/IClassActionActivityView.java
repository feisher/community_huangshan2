package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.school.bean.HuoType;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/10.
 */

public interface IClassActionActivityView extends BaseView {
    void getcategoryList(List<HuoType> data);
}
