package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.mvp.entity.Notice;

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
