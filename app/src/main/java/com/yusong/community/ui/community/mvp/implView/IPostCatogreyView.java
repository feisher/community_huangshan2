package com.yusong.community.ui.community.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.community.mvp.entity.PostsCatogrey;

import java.util.List;

/**
 * Created by feisher on 2017/1/14.
 */
public  interface IPostCatogreyView extends BaseView {
    void setViewPagerAdapter(List<PostsCatogrey> data);
}
