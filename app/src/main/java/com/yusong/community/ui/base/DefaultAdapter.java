package com.yusong.community.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 *
 */
public abstract class DefaultAdapter<T> extends RecyclerView.Adapter<BaseHolder<T>> {
    public final Context mContext;
    protected List<T> mDatas;
    protected OnRecyclerViewItemClickListener mOnBtnClickListener = null;

    private BaseHolder<T> mHolder;
    public static int mPosition = 0;//记录当前点击的item

    public DefaultAdapter(List<T> mDatas, Context context) {
        super();
        this.mDatas = mDatas;
        this.mContext = context;
    }


    /**
     * 创建Hodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        mHolder = getHolder(view);
        mHolder.setOnItemClickListener(new BaseHolder.OnViewClickListener() {//设置Item点击事件
            @Override
            public void onViewClick(View view, int position) {
                mPosition = position;
                if (mOnBtnClickListener != null) {
                    mOnBtnClickListener.onItemClick(view, mDatas.get(position), position);
                }
            }
        });
        return mHolder;
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseHolder<T> holder, int position) {
        holder.setData(mDatas, position);
    }

    /**
     * 数据的个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public List<T> getInfos() {
        return mDatas;
    }

    public void setInfos(List<T> datas) {
        mDatas = datas;
    }

    /**
     * 获得item的数据
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return mDatas == null ? null : mDatas.get(position);
    }

    /**
     * 子类实现提供holder
     *
     * @param v
     * @return
     */
    public abstract BaseHolder<T> getHolder(View v);

    /**
     * 提供Item的布局
     *
     * @return
     */
    public abstract int getLayoutId();

    public interface OnRecyclerViewItemClickListener<T> {
        void onItemClick(View view, T data, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnBtnClickListener = listener;
    }
}
