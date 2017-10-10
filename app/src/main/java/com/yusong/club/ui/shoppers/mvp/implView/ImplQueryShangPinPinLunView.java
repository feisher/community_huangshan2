package com.yusong.club.ui.shoppers.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.shoppers.bean.PinLunBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询商品评论
 */

public interface ImplQueryShangPinPinLunView extends BaseView {
    void refreshPinlun(List<PinLunBean> data);
}
