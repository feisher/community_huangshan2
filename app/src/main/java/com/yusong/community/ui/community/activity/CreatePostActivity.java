package com.yusong.community.ui.community.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.community.adapter.CreatePostCategorysAdapter;
import com.yusong.community.ui.community.adapter.CreatePostImagesAdapter;
import com.yusong.community.ui.community.event.CreatePostEvent;
import com.yusong.community.ui.community.mvp.entity.PostsCatogrey;
import com.yusong.community.ui.community.mvp.implView.CreatePostActivityView;
import com.yusong.community.ui.community.mvp.presenter.impl.CreatePostActivityPresenterImpl;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.SpaceItemDecoration;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * create by feisher on 2017/3/2 13:56
 * Email：458079442@qq.com
 */
public class CreatePostActivity extends BaseActivity implements CreatePostActivityView {

    RequestBody NO_DATA;

    //    String[] mCategorysDatas = {"谈天说地","房屋租售","生活服务","二手交易","特色旅游"};
    List<String> mImagesDatasList = new ArrayList<>();
    List<PostsCatogrey> mCategorysDatasList = new ArrayList<>();
    public CreatePostCategorysAdapter mCreatePostCategorysAdapter;
    public CreatePostImagesAdapter mCreatePostImagesAdapter;
    public CreatePostActivityPresenterImpl mPresenter;
    public int categoryId;
    public int customerId;
    public String content;
    public String token;
    public Context mContext;
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
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.iv_select_img)
    ImageView ivSelectImg;
    @InjectView(R.id.rv_imgs)
    RecyclerView rvImgs;
    @InjectView(R.id.rv_categorys)
    RecyclerView rvCategorys;
    @InjectView(R.id.activity_create_post)
    LinearLayout activityCreatePost;
    // 自定义图片加载器
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };
    public ImgSelConfig config;
    int ZIP_WIDTH = 480;
    int ZIP_HEIGHT = 480;
    public int postCategoryIndex;


    @Override
    protected void initListener() {
        mCreatePostCategorysAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                categoryId = mCategorysDatasList.get(position).getId();
                postCategoryIndex = position;
                mCreatePostCategorysAdapter.notifyDataSetChanged();
            }
        });
        mCreatePostImagesAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                ImgSelActivity.startActivity(CreatePostActivity.this, config, 0);
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_create_post;
    }

    @Override
    public void initView() {
        mContext = CreatePostActivity.this;
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("发布/求助");
        rlTxt.setVisibility(View.VISIBLE);
        tvText.setText("发布");
        initImgSelectLayout();
        initCategorySelectLayout();
        initImageSelector();
        NO_DATA = getRequestBody("");
    }

    @NonNull
    private RequestBody getRequestBody(String s) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), s);
    }


    private void initCategorySelectLayout() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        mCreatePostCategorysAdapter = new CreatePostCategorysAdapter(mCategorysDatasList, this);
        rvCategorys.setAdapter(mCreatePostCategorysAdapter);
        rvCategorys.addItemDecoration(new SpaceItemDecoration(1, 1));
        rvCategorys.setLayoutManager(linearLayoutManager);
    }

    private void initImgSelectLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mCreatePostImagesAdapter = new CreatePostImagesAdapter(mImagesDatasList, this);
        rvImgs.setAdapter(mCreatePostImagesAdapter);
        rvImgs.addItemDecoration(new SpaceItemDecoration(1, 1));
        rvImgs.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void initData() {
        ArrayList<PostsCatogrey> posts_category = getIntent().getExtras().getParcelableArrayList("POSTS_CATEGORY");
        mCategorysDatasList.clear();
        if (null != posts_category) {
            mCategorysDatasList.addAll(posts_category);
            mCreatePostCategorysAdapter.notifyDataSetChanged();
            if (posts_category.size() > 0) {
                categoryId = mCategorysDatasList.get(0).getId();
            }
        }
        mPresenter = new CreatePostActivityPresenterImpl(this, CreatePostActivity.this);
        token = CacheUtils.getToken(this);
        customerId = CacheUtils.getId(this);

        if (mImagesDatasList.size() == 0) {
            rlTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    content = etContent.getText().toString().trim();
                    mPresenter.createPost(getRequestBody(token), getRequestBody(categoryId + ""), getRequestBody(content));
                }
            });
        }
    }


    @OnClick({R.id.ll_back, R.id.iv_select_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_select_img://点击跳转图片选择界mian
                ImgSelActivity.startActivity(this, config, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data); // 图片选择结果回调
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (pathList.size() != 0) {
                ivSelectImg.setVisibility(View.GONE);
                rvImgs.setVisibility(View.VISIBLE);
            } else {
                ivSelectImg.setVisibility(View.VISIBLE);
                rvImgs.setVisibility(View.GONE);
            }
            mImagesDatasList.clear();
            for (String path : pathList) {
                mImagesDatasList.add(path);
            }
            mCreatePostImagesAdapter.notifyDataSetChanged();
            if (mImagesDatasList.size() == 1) {
                File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(0)));
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
                rlTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        content = etContent.getText().toString().trim();
                        mPresenter.createPost(getRequestBody(token), getRequestBody(categoryId + ""), getRequestBody(content), image1);
                    }
                });
            } else if (mImagesDatasList.size() == 2) {
                File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(0)));
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
                File file1 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(1)));
                RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpg"), file1);
                final MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file1.getName(), requestFile1);
                rlTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        content = etContent.getText().toString().trim();
                        mPresenter.createPost(getRequestBody(token), getRequestBody(categoryId + ""), getRequestBody(content), image1, image2);
                    }
                });
            } else if (mImagesDatasList.size() == 3) {
                File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(0)));
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
                File file1 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(1)));
                RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpg"), file1);
                final MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file1.getName(), requestFile1);
                File file2 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(2)));
                RequestBody requestFile2 = RequestBody.create(MediaType.parse("image/jpg"), file2);
                final MultipartBody.Part image3 = MultipartBody.Part.createFormData("image3", file2.getName(), requestFile2);
                rlTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        content = etContent.getText().toString().trim();
                        mPresenter.createPost(getRequestBody(token), getRequestBody(categoryId + ""), getRequestBody(content), image1, image2, image3);
                    }
                });
            } else if (mImagesDatasList.size() == 4) {
                File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(0)));
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
                File file1 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(1)));
                RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpg"), file1);
                final MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file1.getName(), requestFile1);
                File file2 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(2)));
                RequestBody requestFile2 = RequestBody.create(MediaType.parse("image/jpg"), file2);
                final MultipartBody.Part image3 = MultipartBody.Part.createFormData("image3", file2.getName(), requestFile2);
                File file3 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(3)));
                RequestBody requestFile3 = RequestBody.create(MediaType.parse("image/jpg"), file3);
                final MultipartBody.Part image4 = MultipartBody.Part.createFormData("image4", file3.getName(), requestFile3);
                rlTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        content = etContent.getText().toString().trim();
                        mPresenter.createPost(getRequestBody(token), getRequestBody(categoryId + ""), getRequestBody(content), image1, image2, image3, image4);
                    }
                });
            }
        } else {
            rlTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    content = etContent.getText().toString().trim();
                    mPresenter.createPost(getRequestBody(token), getRequestBody(categoryId + ""), getRequestBody(content));
                }
            });
        }

    }

    private void initImageSelector() {
        config = new ImgSelConfig.Builder(this, loader)
                .multiSelect(true)
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

    /**
     * 提交(创建)帖子回调
     */
    @Override
    public void createPostSucceedCallback() {
        final BaseDialog dialog = new BaseDialog(this);
        dialog.setTitle("发布成功");
        dialog.setMessage("待系统审核后，自动显示到社区平台！");
        dialog.setPositiveButton("知道了", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new CreatePostEvent(postCategoryIndex, categoryId));
                dialog.dismiss();
                finish();
            }
        });
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
