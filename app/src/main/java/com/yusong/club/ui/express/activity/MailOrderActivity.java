package com.yusong.club.ui.express.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.express.event.OpenBox;
import com.yusong.club.ui.express.fragment.SimpleOrderFragment;
import com.yusong.club.utils.ActivityConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <ul>
 * <li>功能说明：订单列表界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class MailOrderActivity extends BaseActivity {
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tl_order)
    TabLayout mTlOrder;
    @InjectView(R.id.vp_order)
    ViewPager mVpOrder;
    int type;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    private List<SimpleOrderFragment> mAllOrderNumFragments = new ArrayList<>();


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void openBoxEvent(OpenBox openBox) {
        final String token = openBox.getToken();
        final int type = openBox.getType();
        final String id = openBox.getId();
        openBox(token, type, id);

    }

    private void openBox(String token, int type, String id) {
        Subscription subscription = HttpUtil.getInstance().openBox(token, type, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>(this) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult result) {
                        if (TextUtils.isEmpty(result.message)) {
                            MyApplication.showMessage("开箱成功");
                        } else {
                            MyApplication.showMessage(result.message);
                        }
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_order;
    }

    @Override
    public void initView() {

        initTitle();

        initFragment();

        initViewPager();

        EventBus.getDefault().register(this);
    }

    private void initFragment() {
        if (type == 1) {//收件
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.all, 1));
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.timeout, 1));
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.wait_take, 1));
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.taken, 1));
            mTlOrder.setTabMode(TabLayout.MODE_FIXED);
        } else if (type == 2) {//寄件
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.all, 2));
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.timeout, 2));
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.wait_pay, 2));
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.payed, 2));
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.taken, 2));
            mTlOrder.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else if (type == 3) {//存包
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.all, 3));
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.timeout, 3));
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.wait_put, 3));
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.wait_take, 3));
            mAllOrderNumFragments.add(SimpleOrderFragment.newInstance(ActivityConstants.taken, 3));
            mTlOrder.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    private void initViewPager() {

        //设置adapter
        mVpOrder.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mAllOrderNumFragments.get(position);
            }

            @Override
            public int getCount() {
                return mAllOrderNumFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                String[] title = null;
                if (type == 1) {
                    title = new String[]{"全部", "超时", "待签收", "已签收"};
                }
                if (type == 2) {
                    title = new String[]{"全部", "超时", "待付款", "已付款", "已揽件"};
                }
                if (type == 3) {
                    title = new String[]{"全部", "超时", "待存", "待取", "已取"};
                }
                return title[position];
            }
        });
        //关联页签与viewpager
        mTlOrder.setupWithViewPager(mVpOrder);

    }

    private void initTitle() {

        Intent intent = getIntent();
        type = intent.getIntExtra(ActivityConstants.ORDER_TYPE, 1);
        if (type == 1) {
            mTvTitle.setText("取件订单");

        }
        if (type == 2) {
            mTvTitle.setText("寄件订单");
            mTlOrder.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        if (type == 3) {
            mTvTitle.setText("存包订单");
        }

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.ll_back)
    public void onClick() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initListener() {

    }
}
