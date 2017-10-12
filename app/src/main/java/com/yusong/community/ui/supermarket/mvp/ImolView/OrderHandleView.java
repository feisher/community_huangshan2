package com.yusong.community.ui.supermarket.mvp.ImolView;

import com.yusong.community.mvp.implView.BaseView;

/**
 * @author Mr_Peng
 * @created at 2017-09-08.
 * @describe: null
 */

public interface OrderHandleView extends BaseView {
    void commitCommentSucced(String message);

    void confirmReceiptSucced(String message);

    void salesReturnSucced(String message);

    void cancelSmOrderSucced(String message);
}
