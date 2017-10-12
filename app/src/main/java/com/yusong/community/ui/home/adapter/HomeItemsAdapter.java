package com.yusong.community.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yusong.community.R;
import com.yusong.community.ui.express.holder.HomeItemsHolder;
import com.yusong.community.utils.glide.GlideImgManager;

/**
 * Created by quaner on 16/12/24.
 */

public class HomeItemsAdapter extends RecyclerView.Adapter<HomeItemsHolder>{

    private Context mContext;
    private int[] items_img;

    private String[] items_name;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public HomeItemsAdapter(Context mContext, int[] items_img, String[] items_name){
        this.items_img = items_img;
        this.items_name = items_name;
        this.mContext = mContext;
    }

    @Override
    public HomeItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_express_home, parent, false);
        return new HomeItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeItemsHolder holder, final int position) {

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

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
