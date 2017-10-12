package com.yusong.community.ui.shoppers.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.yusong.community.R;
import com.yusong.community.pay.CommonPayActivity;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.express.activity.AddressActivity;
import com.yusong.community.ui.express.mvp.entity.ContactGroup;
import com.yusong.community.ui.express.mvp.implView.IAddressView;
import com.yusong.community.ui.express.mvp.presenter.impl.IAddressPresenterImpl;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.CreataOrderBean;
import com.yusong.community.ui.shoppers.bean.OrderShopBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplCreateOrderView;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplCreateOrderPersenterImpl;
import com.yusong.community.ui.supermarket.mvp.ImolView.SuperMarketOrderView;
import com.yusong.community.ui.supermarket.mvp.presenter.impl.SuperMarketPresenterImpl;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.ArithUtil;
import com.yusong.community.utils.DateUtil;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.glide.GlideImgManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         created at 2017/3/7 9:36.
 *         提交订单
 */

public class ConfirmOrderActivity extends BaseActivity implements ImplCreateOrderView, IAddressView, SuperMarketOrderView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.name_and_phone_tv)
    TextView nameAndPhoneTv;
    @InjectView(R.id.shouhuo_address)
    TextView shouhuoAddress;
    @InjectView(R.id.dian_pu_name_tv)
    TextView dianPuNameTv;
    @InjectView(R.id.commodity_image)
    ImageView commodityImage;
    @InjectView(R.id.commodity_details_tv)
    TextView commodityDetailsTv;
    @InjectView(R.id.commodity_price)
    TextView commodityPrice;
    @InjectView(R.id.commodity_num)
    TextView commodityNum;
    @InjectView(R.id.jian_num_tv)
    ImageView jianNumTv;
    @InjectView(R.id.num_tv)
    TextView numTv;
    @InjectView(R.id.jia_num_tv)
    ImageView jiaNumTv;
    @InjectView(R.id.all_num_tv)
    TextView allNumTv;
    @InjectView(R.id.price_tv)
    TextView priceTv;
    @InjectView(R.id.all_price_tv)
    TextView allPriceTv;
    @InjectView(R.id.confirm_order_btn)
    Button confirmOrderBtn;
    @InjectView(R.id.address_layout)
    RelativeLayout addressLayout;
    @InjectView(R.id.pinlun_et)
    EditText pinlunEt;
    @InjectView(R.id.kuaidigui_check)
    CheckBox kuaidiguiCheck;
    @InjectView(R.id.ziti_check)
    CheckBox zitiCheck;
    @InjectView(R.id.send_time_tv)
    TextView sendTimeTv;
    @InjectView(R.id.send_time_layout)
    RelativeLayout sendTimeLayout;

    private ImplCreateOrderPersenterImpl implCreateOrderPersenterImpl;//下单
    private SuperMarketPresenterImpl superMarketPresenterImpl;
    private IAddressPresenterImpl iAddressPresenterImpl;//查询常用联系人
    private CommodityBean commodityBean;
    private ContactGroup address;//收件地址
    private int canQG = 0;
    private int num = 1;
    private int type = 1;//送货类型    快递柜or自提
    private String sendTime = "";//配送时间
    private int isSM = -1;
    private String specitication ="";


    @OnClick({R.id.ll_back, R.id.jia_num_tv, R.id.jian_num_tv, R.id.confirm_order_btn, R.id.address_layout, R.id.kuaidigui_check, R.id.ziti_check, R.id.send_time_layout})
    void click(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.jia_num_tv:
                if (num < canQG) {
                    ++num;
                    refreshNum(num);
                }
                break;
            case R.id.jian_num_tv:
                if (num > 1) {
                    --num;
                    refreshNum(num);
                }
                break;
            case R.id.confirm_order_btn:
                if (address != null) {
                    if (isSM != 3) {
                        if (commodityBean.getAmount() > 0) {
                            implCreateOrderPersenterImpl.createOrder(type, commodityBean.getId(), commodityBean.getItemId(), num,
                                    commodityBean.getPrice(), address.getProvince(), address.getCity(), address.getDistrict(),
                                    address.getStreet(), address.getMobile(), address.getContactName(), sendTime, pinlunEt.getText().toString().toString(),
                                    specitication);
                        } else {
                            implCreateOrderPersenterImpl.createOrder(type, null, commodityBean.getId(), num,
                                    commodityBean.getPrice(), address.getProvince(), address.getCity(), address.getDistrict(),
                                    address.getStreet(), address.getMobile(), address.getContactName(), sendTime, pinlunEt.getText().toString().toString(),
                                    specitication);
                        }
                    } else {
                        superMarketPresenterImpl.createSMOrder(type, commodityBean.getId(), num,
                                commodityBean.getPrice(), address.getProvince(), address.getCity(), address.getDistrict(),
                                address.getStreet(), address.getMobile(), address.getContactName(), sendTime, pinlunEt.getText().toString().toString(),specitication);
                    }

                } else {
                    ToastUtils.showShort(this, "请完善地址信息");
                }

                break;
            case R.id.address_layout:
                Intent mIntent = new Intent(ConfirmOrderActivity.this, AddressActivity.class);
                mIntent.putExtra(ActivityConstants.INFO_FILL, 4);
                startActivityForResult(mIntent, ActivityConstants.INFO_GET_REQUEST);
                break;
            case R.id.kuaidigui_check:
                kuaidiguiCheck.setChecked(true);
                zitiCheck.setChecked(false);
                type = 1;
                break;
            case R.id.ziti_check:
                zitiCheck.setChecked(true);
                kuaidiguiCheck.setChecked(false);
                type = 2;
                break;
            case R.id.send_time_layout:
                DatePickDialog dialog = new DatePickDialog(this);
                //设置上下年分限制
                dialog.setYearLimt(0);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        if (DateUtil.compareDate(formatter.format(date), formatter.format(new Date()))) {
                            sendTime = formatter.format(date);
                            sendTimeTv.setText(sendTime);
                        } else {
                            ToastUtils.showShort(ConfirmOrderActivity.this, "送货日期不得小于当天");
                        }
                    }
                });
                dialog.show();
                break;
        }

    }

    private void refreshNum(int num) {
        numTv.setText(String.valueOf(num));
        commodityNum.setText("x" + num);
        allNumTv.setText("共" + num + "件商品,合计:");
        allPriceTv.setText("￥" + ArithUtil.mul(commodityBean.getPrice(), num));
        priceTv.setText("￥" + ArithUtil.mul(commodityBean.getPrice(), num));

    }


    @Override
    protected int layoutId() {
        return R.layout.activity_shop_confirm_order;
    }

    @Override
    public void initView() {
        commodityBean = (CommodityBean) getIntent().getSerializableExtra("CommodityBean");
        canQG = getIntent().getIntExtra("canQG", -1);
        isSM = getIntent().getIntExtra("type", -1);
        specitication=getIntent().getStringExtra("standard");
        if (canQG == -1) {
            canQG = 100000000;
        }
        tvTitle.setText("确认订单");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        sendTime = formatter.format(new Date());
        sendTimeTv.setText(sendTime);
        kuaidiguiCheck.setChecked(true);
        if (commodityBean.getImageList().length > 0) {
            GlideImgManager.loadImage(this, commodityBean.getImageList()[0], commodityImage);
        } else {
            GlideImgManager.loadImage(this, "", commodityImage);
        }
        dianPuNameTv.setText(commodityBean.getItemName());
        commodityDetailsTv.setText(commodityBean.getIntroduction());
        commodityPrice.setText("￥ " + commodityBean.getPrice());
        priceTv.setText("￥ " + commodityBean.getPrice());
        allPriceTv.setText("￥ " + commodityBean.getPrice());
        implCreateOrderPersenterImpl = new ImplCreateOrderPersenterImpl(this, ConfirmOrderActivity.this);
        iAddressPresenterImpl = new IAddressPresenterImpl(this, ConfirmOrderActivity.this);
        iAddressPresenterImpl.queryContact(2);
        superMarketPresenterImpl = new SuperMarketPresenterImpl(this, this);
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
    public void createOrderSucced(String orderId) {//下单成功
        Intent intent = new Intent(this, CommonPayActivity.class);
        intent.putExtra(ActivityConstants.ORDER_ID, orderId);
        intent.putExtra(ActivityConstants.CHARGE, String.valueOf(ArithUtil.mul(commodityBean.getPrice(), num)));
        intent.putExtra(ActivityConstants.COMMON_PAYTYPE, ActivityConstants.STORE_ORDER);
        startActivityForResult(intent, ActivityConstants.REQUEST_CODE);
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
                Intent mIntent = new Intent(ConfirmOrderActivity.this, AddressActivity.class);
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
    public void createSucced(CreataOrderBean bean) {

        Intent intent = new Intent(this, CommonPayActivity.class);
        intent.putExtra(ActivityConstants.ORDER_ID, bean.getId());
        intent.putExtra(ActivityConstants.CHARGE, String.valueOf(ArithUtil.mul(commodityBean.getPrice(), num)));
        intent.putExtra(ActivityConstants.COMMON_PAYTYPE, ActivityConstants.SUPER_MARKET_OERDER);
        startActivityForResult(intent, ActivityConstants.REQUEST_CODE);
        ToastUtils.showShort(this, "下单成功");
    }

    @Override
    public void querySMOrderSucced(List<OrderShopBean> data) {

    }

    @Override
    public void querySMOrderError() {

    }
}
