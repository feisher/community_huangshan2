package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.mvp.entity.LatestPhoto;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/9.
 */

public interface ILatestPhotoFragmentView extends BaseView{
    void getLatestPhotoList(List<LatestPhoto> data);
    void closeRefreshing();
}
