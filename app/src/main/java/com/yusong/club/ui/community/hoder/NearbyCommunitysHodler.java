package com.yusong.club.ui.community.hoder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.community.mvp.entity.Community;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/2/13.
 */
public class NearbyCommunitysHodler extends BaseHolder<Community> {
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_distance)
    TextView tvDistance;
    public NearbyCommunitysHodler(View v, Context mContext) {
        super(v,mContext);
    }

    @Override
    public void setData(List<Community> datas, int position) {
        if (!TextUtils.isEmpty(datas.get(position).getCommunityName())) {
            tvName.setText(datas.get(position).getCommunityName());
        }else {
            tvName.setText("？？？");
        }
        double distance = datas.get(position).getDistance();
        if (distance<=0) {
            tvDistance.setText("定位失败，距离未知");
        }else if (distance < 1000) {
            tvDistance.setText((int)distance+"m");
        }else {
            double showDistance = distance / 1000;
            DecimalFormat df = new DecimalFormat("0.0");
            String format = df.format(showDistance);
            tvDistance.setText(format+"km");
        }


    }
}
