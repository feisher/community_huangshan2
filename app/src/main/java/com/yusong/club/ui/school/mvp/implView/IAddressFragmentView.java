package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.TelBookData;

import java.util.List;

/**
 * create by feisher on 2017/3/21
 * Email：458079442@qq.com
 */
public interface IAddressFragmentView extends BaseView {
   void getschoolTelBookListCallback(List<TelBookData> data);
}