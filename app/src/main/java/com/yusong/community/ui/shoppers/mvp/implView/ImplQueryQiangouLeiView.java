package com.yusong.community.ui.shoppers.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.QiangGouBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询抢购大类
 */

public interface ImplQueryQiangouLeiView extends BaseView {
    void refreshQianggou(List<QiangGouBean> data);

    void leiClose();
}
