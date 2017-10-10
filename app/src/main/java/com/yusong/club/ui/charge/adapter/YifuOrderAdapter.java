package com.yusong.club.ui.charge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.map.NavigationUtil;
import com.yusong.club.ui.charge.bean.MyOrderBean;
import com.yusong.club.ui.charge.mvp.presenter.impl.IChargeCancelOrderPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr_Peng on 2017/1/4.
 */

public class YifuOrderAdapter extends BaseAdapter {
    private Context context;
    private List<MyOrderBean> yifuList = new ArrayList<MyOrderBean>();
    private IChargeCancelOrderPresenterImpl cancelOrderPresenterImpl;
    private LayoutInflater inflater;

    public YifuOrderAdapter(Context context, List<MyOrderBean> yifuList,IChargeCancelOrderPresenterImpl cancelOrderPresenterImpl) {
        this.context = context;
        this.yifuList = yifuList;
        this.cancelOrderPresenterImpl =cancelOrderPresenterImpl;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return yifuList.size();
    }

    @Override
    public Object getItem(int i) {
        return yifuList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_charge_yifu, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final MyOrderBean myOrderBean = yifuList.get(i);
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
        viewHolder.lijiFukuangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NavigationUtil(context, myOrderBean.getLng(), myOrderBean.getLat());
            }
        });
        viewHolder.cancelOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelOrderPresenterImpl.cancelOrder(myOrderBean.getId());
            }
        });
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
        @InjectView(R.id.cancel_order_btn)
        Button cancelOrderBtn;
        @InjectView(R.id.liji_fukuang_btn)
        Button lijiFukuangBtn;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
