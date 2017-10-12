package com.yusong.community.ui.school.teacher.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.mvp.implView.IScheduleActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.IScheduleActivityImpl;
import com.yusong.community.ui.school.teacher.adapter.ScheduleAdapter;
import com.yusong.community.ui.school.teacher.bean.Schedule;
import com.yusong.community.ui.school.teacher.bean.SubjectTable;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.DateUtil;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ScheduleActivity extends BaseActivity implements View.OnClickListener, IScheduleActivityView {
    @InjectView(R.id.recycle_view2)
    RecyclerView recycleView2;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_select_one)
    ImageView ivSelectOne;
    @InjectView(R.id.iv_select_two)
    ImageView ivSelectTwo;
    @InjectView(R.id.iv_select_three)
    ImageView ivSelectThree;
    @InjectView(R.id.iv_select_four)
    ImageView ivSelectFour;
    @InjectView(R.id.iv_select_five)
    ImageView ivSelectFive;
    @InjectView(R.id.iv_select_one_new)
    ImageView ivSelectOneNew;
    @InjectView(R.id.tv_xing_one)
    TextView tvXingOne;
    @InjectView(R.id.tv_date_one)
    TextView tvDateOne;
    @InjectView(R.id.tv_xing_two)
    TextView tvXingTwo;
    @InjectView(R.id.tv_date_two)
    TextView tvDateTwo;
    @InjectView(R.id.tv_xing_three)
    TextView tvXingThree;
    @InjectView(R.id.tv_date_three)
    TextView tvDateThree;
    @InjectView(R.id.tv_xing_four)
    TextView tvXingFour;
    @InjectView(R.id.tv_date_four)
    TextView tvDateFour;
    @InjectView(R.id.tv_xing_five)
    TextView tvXingFive;
    @InjectView(R.id.tv_date_five)
    TextView tvDateFive;
    @InjectView(R.id.tv_xing_six)
    TextView tvXingSix;
    @InjectView(R.id.tv_date_six)
    TextView tvDateSix;
    @InjectView(R.id.iv_select_six)
    ImageView ivSelectSix;
    @InjectView(R.id.tv_xing_seven)
    TextView tvXingSeven;
    @InjectView(R.id.tv_date_seven)
    TextView tvDateSeven;
    @InjectView(R.id.iv_ke_one)
    ImageView ivKeOne;
    @InjectView(R.id.iv_ke_two)
    ImageView ivKeTwo;
    @InjectView(R.id.iv_ke_three)
    ImageView ivKeThree;
    @InjectView(R.id.iv_ke_four)
    ImageView ivKeFour;
    @InjectView(R.id.iv_ke_five)
    ImageView ivKeFive;
    @InjectView(R.id.iv_ke_six)
    ImageView ivKeSix;
    @InjectView(R.id.iv_ke_seven)
    ImageView ivKeSeven;
    @InjectView(R.id.iv_select_seven)
    ImageView ivSelectSeven;
    @InjectView(R.id.tv_ke_one)
    TextView tvKeOne;
    @InjectView(R.id.tv_ke_two)
    TextView tvKeTwo;
    @InjectView(R.id.tv_ke_three)
    TextView tvKeThree;
    @InjectView(R.id.tv_ke_four)
    TextView tvKeFour;
    @InjectView(R.id.tv_ke_five)
    TextView tvKeFive;
    @InjectView(R.id.tv_ke_six)
    TextView tvKeSix;
    @InjectView(R.id.tv_ke_seven)
    TextView tvKeSeven;
    private ScheduleAdapter adapter;
    private ScheduleAdapter adapter2;
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
    @InjectView(R.id.recycle_view)
    RecyclerView recycleView;
    private Context mContext;
    private IScheduleActivityImpl mPresenter;
    List<Schedule> schedules;
    List<Schedule> schedules1;


    @Override
    protected void initListener() {
        linearBack.setOnClickListener(this);
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                String id = schedules.get(position).getId();
                String[] splits = id.split(",");
                clearAllCorlor();
                schedules.get(position).setTagId(true);
                switch (splits[0]) {
                    case "1":
                        setCororsImageNew(ivSelectOne);
                        setCororsTextNew(tvXingOne);
                        setCororsTextNew(tvDateOne);
                        break;
                    case "2":
                        setCororsImageNew(ivSelectTwo);
                        setCororsTextNew(tvXingTwo);
                        setCororsTextNew(tvDateTwo);
                        break;
                    case "3":
                        setCororsImageNew(ivSelectThree);
                        setCororsTextNew(tvXingThree);
                        setCororsTextNew(tvDateThree);
                        break;
                    case "4":
                        setCororsImageNew(ivSelectFour);
                        setCororsTextNew(tvXingFour);
                        setCororsTextNew(tvDateFour);
                        break;
                    case "5":
                        setCororsImageNew(ivSelectFive);
                        setCororsTextNew(tvXingFive);
                        setCororsTextNew(tvDateFive);
                        break;
                    case "6":
                        setCororsImageNew(ivSelectSix);
                        setCororsTextNew(tvXingSix);
                        setCororsTextNew(tvDateSix);
                        break;
                    case "7":
                        setCororsImageNew(ivSelectSeven);
                        setCororsTextNew(tvXingSeven);
                        setCororsTextNew(tvDateSeven);
                        break;

                }
                switch (splits[1]) {
                    case "1":
                        setCororsImageNew(ivKeOne);
                        setCororsTextNew(tvKeOne);
                        break;
                    case "2":
                        setCororsImageNew(ivKeTwo);
                        setCororsTextNew(tvKeTwo);
                        break;
                    case "3":
                        setCororsImageNew(ivKeThree);
                        setCororsTextNew(tvKeThree);
                        break;
                    case "4":
                        setCororsImageNew(ivKeFour);
                        setCororsTextNew(tvKeFour);
                        break;
                }
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });
        adapter2.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                String id = schedules1.get(position).getId();
                String[] splits = id.split(",");
                clearAllCorlor();
                schedules1.get(position).setTagId(true);
                switch (splits[0]) {
                    case "1":
                        setCororsImageNew(ivSelectOne);
                        setCororsTextNew(tvXingOne);
                        setCororsTextNew(tvDateOne);
                        break;
                    case "2":
                        setCororsImageNew(ivSelectTwo);
                        setCororsTextNew(tvXingTwo);
                        setCororsTextNew(tvDateTwo);
                        break;
                    case "3":
                        setCororsImageNew(ivSelectThree);
                        setCororsTextNew(tvXingThree);
                        setCororsTextNew(tvDateThree);
                        break;
                    case "4":
                        setCororsImageNew(ivSelectFour);
                        setCororsTextNew(tvXingFour);
                        setCororsTextNew(tvDateFour);
                        break;
                    case "5":
                        setCororsImageNew(ivSelectFive);
                        setCororsTextNew(tvXingFive);
                        setCororsTextNew(tvDateFive);
                        break;
                    case "6":
                        setCororsImageNew(ivSelectSix);
                        setCororsTextNew(tvXingSix);
                        setCororsTextNew(tvDateSix);
                        break;
                    case "7":
                        setCororsImageNew(ivSelectSeven);
                        setCororsTextNew(tvXingSeven);
                        setCororsTextNew(tvDateSeven);
                        break;
                }
                switch (splits[1]) {
                    case "1":
                        setCororsImageNew(ivKeFive);
                        setCororsTextNew(tvKeFive);
                        break;
                    case "2":
                        setCororsImageNew(ivKeSix);
                        setCororsTextNew(tvKeSix);
                        break;
                    case "3":
                        setCororsImageNew(ivKeSeven);
                        setCororsTextNew(tvKeSeven);
                        break;
                }
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });
    }

    private void clearAllCorlor() {
        setCororsImage(ivSelectOne);
        setCororsImage(ivSelectTwo);
        setCororsImage(ivSelectThree);
        setCororsImage(ivSelectFour);
        setCororsImage(ivSelectFive);
        setCororsImage(ivSelectSix);
        setCororsImage(ivSelectSeven);

        setCororsText(tvXingOne);
        setCororsText(tvDateOne);
        setCororsText(tvXingTwo);
        setCororsText(tvDateTwo);
        setCororsText(tvXingThree);
        setCororsText(tvDateThree);
        setCororsText(tvXingFour);
        setCororsText(tvDateFour);
        setCororsText(tvXingFive);
        setCororsText(tvDateFive);
        setCororsText(tvXingSix);
        setCororsText(tvDateSix);
        setCororsText(tvXingSeven);
        setCororsText(tvDateSeven);


        setCororsImage(ivKeOne);
        setCororsImage(ivKeTwo);
        setCororsImage(ivKeThree);
        setCororsImage(ivKeFour);
        setCororsImage(ivKeFive);
        setCororsImage(ivKeSix);
        setCororsImage(ivKeSeven);

        setCororsText(tvKeOne);
        setCororsText(tvKeTwo);
        setCororsText(tvKeThree);
        setCororsText(tvKeFour);
        setCororsText(tvKeFive);
        setCororsText(tvKeSix);
        setCororsText(tvKeSeven);

        for (int i = 0; i < schedules.size(); i++) {
            schedules.get(i).setTagId(false);
        }
        for (int i = 0; i < schedules1.size(); i++) {
            schedules1.get(i).setTagId(false);
        }
    }

    public void setCororsText(TextView tv) {
        tv.setTextColor(Color.parseColor("#888888"));


    }

    public void setCororsTextNew(TextView tv) {
        tv.setTextColor(Color.parseColor("#FF9001"));


    }

    public void setCororsImage(ImageView tv) {
        tv.setBackgroundColor(Color.parseColor("#888888"));


    }

    public void setCororsImageNew(ImageView tv) {
        tv.setBackgroundColor(Color.parseColor("#FF9001"));


    }

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher_schedulea;
    }

    @Override
    public void initView() {
        if (getIntent() != null && getIntent().getSerializableExtra("SchoolName") != null) {
            tvTitle.setText((String) getIntent().getSerializableExtra("SchoolName") + "");
        }
        schedules = new ArrayList<>();
        imMsg.setVisibility(View.GONE);
        mContext = ScheduleActivity.this;
        schedules1 = new ArrayList<>();
        mPresenter = new IScheduleActivityImpl(this, this);
        mPresenter.timeTableList(CacheUtils.getToken(mContext));
        tvDateOne.setText(DateUtil.getHidatWeek(DateUtil.getWorkDay(0)));
        tvDateTwo.setText(DateUtil.getHidatWeek(DateUtil.getWorkDay(1)));
        tvDateThree.setText(DateUtil.getHidatWeek(DateUtil.getWorkDay(2)));
        tvDateFour.setText(DateUtil.getHidatWeek(DateUtil.getWorkDay(3)));
        tvDateFive.setText(DateUtil.getHidatWeek(DateUtil.getWorkDay(4)));
        tvDateSix.setText(DateUtil.getHidatWeek(DateUtil.getWorkDay(5)));
        tvDateSeven.setText(DateUtil.getHidatWeek(DateUtil.getWorkDay(6)));

    }


    @Override
    public void initData() {
        //条目间距
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ScheduleActivity.this, 7);
        //给recyclerView设置间距
        recycleView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recycleView.setLayoutManager(gridLayoutManager);
        adapter = new ScheduleAdapter(schedules, this);
        recycleView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 7);
        //给recyclerView设置间距
        recycleView2.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recycleView2.setLayoutManager(gridLayoutManager2);
        adapter2 = new ScheduleAdapter(schedules1, this);
        recycleView2.setAdapter(adapter2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
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
            case R.id.linear_back:
                finish();
                break;
        }
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = space;
            outRect.bottom = space;

            //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) % 7 == 0) {
                outRect.left = 0;
            }
        }


    }

    @Override
    public void getAllTable(List<SubjectTable> data) {
        if (data != null && data.size() != 0) {
            initDataInfo(data);
        } else {
            initNoDataInfo();
        }
    }

    private void initNoDataInfo() {
        List<String> strs = new ArrayList<>();
        List<String> strs1 = new ArrayList<>();
        List<String> listOne = new ArrayList<>();
        List<String> listTwo = new ArrayList<>();
        List<String> listThree = new ArrayList<>();
        List<String> listFour = new ArrayList<>();
        List<String> listFive = new ArrayList<>();
        List<String> listSix = new ArrayList<>();
        List<String> listSeven = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            listOne.add("暂无");
            listTwo.add("暂无");
            listThree.add("暂无");
            listFour.add("暂无");
        }
        for (int j = 0; j < 7; j++) {
            listFive.add("暂无");
            listSix.add("暂无");
            listSeven.add("暂无");
        }
        strs.addAll(listOne);
        strs.addAll(listTwo);
        strs.addAll(listThree);
        strs.addAll(listFour);
        strs1.addAll(listFive);
        strs1.addAll(listSix);
        strs1.addAll(listSeven);
        Schedule schedule = null;
        for (int j = 0; j < strs.size(); j++) {
            schedule = new Schedule();
            schedule.setSubject(strs.get(j));
            schedules.add(schedule);
        }
        for (int j = 0; j < strs1.size(); j++) {
            schedule = new Schedule();
            schedule.setSubject(strs1.get(j));
            schedules1.add(schedule);
        }

        int a = 1, b = 1, c = 1, d = 1, e = 1, f = 1, g = 1;
        for (int i = 0; i < schedules.size(); i++) {
            if (i % 7 == 0) {
                schedules.get(i).setId(1 + "," + a);
                a++;
            } else if (i % 7 == 1) {
                schedules.get(i).setId(2 + "," + b);
                b++;
            } else if (i % 7 == 2) {
                schedules.get(i).setId(3 + "," + c);
                c++;
            } else if (i % 7 == 3) {
                schedules.get(i).setId(4 + "," + d);
                d++;
            } else if (i % 7 == 4) {
                schedules.get(i).setId(5 + "," + e);
                e++;
            } else if (i % 7 == 5) {
                schedules.get(i).setId(6 + "," + f);
                f++;
            } else if (i % 7 == 6) {
                schedules.get(i).setId(7 + "," + g);
                g++;
            }
        }
        a = 1;
        b = 1;
        c = 1;
        d = 1;
        e = 1;
        f = 1;
        g = 1;
        for (int i = 0; i < schedules1.size(); i++) {
            if (i % 7 == 0) {
                schedules1.get(i).setId(1 + "," + a);
                a++;
            } else if (i % 7 == 1) {
                schedules1.get(i).setId(2 + "," + b);
                b++;
            } else if (i % 7 == 2) {
                schedules1.get(i).setId(3 + "," + c);
                c++;
            } else if (i % 7 == 3) {
                schedules1.get(i).setId(4 + "," + d);
                d++;
            } else if (i % 7 == 4) {
                schedules1.get(i).setId(5 + "," + e);
                e++;
            } else if (i % 7 == 5) {
                schedules1.get(i).setId(6 + "," + f);
                f++;
            } else if (i % 7 == 6) {
                schedules1.get(i).setId(7 + "," + g);
                g++;
            }
        }
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }

    private void initDataInfo(List<SubjectTable> data) {
        List<String> strs = new ArrayList<>();
        List<String> strs1 = new ArrayList<>();
        List<String> listOne = new ArrayList<>();
        List<String> listTwo = new ArrayList<>();
        List<String> listThree = new ArrayList<>();
        List<String> listFour = new ArrayList<>();
        List<String> listFive = new ArrayList<>();
        List<String> listSix = new ArrayList<>();
        List<String> listSeven = new ArrayList<>();
        for (SubjectTable subjectTable : data) {
            if (!StringUtils.isEmpty(subjectTable.getCourse1())) {
                listOne.add(subjectTable.getCourse1());
            } else {
                listOne.add("暂无");
            }
            if (!StringUtils.isEmpty(subjectTable.getCourse2())) {
                listTwo.add(subjectTable.getCourse2());
            } else {
                listTwo.add("暂无");
            }
            if (!StringUtils.isEmpty(subjectTable.getCourse3())) {
                listThree.add(subjectTable.getCourse3());
            } else {
                listThree.add("暂无");
            }
            if (!StringUtils.isEmpty(subjectTable.getCourse4())) {
                listFour.add(subjectTable.getCourse4());
            } else {
                listFour.add("暂无");
            }
            if (!StringUtils.isEmpty(subjectTable.getCourse5())) {
                listFive.add(subjectTable.getCourse5());
            } else {
                listFive.add("暂无");
            }
            if (!StringUtils.isEmpty(subjectTable.getCourse6())) {
                listSix.add(subjectTable.getCourse6());
            } else {
                listSix.add("暂无");
            }
            if (!StringUtils.isEmpty(subjectTable.getCourse7())) {
                listSeven.add(subjectTable.getCourse7());
            } else {
                listSeven.add("暂无");
            }
        }
        strs.addAll(listOne);
        strs.addAll(listTwo);
        strs.addAll(listThree);
        strs.addAll(listFour);
        strs1.addAll(listFive);
        strs1.addAll(listSix);
        strs1.addAll(listSeven);
        Schedule schedule = null;
        for (int j = 0; j < strs.size(); j++) {
            schedule = new Schedule();
            schedule.setSubject(strs.get(j));
            schedules.add(schedule);
        }
        for (int j = 0; j < strs1.size(); j++) {
            schedule = new Schedule();
            schedule.setSubject(strs1.get(j));
            schedules1.add(schedule);
        }

        int a = 1, b = 1, c = 1, d = 1, e = 1, f = 1, g = 1;
        for (int i = 0; i < schedules.size(); i++) {
            if (i % 7 == 0) {
                schedules.get(i).setId(1 + "," + a);
                a++;
            } else if (i % 7 == 1) {
                schedules.get(i).setId(2 + "," + b);
                b++;
            } else if (i % 7 == 2) {
                schedules.get(i).setId(3 + "," + c);
                c++;
            } else if (i % 7 == 3) {
                schedules.get(i).setId(4 + "," + d);
                d++;
            } else if (i % 7 == 4) {
                schedules.get(i).setId(5 + "," + e);
                e++;
            } else if (i % 7 == 5) {
                schedules.get(i).setId(6 + "," + f);
                f++;
            } else if (i % 7 == 6) {
                schedules.get(i).setId(7 + "," + g);
                g++;
            }
        }
        a = 1;
        b = 1;
        c = 1;
        d = 1;
        e = 1;
        f = 1;
        g = 1;
        for (int i = 0; i < schedules1.size(); i++) {
            if (i % 7 == 0) {
                schedules1.get(i).setId(1 + "," + a);
                a++;
            } else if (i % 7 == 1) {
                schedules1.get(i).setId(2 + "," + b);
                b++;
            } else if (i % 7 == 2) {
                schedules1.get(i).setId(3 + "," + c);
                c++;
            } else if (i % 7 == 3) {
                schedules1.get(i).setId(4 + "," + d);
                d++;
            } else if (i % 7 == 4) {
                schedules1.get(i).setId(5 + "," + e);
                e++;
            } else if (i % 7 == 5) {
                schedules1.get(i).setId(6 + "," + f);
                f++;
            } else if (i % 7 == 6) {
                schedules1.get(i).setId(7 + "," + g);
                g++;
            }
        }
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }
}
