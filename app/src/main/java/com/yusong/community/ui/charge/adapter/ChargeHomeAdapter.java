package com.yusong.community.ui.charge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yusong.community.R;
import com.yusong.community.ui.charge.holder.ChargeHomeHolder;
import com.yusong.community.ui.home.adapter.HomeItemsAdapter;
import com.yusong.community.utils.glide.GlideImgManager;

/**
 * Created by Mr_Peng on 2016/12/26.
 */

public class ChargeHomeAdapter extends RecyclerView.Adapter<ChargeHomeHolder> {

    private Context mContext;
    private int[] items_img;
    private int[] items_name;

    private HomeItemsAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public ChargeHomeAdapter(Context mContext, int[] items_img, int[] items_name){
        this.items_img = items_img;
        this.items_name = items_name;
        this.mContext = mContext;
    }

    @Override
    public ChargeHomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_charge_home, parent, false);
        return new ChargeHomeHolder(view);
    }

    @Override
    public void onBindViewHolder(ChargeHomeHolder holder, final int position) {

        GlideImgManager.loadImage(mContext,items_img[position],holder.iv_itemImg);
        holder.tv_itemName.setText(items_name[position]);
        holder.ll_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items_img.length;
    }

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(HomeItemsAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
