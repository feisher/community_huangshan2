package com.yusong.club.ui.shoppers.used.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.shoppers.bean.FenLeiBean;
import com.yusong.club.ui.shoppers.used.bean.UsedBean;

import java.util.List;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/18.
 *         描述: null
 */

public interface ImplUsedView extends BaseView {
    void colse();

    void queryFenleiSucced(List<FenLeiBean> data);

    void quertListSucced(List<UsedBean> data);
}
