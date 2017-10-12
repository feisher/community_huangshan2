package com.yusong.community.ui.community.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.community.hoder.NearbyCommunitysHodler;
import com.yusong.community.ui.community.mvp.entity.Community;

import java.util.List;

/**
 * Created by feisher on 2017/2/13.
 */
public class NearbyCommuitysAdapter extends DefaultAdapter <Community>{


    public NearbyCommuitysAdapter(Context context, List<Community> mCommunityLists) {
        super(mCommunityLists, context);
    }

    @Override
    public BaseHolder<Community> getHolder(View v) {
        return new NearbyCommunitysHodler(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_community_nearby;
    }
}
