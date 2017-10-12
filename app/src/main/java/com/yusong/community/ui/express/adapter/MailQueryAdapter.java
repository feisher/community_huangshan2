package com.yusong.community.ui.express.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.express.holder.MailQueryHolder;

import java.util.List;
/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class MailQueryAdapter extends DefaultAdapter<Long> {



    public MailQueryAdapter(List<Long> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<Long> getHolder(View v) {
        return new MailQueryHolder(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_express_mailquery;
    }
}
