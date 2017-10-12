package com.yusong.community.ui.im.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.yusong.community.R;
import com.hyphenate.easeui.widget.EaseTitleBar;
import com.yusong.community.ui.im.IMBaseActivity;
import com.yusong.community.ui.im.IMModel;

public class SetServersActivity extends IMBaseActivity {

    EditText restEdit;
    EditText imEdit;
    EaseTitleBar titleBar;

    IMModel IMModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_servers);

        restEdit = (EditText) findViewById(R.id.et_rest);
        imEdit = (EditText) findViewById(R.id.et_im);
        titleBar = (EaseTitleBar) findViewById(R.id.title_bar);

        IMModel = new IMModel(this);
        if(IMModel.getRestServer() != null)
            restEdit.setText(IMModel.getRestServer());
        if(IMModel.getIMServer() != null)
            imEdit.setText(IMModel.getIMServer());
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(!TextUtils.isEmpty(restEdit.getText()))
            IMModel.setRestServer(restEdit.getText().toString());
        if(!TextUtils.isEmpty(imEdit.getText()))
            IMModel.setIMServer(imEdit.getText().toString());
        super.onBackPressed();
    }
}
