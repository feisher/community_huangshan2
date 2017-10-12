package com.yusong.community.ui.express.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.pay.CommonPayActivity;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.express.mvp.entity.CommitOrderResult;
import com.yusong.community.ui.express.mvp.entity.ConfigInfo;
import com.yusong.community.ui.express.mvp.entity.ContactGroup;
import com.yusong.community.ui.express.mvp.implView.ISendMailView;
import com.yusong.community.ui.express.mvp.presenter.impl.ISendMailPresenterImpl;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.LogUtils;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.city.CityPicker;
import com.yusong.community.utils.city.CityUtils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.yusong.community.R.id.tv_corporation;
import static com.yusong.community.R.id.tv_money;

/**
 * <ul>
 * <li>功能说明：快柜寄件界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class SendMailActivity extends BaseActivity implements View.OnClickListener, ISendMailView {
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_name)
    TextView mTvName;
    @InjectView(R.id.tv_phone)
    TextView mTvPhone;
    @InjectView(R.id.tv_senderName)
    TextView mTvSenderName;
    @InjectView(R.id.tv_senderPhone)
    TextView mTvSenderPhone;
    @InjectView(R.id.tv_senderAddress)
    TextView mTvSenderAddress;
    @InjectView(R.id.ll_senderAddress)
    LinearLayout mLlSenderAddress;
    @InjectView(R.id.tv_info)
    TextView mTvInfo;
    @InjectView(R.id.tv_address)
    TextView mTvAddress;
    @InjectView(R.id.tv_type)
    TextView mTvType;
    @InjectView(R.id.ll_type)
    LinearLayout mLlType;
    @InjectView(R.id.tv_number)
    TextView mTvNumber;
    @InjectView(R.id.ll_number)
    LinearLayout mLlNumber;
    @InjectView(R.id.ll_sender)
    LinearLayout mLlSender;
    @InjectView(R.id.ll_get)
    LinearLayout mLlGet;
    @InjectView(R.id.ll_address)
    LinearLayout mLlAddress;
    @InjectView(R.id.btn_commit)
    Button mBtnCommit;
    @InjectView(R.id.tv_senderInfo)
    TextView mTvSenderInfo;
    @InjectView(tv_corporation)
    TextView mTvCorporation;
    @InjectView(R.id.tv_box)
    TextView mTvBox;
    @InjectView(tv_money)
    TextView mTvMoney;
    @InjectView(R.id.pop_view)
    View mPopView;
    @InjectView(R.id.rl_sender)
    RelativeLayout mRlSender;
    @InjectView(R.id.rl_get)
    RelativeLayout mRlGet;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    private String mPhone;
    private String mName;
    private String mAddress;
    private String mCity;
    private String mSenderPhone;
    private String mSenderName;
    private String mSenderAddress;
    private String mSenderCity;
    private boolean mIs_save;
    boolean mSenderIs_save;
    private ISendMailPresenterImpl mPresenter;

    private String province = "浙江省";
    private String city = "杭州市";
    private String couny = "西湖区";

    String thing;
    String thingAmount;


    String senderProvince;
    String senderCity;
    String senderDistrict;

    String receiverProvince;
    String receiverCity;
    String receiverDistrict;
    CityPicker cityPicker = null;

    private List<String> mThingType;
    private List<ConfigInfo.Express> mCompanyList;
    private List<ConfigInfo.Box> mBoxType;
    private List<String> boxType_list = new ArrayList<>();
    private List<String> company_list = new ArrayList<>();
    private List<Integer> companyId_list = new ArrayList<>();
    private List<Integer> boxTypeId_list = new ArrayList<>();
    int companyId;
    int boxType;
    private int sender_id = -1;
    private int get_id = -1;

    public static boolean flag = true;
    private int mCharge;
    private int mInfo_id = -1;

    /**
     * 填写信息返回结果
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //寄件填写信息
        if (requestCode == ActivityConstants.INFO_SENDER_REQUEST
                && resultCode == ActivityConstants.INFO_SENDER_RESPONS) {

            mSenderPhone = data.getStringExtra(ActivityConstants.INFO_PHONE);
            mSenderName = data.getStringExtra(ActivityConstants.INFO_NAME);
            mSenderAddress = data.getStringExtra(ActivityConstants.INFO_ADDRESS);
            mSenderCity = data.getStringExtra(ActivityConstants.INFO_CITY);
            mSenderIs_save = data.getBooleanExtra(ActivityConstants.INFO_IS_SAVE, false);
            province = data.getStringExtra(ActivityConstants.PROVINCE);
            city = data.getStringExtra(ActivityConstants.CITY);
            couny = data.getStringExtra(ActivityConstants.COUNY);
            senderProvince = data.getStringExtra(ActivityConstants.PROVINCE_CODE);
            senderCity = data.getStringExtra(ActivityConstants.CITY_CODE);
            senderDistrict = data.getStringExtra(ActivityConstants.DISTRICT_CODE);
            mInfo_id = data.getIntExtra(ActivityConstants.INFO_ID, -1);

            if (!StringUtils.isEmpty(province)) {
                View contentView = LayoutInflater.from(this).inflate(R.layout.activity_cities_layout, null);
                cityPicker = (CityPicker) contentView.findViewById(R.id.cp_citypicker);
                int Pindex = CityUtils.provinceFindIndex(province);
                int Cindex = CityUtils.cityFindIndex(Pindex, city);
                int CCindex = CityUtils.counyFindIndex(Pindex, Cindex, couny);
                cityPicker.SetProvince(Pindex, Cindex, CCindex);
                senderProvince = cityPicker.getprovince_code() + "0000";
                senderCity = cityPicker.getcity_code() + "00";
                senderDistrict = cityPicker.getcouny_code() + "";
            }
            //显示信息
            showSenderInfo();

            if (mTvInfo.getVisibility() == View.GONE) {
                String box = mTvBox.getText().toString().trim();
                String corporation = mTvCorporation.getText().toString().trim();
                String number = mTvNumber.getText().toString().trim();
                String type = mTvType.getText().toString().trim();
                if (!StringUtils.isEmpty(box) && !StringUtils.isEmpty(corporation)
                        && !StringUtils.isEmpty(number) && !StringUtils.isEmpty(type)) {
                    queryRates();
                }
            }
            LogUtils.kdg_log("收到返回寄件消息 name = " + mSenderName + ", phone = " +
                    mSenderPhone + ", address = " + mSenderAddress + ", city = " +
                    mSenderCity + ", is_save =" + mSenderIs_save + ", senderProvince = " + senderProvince
                    + ", senderCity = " + senderCity + ", senderDistrict =" + senderDistrict);
        }

        //收件填写信息
        if (requestCode == ActivityConstants.INFO_GET_REQUEST
                && resultCode == ActivityConstants.INFO_GET_RESPONS) {
            mPhone = data.getStringExtra(ActivityConstants.INFO_PHONE);
            mCity = data.getStringExtra(ActivityConstants.INFO_CITY);
            mName = data.getStringExtra(ActivityConstants.INFO_NAME);
            mAddress = data.getStringExtra(ActivityConstants.INFO_ADDRESS);
            mIs_save = data.getBooleanExtra(ActivityConstants.INFO_IS_SAVE, false);
            province = data.getStringExtra(ActivityConstants.PROVINCE);
            city = data.getStringExtra(ActivityConstants.CITY);
            couny = data.getStringExtra(ActivityConstants.COUNY);
            receiverProvince = data.getStringExtra(ActivityConstants.PROVINCE_CODE);
            receiverCity = data.getStringExtra(ActivityConstants.CITY_CODE);
            receiverDistrict = data.getStringExtra(ActivityConstants.DISTRICT_CODE);
            mInfo_id = data.getIntExtra(ActivityConstants.INFO_ID, -1);

            if (!StringUtils.isEmpty(province)) {
                View contentView = LayoutInflater.from(this).inflate(R.layout.activity_cities_layout, null);
                cityPicker = (CityPicker) contentView.findViewById(R.id.cp_citypicker);
                int Pindex = CityUtils.provinceFindIndex(province);
                int Cindex = CityUtils.cityFindIndex(Pindex, city);
                int CCindex = CityUtils.counyFindIndex(Pindex, Cindex, couny);
                cityPicker.SetProvince(Pindex, Cindex, CCindex);
                receiverProvince = cityPicker.getprovince_code() + "0000";
                receiverCity = cityPicker.getcity_code() + "00";
                receiverDistrict = cityPicker.getcouny_code() + "";
            }
            //显示信息
            showGetInfo();
            if (mTvSenderInfo.getVisibility() == View.GONE) {
                String box = mTvBox.getText().toString().trim();
                String corporation = mTvCorporation.getText().toString().trim();
                String number = mTvNumber.getText().toString().trim();
                String type = mTvType.getText().toString().trim();
                if (!StringUtils.isEmpty(box) && !StringUtils.isEmpty(corporation)
                        && !StringUtils.isEmpty(number) && !StringUtils.isEmpty(type)) {
                    queryRates();
                }
            }
            LogUtils.kdg_log("收到返回寄件消息 name = " + mName + ", phone = " +
                    mPhone + ", address = " + mAddress + ", city = " + mCity + ", is_save =" + mIs_save
                    + ", senderProvince = " + receiverProvince
                    + ", senderCity = " + receiverCity + ", senderDistrict =" + receiverDistrict);
        }
    }


    @Override
    public void onClick(View view) {
        int visibility;
        Intent mIntent = null;
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;

            case R.id.rl_sender://寄件人信息填写
                visibility = mTvSenderInfo.getVisibility();//返回值为0，visible；返回值为4，invisible；返回值为8，gone。

                mIntent = new Intent(SendMailActivity.this, FillInfoActivity.class);
                if (visibility == 0) {
                    mIntent.putExtra(ActivityConstants.INFO_FILL, 1);
                } else if (visibility == 8) {
                    mIntent.putExtra(ActivityConstants.INFO_FILL, 6);

                    if (mInfo_id != -1) {
                        mIntent.putExtra(ActivityConstants.GET_CONID, mInfo_id);
                    } else {
                        mIntent.putExtra(ActivityConstants.SENDER_CONID, sender_id);
                    }
                }

                if (!StringUtils.isEmpty(mSenderName) &&
                        !StringUtils.isEmpty(mSenderCity) && !StringUtils.isEmpty(mSenderAddress)
                        && !StringUtils.isEmpty(mSenderPhone)) {
                    mIntent.putExtra(ActivityConstants.INFO_NAME, mSenderName);
                    mIntent.putExtra(ActivityConstants.INFO_ADDRESS, mSenderAddress);
                    mIntent.putExtra(ActivityConstants.INFO_PHONE, mSenderPhone);
                    mIntent.putExtra(ActivityConstants.INFO_CITY, mSenderCity);
                    mIntent.putExtra(ActivityConstants.PROVINCE_CODE, senderProvince);
                    mIntent.putExtra(ActivityConstants.CITY_CODE, senderCity);
                    mIntent.putExtra(ActivityConstants.DISTRICT_CODE, senderDistrict);
                }
                mIntent.putExtra(ActivityConstants.CITY, city);
                mIntent.putExtra(ActivityConstants.PROVINCE, province);
                mIntent.putExtra(ActivityConstants.COUNY, couny);
                startActivityForResult(mIntent, ActivityConstants.INFO_SENDER_REQUEST);

                break;
            case R.id.rl_get://收件人信息填写  返回值为0，visible；返回值为4，invisible；返回值为8，gone。
                visibility = mTvInfo.getVisibility();
                mIntent = new Intent(SendMailActivity.this, FillInfoActivity.class);
                if (visibility == 0) {
                    mIntent.putExtra(ActivityConstants.INFO_FILL, 2);
                } else if (visibility == 8) {
                    mIntent.putExtra(ActivityConstants.INFO_FILL, 7);
                    if (mInfo_id != -1) {

                        mIntent.putExtra(ActivityConstants.GET_CONID, mInfo_id);
                    } else {
                        mIntent.putExtra(ActivityConstants.GET_CONID, get_id);
                    }
                }
                if (!StringUtils.isEmpty(mName) &&
                        !StringUtils.isEmpty(mCity) && !StringUtils.isEmpty(mAddress)
                        && !StringUtils.isEmpty(mPhone)) {
                    mIntent.putExtra(ActivityConstants.INFO_NAME, mName);
                    mIntent.putExtra(ActivityConstants.INFO_ADDRESS, mAddress);
                    mIntent.putExtra(ActivityConstants.INFO_PHONE, mPhone);
                    mIntent.putExtra(ActivityConstants.INFO_CITY, mCity);
                    mIntent.putExtra(ActivityConstants.PROVINCE_CODE, receiverProvince);
                    mIntent.putExtra(ActivityConstants.CITY_CODE, receiverCity);
                    mIntent.putExtra(ActivityConstants.DISTRICT_CODE, receiverDistrict);

                }
                mIntent.putExtra(ActivityConstants.CITY, city);
                mIntent.putExtra(ActivityConstants.PROVINCE, province);
                mIntent.putExtra(ActivityConstants.COUNY, couny);
                startActivityForResult(mIntent, ActivityConstants.INFO_GET_REQUEST);

                break;
            case R.id.ll_senderAddress://地址簿

                mIntent = new Intent(SendMailActivity.this, AddressActivity.class);
//                mIntent.putExtra(ActivityConstants.INFO_FILL, 1);
                mIntent.putExtra(ActivityConstants.INFO_FILL, 10);
                flag = false;
                startActivityForResult(mIntent, ActivityConstants.INFO_SENDER_REQUEST);
                break;
            case R.id.ll_address://地址簿

                mIntent = new Intent(SendMailActivity.this, AddressActivity.class);
//                mIntent.putExtra(ActivityConstants.INFO_FILL, 2);
                mIntent.putExtra(ActivityConstants.INFO_FILL, 11);
                flag = false;
                startActivityForResult(mIntent, ActivityConstants.INFO_GET_REQUEST);
                break;
            case R.id.btn_commit://提交

                String corporation = mTvCorporation.getText().toString().trim();
                String box = mTvBox.getText().toString().trim();
                boolean isEmpty = mPresenter.isEmpty(mSenderAddress, mAddress,
                        thing, thingAmount, corporation, box);

                if (isEmpty) {

                    if (mCharge == 0) {
                        ToastUtils.showShort(getApplicationContext(), "该地区暂无资费标准");
                        return;
                    }

                    mPresenter.commitOrder(companyId, thing, mSenderName, mSenderPhone, senderProvince, senderCity
                            , senderDistrict, mSenderAddress, mName, mPhone, receiverProvince,
                            receiverCity, receiverDistrict, mAddress, Integer.parseInt(thingAmount), mCharge * 100, boxType);
                }
                break;
        }
    }


    /**
     * 显示收件人信息
     */
    private void showGetInfo() {
        mTvInfo.setVisibility(View.GONE);
        mLlGet.setVisibility(View.VISIBLE);
        mTvName.setText(mName);
        mTvAddress.setText(mCity + mAddress);
        mTvPhone.setText(mPhone);
    }

    /**
     * 显示寄件人信息
     */
    private void showSenderInfo() {
        mTvSenderInfo.setVisibility(View.GONE);
        mLlSender.setVisibility(View.VISIBLE);
        mTvSenderName.setText(mSenderName);
        mTvSenderAddress.setText(mSenderCity + mSenderAddress);
        mTvSenderPhone.setText(mSenderPhone);
    }


    @Override
    protected void initListener() {
        mRlSender.setOnClickListener(this);
        mRlGet.setOnClickListener(this);
        mLlType.setOnClickListener(this);
        mLlNumber.setOnClickListener(this);
        mLlSenderAddress.setOnClickListener(this);
        mLlAddress.setOnClickListener(this);
        mLlBack.setOnClickListener(this);
        mBtnCommit.setOnClickListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_sendmail;
    }

    @Override
    public void initView() {

        mPresenter = new ISendMailPresenterImpl(this, this);
        initText();
    }


    /**
     * 显示选择箱子
     *
     * @param view
     */
    public void boxDialog(View view) {

        if (boxType_list != null && boxType_list.size() > 0) {
            showPopupWindow(4, boxType_list);
        } else {
            mPresenter.queryConfig();
        }
    }

    /**
     * 显示选择物品数量
     *
     * @param view
     */
    public void numberDialog(View view) {

        showPopupWindow(2, createNumberArrays());

    }

    /**
     * 显示选择物品类型
     *
     * @param view
     */
    public void typeDialog(View view) {
        if (mThingType != null && mThingType.size() > 0) {
            showPopupWindow(1, mThingType);
        } else {
            mPresenter.queryConfig();
        }
    }

    /**
     * 选择快递公司
     *
     * @param view
     */
    public void corporationDialog(View view) {
        if (company_list != null && company_list.size() > 0) {
            showPopupWindow(3, company_list);
        } else {
            mPresenter.queryConfig();
        }
    }


    private void showPopupWindow(final int number, List<String> data) {

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

                if (number == 1) {
                    mTvType.setText((CharSequence) wheelView.getSelectionItem());
                    thing = (String) wheelView.getSelectionItem();
                    queryRates(mTvNumber, mTvCorporation, mTvBox);
                    window.dismiss();
                    return;
                }
                if (number == 2) {
                    mTvNumber.setText((CharSequence) wheelView.getSelectionItem());
                    thingAmount = (String) wheelView.getSelectionItem();
                    queryRates(mTvType, mTvCorporation, mTvBox);
                    window.dismiss();
                    return;
                }
                if (number == 3) {
                    mTvCorporation.setText((CharSequence) wheelView.getSelectionItem());
                    Observable.from(mCompanyList)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<ConfigInfo.Express>() {
                                @Override
                                public void call(ConfigInfo.Express express) {

                                    String tx = mTvCorporation.getText().toString().trim();
                                    String name = express.getCompanyName();
                                    if (tx.equals(name)) {
                                        companyId = express.getId();
                                        queryRates(mTvNumber, mTvType, mTvBox);
                                    }
                                    window.dismiss();
                                    return;
                                }
                            });

                }
                if (number == 4) {
                    mTvBox.setText((CharSequence) wheelView.getSelectionItem());

                    Observable.from(mBoxType)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<ConfigInfo.Box>() {
                                @Override
                                public void call(ConfigInfo.Box box) {
                                    String tx = mTvBox.getText().toString().trim();
                                    String type = box.getType();
                                    if (tx.equals(type)) {
                                        boxType = box.getId();
                                        queryRates(mTvNumber, mTvCorporation, mTvType);
                                    }
                                    window.dismiss();
                                    return;
                                }
                            });
                }
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
        int[] xy = new int[2];
        mPopView.getLocationOnScreen(xy);
        window.showAtLocation(mPopView, Gravity.CENTER | Gravity.BOTTOM, 0, 0);
    }


    private void queryRates(TextView tv_1, TextView tv_2, TextView tv_3) {
        int sender_visibility = mTvSenderInfo.getVisibility();
        int visibility = mTvInfo.getVisibility();
        String tx1 = tv_1.getText().toString().trim();
        String tx2 = tv_2.getText().toString().trim();
        String tx3 = tv_3.getText().toString().trim();
        //如果全部填写完毕 请求查询资费
        if (sender_visibility == 8 && visibility == 8
                && !StringUtils.isEmpty(tx1) && !StringUtils.isEmpty(tx2)
                && !StringUtils.isEmpty(tx3)) {
            mPresenter.queryRates(companyId, Integer.parseInt(senderProvince), Integer.parseInt(senderCity),
                    Integer.parseInt(receiverProvince), Integer.parseInt(receiverCity), boxType);
        }
    }

    private void queryRates() {
        mPresenter.queryRates(companyId, Integer.parseInt(senderProvince), Integer.parseInt(senderCity),
                Integer.parseInt(receiverProvince), Integer.parseInt(receiverCity), boxType);

    }


    /**
     * 初始化显示 标题 红色部分。
     */
    private void initText() {
        mTvTitle.setText("快柜寄件");
    }


    //物品数量
    private ArrayList<String> createNumberArrays() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i <= 100; i++) {
            list.add(i + "");
        }
        return list;
    }


    @Override
    public void initData() {
        mPresenter.queryConfig();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        flag = true;
    }


    /**
     * 提交成功 跳转提交成功界面
     */
    @Override
    public void commitSuccess(CommitOrderResult data) {
        int charge = data.getCharge();
        String id = data.getId();
        Intent mIntent = new Intent(this, CommonPayActivity.class);
        mIntent.putExtra(ActivityConstants.CHARGE, charge / 100 + "");
        mIntent.putExtra(ActivityConstants.COMMON_PAYTYPE, ActivityConstants.SENDER_ORDER);
        mIntent.putExtra(ActivityConstants.ORDER_ID, id);
        startActivity(mIntent);
        finish();

    }


    /**
     * 保存配置选项
     *
     * @param data
     */
    @Override
    public void saveConfig(ConfigInfo data) {
        //物品类型
        mThingType = data.getThingType();

        //快递公司
        mCompanyList = data.getCompanyList();

        //box类型大小
        mBoxType = data.getBoxType();


        for (int i = 0; i < mCompanyList.size(); i++) {
            ConfigInfo.Express express = mCompanyList.get(i);
            company_list.add(express.getCompanyName());
            companyId_list.add(express.getId());
        }

        for (int i = 0; i < mBoxType.size(); i++) {
            ConfigInfo.Box box = mBoxType.get(i);
            boxType_list.add(box.getType());
            boxTypeId_list.add(box.getId());
        }

    }


    /**
     * 显示资费
     *
     * @param charge
     */
    @Override
    public void showRates(int charge) {
        mCharge = charge;
        mTvMoney.setText(charge + "元");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag) {
            mPresenter.queryContact(1);
            mPresenter.queryContact(2);
        }
    }

    /**
     * 设置默认显示
     *
     * @param group
     */
    @Override
    public void setDftInfo(ContactGroup group) {

        int receiverFlag = group.getFavoriteReceiverFlag();
        int senderFlag = group.getFavoriteSenderFlag();

        if (receiverFlag == 1) {
            mPhone = group.getMobile();
            mName = group.getContactName();
            mAddress = group.getStreet();
            mCity = group.getProvinceName() + group.getCityName() + group.getDistrictName();
            province = group.getProvinceName();
            city = group.getCityName();
            couny = group.getDistrictName();
            receiverProvince = group.getProvince();
            receiverCity = group.getCity();
            receiverDistrict = group.getDistrict();
            get_id = group.getId();
            //显示信息
            showGetInfo();

        }

        if (senderFlag == 1) {
            mSenderPhone = group.getMobile();
            mSenderName = group.getContactName();
            mSenderAddress = group.getStreet();
            mSenderCity = group.getProvinceName() + group.getCityName() + group.getDistrictName();
            province = group.getProvinceName();
            city = group.getCityName();
            couny = group.getDistrictName();
            senderProvince = group.getProvince();
            senderCity = group.getCity();
            senderDistrict = group.getDistrict();
            sender_id = group.getId();
            //显示信息
            showSenderInfo();
        }
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }

}
