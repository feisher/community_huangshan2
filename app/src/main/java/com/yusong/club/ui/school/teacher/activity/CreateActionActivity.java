package com.yusong.club.ui.school.teacher.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.community.adapter.CreatePostImagesAdapter;
import com.yusong.club.ui.school.event.CreateActionEvent;
import com.yusong.club.ui.school.mvp.implView.ICreateActionActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.ICreateActionActivityPresenterImpl;
import com.yusong.club.ui.school.school.bean.HuoType;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.DateUtil;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.ToastUtils;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.apache.commons.lang.StringUtils;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.InjectView;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateActionActivity extends BaseActivity implements View.OnClickListener, ICreateActionActivityView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.et_title)
    EditText etTitle;
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.im_add)
    ImageView imAdd;
    @InjectView(R.id.tv_action)
    TextView tvAction;
    @InjectView(R.id.lin_action_type)
    LinearLayout linActionType;
    @InjectView(R.id.tv_start_time)
    TextView tvStartTime;
    @InjectView(R.id.lin_start_time)
    LinearLayout linStartTime;
    @InjectView(R.id.tv_ed_time)
    TextView tvEdTime;
    @InjectView(R.id.lin_end_time)
    LinearLayout linEndTime;
    @InjectView(R.id.btn_publish)
    Button btnPublish;
    @InjectView(R.id.rv_imgs)
    RecyclerView rvImgs;
    @InjectView(R.id.activity_create_action)
    LinearLayout activityCreateAction;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    private List<HuoType> huoTypes;
    private List<String> typeNames;
    private ICreateActionActivityPresenterImpl mPresenter;
    private Context mContext;
    private List<String> mImagesDatasList = new ArrayList<>();
    private int type = -1;
    private String startDate = "";
    private String endDate = "";
    private TimePickerView pvTime;
    private int dateType = -1;
    // 自定义图片加载器
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };
    public ImgSelConfig config;
    private CreatePostImagesAdapter mCreatePostImagesAdapter;

    @Override
    protected int layoutId() {
        return R.layout.activity_create_action;
    }

    @Override
    public void initData() {
        typeNames = new ArrayList<>();
        huoTypes = new ArrayList<>();
        llImg.setVisibility(View.GONE);
        mContext = CreateActionActivity.this;
        mPresenter = new ICreateActionActivityPresenterImpl(this, this);
        mPresenter.categoryList(CacheUtils.getToken(mContext));
        llBack.setOnClickListener(this);
        btnPublish.setOnClickListener(this);
        linActionType.setOnClickListener(this);
        linStartTime.setOnClickListener(this);
        linEndTime.setOnClickListener(this);
        tvTitle.setText("新建活动");
        initImgSelectLayout();
        initImageSelector();
        initDateInfo();
        imAdd.setOnClickListener(this);
        mCreatePostImagesAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                ImgSelActivity.startActivity(CreateActionActivity.this, config, 0);
            }
        });
    }

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
                tvAction.setText(huoTypes.get(wheelView.getCurrentPosition()).getCategoryName());
                type = huoTypes.get(wheelView.getCurrentPosition()).getId();
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
        window.showAtLocation(activityCreateAction, Gravity.BOTTOM, 0, 0);
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
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_publish:
                if (checkInfo()) {
                    createActionInfo();
                }
                break;
            case R.id.im_add:
                ImgSelActivity.startActivity(this, config, 0);
                break;
            case R.id.lin_action_type:
                showPopupWindow(typeNames);
                break;
            case R.id.lin_start_time:
                dateType = 1;
                pvTime.show();
                break;
            case R.id.lin_end_time:
                dateType = 2;
                pvTime.show();
                break;
        }
    }

    private void initDateInfo() {
        Calendar selectedDate = Calendar.getInstance();
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (dateType == 1) {
                    tvStartTime.setText(getTime(date));
                    startDate = getTimeNew(date);
                } else if (dateType == 2) {
                    tvEdTime.setText(getTime(date));
                    endDate = getTimeNew(date);
                }
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentSize(20)
                .setDate(selectedDate)
                .setRange(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.YEAR))//默认是1900-2100年
                .build();
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    public static String getTimeNew(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private boolean checkInfo() {
        if (StringUtils.isEmpty(startDate)) {
            MyApplication.showMessage("开始日期不能为空");
            return false;
        }
        if (StringUtils.isEmpty(endDate)) {
            MyApplication.showMessage("结束日期不能为空");
            return false;
        }
        if (StringUtils.isEmpty(etTitle.getText().toString())) {
            MyApplication.showMessage("活动标题不能为空");
            return false;
        }
        if (DateUtil.compareDate(startDate, endDate)) {
            MyApplication.showMessage("结束时间不能小于开始时间");
            return false;
        }
        if (type == -1) {
            MyApplication.showMessage("活动分类不能为空");
            return false;
        }
        if (StringUtils.isEmpty(etContent.getText().toString())) {
            MyApplication.showMessage("活动描述不能为空");
            return false;
        }
        if (DateUtil.compareDate(DateUtil.getDate(), startDate)) {
            MyApplication.showMessage("活动开始时间不能小于当前时间");
            return false;
        }
        return true;
    }

    private void createActionInfo() {
        if (mImagesDatasList.size() == 0) {
            mPresenter.createAction(getRequestBody(CacheUtils.getToken(mContext)),
                    getRequestBody(type + ""),
                    getRequestBody(etTitle.getText().toString()),
                    getRequestBody(startDate),
                    getRequestBody(endDate),
                    getRequestBody(etContent.getText().toString()), null, null, null, null);
        } else if (mImagesDatasList.size() == 1) {
            File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(0)));
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
            final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
            mPresenter.createAction(getRequestBody(CacheUtils.getToken(mContext)),
                    getRequestBody(type + ""),
                    getRequestBody(etTitle.getText().toString()),
                    getRequestBody(startDate),
                    getRequestBody(endDate),
                    getRequestBody(etContent.getText().toString()), image1, null, null, null);
        } else if (mImagesDatasList.size() == 2) {
            File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(0)));
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
            final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
            File file1 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(1)));
            RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpg"), file1);
            final MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file1.getName(), requestFile1);
            mPresenter.createAction(getRequestBody(CacheUtils.getToken(mContext)),
                    getRequestBody(type + ""),
                    getRequestBody(etTitle.getText().toString()),
                    getRequestBody(startDate),
                    getRequestBody(endDate),
                    getRequestBody(etContent.getText().toString()), image1, image2, null, null);
        } else if (mImagesDatasList.size() == 3) {
            File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(0)));
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
            final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
            File file1 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(1)));
            RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpg"), file1);
            final MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file1.getName(), requestFile1);
            File file2 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(2)));
            RequestBody requestFile2 = RequestBody.create(MediaType.parse("image/jpg"), file2);
            final MultipartBody.Part image3 = MultipartBody.Part.createFormData("image3", file2.getName(), requestFile2);
            mPresenter.createAction(getRequestBody(CacheUtils.getToken(mContext)),
                    getRequestBody(type + ""),
                    getRequestBody(etTitle.getText().toString()),
                    getRequestBody(startDate),
                    getRequestBody(endDate),
                    getRequestBody(etContent.getText().toString()), image1, image2, image3, null);
        } else if (mImagesDatasList.size() == 4) {
            File file = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(0)));
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
            final MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file.getName(), requestFile);
            File file1 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(1)));
            RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpg"), file1);
            final MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file1.getName(), requestFile1);
            File file2 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(2)));
            RequestBody requestFile2 = RequestBody.create(MediaType.parse("image/jpg"), file2);
            final MultipartBody.Part image3 = MultipartBody.Part.createFormData("image3", file2.getName(), requestFile2);
            File file3 = Compressor.getDefault(mContext.getApplicationContext()).compressToFile(new File(mImagesDatasList.get(3)));
            RequestBody requestFile3 = RequestBody.create(MediaType.parse("image/jpg"), file3);
            final MultipartBody.Part image4 = MultipartBody.Part.createFormData("image4", file3.getName(), requestFile3);
            mPresenter.createAction(getRequestBody(CacheUtils.getToken(mContext)),
                    getRequestBody(type + ""),
                    getRequestBody(etTitle.getText().toString()),
                    getRequestBody(startDate),
                    getRequestBody(endDate),
                    getRequestBody(etContent.getText().toString()), image1, image2, image3, image4);
        }
        mCreatePostImagesAdapter.notifyDataSetChanged();
    }

    private void showDate(final int dateType) {
        Calendar instance = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                        String monthe = (month + 1) + "";
                        String day = (dayOfMonth) + "";
                        if (month < 10) {
                            monthe = "0" + monthe;
                        }
                        if (dayOfMonth < 10) {
                            day = "0" + day;
                        }
                        if (dateType == 1) {
                            tvStartTime.setText(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
                            startDate = year + "-" + monthe + "-" + day;
                        } else if (dateType == 2) {
                            tvEdTime.setText(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
                            endDate = year + "-" + monthe + "-" + day;
                        }
                    }
                },
                instance.get(Calendar.YEAR), // 传入年份
                instance.get(Calendar.MONTH), // 传入月份
                instance.get(Calendar.DAY_OF_MONTH) // 传入天数
        );
        dialog.show();
        dialog.getDatePicker().setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialogWindow.setGravity(Gravity.CENTER);
        //显示的坐标
//        lp.x = 130;
//        lp.y = 1310;
        dialogWindow.setAttributes(lp);

    }

    @NonNull
    private RequestBody getRequestBody(String s) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), s);
    }

    @Override
    public void createAction(String data) {
        ToastUtils.show(mContext, "创建成功", Toast.LENGTH_LONG);
        EventBus.getDefault().post(new CreateActionEvent());
        finish();
    }

    @Override
    public void getcategoryList(List<HuoType> data) {
        for (HuoType huoType : data) {
            typeNames.add(huoType.getCategoryName());
            huoTypes.add(huoType);
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
            mCreatePostImagesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }

}