package com.yusong.community.ui.shoppers.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 *  商城轮播图
 */

public interface ImplRefershView extends BaseView {
    void referImageList(List<String> listStr);
    void imageListClose();
}
