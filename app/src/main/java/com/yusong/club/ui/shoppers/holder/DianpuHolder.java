package com.yusong.club.ui.shoppers.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.shoppers.bean.CommodityBean;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;


public class DianpuHolder extends BaseHolder <CommodityBean>{
    @InjectView(R.id.im_itemImage)
    ImageView imItemImage;
    @InjectView(R.id.tv_itemName)
    TextView tvItemName;
    @InjectView(R.id.tv_newPrice)
    TextView tvNewPrice;
    public DianpuHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<CommodityBean> datas, int position) {
        CommodityBean bean = datas.get(position);
        tvItemName.setText(bean.getItemName());
        if (bean.getImageList().length > 0) {
            GlideImgManager.loadImage(mContext, bean.getImageList()[0], imItemImage);
        } else {
            GlideImgManager.loadImage(mContext, "", imItemImage);
        }
        tvNewPrice.setText("￥" + String.valueOf(bean.getPrice()));
    }

}
