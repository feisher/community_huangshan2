package com.yusong.club.ui.school.school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.school.school.bean.Notice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/1/8.
 */
public class NoticeAdapter extends BaseAdapter {
    private List<Notice> data;
    private LayoutInflater inflater;

    public NoticeAdapter(List<Notice> data, Context context) {
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Notice getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_notice, null);
            viewHolder = new ViewHolder();
            viewHolder.item_img = (ImageView) convertView.findViewById(R.id.item_img);
            viewHolder.item_title = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.item_time = (TextView) convertView.findViewById(R.id.item_time);
            viewHolder.item_content = (TextView) convertView.findViewById(R.id.item_content);
            viewHolder.item_count = (TextView) convertView.findViewById(R.id.item_count);
            viewHolder.item_delete = (LinearLayout) convertView.findViewById(R.id.item_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.item_img.setImageResource(R.mipmap.notice);
        if (getItem(position).getImgStyle()==1) {
            viewHolder.item_img.setImageResource(R.mipmap.notice);
        }else if(getItem(position).getImgStyle()==2){
            viewHolder.item_img.setImageResource(R.mipmap.inform);
        }
        viewHolder.item_title.setText(getItem(position).getTitle());
        viewHolder.item_time.setText(getItem(position).getTime());
        viewHolder.item_content.setText(getItem(position).getNoticeContent());
        viewHolder.item_count.setText("阅读"+getItem(position).getCount()+"次");
        viewHolder.item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class ViewHolder{
        ImageView item_img;
        TextView item_title;
        TextView item_time;
        TextView item_content;
        TextView item_count;
        LinearLayout item_delete;
    }
}
