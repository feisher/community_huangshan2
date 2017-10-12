package com.yusong.community.ui.shoppers.holder;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.shoppers.bean.TuiJianBean;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         created at 2017/3/6 8:57.
 */

public class TehuiItemHolder extends BaseHolder<TuiJianBean.Commodity> {

    public TehuiItemHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<TuiJianBean.Commodity> datas, int position) {
        TuiJianBean.Commodity commodity = datas.get(position);
        tvItemName.setText(commodity.getItemName());
        if (commodity.getImageList().length > 0) {
            GlideImgManager.loadImage(mContext, commodity.getImageList()[0], imItemImage);
        } else {
            GlideImgManager.loadImage(mContext, "", imItemImage);
        }
        tvNewPrice.setText("￥" + commodity.getPrice());
        tvOldPrice.setText("￥" + commodity.getShowPrice());
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @InjectView(R.id.tv_itemName)
    TextView tvItemName;
    @InjectView(R.id.im_itemImage)
    ImageView imItemImage;
    @InjectView(R.id.tv_newPrice)
    TextView tvNewPrice;
    @InjectView(R.id.tv_oldPrice)
    TextView tvOldPrice;
}
