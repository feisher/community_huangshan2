package com.yusong.club.ui.charge.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.map.NavigationUtil;
import com.yusong.club.ui.charge.activity.YuYueDetailsActivity;
import com.yusong.club.ui.charge.bean.NearbyBean;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr_Peng on 2016/12/27.
 */

public class YuYueAdapter extends BaseAdapter {
    private Context context;
    private List<NearbyBean> nearbyBeanList = new ArrayList<NearbyBean>();
    private LayoutInflater inflater;
    private Activity mActivity;

    public YuYueAdapter(Context context, Activity mActivity, List<NearbyBean> nearbyBeanList) {
        this.context = context;
        this.nearbyBeanList = nearbyBeanList;
        this.mActivity = mActivity;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return nearbyBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return nearbyBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_charge_yuyue, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final NearbyBean nearbyBean = nearbyBeanList.get(i);
        viewHolder.addressTv.setText(nearbyBean.getChargerName());
        viewHolder.juliTv.setText(nearbyBean.getDistance() + "km");
        viewHolder.shiyongzhongTv.setText(String.valueOf(nearbyBean.getBusyPoint()));
        viewHolder.kongxianTv.setText(String.valueOf(nearbyBean.getFreePoint()));
        if (nearbyBean.getImageList().size() > 0) {
            GlideImgManager.loadImage(context, nearbyBean.getImageList().get(0), viewHolder.chongdianImage);
        } else {
            GlideImgManager.loadImage(context, "", viewHolder.chongdianImage);
        }

        viewHolder.hereToLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NavigationUtil(context, nearbyBean.getLng(), nearbyBean.getLat());
            }
        });
        viewHolder.yuyueLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, YuYueDetailsActivity.class);
                intent.putExtra("chargeId", nearbyBean.getId());
                intent.putExtra("orderType", 1);
                context.startActivity(intent);
            }
        });
        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.address_tv)
        TextView addressTv;
        @InjectView(R.id.juli_tv)
        TextView juliTv;
        @InjectView(R.id.shiyongzhong_tv)
        TextView shiyongzhongTv;
        @InjectView(R.id.kongxian_tv)
        TextView kongxianTv;
        @InjectView(R.id.chongdian_image)
        ImageView chongdianImage;
        @InjectView(R.id.here_to_layout)
        LinearLayout hereToLayout;
        @InjectView(R.id.yuyue_layout)
        LinearLayout yuyueLayout;
        @InjectView(R.id.down_layout)
        LinearLayout downLayout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
