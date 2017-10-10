package com.yusong.club.ui.me.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.me.mvp.entity.MoneyList;

import java.util.List;

import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/9 09:51 </li>
 * </ul>
 */
public class MoneyHolder extends BaseHolder<MoneyList> {

    @InjectView(R.id.tv_rechargeName)
    TextView mTvRechargeName;
    @InjectView(R.id.tv_time)
    TextView mTvTime;
    @InjectView(R.id.tv_money)
    TextView mTvMoney;


    public MoneyHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<MoneyList> datas, int position) {

        MoneyList moneyList = datas.get(position);
        int money = moneyList.getMoney();
        if (money > 0){
            mTvMoney.setTextColor(Color.parseColor("#FF5151"));
            mTvMoney.setText("+"+money/100);
        }else {
            mTvMoney.setTextColor(Color.parseColor("#00B10C"));
            mTvMoney.setText(money/100+"");
        }


        mTvRechargeName.setText(moneyList.getBizTypeName());
        mTvTime.setText(moneyList.getCreateTime());

    }
}
