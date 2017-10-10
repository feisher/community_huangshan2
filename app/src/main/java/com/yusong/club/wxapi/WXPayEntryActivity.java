package com.yusong.club.wxapi;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yusong.club.MyApplication;
import com.yusong.club.pay.PayUtils;
import com.yusong.club.ui.home.activity.LoginActivity;
import com.yusong.club.ui.home.mvp.cache.TokenInfo;
import com.yusong.club.utils.AppUtils;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.Constants;
import com.yusong.club.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.yusong.club.MyApplication.showMessage;
import static com.yusong.club.utils.ActivityConstants.REQUEST_CODE_ASK_CALL_PHONE;

public class WXPayEntryActivity extends AutoLayoutActivity implements IWXAPIEventHandler {
    public IWXAPI api;

    private static OnPayResultListener mPayListener;

    public void setOnPayListener(OnPayResultListener listener) {
        this.mPayListener = listener;
    }

    public interface OnPayResultListener {
        void onPayResult(BaseResp resp, Activity activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //将当前Activity压入栈
        this.context = new WeakReference<Activity>(this);
        MyApplication.addActivity(this.context);
        initLayout();
        ButterKnife.inject(this);
        adaptiveSystemVersions();
        List<String> list = new ArrayList<>();
        list.add("agentId-" + Constants.AGENTID);
        initView();

        api = WXAPIFactory.createWXAPI(this, PayUtils.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Log.e("pcg", "0000");
    }

    @Override
    public void onResp(BaseResp resp) {
        callBack(resp, this);
    }


    private void callBack(BaseResp resp, Activity activity) {
        if (null != mPayListener) {
            if (resp.errCode == 0) {
                mPayListener.onPayResult(resp, activity);
            } else if (resp.errCode == -2) {
                ToastUtils.showShort(this, "取消支付");
            } else {
                ToastUtils.showShort(this, "支付失败");
            }
        }
        finish();
    }


    public boolean LOGIN_TIMEOUT = true;
    //当前Activity的弱引用，防止内存泄露
    private WeakReference<Activity> context = null;
    protected static CompositeSubscription mCompositeSubscription;
    public String phoneNumber;


    //用于适配titlebar
    protected void adaptiveSystemVersions() {
        ImageView iv = (ImageView) findViewById
                (getResources().getIdentifier("iv_adaptive_down_api18", "id", getPackageName()));

        if (iv == null) {
            return;
        }
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            iv.setVisibility(View.VISIBLE);
        } else {
            iv.setVisibility(View.GONE);
        }
    }

    protected void initListener() {
    }

    public void initLayout() {
    }

    public void initView() {
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (LOGIN_TIMEOUT) {
            TokenInfo info = CacheUtils.getTokenInfo(this);
            if (info == null) {
                showMessage("登陆超时，请重新登录！");
                EMClient.getInstance().logout(true);
                MyApplication.exit();
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                String account = info.getImAccount();
                if (account == null) {
                    AppUtils.loginAsyc(info.getName(), info.getPwd(), Constants.AGENTID);
                } else {
                    long saveTime = info.getSaveTime();
                    long millis = System.currentTimeMillis();
                    if ((millis - saveTime) > 1000000) {
                        AppUtils.loginAsyc(info.getName(), info.getPwd(), Constants.AGENTID);
                    }
                }
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.removeActivity(context);
    }

    protected static void addSubcribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);//将所有subscription放入,集中处理
    }

    protected static void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//保证activity结束时取消所有正在执行的订阅
        }
    }

    public Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    /**
     * 拨打电话
     *
     * @param mobile
     */
    public void callDirectly(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            showMessage("号码不能空");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + mobile));
        startActivity(intent);
    }

    /**
     * android 动态权限
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callDirectly(phoneNumber);
                } else {
                    Toast.makeText(WXPayEntryActivity.this, "CALL_PHONE Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}