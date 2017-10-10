package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.Notice;

import java.util.List;

/**
 * Created by feisher on 2017/2/24.
 */

public interface INoticeActivityView extends BaseView{
   void noticeDataCallback(List<Notice> data);
   void noticeTeacherDataCallback(List<Notice> data);
   void noticeParentDataCallback(List<Notice> data);
   void closeRefreshing();
   void refreshList(int position);
}
