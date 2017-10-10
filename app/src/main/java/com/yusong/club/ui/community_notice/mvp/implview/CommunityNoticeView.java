package com.yusong.club.ui.community_notice.mvp.implview;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.community_notice.entity.NoticeBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-06.
 * @describe: null
 */

public interface CommunityNoticeView extends BaseView {
    void querySucced(List<NoticeBean> data);

    void queryError();
}
