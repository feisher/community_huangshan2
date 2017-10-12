package com.yusong.community.ui.community_service.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.community_service.entity.ServiceBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-05.
 * @describe: null
 */

public class ServiceAdapter extends DefaultAdapter<ServiceBean> {
    @Override
    public BaseHolder<ServiceBean> getHolder(View v) {
        return new ServiceHolder(
                v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dianpu;
    }

    public ServiceAdapter(List<ServiceBean> mDatas, Context context) {
        super(mDatas, context);
    }
}
