package com.yusong.community.ui.express.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.express.mvp.implView.IFillInfoView;
import com.yusong.community.ui.express.mvp.presenter.impl.IFillInfoPresenterImpl;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.LogUtils;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.city.CityPicker;
import com.yusong.community.utils.city.CityUtils;

import org.apache.commons.lang.StringUtils;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.yusong.community.R.id.cb_dftSender;
import static com.yusong.community.R.id.ll_dft;
import static com.yusong.community.R.id.tv_city;


/**
 * <ul>
 * <li>功能说明：修改、新建联系人界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class FillInfoActivity extends BaseActivity implements IFillInfoView {

    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.rl_city)
    RelativeLayout mRlCity;
    @InjectView(R.id.et_address)
    EditText mEtAddress;
    @InjectView(R.id.btn_ok)
    Button mBtnOk;
    @InjectView(tv_city)
    TextView mTvCity;
    @InjectView(R.id.et_name)
    EditText mEtName;
    @InjectView(R.id.et_phone)
    EditText mEtPhone;
    @InjectView(cb_dftSender)
    CheckBox mCbDftSender;
    @InjectView(R.id.cb_dftGet)
    CheckBox mCbDftGet;
    @InjectView(ll_dft)
    LinearLayout mLlDft;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    @InjectView(R.id.pop_view)
    View mPopView;

    private int info_fill;

    private String mPhone;
    private String mName;
    private String mAddress;
    private String mCity;
    String mSenderPhone;
    String mSenderName;
    String mSenderAddress;
    private Intent mIntent;

    private String province = "浙江省";
    private String city = "杭州市";
    private String couny = "西湖区";
    private CityPicker cityPicker;
    String provinceCode;
    String cityCode;
    String districtCode;
    private IFillInfoPresenterImpl mPresenter;
    private int mContact_id;
    private String receiverDistrict;
    private String mSenderCity;
    private String receiverProvince;
    private String receiverCity;
    private int sender_id;
    private int get_id;
    private int request_id;
    private int type = 0;


    @OnClick({R.id.ll_back, R.id.rl_city, cb_dftSender, R.id.cb_dftGet, R.id.btn_ok})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.rl_city://城市三级联动选择
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                PopupWindow popupWindow = makePopupWindow(this);
                int[] xy = new int[2];
                mBtnOk.getLocationOnScreen(xy);
                popupWindow.showAtLocation(mBtnOk, Gravity.CENTER | Gravity.BOTTOM, 0, 0);

                break;
            case R.id.ll_back://返回
                finish();
                break;
            case R.id.btn_ok://确定
                if (get_id == -1) {

                    request_id = sender_id;
                } else if (sender_id == -1) {
                    request_id = get_id;
                }
                mPresenter.commit(mEtName, mEtPhone, mTvCity, mEtAddress,
                        mCbDftSender, mCbDftGet, info_fill,
                        province, city, couny, provinceCode, cityCode, districtCode, mContact_id, request_id);
                break;
            case cb_dftSender:
                mCbDftGet.setChecked(false);
                mCbDftSender.setChecked(true);
                break;
            case R.id.cb_dftGet:
                mCbDftSender.setChecked(false);
                mCbDftGet.setChecked(true);
                break;

        }

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_fillinfo;
    }

    @Override
    public void initView() {

        mPresenter = new IFillInfoPresenterImpl(this, this);

        mIntent = getIntent();

        if (mIntent != null) {
            info_fill = mIntent.getIntExtra(ActivityConstants.INFO_FILL, 1);
            province = mIntent.getStringExtra(ActivityConstants.PROVINCE);
            city = mIntent.getStringExtra(ActivityConstants.CITY);
            couny = mIntent.getStringExtra(ActivityConstants.COUNY);
            mPhone = mIntent.getStringExtra(ActivityConstants.INFO_PHONE);
            mCity = mIntent.getStringExtra(ActivityConstants.INFO_CITY);
            mName = mIntent.getStringExtra(ActivityConstants.INFO_NAME);
            mAddress = mIntent.getStringExtra(ActivityConstants.INFO_ADDRESS);
            receiverProvince = mIntent.getStringExtra(ActivityConstants.PROVINCE_CODE);
            receiverCity = mIntent.getStringExtra(ActivityConstants.CITY_CODE);
            receiverDistrict = mIntent.getStringExtra(ActivityConstants.DISTRICT_CODE);
            sender_id = mIntent.getIntExtra(ActivityConstants.SENDER_CONID, -1);
            get_id = mIntent.getIntExtra(ActivityConstants.GET_CONID, -1);
            type = mIntent.getIntExtra(ActivityConstants.TYPE, -1);

            LogUtils.kdg_log("info_fill = " + info_fill + ",  province " + province + ",, city " + city + ",, couny " + couny
                    + ",, mPhone " + mPhone + ",, mCity " + mCity + ",, mName " + mName + ",, mAddress " + mAddress
                    + ",, sender_id " + sender_id + ",, get_id " + get_id);
        }
        initTitle();

    }


    /**
     * 用于回显数据
     */
    private void showGetInfo() {
        mPhone = mIntent.getStringExtra(ActivityConstants.INFO_PHONE);
        mCity = mIntent.getStringExtra(ActivityConstants.INFO_CITY);
        mName = mIntent.getStringExtra(ActivityConstants.INFO_NAME);
        mAddress = mIntent.getStringExtra(ActivityConstants.INFO_ADDRESS);
        if (!StringUtils.isEmpty(mName) &&
                !StringUtils.isEmpty(mCity) && !StringUtils.isEmpty(mAddress)
                && !StringUtils.isEmpty(mPhone)) {
            mEtAddress.setText(mAddress);
            mEtPhone.setText(mPhone);
            mEtName.setText(mName);
            mTvCity.setText(mCity);
        }
    }

    /**
     * 用于回显数据
     */
    private void showSenderInfo() {
        mSenderPhone = mIntent.getStringExtra(ActivityConstants.INFO_PHONE);
        mSenderName = mIntent.getStringExtra(ActivityConstants.INFO_NAME);
        mSenderAddress = mIntent.getStringExtra(ActivityConstants.INFO_ADDRESS);
        mSenderCity = mIntent.getStringExtra(ActivityConstants.INFO_CITY);
        if (!StringUtils.isEmpty(mSenderName) &&
                !StringUtils.isEmpty(mSenderCity) && !StringUtils.isEmpty(mSenderAddress)
                && !StringUtils.isEmpty(mSenderPhone)) {
            mEtAddress.setText(mSenderAddress);
            mEtPhone.setText(mSenderPhone);
            mEtName.setText(mSenderName);
            mTvCity.setText(mSenderCity);
        }
    }

    /**
     * 底部弹出省市区
     *
     * @param cx 上下文
     * @return 弹窗对象
     */
    private PopupWindow makePopupWindow(Context cx) {
        final PopupWindow window;

        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_cities_layout, null);
        contentView.getBackground().setAlpha(100);
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        cityPicker = (CityPicker) contentView.findViewById(R.id.cp_citypicker);
        int Pindex = CityUtils.provinceFindIndex(province);
        int Cindex = CityUtils.cityFindIndex(Pindex, city);
        int CCindex = CityUtils.counyFindIndex(Pindex, Cindex, couny);
        cityPicker.SetProvince(Pindex, Cindex, CCindex);

        TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                province = cityPicker.getprovince_string();
                city = cityPicker.getcity_string();
                couny = cityPicker.getcouny_string();
                if (couny.equals("市辖区")) {
                    ToastUtils.showShort(FillInfoActivity.this, "请选择区");
                } else {
                    mTvCity.setText(province + city + couny);
                    provinceCode = cityPicker.getprovince_code() + "0000";
                    cityCode = cityPicker.getcity_code() + "00";
                    districtCode = cityPicker.getcouny_code() + "";
                    LogUtils.kdg_log("设置code为 省 = " + provinceCode + "市 = " + cityCode + "区 = " + districtCode);
                    window.dismiss();
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

        window.setBackgroundDrawable(new BitmapDrawable());
        window.setFocusable(true);
        window.setTouchable(true);
        window.setOutsideTouchable(true);
        window.showAtLocation(mPopView, Gravity.BOTTOM, 0, 0);
        return window;
    }

    @Override
    public void initData() {

    }


    /**
     * 初始化标题
     */
    private void initTitle() {

        if (info_fill != 2) {
            mCbDftSender.setChecked(true);
        }
        if (info_fill == 1) {
            mTvTitle.setText("填写寄件人");
            showSenderInfo();
            mLlDft.setVisibility(View.INVISIBLE);
        }

        if (info_fill == 2) {
            mTvTitle.setText("填写收件人");
            showGetInfo();
            mCbDftGet.setChecked(true);
            mLlDft.setVisibility(View.INVISIBLE);
        }

        if (info_fill == 3) {
            mTvTitle.setText("新建联系人");
        }

        if (info_fill == 4) {
            mTvTitle.setText("修改联系人");
            mContact_id = mIntent.getIntExtra(ActivityConstants.CONTACT_ID, 1);
            showGetInfo();
        }

        if (info_fill == 6) {
            mTvTitle.setText("修改联系人");
            mCbDftGet.setChecked(false);
            mCbDftSender.setChecked(true);
            mLlDft.setVisibility(View.INVISIBLE);
            showSenderInfo();
        }

        if (info_fill == 7) {
            mTvTitle.setText("修改联系人");
            mCbDftSender.setChecked(false);
            mCbDftGet.setChecked(true);
            mLlDft.setVisibility(View.INVISIBLE);
            showGetInfo();
        }

        if (StringUtils.isEmpty(province)) {
            province = "浙江省";
            city = "杭州市";
            couny = "西湖区";
        }
        if (type == 1) {
            mCbDftGet.setChecked(false);
            mCbDftSender.setChecked(true);
        } else if (type == 2) {
            mCbDftGet.setChecked(true);
            mCbDftSender.setChecked(false);
        }

        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_cities_layout, null);
        cityPicker = (CityPicker) contentView.findViewById(R.id.cp_citypicker);
        int Pindex = CityUtils.provinceFindIndex(province);
        int Cindex = CityUtils.cityFindIndex(Pindex, city);
        int CCindex = CityUtils.counyFindIndex(Pindex, Cindex, couny);
        cityPicker.SetProvince(Pindex, Cindex, CCindex);
        provinceCode = cityPicker.getprovince_code() + "0000";
        cityCode = cityPicker.getcity_code() + "00";
        districtCode = cityPicker.getcouny_code() + "";
        LogUtils.kdg_log("设置code为 省 = " + provinceCode + "市 = " + cityCode + "区 = " + districtCode);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }


    @Override
    public void close() {
        finish();
    }

    @Override
    public void result(int respons, Intent intent) {
        setResult(respons, intent);
    }


    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
