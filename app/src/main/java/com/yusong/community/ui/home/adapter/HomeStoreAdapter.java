package com.yusong.community.ui.home.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yusong.community.R;
import com.yusong.community.ui.home.holder.HomoStoreHolder;
import com.yusong.community.ui.shoppers.bean.PreBusiness;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

/**
 * Created by quaner on 16/12/27.
 */

public class HomeStoreAdapter extends RecyclerView.Adapter<HomoStoreHolder> {

    private List<PreBusiness>data;
    private final Context mContext;


    public HomeStoreAdapter(Context context,List<PreBusiness>data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public HomoStoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(mContext, R.layout.item_fragment_homestore, null);
        return new HomoStoreHolder(view);
    }

    @Override
    public void onBindViewHolder(HomoStoreHolder holder, final int position) {
        GlideImgManager.loadImage(mContext,data.get(position).getImageInt(),holder.mIvCommodity);
        holder.mTvCommodityName.setText(data.get(position).getName());
        holder.mTvPrice.setText(data.get(position).getNewPrice());
        holder.mTvYjj.setText(data.get(position).getOldPrice());
        holder.mTvYjj.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );//设置横线
        holder.mLl_commodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(position);
            }
        });

    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
