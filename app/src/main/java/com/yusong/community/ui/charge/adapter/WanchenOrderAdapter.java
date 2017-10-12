package com.yusong.community.ui.charge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.charge.bean.MyOrderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr_Peng on 2017/1/4.
 */

public class WanchenOrderAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<MyOrderBean> list = new ArrayList<MyOrderBean>();

    public WanchenOrderAdapter(Context context, List<MyOrderBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_charge_yiwancheng, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        MyOrderBean myOrderBean = list.get(i);
        viewHolder.orderNumberTv.setText(myOrderBean.getId());
        viewHolder.orderStatusTv.setText(myOrderBean.getOrderStatusName());
        viewHolder.chargeTypeTv.setText(myOrderBean.getPrice() + "元充");
        viewHolder.orderTimeTv.setText(myOrderBean.getCreateTime());
        viewHolder.yuyueAddressTv.setText(myOrderBean.getAddress());
        int point = myOrderBean.getPointNum();
        if (point > 0) {
            viewHolder.yuyueZhuangdianTv.setText(myOrderBean.getChargerName() + point + "号");
        } else {
            viewHolder.yuyueZhuangdianTv.setText(myOrderBean.getChargerName());
        }
        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.order_number_tv)
        TextView orderNumberTv;
        @InjectView(R.id.order_status_tv)
        TextView orderStatusTv;
        @InjectView(R.id.charge_type_tv)
        TextView chargeTypeTv;
        @InjectView(R.id.order_time_tv)
        TextView orderTimeTv;
        @InjectView(R.id.yuyue_address_tv)
        TextView yuyueAddressTv;
        @InjectView(R.id.yuyue_zhuangdian_tv)
        TextView yuyueZhuangdianTv;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
