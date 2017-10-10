package com.yusong.club.ui.shoppers.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.shoppers.bean.TuiJianBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询推荐商品分类
 */


public interface ImplQueryTuijianLeiView extends BaseView {
    void refreshTuijian(List<TuiJianBean> data);
    void tuijianClose();
}
