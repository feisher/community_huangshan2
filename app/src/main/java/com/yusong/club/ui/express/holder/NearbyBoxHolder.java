package com.yusong.club.ui.express.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.express.activity.SaveBagsActivity;
import com.yusong.club.ui.express.activity.SendMailActivity;
import com.yusong.club.ui.express.event.Navigation;
import com.yusong.club.ui.express.mvp.entity.NearbyBox;
import com.yusong.club.utils.glide.GlideImgManager;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/2/13 11:30 </li>
 * </ul>
 */
public class NearbyBoxHolder extends BaseHolder<NearbyBox> {


    @InjectView(R.id.tv_address)
    TextView mTvAddress;
    @InjectView(R.id.tv_km)
    TextView mTvKm;
    @InjectView(R.id.tv_employ)
    TextView mTvEmploy;
    @InjectView(R.id.tv_surplus)
    TextView mTvSurplus;
    @InjectView(R.id.iv_icon)
    ImageView mIvIcon;
    @InjectView(R.id.ll_go)
    LinearLayout mLlGo;
    @InjectView(R.id.ll_send)
    LinearLayout mLlSend;
    @InjectView(R.id.ll_save)
    LinearLayout mLlSave;
    @InjectView(R.id.down_layout)
    LinearLayout mDownLayout;
    @InjectView(R.id.ll_lines)
    LinearLayout mLlLines;

    public NearbyBoxHolder(View v, Context context) {
        super(v, context);
    }

    @Override
    public void setData(List<NearbyBox> datas, final int position) {

        final NearbyBox nearbyBox = datas.get(position);
        GlideImgManager.loadImage(mContext, nearbyBox.getImagePath1(), mIvIcon);
        mTvAddress.setText(nearbyBox.getTerminalName());
        mTvEmploy.setText(nearbyBox.getFullCount() + "");
        mTvSurplus.setText(nearbyBox.getEmptyCount() + "");
        double distance = nearbyBox.getDistance();
        if (distance < 1000) {
            mTvKm.setText((int) distance + "m");
        } else {

            DecimalFormat df = new DecimalFormat("0.0");
            String format = df.format(distance);
            mTvKm.setText(format + "km");
        }
        mLlGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new Navigation(nearbyBox.getLat(), nearbyBox.getLng()));
            }
        });
        mLlSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, SendMailActivity.class));
            }
        });
        mLlSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, SaveBagsActivity.class));
            }
        });
    }

}
