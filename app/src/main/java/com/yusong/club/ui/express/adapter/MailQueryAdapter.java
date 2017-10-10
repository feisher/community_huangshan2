package com.yusong.club.ui.express.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.express.holder.MailQueryHolder;

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
