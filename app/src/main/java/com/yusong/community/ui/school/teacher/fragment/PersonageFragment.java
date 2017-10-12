package com.yusong.community.ui.school.teacher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.easeui.domain.EaseUser;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.im.db.UserDao;
import com.yusong.community.ui.school.mvp.implView.IPersonageFragmentIView;
import com.yusong.community.ui.school.mvp.presenter.impl.IPersonageFragmentIPresenterImpl;
import com.yusong.community.ui.school.teacher.adapter.StudentAdapter;
import com.yusong.community.ui.school.teacher.adapter.TeacherAdapter;
import com.yusong.community.ui.school.teacher.bean.Student;
import com.yusong.community.ui.school.teacher.bean.Teacher;
import com.yusong.community.ui.school.teacher.bean.TelBook;
import com.yusong.community.ui.school.user.activity.UserInfoDetailsActivity;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

import static com.yusong.community.R.id.tv_teacher;

/**
 * 通讯录第一个页面
 */
public class PersonageFragment extends BaseFragment implements IPersonageFragmentIView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(tv_teacher)
    TextView tvTeacher;
    @InjectView(R.id.tv_student)
    TextView tvStudent;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private TeacherAdapter teacherAdapter;
    private StudentAdapter studentAdapter;
    private List<Teacher> teacherList;
    private List<Student> studentList;
    @InjectView(R.id.rv_teacher)
    RecyclerView rvTeacher;
    @InjectView(R.id.rv_student)
    RecyclerView rvStudent;
    private IPersonageFragmentIPresenterImpl mPresenter;
    private List<TelBook.StudentListBean> studentListBeen;
    private List<TelBook.TeacherListBean> teacherListBeen;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    private int roleFlag = 0;

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_teacher_personage, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().getInt("roleFlag") != 0) {
            roleFlag = getArguments().getInt("roleFlag");
        }
    }

    @Override
    public void initData() {
        initRefreshLayout();
        setAdapter();
    }

    @Override
    public void getBookList(TelBook data) {
        if (data != null) {
            notDataLayout.setVisibility(View.GONE);
            setBookInfos(data);
        } else {
            notDataLayout.setVisibility(View.VISIBLE);
        }
    }


    public void setBookInfos(TelBook data) {
        List<EaseUser> users = new ArrayList<EaseUser>();
        UserDao dao = new UserDao(getActivity());
        Map<String, EaseUser> list = dao.getContactList();
        if (data.getStudentList().size() > 0) {
            tvStudent.setVisibility(View.VISIBLE);
        }
        if (data.getTeacherList().size() > 0) {
            tvTeacher.setVisibility(View.VISIBLE);
        }
        studentListBeen.clear();
        List<TelBook.StudentListBean> studentList = data.getStudentList();
        studentListBeen.addAll(studentList);
        if (studentListBeen.size() == 0) {
            tvStudent.setVisibility(View.GONE);
        } else {
            studentAdapter.notifyDataSetChanged();
            int size = teacherList.size();
            for (int i = 0; i < size; i++) {
                TelBook.StudentListBean bean = studentList.get(i);
                String name = bean.getStudentName();
                if (list.get(name) == null && bean.getImAccountId() != null) {
                    EaseUser mEaseUser = new EaseUser(name);
                    mEaseUser.setNick(name);
                    users.add(mEaseUser);
                }
            }

        }
        teacherListBeen.clear();
        List<TelBook.TeacherListBean> teacherList = data.getTeacherList();
        teacherListBeen.addAll(teacherList);
        if (teacherListBeen.size() == 0) {
            tvTeacher.setVisibility(View.GONE);
        } else {
            teacherAdapter.notifyDataSetChanged();
            int size = teacherList.size();
            for (int i = 0; i < size; i++) {
                TelBook.TeacherListBean bean = teacherList.get(i);
                String name = bean.getTeacherName();
                if (name != null)
                    if (list.get(name) == null && bean.getImAccountId() != null) {
                        String portrait = bean.getPortrait();
                        EaseUser mEaseUser = new EaseUser(name);
                        mEaseUser.setNick(name);
                        mEaseUser.setAvatar(portrait);
                        users.add(mEaseUser);
                    }
            }
        }

        dao.saveContactList(users);
    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        mBGALayout.setDelegate(this);//设置代理
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        mBGALayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void initListener() {
        teacherAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                TelBook.TeacherListBean teacherListBean = (TelBook.TeacherListBean) data;

                if (teacherListBean.getTeacherName() != null) {
                    Intent mIntent = new Intent(getActivity(), UserInfoDetailsActivity.class);
                    mIntent.putExtra(ActivityConstants.NAME, teacherListBean.getTeacherName());
                    mIntent.putExtra(ActivityConstants.PORTRAIT, teacherListBean.getPortrait());
                    mIntent.putExtra(ActivityConstants.USER_TYPE, ActivityConstants.Teachar_TYPE);
                    mIntent.putExtra(ActivityConstants.USER_ID, teacherListBean.getRoleId() + "");
                    startActivity(mIntent);
                } else {
                    ToastUtils.showShort(mContext, "用户信息错误");
                }
            }
        });
        studentAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                TelBook.StudentListBean studentListBean = (TelBook.StudentListBean) data;
                Intent mIntent = new Intent(getActivity(), UserInfoDetailsActivity.class);
                mIntent.putExtra(ActivityConstants.NAME, studentListBean.getStudentName());
                mIntent.putExtra(ActivityConstants.USER_TYPE, ActivityConstants.Parent_TYPE);
                mIntent.putExtra(ActivityConstants.USER_ID, studentListBean.getRoleId() + "");
                startActivity(mIntent);

            }
        });
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.telBookList(CacheUtils.getToken(mContext));
    }

    @Override
    public void closeRefreshing() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
            mBGALayout.endLoadingMore();
        }
    }

    private void setAdapter() {
        teacherList = new ArrayList<>();
        studentListBeen = new ArrayList<>();
        teacherListBeen = new ArrayList<>();
        mPresenter = new IPersonageFragmentIPresenterImpl(this, mContext);
        mPresenter.telBookList(CacheUtils.getToken(mContext));
        rvTeacher.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        teacherAdapter = new TeacherAdapter(teacherListBeen, mContext);
        rvTeacher.setAdapter(teacherAdapter);

        studentList = new ArrayList<>();
        rvStudent.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        studentAdapter = new StudentAdapter(studentListBeen, mContext);
        rvStudent.setAdapter(studentAdapter);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
