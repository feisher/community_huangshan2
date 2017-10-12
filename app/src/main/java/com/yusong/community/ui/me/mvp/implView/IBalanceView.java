package com.yusong.community.ui.me.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.me.mvp.entity.MoneyList;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/7 16:22 </li>
 * </ul>
 */
public interface IBalanceView extends BaseView {


    void updateBalance(String balance);

    void closeLoading();

    void setMoneyAdapter(List<MoneyList> data);
}