package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.TelBook;

/**
 * Created by ruanjian on 2017/3/13.
 */

public interface IPersonageFragmentIView extends BaseView{
    void  getBookList(TelBook data);
    void closeRefreshing();
}
