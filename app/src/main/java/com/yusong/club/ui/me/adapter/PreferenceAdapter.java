package com.yusong.club.ui.me.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.me.holder.PreferenceHolder;
import com.yusong.club.ui.me.mvp.entity.MoneyList;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/9 15:19 </li>
 * </ul>
 */
public class PreferenceAdapter extends DefaultAdapter<MoneyList> {

    int mPosition = -1;
    private boolean other = false;


    public PreferenceAdapter(List<MoneyList> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<MoneyList> getHolder(View v) {
        return new PreferenceHolder(v, mContext);
    }

    @Override
    public void onBindViewHolder(BaseHolder<MoneyList> holder, int position) {
        PreferenceHolder preferenceHolder = (PreferenceHolder) holder;
        preferenceHolder.setSelectionItem(mPosition, position);
        preferenceHolder.setOther(other);
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_me_preference;
    }

    public void setSelectionItem(int position) {
        this.mPosition = position;
    }

    public void setOther(boolean other){
        this.other = other;
    }
}