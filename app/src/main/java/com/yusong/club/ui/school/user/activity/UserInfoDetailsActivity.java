package com.yusong.club.ui.school.user.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.im.ui.ChatActivity;
import com.yusong.club.ui.school.mvp.entity.SchoolManager;
import com.yusong.club.ui.school.mvp.entity.UserInfoDetails;
import com.yusong.club.ui.school.mvp.implView.IUserInfoDetailsView;
import com.yusong.club.ui.school.mvp.presenter.impl.UserInfoDetailsPresenterImpl;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.glide.GlideImgManager;

import org.apache.commons.lang.StringUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ***********************************************
 * **                ┏┓   ┏┓                    **
 * **                ┏┛┻━━━┛┻┓                  **
 * **                ┃       ┃                  **
 * **                ┃   ━   ┃                  **
 * **                ┃ ┳┛ ┗┳ ┃                  **
 * **                ┃       ┃                  **
 * **                ┃   ┻   ┃                  **
 * **                ┃       ┃                  **
 * **                ┗━┓   ┏━┛                  **
 * **                  ┃   ┃                    **
 * **                  ┃   ┃                    **
 * **                  ┃   ┗━━━┓                **
 * **                  ┃       ┣┓               **
 * **                  ┃       ┏┛               **
 * **                  ┗┓┓┏━┳┓┏┛                **
 * **                   ┃┫┫ ┃┫┫                 **
 * **                  ┗┻┛ ┗┻┛                  **
 * ***********************************************
 * **               神兽助我 扬我神威              **
 * ***********************************************
 * <p>
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/14 10:09 </li>
 * </ul>
 */
public class UserInfoDetailsActivity extends BaseActivity implements IUserInfoDetailsView {
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.iv_icon)
    ImageView mIvIcon;
    @InjectView(R.id.tv_iname)
    TextView mTvIname;
    @InjectView(R.id.tv_name)
    TextView mTvName;
    @InjectView(R.id.tv_phone)
    TextView mTvPhone;
    @InjectView(R.id.tv_subject)
    TextView mTvSubject;
    @InjectView(R.id.tv_position)
    TextView mTvPosition;
    @InjectView(R.id.tv_other)
    TextView mTvOther;
    @InjectView(R.id.ll_sendMsg)
    LinearLayout mLlSendMsg;
    @InjectView(R.id.ll_call)
    LinearLayout mLlCall;
    @InjectView(R.id.ll_sendMsm)
    LinearLayout mLlSendMsm;
    @InjectView(R.id.ll_subject)
    LinearLayout mLlSubject;
    @InjectView(R.id.ll_position)
    LinearLayout mLlPosition;
    private UserInfoDetailsPresenterImpl mPresenter;
    private String mName;

    private String imAccount;

    @Override
    public void showTeacharInfo(UserInfoDetails data) {
        mTvPhone.setText(data.getMobile());
        mTvSubject.setText(data.getCourerName());
        mTvPosition.setText(data.getJob());
        imAccount = data.getImAccountId();
    }

    @Override
    public void showParentInfo(UserInfoDetails data) {
        mTvPhone.setText(data.getMobile());
        imAccount = data.getImAccountId();
    }

    @Override
    public void showStudentInfo(UserInfoDetails data) {
        mTvPhone.setText(data.getTel());
        mTvPosition.setText("学生");
        imAccount = data.getImAccountId();
    }

    @Override
    public void showAdminInfo(SchoolManager data) {
        mTvPhone.setText(data.getMobile());
//        mTvSubject.setText(data.getCourerName());
//        mTvPosition.setText(data.getJob());
        imAccount = data.getImAccountId();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_school_userdetails;
    }

    @Override
    public void initData() {
        mTvTitle.setText("个人信息");
        mPresenter = new UserInfoDetailsPresenterImpl(this, this);
        Intent intent = getIntent();
        String portrait = intent.getStringExtra(ActivityConstants.PORTRAIT);
        mName = intent.getStringExtra(ActivityConstants.NAME);
        String userId = intent.getStringExtra(ActivityConstants.USER_ID);
        int userType = intent.getIntExtra(ActivityConstants.USER_TYPE, 0);
        if (StringUtils.isEmpty(mName)
                || StringUtils.isEmpty(userId) || userType == 0) {
            throw new RuntimeException(UserInfoDetailsActivity.class.getSimpleName() + "缺少必传字段或Type为0");
        }
        if (userType == ActivityConstants.Parent_TYPE) {
            mLlSubject.setVisibility(View.GONE);
            mLlPosition.setVisibility(View.GONE);
        }

        mTvIname.setText(mName);
        mTvName.setText(mName);
        if (!StringUtils.isEmpty(portrait)) {
            GlideImgManager.loadCircleImage(this, portrait, mIvIcon);
        }

        mPresenter.queryInfoDetails(userType, Integer.parseInt(userId), CacheUtils.getToken(this));
    }


    @OnClick({R.id.ll_back, R.id.ll_sendMsg, R.id.ll_call, R.id.ll_sendMsm})
    public void onClick(View view) {
        Intent intent = null;
        String phoneNumber = mTvPhone.getText().toString();
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_sendMsg:

                if (StringUtils.isEmpty(imAccount) || imAccount == null) {
                    ToastUtils.showShort(this, "该用户没有聊天权限");
                    return;
                }

                if (!StringUtils.isEmpty(mName) || !StringUtils.isEmpty(imAccount)) {

                    intent = new Intent(this, ChatActivity.class);
                    intent.putExtra("userId", imAccount);
                    intent.putExtra("userName", mName);
                    startActivity(intent);
                }

                break;
            case R.id.ll_call:

                intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);

                break;
            case R.id.ll_sendMsm:
                Uri smsToUri = Uri.parse("smsto:" + phoneNumber);
                intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(smsToUri);
                startActivity(intent);

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showProgressDialog() {

    }

}