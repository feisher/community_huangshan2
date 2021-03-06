package com.yusong.community.ui.school.parent.fragment;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunfusheng.marqueeview.MarqueeView;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.base.BaseWebViewActivity;
import com.yusong.community.ui.school.mvp.entity.Notice;
import com.yusong.community.ui.school.mvp.entity.SchoolIntro;
import com.yusong.community.ui.school.mvp.implView.IParentHomeFragmentView;
import com.yusong.community.ui.school.mvp.presenter.impl.ParentHomeFragmentPresenterImpl;
import com.yusong.community.ui.school.school.activity.NoticeActivity;
import com.yusong.community.ui.school.school.activity.NoticeDetailActivity;
import com.yusong.community.ui.school.teacher.activity.AddressBookActivity;
import com.yusong.community.ui.school.teacher.activity.ClassPhotoActivity;
import com.yusong.community.ui.school.teacher.activity.EducationActivity;
import com.yusong.community.ui.school.teacher.activity.EvaluateActivity;
import com.yusong.community.ui.school.teacher.activity.HomeworkActivity;
import com.yusong.community.ui.school.teacher.activity.ParentLeaveActivity;
import com.yusong.community.ui.school.teacher.activity.ScheduleActivity;
import com.yusong.community.ui.school.teacher.adapter.HomeAdapter;
import com.yusong.community.ui.school.teacher.bean.Home;
import com.yusong.community.ui.school.teacher.view.NoScrollWebView;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * create by feisher on 2017/3/1 16:47
 * Email：458079442@qq.com
 */
public class ParentHomeFragment extends BaseFragment implements IParentHomeFragmentView, AdapterView.OnItemClickListener {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.marqueeView)
    MarqueeView marqueeView;
    private HomeAdapter adapter;
    private List<Home> data;
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
    @InjectView(R.id.gv_teacher)
    GridView gvTeacher;
    @InjectView(R.id.webView)
    NoScrollWebView webView;
    private String url = "";
    public ParentHomeFragmentPresenterImpl mPresenter;
    private String SchoolName;
    private String SchoolId;
    private String schoolDescriptionUrl;
    public List<Notice> mNoticeData = new ArrayList<>();

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_parent_home, null);
    }
    private void initWebView() {
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.cancel(); // Android默认的处理方式
                handler.proceed();  // 接受所有网站的证书
                //handleMessage(Message msg); // 进行其他处理
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("gb2312");
        webView.loadUrl(url);

    }
    @Override
    public void initData() {
        url = Constants.getNetUrl() + "/api/v1/school/timetable/h5_detail?token=" + CacheUtils.getToken(mContext);
        tvBack.setVisibility(View.GONE);
        imMsg.setVisibility(View.GONE);
        if (getArguments() != null) {
            SchoolName = getArguments().getString("SchoolName", "");
            SchoolId = getArguments().getString("SchoolId", "");
        }
        tvTitle.setText(SchoolName);
        data = new ArrayList<>();
        initWebView();
        data.add(new Home(R.mipmap.teacher1, "学校简介"));
//        data.add(new Home(R.mipmap.teacher2, "校训录"));
        data.add(new Home(R.mipmap.teacher3, "公告"));
//        data.add(new Home(R.mipmap.teacher4, "分享"));
        data.add(new Home(R.mipmap.teacher5, "作业发放"));
        data.add(new Home(R.mipmap.teacher6, "教育课程"));
        data.add(new Home(R.mipmap.teacher7, "班级相册"));
        data.add(new Home(R.mipmap.teacher8, "班级视频"));
        data.add(new Home(R.mipmap.teacher9, "班课表"));
        data.add(new Home(R.mipmap.teacher10, "学生评价"));
        data.add(new Home(R.mipmap.teacher12, "小孩请假"));
        data.add(new Home(R.mipmap.teacher13, "班训录"));
        data.add(new Home(0, null));
        data.add(new Home(0, null));
        initGridView();
        mPresenter = new ParentHomeFragmentPresenterImpl(this, mContext);
        mPresenter.queryGuardianNoticeList(CacheUtils.getToken(mContext), 0, 15);
        mPresenter.querySchoolIntro(CacheUtils.getToken(getContext()));

    }

    private void initGridView() {
        adapter = new HomeAdapter(getActivity(), data);
        gvTeacher.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        gvTeacher.setOnItemClickListener(this);
    }

    @Override
    public void initListener() {
        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0://学校简介
                intent = new Intent(getActivity(), BaseWebViewActivity.class);
                intent.putExtra("title", SchoolName);
                intent.putExtra("url", schoolDescriptionUrl);
                startActivity(intent);
                break;
            case 1://公告
                intent = new Intent(mContext, NoticeActivity.class);
                intent.putExtra("SchoolName", SchoolName);
                intent.putExtra("roleType", 3 + "");
                intent.putExtra("SchoolId", SchoolId);
                startActivity(intent);
                break;
            case 2://作业发放
                intent = new Intent(mContext, HomeworkActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("roleTag", 2);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 3://教育课程
                intent = new Intent(mContext, EducationActivity.class);
                startActivity(intent);
                break;
            case 4://班级相册
                intent = new Intent(mContext, ClassPhotoActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("roleTag", 2);
                bundle3.putInt("mediaType", 1);
                intent.putExtras(bundle3);
                startActivity(intent);
                break;
            case 5://班级视频
                intent = new Intent(mContext, ClassPhotoActivity.class);
                Bundle bundle4 = new Bundle();
                bundle4.putInt("roleTag", 2);
                bundle4.putInt("mediaType", 2);
                intent.putExtras(bundle4);
                startActivity(intent);
                break;
            case 6://班课表
                intent = new Intent(mContext, ScheduleActivity.class);
                intent.putExtra("SchoolName", SchoolName);
                intent.putExtra("SchoolId", SchoolId);
                startActivity(intent);
                break;
            case 7://学生评价
                intent = new Intent(mContext, EvaluateActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("roleTag", 2);
                intent.putExtras(bundle1);
                startActivity(intent);
                break;
            case 8://提前放学  小孩请假
                intent = new Intent(mContext, ParentLeaveActivity.class);
                intent.putExtra("SchoolName", SchoolName);
                intent.putExtra("SchoolId", SchoolId);
                startActivity(intent);
                break;
            case 9://班讯录
                intent = new Intent(mContext, AddressBookActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("roleTag", 2);
                intent.putExtras(bundle2);
                startActivity(intent);
                break;


        }
    }
    @Override
    public void onPause() {
        super.onPause();
        if (webView != null) webView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (webView != null) webView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            ViewGroup parent = (ViewGroup) webView.getParent();
            if (parent != null) {
                parent.removeView(webView);
            }
            webView.removeAllViews();
            webView.destroy();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }

    @Override
    public void noticeDataCallback(List<Notice> data) {
        mNoticeData.clear();
        mNoticeData.addAll(data);
        List<String> info = new ArrayList<>();
        for (Notice mNotice : data) {
            if (!TextUtils.isEmpty(mNotice.getTitle())) {
                info.add(mNotice.getTitle());
            }
        }
        marqueeView.startWithList(info);
    }

    @Override
    public void querySchoolIntro(SchoolIntro data) {
        schoolDescriptionUrl = data.getSchoolDescriptionUrl();


    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(getActivity());
    }
}
