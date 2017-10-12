package com.yusong.community.ui.school.school.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.mvp.entity.Role;
import com.yusong.community.ui.school.mvp.entity.School;
import com.yusong.community.ui.school.mvp.implView.IChooseActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.ChooseActivityPresenterImpl;
import com.yusong.community.ui.school.parent.activity.ParentActivity;
import com.yusong.community.ui.school.teacher.activity.TeacherActivity;
import com.yusong.community.ui.school.user.activity.CreateRoleActivity;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.DialogParentTypeTool;
import com.yusong.community.utils.DialogTypeTool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * 选择身份
 */
public class ChooseActivity extends BaseActivity implements DialogTypeTool.OnChooseTypeLinster, DialogParentTypeTool.OnChooseTypeLinster, IChooseActivityView, RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    @InjectView(R.id.btn_join)
    Button btnJoin;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.pop_view)
    View mPopView;
    @InjectView(R.id.ll_all_views)
    LinearLayout llAllViews;
    private int jobNum = 3;
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
    @InjectView(R.id.im_job)
    ImageView imJob;
    @InjectView(R.id.rbtn_school)
    RadioButton rbtnSchool;
    @InjectView(R.id.rbtn_teacher)
    RadioButton rbtnTeacher;
    @InjectView(R.id.rbtn_parent)
    RadioButton rbtnParent;
    @InjectView(R.id.rg_group)
    RadioGroup rgGroup;
    public String type;

    List<School> schoolListDatas = new ArrayList<>();
    public ChooseActivityPresenterImpl mPresenter;
    public int selection;
    public String roleTypeList;
    public String schoolName;
    public String schoolId;
    public List<Role.RoleListBean> multiSchoolRoleDatas;
    public boolean isChooseInMultiSchool;
    public Context mContext;
    public Role.RoleListBean roleListBean;
    public Role.RoleListBean singleSchoolRoleList;
    private int teacherRole = -1;
    private int parentRole = -1;
    private int clazzSelect = 0;
    private int parentSelect = 0;
    public static final int REQUSET_CODE = 1000;


    @Override
    protected void initListener() {
        rgGroup.setOnCheckedChangeListener(this);
        btnJoin.setOnClickListener(this);
        linearBack.setOnClickListener(this);
        tvTitle.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_school_choose;
    }

    @Override
    public void initView() {
        mContext = ChooseActivity.this;
        tvBack.setVisibility(View.GONE);
        imMsg.setVisibility(View.GONE);
        mPresenter = new ChooseActivityPresenterImpl(this, this);
    }

    @Override
    public void initData() {
        type = getIntent().getExtras().getString("type");
        roleTypeList = getIntent().getExtras().getString("roleTypeList");
        schoolName = getIntent().getExtras().getString("SchoolName");
        schoolId = getIntent().getExtras().getString("SchoolId");
        multiSchoolRoleDatas = (List<Role.RoleListBean>) getIntent().getExtras().getSerializable("multiSchoolRoleDatas");
        singleSchoolRoleList = (Role.RoleListBean) getIntent().getExtras().getSerializable("SingleSchoolRoleList");
        tvTitle.setText("点击选择学校");
        //默认选中
        jobNum = 1;
        imJob.setImageResource(R.mipmap.school_big);
        rgGroup.check(R.id.rbtn_school);
        distributeType();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void distributeType() {
        if (type.equals("chooseIn")) {//一所学校多角色
            if (!TextUtils.isEmpty(schoolName)) {
                tvTitle.setText(schoolName);
            }
            if (!TextUtils.isEmpty(roleTypeList)) {
                if (roleTypeList.equals("12")) {
                    rbtnSchool.setVisibility(View.VISIBLE);
                    rbtnTeacher.setVisibility(View.VISIBLE);
                    rbtnParent.setVisibility(View.GONE);
                    imJob.setImageResource(R.mipmap.teacher_big);
                    rgGroup.check(R.id.rbtn_teacher);
                    llAllViews.invalidate();
                } else if (roleTypeList.equals("23")) {
                    rbtnSchool.setVisibility(View.GONE);
                    rbtnTeacher.setVisibility(View.VISIBLE);
                    rbtnParent.setVisibility(View.VISIBLE);
                    imJob.setImageResource(R.mipmap.teacher_big);
                    rgGroup.check(R.id.rbtn_teacher);
                    llAllViews.invalidate();
                } else if (roleTypeList.equals("13")) {
                    rbtnSchool.setVisibility(View.VISIBLE);
                    rbtnTeacher.setVisibility(View.GONE);
                    rbtnParent.setVisibility(View.VISIBLE);
                    imJob.setImageResource(R.mipmap.school_big);
                    rgGroup.check(R.id.rbtn_school);
                    llAllViews.invalidate();
                } else if (roleTypeList.equals("123")) {
                    rbtnSchool.setVisibility(View.VISIBLE);
                    rbtnTeacher.setVisibility(View.VISIBLE);
                    rbtnParent.setVisibility(View.VISIBLE);
                    imJob.setImageResource(R.mipmap.school_big);
                    rgGroup.check(R.id.rbtn_school);
                    llAllViews.invalidate();
                }
            }
            isChooseInMultiSchool = false;

        } else if (type.equals("chooseInMultiSchool")) {//多学校，多角色处理
            isChooseInMultiSchool = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    multiSchoolChoose();
                }
            }, 500);

        } else if (type.equals("chooseCreate")) {
            isChooseInMultiSchool = false;
            rbtnSchool.setVisibility(View.GONE);
            mPresenter.querySchoolList(CacheUtils.getToken(this), 0, 50);
        } else {
            isChooseInMultiSchool = false;
            rbtnSchool.setVisibility(View.VISIBLE);
        }
    }

    public void setRoleInfo() {
    }

    private void multiSchoolChoose() {
        List<School> schoolNames = new ArrayList<>();
        for (Role.RoleListBean roleListBean : multiSchoolRoleDatas) {
            School school = new School();
            school.setSchoolName(roleListBean.getSchoolName());
            school.setId(roleListBean.getId());
            schoolNames.add(school);
        }
        showWheelView(schoolNames);
    }


    private void showWheelView(final List<School> data) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow_express_layout, null);
        contentView.getBackground().setAlpha(100);
        final PopupWindow window;
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        window.setContentView(contentView);
        final WheelView wheelView = (WheelView) contentView.findViewById(R.id.wheelview);
        wheelView.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        List<String> schoolNames = new ArrayList<>();
        for (School s : data) {
            if (!TextUtils.isEmpty(s.getSchoolName())) {
                schoolNames.add(s.getSchoolName());
            }
        }
        wheelView.setWheelData(schoolNames);  // 数据集合
        wheelView.setSkin(WheelView.Skin.Holo);
        wheelView.setWheelSize(5);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.textColor = Color.parseColor("#888888");
        style.selectedTextColor = Color.parseColor("#333333");
        wheelView.setStyle(style);
        TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            public int size2;
            public int size1;
            public int size;

            @Override
            public void onClick(View v) {
                String school = (String) wheelView.getSelectionItem();
                tvTitle.setText(school);
                selection = wheelView.getCurrentPosition();
                window.dismiss();
                if (isChooseInMultiSchool == true) {//TODO 选择完学校，需要根据角色种类刷新界面
                    Intent mIntent = null;
                    roleListBean = multiSchoolRoleDatas.get(selection);
                    schoolId = roleListBean.getId() + "";
                    if (roleListBean.getManagerList() != null) {
                        size = roleListBean.getManagerList().size();
                    }
                    if (roleListBean.getTeacherList() != null) {
                        size1 = roleListBean.getTeacherList().size();
                    }
                    if (roleListBean.getGuardianList() != null) {
                        size2 = roleListBean.getGuardianList().size();
                    }
                    if (size + size1 + size2 == 0) {
                        MyApplication.showMessage("数据异常");
                        return;
                    }
                    StringBuffer sb = new StringBuffer();
                    if (roleListBean.getManagerList().size() > 0) {
                        sb.append("1");
                    }
                    if (roleListBean.getTeacherList().size() > 0) {
                        sb.append("2");
                    }
                    if (roleListBean.getGuardianList().size() > 0) {
                        sb.append("3");
                    }
                    //进入角色选择页面需要做如下判断
                    //TODO sb.toString():1 or 2 or 3 or 12 or 13 or 23 or 123
                    if (sb.toString().equals("1")) {
                        mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getManagerList().get(0).getId(), 1);
                    } else if (sb.toString().equals("2")) {
                        mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getTeacherList().get(0).getId(), 2);
                    } else if (sb.toString().equals("3")) {
                        mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getGuardianList().get(0).getId(), 3);
                    } else if (sb.toString().equals("12")) {
                        rbtnSchool.setVisibility(View.VISIBLE);
                        rbtnTeacher.setVisibility(View.VISIBLE);
                        rbtnParent.setVisibility(View.GONE);
                        imJob.setImageResource(R.mipmap.teacher_big);
                        rgGroup.check(R.id.rbtn_teacher);
                        llAllViews.invalidate();
                    } else if (sb.toString().equals("23")) {
                        rbtnSchool.setVisibility(View.GONE);
                        rbtnTeacher.setVisibility(View.VISIBLE);
                        rbtnParent.setVisibility(View.VISIBLE);
                        imJob.setImageResource(R.mipmap.teacher_big);
                        rgGroup.check(R.id.rbtn_teacher);
                        llAllViews.invalidate();
                    } else if (sb.toString().equals("13")) {
                        rbtnSchool.setVisibility(View.VISIBLE);
                        rbtnTeacher.setVisibility(View.GONE);
                        rbtnParent.setVisibility(View.VISIBLE);
                        imJob.setImageResource(R.mipmap.school_big);
                        rgGroup.check(R.id.rbtn_school);
                        llAllViews.invalidate();
                    } else if (sb.toString().equals("123")) {
                        rbtnSchool.setVisibility(View.VISIBLE);
                        rbtnTeacher.setVisibility(View.VISIBLE);
                        rbtnParent.setVisibility(View.VISIBLE);
                        imJob.setImageResource(R.mipmap.school_big);
                        rgGroup.check(R.id.rbtn_school);
                        llAllViews.invalidate();
                    }
                }
            }
        });
        TextView tv_find = (TextView) contentView.findViewById(R.id.tv_find_school);
        tv_find.setVisibility(View.VISIBLE);
        tv_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                Intent intent = new Intent(ChooseActivity.this, FindSchoolActivity.class);
                intent.putExtra("multiSchoolRoleDatas", (Serializable) data);
                startActivityForResult(intent, REQUSET_CODE);
