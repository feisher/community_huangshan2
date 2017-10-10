package com.yusong.club.ui.school.teacher.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.utils.NetworkUtils;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.map.NavigationUtil;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.mvp.implView.IUploadPhotoActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.IUploadPhotoActivityPresenterImpl;
import com.yusong.club.ui.school.teacher.adapter.UploadPhotoAdapter;
import com.yusong.club.ui.school.teacher.bean.EventMsg;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.ToastUtils;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 上传照片
 */
public class UploadPhotoActivity extends BaseActivity implements View.OnClickListener, IUploadPhotoActivityView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.tv_photo_name)
    TextView tvPhotoName;
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
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
    @InjectView(R.id.im_add)
    ImageView imAdd;
    @InjectView(R.id.tv_address)
    TextView tvAddress;
    @InjectView(R.id.btn_upload)
    Button btnUpload;
    @InjectView(R.id.rv_imgs)
    RecyclerView rvImgs;
    public Context mContext;
    private PhotoAlbum photoAlbum;
    private List<String> mImagesDatasList = new ArrayList<>();
    private UploadPhotoAdapter mUploadPhotoAdapter;
    RequestBody NO_DATA;
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
    private IUploadPhotoActivityPresenterImpl mPresenter;
    private int a = 0;

    @Override
    protected void initListener() {
        linearBack.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        imAdd.setOnClickListener(this);
        tvPhotoName.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        mUploadPhotoAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                ImgSelActivity.startActivity(UploadPhotoActivity.this, config, 0);
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_upload_phone;
    }

    @Override
    public void initView() {
        if (getIntent() != null && getIntent().getSerializableExtra("photoAlbum") != null) {
            photoAlbum = (PhotoAlbum) getIntent().getSerializableExtra("photoAlbum");
        }
        EventBus.getDefault().register(this);
        mContext = UploadPhotoActivity.this;
        tvTitle.setText("相册上传");
        imMsg.setVisibility(View.GONE);
        tvPhotoName.setText(photoAlbum.getAlbumName() + "");
        tvAddress.setText(NavigationUtil.getAdress() + "");
        initImgSelectLayout();
        initImageSelector();
        mPresenter = new IUploadPhotoActivityPresenterImpl(this, this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventMsg(EventMsg event) {
    }

    @NonNull
    private RequestBody getRequestBody(String s) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), s);
    }

    private void initImgSelectLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        mUploadPhotoAdapter = new UploadPhotoAdapter(mImagesDatasList, this);
        rvImgs.setAdapter(mUploadPhotoAdapter);
        rvImgs.addItemDecoration(new SpaceItemDecoration(1, 1));
        rvImgs.setLayoutManager(gridLayoutManager);
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

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
//            case R.id.im_add:
//                //调用相册
//                Intent intent = new Intent(Intent.ACTION_PICK,
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, IMAGE);
//                break;
            case R.id.btn_upload:
                if (mImagesDatasList.size() != 0) {
                    upLoadPhotoFile(a);
                } else {
                    ToastUtils.showShort(this, "至少选择一张上传的图片...");
                }
                break;
            case R.id.im_add://点击跳转图片选择界mian//调用相册
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
                imAdd.setVisibility(View.GONE);
                rvImgs.setVisibility(View.VISIBLE);
            } else {
                imAdd.setVisibility(View.VISIBLE);
                rvImgs.setVisibility(View.GONE);
            }
            mImagesDatasList.clear();
            for (String path : pathList) {
                mImagesDatasList.add(path);
            }
        }
    }

    private void upLoadPhotoFile(final int a) {
        if (!NetworkUtils.isAvailable(mContext)) {
            Toast.makeText(mContext, getResources().getString(R.string.no_net), Toast.LENGTH_LONG).show();
            return;
        }
        if (NetworkUtils.isWifiConnected(mContext)) {
            File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(a)));
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
            final MultipartBody.Part image1 = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            mPresenter.uploadPhotoFile(getRequestBody(CacheUtils.getToken(mContext)),
                    getRequestBody(photoAlbum.getId() + ""),
                    image1);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(getResources().getString(R.string.tips_not_wifi_upload));
            builder.setPositiveButton(getResources().getString(R.string.tips_not_wifi_upload_confirm), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(a)));
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                    final MultipartBody.Part image1 = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                    mPresenter.uploadPhotoFile(getRequestBody(CacheUtils.getToken(mContext)),
                            getRequestBody(photoAlbum.getId() + ""),
                            image1);
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.tips_not_wifi_upload_cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //获取图片路径
//        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumns = {MediaStore.Images.Media.DATA};
//            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
//            c.moveToFirst();
//            int columnIndex = c.getColumnIndex(filePathColumns[0]);
//            String imagePath = c.getString(columnIndex);
//            showImage(imagePath);
//            c.close();
//        }
//    }

//    //加载图片
//    private void showImage(String imaePath) {
//        Bitmap bm = BitmapFactory.decodeFile(imaePath);
//        ((ImageView) findViewById(R.id.im_add)).setImageBitmap(bm);
//    }

    @Override
    public void upLoadPhotoSucess(String data) {
        if ((a + 1) == mImagesDatasList.size()) {
            EventBus.getDefault().post(new EventMsg(EventMsg.REFRESH_PHOT_DETAIL, photoAlbum.getId()));
            UploadPhotoActivity.this.finish();
            a = 0;
        } else {
            a++;
            upLoadPhotoFile(a);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onError() {
        upLoadPhotoFile(a);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }
}
