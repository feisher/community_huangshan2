package com.yusong.club.ui.school.school.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.school.school.bean.Messages;

import java.util.List;

import butterknife.InjectView;


public class MessagesHolder extends BaseHolder<Messages> {

    @InjectView(R.id.item_head)
    public ImageView mItemHead;
    @InjectView(R.id.item_tiele)
    public TextView itemTitle;
    @InjectView(R.id.item_time)
    public TextView itemTime;
    @InjectView(R.id.item_content)
    public TextView itemContent;
    @InjectView(R.id.item_count)
    public TextView itemCount;


    public MessagesHolder(View itemView,Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List datas, int position) {

    }
}
