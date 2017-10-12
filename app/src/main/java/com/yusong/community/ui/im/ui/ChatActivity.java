package com.yusong.community.ui.im.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyphenate.util.EasyUtils;
import com.yusong.community.R;
import com.yusong.community.ui.MainActivity;
import com.yusong.community.ui.im.IMBaseActivity;
import com.yusong.community.ui.im.runtimepermissions.PermissionsManager;
import com.yusong.community.utils.AndroidBug5497Workaround;

/**
 *
 */
public class ChatActivity extends IMBaseActivity {
    public static ChatActivity activityInstance;
    public String toChatUsername;
    ChatFragment chatFragment;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.em_activity_chat);
        activityInstance = this;
        AndroidBug5497Workaround.assistActivity(this);
        //get user id or group id
        toChatUsername = getIntent().getExtras().getString("userId");
        mName = getIntent().getExtras().getString("userName");
        //use EaseChatFratFragment

        chatFragment = new ChatFragment();
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, chatFragment).commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // make sure only one chat activity is opened
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else if (mName.equals(username)) {
            super.onNewIntent(intent);
        } else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
        if (EasyUtils.isSingleActivity(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public String getToChatUsername() {
        return toChatUsername;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }


}