//                startActivity(intent);
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbtn_school:
                imJob.setImageResource(R.mipmap.school_big);
                jobNum = 1;
                break;
            case R.id.rbtn_teacher:
                imJob.setImageResource(R.mipmap.teacher_big);
                jobNum = 2;
                break;
            case R.id.rbtn_parent:
                imJob.setImageResource(R.mipmap.parent_big);
                jobNum = 3;
                break;
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.tv_title:
                showWheelView(schoolListDatas);
                break;
            case R.id.btn_join:
                if (tvTitle.getText().toString().equals("点击选择学校") && isChooseInMultiSchool == true) {
                    MyApplication.showMessage("请先选择学校！");
                    multiSchoolChoose();
                    return;
                } else if (!tvTitle.getText().toString().equals("点击选择学校") && isChooseInMultiSchool == true) {
                    if (jobNum == 1) {//（管理员）当前账号每个学校只能有一个管理员角色(可省略，走不到)
                        mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getManagerList().get(0).getId(), 1);
                    }
                    if (jobNum == 2) {//(老师)多学校不存在出现创建角色情况
                        if (!TextUtils.isEmpty(schoolId) && !schoolId.equals("0")) {//多学校内一所学校多角色
                            //TODO 多个老师角色处理
                            teacherRole = 2;
                            if (multiSchoolRoleDatas.get(selection).getTeacherList().size() > 1) {
                                showTeacherChooseRole();
                            } else {
                                mPresenter.selectRole(CacheUtils.getToken(mContext), multiSchoolRoleDatas.get(selection).getId(), multiSchoolRoleDatas.get(selection).getTeacherList().get(0).getId(), 2);
                            }
                        } else {
                            MyApplication.showMessage("数据异常schoolId=" + schoolId);
                        }
                    }
                    if (jobNum == 3) {//（家长）
                        if (!TextUtils.isEmpty(schoolId) && !schoolId.equals("0")) {//多学校内一所学校多角色
                            //TODO 选择登入家长角色
                            parentRole = 2;
                            if (multiSchoolRoleDatas.get(selection).getGuardianList().size() > 1) {
                                parentRole = 2;
                                showParentChooseRole();
                            } else {
                                mPresenter.selectRole(CacheUtils.getToken(mContext), multiSchoolRoleDatas.get(selection).getId(), multiSchoolRoleDatas.get(selection).getGuardianList().get(0).getId(), 3);
                            }
                        } else {
                            MyApplication.showMessage("数据异常schoolId=" + schoolId);
                        }

                    }
                } else {//单学校  isChooseInMultiSchool==false 的情况
                    if (jobNum == 1) {
                        if (!TextUtils.isEmpty(schoolId) && !schoolId.equals("0")) {
                            mPresenter.selectRole(CacheUtils.getToken(mContext), Integer.parseInt(schoolId), singleSchoolRoleList.getManagerList().get(0).getId(), 1);
                        }
                    }
                    if (jobNum == 2) {//（老师）
                        if (type.equals("chooseCreate")) {
                            intent = new Intent(this, CreateRoleActivity.class);
                            intent.putExtra("SchoolName", tvTitle.getText().toString());
                            intent.putExtra("SchoolId", schoolListDatas.get(selection).getId() + "");
                            intent.putExtra("role", "老师");
                            if (!tvTitle.getText().toString().equals("点击选择学校")) {//单个学校登陆，或创建
                                startActivity(intent);
                                finish();
                            } else {//查询学校列表
                                mPresenter.querySchoolList(CacheUtils.getToken(this), 0, 50);
                            }
                        } else {
                            if (!TextUtils.isEmpty(schoolId) && !schoolId.equals("0")) {//一所学校多角色
                                //TODO 一所学校单角色的情况已经在外面处理，这里需要处理一个学校多角色
                                if (singleSchoolRoleList.getTeacherList().size() > 1) {
                                    teacherRole = 2;
                                    showTeacherChooseRole();
                                } else {
                                    mPresenter.selectRole(CacheUtils.getToken(mContext), singleSchoolRoleList.getId(), singleSchoolRoleList.getTeacherList().get(0).getId(), 2);
                                }
                            } else {
                                MyApplication.showMessage("数据异常schoolId=" + schoolId);
                            }
                        }

                    }
                    if (jobNum == 3) {
                        if (type.equals("chooseCreate")) {
                            intent = new Intent(this, CreateRoleActivity.class);
                            intent.putExtra("SchoolName", tvTitle.getText().toString());
                            intent.putExtra("SchoolId", schoolListDatas.get(selection).getId() + "");
                            intent.putExtra("role", "家长");
                            if (!tvTitle.getText().toString().equals("点击选择学校")) {
                                startActivity(intent);
                                finish();
                            } else {
                                mPresenter.querySchoolList(CacheUtils.getToken(this), 0, 50);
                            }
                        } else {
                            if (!TextUtils.isEmpty(schoolId) && !schoolId.equals("0")) {//一所学校多角色
                                //TODO 需要仿照多教师   重写多家长角色选择
                                if (singleSchoolRoleList.getGuardianList().size() > 1) {
                                    parentRole = 2;
                                    showParentChooseRole();

                                } else {
                                    mPresenter.selectRole(CacheUtils.getToken(mContext), singleSchoolRoleList.getId(), singleSchoolRoleList.getGuardianList().get(0).getId(), 3);
                                }
                            } else {
                                MyApplication.showMessage("数据异常schoolId=" + schoolId);
                            }
                        }

                    }
                }
                break;
        }
    }

    private void showTeacherChooseRole() {
        List<Role.RoleListBean.TeacherListBean> teachersListBean = new ArrayList<>();
        if (isChooseInMultiSchool == true) {
            teachersListBean = multiSchoolRoleDatas.get(selection).getTeacherList();
        } else {
            teachersListBean = singleSchoolRoleList.getTeacherList();
        }
        DialogTypeTool.getInstance().initDialog(mContext, llAllViews, teachersListBean);
        DialogTypeTool.getInstance().setmOnChooseTypeLinster(this);
    }

    private void showParentChooseRole() {
        List<Role.RoleListBean.GuardianListBean> guardianListBean = new ArrayList<>();
        if (isChooseInMultiSchool == true) {
            guardianListBean = multiSchoolRoleDatas.get(selection).getGuardianList();
        } else {
            guardianListBean = singleSchoolRoleList.getGuardianList();
        }
        DialogParentTypeTool.getInstance().initDialog(mContext, llAllViews, guardianListBean);
        DialogParentTypeTool.getInstance().setmOnChooseTypeLinster(this);
    }

    @Override
    public void schoolListCallback(List<School> data) {
        schoolListDatas.clear();
        schoolListDatas.addAll(data);
        if (schoolListDatas.size() > 0) {
            showWheelView(schoolListDatas);
        }
    }

    @Override
    public void selectRoleCallback(int type) {
        Intent mIntent = null;
        switch (type) {
            case 1:
                mIntent = new Intent(mContext, SchoolActivity.class);//进入学校界面
                if (isChooseInMultiSchool == true) {
                    mIntent.putExtra("SchoolName", roleListBean.getSchoolName());
                    mIntent.putExtra("SchoolId", roleListBean.getId() + "");
                } else {
                    mIntent.putExtra("SchoolName", singleSchoolRoleList.getSchoolName());
                    mIntent.putExtra("SchoolId", singleSchoolRoleList.getId() + "");
                }
                break;
            case 2:
                mIntent = new Intent(mContext, TeacherActivity.class);//进入老师界面
                if (isChooseInMultiSchool == true) {
                    mIntent.putExtra("SchoolName", roleListBean.getTeacherList().get(clazzSelect).getClazzName());
                    mIntent.putExtra("SchoolId", roleListBean.getId() + "");
                } else {
                    mIntent.putExtra("SchoolName", singleSchoolRoleList.getTeacherList().get(clazzSelect).getClazzName());
                    mIntent.putExtra("SchoolId", singleSchoolRoleList.getId() + "");
                }
                break;
            case 3:
                mIntent = new Intent(mContext, ParentActivity.class);//进入家长界面
                if (isChooseInMultiSchool == true) {
                    mIntent.putExtra("SchoolName", roleListBean.getGuardianList().get(parentSelect).getClazzName());
                    mIntent.putExtra("SchoolId", roleListBean.getId() + "");
                } else {
                    mIntent.putExtra("SchoolName", singleSchoolRoleList.getGuardianList().get(parentSelect).getClazzName());
                    mIntent.putExtra("SchoolId", singleSchoolRoleList.getId() + "");
                }
                break;
        }
        startActivity(mIntent);
        finish();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }

    @Override
    public void getChooseType(int pos) {
        if (teacherRole == 2) {
            clazzSelect = pos;
            if (isChooseInMultiSchool == true) {
                mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getTeacherList().get(pos).getId(), 2);
            } else {
                mPresenter.selectRole(CacheUtils.getToken(mContext), singleSchoolRoleList.getId(), singleSchoolRoleList.getTeacherList().get(pos).getId(), 2);
            }
        }
    }

    @Override
    public void getChooseParentType(int pos) {
        if (parentRole == 2) {
            parentSelect = pos;
            if (isChooseInMultiSchool == true) {
                mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getGuardianList().get(pos).getId(), 3);
            } else {
                mPresenter.selectRole(CacheUtils.getToken(mContext), singleSchoolRoleList.getId(), singleSchoolRoleList.getGuardianList().get(pos).getId(), 3);

            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int size2 = 0;
        int size1 = 0;
        int size = 0;
        if (requestCode == REQUSET_CODE && resultCode == FindSchoolActivity.RESULT_CODE) {

            School school = (School) data.getSerializableExtra("school");
            selection = data.getIntExtra("selection", 0);
            String s = school.getSchoolName();
            tvTitle.setText(s);
            if (isChooseInMultiSchool == true) {//TODO 选择完学校，需要根据角色种类刷新界面
                Intent mIntent = null;
                roleListBean = multiSchoolRoleDatas.get(selection);
                schoolId = school.getId() + "";
                if (roleListBean.getManagerList() != null) {
                    size = roleListBean.getManagerList().size();
                }
                if (roleListBean.getTeacherList() != null) {
                    size1 = roleListBean.getTeacherList().size();
                }
                if (roleListBean.getGuardianList() != null) {
                    size2 = roleListBean.getGuardianList().size();
                }
                if (size + size1 + size2 == 0) {
                    MyApplication.showMessage("数据异常");
                    return;
                }
                StringBuffer sb = new StringBuffer();
                if (roleListBean.getManagerList().size() > 0) {
                    sb.append("1");
                }
                if (roleListBean.getTeacherList().size() > 0) {
                    sb.append("2");
                }
                if (roleListBean.getGuardianList().size() > 0) {
                    sb.append("3");
                }
                //进入角色选择页面需要做如下判断
                //TODO sb.toString():1 or 2 or 3 or 12 or 13 or 23 or 123
                if (sb.toString().equals("1")) {
                    mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getManagerList().get(0).getId(), 1);
                } else if (sb.toString().equals("2")) {
                    mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getTeacherList().get(0).getId(), 2);
                } else if (sb.toString().equals("3")) {
                    mPresenter.selectRole(CacheUtils.getToken(mContext), roleListBean.getId(), roleListBean.getGuardianList().get(0).getId(), 3);
                } else if (sb.toString().equals("12")) {
                    rbtnSchool.setVisibility(View.VISIBLE);
                    rbtnTeacher.setVisibility(View.VISIBLE);
                    rbtnParent.setVisibility(View.GONE);
                    imJob.setImageResource(R.mipmap.teacher_big);
                    rgGroup.check(R.id.rbtn_teacher);
                    llAllViews.invalidate();
                } else if (sb.toString().equals("23")) {
                    rbtnSchool.setVisibility(View.GONE);
                    rbtnTeacher.setVisibility(View.VISIBLE);
                    rbtnParent.setVisibility(View.VISIBLE);
                    imJob.setImageResource(R.mipmap.teacher_big);
                    rgGroup.check(R.id.rbtn_teacher);
                    llAllViews.invalidate();
                } else if (sb.toString().equals("13")) {
                    rbtnSchool.setVisibility(View.VISIBLE);
                    rbtnTeacher.setVisibility(View.GONE);
                    rbtnParent.setVisibility(View.VISIBLE);
                    imJob.setImageResource(R.mipmap.school_big);
                    rgGroup.check(R.id.rbtn_school);
                    llAllViews.invalidate();
                } else if (sb.toString().equals("123")) {
                    rbtnSchool.setVisibility(View.VISIBLE);
                    rbtnTeacher.setVisibility(View.VISIBLE);
                    rbtnParent.setVisibility(View.VISIBLE);
                    imJob.setImageResource(R.mipmap.school_big);
                    rgGroup.check(R.id.rbtn_school);
                    llAllViews.invalidate();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
