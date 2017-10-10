package com.yusong.club.ui.visitor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.visitor.entity.ThroughCardBean;
import com.yusong.club.utils.QRUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * @author Mr_Peng
 * @created at 2017-08-24.
 * @describe: 通行证卡片页面
 */

public class ThroughCardActivity extends BaseActivity {
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
    @InjectView(R.id.send_through_layout)
    LinearLayout sendThroughLayout;
    @InjectView(R.id.throught_layout)
    LinearLayout throughtLayout;
    @InjectView(R.id.through_name_tv)
    TextView throughNameTv;
    @InjectView(R.id.through_code_tv)
    TextView throughCodeTv;
    @InjectView(R.id.erweima_image)
    ImageView erweimaImage;
    @InjectView(R.id.valid_time_tv)
    TextView validTimeTv;
    @InjectView(R.id.card_layout)
    LinearLayout cardLayout;

//    private Handler mHandler = new Handler();
    private ThroughCardBean throughCardBean;
    private int height, width;
    private boolean hasMeasured = false;

    @OnClick({R.id.ll_back, R.id.send_through_layout})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.send_through_layout:
                showShare();
//                showPopupWindow();
                break;

        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_through_card;
    }

    @Override
    public void initData() {

    }


    @Override
    public void initView() {
        Intent intent = getIntent();
        throughCardBean = (ThroughCardBean) intent.getSerializableExtra("ThroughCardBean");
        tvTitle.setText(R.string.tongxingzheng);
    }

    @Override
    protected void onStart() {
        if (throughCardBean != null) {
            String sex = "";
            if (throughCardBean.getSex() == 1) {
                sex = "先生";
            } else {
                sex = "女士";
            }
            throughNameTv.setText(String.format("尊敬的 %s" + sex, throughCardBean.getVisitorName()));
            throughCodeTv.setText(throughCardBean.getPermitCode());
            validTimeTv.setText(String.format("有效日期：%s", throughCardBean.getVisitorTime()));
            ViewTreeObserver vto = erweimaImage.getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    if (hasMeasured == false) {
                        hasMeasured = true;
                        height = erweimaImage.getMeasuredHeight();
                        width = erweimaImage.getMeasuredWidth();
                        Log.e("pcg xixi", height + ":" + width);
                        try {
                            Log.e("pcg haha", height + ":" + width);
                            Bitmap bitmap = QRUtils.bitmap(throughCardBean.getPermitCode(), width, width);
                            erweimaImage.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
            });
        }
//        //保存图片
//        cardLayout.setDrawingCacheEnabled(true);
//        cardLayout.buildDrawingCache();
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                final Bitmap bmp = cardLayout.getDrawingCache(); // 获取图片
//                savePicture(bmp, String.format("%s.png", throughCardBean.getPermitCode()));// 保存图片
//                cardLayout.destroyDrawingCache(); // 保存过后释放资源
//            }
//        }, 100);
        super.onStart();
    }


    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle("黄山智联");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
//        oks.setText("访客通行证");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/hszl/"+String.format("%s.jpg",throughCardBean.getPermitCode()));//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.setViewToShare(cardLayout);
        oks.show(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    public void savePicture(Bitmap bm, String fileName) {

        if (bm == null) {
            return;
        }
        File foder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/hszl");
        if (!foder.exists()) {
            foder.mkdirs();
        }
        File myCaptureFile = new File(foder, fileName);
        try {
            if (!myCaptureFile.exists()) {
                myCaptureFile.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 90, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_through_send, null);
        LinearLayout sendWeixinLayout = (LinearLayout) contentView.findViewById(R.id.send_weixin_layout);
        LinearLayout sendQQLayout = (LinearLayout) contentView.findViewById(R.id.send_qq_layout);
        LinearLayout sendDuanxinLayout = (LinearLayout) contentView.findViewById(R.id.send_duanxin_layout);
        LinearLayout saveNativeLayout = (LinearLayout) contentView.findViewById(R.id.save_native_layout);
        sendWeixinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        sendQQLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        sendDuanxinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        saveNativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        popupWindow.showAtLocation(throughtLayout, Gravity.BOTTOM, 0, 0);
        popupWindow.update();
    }
}
