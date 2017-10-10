package com.yusong.club.ui.community.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.community.event.PostsCommentEvent;
import com.yusong.club.ui.community.mvp.entity.PostComment;
import com.yusong.club.ui.community.mvp.implView.PostDetailActivityView;
import com.yusong.club.ui.community.mvp.presenter.impl.PostDetailActivityPresenterIpml;
import com.yusong.club.utils.AndroidBug5497Workaround;
import com.yusong.club.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017/6/8 15:28.
 * @describe: 评论输入样式
 */

public class InputActivity extends BaseActivity implements PostDetailActivityView {
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.btn_send)
    Button btnSend;
    @InjectView(R.id.comment_layout)
    RelativeLayout commentLayout;
    private InputMethodManager inputManager;
    public PostDetailActivityPresenterIpml postIpml; //评论请求
    private int postId = 0;

    @OnClick({R.id.et_content, R.id.btn_send, R.id.comment_layout})
    void onClick(View v) {
        inputManager.hideSoftInputFromWindow(etContent.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        switch (v.getId()) {
            case R.id.btn_send:
                String commtent = etContent.getText().toString().trim();
                if (TextUtils.isEmpty(commtent)) {
                    ToastUtils.showShort(this, "内容不能为空");
                    return;
                }
                postIpml.createPostComment(postId, commtent);
                break;
            case R.id.comment_layout:
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_input_layout;
    }

    @Override
    public void initView() {
        super.initView();
        AndroidBug5497Workaround.assistActivity(this);
        inputManager = (InputMethodManager) etContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        postIpml = new PostDetailActivityPresenterIpml(this, this);
        postId = getIntent().getIntExtra("postId", 0);
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

    @Override
    public void setCommentsAdapter(List<PostComment> data) {

    }

    @Override
    public void commentSucceedCallback() {
        EventBus.getDefault().post(new PostsCommentEvent(0));// 参数0无用
        this.finish();
    }

    @Override
    public void closeRefresh() {

    }

    @Override
    public void showProgressDialog() {

    }
}
