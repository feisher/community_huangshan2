package com.yusong.community.ui.charge.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.TextView;

import com.yusong.community.R;

/**
 * Created by Mr_Peng on 2017/1/9.
 */

public class SuccedDialog {
    Context context;
    android.app.AlertDialog dialog;
    TextView titleView;
    private CloseDialogDo mCloseDialogDo;

    public SuccedDialog(Context context, String dialogText) {
        this.context = context;
        dialog = new android.app.AlertDialog.Builder(context, R.style.BaseDialog).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_base_succed);
        titleView = (TextView) window.findViewById(R.id.succed_tv);
        titleView.setText(dialogText);
        thread.start();
    }

    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                handler.sendEmptyMessage(0x11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mCloseDialogDo != null) {
                mCloseDialogDo.doThings();
            }
            dismiss();
        }
    };

    public void dismiss() {
        dialog.dismiss();
    }

    public void setCloseDialogDoListner(CloseDialogDo mCloseDialogDo) {
        this.mCloseDialogDo = mCloseDialogDo;
    }

    public interface CloseDialogDo {
        void doThings();
    }

}
