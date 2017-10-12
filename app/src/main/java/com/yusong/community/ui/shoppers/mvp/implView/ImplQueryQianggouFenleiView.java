package com.yusong.community.ui.shoppers.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.QiangGouDaleiBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询抢购分类
 */

public interface ImplQueryQianggouFenleiView extends BaseView {
    void refreshQianggoufenlei(List<QiangGouDaleiBean> data);
}
