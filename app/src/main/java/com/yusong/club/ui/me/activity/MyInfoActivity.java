package com.yusong.club.ui.me.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMClient;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.community.activity.NearbyCommuityActivity;
import com.yusong.club.ui.home.activity.LoginActivity;
import com.yusong.club.ui.me.mvp.entity.UserInfo;
import com.yusong.club.ui.me.mvp.implView.IMyInfoActivityView;
import com.yusong.club.ui.me.mvp.presenter.impl.MyInfoActivityPresenterImpl;
import com.yusong.club.ui.school.mvp.entity.Role;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.MD5Utils;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.glide.GlideImgManager;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyInfoActivity extends BaseActivity implements IMyInfoActivityView {
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @InjectView(R.id.ll_change_user_icon)
    LinearLayout llChangeUserIcon;
    @InjectView(R.id.tv_nickname)
    TextView tvNickname;
    @InjectView(R.id.ll_change_neckname)
    LinearLayout llChangeNeckname;
    @InjectView(R.id.tv_realname)
    TextView tvRealname;
    @InjectView(R.id.ll_change_realname)
    LinearLayout llChangeRealname;
    @InjectView(R.id.tv_sex)
    TextView tvSex;
    @InjectView(R.id.ll_change_sex)
    LinearLayout llChangeSex;
    @InjectView(R.id.tv_phone)
    TextView tvPhone;
    @InjectView(R.id.ll_change_phone)
    LinearLayout llChangePhone;
    @InjectView(R.id.ll_change_password)
    LinearLayout llChangePassword;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.right_radio)
    RadioButton yiwanchnengRadio;
    @InjectView(R.id.tv_current_community)
    TextView tvCurrentCommunity;
    @InjectView(R.id.ll_change_community)
    LinearLayout llChangeCommunity;
    @InjectView(R.id.activity_my_info)
    LinearLayout activityMyInfo;
    //    @InjectView(R.id.tv_current_school)
//    TextView tvCurrentSchool;
//    @InjectView(R.id.ll_change_school)
//    LinearLayout llChangeSchool;
    @InjectView(R.id.pop_view)
    View mPopView;
    // 自定义图片加载器
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };
    public ImgSelConfig config;
    int SETCOMMUNITY_REQUEST_CODE = 9;
    public MyInfoActivityPresenterImpl mPresenter;
    public RequestBody emptyParmas;
    int ZIP_WIDTH = 240;
    int ZIP_HEIGHT = 240;
    public Context mContext;

    List<Role.RoleListBean> rolesData = new ArrayList<>();

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    public void initView() {
        mContext = MyInfoActivity.this;
        tvTitle.setText("个人信息");
        llBack.setVisibility(View.VISIBLE);
        mPresenter = new MyInfoActivityPresenterImpl(this, this);
        emptyParmas = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        initImageSelector();
    }

    @Override
    public void initData() {
        UserInfo userInfo = CacheUtils.getUserInfo(this);
        updateShowUserInfo(userInfo);
    }

    private void updateShowUserInfo(UserInfo userInfo) {
        if (userInfo != null) {
            if (!TextUtils.isEmpty(userInfo.getPortrait())) {
                GlideImgManager.loadCircleImage(this, userInfo.getPortrait(), ivUserIcon);
            }
            if (!TextUtils.isEmpty(userInfo.getNickname())) {
                tvNickname.setText(userInfo.getNickname());
                EMClient.getInstance().updateCurrentUserNick(userInfo.getNickname());
            }
            if (!TextUtils.isEmpty(userInfo.getRealName())) {
                tvRealname.setText(userInfo.getRealName());
                if (StringUtils.isEmpty(userInfo.getNickname())) {
                    EMClient.getInstance().updateCurrentUserNick(userInfo.getRealName());
                }
            }
            if (!TextUtils.isEmpty(userInfo.getCommunityName())) {
                tvCurrentCommunity.setText(userInfo.getCommunityName());
            }
            if (userInfo.getGender() == 1) {
                tvSex.setText("男");
            } else {
                tvSex.setText("女");
            }
            String mobile = userInfo.getMobile();
            if (mobile.length() > 8) {
                tvPhone.setText(mobile.substring(0, 3) + "****" + mobile.substring(7));
            }
            activityMyInfo.invalidate();
        }
    }

    @OnClick({R.id.ll_back, R.id.ll_change_user_icon, R.id.ll_change_neckname, R.id.ll_change_realname, R.id.ll_change_sex, R.id.ll_change_phone, R.id.ll_change_password, R.id.ll_change_community})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_change_user_icon:
                ImgSelActivity.startActivity(this, config, 0);
                break;
            case R.id.ll_change_neckname:
                showDialog("neckname");
                break;
            case R.id.ll_change_realname:
                showDialog("realname");
                break;
            case R.id.ll_change_sex:
                MultipartBody.Part portrait = MultipartBody.Part.createFormData("portrait", "portrait.jpg", emptyParmas);
                if (tvSex.getText().equals("男")) {
                    tvSex.setText("女");
                    RequestBody woman = RequestBody.create(MediaType.parse("multipart/form-data"), "2");
                    mPresenter.upDateUserInfo(emptyParmas, emptyParmas, woman, emptyParmas, portrait);
                } else {
                    tvSex.setText("男");
                    RequestBody man = RequestBody.create(MediaType.parse("multipart/form-data"), "1");
                    mPresenter.upDateUserInfo(emptyParmas, emptyParmas, man, emptyParmas, portrait);
                }
                break;
            case R.id.ll_change_password:
                showChangePasswordDialog();
                break;
            case R.id.ll_change_community:
                startActivityForResult(new Intent(this, NearbyCommuityActivity.class), SETCOMMUNITY_REQUEST_CODE);
                break;
