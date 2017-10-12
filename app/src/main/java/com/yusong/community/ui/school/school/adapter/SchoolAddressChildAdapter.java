package com.yusong.community.ui.school.school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.school.mvp.entity.TelBookData;

import java.util.ArrayList;

/**
 * 二级菜单适配器
 * Created by Administrator on 2016/7/18.
 */
public class SchoolAddressChildAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<TelBookData.ChildrenBean> mDatas;
    int mPosition;
    public SchoolAddressChildAdapter(Context context, ArrayList<TelBookData.ChildrenBean> data, int pos){
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
        return mDatas.get(childPosition).getChildren()!=null
                ?mDatas.get(childPosition).getChildren().size():0;
    }

    @Override
    public Object getGroup(int parentPosition) {
        return mDatas.get(parentPosition);
    }

    @Override
    public Object getChild(int parentPosition, int childPosition) {
        return mDatas.get(parentPosition).getChildren().get(childPosition);
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
        holder.mChildGroupTV.setText(mDatas.get(parentPosition).getOrgName()+"("+mDatas.get(parentPosition).getTel()+")");

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
        holder.childChildTV.setText(mDatas.get(parentPosition).getChildren().get(childPosition).getOrgName());
        holder.mobile.setText("("+mDatas.get(parentPosition).getChildren().get(childPosition).getTel()+")");
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
