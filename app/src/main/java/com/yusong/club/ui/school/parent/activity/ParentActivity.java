package com.yusong.club.ui.school.parent.activity;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.yusong.club.R;
import com.yusong.club.ui.home.fragment.Msg1Fragment;
import com.yusong.club.ui.im.Constant;
import com.yusong.club.ui.im.IMBaseActivity;
import com.yusong.club.ui.im.IMHelpers;
import com.yusong.club.ui.im.db.InviteMessgeDao;
import com.yusong.club.ui.im.runtimepermissions.PermissionsManager;
import com.yusong.club.ui.im.runtimepermissions.PermissionsResultAction;
import com.yusong.club.ui.im.ui.ChatActivity;
import com.yusong.club.ui.im.ui.ContactListFragment;
import com.yusong.club.ui.im.ui.ConversationListFragment;
import com.yusong.club.ui.im.ui.GroupsActivity;
import com.yusong.club.ui.school.parent.fragment.ParentHomeFragment;
import com.yusong.club.ui.school.teacher.fragment.AddressBookFragment;
import com.yusong.club.ui.school.teacher.fragment.ClassActionFragment;
import com.yusong.club.ui.school.teacher.fragment.ParentMineFragment;

import java.util.List;

import butterknife.InjectView;

/**
 * create by feisher on 2017/3/1 16:47
 * Email：458079442@qq.com
 */
