package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.mvp.entity.Notice;
import com.yusong.community.ui.school.mvp.entity.SchoolIntro;

import java.util.List;

/**
 * Created by feisher on 2017/2/24.
 */

public interface IFirstFragmentView extends BaseView{
   void setBannerAdapter(List<String> data);
   void noticeDataCallback(List<Notice> data);
   void schoolIntroCallback(SchoolIntro data);

}
