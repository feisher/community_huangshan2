package com.yusong.community.ui.repairs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.community.adapter.CreatePostImagesAdapter;
import com.yusong.community.ui.repairs.mvp.ImplView.CreateRepairsView;
import com.yusong.community.ui.repairs.mvp.persenter.impl.CreateRepairPresenterImpl;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.SpaceItemDecoration;
import com.yusong.community.utils.ToastUtils;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

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
 * @author Mr_Peng
 * @created at 2017-08-24.
 * @describe: 报修报事
 */

public class RepairsActivity extends BaseActivity implements CreateRepairsView {

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
    @InjectView(R.id.tel_tv)
    TextView telTv;
    @InjectView(R.id.fangwu_image)
    ImageView fangwuImage;
    @InjectView(R.id.fangwu_layout)
    LinearLayout fangwuLayout;
    @InjectView(R.id.weixiu_image)
    ImageView weixiuImage;
    @InjectView(R.id.weixiu_layout)
    LinearLayout weixiuLayout;
    @InjectView(R.id.baojie_image)
    ImageView baojieImage;
    @InjectView(R.id.baojie_layout)
    LinearLayout baojieLayout;
    @InjectView(R.id.lvhua_image)
    ImageView lvhuaImage;
    @InjectView(R.id.lvlua_layout)
    LinearLayout lvluaLayout;
    @InjectView(R.id.yezhu_tv)
    TextView yezhuTv;
    @InjectView(R.id.phone_tv)
    TextView phoneTv;
    @InjectView(R.id.address_tv)
    TextView addressTv;
    @InjectView(R.id.memo_et)
    EditText memoEt;
    @InjectView(R.id.iv_select_img)
    ImageView ivSelectImg;
    @InjectView(R.id.rv_imgs)
    RecyclerView rvImgs;
    @InjectView(R.id.confirm_button)
    Button confirmButton;
    private int selectPosition = 1;
    public String token;
    public Context mContext;
    private CreateRepairPresenterImpl presenter;
    private String memo;
    private int proprietorId;
    private String proprietorName;
    private String proprietorMobile;
    private String proprietorAddress;


