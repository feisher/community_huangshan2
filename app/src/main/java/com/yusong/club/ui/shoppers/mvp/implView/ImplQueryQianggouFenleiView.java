package com.yusong.club.ui.shoppers.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.shoppers.bean.QiangGouDaleiBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询抢购分类
 */

public interface ImplQueryQianggouFenleiView extends BaseView {
    void refreshQianggoufenlei(List<QiangGouDaleiBean> data);
}
