package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.LatestPhoto;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/9.
 */

public interface ILatestPhotoFragmentView extends BaseView{
    void getLatestPhotoList(List<LatestPhoto> data);
    void closeRefreshing();
}
