package com.yusong.club.ui.express.activity;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;

import butterknife.InjectView;


/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class BoxDetailsActivity extends BaseActivity {

    @InjectView(R.id.ll_bg)
    LinearLayout mLlBg;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_boxdetails;
    }

    @Override
    public void initView() {
        mLlBg.getBackground().setAlpha(100);
    }

    @Override
    public void initData() {

    }

}
