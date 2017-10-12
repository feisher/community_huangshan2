package com.yusong.community.ui.shoppers.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.shoppers.bean.PinLunBean;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         created at 2017/3/9 11:38.
 *         评论
 */

public class CommentHolder extends BaseHolder<PinLunBean> {
    @InjectView(R.id.pinlun_time)
    TextView pinlunTime;
    @InjectView(R.id.tell_number)
    TextView tellNumber;
    @InjectView(R.id.pinlun_content)
    TextView pinlunContent;
    public CommentHolder(View itemView, Context context) {
        super(itemView, context);
    }
    @Override
    public void setData(List<PinLunBean> datas, int position) {
        PinLunBean bean = datas.get(position);
        pinlunTime.setText(bean.getCreateTime());
        tellNumber.setText(bean.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
        pinlunContent.setText(bean.getContent());
    }
}
