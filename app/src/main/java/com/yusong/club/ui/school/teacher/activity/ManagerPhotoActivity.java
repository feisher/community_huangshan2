package com.yusong.club.ui.school.teacher.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shuyu.gsyvideoplayer.utils.NetworkUtils;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.implView.IManagerPhotoActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.IManagerPhotoActivityPresenterImpl;
import com.yusong.club.ui.school.teacher.adapter.ManagerPhotoAdapter;
import com.yusong.club.ui.school.teacher.bean.EventMsg;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.ui.school.teacher.view.MyGridView;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.DateUtil;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import zlc.season.rxdownload.RxDownload;
import zlc.season.rxdownload.entity.DownloadStatus;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class ManagerPhotoActivity extends BaseActivity implements View.OnClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, IManagerPhotoActivityView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_choose_all)
    TextView tvChooseAll;
    @InjectView(R.id.checkBox)
    CheckBox checkBox;
    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.lg_list)
    MyGridView lgList;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    @InjectView(R.id.linear_download)
    LinearLayout linearDownload;
    @InjectView(R.id.linear_delete)
    LinearLayout linearDelete;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    @InjectView(R.id.item_good)
    TextView itemGood;
    @InjectView(R.id.item_talk)
    TextView itemTalk;
    @InjectView(R.id.activity_manager_photo)
    LinearLayout activityManagerPhoto;
    private Context mContext;
    private ManagerPhotoAdapter managerPhotoAdapter;
    private List<PhotoAlbum.ImageListBean> imageListBeanList;
    private PhotoAlbum photoAlbum;
    private IManagerPhotoActivityPresenterImpl mPresenter;
    private String phtoId = "";
    private List<String> urls;
    private int downNum = 0;
    private String defaultPath = getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getPath();
    private Subscription subscription;
    private RxDownload mRxDownload;

    @Override
    protected int layoutId() {
        return R.layout.activity_manager_photo;
    }

    @Override
    public void initData() {
        mContext = ManagerPhotoActivity.this;
        EventBus.getDefault().register(this);
        mPresenter = new IManagerPhotoActivityPresenterImpl(this, this);
        urls = new ArrayList<>();
        linearBack.setOnClickListener(this);
        checkBox.setOnClickListener(this);
        tvChooseAll.setOnClickListener(this);
        linearDownload.setOnClickListener(this);
        linearDelete.setOnClickListener(this);
        imageListBeanList = new ArrayList<>();
        if (getIntent() != null && getIntent().getSerializableExtra("photoAlbum") != null) {
            photoAlbum = (PhotoAlbum) getIntent().getSerializableExtra("photoAlbum");
            tvTitle.setText(photoAlbum.getAlbumName() + "");
            imageListBeanList = photoAlbum.getImageList();
            if (imageListBeanList.size() != 0) {
                notDataLayout.setVisibility(View.GONE);
            } else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
            if (photoAlbum.getCreateTime() != null) {
                tvDate.setText(DateUtil.getHidatenew(photoAlbum.getCreateTime()) + "");
            }
        }
        initRefreshLayout();
        managerPhotoAdapter = new ManagerPhotoAdapter(ManagerPhotoActivity.this, imageListBeanList);
        managerPhotoAdapter.setManagerPhoto(new ManagerPhotoAdapter.ManagerPhoto() {
            @Override
            public void judegeSelect(int pos) {
                imageListBeanList.get(pos).setCheck(true);
                judegeCheck();
            }
        });
        lgList.setAdapter(managerPhotoAdapter);
        initRxDown();
    }

    private void initRxDown() {
        mRxDownload = RxDownload.getInstance()
                .maxThread(10)
                .context(this)      // 自动安装需要Context
                .autoInstall(false); //下载完成自动安装
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void judegeCheck() {
        boolean flag = true;
        for (int i = 0; i < imageListBeanList.size(); i++) {
            if (!(imageListBeanList.get(i).isCheck())) {
                flag = false;
                break;
            }
        }
        if (flag) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        mBGALayout.setDelegate(this);//设置代理
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        mBGALayout.setRefreshViewHolder(mBGARefreshViewHolder);
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
        ButterKnife.inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.checkBox:
                setImageInfo();
                break;
            case R.id.tv_choose_all:
                checkBox.setChecked(true);
                setImageInfo();
                break;
            case R.id.linear_download:
                phtoId = "";
                if (checkImagDown()) {
                    upLoadPhotoFile();
                } else {
                    ToastUtils.showShort(mContext, "你至少选择一张图片");
                }
                break;
            case R.id.linear_delete:
                phtoId = "";
                if (checkImag()) {
                    showAlert("确定删除吗?");
                } else {
                    ToastUtils.showShort(mContext, "你至少选择一张图片");
                }
                break;
        }
    }

    public void startDownLoad(final String url, String saveName) {
        subscription = RxDownload.getInstance()
                .download(url, saveName, defaultPath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DownloadStatus>() {
                    @Override
                    public void onCompleted() {
                        //下载完成
                        if (downNum + 1 == urls.size()) {
                            ToastUtils.showLong(mContext, "图片已全部下载到相册");
                            downNum = 0;
                            MyApplication.closeProgressDialog();
                            finish();
                            return;
                        }
                        downNum++;
                        downLoadPhoto();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //下载出错
                        downLoadPhoto();
                    }

                    @Override
                    public void onNext(final DownloadStatus status) {
                        //下载状态

                    }
                });

    }

    private boolean checkImag() {
        boolean flag = false;
        for (int i = 0; i < imageListBeanList.size(); i++) {
            if ((imageListBeanList.get(i).isCheck())) {
                flag = true;
                break;
            }
        }
        List<String> ids = new ArrayList<>();
        for (int j = 0; j < imageListBeanList.size(); j++) {
            if ((imageListBeanList.get(j).isCheck())) {
                ids.add(imageListBeanList.get(j).getImageId() + "");
            }
        }
        if (ids.size() == 1) {
            phtoId = ids.get(0);
        } else {
            if (ids.size() != 0) {
                for (int a = 0; a < ids.size() - 1; a++) {
                    phtoId = ids.get(a) + "," + phtoId;
                }
                phtoId = phtoId + ids.get(ids.size() - 1);
            }
        }
        return flag;
    }

    private boolean checkImagDown() {
        boolean flag = false;
        for (int i = 0; i < imageListBeanList.size(); i++) {
            if ((imageListBeanList.get(i).isCheck())) {
                flag = true;
                break;
            }
        }
        for (int j = 0; j < imageListBeanList.size(); j++) {
            if ((imageListBeanList.get(j).isCheck())) {
                urls.add(imageListBeanList.get(j).getImagePath() + "");
            }
        }
        return flag;
    }

    private void showAlert(String msg) {
        new AlertDialog.Builder(mContext)
                .setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deletePhoto(CacheUtils.getToken(mContext), phtoId, photoAlbum.getId());

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }

    private void upLoadPhotoFile() {
        if (!NetworkUtils.isAvailable(mContext)) {
            Toast.makeText(mContext, getResources().getString(R.string.no_net), Toast.LENGTH_LONG).show();
            return;
        }
        if (NetworkUtils.isWifiConnected(mContext)) {
            MyApplication.showProgressDialog(mContext);
            downLoadPhoto();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(getResources().getString(R.string.tips_not_wifi_down));
            builder.setPositiveButton(getResources().getString(R.string.tips_not_wifi_down_confirm), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    MyApplication.showProgressDialog(mContext);
                    downLoadPhoto();
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.tips_not_wifi_down_cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }

    private void downLoadPhoto() {
        String saveName = "";
        int index = urls.get(downNum).lastIndexOf("/");
        saveName = urls.get(downNum).substring(index + 1);
        startDownLoad(urls.get(downNum), saveName);

    }

    private void setImageInfo() {
        if (checkBox.isChecked()) {
            for (int i = 0; i < imageListBeanList.size(); i++) {
                imageListBeanList.get(i).setCheck(true);
            }
        } else {
            for (int i = 0; i < imageListBeanList.size(); i++) {
                imageListBeanList.get(i).setCheck(false);
            }
        }
        managerPhotoAdapter.notifyDataSetChanged();
    }

    @Override
    public void closeRefreshing() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
            mBGALayout.endLoadingMore();
        }
    }

    @Override
    public void setPhotList(PhotoAlbum list) {
        if (list != null) {
            phtoId = "";
            imageListBeanList.clear();
            imageListBeanList.addAll(list.getImageList());
            if (list.getImageList() != null && list.getImageList().size() != 0) {
                notDataLayout.setVisibility(View.GONE);
            } else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
            managerPhotoAdapter.notifyDataSetChanged();
        } else {
            notDataLayout.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void deletePhoto(String data) {
        ToastUtils.showShort(mContext, "删除成功");
        finish();

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventMsg(EventMsg event) {


    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        phtoId = "";
        checkBox.setChecked(false);
        mPresenter.getClazzPhotoList(CacheUtils.getToken(mContext), photoAlbum.getId());
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
