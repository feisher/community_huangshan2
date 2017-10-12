package com.yusong.community.ui.express.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.express.mvp.entity.OrderLogistics;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class LogisticsInfoHolder extends BaseHolder<OrderLogistics.LogisticsInfo> {


    @InjectView(R.id.tv_time)
    TextView mTvTime;
    @InjectView(R.id.tv_info)
    TextView mTvInfo;
    @InjectView(R.id.view)
    View mView;
    @InjectView(R.id.iv_orange)
    ImageView mIvOrange;

    List<OrderLogistics.LogisticsInfo> data = new ArrayList<>();
    public LogisticsInfoHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<OrderLogistics.LogisticsInfo> datas, int position) {

        setColor(datas, position);

        int size = datas.size();
        for (int i = size - 1; i >= 0 ; i--) {
            OrderLogistics.LogisticsInfo info = datas.get(i);
            data.add(info);
        }

        OrderLogistics.LogisticsInfo info = data.get(position);
        mTvTime.setText(info.getAcceptTime());
        mTvInfo.setText(info.getAcceptStation());
    }

    private void setColor(List<OrderLogistics.LogisticsInfo> datas, int position) {
        if (position == datas.size()){
            mView.setVisibility(View.GONE);
            mIvOrange.setVisibility(View.GONE);
            mTvInfo.setTextColor(Color.parseColor("#808080"));
            mTvTime.setTextColor(Color.parseColor("#808080"));
        }else if (position == 0){
            mView.setVisibility(View.GONE);
            mIvOrange.setVisibility(View.VISIBLE);
            mTvInfo.setTextColor(Color.parseColor("#ff6e04"));
            mTvTime.setTextColor(Color.parseColor("#ff6e04"));

        }else {
            mView.setVisibility(View.VISIBLE);
            mIvOrange.setVisibility(View.GONE);
            mTvInfo.setTextColor(Color.parseColor("#808080"));
            mTvTime.setTextColor(Color.parseColor("#808080"));
        }
    }

}
