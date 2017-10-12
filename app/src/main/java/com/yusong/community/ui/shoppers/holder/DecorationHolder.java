package com.yusong.community.ui.shoppers.holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.shoppers.activity.CommodityDetailsActivity;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         created at 2017/3/16 11:00.
 */
public class DecorationHolder extends BaseHolder<CommodityBean> {
    public DecorationHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(final List<CommodityBean> datas, final int position) {
        CommodityBean commodityBean = datas.get(position);
        if (commodityBean.getImageList().length > 0) {
            GlideImgManager.loadImage(mContext, commodityBean.getImageList()[0], commundityImage);
        } else {
            GlideImgManager.loadImage(mContext, "", commundityImage);
        }
        tvNewPrice.setText(String.valueOf(commodityBean.getPrice()));
        tvOldPrice.setText(String.valueOf(commodityBean.getShowPrice()));
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvContent.setText(commodityBean.getItemName());
        tvCount.setText("已售出" + commodityBean.getSoldAmount() + "单");
        tvTip.setText(datas.get(position).getIntroduction());
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommodityDetailsActivity.class);
                intent.putExtra("itemId", datas.get(position).getId());
                intent.putExtra("isQG", 1);
                mContext.startActivity(intent);
            }
        });
    }

    @InjectView(R.id.commundity_image)
    ImageView commundityImage;
    @InjectView(R.id.tv_newPrice)
    TextView tvNewPrice;
    @InjectView(R.id.tv_oldPrice)
    TextView tvOldPrice;
    @InjectView(R.id.tv_tip)
    TextView tvTip;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.tv_count)
    TextView tvCount;
    @InjectView(R.id.btn_buy)
    Button btnBuy;
}
