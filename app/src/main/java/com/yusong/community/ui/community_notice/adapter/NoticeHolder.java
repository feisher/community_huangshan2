package com.yusong.community.ui.community_notice.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.community_notice.entity.NoticeBean;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 * @created at 2017-09-06.
 * @describe: null
 */

public class NoticeHolder extends BaseHolder<NoticeBean> {
    @Override
    public void setData(List<NoticeBean> datas, int position) {
        NoticeBean noticeBean = datas.get(position);
        titleTv.setText(noticeBean.getTitle());
        timeTv.setText(noticeBean.getCreateTime());
        contentTv.setText(noticeBean.getContent());
    }

    public NoticeHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @InjectView(R.id.title_tv)
    TextView titleTv;
    @InjectView(R.id.time_tv)
    TextView timeTv;
    @InjectView(R.id.content_tv)
    TextView contentTv;
}
