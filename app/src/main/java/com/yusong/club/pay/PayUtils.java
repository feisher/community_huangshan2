package com.yusong.club.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.yusong.club.pay.zfb.PayResult;
import com.yusong.club.pay.zfb.SignUtils;
import com.yusong.club.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/3 16:04 </li>
 * </ul>
 */
public class PayUtils {

// ------------------------------------------ 支付宝 start -------------------------------------------
    /**
     * 支付宝参数
     */
    public static String PARTNER = null;// 商户PID
    public static String SELLER = null; // 商户收款账号
    public static String RSA_PRIVATE = null;// 商户私钥，pkcs8格式
    //public  String RSA_PUBLIC = null;// 支付宝公钥
    public static String ZFBOrderId = null;//唯一订单号
    public static String ZFBNotifyUrl = null; // 通知url

    /**
     * 支付宝支付
     */
    public static void zfbPay(final Activity activity, String orderInfo) {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(activity).setTitle("警告").setMessage("支付信息不完整").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface, int i) {

                }
            }).show();
            return;
        }


        // 对订单做RSA 签名
        String sign = SignUtils.sign(orderInfo, RSA_PRIVATE);
        Log.e("ohttp支付宝sign",sign);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        LogUtils.e("支付宝拼接信息 - " + payInfo);

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(activity);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo);
                //通知支付结果
                EventBus.getDefault().post(new PayResult(result));

            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

        PARTNER = null;// 商户PID
        SELLER = null; // 商户收款账号
        RSA_PRIVATE = null;// 商户私钥，pkcs8格式
        //public  String RSA_PUBLIC = null;// 支付宝公钥
        ZFBOrderId = null;//唯一订单号
        ZFBNotifyUrl = null; // 通知url
    }


    /**
     * get the sign type we use. 获取签名方式
     */
    public static String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * create the order info. 创建订单信息
     */
    public static String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + ZFBOrderId + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + ZFBNotifyUrl + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }
// ------------------------------------------ 支付宝 end -------------------------------------------


// ------------------------------------------ 微信 start -------------------------------------------
    /**
     * 微信参数
     */
    public static String APP_ID = "wxd53fcae293b59597";  //appid 微信分配的公众账号ID
}
