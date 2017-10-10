package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.ChatGroup;
import com.yusong.club.ui.school.teacher.bean.MemberGroup;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/13.
 */

public interface IGroupFragmentView  extends BaseView{
  void   getchatGroupList(ChatGroup data);
  void   getMemberList(List<MemberGroup> data);
}
