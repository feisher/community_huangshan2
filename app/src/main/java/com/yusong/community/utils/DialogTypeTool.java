package com.yusong.community.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.yusong.community.R;
import com.yusong.community.ui.school.mvp.entity.Role;
import com.yusong.community.ui.school.teacher.adapter.ChoseTypeAdp;

import java.util.List;

import butterknife.InjectView;


/**
 * Created by dingsheng on
 */
public class DialogTypeTool implements View.OnClickListener {
    private static DialogTypeTool mDialogTool;
    @InjectView(R.id.tv_sure)
    TextView tvSure;
    private PopupWindow mPopupWindow;
    private Context mcontext;
    private int pos = -1;
    private String token = "";
    private String order_id = "";
    private String status = "";
    protected ProgressDialog mDialog;
    private OnChooseTypeLinster mOnChooseTypeLinster;

    public void setmOnChooseTypeLinster(OnChooseTypeLinster mOnChooseTypeLinster) {
        this.mOnChooseTypeLinster = mOnChooseTypeLinster;
    }

    public PopupWindow getmPopupWindow() {
        return mPopupWindow;
    }

    List<Role.RoleListBean.TeacherListBean> mTypeBeans;
    private ListView listView;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public static DialogTypeTool getInstance() {
        if (null == mDialogTool) {
            mDialogTool = new DialogTypeTool();
        }
        return mDialogTool;
    }

    public void initDialog(Context mcontext, View views, List<Role.RoleListBean.TeacherListBean> mTypeBeans) {
        this.mTypeBeans = mTypeBeans;
        this.mcontext = mcontext;
        View popupWindowView = LayoutInflater.from(mcontext).inflate(R.layout.dialog_choose_type, null);
        mPopupWindow = new PopupWindow(popupWindowView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        //菜单背景色
        ColorDrawable dw = new ColorDrawable(0);
        mPopupWindow.setBackgroundDrawable(dw);
        //显示位置
        mPopupWindow.showAtLocation(views, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //设置背景半透明
//        backgroundAlpha(0.5f);
        popupWindowView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                    mPopupWindow = null;
                }
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });
        //关闭事件
        mPopupWindow.setOnDismissListener(new popupDismissListener());
        tvSure = (TextView) popupWindowView.findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(this);
        listView = (ListView) popupWindowView.findViewById(R.id.lv_choose_type);
        ChoseTypeAdp mChoseTypeAdp = new ChoseTypeAdp(mcontext, mTypeBeans);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(mChoseTypeAdp);
    }

    public interface OnChooseTypeLinster {
        public void getChooseType(int pos);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sure:
                pos = listView.getCheckedItemPosition();
                if (pos != -1) {
                    if (mPopupWindow != null && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                        mPopupWindow = null;
                    }
                    if (mOnChooseTypeLinster != null) {
                        mOnChooseTypeLinster.getChooseType(pos);
                    }
                } else {
                    Toast.makeText(mcontext, "必需选择一个班级。。。", Toast.LENGTH_SHORT).show();
                }
                pos = -1;
                break;
        }
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     */
    class popupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mcontext).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((Activity) mcontext).getWindow().setAttributes(lp);
    }

}
