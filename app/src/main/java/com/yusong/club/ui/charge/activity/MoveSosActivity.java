package com.yusong.club.ui.charge.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.charge.adapter.SosItemAdapter;
import com.yusong.club.ui.charge.bean.MoveSosBean;
import com.yusong.club.ui.charge.mvp.implView.IChareMoveSosView;
import com.yusong.club.ui.charge.mvp.presenter.impl.IChargeMoveSosPresenterImpl;
import com.yusong.club.ui.charge.view.BaseDialog;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by Mr_Peng on 2016/12/30.
 */

public class MoveSosActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate, IChareMoveSosView {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.find_et)
    EditText findEt;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;

    @Override
    public void refreshView(List<MoveSosBean> data) {
        sosPriceRefresh.endRefreshing();
        sosPriceRefresh.endLoadingMore();
        moveSosList.clear();
        moveSosList.addAll(data);
        sosItemAdapter.notifyDataSetChanged();
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (flag) {
            moveSosPresenter.queryMoveSos(PRICE);
        } else {
            moveSosPresenter.queryMoveSos(STAR);
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (flag) {
            moveSosPresenter.queryMoveSos(PRICE);
        } else {
            moveSosPresenter.queryMoveSos(STAR);
        }
        return true;
    }

    private BaseDialog baseDialog;
    private final String PRICE = "price";
    private final String STAR = "star";
    private boolean flag = true;
    private String tel;

    @InjectView(R.id.price_zuidi)
    TextView priceZuidi;
    @InjectView(R.id.price_zuidi_image)
    ImageView priceZuidiImage;
    @InjectView(R.id.price_layout_but)
    RelativeLayout jiageLayoutBut;
    @InjectView(R.id.pingfen_zuigao)
    TextView pingfenZuigao;
    @InjectView(R.id.pingfen_zuigao_image)
    ImageView pingfenZuigaoImage;
    @InjectView(R.id.pingfen_layout_but)
    RelativeLayout pingfenLayoutBut;
    @InjectView(R.id.sos_list_view)
    ListView sosListView;
    @InjectView(R.id.sos_price_refresh)
    BGARefreshLayout sosPriceRefresh;

    private IChargeMoveSosPresenterImpl moveSosPresenter;
    private List<MoveSosBean> moveSosList = new ArrayList<MoveSosBean>();
    private SosItemAdapter sosItemAdapter;

    @OnItemClick(R.id.sos_list_view)
    void onItemClick(int position) {
        showBaseDialog(position);
    }

    @OnClick({R.id.ll_back, R.id.ll_img, R.id.price_layout_but, R.id.pingfen_layout_but})
    void allClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.ll_img:
                startActivity(new Intent(this, ChargeMyOrderActivity.class));
                break;
            case R.id.price_layout_but:
                if (!flag) {
                    pingfenZuigao.setTextColor(Color.parseColor("#888888"));
                    pingfenZuigaoImage.setImageResource(R.mipmap.pinfen_1);
                    priceZuidi.setTextColor(Color.parseColor("#1DACFF"));
                    priceZuidiImage.setImageResource(R.mipmap.jiage_2);
                    flag = true;
                    moveSosPresenter.queryMoveSos(PRICE);
                }

                break;
            case R.id.pingfen_layout_but:
                if (flag) {
                    priceZuidi.setTextColor(Color.parseColor("#888888"));
                    priceZuidiImage.setImageResource(R.mipmap.jiage_1);
                    pingfenZuigao.setTextColor(Color.parseColor("#1DACFF"));
                    pingfenZuigaoImage.setImageResource(R.mipmap.pinfen_2);
                    flag = false;
                    moveSosPresenter.queryMoveSos(STAR);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_charge_move_sos;
    }

    @Override
    public void initView() {
        tvTitle.setText("移动救援");
        ivImg.setBackgroundResource(R.mipmap.wode);
        llImg.setVisibility(View.VISIBLE);
        initRefreshLayout();
        sosItemAdapter = new SosItemAdapter(this, moveSosList);
        sosListView.setAdapter(sosItemAdapter);
        moveSosPresenter = new IChargeMoveSosPresenterImpl(this, MoveSosActivity.this);
        moveSosPresenter.queryMoveSos(PRICE);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    protected void adaptiveSystemVersions() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        sosPriceRefresh.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        sosPriceRefresh.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    private void showBaseDialog(final int position) {
        baseDialog = new BaseDialog(this);
        tel = moveSosList.get(position).getTel();
        super.phoneNumber = tel;
        baseDialog.setTitle("移动救援");
        baseDialog.setMessage("确定拨打电话:" + tel + "吗?");
        baseDialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
            }
        });
        baseDialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
                callDirectly(tel);
        }
        });
    }
    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
