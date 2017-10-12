package com.yusong.community.ui.me.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.me.holder.MoneyHolder;
import com.yusong.community.ui.me.mvp.entity.MoneyList;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/9 09:44 </li>
 * </ul>
 */
public class MoneyAdapter extends DefaultAdapter<MoneyList> {



    public MoneyAdapter(List<MoneyList> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<MoneyList> getHolder(View v) {
        return new MoneyHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_me_moneylist;
    }
}
