package com.yusong.club.ui.school.school.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.charge.view.BaseDialog;
import com.yusong.club.ui.school.mvp.entity.TelBookData;
import com.yusong.club.ui.school.mvp.implView.ISchoolAddressView;
import com.yusong.club.ui.school.mvp.presenter.impl.AddressFragmentPresenterImpl;
import com.yusong.club.ui.school.mvp.presenter.impl.ISchoolAddressPresenterImpl;
import com.yusong.club.ui.school.school.activity.SchoolActivity;
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
 * 校讯通
 */
public class AddressFragment extends BaseFragment implements ISchoolAddressView/*,IAddressFragmentView, ExpandableListView.OnGroupExpandListener, SchoolAddressMainAdapter.OnExpandClickListener*/ {
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
    @InjectView(R.id.address_list)
    ListView listView;
    //    @InjectView(R.id.expand_list)
//    ExpandableListView expandList;

    public String schoolName;
    public String schoolId;
    public AddressFragmentPresenterImpl mPresenter;
    ArrayList<TelBookData> TelBookListDatas = new ArrayList<>();
    private List<AddressBean> list = new ArrayList<AddressBean>();

    private ISchoolAddressPresenterImpl iSchoolAddressPresenter;
    private List<FileBean> mDatas = new ArrayList<FileBean>();
    private SimpleTreeAdapter simpleTreeAdapter;


    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.activity_school_address, null);
    }

    @Override
    public void initData() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }
        linearBack.setVisibility(View.GONE);
        tvBack.setVisibility(View.GONE);
        tvTitle.setText("校讯通");
        imMsg.setVisibility(View.GONE);
        SchoolActivity activity = (SchoolActivity) getActivity();
        schoolName = activity.getIntent().getStringExtra("SchoolName");
        schoolId = activity.getIntent().getStringExtra("SchoolId");
        tvSchoolName.setText(schoolName);
        //联网请求通讯录
//        mPresenter = new AddressFragmentPresenterImpl(this, mContext);
//        mPresenter.schoolTelBookList(CacheUtils.getToken(mContext));
//        fillDataOffline();
//        try {
//            simpleTreeAdapter = new SimpleTreeAdapter<FileBean>(listView, getActivity(), mDatas, 0);
//            listView.setAdapter(simpleTreeAdapter);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        iSchoolAddressPresenter = new ISchoolAddressPresenterImpl(this, getActivity());
        iSchoolAddressPresenter.querySchoolAddressTwo();
    }

    @Override
    public void showProgressDialog() {

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
            simpleTreeAdapter = new SimpleTreeAdapter<FileBean>(listView, getActivity(), mDatas, 0);
            listView.setAdapter(simpleTreeAdapter);
            simpleTreeAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(final Node node, int position) {
                    if (node.getMobile().length() > 0) {
                        final BaseDialog  baseDialog = new BaseDialog(getActivity());
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
                                Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + node.getMobile()));
                                startActivity(phoneIntent);
                                baseDialog.dismiss();
                            }
                        });
                    } else {
                        ToastUtils.showShort(getActivity(), "此人信息不全");
                    }
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listSizeNull() {
        ToastUtils.showShort(getActivity(), "暂无通讯信息");
    }
//    private void fillDataOffline() {
//        for (int i = 0; i < 4; i++) {
//            FirstBean firstBean = new FirstBean();
//            secondList = new ArrayList<SecondBean>();
//            if (i == 0) {
//                firstBean.setTitle("校长室");
//                SecondBean secondBean = new SecondBean();
//                secondBean.setTitle("办公室");
//                ArrayList<ThirdBean> thirdList = new ArrayList<ThirdBean>();
//                ThirdBean thirdBean = new ThirdBean();
//                thirdBean.setTitle("李峰");
//                thirdBean.setMobile("13012341234");
//                thirdList.add(thirdBean);
//                secondBean.setSecondBean(thirdList);
//                secondList.add(secondBean);
//
//                firstBean.setFirstData(secondList);
//                mDatas.add(firstBean);
//
//            } else if (i == 1) {
//                firstBean.setTitle("一年级教导处");
//                expendData("一");
//                firstBean.setFirstData(secondList);
//                mDatas.add(firstBean);
//            } else if (i == 2) {
//                firstBean.setTitle("二年级教导处");
//                expendData("二");
//                firstBean.setFirstData(secondList);
//                mDatas.add(firstBean);
//            } else if (i == 3) {
//                firstBean.setTitle("三年级教导处");
//                expendData("三");
//                firstBean.setFirstData(secondList);
//                mDatas.add(firstBean);
//            }
//        }
//    }

//    @Override
//    public void initListener() {
//        linearBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().finish();
//            }
//        });
//
////        mAdapter = new MainAdapter(getActivity(), mDatas);
//        mAdapter = new SchoolAddressMainAdapter(getContext(), TelBookListDatas);
//        expandList.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
//        //设置点击父控件的监听
//        expandList.setOnGroupExpandListener(this);
//        //点击最里面的菜单的点击事件
//        mAdapter.setOnChildListener(this);
//    }


//    @Override
//    public void onGroupExpand(int groupPosition) {
//        Log.e("xxx", "onGroupExpand>>" + groupPosition);
//        for (int i = 0; i < mDatas.size(); i++) {
//            if (i != groupPosition) {
//                expandList.collapseGroup(i);
//            }
//        }
//    }

//    @Override
//    public void onclick(int parentPosition, int childPosition, int childIndex) {
//        Log.e("xxx", "点了" + "parentPosition>>" + "childPosition>>" + childPosition +
//                "childIndex>>" + childIndex);
//    }
//
//    private void expendData(String group) {
//        for (int j = 0; j < 3; j++) {
//            if (j == 0) {
//                SecondBean secondBean = new SecondBean();
//                secondBean.setTitle("教导处");
//                ArrayList<ThirdBean> thirdList = new ArrayList<ThirdBean>();
//                ThirdBean thirdBean = new ThirdBean();
//                thirdBean.setTitle("李峰");
//                thirdBean.setMobile("13012341234");
//                thirdList.add(thirdBean);
//
//                secondBean.setSecondBean(thirdList);
//                secondList.add(secondBean);
//            } else {
//                SecondBean secondBean = new SecondBean();
//                secondBean.setTitle(group + "年级" + j + "班");
//                ArrayList<ThirdBean> thirdList = new ArrayList<ThirdBean>();
//                for (int k = 0; k < 2; k++) {
//                    ThirdBean thirdBean = new ThirdBean();
//                    thirdBean.setTitle("李峰");
//                    thirdBean.setMobile("13012341234");
//                    thirdList.add(thirdBean);
//                }
//                secondBean.setSecondBean(thirdList);
//                secondList.add(secondBean);
//            }
//        }
//    }

//    @Override
//    public void getschoolTelBookListCallback(List<TelBookData> data) {
//        TelBookListDatas.clear();
//        TelBookListDatas.addAll(data);
//        mAdapter.notifyDataSetChanged();
//    }


}
