package com.yusong.club.ui.tenement.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.tenement.entity.TenementBean;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 * @created at 2017-09-04.
 * @describe: null
 */

public class TenementInfoHolder extends BaseHolder<TenementBean> {

    public TenementInfoHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<TenementBean> datas, int position) {
        TenementBean bean = datas.get(position);
        if (bean.getIsSelect() == 1) {
            selectImage.setImageResource(R.mipmap.selected);
        } else {
            selectImage.setImageResource(R.mipmap.not_select);
        }
        priceTv.setText("￥" + bean.getAmount());
        payNameTv.setText(bean.getPayName());
        timeTv.setText(bean.getBeginTime() + "-" + bean.getEndTime());
    }

    @InjectView(R.id.select_image)
    ImageView selectImage;
    @InjectView(R.id.pay_name_tv)
    TextView payNameTv;
    @InjectView(R.id.price_tv)
    TextView priceTv;
    @InjectView(R.id.time_tv)
    TextView timeTv;
}
