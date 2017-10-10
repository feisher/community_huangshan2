package com.yusong.club.ui.express.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;

import java.util.List;

import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class MailQueryHolder extends BaseHolder<Long> {

    @InjectView(R.id.tv_number)
    TextView mTvNumber;

    public MailQueryHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<Long> datas, int position) {
        long number = datas.get(position);
        mTvNumber.setText(String.valueOf(number));
    }
}
