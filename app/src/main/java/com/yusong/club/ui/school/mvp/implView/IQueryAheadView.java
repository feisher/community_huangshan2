package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.AheadBean;

import java.util.List;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/31.
 *         描述: 查询提前放假
 */

public interface IQueryAheadView extends BaseView {
    void queryAheadSucced(List<AheadBean> data);
}
