package com.yusong.club.ui.school.school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.school.school.bean.SecondBean;

import java.util.ArrayList;

/**
 * 二级菜单适配器
 * Created by Administrator on 2016/7/18.
 */
public class ChildAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<SecondBean> mDatas;
    int mPosition;
    public ChildAdapter(Context context, ArrayList<SecondBean> data, int pos){
        this.mContext = context;
        this.mDatas = data;
        this.mPosition = pos;
    }
    @Override
    public int getGroupCount() {
        return mDatas!= null?mDatas.size():0;
    }

    @Override
    public int getChildrenCount(int childPosition) {
        return mDatas.get(childPosition).getSecondBean()!=null
                ?mDatas.get(childPosition).getSecondBean().size():0;
    }

    @Override
    public Object getGroup(int parentPosition) {
        return mDatas.get(parentPosition);
    }

    @Override
    public Object getChild(int parentPosition, int childPosition) {
        return mDatas.get(parentPosition).getSecondBean().get(childPosition);
    }

    @Override
    public long getGroupId(int parentPosition) {
        return parentPosition;
    }

    @Override
    public long getChildId(int parentPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * 主菜单布局
     * @param parentPosition
     * @param isExpandabled  是否展开
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getGroupView(int parentPosition, boolean isExpandabled, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.child_adapter,null);
            holder = new ViewHolder();
            holder.mChildGroupTV = (TextView)view.findViewById(R.id.childGroupTV);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.mChildGroupTV.setText(mDatas.get(parentPosition).getTitle());

        return view;
    }
    class ViewHolder {
        private TextView mChildGroupTV;
    }
    /**
     * 子菜单布局
     * @param parentPosition
     * @param childPosition
     * @param isExpandabled
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getChildView(int parentPosition, int childPosition, boolean isExpandabled, View view, ViewGroup viewGroup) {
        ChildHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(
                    R.layout.child_child, null);
            holder = new ChildHolder();
            holder.childChildTV = (TextView) view.findViewById(R.id.childGroupTV);
            holder.mobile = (TextView) view.findViewById(R.id.score_value);
                    view.setTag(holder);
        } else {
            holder = (ChildHolder) view.getTag();
        }
        holder.childChildTV.setText(mDatas.get(parentPosition).getSecondBean().get(childPosition).getTitle());
        holder.mobile.setText(mDatas.get(parentPosition).getSecondBean().get(childPosition).getMobile());
        return view;
    }
    class ChildHolder {
        private TextView childChildTV;
        private TextView mobile;
    }
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
