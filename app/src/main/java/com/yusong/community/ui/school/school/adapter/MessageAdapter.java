package com.yusong.community.ui.school.school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.school.bean.Messages;
import com.yusong.community.ui.school.school.holder.MessagesHolder;

import java.util.List;


public class MessageAdapter extends SwipeMenuAdapter<MessagesHolder> {

    private List<Messages> datas;
    Context mContext;

    protected DefaultAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public MessageAdapter(List<Messages> datas, Context mContext) {
        super();
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_school_msg, parent, false);
        return view;
    }

    @Override
    public MessagesHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        MessagesHolder messagesHolder = new MessagesHolder(realContentView,mContext);
        messagesHolder.setOnItemClickListener(new BaseHolder.OnViewClickListener() {//设置Item点击事件
            @Override
            public void onViewClick(View view, int position) {

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, datas.get(position), position);
                }
            }
        });
        return messagesHolder;
    }


    @Override
    public void onBindViewHolder(MessagesHolder holder, int position) {
        holder.itemTitle.setText(datas.get(position).getTitle());
        holder.itemTime.setText(datas.get(position).getTime());
        holder.itemContent.setText(datas.get(position).getMsgContent());
        holder.itemCount.setText(datas.get(position).getCount());
    }

    public void setOnItemClickListener(DefaultAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