    @OnClick({R.id.ll_back, R.id.fangwu_layout, R.id.weixiu_layout,
            R.id.baojie_layout, R.id.lvlua_layout, R.id.rl_txt,
            R.id.iv_select_img})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.fangwu_layout:
                selectView(R.id.fangwu_layout);
                break;
            case R.id.weixiu_layout:
                selectView(R.id.weixiu_layout);
                break;
            case R.id.baojie_layout:
                selectView(R.id.baojie_layout);
                break;
            case R.id.lvlua_layout:
                selectView(R.id.lvlua_layout);
                break;
            case R.id.iv_select_img:
                ImgSelActivity.startActivity(this, config, 0);
                break;
            case R.id.rl_txt://历史
                startActivity(new Intent(this, RepairsHistoryActivity.class));
                break;

        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_repairs;
    }

    @Override
    public void initView() {
        mContext = RepairsActivity.this;
        tvTitle.setText("报修/报事");
        rlTxt.setVisibility(View.VISIBLE);
        tvText.setText("历史");
        telTv.setText(CacheUtils.getTenementTel(this));
        proprietorId = CacheUtils.getProprietorId(this);
        proprietorName = CacheUtils.getProprietorName(this);
        proprietorMobile = CacheUtils.getMobile(this);
        proprietorAddress = CacheUtils.getAddress(this);
        token = CacheUtils.getToken(this);
        yezhuTv.setText(proprietorName);
        phoneTv.setText(proprietorMobile);
        addressTv.setText(proprietorAddress);
        initImgSelectLayout();
        initImageSelector();
        presenter = new CreateRepairPresenterImpl(this, RepairsActivity.this);
        if (mImagesDatasList.size() == 0) {
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    memo = memoEt.getText().toString().trim();
                    presenter.createRepairs(getRequestBody(token), getRequestBody(selectPosition + ""), getRequestBody(proprietorId + ""),
                            getRequestBody(proprietorName), getRequestBody(proprietorMobile),
                            getRequestBody(proprietorAddress), getRequestBody(memo));
                }
            });
        }

    }

    @NonNull
    private RequestBody getRequestBody(String s) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), s);
    }

    @Override
    public void initData() {
        token = CacheUtils.getToken(this);
    }

    public void selectView(int viewId) {
        switch (viewId) {
            case R.id.fangwu_layout:
                if (selectPosition == 1) {
                    return;
                }
                selectPosition = 1;
                fangwuImage.setImageResource(R.mipmap.fangwu_select);
                weixiuImage.setImageResource(R.mipmap.weixiu_select_not);
                baojieImage.setImageResource(R.mipmap.baojie_select_not);
                lvhuaImage.setImageResource(R.mipmap.lvhua_select_not);
                break;
            case R.id.weixiu_layout:
                if (selectPosition == 2) {
                    return;
                }
                selectPosition = 2;
                fangwuImage.setImageResource(R.mipmap.fangwu_select_not);
                weixiuImage.setImageResource(R.mipmap.yuanqu_select);
                baojieImage.setImageResource(R.mipmap.baojie_select_not);
                lvhuaImage.setImageResource(R.mipmap.lvhua_select_not);
                break;
            case R.id.baojie_layout:
                if (selectPosition == 3) {
                    return;
                }
                selectPosition = 3;
                fangwuImage.setImageResource(R.mipmap.fangwu_select_not);
                weixiuImage.setImageResource(R.mipmap.weixiu_select_not);
                baojieImage.setImageResource(R.mipmap.baojie_select);
                lvhuaImage.setImageResource(R.mipmap.lvhua_select_not);
                break;
            case R.id.lvlua_layout:
                if (selectPosition == 4) {
                    return;
                }
                selectPosition = 4;
                fangwuImage.setImageResource(R.mipmap.fangwu_select_not);
                weixiuImage.setImageResource(R.mipmap.weixiu_select_not);
                baojieImage.setImageResource(R.mipmap.baojie_select_not);
                lvhuaImage.setImageResource(R.mipmap.lvhua_select);
                break;
        }
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

    public CreatePostImagesAdapter mCreatePostImagesAdapter;
    List<String> mImagesDatasList = new ArrayList<>();

    @Override
    protected void initListener() {
        mCreatePostImagesAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                ImgSelActivity.startActivity(RepairsActivity.this, config, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        memo = memoEt.getText().toString().trim();
                        presenter.createRepairs(getRequestBody(token), getRequestBody(selectPosition + ""), getRequestBody(proprietorId + ""),
                                getRequestBody(proprietorName), getRequestBody(proprietorMobile),
                                getRequestBody(proprietorAddress), getRequestBody(memo), image1);
                    }
                });
            } else if (mImagesDatasList.size() == 2) {
                File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(0)));
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
                File file1 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(pathList.get(1)));
                RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpg"), file1);
                final MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file1.getName(), requestFile1);
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        memo = memoEt.getText().toString().trim();
                        presenter.createRepairs(getRequestBody(token), getRequestBody(selectPosition + ""), getRequestBody(proprietorId + ""),
                                getRequestBody(proprietorName), getRequestBody(proprietorMobile),
                                getRequestBody(proprietorAddress), getRequestBody(memo), image1, image2);
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
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        memo = memoEt.getText().toString().trim();
                        presenter.createRepairs(getRequestBody(token), getRequestBody(selectPosition + ""), getRequestBody(proprietorId + ""),
                                getRequestBody(proprietorName), getRequestBody(proprietorMobile),
                                getRequestBody(proprietorAddress), getRequestBody(memo), image1, image2, image3);
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
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        memo = memoEt.getText().toString().trim();
                        presenter.createRepairs(getRequestBody(token), getRequestBody(selectPosition + ""), getRequestBody(proprietorId + ""),
                                getRequestBody(proprietorName), getRequestBody(proprietorMobile),
                                getRequestBody(proprietorAddress), getRequestBody(memo), image1, image2, image3, image4);
                    }
                });
            }
        } else {
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    memo = memoEt.getText().toString().trim();
                    presenter.createRepairs(getRequestBody(token), getRequestBody(selectPosition + ""), getRequestBody(proprietorId + ""),
                            getRequestBody(proprietorName), getRequestBody(proprietorMobile),
                            getRequestBody(proprietorAddress), getRequestBody(memo));
                }
            });
        }
    }

    private void initImageSelector() {
        config = new ImgSelConfig.Builder(this, loader)
                .rememberSelected(false)
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

    // 自定义图片加载器
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };
    public ImgSelConfig config;

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void createSucces() {
        ToastUtils.showShort(RepairsActivity.this, "申报成功");
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