//            case R.id.ll_change_school:
//                List<String> data = new ArrayList<>();
//                data.add("一中");
//                data.add("良渚大学");
//                data.add("国务院直属一小");
//                showPopupWindow(data);
////                showMessage("学校模块暂未开放");
//                MyApplication.showMessage("学校模块暂未开放");
//                break;
        }
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
                String school = (String) wheelView.getSelectionItem();
//                tvCurrentSchool.setText(school);
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

    private void showChangePasswordDialog() {
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        final View view = factory.inflate(R.layout.layout_change_password_dialog, null);//这里必须是final的
        final EditText nowPassWord = (EditText) view.findViewById(R.id.et_now_password);//获得输入框对象
        final EditText newPassWord = (EditText) view.findViewById(R.id.et_new_password);//获得输入框对象
        final EditText newPassWord2 = (EditText) view.findViewById(R.id.et_new_password2);//获得输入框对象
        new AlertDialog.Builder(this)
                .setTitle("修改密码")//提示框标题
                .setView(view)
                .setPositiveButton("提交",//提示框的两个按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                String nowPassWord_content = nowPassWord.getText().toString().trim();
                                String newPassWord_content = newPassWord.getText().toString().trim();
                                String newPassWord2_content = newPassWord2.getText().toString().trim();
                                if (!newPassWord_content.equals(newPassWord2_content)) {
                                    ToastUtils.showShort(MyInfoActivity.this, "两次输入的密码不一致");
                                    return;
                                }
                                //联网将修改密码发送到服务器
                                mPresenter.updatePassword(MD5Utils.md5Password(nowPassWord_content), MD5Utils.md5Password(newPassWord_content));
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    private void showDialog(final String type) {
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        final View view = factory.inflate(R.layout.layout_with_edittext_dialog, null);//这里必须是final的
        final EditText editText = (EditText) view.findViewById(R.id.editText);//获得输入框对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (type.equals("neckname")) {
            builder.setTitle("请设置昵称");//提示框标题
            editText.setHint("请输入昵称");
        }
        if (type.equals("realname")) {
            builder.setTitle("请输入真实姓名");//提示框标题
            editText.setHint("请输入姓名");
        }
        builder.setView(view)
                .setPositiveButton("提交",//提示框的两个按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String content = editText.getText().toString().trim();
                                if (!TextUtils.isEmpty(content)) {
                                    MultipartBody.Part portrait = MultipartBody.Part.createFormData("portrait", "portrait.jpg", emptyParmas);
                                    if (type.equals("neckname")) {
                                        tvNickname.setText(content);
                                        RequestBody nickname = RequestBody.create(MediaType.parse("multipart/form-data"), content);
                                        mPresenter.upDateUserInfo(nickname, emptyParmas, emptyParmas, emptyParmas, portrait);
                                    }
                                    if (type.equals("realname")) {
                                        RequestBody realname = RequestBody.create(MediaType.parse("multipart/form-data"), content);
                                        mPresenter.upDateUserInfo(emptyParmas, realname, emptyParmas, emptyParmas, portrait);
                                        tvRealname.setText(content);
                                    }
                                } else {
                                    ToastUtils.showShort(MyInfoActivity.this, "请输入内容后再提交" + content);
                                    return;
                                }
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data); // 图片选择结果回调
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (pathList != null && pathList.size() > 0) {
                GlideImgManager.loadCircleImage(this, pathList.get(0), ivUserIcon);
                Bitmap bitmap = null;
                try {
//                    File file = Compressor.getDefault(this).compressToFile(new File(pathList.get(0)));
                    File file = new Compressor.Builder(mContext.getApplicationContext())
                            .setMaxWidth(240)
                            .setMaxHeight(240)
                            .setQuality(75)
                            .setCompressFormat(Bitmap.CompressFormat.JPEG)
                            .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_PICTURES).getAbsolutePath())
                            .build()
                            .compressToFile(new File(pathList.get(0)));
//                    bitmap = BitmapUtil.safeDecodeStream(getImageContentUri(new File(pathList.get(0))), ZIP_WIDTH, ZIP_HEIGHT, mContext);
//                    File file = BitmapUtil.saveBitmapFile(bitmap);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                    MultipartBody.Part portrait = MultipartBody.Part.createFormData("portrait", file.getName(), requestFile);
                    mPresenter.upDateUserInfo(emptyParmas, emptyParmas, emptyParmas, emptyParmas, portrait);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == SETCOMMUNITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String communityName = data.getExtras().getString("communityName");
            tvCurrentCommunity.setText(communityName);
        }

    }

    private void initImageSelector() {
        config = new ImgSelConfig.Builder(this, loader)
                .multiSelect(false)
                .btnBgColor(Color.TRANSPARENT)
                .btnTextColor(Color.WHITE)
                .statusBarColor(Color.parseColor("#1DACFF"))
                .backResId(R.mipmap.back)
                .title("请选择图片")
                .titleColor(Color.WHITE)
                .titleBgColor(Color.parseColor("#1DACFF"))
                .cropSize(1, 1, 200, 200)
                .needCrop(false)//是否需要图片修剪功能
                .needCamera(true)
                .maxNum(4)
                .build();
    }


    @Override
    public void updateUserInfoCallback(UserInfo userInfo) {
        ToastUtils.showShort(this, "修改成功！");
        updateShowUserInfo(userInfo);
    }

    @Override
    public void updatePasswordCallback() {
        CacheUtils.clearSP(this);
        MyApplication.exit();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void roleListCallback(Role data) {
        rolesData.addAll(data.getRoleList());
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
