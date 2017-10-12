package com.yusong.community.ui.school.teacher.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shuyu.gsyvideoplayer.utils.NetworkUtils;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.mvp.implView.IUpLoadStudyVideoActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.IUpLoadStudyVideoActivityPresenterImpl;
import com.yusong.community.ui.school.teacher.adapter.UpLoadVedioAdapter;
import com.yusong.community.ui.school.teacher.bean.StudyVideo;
import com.yusong.community.ui.school.teacher.view.MyRecyclerView;
import com.yusong.community.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.ui.RxGalleryListener;
import cn.finalteam.rxgalleryfinal.ui.base.IMultiImageCheckedListener;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UpLoadStudyVideoActivity extends BaseActivity implements View.OnClickListener, IUpLoadStudyVideoActivityView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.rv_List)
    MyRecyclerView rvList;
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
    @InjectView(R.id.et_title)
    EditText etTitle;
    @InjectView(R.id.tv_video_name)
    TextView tvVideoName;
    @InjectView(R.id.tv_address)
    TextView tvAddress;
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    @InjectView(R.id.btn_upload)
    Button btnUpload;
    private Context mContext;
    private UpLoadVedioAdapter mUpLoadVedioAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private int max = 4;
    private int min = 0;
    private int a = 0;
    private IUpLoadStudyVideoActivityPresenterImpl mPresenter;
    private StudyVideo mStudyVideo;

    @Override
    protected int layoutId() {
        return R.layout.activity_up_load_study_video;
    }

    @Override
    public void initData() {
        linearBack.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        tvTitle.setText("上传学习视频");
        if (getIntent() != null && getIntent().getSerializableExtra("studyVideo") != null) {
            mStudyVideo = (StudyVideo) getIntent().getSerializableExtra("studyVideo");
        }
        mPresenter = new IUpLoadStudyVideoActivityPresenterImpl(this, mContext);
        initPhoto();
    }

    private void initPhoto() {
        mUpLoadVedioAdapter = new UpLoadVedioAdapter(this, selectedPhotos, new UpLoadVedioAdapter.OnTouchListener() {
            @Override
            public void onTouch(View v, final int pos) {
                if (selectedPhotos.size() < 1 && selectedPhotos.size() == pos) {
                    selectVideo();
                } else {
                    if (v.getId() == R.id.v_selected) {
                        selectedPhotos.remove(pos);
                        min = selectedPhotos.size();
                        mUpLoadVedioAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        rvList.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        rvList.setAdapter(mUpLoadVedioAdapter);
    }

    public void selectVideo() {
        RxGalleryFinal
                .with(mContext)
                .video()
                .multiple()
                .hideCamera()
                .maxSize(max - min)
                .imageLoader(ImageLoaderType.GLIDE)
                .subscribe(new RxBusResultSubscriber<ImageMultipleResultEvent>() {
                    @Override
                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                        List<MediaBean> mediaResultList = imageMultipleResultEvent.getResult();
                        if (mediaResultList != null && mediaResultList.size() != 0) {
                            for (MediaBean mediaBean : mediaResultList) {
                                selectedPhotos.add(mediaBean.getOriginalPath());
                            }
                            min = selectedPhotos.size();
                        }
                        mUpLoadVedioAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }
                })
                .openGallery();
        //得到多选的事件
        RxGalleryListener.getInstance().setMultiImageCheckedListener(new IMultiImageCheckedListener() {
            @Override
            public void selectedImg(Object t, boolean isChecked) {
                //这个主要点击或者按到就会触发，所以不建议在这里进行Toast
                      /*  Toast.makeText(getBaseContext(),"->"+isChecked,Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void selectedImgMax(Object t, boolean isChecked, int maxSize) {
                Toast.makeText(getBaseContext(), "你最多只能选择" + maxSize + "张视频", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.btn_upload:
                ToastUtils.showShort(mContext, "该功能未开放....");
                if (selectedPhotos.size() != 0) {
                    upLoadVideoFile();
                } else {
                    ToastUtils.showShort(this, "至少选择一个传的视频...");
                }
                break;
        }
    }

    @NonNull
    private RequestBody getRequestBody(String s) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), s);
    }

    private void upLoadVideoFile() {
        if (!NetworkUtils.isAvailable(mContext)) {
            Toast.makeText(mContext, getResources().getString(R.string.no_net), Toast.LENGTH_LONG).show();
            return;
        }
        if (NetworkUtils.isWifiConnected(mContext)) {
            File file = new File(selectedPhotos.get(a));
            RequestBody requestFile = RequestBody.create(MediaType.parse("video/mp4"), file);
            final MultipartBody.Part image1 = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//            mPresenter.uploadVideoFile(getRequestBody(CacheUtils.getToken(mContext)),
//                    getRequestBody(mStudyVideo.get() + ""),
//                    image1);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(getResources().getString(R.string.tips_not_wifi_upload));
            builder.setPositiveButton(getResources().getString(R.string.tips_not_wifi_upload_confirm), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    File file = new File(selectedPhotos.get(a));
                    RequestBody requestFile = RequestBody.create(MediaType.parse("video/mp4"), file);
                    final MultipartBody.Part image1 = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//                    mPresenter.uploadVideoFile(getRequestBody(CacheUtils.getToken(mContext)),
//                            getRequestBody(videoAlbum.getId() + ""),
//                            image1);
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

    @Override
    public void upLoadVideoSucess(String data) {
        if ((a + 1) == selectedPhotos.size()) {
            UpLoadStudyVideoActivity.this.finish();
            a = 0;
        } else {
            a++;
            upLoadVideoFile();
        }
    }

    @Override
    public void onError() {
//        upLoadVideoFile();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }
}
