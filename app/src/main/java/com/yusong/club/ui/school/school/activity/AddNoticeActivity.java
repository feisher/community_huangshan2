package com.yusong.club.ui.school.school.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.implView.AddNoticeActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.AddNoticeActivityPresenterImpl;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;


/**
 * 新建公告
 */
public class AddNoticeActivity extends BaseActivity implements AddNoticeActivityView,View.OnClickListener {
    //调用系统相册-选择图片
    private static final int IMAGE = 1;

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
    @InjectView(R.id.et_title)
    EditText etTitle;
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.btn_notice)
    Button btnNotice;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.tv_type)
    TextView tvType;
    @InjectView(R.id.tv_limits)
    TextView tvLimits;
    public Context mContext;
    @InjectView(R.id.mPop_view)
    View mPopView;
    public int currentPosition;
    List<String> noticeLimitsListData = new ArrayList<>();
    public AddNoticeActivityPresenterImpl mPresenter;
    public int noticeType= 2;

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
    protected void initListener() {
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_school_add_notice;
    }

    @Override
    public void initView() {
        tvBack.setVisibility(View.GONE);
        tvTitle.setText("新建公告");
        mContext = AddNoticeActivity.this;
        imMsg.setVisibility(View.GONE);
        mPresenter = new AddNoticeActivityPresenterImpl(this, this);
    }

    @Override
    public void initData() {
        noticeLimitsListData.clear();
        noticeLimitsListData.add("老师可见");
        noticeLimitsListData.add("老师家长可见");
        noticeLimitsListData.add("所有人可见");
    }

    private void showPopupWindow(List<String> data) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow_express_layout, null);
        contentView.getBackground().setAlpha(100);
        final PopupWindow window;
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        window.setContentView(contentView);
        final WheelView wheelView = (WheelView) contentView.findViewById(R.id.wheelview);
        wheelView.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        wheelView.setWheelData(data);  // 数据集合
        wheelView.setSkin(WheelView.Skin.Holo);
        wheelView.setWheelSize(5);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.textColor = Color.parseColor("#888888");
        style.selectedTextColor = Color.parseColor("#333333");
        wheelView.setStyle(style);
        TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String limits = (String) wheelView.getSelectionItem();
                tvLimits.setText(limits);
                currentPosition = wheelView.getCurrentPosition();
                window.dismiss();
            }
        });

        TextView tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.showAtLocation(mPopView, Gravity.BOTTOM, 0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        //获取图片路径
//        if (requestCode == SETCOMMUNITY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
//            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
//            if (pathList != null && pathList.size() > 0) {
//                GlideImgManager.loadImage(this, pathList.get(0), imPicture);
//                Bitmap bitmap = null;
//                try {
//                    bitmap = BitmapUtil.safeDecodeStream(getImageContentUri(new File(pathList.get(0))), ZIP_WIDTH, ZIP_HEIGHT, mContext);
//                    File file = BitmapUtil.saveBitmapFile(bitmap);
//                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
//                    portrait = MultipartBody.Part.createFormData("portrait", file.getName(), requestFile);
////                    mPresenter.upDateUserInfo(emptyParmas, emptyParmas, emptyParmas, emptyParmas, portrait);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }


    @OnClick({R.id.tv_type, R.id.tv_limits, R.id.linear_back, R.id.btn_notice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.btn_notice:
                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString().trim();
                if (!TextUtils.isEmpty(title)&&!TextUtils.isEmpty(content)) {
                    mPresenter.createNotice(CacheUtils.getToken(mContext),title,content, noticeType, currentPosition+1);
                }
                break;
            case R.id.tv_type:
                if (tvType.getText().toString().equals("学校公告")) {
                    tvType.setText("学校通知");
                    noticeType = 1;
                } else {
                    tvType.setText("学校公告");
                    noticeType = 2;
                }
                break;
            case R.id.tv_limits:
                showPopupWindow(noticeLimitsListData);
                break;
        }
    }


    @Override
    public void addNoticeCallback() {
        setResult(ActivityConstants.NOTITY_CONGIRM);
        finish();
    }

    @Override
    public void showProgressDialog() {

    }
}
