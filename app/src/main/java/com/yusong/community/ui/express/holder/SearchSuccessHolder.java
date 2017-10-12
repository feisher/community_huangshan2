package com.yusong.community.ui.express.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.express.mvp.entity.ScanOrder;

import java.util.List;

import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class SearchSuccessHolder extends BaseHolder<ScanOrder.ShipperInfo> {

    @InjectView(R.id.iv_icon)
    public ImageView mIvIcon;
    @InjectView(R.id.tv_name)
    public TextView mTvName;
    @InjectView(R.id.tv_number)
    public TextView mTvNumber;

    public SearchSuccessHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<ScanOrder.ShipperInfo> datas, int position) {

    }
}
