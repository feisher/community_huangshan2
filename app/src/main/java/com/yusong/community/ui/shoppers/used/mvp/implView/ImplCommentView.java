package com.yusong.community.ui.shoppers.used.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.used.bean.UsedCommentBean;

import java.util.List;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/22.
 *         描述: null
 */

public interface ImplCommentView extends BaseView {
    void queryCommentSucced(List<UsedCommentBean> datas);

    void commentSucced();

    void replySucced();

    void close();
}
