package com.yusong.community.ui.express.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.express.mvp.entity.SaveDetails;
import com.yusong.community.ui.express.mvp.entity.SenderDetails;
import com.yusong.community.ui.express.mvp.implView.ISenderOrderDetailsView;
import com.yusong.community.ui.express.mvp.presenter.impl.ISenderOrderDetailsPresenterImpl;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.RxCountDown;

import org.apache.commons.lang.StringUtils;

import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * <ul>
 * <li>功能说明：提交成功界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class CommitSuccessActivity extends BaseActivity implements ISenderOrderDetailsView {
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_text)
    TextView mTvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout mRlTxt;
    @InjectView(R.id.tv_authCode)
    TextView mTvAuthCode;
    @InjectView(R.id.tv_msg)
    TextView mTvMsg;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    private ISenderOrderDetailsPresenterImpl mPresenter;
    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_commitsuccess;
    }

    @Override
    public void initView() {
        initTitle();
        mPresenter = new ISenderOrderDetailsPresenterImpl(this,this);
    }

    private void initTitle() {
        mTvTitle.setText("提交成功");
        mRlTxt.setVisibility(View.VISIBLE);
        mLlBack.setVisibility(View.GONE);
        mTvText.setText("完成");
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        final int type = intent.getIntExtra(ActivityConstants.ORDER_TYPE,1);
        final String orderid = intent.getStringExtra(ActivityConstants.ORDER_ID);

        String auth_code = intent.getStringExtra(ActivityConstants.AUTH_CODE);
        String msg = intent.getStringExtra(ActivityConstants.MSG);

        if (!StringUtils.isEmpty(auth_code) || !StringUtils.isEmpty(msg)){

            mTvAuthCode.setText(auth_code);
            mTvMsg.setText(msg);

        }else {

            MyApplication.showProgressDialog(this);
            RxCountDown.countdown(4)
                    .subscribe(new Subscriber<Integer>() {
                        @Override
                        public void onCompleted() {
                            MyApplication.closeProgressDialog();
                            if (type == 1){
                                mPresenter.querySenderDetails(orderid);
                            }else {
                                mPresenter.querySaveDetails(orderid);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Integer integer) {
                        }
                    });

        }




    }

    @Override
    public void updateUI(SenderDetails data) {
        mTvMsg.setText("请前往快递柜输入寄件码投件！");
        mTvAuthCode.setText(data.getPutAuthCode());
    }


    @Override
    public void updateUI(SaveDetails data) {
        mTvMsg.setText("请前往快递柜输入存包码完成存包！");
        mTvAuthCode.setText(data.getPutAuthCode());
    }

    @OnClick(R.id.rl_txt)
    public void onClick() {
        finish();
    }




    @Override
    public void setIcon(String code) {

    }



    @Override
    public void showProgressDialog() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
