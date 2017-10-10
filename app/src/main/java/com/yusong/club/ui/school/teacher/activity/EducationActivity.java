package com.yusong.club.ui.school.teacher.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.school.mvp.implView.IEducationActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.IEducationActivityPresenterImpl;
import com.yusong.club.ui.school.teacher.bean.StudyVideo;
import com.yusong.club.ui.school.teacher.fragment.CorrelationFragment;
import com.yusong.club.ui.school.teacher.fragment.IntroduceFragment;
import com.yusong.club.ui.video.VideoPlayDetailActivity;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.glide.GlideImgManager;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 教育课堂
 */
public class EducationActivity extends BaseActivity implements View.OnClickListener, IEducationActivityView {
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
    @InjectView(R.id.rating_bar)
    RatingBar ratingBar;
    @InjectView(R.id.tv_one)
    TextView tvOne;
    @InjectView(R.id.im_one)
    ImageView imOne;
    @InjectView(R.id.linear_one)
    LinearLayout linearOne;
    @InjectView(R.id.tv_two)
    TextView tvTwo;
    @InjectView(R.id.im_two)
    ImageView imTwo;
    @InjectView(R.id.linear_two)
    LinearLayout linearTwo;
    @InjectView(R.id.frame_video)
    FrameLayout frameVideo;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.btn_play)
    Button btnPlay;
    @InjectView(R.id.btnA_delete)
    Button btnADelete;
    @InjectView(R.id.iv_title)
    ImageView ivTitle;
    @InjectView(R.id.tv_teacher)
    TextView tvTeacher;
    @InjectView(R.id.tv_teacher_job)
    TextView tvTeacherJob;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    @InjectView(R.id.tv_titles)
    TextView tvTitles;
    @InjectView(R.id.lin_title)
    LinearLayout linTitle;
    @InjectView(R.id.lin_choose)
    LinearLayout linChoose;
    private Context mContext;
    private IEducationActivityPresenterImpl mPresenter;
    private String videoUrl = "";
    private StudyVideo studyVideo = null;
    private List<BaseFragment> mFragments;
    private Fragment CURRENT_FRAGMENT;
    private FragmentTransaction ft;

    @Override
    protected void initListener() {
        linearBack.setOnClickListener(this);
        linearOne.setOnClickListener(this);
        linearTwo.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnADelete.setOnClickListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher_education;
    }

    @Override
    public void initView() {
        mContext = EducationActivity.this;
        mPresenter = new IEducationActivityPresenterImpl(this, this);
        mPresenter.studyVideoList(CacheUtils.getToken(mContext), 0, 10);
        tvTitle.setText("教育课堂");
        tvBack.setVisibility(View.GONE);
        imMsg.setImageResource(R.mipmap.add);
        imMsg.setOnClickListener(this);
        btnADelete.setOnClickListener(this);
        btnADelete.setVisibility(View.GONE);
        LayerDrawable layerDrawable = (LayerDrawable) ratingBar.getProgressDrawable();
        layerDrawable.getDrawable(2).setColorFilter(0xFFFF9001, PorterDuff.Mode.SRC_ATOP);
        imMsg.setVisibility(View.GONE);
    }

    public void showFragment(int index) {
        ft = getSupportFragmentManager().beginTransaction();
        //判断当前的fragment是否为空
        if (null != CURRENT_FRAGMENT) {
            ft.hide(CURRENT_FRAGMENT);
        }
        //用缓存获取当前的fragment
        Fragment fragment = getSupportFragmentManager()
                .findFragmentByTag(mFragments.get(index).getClass().getName());
        if (fragment == null) {
            fragment = mFragments.get(index);
        }
        //为每一个fragment做标记
        CURRENT_FRAGMENT = fragment;
        //设置当前的fragment
        if (!fragment.isAdded()) {
            ft.add(R.id.frame_video, fragment, fragment.getClass().getName());
        } else {
            ft.show(fragment);
        }
        //提交
        ft.commit();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_one:
                tvOne.setTextColor(Color.BLUE);
                imOne.setVisibility(View.VISIBLE);
                imOne.setImageResource(R.mipmap.line_blue);
                tvTwo.setTextColor(Color.BLACK);
                imTwo.setVisibility(View.GONE);
                showFragment(0);
                break;
            case R.id.linear_two:
                tvOne.setTextColor(Color.BLACK);
                imOne.setVisibility(View.GONE);
                tvTwo.setTextColor(Color.BLUE);
                imTwo.setVisibility(View.VISIBLE);
                imTwo.setImageResource(R.mipmap.line_blue);
                showFragment(1);
                break;
            case R.id.linear_back:
                finish();
                break;
            case R.id.btn_play:
                Intent intent = new Intent(EducationActivity.this, VideoPlayDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("videoUrl", videoUrl);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.im_msg:
                Intent intent1 = new Intent(mContext, UpLoadStudyVideoActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("studyVideo", studyVideo);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
            case R.id.btnA_delete:
                mPresenter.deleteStudyVideo(CacheUtils.getToken(mContext), studyVideo.getId());
                break;
        }
    }


    @Override
    public void deleteVideo(String data) {
        finish();

    }

    @Override
    public void closeRefreshing() {


    }

    @Override
    public void getVideoAlbumList(List<StudyVideo> data) {
        if (data != null && data.size() != 0) {
            linChoose.setVisibility(View.VISIBLE);
            linTitle.setVisibility(View.VISIBLE);
            notDataLayout.setVisibility(View.GONE);
            linTitle.setVisibility(View.VISIBLE);
            linChoose.setVisibility(View.VISIBLE);
            setDataInfo(data);
        } else {
            linChoose.setVisibility(View.GONE);
            linTitle.setVisibility(View.GONE);
            notDataLayout.setVisibility(View.VISIBLE);
        }
    }

    private void initFragments() {
        Bundle bundle = new Bundle();
        if (studyVideo != null) {
            bundle.putSerializable("studyVideo", studyVideo);

        }
        mFragments = new ArrayList<>();
        BaseFragment introduceFragment = new IntroduceFragment();
        introduceFragment.setArguments(bundle);
        mFragments.add(introduceFragment);
        BaseFragment correlationFragment = new CorrelationFragment();
        correlationFragment.setArguments(bundle);
        mFragments.add(correlationFragment);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }

    public void setDataInfo(List<StudyVideo> dataInfo) {
        ratingBar.setNumStars((int) Double.parseDouble(dataInfo.get(0).getScore() + ""));
        GlideImgManager.loadImage(mContext, dataInfo.get(0).getImagePath(), ivTitle);
        if (!StringUtils.isEmpty(dataInfo.get(0).getVideoName())) {
            tvTitles.setText(dataInfo.get(0).getVideoName());
        } else {
            tvTitles.setText("暂无");
        }
        if (!StringUtils.isEmpty(dataInfo.get(0).getTeacherName())) {
            tvTeacher.setText("老师:\t" + dataInfo.get(0).getTeacherName());
        } else {
            tvTeacher.setText("老师:\t");
        }
        if (!StringUtils.isEmpty(dataInfo.get(0).getSubjectName())) {
            tvTeacherJob.setText(dataInfo.get(0).getSubjectName());
        } else {
            tvTeacherJob.setText("");
        }
        videoUrl = dataInfo.get(0).getFilePath();
        studyVideo = dataInfo.get(0);
        initFragments();
        showFragment(0);

    }

}
