package com.yusong.community.ui.shoppers.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.TuiJianBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询推荐商品分类
 */


public interface ImplQueryTuijianLeiView extends BaseView {
    void refreshTuijian(List<TuiJianBean> data);
    void tuijianClose();
}
