package com.yusong.community.ui.shoppers.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.shoppers.bean.QiangGouBean;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         created at 2017/3/4 13:01.
 */

public class QianggouItemHolder extends BaseHolder<QiangGouBean.Categorys> {
    @InjectView(R.id.tv_itemName)
    TextView tvItemName;
    @InjectView(R.id.tv_itemPrice)
    TextView tvItemPrice;
    @InjectView(R.id.im_itemImage)
    ImageView imItemImage;

    public QianggouItemHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<QiangGouBean.Categorys> datas, int position) {
        QiangGouBean.Categorys categorys = datas.get(position);
        tvItemName .setText(categorys.getCategoryName());
        tvItemPrice.setText(categorys.getIntroduction());
        GlideImgManager.loadImage(mContext,categorys.getIconPath(),imItemImage);
    }

}
