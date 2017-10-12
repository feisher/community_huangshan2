package com.yusong.community.ui.shoppers.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.community_service.activity.ServiceDetailsActivity;
import com.yusong.community.ui.shoppers.adapter.FindCommodityAdapter;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplFindView;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplFindCommodityPresenterImpl;
import com.yusong.community.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-09-18.
 * @describe: null
 */

public class FindCommodityActivity extends BaseActivity implements ImplFindView {
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
    @InjectView(R.id.find_layout)
    RelativeLayout findLayout;
    @InjectView(R.id.left_radio)
    RadioButton leftRadio;
    @InjectView(R.id.center_radio)
    RadioButton centerRadio;
    @InjectView(R.id.right_radio)
    RadioButton rightRadio;
    @InjectView(R.id.radio_group)
    RadioGroup radioGroup;
    @InjectView(R.id.left_radio_address)
    RadioButton leftRadioAddress;
    @InjectView(R.id.right_radio_address)
    RadioButton rightRadioAddress;
    @InjectView(R.id.radio_group_address)
    RadioGroup radioGroupAddress;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.commodity_find_et)
    EditText commodityFindEt;
    @InjectView(R.id.find_result_list)
    RecyclerView findResultList;

    @OnClick(R.id.ll_back)
    void blackClick() {
        this.finish();
    }

    private int findType = 0;
    private ImplFindCommodityPresenterImpl mPresenter;
    private List<CommodityBean> beanList = new ArrayList<CommodityBean>();
    private FindCommodityAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.activity_find_commodity;
    }

    @Override
    public void initView() {
        findType = getIntent().getIntExtra("findType", 0);
        tvTitle.setText("搜索");
        adapter = new FindCommodityAdapter(beanList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        findResultList.setLayoutManager(linearLayoutManager);
        findResultList.setAdapter(adapter);
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {

                if (findType == 3) {
                    Intent intent = new Intent(FindCommodityActivity.this, ServiceDetailsActivity.class);
                    intent.putExtra("itemId", beanList.get(position).getId());
                    startActivity(intent);
                    FindCommodityActivity.this.finish();
                    return;
                }
                Intent intent = new Intent(FindCommodityActivity.this, CommodityDetailsActivity.class);
                intent.putExtra("itemId", beanList.get(position).getId());
                if (findType == 1) {
                    intent.putExtra("isQG", 1);
                } else {
                    intent.putExtra("isQG", 3);
                }
                startActivity(intent);
                FindCommodityActivity.this.finish();
            }
        });
        findResultList.addItemDecoration(new SpaceItemDecoration(2, 8));
        mPresenter = new ImplFindCommodityPresenterImpl(this, this);
        commodityFindEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    findResultList.setVisibility(View.GONE);
                } else {
                    findResultList.setVisibility(View.VISIBLE);
                    if (findType == 1) {
                        mPresenter.findCommodity(charSequence.toString());
                    } else if (findType == 3) {
                        mPresenter.findService(charSequence.toString());
                    } else {
                        mPresenter.findSMCommodity(charSequence.toString());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void findCommoditySucced(List<CommodityBean> data) {

        if (beanList.size() > 0) {
            beanList.clear();
        }
        beanList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void findCommodityError() {

    }

    @Override
    public void findSMCommoditySucced(List<CommodityBean> data) {
        if (beanList.size() > 0) {
            beanList.clear();
        }
        beanList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void findSMCommodityError() {

    }

    @Override
    public void findServiceSucced(List<CommodityBean> data) {
        if (beanList.size() > 0) {
            beanList.clear();
        }
        beanList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void findServiceError() {

    }
}
