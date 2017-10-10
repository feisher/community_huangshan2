package com.yusong.club.ui.shoppers.used.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yusong.club.R;
import com.yusong.club.map.LocationService;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.community.adapter.CreatePostImagesAdapter;
import com.yusong.club.ui.shoppers.bean.FenLeiBean;
import com.yusong.club.ui.shoppers.used.bean.MyUsedBean;
import com.yusong.club.ui.shoppers.used.bean.UsedBean;
import com.yusong.club.ui.shoppers.used.mvp.implView.ImplUsedIssueView;
import com.yusong.club.ui.shoppers.used.mvp.implView.ImplUsedView;
import com.yusong.club.ui.shoppers.used.mvp.presenter.impl.ImplUsedIssuePersenterImpl;
import com.yusong.club.ui.shoppers.used.mvp.presenter.impl.ImplUsedPresenterImpl;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.ToastUtils;
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
 *         created at 2017/3/17 13:10.
 *         描述: 发布界面
 */

public class IssueActivity extends BaseActivity implements ImplUsedView, ImplUsedIssueView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.content_et)
    EditText contenEt;
    @InjectView(R.id.iv_select_img)
    ImageView ivSelectImg;
    @InjectView(R.id.rv_imgs)
    RecyclerView rvImgs;
    @InjectView(R.id.address_tv)
    TextView addressTv;
    @InjectView(R.id.rl_select_category)
    RelativeLayout rlSelectCategory;
    @InjectView(R.id.commdity_new_price_et)
    EditText commdityNewPriceEt;
    @InjectView(R.id.commdiy_yuanjia_et)
    EditText commdiyYuanjiaEt;
    @InjectView(R.id.commdity_type_tv)
    TextView commdityTypeTv;
    @InjectView(R.id.select_commdity_type)
    RelativeLayout selectCommdityType;
    @InjectView(R.id.issue_button)
    Button issueButton;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.pop_view)
    View mPopView;
    public ImgSelConfig config;
    private ImplUsedPresenterImpl implUsedPresenterImpl;//查询分类
    private ImplUsedIssuePersenterImpl issuePersenterImpl;//发布商品
    List<FenLeiBean> beanList = new ArrayList<FenLeiBean>();//分类集合
    public CreatePostImagesAdapter mCreatePostImagesAdapter;
    private int usedType = 0;//二手类型
    private List<String> imageList = new ArrayList<String>();
    private String token = null;
    private MyUsedBean bean;
    private AlertDialog.Builder builder;

    @OnClick({R.id.ll_back, R.id.iv_select_img, R.id.select_commdity_type, R.id.issue_button})
    void allClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_select_img://添加图片
                ImgSelActivity.startActivity(this, config, 0);
                break;
            case R.id.select_commdity_type://宝贝类型
                implUsedPresenterImpl.queryUsedFenlei();
                break;
            case R.id.issue_button://发布
                issueUsed();
                break;
            default:
                break;
        }
    }

    //发布逻辑
    private void issueUsed() {
        BDLocation mLocation = LocationService.mLocation;
        String content = contenEt.getText().toString().toString();
        float price = Float.parseFloat(commdityNewPriceEt.getText().toString().trim());
        float showPrice = Float.parseFloat(commdiyYuanjiaEt.getText().toString().trim());
        double lng = mLocation.getLongitude();
        double lat = mLocation.getLatitude();
        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(commdityNewPriceEt.getText().toString().trim()) ||
                TextUtils.isEmpty(commdiyYuanjiaEt.getText().toString().trim())) {
            ToastUtils.showShort(this, "信息不完整");
            return;
        } else if (imageList.size() == 0) {
            ToastUtils.showShort(this, "请上传照片");
            return;
        } else if (usedType == 0) {
            ToastUtils.showShort(this, "请选择类型");
            return;
        } else {
            if (imageList.size() == 1) {
                File file = Compressor.getDefault(this.getApplicationContext()).compressToFile(new File(imageList.get(0)));
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
                if (bean == null) {
                    issuePersenterImpl.issueUsed(getRequestBody(token), getRequestBody(usedType + ""), getRequestBody(content), image1, null, null, null,
                            getRequestBody(price + ""), getRequestBody(showPrice + ""), getRequestBody(mLocation.getAddrStr()),
                            getRequestBody(lng + ""), getRequestBody(lat + ""));
                } else {
                    issuePersenterImpl.editUsed(getRequestBody(token), getRequestBody(bean.getId() + ""), getRequestBody(usedType + ""), getRequestBody(content), image1, null, null, null,
                            getRequestBody(price + ""), getRequestBody(showPrice + ""), getRequestBody(mLocation.getAddrStr()),
                            getRequestBody(lng + ""), getRequestBody(lat + ""));
                }

            } else if (imageList.size() == 2) {
                File file = Compressor.getDefault(this.getApplicationContext()).compressToFile(new File(imageList.get(0)));
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
                File file1 = Compressor.getDefault(this.getApplicationContext()).compressToFile(new File(imageList.get(1)));
                RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpg"), file1);
                final MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file1.getName(), requestFile1);
                if (bean == null) {
                    issuePersenterImpl.issueUsed(getRequestBody(token), getRequestBody(usedType + ""), getRequestBody(content), image1, image2, null, null,
                            getRequestBody(price + ""), getRequestBody(showPrice + ""), getRequestBody(mLocation.getAddrStr()),
                            getRequestBody(lng + ""), getRequestBody(lat + ""));
                } else {
                    issuePersenterImpl.editUsed(getRequestBody(token), getRequestBody(bean.getId() + ""), getRequestBody(usedType + ""), getRequestBody(content), image1, image2, null, null,
                            getRequestBody(price + ""), getRequestBody(showPrice + ""), getRequestBody(mLocation.getAddrStr()),
                            getRequestBody(lng + ""), getRequestBody(lat + ""));

                }

            } else if (imageList.size() == 3) {
                File file = Compressor.getDefault(this.getApplicationContext()).compressToFile(new File(imageList.get(0)));
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
                File file1 = Compressor.getDefault(this.getApplicationContext()).compressToFile(new File(imageList.get(1)));
                RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpg"), file1);
                final MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file1.getName(), requestFile1);
                File file2 = Compressor.getDefault(this.getApplicationContext()).compressToFile(new File(imageList.get(2)));
                RequestBody requestFile2 = RequestBody.create(MediaType.parse("image/jpg"), file2);
                final MultipartBody.Part image3 = MultipartBody.Part.createFormData("image3", file2.getName(), requestFile2);
                if (bean == null) {
                    issuePersenterImpl.issueUsed(getRequestBody(token), getRequestBody(usedType + ""), getRequestBody(content), image1, image2, image3, null,
                            getRequestBody(price + ""), getRequestBody(showPrice + ""), getRequestBody(mLocation.getAddrStr()),
                            getRequestBody(lng + ""), getRequestBody(lat + ""));
                } else {
                    issuePersenterImpl.editUsed(getRequestBody(token), getRequestBody(bean.getId() + ""), getRequestBody(usedType + ""), getRequestBody(content), image1, image2, image3, null,
                            getRequestBody(price + ""), getRequestBody(showPrice + ""), getRequestBody(mLocation.getAddrStr()),
                            getRequestBody(lng + ""), getRequestBody(lat + ""));
                }
            } else if (imageList.size() == 4) {
                File file = Compressor.getDefault(this.getApplicationContext()).compressToFile(new File(imageList.get(0)));
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
                File file1 = Compressor.getDefault(this.getApplicationContext()).compressToFile(new File(imageList.get(1)));
                RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpg"), file1);
                final MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file1.getName(), requestFile1);
                File file2 = Compressor.getDefault(this.getApplicationContext()).compressToFile(new File(imageList.get(2)));
                RequestBody requestFile2 = RequestBody.create(MediaType.parse("image/jpg"), file2);
                final MultipartBody.Part image3 = MultipartBody.Part.createFormData("image3", file2.getName(), requestFile2);
                File file3 = Compressor.getDefault(this.getApplicationContext()).compressToFile(new File(imageList.get(3)));
                RequestBody requestFile3 = RequestBody.create(MediaType.parse("image/jpg"), file3);
                final MultipartBody.Part image4 = MultipartBody.Part.createFormData("image4", file3.getName(), requestFile3);
                if (bean == null) {
                    issuePersenterImpl.issueUsed(getRequestBody(token), getRequestBody(usedType + ""), getRequestBody(content), image1, image2, image3, image4,
                            getRequestBody(price + ""), getRequestBody(showPrice + ""), getRequestBody(mLocation.getAddrStr()),
                            getRequestBody(lng + ""), getRequestBody(lat + ""));
                } else {
                    issuePersenterImpl.editUsed(getRequestBody(token), getRequestBody(bean.getId() + ""), getRequestBody(usedType + ""), getRequestBody(content), image1, image2, image3, image4,
                            getRequestBody(price + ""), getRequestBody(showPrice + ""), getRequestBody(mLocation.getAddrStr()),
                            getRequestBody(lng + ""), getRequestBody(lat + ""));
                }
            }
        }
    }

    @NonNull
    private RequestBody getRequestBody(String s) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), s);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_used_issue;
    }

    @Override
    public void initData() {
        bean = (MyUsedBean) getIntent().getSerializableExtra("MyUsedBean");
        if (bean != null) {
            tvTitle.setText("修改");
            contenEt.setText(bean.getIntroduction());
            commdityNewPriceEt.setText(bean.getPrice() + "");
            commdiyYuanjiaEt.setText(bean.getShowPrice() + "");
            usedType = bean.getCategoryId();
            commdityTypeTv.setText(bean.getCategoryName());
        } else {
            tvTitle.setText("发布");
        }
        initImgSelectLayout();
        initImageSelector();
        addressTv.setText(LocationService.mLocation.getAddrStr());
        token = CacheUtils.getToken(this);
        implUsedPresenterImpl = new ImplUsedPresenterImpl(this, IssueActivity.this);
        issuePersenterImpl = new ImplUsedIssuePersenterImpl(this, IssueActivity.this);
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initListener() {
        mCreatePostImagesAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                ImgSelActivity.startActivity(IssueActivity.this, config, 0);
            }
        });
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    /**
     * 图片加载相关
     */
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };

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


    private void initImgSelectLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mCreatePostImagesAdapter = new CreatePostImagesAdapter(imageList, this);
        rvImgs.setAdapter(mCreatePostImagesAdapter);
        rvImgs.addItemDecoration(new SpaceItemDecoration(1, 1));
        rvImgs.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (pathList.size() != 0) {
                ivSelectImg.setVisibility(View.GONE);
                rvImgs.setVisibility(View.VISIBLE);
            } else {
                ivSelectImg.setVisibility(View.VISIBLE);
                rvImgs.setVisibility(View.GONE);
            }
            if (imageList.size() > 0) {
                imageList.clear();
            }
            for (String path : pathList) {
                imageList.add(path);
            }
            mCreatePostImagesAdapter.notifyDataSetChanged();
        }
    }

    //选择器
    private void showPopupWindow(final List<String> data) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow_express_layout, null);
        contentView.getBackground().setAlpha(100);
        final PopupWindow window;
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        window.setContentView(contentView);
        final WheelView wheelView = (WheelView) contentView.findViewById(R.id.wheelview);
        wheelView.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        wheelView.setWheelData(data);  // 数据集合
        wheelView.setSkin(WheelView.Skin.Holo);
        wheelView.setWheelSize(5);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.textColor = Color.parseColor("#888888");
        style.selectedTextColor = Color.parseColor("#333333");
        wheelView.setStyle(style);
        TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commdityTypeTv.setText(data.get(wheelView.getCurrentPosition()));
                usedType = beanList.get(wheelView.getCurrentPosition()).getId();
                window.dismiss();
            }
        });

        TextView tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.showAtLocation(mPopView, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void queryFenleiSucced(List<FenLeiBean> data) {
        if (beanList.size() > 0) {
            beanList.clear();
        }
        beanList.addAll(data);
        if (beanList.size() > 0) {
            List<String> list = new ArrayList<String>();
            for (FenLeiBean bean : beanList) {
                list.add(bean.getCategoryName());
            }
            showPopupWindow(list);
        } else {
            ToastUtils.showShort(this, "暂无分类");
        }
    }

    @Override
    public void quertListSucced(List<UsedBean> data) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void issueSucced() {//发布成功
        builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("发布成功（待管理员审核过后自动上架）");
        builder.setTitle("提示");
        builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                IssueActivity.this.finish();
            }
        });
        builder.show();
    }

    @Override
    public void editSucced() {
        builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("修改成功（待管理员审核过后自动上架）");
        builder.setTitle("提示");
        builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                IssueActivity.this.finish();
            }
        });
        builder.show();
    }

    @Override
    public void issueClose() {
        ToastUtils.showShort(IssueActivity.this, "发布失败");
    }

    @Override
    public void colse() {

    }

    @Override
    protected void onDestroy() {
        implUsedPresenterImpl.onDestroy();
        issuePersenterImpl.onDestroy();
        super.onDestroy();
    }
}
