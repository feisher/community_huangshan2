package com.yusong.club.ui.tenement.adapter;

import android.content.Context;
import android.view.View;
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

public class TenementHistoryHolder extends BaseHolder<TenementBean> {
    public TenementHistoryHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<TenementBean> datas, int position) {
        TenementBean bean = datas.get(position);
        payTypeTv.setText(bean.getPayName());
        timeTv.setText(bean.getBeginTime() + "至" + bean.getEndTime());
        priceTv.setText("￥" + bean.getAmount());
        payTimeTV.setText(bean.getPayTime());
    }

    @InjectView(R.id.time_tv)
    TextView timeTv;
    @InjectView(R.id.price_tv)
    TextView priceTv;
    @InjectView(R.id.pay_time_tv)
    TextView payTimeTV;
    @InjectView(R.id.pay_type_tv)
    TextView payTypeTv;
}
