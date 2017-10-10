package com.yusong.club.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.easeui.model.DataBean;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.home.holder.NotificationHolder;

import java.util.List;


/**
 * ***********************************************
 * **                ┏┓   ┏┓                    **
 * **                ┏┛┻━━━┛┻┓                  **
 * **                ┃       ┃                  **
 * **                ┃   ━   ┃                  **
 * **                ┃ ┳┛ ┗┳ ┃                  **
 * **                ┃       ┃                  **
 * **                ┃   ┻   ┃                  **
 * **                ┃       ┃                  **
 * **                ┗━┓   ┏━┛                  **
 * **                  ┃   ┃                    **
 * **                  ┃   ┃                    **
 * **                  ┃   ┗━━━┓                **
 * **                  ┃       ┣┓               **
 * **                  ┃       ┏┛               **
 * **                  ┗┓┓┏━┳┓┏┛                **
 * **                   ┃┫┫ ┃┫┫                 **
 * **                  ┗┻┛ ┗┻┛                  **
 * ***********************************************
 * **               神兽助我 扬我神威              **
 * ***********************************************
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/30 12:44 </li>
 * </ul>
 */
public class NotificationAdapter extends SwipeMenuAdapter<NotificationHolder> {

    private final Context mContext;
    private List<DataBean> mDatas;
    protected DefaultAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public NotificationAdapter(List<DataBean> mDatas, Context context) {
        super();
        this.mDatas = mDatas;
        this.mContext = context;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_im_notify, parent, false);
        return view;
    }

    @Override
    public NotificationHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        NotificationHolder mAddressHolder = new NotificationHolder(realContentView, mContext);
        mAddressHolder.setOnItemClickListener(new BaseHolder.OnViewClickListener() {//设置Item点击事件
            @Override
            public void onViewClick(View view, int position) {

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, mDatas.get(position), position);
                }
            }
        });
        return mAddressHolder;
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {
        holder.setData(mDatas, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setOnItemClickListener(DefaultAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}