package com.yusong.club.ui.school.school.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.charge.view.BaseDialog;
import com.yusong.club.ui.school.mvp.entity.TelBookData;
import com.yusong.club.ui.school.mvp.implView.ISchoolAddressView;
import com.yusong.club.ui.school.mvp.presenter.impl.IAddressActivityPresenterImpl;
import com.yusong.club.ui.school.mvp.presenter.impl.ISchoolAddressPresenterImpl;
import com.yusong.club.ui.school.school.adapter.SchoolAddressMainAdapter;
import com.yusong.club.ui.school.school.bean.AddressBean;
import com.yusong.club.ui.school.school.bean.SecondBean;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.tree.FileBean;
import com.yusong.club.utils.tree.Node;
import com.yusong.club.utils.tree.SimpleTreeAdapter;
import com.yusong.club.utils.tree.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * 校讯通页面
 */
public class AddressActivity extends BaseActivity implements ISchoolAddressView/* ExpandableListView.OnGroupExpandListener, SchoolAddressMainAdapter.OnExpandClickListener, IAddressActivityView */ {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.tv_school_name)
    TextView tvSchoolName;
    //    private ArrayList<FirstBean> mDatas = new ArrayList<FirstBean>();
    private SchoolAddressMainAdapter mAdapter;
    private ArrayList<SecondBean> secondList;
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
    //    @InjectView(R.id.expand_list)
//    ExpandableListView expandList;
    private Context mContext;
    private IAddressActivityPresenterImpl mPresenter;
    public String schoolId;
    public String schoolName;
    ArrayList<TelBookData> TelBookListDatas = new ArrayList<>();

    @InjectView(R.id.address_list)
    ListView listView;
    private List<AddressBean> list = new ArrayList<AddressBean>();
    private ISchoolAddressPresenterImpl iSchoolAddressPresenter;
    private List<FileBean> mDatas = new ArrayList<FileBean>();
    private SimpleTreeAdapter simpleTreeAdapter;
    private BaseDialog baseDialog;

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
        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        expandList = (ExpandableListView) findViewById(R.id.expand_list);
////        mAdapter = new MainAdapter(this, mDatas);
//        mAdapter = new SchoolAddressMainAdapter(this, TelBookListDatas);
//        expandList.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
//        //设置点击父控件的监听
//        expandList.setOnGroupExpandListener(this);
//        //点击最里面的菜单的点击事件
//        mAdapter.setOnChildListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_school_address;
    }

    @Override
    public void queryAddressViewSucced(List<AddressBean> datas) {
        if (mDatas.size() > 0) {
            mDatas.clear();
        }
        for (AddressBean bean : datas) {
            mDatas.add(new FileBean(bean.getId(), bean.getParentId(), bean.getOrgName(), bean.getTel()));
            Log.e("pcg", bean.getId() + ":" + bean.getParentId() + ":" + bean.getOrgName() + ":" + bean.getTel());
        }
        try {
            simpleTreeAdapter = new SimpleTreeAdapter<FileBean>(listView, this, mDatas, 0);
            listView.setAdapter(simpleTreeAdapter);
            simpleTreeAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(final Node node, int position) {
                    if (node.getMobile().length() > 0) {
                        baseDialog = new BaseDialog(AddressActivity.this);
                        baseDialog.setTitle("联系他");
                        baseDialog.setMessage("确定拨打"+node.getName()+":" + node.getMobile() + "吗?");
                        baseDialog.setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                baseDialog.dismiss();
                            }
                        });
                        baseDialog.setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent phoneIntent = new Intent();
                                phoneIntent.setAction(Intent.ACTION_CALL); // 设置动作
                                Uri data = Uri.parse("tel:" + node.getMobile()); // 设置数据
                                phoneIntent.setData(data);
                                startActivity(phoneIntent);
                                baseDialog.dismiss();
                            }
                        });
                    } else {
                        ToastUtils.showShort(AddressActivity.this, "此人信息不全");
                    }
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listSizeNull() {
        ToastUtils.showShort(AddressActivity.this, "暂无通讯信息");
    }

    @Override
    public void initView() {
        mContext = AddressActivity.this;
//        mPresenter = new IAddressActivityPresenterImpl(this, this);
        tvBack.setVisibility(View.GONE);
        imMsg.setVisibility(View.GONE);
        tvTitle.setText("校讯通");
        iSchoolAddressPresenter = new ISchoolAddressPresenterImpl(this, AddressActivity.this);
        iSchoolAddressPresenter.querySchoolAddressTwo();
    }


    @Override
    public void initData() {
        schoolId = getIntent().getStringExtra("SchoolId");
        schoolName = getIntent().getStringExtra("SchoolName");
        if (!TextUtils.isEmpty(schoolName)) {
            tvSchoolName.setText(schoolName);
        }
//        mPresenter.schoolTelBookList(CacheUtils.getToken(mContext));
    }


