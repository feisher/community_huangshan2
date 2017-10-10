package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.school.bean.HuoType;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/10.
 */

public interface IClassActionFragmentView extends BaseView {
    void getcategoryList(List<HuoType> data);
}
