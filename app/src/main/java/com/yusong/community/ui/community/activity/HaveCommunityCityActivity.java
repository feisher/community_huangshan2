package com.yusong.community.ui.community.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.community.adapter.CitiesAdapter;
import com.yusong.community.ui.community.mvp.entity.HaveCommunityCity;
import com.yusong.community.ui.community.mvp.implView.HaveCommunityCityActivityView;
import com.yusong.community.ui.community.mvp.presenter.impl.HaveCommunityCityActivityPresenterIpml;
import com.yusong.community.ui.community.view.QuickIndexView;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
/**
 * create by feisher on 2017/3/2 13:56
 * Email：458079442@qq.com
 */
public class HaveCommunityCityActivity extends BaseActivity implements HaveCommunityCityActivityView{
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.activity_have_community_city)
    LinearLayout activityHaveCommunityCity;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.quickIndexView)
    QuickIndexView quickIndexView;
    public CitiesAdapter adapter;
    public String cityname;
    public int areaid;
    public HaveCommunityCityActivityPresenterIpml mPresenter;

    protected int layoutId() {
        return R.layout.activity_have_community_city;
    }

    @Override
    public void initView() {
        tvTitle.setText("选择城市");
    }

    @Override
    public void initData() {
        cityname = getIntent().getExtras().getString("CITYNAME");
        areaid = getIntent().getExtras().getInt("AREAID");
        mPresenter = new HaveCommunityCityActivityPresenterIpml(this, this);
        mPresenter.queryHaveCommunityCity();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void initListener() {
        quickIndexView.setOnIndexChangeListener(new QuickIndexView.OnIndexChangeListener() {
            @Override
            public void onIndexChange(String words) {
                if(words.equals("当") || words.equals("热")){
                    LinearLayoutManager llm = (LinearLayoutManager) recyclerView
                            .getLayoutManager();
                    llm.scrollToPositionWithOffset(0, 0);
                    return;
                }
                List<HaveCommunityCity> datas = adapter.getData();
                if(datas!=null && datas.size()>0) {
                    int count = 0;
                    for (int i = 0; i < datas.size(); i++) {
                        HaveCommunityCity datasBean = datas.get(i);
                        if(datasBean.getLetter().equals(words)){
                            LinearLayoutManager llm = (LinearLayoutManager) recyclerView
                                    .getLayoutManager();
                            llm.scrollToPositionWithOffset(count+1, 0);
                            return;
                        }
                        count+=datasBean.getList().size()+1;
                    }
                }
            }
        });

    }

    @Override
    public void setAdapter4ListDataCallback(List<HaveCommunityCity> data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        Gson gson = new Gson();
//        CitiesBean citiesBean = gson.fromJson(Data.citiesJson, CitiesBean.class);
//        adapter = new CitiesAdapter(this,citiesBean.getDatas());
        adapter = new CitiesAdapter(this,data);
        recyclerView.setAdapter(adapter);
    }


    @OnClick({R.id.ll_back, R.id.rl_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_txt:
                break;
        }
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
