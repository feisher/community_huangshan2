package com.yusong.club.ui.community_notice.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.community_notice.entity.NoticeBean;

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
