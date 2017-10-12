package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.AheadBean;

import java.util.List;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/31.
 *         描述: 查询提前放假
 */

public interface IQueryAheadView extends BaseView {
    void queryAheadSucced(List<AheadBean> data);
}
