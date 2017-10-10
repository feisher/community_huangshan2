package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.Notice;
import com.yusong.club.ui.school.mvp.entity.SchoolIntro;

import java.util.List;

/**
 * Created by feisher on 2017/2/24.
 */

public interface ITeacherHomeFragmentView extends BaseView{
   void noticeDataCallback(List<Notice> data);
   void querySchoolIntro(SchoolIntro data);
}
