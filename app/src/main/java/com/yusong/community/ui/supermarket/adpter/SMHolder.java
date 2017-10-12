package com.yusong.community.ui.supermarket.adpter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.supermarket.entity.SMCommodityBean;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 * @created at 2017-09-05.
 * @describe: null
 */

public class SMHolder extends BaseHolder<SMCommodityBean> {
    public SMHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<SMCommodityBean> datas, int position) {
        SMCommodityBean bean = datas.get(position);
        tvItemName.setText(bean.getItemName());
        if (bean.getImageList().length > 0) {
            GlideImgManager.loadImage(mContext, bean.getImageList()[0], imItemImage);
        } else {
            GlideImgManager.loadImage(mContext, "", imItemImage);
        }
        tvNewPrice.setText("￥" + String.valueOf(bean.getPrice()));
    }
    @InjectView(R.id.im_itemImage)
    ImageView imItemImage;
    @InjectView(R.id.tv_itemName)
    TextView tvItemName;
    @InjectView(R.id.tv_newPrice)
    TextView tvNewPrice;
}
