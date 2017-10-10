package com.yusong.club.ui.charge.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.charge.bean.SancContentBean;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Mr_Peng on 2017/2/22.
 */

public class YuYueOrderHodler extends BaseHolder<SancContentBean> {

    @InjectView(R.id.tv_charge_address)
    TextView tvChargeAddress;
    @InjectView(R.id.tv_charge_order_create_time)
    TextView tvChargeOrderCreateTime;

    public YuYueOrderHodler(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<SancContentBean> datas, int position) {
        tvChargeAddress.setText(datas.get(position).getAddress());
        tvChargeOrderCreateTime.setText(datas.get(position).getCreateTime());
    }
}
