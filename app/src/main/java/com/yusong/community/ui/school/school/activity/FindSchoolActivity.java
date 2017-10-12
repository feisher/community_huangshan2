package com.yusong.community.ui.school.school.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.mvp.entity.Role;
import com.yusong.community.ui.school.mvp.entity.School;
import com.yusong.community.ui.school.school.adapter.FindAdapter;
import com.yusong.community.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-08-01.
 * @describe: null
 */

public class FindSchoolActivity extends BaseActivity {
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
    @InjectView(R.id.school_find_et)
    EditText schoolFindEt;
    @InjectView(R.id.find_result_list)
    RecyclerView findResultList;
    private FindAdapter adapter;
    public static final int RESULT_CODE = 1001;

    private List<School> list = new ArrayList<School>();
    private List<School> schoolList = new ArrayList<School>();
    public List<Role.RoleListBean> multiSchoolRoleDatas;

    @OnClick(R.id.ll_back)
    void backClick() {
        this.finish();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_school_find;
    }


    @Override
    public void initView() {
        schoolList = (List<School>) getIntent().getExtras().getSerializable("multiSchoolRoleDatas");
        tvTitle.setText("学校搜索");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new FindAdapter(list, this);
        findResultList.setLayoutManager(linearLayoutManager);
        findResultList.setAdapter(adapter);
        findResultList.addItemDecoration(new SpaceItemDecoration(2, 8));
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent();
                int selection = 0;
                School school = list.get(position);
                for (int i=0;i<schoolList.size();i++){
                    if(school.getId()==schoolList.get(i).getId()&&school.getSchoolName().equals(schoolList.get(i).getSchoolName())){
                        selection = i;
                    }
                }
                intent.putExtra("school", school);
                intent.putExtra("selection", selection);
                setResult(RESULT_CODE, intent);
                finish();
            }
        });
        schoolFindEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() == 0) {
                    findResultList.setVisibility(View.GONE);
                } else {
                    findResultList.setVisibility(View.VISIBLE);
                    findList(charSequence.toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void findList(String conStr) {
        if (list.size() > 0) {
            list.clear();
        }
        for (School school : schoolList) {
            if (school.getSchoolName().indexOf(conStr) != -1) {
                list.add(school);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
