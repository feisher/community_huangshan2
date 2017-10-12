package com.yusong.community.ui.school.user.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
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

import com.bumptech.glide.Glide;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.school.mvp.entity.Clazz;
import com.yusong.community.ui.school.mvp.implView.ICreateRoleActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.CreateRoleActivityPresenterImpl;
import com.yusong.community.utils.AndroidBug5497Workaround;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.glide.GlideImgManager;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateRoleActivity extends BaseActivity implements ICreateRoleActivityView {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_mobile)
    TextView tvMobile;
    @InjectView(R.id.tv_school)
    TextView tvSchool;
    @InjectView(R.id.ll_setting_school)
    LinearLayout llSettingSchool;
    @InjectView(R.id.tv_role)
    TextView tvRole;
    @InjectView(R.id.ll_setting_role)
    LinearLayout llSettingRole;
    @InjectView(R.id.et_student_id)
    EditText etStudentId;
    @InjectView(R.id.tv_class)
    TextView tvClass;
    @InjectView(R.id.ll_setting_class)
    LinearLayout llSettingClass;
    @InjectView(R.id.iv_child_icon)
    ImageView ivChildIcon;
    @InjectView(R.id.ll_setting_child_icon)
    LinearLayout llSettingChildIcon;
    @InjectView(R.id.btn_commit)
    Button btnCommit;
    @InjectView(R.id.activity_create_role)
    LinearLayout llactivityCreateRole;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_relation)
    TextView tvRelation;
    @InjectView(R.id.ll_select_relation)
    LinearLayout llSelectRelation;
    @InjectView(R.id.pop_view)
    View mPopView;
    @InjectView(R.id.ll_student_name)
    LinearLayout llStudentName;
    @InjectView(R.id.ll_student_id)
    LinearLayout llStudentId;
    @InjectView(R.id.activity_create_role)
    LinearLayout activityCreateRole;
    @InjectView(R.id.iv1)
    ImageView iv1;
    @InjectView(R.id.iv2)
    ImageView iv2;
    @InjectView(R.id.iv3)
    ImageView iv3;
    @InjectView(R.id.et_role_realname)
    EditText etRoleRealname;
    @InjectView(R.id.et_role_idcode)
    EditText etRoleIdcode;
    @InjectView(R.id.et_student_name)
    EditText etStudentName;
    // 自定义图片加载器
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };
    public ImgSelConfig config;
    int ZIP_WIDTH = 400;
    int ZIP_HEIGHT = 600;
    public MultipartBody.Part userIconFile;
    public Context mContext;
    List<Clazz> clazzListDatas = new ArrayList<>();
    public List<String> ClazzNameListData = new ArrayList<>();
    public List<String> relationListData = new ArrayList<>();
    public String school;
    public String role;
    public int schoolId;
    public CreateRoleActivityPresenterImpl mPresenter;
    public int clazzSelection;
    public int relationSelection;


    @Override
    protected int layoutId() {
        return R.layout.activity_create_role;
    }

    @Override
    public void initData() {
        mContext = CreateRoleActivity.this;
        AndroidBug5497Workaround.assistActivity(this);
        mPresenter = new CreateRoleActivityPresenterImpl(this, this);
        initImageSelector();
        llBack.setVisibility(View.VISIBLE);
        tvTitle.setText("申请角色");
        school = getIntent().getExtras().getString("SchoolName");
        role = getIntent().getExtras().getString("role");
        if (!TextUtils.isEmpty(getIntent().getStringExtra("SchoolId"))) {
            schoolId = Integer.parseInt(getIntent().getStringExtra("SchoolId"));
        }

        if (!TextUtils.isEmpty(school)) {
            tvSchool.setText(school);
        }
        if (!TextUtils.isEmpty(role)) {
            tvRole.setText(role);
            if (role.equals("老师")) {
                llSelectRelation.setVisibility(View.GONE);
                llStudentName.setVisibility(View.GONE);
                llStudentId.setVisibility(View.GONE);
                iv1.setVisibility(View.GONE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.GONE);
            } else {
                llSelectRelation.setVisibility(View.VISIBLE);
                llStudentName.setVisibility(View.VISIBLE);
                llStudentId.setVisibility(View.VISIBLE);
                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.VISIBLE);
                iv3.setVisibility(View.VISIBLE);
                relationListData.add("爸爸");
                relationListData.add("妈妈");
                relationListData.add("爷爷");
                relationListData.add("奶奶");
                relationListData.add("其他亲属");
            }
            llactivityCreateRole.invalidate();//刷新界面
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryClazzList(CacheUtils.getToken(this), schoolId, 0, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data); // 图片选择结果回调
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (pathList.size() > 0) {
                try {
                    GlideImgManager.loadImageFitCenter(mContext, pathList.get(0), ivChildIcon);
                    File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(0)));
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                    userIconFile = MultipartBody.Part.createFormData("photo", file.getName(), requestFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @NonNull
    private RequestBody getRequestBody(String s) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), s);
    }


    @OnClick({R.id.ll_back, R.id.btn_commit, R.id.ll_select_relation, R.id.ll_setting_school, R.id.ll_setting_role, R.id.ll_setting_class, R.id.ll_setting_child_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_commit://提交按钮
                if (ClazzNameListData.size() > 0) {
                    if (role.equals("老师")) {
                        teacherApply2();//先对数据进行本地判断，再去提交
                    } else {
                        guardianApply2();
                    }
                } else {
                    MyApplication.showMessage("班级数据为空");
                }
                break;
            case R.id.ll_setting_school:
//                showWheelView(1, schoolListData);
                break;
            case R.id.ll_select_relation:
                if (relationListData.size() > 0) {
                    showWheelView(3, relationListData);
                }
                break;
            case R.id.ll_setting_role:
                break;
            case R.id.ll_setting_class:
                if (ClazzNameListData.size() > 0) {
                    showWheelView(2, ClazzNameListData);
                } else {
                    MyApplication.showMessage("班级列表为空！");
                }
                break;
            case R.id.ll_setting_child_icon:
                ImgSelActivity.startActivity(this, config, 0);
                break;
        }
    }

    private void guardianApply2() {
        if (TextUtils.isEmpty(etRoleRealname.getText().toString())) {
            MyApplication.showMessage("真实姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(etRoleRealname.getText().toString())) {
            MyApplication.showMessage("真实姓名不能为空");
            return;
        }
        if (null == userIconFile) {
            MyApplication.showMessage("请添加照片后再次提交");
            return;
        }
        if (TextUtils.isEmpty(etStudentName.getText().toString().trim())) {
            MyApplication.showMessage("请填入孩子姓名后再提交");
            return;
        }
        if (TextUtils.isEmpty(etStudentId.getText().toString().trim())) {
            MyApplication.showMessage("请填入孩子学号后再提交");
            return;
        }
        mPresenter.guardianApply(getRequestBody(CacheUtils.getToken(this)),
                getRequestBody(schoolId + ""),
                getRequestBody(clazzListDatas.get(clazzSelection).getId() + ""),
                getRequestBody(etRoleRealname.getText().toString().trim()),
                getRequestBody("家长"),
                getRequestBody(etRoleIdcode.getText().toString().trim()),
                getRequestBody(etStudentName.getText().toString().trim()),
                getRequestBody(etStudentId.getText().toString().trim()),
                getRequestBody(tvRelation.getText().toString().trim()),
                getRequestBody("家长申请"),
                userIconFile);

    }

    private void teacherApply2() {
        if (TextUtils.isEmpty(etRoleRealname.getText().toString())) {
            MyApplication.showMessage("真实姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(etRoleRealname.getText().toString())) {
            MyApplication.showMessage("真实姓名不能为空");
            return;
        }

        if (null == userIconFile) {
            MyApplication.showMessage("请添加照片后再次提交");
            return;
        }
        final BaseDialog baseDialog = new BaseDialog(mContext);
        baseDialog.setTitle("提醒").setMessage("确认提交吗？一旦提交通过审核将不允许修改个人信息！")
                .setPositiveButton("确定递交", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.teacherApply(getRequestBody(CacheUtils.getToken(mContext)), getRequestBody(schoolId + ""),
                                getRequestBody(clazzListDatas.get(clazzSelection).getId() + ""),
                                getRequestBody(etRoleRealname.getText().toString()), getRequestBody("老师"),
                                getRequestBody(etRoleIdcode.getText().toString()), getRequestBody("老师申请"), userIconFile);
                        baseDialog.dismiss();
                    }
                }).setNegativeButton("再看一下", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
            }
        });


    }

    private void showWheelView(final int type, final List<String> data) {
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
                switch (type) {
                    case 2:
                        String clazz = (String) wheelView.getSelectionItem();
                        tvClass.setText(clazz);
                        clazzSelection = wheelView.getCurrentPosition();
                        window.dismiss();
                        break;
                    case 3:
                        String relation = (String) wheelView.getSelectionItem();
                        tvRelation.setText(relation);
                        relationSelection = wheelView.getCurrentPosition();
                        window.dismiss();
                        break;
                }
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
                .needCrop(false)
                .needCamera(true)
                .maxNum(4)
                .build();
    }

    @Override
    public void clazzCallback(List<Clazz> data) {
        ClazzNameListData.clear();
        for (Clazz c : data) {
            if (!TextUtils.isEmpty(c.getClazzName())) {
                ClazzNameListData.add(c.getClazzName());
            }
        }
        clazzListDatas.clear();
        clazzListDatas.addAll(data);
    }

    @Override
    public void teacherApplyCallback() {
        startActivity(new Intent(this, CreateRoleExplainActivity.class));
        finish();
    }

    @Override
    public void guardianApplyCallback() {
        startActivity(new Intent(this, CreateRoleExplainActivity.class));
        finish();
    }

    @Override
    public void showProgressDialog() {

    }

}
