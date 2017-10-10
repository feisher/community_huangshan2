package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.yusong.club.R;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

public class DetailActionImageAdapter extends BaseAdapter {
    private Context mContex;
    private List<String> strings = new ArrayList<>();

    public DetailActionImageAdapter(Context mContex, List<String> strings) {
        this.mContex = mContex;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = LayoutInflater.from(mContex).inflate(R.layout.item_action_detail_img, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (strings.size() != 0) {
            GlideImgManager.loadImage(mContex, strings.get(position), viewHolder.item_img);
        }
        return convertView;
    }

    class ViewHolder {
        public final View root;
        public final ImageView item_img;

        public ViewHolder(View root) {
            this.root = root;
            this.item_img = (ImageView) this.root.findViewById(R.id.item_img);
        }
    }
}
