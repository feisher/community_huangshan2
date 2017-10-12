package com.yusong.community.ui.me.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.me.mvp.entity.MoneyList;

import java.util.List;

import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/9 15:19 </li>
 * </ul>
 */
public class PreferenceHolder extends BaseHolder<MoneyList> {

    @InjectView(R.id.tv_money)
    TextView mTvMoney;
    @InjectView(R.id.tv_preference)
    TextView mTvPreference;
    @InjectView(R.id.ll_item)
    LinearLayout mLlItem;
    private boolean other = false;
    public PreferenceHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<MoneyList> datas, int position) {

        MoneyList moneyList = datas.get(position);
        String money = moneyList.getMoney() / 100 + "";
        String gift = moneyList.getGift() / 100 + "";
        mTvMoney.setText(money);
        mTvPreference.setText(String.format("充值%s送%s", money, gift));

    }

    public void setSelectionItem(int mPosition, int position) {

        if (mPosition == position) {
            if (other){
                mLlItem.setBackgroundColor(Color.parseColor("#ffffff"));
                mTvMoney.setTextColor(Color.parseColor("#333333"));
                mTvPreference.setTextColor(Color.parseColor("#888888"));
            }else {
                mLlItem.setBackgroundColor(Color.parseColor("#1dacff"));
                mTvMoney.setTextColor(Color.parseColor("#ffffff"));
                mTvPreference.setTextColor(Color.parseColor("#ffffff"));
            }
        } else {
            mLlItem.setBackgroundColor(Color.parseColor("#ffffff"));
            mTvMoney.setTextColor(Color.parseColor("#333333"));
            mTvPreference.setTextColor(Color.parseColor("#888888"));
        }
    }

    public void setOther(boolean other) {
        this.other = other;
    }
}