public class ParentActivity extends IMBaseActivity implements RadioGroup.OnCheckedChangeListener {

    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.rb_home)
    RadioButton rbHome;
    @InjectView(R.id.rb_msg)
    RadioButton rbMsg;
    @InjectView(R.id.rb_action)
    RadioButton rbAction;
    @InjectView(R.id.rb_class)
    RadioButton rbClass;
    @InjectView(R.id.rb_me)
    RadioButton rbMe;
    @InjectView(R.id.rg_group)
    RadioGroup rgGroup;
    Fragment fragment = null;
    private String SchoolName;
    private String SchoolId;
    private BroadcastReceiver broadcastReceiver;
    private LocalBroadcastManager broadcastManager;
    private InviteMessgeDao inviteMessgeDao;
    private int currentTabIndex = 0;
    // user logged into another device
    public boolean isConflict = false;
    // user account was removed
    private boolean isCurrentAccountRemoved = false;
    private ConversationListFragment conversationListFragment;
    private ContactListFragment contactListFragment;

    protected void initListener() {
        rgGroup.setOnCheckedChangeListener(this);
    }

    protected int layoutId() {
        return R.layout.activity_parent_parent;
    }

    public void initView() {
        if (getIntent() != null) {
            SchoolName = getIntent().getStringExtra("SchoolName");
            SchoolId = getIntent().getStringExtra("SchoolId");
        }
        //默认第一个为选中
        rgGroup.check(R.id.rb_home);
        //默认跳入首页
        fragment = new ParentHomeFragment();
        Bundle bundle = null;
        bundle = new Bundle();
        bundle.putString("SchoolName", SchoolName);
        bundle.putString("SchoolId", SchoolId);
        fragment.setArguments(bundle);
        jumpFragment();
    }

    private void jumpFragment() {
        //使用指定的fragment切换当前页面
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }

    public void initData() {
        if (getIntent() != null) {
            SchoolName = getIntent().getStringExtra("SchoolName");
            SchoolId = getIntent().getStringExtra("SchoolId");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Bundle bundle = null;
        bundle = new Bundle();
        bundle.putString("SchoolName", SchoolName);
        bundle.putString("SchoolId", SchoolId);
        bundle.putString("role","3");
        bundle.putInt("roleTag",2);
        switch (checkedId) {
            case R.id.rb_home://主页
                fragment = new ParentHomeFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.rb_msg://消息
//                fragment = new MessageFragment();//学校端的碎片
                fragment = new Msg1Fragment();
                fragment.setArguments(bundle);
                break;
            case R.id.rb_action://活动
                fragment = new ClassActionFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.rb_class://校讯通
                fragment = new AddressBookFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.rb_me://我的
                fragment = new ParentMineFragment();//学校端的碎片
                fragment.setArguments(bundle);
                break;
        }
        //使用指定的fragment切换当前页面
        jumpFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(layoutId());
        super.onCreate(savedInstanceState);

        initView();
        initData();
        initListener();

        inviteMessgeDao = new InviteMessgeDao(this);
        //register broadcast receiver to receive the change of group from IMHelpers
        registerBroadcastReceiver();


        EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
    }

    private void refreshUIWithMessage() {
        runOnUiThread(new Runnable() {
            public void run() {
                // refresh unread count
//                updateUnreadLabel();
//                if (currentTabIndex == 0) {
                // refresh conversation list
                if (conversationListFragment != null) {
                    conversationListFragment.refresh();

                }
//                }
            }
        });
    }

    //获取最新消息 刷新界面 发送通知。
    EMMessageListener messageListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            refreshUIWithMessage();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {

        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        if (!isConflict && !isCurrentAccountRemoved) {
//            updateUnreadLabel();
//            updateUnreadAddressLable();
        }

        // unregister this event listener when this activity enters the
        // background
        IMHelpers sdkHelper = IMHelpers.getInstance();
        sdkHelper.pushActivity(this);

        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    @Override
    protected void onStop() {
        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
        IMHelpers sdkHelper = IMHelpers.getInstance();
        sdkHelper.popActivity(this);

        super.onStop();
    }

    private void registerBroadcastReceiver() {
        broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_CONTACT_CHANAGED);
        intentFilter.addAction(Constant.ACTION_GROUP_CHANAGED);
//        intentFilter.addAction(RPConstant.REFRESH_GROUP_RED_PACKET_ACTION);
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
//                updateUnreadLabel();
//                updateUnreadAddressLable();
                if (currentTabIndex == 0) {
                    // refresh conversation list
                    if (conversationListFragment != null) {
                        conversationListFragment.refresh();
                    }
                } else if (currentTabIndex == 1) {
                    if (contactListFragment != null) {
                        contactListFragment.refresh();
                    }
                }
                String action = intent.getAction();
                if (action.equals(Constant.ACTION_GROUP_CHANAGED)) {
                    if (EaseCommonUtils.getTopActivity(ParentActivity.this).equals(GroupsActivity.class.getName())) {
                        GroupsActivity.instance.onResume();
                    }
                }
            }
        };
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    public class MyContactListener implements EMContactListener {
        @Override
        public void onContactAdded(String username) {
        }

        @Override
        public void onContactDeleted(final String username) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (ChatActivity.activityInstance != null && ChatActivity.activityInstance.toChatUsername != null &&
                            username.equals(ChatActivity.activityInstance.toChatUsername)) {
                        String st10 = getResources().getString(R.string.have_you_removed);
                        Toast.makeText(ParentActivity.this, ChatActivity.activityInstance.getToChatUsername() + st10, Toast.LENGTH_LONG)
                                .show();
                        ChatActivity.activityInstance.finish();
                    }
                }
            });
        }

        @Override
        public void onContactInvited(String username, String reason) {
        }

        @Override
        public void onFriendRequestAccepted(String username) {
        }

        @Override
        public void onFriendRequestDeclined(String username) {
        }
    }


    //6.0权限
    @TargetApi(23)
    private void requestPermissions() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
            }

            @Override
            public void onDenied(String permission) {
            }
        });
    }

    private void unregisterBroadcastReceiver() {
        broadcastManager.unregisterReceiver(broadcastReceiver);
    }


    /**
     * update the total unread count
     */
    public void updateUnreadAddressLable() {
//        runOnUiThread(new Runnable() {
//            public void run() {
//                int count = getUnreadAddressCountTotal();
//                if (count > 0) {
//                    unreadAddressLable.setVisibility(View.VISIBLE);
//                } else {
//                    unreadAddressLable.setVisibility(View.INVISIBLE);
//                }
//            }
//        });

    }

    /**
     * update unread message count
     */
    public void updateUnreadLabel() {
//        int count = getUnreadMsgCountTotal();
//        if (count > 0) {
//            unreadLabel.setText(String.valueOf(count));
//            unreadLabel.setVisibility(View.VISIBLE);
//        } else {
//            unreadLabel.setVisibility(View.INVISIBLE);
//        }
    }

    /**
     * get unread event notification count, including application, accepted, etc
     *
     * @return
     */
    public int getUnreadAddressCountTotal() {
        int unreadAddressCountTotal = 0;
        unreadAddressCountTotal = inviteMessgeDao.getUnreadMessagesCount();
        return unreadAddressCountTotal;
    }


    /**
     * get unread message count
     *
     * @return
     */
    public int getUnreadMsgCountTotal() {
        int unreadMsgCountTotal = 0;
        int chatroomUnreadMsgCount = 0;
        unreadMsgCountTotal = EMClient.getInstance().chatManager().getUnreadMessageCount();
        for (EMConversation conversation : EMClient.getInstance().chatManager().getAllConversations().values()) {
            if (conversation.getType() == EMConversation.EMConversationType.ChatRoom)
                chatroomUnreadMsgCount = chatroomUnreadMsgCount + conversation.getUnreadMsgCount();
        }
        return unreadMsgCountTotal - chatroomUnreadMsgCount;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isConflict", isConflict);
        outState.putBoolean(Constant.ACCOUNT_REMOVED, isCurrentAccountRemoved);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBroadcastReceiver();
    }
}
