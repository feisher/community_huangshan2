package com.yusong.club.ui.community_service.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.yusong.club.R;
import com.yusong.club.pay.CommonPayActivity;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.charge.view.BaseDialog;
import com.yusong.club.ui.community_service.mvp.ImplView.CreateOrderView;
import com.yusong.club.ui.community_service.mvp.presenter.ImplPresenter.CreateOrderPresenterImpl;
import com.yusong.club.ui.express.activity.AddressActivity;
import com.yusong.club.ui.express.mvp.entity.ContactGroup;
import com.yusong.club.ui.express.mvp.implView.IAddressView;
import com.yusong.club.ui.express.mvp.presenter.impl.IAddressPresenterImpl;
import com.yusong.club.ui.shoppers.bean.CommodityBean;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.DateUtil;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.glide.GlideImgManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         created at 2017/3/7 9:36.
 *         提交服务订单
 */

public class ServiceConfirmOrderActivity extends BaseActivity implements IAddressView, CreateOrderView {
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
    @InjectView(R.id.name_and_phone_tv)
    TextView nameAndPhoneTv;
    @InjectView(R.id.shouhuo_address)
    TextView shouhuoAddress;
    @InjectView(R.id.address_layout)
    RelativeLayout addressLayout;
    @InjectView(R.id.dian_pu_name_tv)
    TextView dianPuNameTv;
    @InjectView(R.id.commodity_image)
    ImageView commodityImage;
    @InjectView(R.id.commodity_details_tv)
    TextView commodityDetailsTv;
    @InjectView(R.id.commodity_price)
    TextView commodityPrice;
    @InjectView(R.id.send_time_tv)
    TextView sendTimeTv;
    @InjectView(R.id.send_time_layout)
    RelativeLayout sendTimeLayout;
    @InjectView(R.id.pinlun_et)
    EditText pinlunEt;
    @InjectView(R.id.all_price_tv)
    TextView allPriceTv;
    @InjectView(R.id.confirm_order_btn)
    Button confirmOrderBtn;
    private IAddressPresenterImpl iAddressPresenterImpl;//查询常用联系人
    private CreateOrderPresenterImpl mPresenter;
    private CommodityBean commodityBean;
    private ContactGroup address;//收件地址
    private String sendTime = "";//配送时间


    @OnClick({R.id.ll_back, R.id.confirm_order_btn, R.id.address_layout, R.id.send_time_layout})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.confirm_order_btn:
                if (address != null) {
                    mPresenter.createServiceOrder(1, commodityBean.getId(),
                            commodityBean.getPrice(), address.getProvince(), address.getCity(), address.getDistrict(),
                            address.getStreet(), address.getMobile(), address.getContactName(), sendTime,
                            pinlunEt.getText().toString().toString());
                } else {
                    ToastUtils.showShort(this, "请完善地址信息");
                }

                break;
            case R.id.address_layout:
                Intent mIntent = new Intent(ServiceConfirmOrderActivity.this, AddressActivity.class);
                mIntent.putExtra(ActivityConstants.INFO_FILL, 4);
                startActivityForResult(mIntent, ActivityConstants.INFO_GET_REQUEST);
                break;
            case R.id.send_time_layout:
                DatePickDialog dialog = new DatePickDialog(this);
                //设置上下年分限制
                dialog.setYearLimt(0);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMDHM);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd HH:mm");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        if (DateUtil.compareDate(formatter.format(date), formatter.format(new Date()))) {
                            sendTime = formatter.format(date);
                            sendTimeTv.setText(formatter1.format(date));
                        } else {
                            ToastUtils.showShort(ServiceConfirmOrderActivity.this, "服务时间不能小于当前时间");
                        }
                    }
                });
                dialog.show();
                break;
        }

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_service_confirm_order;
    }

    @Override
    public void initView() {
        commodityBean = (CommodityBean) getIntent().getSerializableExtra("CommodityBean");
        tvTitle.setText("确认订单");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sendTime = formatter.format(new Date());
        sendTimeTv.setText(sendTime);
        if (commodityBean.getImageList().length > 0) {
            GlideImgManager.loadImage(this, commodityBean.getImageList()[0], commodityImage);
        } else {
            GlideImgManager.loadImage(this, "", commodityImage);
        }
        dianPuNameTv.setText(commodityBean.getItemName());
        commodityDetailsTv.setText(commodityBean.getIntroduction());
        commodityPrice.setText("￥ " + commodityBean.getPrice());
        allPriceTv.setText("￥ " + commodityBean.getPrice());
        iAddressPresenterImpl = new IAddressPresenterImpl(this, ServiceConfirmOrderActivity.this);
        iAddressPresenterImpl.queryContact(2);
        mPresenter = new CreateOrderPresenterImpl(this, this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityConstants.INFO_GET_REQUEST
                && resultCode == ActivityConstants.INFO_GET_RESPONS) {
            address = (ContactGroup) data.getSerializableExtra("ContactGroup");
            nameAndPhoneTv.setText(address.getContactName() + "    " + address.getMobile());
            shouhuoAddress.setText("收货地址:  " + address.getProvinceName() + address.getCityName() + address.getDistrictName() + address.getStreet());
        } else if (requestCode == ActivityConstants.REQUEST_CODE && ActivityConstants.RESULT_CODE == resultCode) {
            setResult(ActivityConstants.RESULT_CODE);
            this.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }


    @Override
    public void setContactAdapter(List<ContactGroup> data) {//地址
        if (data == null) return;
        for (ContactGroup contactGroup : data) {
            if (contactGroup.getFavoriteReceiverFlag() == 1) {
                address = contactGroup;
            }
        }
        if (address != null) {
            nameAndPhoneTv.setText(address.getContactName() + "    " + address.getMobile());
            shouhuoAddress.setText("收货地址:  " + address.getProvinceName() + address.getCityName() + address.getDistrictName() + address.getStreet());
        } else {
            showDialog();//跳转设置地址
        }
    }

    private void showDialog() {
        final BaseDialog dialog = new BaseDialog(this);
        dialog.setTitle("温馨提示");
        dialog.setMessage("您还没有设置默认收货地址，是否前往设置？");
        dialog.setNegativeButton("不了", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("前往", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(ServiceConfirmOrderActivity.this, AddressActivity.class);
                mIntent.putExtra(ActivityConstants.INFO_FILL, 3);
                startActivityForResult(mIntent, 200);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void showProgressDialog() {

    }


    @Override
    public void close() {

    }

    @Override
    public void result(int respons, Intent intent) {

    }

    @Override
    public void notifyAdapter() {

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
    public void createOrderSucces(String orderId) {
        Intent intent = new Intent(this, CommonPayActivity.class);
        intent.putExtra(ActivityConstants.ORDER_ID, orderId);
        intent.putExtra(ActivityConstants.CHARGE, commodityBean.getPrice() + "");
        intent.putExtra(ActivityConstants.COMMON_PAYTYPE, ActivityConstants.SERVICE_OERDER);
        startActivityForResult(intent, ActivityConstants.REQUEST_CODE);
    }
}
