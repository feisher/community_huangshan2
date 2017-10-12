package com.yusong.community.ui.school.school.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.base.BaseWebViewActivity;
import com.yusong.community.ui.school.mvp.entity.Notice;
import com.yusong.community.ui.school.mvp.entity.SchoolIntro;
import com.yusong.community.ui.school.mvp.implView.IFirstFragmentView;
import com.yusong.community.ui.school.mvp.presenter.impl.FirstFragmentPresenterImpl;
import com.yusong.community.ui.school.school.activity.AddressActivity;
import com.yusong.community.ui.school.school.activity.AssessorActivity;
import com.yusong.community.ui.school.school.activity.NoticeActivity;
import com.yusong.community.ui.school.school.activity.NoticeDetailActivity;
import com.yusong.community.ui.school.school.activity.SchoolActivity;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * 学校端首页
 */
public class FirstFragment extends BaseFragment implements IFirstFragmentView, View.OnClickListener {


    @InjectView(R.id.linear_intro)
    LinearLayout linearIntro;
    @InjectView(R.id.linear_notice)
    LinearLayout linearNotice;
    @InjectView(R.id.linear_address)
    LinearLayout linearAddress;
    @InjectView(R.id.linear_assessor)
    LinearLayout linearAssessor;
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.banner)
    Banner mBanner;
    public Context mContext;
    public FirstFragmentPresenterImpl mPresenter;
    @InjectView(R.id.marqueeView)
    MarqueeView marqueeView;
    public String schoolDescriptionUrl;
    public String schoolName;
    public String schoolId;
    public List<Notice> mNoticeData = new ArrayList<>();



    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_school_first, null);
    }

    @Override
    public void initData() {
        mContext = getContext();
        SchoolActivity activity = (SchoolActivity) getActivity();
        adapterApi();
        schoolName = activity.getIntent().getStringExtra("SchoolName");
        schoolId = activity.getIntent().getStringExtra("SchoolId");
        if (!TextUtils.isEmpty(schoolName)) {
            tvTitle.setText(schoolName);
        }
        mPresenter = new FirstFragmentPresenterImpl(this, mContext);
        mPresenter.queryBannerList(CacheUtils.getToken(mContext));
        mPresenter.queryPublicNoticeList(CacheUtils.getToken(mContext),0,15);
        mPresenter.querySchoolIntro(CacheUtils.getToken(mContext));
    }

    /**
     * 针对4.4以下系统沉浸式状态栏
     */
    private void adapterApi() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }
    }

    @Override
    public void initListener() {
        linearIntro.setOnClickListener(this);
        linearNotice.setOnClickListener(this);
        linearAddress.setOnClickListener(this);
        linearAssessor.setOnClickListener(this);
        linearBack.setOnClickListener(this);
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Intent mIntent = new Intent(mContext, NoticeDetailActivity.class);
                mIntent.putExtra("noticeType",mNoticeData.get(position).getNoticeType());
                mIntent.putExtra("noticeTitle",mNoticeData.get(position).getTitle());
                mIntent.putExtra("noticeContent",mNoticeData.get(position).getContent());
                mIntent.putExtra("noticeCreateTime",mNoticeData.get(position).getCreateTime());
                mIntent.putExtra("noticeId",mNoticeData.get(position).getId());
                mContext.startActivity(mIntent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent mIntent;
        switch (v.getId()) {
            case R.id.linear_intro:
//                mIntent = new Intent(getActivity(), IntroActivity.class);
//                startActivity(mIntent);
                if (!TextUtils.isEmpty(schoolDescriptionUrl)) {
                    mIntent = new Intent(getActivity(), BaseWebViewActivity.class);
                    mIntent.putExtra("title","学校简介");
                    mIntent.putExtra("url",schoolDescriptionUrl);
//                    mIntent.putExtra("url","https://www.baidu.com/");
                    startActivity(mIntent);
                }else {
                    MyApplication.showMessage("学校简介数据为空");
                }
                break;
            case R.id.linear_notice:
                mIntent = new Intent(getActivity(), NoticeActivity.class);
                mIntent.putExtra("SchoolId",schoolId);
                mIntent.putExtra("SchoolName",schoolName);
                mIntent.putExtra("roleType",1+"");
                startActivity(mIntent);
                break;
            case R.id.linear_address:
                mIntent = new Intent(getActivity(), AddressActivity.class);
                mIntent.putExtra("SchoolId",schoolId);
                mIntent.putExtra("SchoolName",schoolName);
                startActivity(mIntent);
                break;
            case R.id.linear_assessor:
                mIntent = new Intent(getActivity(), AssessorActivity.class);
                startActivity(mIntent);
                break;
            case R.id.linear_back:
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
        marqueeView.stopFlipping();
    }

    @Override
    public void setBannerAdapter(List<String> data) {
        mBanner.setImageLoader(new GlideImgManager());
        mBanner.setImages(data);
        mBanner.start();
    }

    @Override
    public void noticeDataCallback(List<Notice> data) {
        mNoticeData.clear();
        mNoticeData.addAll(data);
        List<String> noticeInfo = new ArrayList<>();
        for (Notice mNotice:data) {
            if (!TextUtils.isEmpty(mNotice.getTitle())) {
                noticeInfo.add(mNotice.getTitle());
            }
        }
//        noticeInfo.add("我是一条本地添加公告数据");
//        noticeInfo.add("我也是一条本地添加公告数据(●'◡'●)");。
        marqueeView.startWithList(noticeInfo);

    }

    @Override
    public void schoolIntroCallback(SchoolIntro data) {
        schoolDescriptionUrl = data.getSchoolDescriptionUrl();
    }


    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(getActivity());
    }
}
