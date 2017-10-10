package com.yusong.club.ui.school.teacher.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.community.photoview.PhotoViewActivity;
import com.yusong.club.ui.school.mvp.implView.IPhotoDetailActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.IPhotoDetailActivityPresenterImpl;
import com.yusong.club.ui.school.teacher.adapter.PhotoDetailAdapter;
import com.yusong.club.ui.school.teacher.bean.EventMsg;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.ui.school.teacher.view.MyGridView;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.DateUtil;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;
import com.yusong.club.utils.glide.GlideImgManager;

import org.apache.commons.lang.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.yusong.club.R.id.im_msg;

public class PhotoDetailActivity extends BaseActivity implements IPhotoDetailActivityView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(im_msg)
    ImageView imMsg;
    @InjectView(R.id.iv_tu)
    ImageView ivTu;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.btn_upload)
    Button btnUpload;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.rv_List)
    MyGridView rvList;
    @InjectView(R.id.tv_title_one)
    TextView tvTitleOne;
    @InjectView(R.id.activity_photo_detail)
    LinearLayout activityPhotoDetail;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    private List<String> chooseTypes;
    private Context mContext;
    private List<PhotoAlbum.ImageListBean> imageListBeanList;
    private PhotoAlbum photoAlbum;
    private PhotoDetailAdapter mPhotoDetailAdapter;
    private IPhotoDetailActivityPresenterImpl mPresenter;
    private int canEdit = 0;

    @Override
    protected int layoutId() {
        return R.layout.activity_photo_detail;
    }

    @Override
    public void initData() {
        mContext = PhotoDetailActivity.this;
        imageListBeanList = new ArrayList<>();
        chooseTypes = new ArrayList<>();
        chooseTypes.add("编辑相册");
        chooseTypes.add("管理照片");
        chooseTypes.add("删除相册");
        EventBus.getDefault().register(this);
        initRefreshLayout();
        mPresenter = new IPhotoDetailActivityPresenterImpl(this, this);
        if (getIntent() != null && getIntent().getSerializableExtra("photoAlbum") != null) {
            photoAlbum = (PhotoAlbum) getIntent().getSerializableExtra("photoAlbum");
            tvTitle.setText(photoAlbum.getAlbumName() + "");
            tvTitleOne.setText(photoAlbum.getAlbumName() + "");
            tvContent.setText(photoAlbum.getMemo() + "");
            canEdit = photoAlbum.getCanEdit();
            if (canEdit == 1) {
                imMsg.setVisibility(View.VISIBLE);
                imMsg.setImageResource(R.mipmap.ic_photo_dian);
            } else {
                imMsg.setVisibility(View.INVISIBLE);
            }
            List<PhotoAlbum.ImageListBean> phtos = photoAlbum.getImageList();
            if (phtos != null && phtos.size() != 0) {
                if (photoAlbum.getCreateTime() != null) {
                    tvTime.setText(DateUtil.getHidatenew(photoAlbum.getCreateTime()) + "");
                    tvTime.setVisibility(View.VISIBLE);
                } else {
                    tvTime.setVisibility(View.INVISIBLE);
                    tvTime.setText("");
                }
            } else {
                tvTime.setVisibility(View.INVISIBLE);
                tvTime.setText("");
            }
            imageListBeanList = photoAlbum.getImageList();
            if (imageListBeanList.size() != 0) {
                GlideImgManager.loadImage(mContext, imageListBeanList.get(0).getImagePath(), ivTu);
            }
        }
        setImageInfo();
    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        mBGALayout.setDelegate(this);//设置代理
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        mBGALayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    private void setImageInfo() {
        mPhotoDetailAdapter = new PhotoDetailAdapter(PhotoDetailActivity.this, imageListBeanList);
        rvList.setAdapter(mPhotoDetailAdapter);
        rvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<String> pothos = new ArrayList<>();
                for (PhotoAlbum.ImageListBean pho : imageListBeanList) {
                    if (pho.getImagePath() != null && !pho.getImagePath().equals("")) {
                        pothos.add(pho.getImagePath());
                    }
                }
                Intent intent = new Intent(mContext, PhotoViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(PhotoViewActivity.EXTRA_IMAGE_INDEX, i);
                bundle.putStringArrayList(PhotoViewActivity.EXTRA_IMAGE_URLS, (ArrayList<String>) pothos);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @OnClick({R.id.btn_upload, R.id.linear_back, R.id.im_msg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_upload:
                Intent intent = new Intent(mContext, UploadPhotoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("photoAlbum", (Serializable) photoAlbum);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.linear_back:
                finish();
                break;
            case R.id.im_msg:
                showPopupWindow(chooseTypes);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventMsg(EventMsg event) {
    }

    private void showPopupWindow(List<String> data) {
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
                if (wheelView.getSelectionItem().toString().equals("编辑相册")) {
                    if (canEdit == 1) {
                        Intent intent = new Intent(PhotoDetailActivity.this, EditPhotoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("photoAlbum", (Serializable) photoAlbum);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        ToastUtils.showShort(mContext, "您没有编辑的权限");
                    }
                } else if (wheelView.getSelectionItem().toString().equals("管理照片")) {
                    if (canEdit == 1) {
                        Intent intent = new Intent(mContext, ManagerPhotoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("photoAlbum", (Serializable) photoAlbum);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        ToastUtils.showShort(mContext, "您没有管理照片的权限");
                    }
                } else if (wheelView.getSelectionItem().toString().equals("删除相册")) {
                    if (canEdit == 1) {
                        mPresenter.deleteAllPhoto(CacheUtils.getToken(mContext), photoAlbum.getId());
                    } else {
                        ToastUtils.showShort(mContext, "您没有删除相册的权限");
                    }
                }
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
        window.showAtLocation(activityPhotoDetail, Gravity.BOTTOM, 0, 0);
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

    @Override
    public void closeRefreshing() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
            mBGALayout.endLoadingMore();
        }
    }

    @Override
    public void setPhotList(PhotoAlbum list) {
        imageListBeanList.clear();
        imageListBeanList.addAll(list.getImageList());
        if (imageListBeanList.size() != 0) {
            GlideImgManager.loadImage(mContext, imageListBeanList.get(0).getImagePath(), ivTu);
        }
        if (list.getCreateTime() != null) {
            tvTime.setText(DateUtil.getHidatenew(list.getCreateTime() + ""));
            tvTime.setVisibility(View.VISIBLE);
        }
        if (!StringUtils.isEmpty(list.getAlbumName())) {
            tvTitle.setText(list.getAlbumName());
        }


        if (!StringUtils.isEmpty(list.getAlbumName())) {
            tvTitleOne.setText(list.getAlbumName() + "");
        }
        if (!StringUtils.isEmpty(list.getMemo())) {
            tvContent.setText(list.getMemo() + "");
        }
        mPhotoDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteAllPhoto(String data) {
        ToastUtils.showShort(mContext, "删除成功");
        EventBus.getDefault().post(new EventMsg(EventMsg.REFRESH_PHOT_DETAIL, photoAlbum.getId()));
        PhotoDetailActivity.this.finish();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getClazzPhotoList(CacheUtils.getToken(mContext), photoAlbum.getId());
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.getClazzPhotoList(CacheUtils.getToken(mContext), photoAlbum.getId());
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
