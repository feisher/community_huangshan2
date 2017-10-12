package com.yusong.community.ui.express.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.express.adapter.AddressAdapter;
import com.yusong.community.ui.express.mvp.entity.ContactGroup;
import com.yusong.community.ui.express.mvp.implView.IAddressView;
import com.yusong.community.ui.express.mvp.presenter.impl.IAddressPresenterImpl;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.SpaceItemDecoration;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：地址薄界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class AddressActivity extends BaseActivity
        implements View.OnClickListener, IAddressView {
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.ll_img)
    LinearLayout mLlImg;
    @InjectView(R.id.tv_text)
    TextView mTvText;
    @InjectView(R.id.iv_img)
    ImageView mIvImg;
    @InjectView(R.id.rl_address)
    SwipeMenuRecyclerView mRlAddress;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    @InjectView(R.id.left_radio_address)
    RadioButton leftRadioAddress;
    @InjectView(R.id.right_radio_address)
    RadioButton rightRadioAddress;
    @InjectView(R.id.radio_group_address)
    RadioGroup radioGroupAddress;

    private Intent mIntent;
    private int mInfo_fill;
    private AddressAdapter mAdapter;
    private IAddressPresenterImpl mPresenter;
    private List<ContactGroup> data;
    private SpaceItemDecoration mDecor;
    private int queryType = 0;

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_back://返回
                SendMailActivity.flag = true;
                finish();
                break;
            case R.id.ll_img:
                mIntent = new Intent(AddressActivity.this, FillInfoActivity.class);
                mIntent.putExtra(ActivityConstants.INFO_FILL, 3);
                startActivity(mIntent);
                break;
            case R.id.left_radio_address:
                queryType = 1;
                mPresenter.queryContact(queryType);
                break;
            case R.id.right_radio_address:
                queryType = 2;
                mPresenter.queryContact(queryType);
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            SendMailActivity.flag = true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 刷新列表
     */
    @Override
    public void notifyAdapter() {
        mPresenter.queryContact(queryType);
    }

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            if (menuPosition == 0) {// 删除按钮被点击。
                mPresenter.deleteContact(adapterPosition);
            }
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.activity_express_address;
    }

    @Override
    public void initView() {
        mPresenter = new IAddressPresenterImpl(this, this);
        mIntent = getIntent();
        if (mIntent != null) {
            //获取寄件or收件
//            mInfo_fill = mIntent.getIntExtra(ActivityConstants.INFO_FILL, 1);
            mInfo_fill = mIntent.getIntExtra(ActivityConstants.INFO_FILL, 0);
        }
        if (mInfo_fill == ActivityConstants.INFO_SENDER_RESPONS) {
            //如果是个人信息进来展示页面显示区分收件人寄件人
            radioGroupAddress.setVisibility(View.VISIBLE);
            mTvTitle.setVisibility(View.GONE);
            leftRadioAddress.setChecked(true);
        }
        if (mInfo_fill == 1 || mInfo_fill == 3) {
            queryType = 1;
        } else if(mInfo_fill==10||mInfo_fill==11||mInfo_fill==4){
            queryType = 0;
        }else{
            queryType = 2;
        }
        initTitle();
    }

    @Override
    public void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryContact(queryType);//查询联系人 获取数据
    }

    /**
     * 设置显示适配器
     *
     * @param data
     */
    @Override
    public void setContactAdapter(List<ContactGroup> data) {

        if (data != null && data.size() >= 0) {
            //设置显示
            this.data = data;
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
            mAdapter = new AddressAdapter(data, this);
            mRlAddress.setAdapter(mAdapter);
            mRlAddress.setLayoutManager(mLinearLayoutManager);
            mRlAddress.setItemAnimator(new DefaultItemAnimator());
            if (mDecor == null) {
                mDecor = new SpaceItemDecoration(22, 0);
                mRlAddress.addItemDecoration(mDecor);
            }
            mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, Object data, int position) {
                    mPresenter.onItemClick((ContactGroup) data, mInfo_fill);
                }
            });
            // 设置菜单创建器。
            mRlAddress.setSwipeMenuCreator(swipeMenuCreator);
            // 设置菜单Item点击监听。
            mRlAddress.setSwipeMenuItemClickListener(menuItemClickListener);

            mAdapter.setOnShowDiaLogLisener(new AddressAdapter.ShowDiaLogLisener() {
                @Override
                public void setDftDiaLog(final ContactGroup contact, final int type) {
                    final BaseDialog mBaseDialog = new BaseDialog(AddressActivity.this);

                    mBaseDialog.setTitle("提示");
                    mBaseDialog.setMessage("确定设为默认联系人吗？");
                    mBaseDialog.setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPresenter.setDftContact(contact.getId(), type);


                            mBaseDialog.dismiss();
                        }
                    });
                    mBaseDialog.setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mBaseDialog.dismiss();
                        }
                    });


                }
            });

        }
    }

    private void initTitle() {
        mTvTitle.setText("地址簿");
        mLlImg.setVisibility(View.VISIBLE);
        mIvImg.setImageResource(R.mipmap.add);
    }


    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(AddressActivity.this)
                    .setBackgroundDrawable(R.drawable.selector_red)
                    .setText("删除") // 文字。
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(16) // 文字大小。
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }


    @Override
    public void close() {
        finish();
    }

    @Override
    public void result(int respons, Intent intent) {
        setResult(respons, intent);
    }

    @Override
    protected void adaptiveSystemVersions() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            mIvAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            mIvAdaptiveDownApi18.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initListener() {
        mLlBack.setOnClickListener(this);
        mLlImg.setOnClickListener(this);
        leftRadioAddress.setOnClickListener(this);
        rightRadioAddress.setOnClickListener(this);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
