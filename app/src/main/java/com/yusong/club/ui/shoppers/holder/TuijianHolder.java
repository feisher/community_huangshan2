package com.yusong.club.ui.shoppers.holder;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.shoppers.bean.TuiJianBean;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         created at 2017/3/6 9:21.
 */

public class TuijianHolder extends BaseHolder<TuiJianBean.Commodity> {

    public TuijianHolder(View itemView, Context context) {
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
        tvNewPrice.setText("￥" + String.valueOf(commodity.getPrice()));
        tvOldPrice.setText("￥" + String.valueOf(commodity.getShowPrice()));
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @InjectView(R.id.im_itemImage)
    ImageView imItemImage;
    @InjectView(R.id.tv_itemName)
    TextView tvItemName;
    @InjectView(R.id.tv_newPrice)
    TextView tvNewPrice;
    @InjectView(R.id.tv_oldPrice)
    TextView tvOldPrice;
}
