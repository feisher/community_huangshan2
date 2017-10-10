package com.yusong.club.ui.school.teacher.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.SuperBaseAdapter;
import com.yusong.club.ui.school.teacher.bean.Home;

import java.util.List;

public class HomeAdapter extends SuperBaseAdapter {


    public HomeAdapter(Activity activity, List dataList) {
        super(activity, dataList);
    }

    @Override
    public int getItemLayoutResId() {
        return R.layout.item_teacher_home;
    }

    @Override
    public Object getViewHolder(View rootView) {
        return new ViewHolder(rootView);
    }


    @Override
    public void setItemData(Object dataItem, Object viewHolder) {
        Home home = (Home)dataItem;
        ViewHolder holder = (ViewHolder)viewHolder;
        holder.item_name.setText(home.getName());
        holder.item_img.setImageResource(home.getImageInt());
    }

    class ViewHolder{
        public final View root;
        public final ImageView item_img;
        public final TextView item_name;

        public ViewHolder(View root) {
            this.root = root;
            this.item_img = (ImageView) this.root.findViewById(R.id.item_img);
            this.item_name = (TextView) this.root.findViewById(R.id.item_name);

        }
    }
}
