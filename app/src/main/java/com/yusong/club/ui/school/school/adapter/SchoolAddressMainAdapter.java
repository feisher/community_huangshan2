package com.yusong.club.ui.school.school.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.school.mvp.entity.TelBookData;
import com.yusong.club.ui.school.school.activity.CustomExpandableListView;
import com.yusong.club.ui.school.user.activity.UserInfoDetailsActivity;
import com.yusong.club.utils.ActivityConstants;

import java.util.ArrayList;


/**
 * Created by admin on 2017/1/9.
 */
public class SchoolAddressMainAdapter extends BaseExpandableListAdapter{
    private Context mContext;
    private ArrayList<TelBookData> mData;
    ViewHolder holder = null;
    public SchoolAddressMainAdapter(Context context,  ArrayList<TelBookData> data){
        this.mContext = context;
        this.mData = data;
    }
    @Override
    public int getGroupCount() {
        return mData != null?mData.size():0;
    }

    @Override
    public int getChildrenCount(int parentPosition) {
        return mData.get(parentPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int parentPosition) {
        return mData.get(parentPosition);
    }

    @Override
    public TelBookData.ChildrenBean getChild(int parentPosition, int childPosition) {
        return mData.get(parentPosition).getChildren().get(childPosition);
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
     *  第一级菜单适配器布局
     * @param parentPosition
     * @param isExpanded
     *
     * @param convertView
     * @param viewGroup
     * @return
     */
    @Override
    public View getGroupView(int parentPosition, boolean isExpanded, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.parent_group_item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.group_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(mData.get(parentPosition).getOrgName()+"("+mData.get(parentPosition).getTel()+")");
        return convertView;
    }
    class ViewHolder{
        private TextView title;

    }
    public ExpandableListView getExpandableListView() {

        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        CustomExpandableListView mExpandableListView = new CustomExpandableListView(
                mContext);
        mExpandableListView.setLayoutParams(lp);
        mExpandableListView.setDividerHeight(0);// 取消group项的分割线
        mExpandableListView.setChildDivider(null);// 取消child项的分割线
        mExpandableListView.setGroupIndicator(null);// 取消展开折叠的指示图标
        return mExpandableListView;
    }
    /**
     * 第二级菜单式配
     * @param parentPosition
     * @param childPosition
     * @param isExpanded
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getChildView(final int parentPosition, final int childPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        final ExpandableListView childListView = getExpandableListView();
        //获取子菜单的数据
        final ArrayList<TelBookData.ChildrenBean> childData = new ArrayList<TelBookData.ChildrenBean>();
        final TelBookData.ChildrenBean bean = getChild(parentPosition,childPosition);
        childData.add(bean);
        SchoolAddressChildAdapter adapter = new SchoolAddressChildAdapter(mContext,childData,parentPosition);
        childListView.setAdapter(adapter);

        /**
         * 点击最小级菜单，调用该方法
         * */
        childListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView arg0, View arg1,
                                        int groupIndex, int childIndex, long arg4) {
                if(mListener != null){
                    mListener.onclick(parentPosition,childPosition, childIndex);
                    //点击三级菜单，跳转到编辑菜单界面
                    TelBookData.ChildrenBean.ChildrenBean1 childrenBean1 = mData.get(parentPosition).getChildren().get(childPosition).getChildren().get(childIndex);
                    if (!TextUtils.isEmpty(childrenBean1.getTel())) {
                        Intent mIntent = null;
                        if (childrenBean1.getRoleType()!=0&&childrenBean1.getRoleId()!=0) {
                            mIntent = new Intent(mContext, UserInfoDetailsActivity.class);
                            mIntent.putExtra(ActivityConstants.PORTRAIT,childrenBean1.getIconPath()+"");
                            mIntent.putExtra(ActivityConstants.NAME,childrenBean1.getOrgName());
                            mIntent.putExtra(ActivityConstants.USER_ID,childrenBean1.getRoleId());
                            mIntent.putExtra(ActivityConstants.USER_TYPE,childrenBean1.getRoleType());
                        }else {
                            Uri uri = Uri.parse("tel:" + mData.get(parentPosition).getChildren().get(childPosition).getChildren().get(childIndex).getTel());
                            mIntent = new Intent(Intent.ACTION_DIAL, uri);
                        }
                        mContext.startActivity(mIntent);
                    }else {
                        MyApplication.showMessage("此人无法联系");
                    }
                }
                return false;
            }
        });
        /**
         *子ExpandableListView展开时，因为group只有一项，所以子ExpandableListView的总高度=
         * （子ExpandableListView的child数量 + 1 ）* 每一项的高度
         * */
        childListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.e("xxx",groupPosition+"onGroupExpand>>");
//                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                childListView.setLayoutParams(lp);
            }
        });
        /**
         *子ExpandableListView关闭时，此时只剩下group这一项，
         * 所以子ExpandableListView的总高度即为一项的高度
         * */
        childListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.e("xxx",groupPosition+">>onGroupCollapse");
//                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                childListView.setLayoutParams(lp);
            }
        });
        /**
         * 在这里对二级菜单的点击事件进行操作
         */
        childListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int Position, long id) {
//                if(isClick){
//                    holder.mUpImg.setImageResource(R.drawable.dowm);
//                    isClick = false;
//                }else{
//                    holder.mUpImg.setImageResource(R.drawable.up);
//                    isClick = true;
//                }
                Log.e("Xxx","恭喜你,点击了"+parentPosition+"childpos>>>"+childPosition);
                return false;
            }
        });
        return childListView;
    }
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /*接口回调*/
    public interface OnExpandClickListener{
        void onclick(int parentPosition, int childPosition, int childIndex);
    }
    OnExpandClickListener mListener;
    public void setOnChildListener(OnExpandClickListener listener){
        this.mListener = listener;
    }
}
