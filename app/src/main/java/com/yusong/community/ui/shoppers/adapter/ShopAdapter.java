package com.yusong.community.ui.shoppers.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.shoppers.bean.PreBusiness;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends BaseAdapter {
    private List<PreBusiness> data;
    private LayoutInflater inflater;

    public ShopAdapter(List<PreBusiness> data, Context context) {
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public PreBusiness getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_shop_tuijian, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_itemName = (TextView) convertView.findViewById(R.id.tv_itemName);
            viewHolder.im_itemImage = (ImageView) convertView.findViewById(R.id.im_itemImage);
            viewHolder.tv_newPrice = (TextView) convertView.findViewById(R.id.tv_newPrice);
            viewHolder.tv_oldPrice = (TextView) convertView.findViewById(R.id.tv_oldPrice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_itemName.setText(getItem(position).getName());
        viewHolder.im_itemImage.setImageResource(getItem(position).getImageInt());
        viewHolder.tv_newPrice.setText("¥" + getItem(position).getNewPrice());
        viewHolder.tv_oldPrice.setText(getItem(position).getOldPrice());
        viewHolder.tv_oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        return convertView;
    }

    class ViewHolder {
        TextView tv_itemName;
        ImageView im_itemImage;
        TextView tv_newPrice;
        TextView tv_oldPrice;
    }
}
