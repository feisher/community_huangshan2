package com.yusong.community.ui.im;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.easeui.model.DataBean;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;

import butterknife.InjectView;
import butterknife.OnClick;

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
 * <li>时间：17/3/30 09:58 </li>
 * </ul>
 */
public class NotifyDetailsActivity extends BaseActivity {
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_notice_title)
    TextView mTvNoticeTitle;
    @InjectView(R.id.tv_notice_time)
    TextView mTvNoticeTime;
    @InjectView(R.id.tv_notice_content)
    TextView mTvNoticeContent;

    @Override
    protected int layoutId() {
        return R.layout.activity_notice_detail;
    }

    @Override
    public void initData() {
        mTvTitle.setText("推送消息");
        Intent intent = getIntent();
        DataBean msgBean = (DataBean) intent.getSerializableExtra("msg");
        if (msgBean != null) {
            mTvNoticeContent.setText(msgBean.getContent());
            mTvNoticeTitle.setText(msgBean.getTitle());
            mTvNoticeTime.setText(String.format("发布时间：%s", msgBean.getEventTime()));
        }
    }

    @OnClick(R.id.ll_back)
    public void onClick() {
        finish();
    }

}