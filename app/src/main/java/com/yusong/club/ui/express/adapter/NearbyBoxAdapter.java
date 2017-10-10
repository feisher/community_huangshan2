package com.yusong.club.ui.express.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.express.holder.NearbyBoxHolder;
import com.yusong.club.ui.express.mvp.entity.NearbyBox;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/2/13 11:29 </li>
 * </ul>
 */
public class NearbyBoxAdapter extends DefaultAdapter<NearbyBox> {

    public NearbyBoxAdapter(List<NearbyBox> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<NearbyBox> getHolder(View v) {
        return new NearbyBoxHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_express_nearby_kdg;
    }


}
