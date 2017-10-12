package com.yusong.community.ui.shoppers.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.PinLunBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询商品评论
 */

public interface ImplQueryShangPinPinLunView extends BaseView {
    void refreshPinlun(List<PinLunBean> data);
}
