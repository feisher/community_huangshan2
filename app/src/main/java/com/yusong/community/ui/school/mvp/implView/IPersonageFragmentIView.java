package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.TelBook;

/**
 * Created by ruanjian on 2017/3/13.
 */

public interface IPersonageFragmentIView extends BaseView{
    void  getBookList(TelBook data);
    void closeRefreshing();
}
