package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.VideoAlbum;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/9.
 */

public interface IAllVideoFragmentView extends BaseView{
    void getVideoAlbumList(List<VideoAlbum> data);
    void closeRefreshing();
}