//    @Override
//    public void onGroupExpand(int groupPosition) {
//        Log.e("xxx", "onGroupExpand>>" + groupPosition);
//        for (int i = 0; i < mDatas.size(); i++) {
//            if (i != groupPosition) {
//                expandList.collapseGroup(i);
//            }
//        }
//    }
//
//    @Override
//    public void onclick(int parentPosition, int childPosition, int childIndex) {
//        Log.e("xxx", "点了" + "parentPosition>>" + "childPosition>>" + childPosition +
//                "childIndex>>" + childIndex);
//    }
//
//    @Override
//    public void getschoolTelBookList(final List<TelBookData> data) {
//        TelBookListDatas.clear();
//        TelBookListDatas.addAll(data);
//        mAdapter.notifyDataSetChanged();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                saveContact(data);
//            }
//        }).start();
//
//    }
//
//    private void saveContact(List<TelBookData> data) {
//        List<EaseUser> users = new ArrayList<EaseUser>();
//        UserDao dao = new UserDao(this);
//        Map<String, EaseUser> list = dao.getContactList();
//        int size = data.size();
//        for (int i = 0; i < size; i++) {
//            TelBookData bookData = data.get(i);
//            String name = bookData.getOrgName();
//            if (list.get(name) == null && bookData.getImAccounId() != null) {
//                String portrait = bookData.getIconPath();
//                EaseUser mEaseUser = new EaseUser(name);
//                mEaseUser.setNick(name);
//                mEaseUser.setAvatar(portrait);
//                users.add(mEaseUser);
//            }
//            List<TelBookData.ChildrenBean> children = bookData.getChildren();
//
//            if (AppUtils.listIsEmpty(children)) {
//
//
//                int size1 = bookData.getChildren().size();
//                for (int i1 = 0; i1 < size1; i1++) {
//                    TelBookData.ChildrenBean bean = children.get(i1);
//                    String name1 = bean.getOrgName();
//                    if (list.get(name1) == null && bean.getImAccounId() != null) {
//                        String portrait = bean.getIconPath();
//                        EaseUser mEaseUser = new EaseUser(name1);
//                        mEaseUser.setNick(name1);
//                        mEaseUser.setAvatar(portrait);
//                        users.add(mEaseUser);
//                    }
//
//                    List<TelBookData.ChildrenBean.ChildrenBean1> children1 = bean.getChildren();
//                    if (AppUtils.listIsEmpty(children1)) {
//
//                        int size2 = children1.size();
//                        for (int i2 = 0; i2 < size2; i2++) {
//
//                            TelBookData.ChildrenBean.ChildrenBean1 bean1 = children1.get(i2);
//
//                            String name2 = bean1.getOrgName();
//                            if (list.get(name2) == null && bean1.getImAccounId() != null) {
//                                String portrait = bean1.getIconPath();
//                                EaseUser mEaseUser = new EaseUser(name2);
//                                mEaseUser.setNick(name2);
//                                mEaseUser.setAvatar(portrait);
//                                users.add(mEaseUser);
//                            }
//
//                            List<TelBookData.ChildrenBean.ChildrenBean1.ChildrenBean11> children2 = bean1.getChildren();
//
//                            if (AppUtils.listIsEmpty(children2)) {
//
//                                int size3 = children2.size();
//
//                                for (int i3 = 0; i3 < size3; i3++) {
//
//                                    TelBookData.ChildrenBean.ChildrenBean1.ChildrenBean11 bean2 = children2.get(i3);
//                                    String name3 = bean2.getOrgName();
//                                    if (list.get(name3) == null && bean2.getImAccounId() != null) {
//                                        String portrait = bean2.getIconPath();
//                                        EaseUser mEaseUser = new EaseUser(name3);
//                                        mEaseUser.setNick(name3);
//                                        mEaseUser.setAvatar(portrait);
//                                        users.add(mEaseUser);
//                                    }
//
//                                    List<TelBookData.ChildrenBean.ChildrenBean1.ChildrenBean11.ChildrenBean111> children3 = bean2.getChildren();
//                                    if (AppUtils.listIsEmpty(children)) {
//
//                                        int size4 = children3.size();
//
//                                        for (int i4 = 0; i4 < size4; i4++) {
//
//                                            TelBookData.ChildrenBean.ChildrenBean1.ChildrenBean11.ChildrenBean111 bean3 = children3.get(i4);
//
//                                            String name4 = bean3.getOrgName();
//                                            if (list.get(name4) == null && bean3.getImAccounId() != null) {
//                                                String portrait = bean3.getIconPath();
//                                                EaseUser mEaseUser = new EaseUser(name4);
//                                                mEaseUser.setNick(name4);
//                                                mEaseUser.setAvatar(portrait);
//                                                users.add(mEaseUser);
//                                            }
//
//                                        }
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                }
//            }
//        }
//
//        dao.saveContactList(users);
//    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }

}
