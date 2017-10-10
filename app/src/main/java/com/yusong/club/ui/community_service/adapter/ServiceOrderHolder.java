package com.yusong.club.ui.community_service.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.community_service.entity.ServiceOrderBean;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         created at 2017/3/14 13:11.
 */

public class ServiceOrderHolder extends BaseHolder<ServiceOrderBean> {

    @InjectView(R.id.dian_pu_name_tv)
    TextView dianPuNameTv;
    @InjectView(R.id.order_stutas_tv)
    TextView orderStutasTv;
    @InjectView(R.id.commodity_image)
    ImageView commodityImage;
    @InjectView(R.id.commodity_details_tv)
    TextView commodityDetailsTv;
    @InjectView(R.id.commodity_price)
    TextView commodityPrice;
    @InjectView(R.id.commodity_num)
    TextView commodityNum;

    public ServiceOrderHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<ServiceOrderBean> datas, int position) {
        ServiceOrderBean bean = datas.get(position);
        dianPuNameTv.setText(bean.getShopName());
        if (bean.getOrderStatus() == 1) {
            orderStutasTv.setTextColor(Color.parseColor("#FF3904"));
        } else {
            orderStutasTv.setTextColor(Color.parseColor("#888888"));
        }
        orderStutasTv.setText(bean.getOrderStatusName());
        if (bean.getItemImageList().size() > 0) {
            GlideImgManager.loadImage(mContext, bean.getItemImageList().get(0), commodityImage);
        } else {
            GlideImgManager.loadImage(mContext, "", commodityImage);
        }
        commodityDetailsTv.setText(bean.getItemName());
        commodityPrice.setText("￥ " + bean.getPrice());
    }
}
