package com.yusong.community.ui.community_notice.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.community_notice.entity.NoticeBean;

import java.util.List;


/**
 * @author Mr_Peng
 * @created at 2017-09-06.
 * @describe: null
 */

public class NoticeAdapter extends DefaultAdapter<NoticeBean> {



    public NoticeAdapter(List<NoticeBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<NoticeBean> getHolder(View v) {
        return new NoticeHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_commodity_noticeactivity;
    }
}
