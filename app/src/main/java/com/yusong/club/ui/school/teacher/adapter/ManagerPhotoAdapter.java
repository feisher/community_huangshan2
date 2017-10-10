package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.yusong.club.R;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/20.
 */

public class ManagerPhotoAdapter extends BaseAdapter {
    private ManagerPhoto managerPhoto;
    private List<PhotoAlbum.ImageListBean> imageListBeanLists;
    private Context mContext;

    public ManagerPhotoAdapter(Context mContext, List<PhotoAlbum.ImageListBean> imageListBeanLists) {
        this.mContext = mContext;
        this.imageListBeanLists = imageListBeanLists;
    }

    public ManagerPhoto getManagerPhoto() {
        return managerPhoto;
    }

    public void setManagerPhoto(ManagerPhoto managerPhoto) {
        this.managerPhoto = managerPhoto;
    }

    @Override
    public int getCount() {
        return imageListBeanLists.size();
    }

    @Override
    public Object getItem(int position) {
        return imageListBeanLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_manager_photo, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PhotoAlbum.ImageListBean dataItem = imageListBeanLists.get(position);
        if (dataItem.isCheck()) {
            viewHolder.vSelected.setChecked(true);
        } else {
            viewHolder.vSelected.setChecked(false);
        }
        viewHolder.vSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (managerPhoto != null) {
                    managerPhoto.judegeSelect(position);
                }
            }
        });
        if (dataItem.getImagePath() != null) {
            GlideImgManager.loadImage(mContext, dataItem.getImagePath(), viewHolder.item_img);
        }
        return convertView;
    }

    public interface ManagerPhoto {
        void judegeSelect(int postion);
    }

    class ViewHolder {
        public final CheckBox vSelected;
        public final View root;
        public final ImageView item_img;

        public ViewHolder(View root) {
            this.root = root;
            this.item_img = (ImageView) this.root.findViewById(R.id.iv_itemImage);
            this.vSelected = (CheckBox) this.root.findViewById(R.id.v_selected);
        }
    }
}
