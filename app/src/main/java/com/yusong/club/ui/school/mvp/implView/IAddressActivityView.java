package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.TelBookData;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/11.
 */

public interface IAddressActivityView extends BaseView{
    void getschoolTelBookList(List<TelBookData> data);

}
