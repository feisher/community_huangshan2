package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.school.mvp.entity.Role;

import java.util.List;

/**
 * Created by dingsheng on 2016/11/14.
 */

public class ChoseParentTypeAdp extends BaseAdapter {
    private Context mContext;
    private List<Role.RoleListBean.GuardianListBean> titles;
    private LayoutInflater mInflater;

    public ChoseParentTypeAdp(Context mContext, List<Role.RoleListBean.GuardianListBean> titles) {
        this.mContext = mContext;
        this.titles = titles;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_choose_type, null);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.imageView = (CheckBox) convertView.findViewById(R.id.iv_other);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(titles.get(position).getClazzName());
        return convertView;
    }

    public final class ViewHolder {
        public TextView title;
        public CheckBox imageView;
    }


}
