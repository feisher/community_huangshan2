package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.StudyVideo;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/13.
 */

public interface ICorrelationFragmentView extends BaseView{
    void getVideoAlbumList(List<StudyVideo> data);
    void closeRefreshing();
}
