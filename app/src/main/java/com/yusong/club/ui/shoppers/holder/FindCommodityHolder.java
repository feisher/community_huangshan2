package com.yusong.club.ui.shoppers.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.shoppers.bean.CommodityBean;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 * @created at 2017-09-18.
 * @describe: null
 */

public class FindCommodityHolder extends BaseHolder<CommodityBean> {
    public FindCommodityHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<CommodityBean> datas, int position) {
        CommodityBean bean = datas.get(position);
        findTv.setText(bean.getItemName() + "-" + bean.getIntroduction());
    }

    @InjectView(R.id.find_tv)
    TextView findTv;
}
