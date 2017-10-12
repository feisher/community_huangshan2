package com.yusong.community.ui.express.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.youth.banner.Banner;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.express.adapter.HomeItemsAdapter;
import com.yusong.community.ui.express.mvp.implView.IBannerView;
import com.yusong.community.ui.express.mvp.presenter.impl.GetKdgBannerPresenterImpl;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.PermissionUtil;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

import static com.yusong.community.utils.PermissionUtil.RESULT_CAPTURE_IMAGE;

/**
 * <ul>
 * <li>功能说明：快递柜首页</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class ExpressHomeActivity extends BaseActivity implements View.OnClickListener, IBannerView {


    int[] items_img = {
            R.mipmap.scan, R.mipmap.get_article, R.mipmap.send_article,
            R.mipmap.sava_article, R.mipmap.query_article, R.mipmap.location_article
    };

    int[] items_name = {
            R.string.saoma_kaixiang, R.string.kuaigui_qujian, R.string.kuaigui_jijian,
            R.string.linshi_jicun, R.string.chaxun_kuaijian, R.string.fujin_guizi
    };

    @InjectView(R.id.iv_img)
    ImageView iv_me;
    @InjectView(R.id.rv_homeItem)
    RecyclerView rv_homeitem;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.ll_img)
    LinearLayout mLlImg;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    @InjectView(R.id.banner)
    Banner mBanner;
    private HomeItemsAdapter mAdapter;

    @Override
    protected void initListener() {
        mLlBack.setOnClickListener(this);
        mLlImg.setOnClickListener(this);

        mAdapter.setOnItemClickListener(new HomeItemsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent mIntent = null;
                switch (position) {
                    case 0:
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                            //扫码开箱
                            PermissionUtil.getCameraAndPhotoPermission(ExpressHomeActivity.this, RESULT_CAPTURE_IMAGE);
                        } else {
                            startActivity(new Intent(ExpressHomeActivity.this, ScanOpenBoxActivity.class));
                        }
                        break;
                    case 1:
                        //快柜收件
                        mIntent = new Intent(ExpressHomeActivity.this, MailOrderActivity.class);
                        mIntent.putExtra(ActivityConstants.ORDER_TYPE, 1);
                        startActivity(mIntent);
                        break;
                    case 2:
                        //快柜寄件
                        startActivity(new Intent(ExpressHomeActivity.this, SendMailActivity.class));
                        break;
                    case 3:
                        //快柜存包
                        startActivity(new Intent(ExpressHomeActivity.this, SaveBagsActivity.class));
                        break;
                    case 4:
                        //查询快件
                        startActivity(new Intent(ExpressHomeActivity.this, MailQueryActivity.class));
                        break;
                    case 5:
                        //附近柜子
                        startActivity(new Intent(ExpressHomeActivity.this, NearbyBoxActivity.class));
                        break;
                }
            }
        });

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        initTitle();
        //设置数据
        setData();
        List<Integer> data = new ArrayList<>();
        data.add(R.mipmap.empty_photo);
        mBanner.setImageLoader(new GlideImgManager());
        //设置图片集合
        mBanner.setImages(data);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    private void initTitle() {
        mTvTitle.setText("快递柜");
        iv_me.setBackgroundResource(R.mipmap.me);
        mLlImg.setVisibility(View.VISIBLE);
        bannerImpl = new GetKdgBannerPresenterImpl(ExpressHomeActivity.this, this);
        bannerImpl.queryKdgBanner();
    }

    private GetKdgBannerPresenterImpl bannerImpl;

    private void setData() {
        //设置数据显示
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mAdapter = new HomeItemsAdapter(this, items_img, items_name);
        rv_homeitem.setAdapter(mAdapter);
        rv_homeitem.setLayoutManager(gridLayoutManager);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_img:
                startActivity(new Intent(ExpressHomeActivity.this, MeActivity.class));
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults, listener);
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode) {
            if (requestCode == 100) {
                startActivity(new Intent(ExpressHomeActivity.this, ScanOpenBoxActivity.class));
            }
        }

        @Override
        public void onFailed(int requestCode) {
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    public void bannerSucced(List<String> list) {
            mBanner.update(list);
    }

    @Override
    public void showProgressDialog() {

    }
}
