package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.school.teacher.bean.ClassHomework;
import com.yusong.community.utils.DateUtil;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

public class HomeworkAdapter extends BaseAdapter {
    private List<ClassHomework> data;
    private LayoutInflater inflater;
    private Context context;

    public HomeworkAdapter(List<ClassHomework> data, Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
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
    public ClassHomework getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_teacher_homework, null);
            viewHolder = new ViewHolder();
            viewHolder.im_subject = (ImageView) convertView.findViewById(R.id.im_subject);
            viewHolder.tv_subject = (TextView) convertView.findViewById(R.id.tv_subject);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_time.setText(DateUtil.getDate());
        viewHolder.tv_content.setText(getItem(position).getContent());
        viewHolder.tv_subject.setText(getItem(position).getCourseName());
        if (!getItem(position).getPhotoPath().equals("")){
            GlideImgManager.loadImage(context,getItem(position).getPhotoPath(),viewHolder.im_subject);
        }else {
            viewHolder.im_subject.setImageResource(R.mipmap.teacher_china);
        }

//        if (getItem(position).g()==1) {
//            viewHolder.im_subject.setImageResource(R.mipmap.teacher_china);
//            viewHolder.tv_subject.setText("语文");
//        }else if(getItem(position).getSubject()==2){
//            viewHolder.im_subject.setImageResource(R.mipmap.teacher_math);
//            viewHolder.tv_subject.setText("数学");
//        }else if(getItem(position).getSubject()==3){
//            viewHolder.im_subject.setImageResource(R.mipmap.teacher_english);
//            viewHolder.tv_subject.setText("英语");
//        }else if(getItem(position).getSubject()==4){
//            viewHolder.im_subject.setImageResource(R.mipmap.teacher_geography);
//            viewHolder.tv_subject.setText("地理");
//        }
        return convertView;
    }

    class ViewHolder{
        ImageView im_subject;
        TextView tv_subject;
        TextView tv_time;
        TextView tv_content;
    }
}
