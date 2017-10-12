package com.yusong.community.ui.school.teacher.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yusong.community.R;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.ArrayList;

/**
 * Created by ds on
 */
public class UpLoadVedioAdapter extends RecyclerView.Adapter<UpLoadVedioAdapter.PhotoViewHolder> {

    private ArrayList<String> photoPaths = new ArrayList<String>();
    private LayoutInflater inflater;

    private Context mContext;
    private OnTouchListener listener;
    int screenWidth = 0;

    public UpLoadVedioAdapter(Context mContext, ArrayList<String> photoPaths, OnTouchListener listener) {
        this.photoPaths = photoPaths;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        // 获取屏幕宽高（方法1）
        screenWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
        // int screenHeight =((Activity) mContext). getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）
        this.listener = listener;

    }

    public interface OnTouchListener {
        void onTouch(View v, int pos);
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_my_photo, parent, false);
        return new PhotoViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {
        if (photoPaths.size() < 1) {
            if (position == photoPaths.size()) {
                GlideImgManager.loadImage(mContext, R.mipmap.add_image, holder.ivPhoto);
                holder.vSelected.setVisibility(View.GONE);
                holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onTouch(v, position);
                    }
                });
            } else {
                GlideImgManager.loadImage(mContext, photoPaths.get(position), holder.ivPhoto);
                holder.vSelected.setVisibility(View.VISIBLE);
            }
        } else {
            GlideImgManager.loadImage(mContext, photoPaths.get(position), holder.ivPhoto);
            holder.vSelected.setVisibility(View.VISIBLE);
        }
        holder.itemView.getLayoutParams().width = screenWidth / 3;
        holder.itemView.getLayoutParams().height = screenWidth / 3;

        holder.vSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTouch(view, position);
            }
        });

    }


    @Override
    public int getItemCount() {
        if (photoPaths.size() < 1) {
            return photoPaths.size() + 1;
        }
        return photoPaths.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private View vSelected;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
            vSelected = itemView.findViewById(R.id.v_selected);
        }
    }

}
