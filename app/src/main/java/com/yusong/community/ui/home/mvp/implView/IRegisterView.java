package com.yusong.community.ui.home.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.home.mvp.entity.Protocol;

/**
 * Created by quaner on 17/1/3.
 */

public interface IRegisterView extends BaseView {

    void recovery();

    void start();

    void upDataTime(Long aLong);

    void close();

    void jumpActivity(Protocol data);
}
