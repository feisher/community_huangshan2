package com.yusong.community.ui.express.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.me.mvp.entity.UserInfo;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.glide.GlideImgManager;

import org.apache.commons.lang.StringUtils;

import butterknife.InjectView;
import butterknife.OnClick;
/**
 * <ul>
 * <li>功能说明：个人信息界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class MeActivity extends BaseActivity {
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.iv_head)
    ImageView mIvHead;
    @InjectView(R.id.tv_name)
    TextView mTvName;
    @InjectView(R.id.rl_senderOrder)
    RelativeLayout mRlSenderOrder;
    @InjectView(R.id.rl_getOrder)
    RelativeLayout mRlGetOrder;
    @InjectView(R.id.rl_savaOrder)
    RelativeLayout mRlSavaOrder;
    @InjectView(R.id.rl_address)
    RelativeLayout mRlAddress;
    @InjectView(R.id.rl_express)
    RelativeLayout mRlExpress;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;

    String url = "http://att2.citysbs.com/taizhou/2011/08/27/101937_kuumaaio_40ee6a85b8df443965c4ca5e6f5d80fa.jpg";
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;


    @Override
    protected int layoutId() {
        return R.layout.activity_express_me;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        UserInfo info = CacheUtils.getUserInfo(this);
        mTvTitle.setVisibility(View.GONE);
        //加载网络图片（圆形）
        String portrait = info.getPortrait();
        String nickname = info.getNickname();
        if (!StringUtils.isEmpty(portrait)){
            GlideImgManager.loadCircleImage(this, portrait, mIvHead);
        }else {
            GlideImgManager.loadCircleImage(this, R.mipmap.default_user_icon, mIvHead);
        }
        mTvName.setText(nickname);

    }


    @OnClick({R.id.ll_back, R.id.rl_senderOrder, R.id.rl_getOrder, R.id.rl_savaOrder, R.id.rl_address, R.id.rl_express})
    public void onClick(View view) {

        Intent mIntent = null;
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_senderOrder://寄件订单
                mIntent = new Intent(MeActivity.this, MailOrderActivity.class);
                mIntent.putExtra(ActivityConstants.ORDER_TYPE, 2);
                startActivity(mIntent);

                break;
            case R.id.rl_getOrder://收件订单
                mIntent = new Intent(MeActivity.this, MailOrderActivity.class);
                mIntent.putExtra(ActivityConstants.ORDER_TYPE, 1);
                startActivity(mIntent);
                break;
            case R.id.rl_savaOrder://存包订单
                mIntent = new Intent(MeActivity.this, MailOrderActivity.class);
                mIntent.putExtra(ActivityConstants.ORDER_TYPE, 3);
                startActivity(mIntent);
                break;
            case R.id.rl_address://地址簿

                mIntent = new Intent(MeActivity.this, AddressActivity.class);
                mIntent.putExtra(ActivityConstants.INFO_FILL, 3);
                startActivity(mIntent);
                break;
            case R.id.rl_express://收藏的快柜

                break;
        }
    }

}
