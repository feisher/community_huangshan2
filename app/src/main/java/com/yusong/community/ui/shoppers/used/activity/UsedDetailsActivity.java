package com.yusong.community.ui.shoppers.used.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.shoppers.used.adapter.CommentAdapter;
import com.yusong.community.ui.shoppers.used.adapter.UsedImageAdapter;
import com.yusong.community.ui.shoppers.used.bean.UsedBean;
import com.yusong.community.ui.shoppers.used.bean.UsedCommentBean;
import com.yusong.community.ui.shoppers.used.mvp.implView.ImplCommentView;
import com.yusong.community.ui.shoppers.used.mvp.presenter.impl.ImplCommentPreseterImpl;
import com.yusong.community.utils.SpaceItemDecoration;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/17.
 *         描述: 二手详情页面
 */

public class UsedDetailsActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate, ImplCommentView {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.user_name_tv)
    TextView userNameTv;
    @InjectView(R.id.time_and_adderss_tv)
    TextView timeAndAdderssTv;
    @InjectView(R.id.used_content_tv)
    TextView usedContentTv;
    @InjectView(R.id.now_price_tv)
    TextView nowPriceTv;
    @InjectView(R.id.old_price_tv)
    TextView oldPriceTv;
    @InjectView(R.id.image_recyclervew)
    RecyclerView imageRecyclervew;
    @InjectView(R.id.message_recyclervew)
    RecyclerView messageRecyclervew;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.head_image)
    ImageView headImage;
    @InjectView(R.id.pinlun_button)
    Button pinlunButton;
    @InjectView(R.id.refresh_layout)
    BGARefreshLayout refreshLayout;
    @InjectView(R.id.liuyanqu_layout)
    LinearLayout liuyanquLayout;
    @InjectView(R.id.scrollview)
    ScrollView scrollview;
    @InjectView(R.id.relative_layout)
    RelativeLayout relativeLayout;

    private UsedBean bean;
    private int itemId;
    private ImplCommentPreseterImpl commentPreseterImpl;
    private List<UsedCommentBean> list = new ArrayList<UsedCommentBean>();
    private CommentAdapter commentAdapter;
    private boolean isClear = false;

    @OnClick({R.id.ll_back, R.id.rl_txt, R.id.pinlun_button})
    void allClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_txt:
                final BaseDialog dialog = new BaseDialog(this);
                dialog.setTitle("提示");
                dialog.setMessage("你确定联系卖家？" + "Tel:" + bean.getOwnerMobile());
                dialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + bean.getOwnerMobile()));
                        startActivity(phoneIntent);
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.pinlun_button:
                showDialog("pinglun", 0);
                break;
            default:
                break;

        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_used_details;
    }

    private LinearLayoutManager manager1;

    @Override
    public void initView() {
        initRefreshLayout();
        tvTitle.setText("二手详情");
        rlTxt.setVisibility(View.VISIBLE);
        tvText.setText("联系他");
        Intent intent = getIntent();
        bean = (UsedBean) intent.getSerializableExtra("UsedBean");
        if (bean != null) {
            itemId = bean.getId();
            GlideImgManager.loadCircleImage(this, bean.getPassport(), headImage);
            userNameTv.setText(bean.getNickName());
            usedContentTv.setText(bean.getIntroduction());
            nowPriceTv.setText("￥" + bean.getPrice());
            oldPriceTv.setText("原价" + bean.getShowPrice());
            LinearLayoutManager manager = new LinearLayoutManager(this) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            imageRecyclervew.setLayoutManager(manager);
            imageRecyclervew.setAdapter(new UsedImageAdapter(bean.getImageList(), this));
            imageRecyclervew.addItemDecoration(new SpaceItemDecoration(10, 0));
            refreshLayout.setLinearLayoutManager(bean.getImageList(), manager);
            manager1 = new LinearLayoutManager(this) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            commentAdapter = new CommentAdapter(list, this);
            messageRecyclervew.setLayoutManager(manager1);
            messageRecyclervew.setAdapter(commentAdapter);
            refreshLayout.setLinearLayoutManager(list, manager1);
            commentPreseterImpl = new ImplCommentPreseterImpl(this, UsedDetailsActivity.this);
            commentPreseterImpl.queryComment(itemId, 0, 15);
            commentAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, Object data, final int position) {
                    showDialog("huifu", position);
                }
            });
        }
    }

    @Override
    public void initData() {

    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        refreshLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        refreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    public void closeRefresh() {
        refreshLayout.endRefreshing();
        refreshLayout.endLoadingMore();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isClear = true;
        commentPreseterImpl.queryComment(itemId, 0, 15);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isClear = false;
        commentPreseterImpl.queryComment(itemId, offset, 15);
        return true;
    }

    private int offset;

    @Override
    public void queryCommentSucced(List<UsedCommentBean> datas) {
        if (datas.size() > 0) {
            liuyanquLayout.setVisibility(View.VISIBLE);
        }
        if (isClear) {
            offset = 0;
            if (list.size() > 0) {
                list.clear();
            }
        }
        for (UsedCommentBean comment : datas) {
            if (comment.getType() == 1) {
                ++offset;
            }
        }
        closeRefresh();
        list.addAll(datas);
        commentAdapter.notifyDataSetChanged();
        if (isComment) {
            scrollview.smoothScrollTo(0, relativeLayout.getTop());//评论成功
        }
        if (isReply) {
            scrollview.smoothScrollTo(0, scrollview.getScrollY() + 200);//留言成功
        }
        isClear = false;
        isComment = false;
        isReply = false;
    }

    @Override
    public void commentSucced() {
        isClear = true;
        isComment = true;
        commentPreseterImpl.queryComment(itemId, 0, list.size() + 2);
    }

    private boolean isComment = false;
    private boolean isReply = false;

    @Override
    public void replySucced() {
        isReply = true;
        isClear = true;
        commentPreseterImpl.queryComment(itemId, 0, list.size() + 2);
    }

    @Override
    public void close() {
        closeRefresh();
    }

    @Override
    public void showProgressDialog() {

    }

    private void showDialog(final String type, final int position) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View view = factory.inflate(R.layout.layout_with_edittext_dialog, null);
        final EditText editText = (EditText) view.findViewById(R.id.editText);//获得输入框对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (type.equals("huifu")) {
            builder.setTitle("回复");//提示框标题
            editText.setHint("请输入回复内容");
        }
        if (type.equals("pinglun")) {
            builder.setTitle("评论");//提示框标题
            editText.setHint("请输入评论内容");
        }
        builder.setView(view)
                .setPositiveButton("确定",//提示框的两个按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String content = editText.getText().toString().trim();
                                if (!TextUtils.isEmpty(content)) {
                                    if (type.equals("huifu")) {
                                        commentPreseterImpl.reply(list.get(position).getCommentId(), content);
                                    }
                                    if (type.equals("pinglun")) {
                                        commentPreseterImpl.comment(itemId, content);
                                    }
                                } else {
                                    ToastUtils.showShort(UsedDetailsActivity.this, "请输入内容后再提交" + content);
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
}
