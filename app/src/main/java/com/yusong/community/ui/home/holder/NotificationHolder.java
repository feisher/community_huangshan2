package com.yusong.community.ui.home.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.model.DataBean;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;

import java.util.List;

import butterknife.InjectView;

/**
 * ***********************************************
 * **                ┏┓   ┏┓                    **
 * **                ┏┛┻━━━┛┻┓                  **
 * **                ┃       ┃                  **
 * **                ┃   ━   ┃                  **
 * **                ┃ ┳┛ ┗┳ ┃                  **
 * **                ┃       ┃                  **
 * **                ┃   ┻   ┃                  **
 * **                ┃       ┃                  **
 * **                ┗━┓   ┏━┛                  **
 * **                  ┃   ┃                    **
 * **                  ┃   ┃                    **
 * **                  ┃   ┗━━━┓                **
 * **                  ┃       ┣┓               **
 * **                  ┃       ┏┛               **
 * **                  ┗┓┓┏━┳┓┏┛                **
 * **                   ┃┫┫ ┃┫┫                 **
 * **                  ┗┻┛ ┗┻┛                  **
 * ***********************************************
 * **               神兽助我 扬我神威              **
 * ***********************************************
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/30 12:44 </li>
 * </ul>
 */
public class NotificationHolder extends BaseHolder<DataBean> {

    @InjectView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @InjectView(R.id.unread_msg_number)
    TextView mUnreadMsgNumber;
    @InjectView(R.id.iv_title)
    TextView mIvTitle;
    @InjectView(R.id.time)
    TextView mTime;
    @InjectView(R.id.tv_content)
    TextView mTvContent;

    public NotificationHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<DataBean> datas, int position) {
        DataBean msgBean = datas.get(position);
        mIvTitle.setText(msgBean.getTitle());
        mTvContent.setText(msgBean.getContent());
        mTime.setText(msgBean.getEventTime());
        if (msgBean.isFlag()){
            mUnreadMsgNumber.setVisibility(View.GONE);
        }else {
            mUnreadMsgNumber.setVisibility(View.VISIBLE);
        }

    }

}
