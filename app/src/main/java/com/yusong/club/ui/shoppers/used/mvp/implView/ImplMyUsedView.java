package com.yusong.club.ui.shoppers.used.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.shoppers.used.bean.MyUsedBean;

import java.util.List;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/18.
 *         描述: null
 */

public interface ImplMyUsedView extends BaseView {
    void close();

    void myUsedSucced(List<MyUsedBean> data);

    void outSucced();

    void deleteSucced();
}
