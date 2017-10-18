package com.yusong.community.ui.home.fragment;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.im.Constant;
import com.yusong.community.ui.im.IMHelper;
import com.yusong.community.ui.im.runtimepermissions.PermissionsManager;
import com.yusong.community.ui.im.runtimepermissions.PermissionsResultAction;
import com.yusong.community.ui.im.ui.ChatActivity;
import com.yusong.community.ui.im.ui.ConversationListFragment;
import com.yusong.community.ui.im.ui.GroupsActivity;
import com.yusong.community.ui.school.teacher.fragment.GroupFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by quaner on 16/12/26.
 */
public class Msg1Fragment extends BaseFragment {
    @InjectView(R.id.tl_msg)
    TabLayout mTlMsg;
    @InjectView(R.id.vp_msg)
    ViewPager mVpMsg;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    private List msgFragments = new ArrayList<>();
    private ConversationListFragment mConversationListFragment;
    private NotificationListFragment mNotificationListFragment;

    private BroadcastReceiver broadcastReceiver;
    private LocalBroadcastManager broadcastManager = IMHelper.broadcastManager;
//    public static PluginLocalBroadcastManager broadcastManager = IMHelper.broadcastManager;

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_im_msg1, null);
    }

    @Override
    public void onResume() {
        super.onResume();

        // unregister this event listener when this activity enters the
        // background
        IMHelper sdkHelper = IMHelper.getInstance();
        sdkHelper.pushActivity(getActivity());

        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    @Override
    public void onStop() {
        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
        IMHelper sdkHelper = IMHelper.getInstance();
        sdkHelper.popActivity(getActivity());

        super.onStop();
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


    private void refreshUIWithMessage() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (mConversationListFragment != null) {
                    mConversationListFragment.refresh();
                }
            }
        });
    }

    @Override
    public void initData() {
        mTvTitle.setText("消息");
        initFragment();
        initViewPager();
        //register broadcast receiver to receive the change of group from IMHelper
        registerBroadcastReceiver();
        EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
    }


    private void initFragment() {
        mConversationListFragment = new ConversationListFragment();
        mNotificationListFragment = new NotificationListFragment();
        msgFragments.add(mConversationListFragment);
        msgFragments.add(mNotificationListFragment);
    }

    private void initViewPager() {

        //设置adapter
        mVpMsg.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return (Fragment) msgFragments.get(position);
            }

            @Override
            public int getCount() {
                return msgFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                String[] title = null;
                title = new String[]{"消息", "通知"};
                return title[position];
            }
        });
        //关联页签与viewpager
        mTlMsg.setupWithViewPager(mVpMsg);

    }


    @Override
    public void initListener() {

    }


    private void registerBroadcastReceiver() {
//        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_CONTACT_CHANAGED);
        intentFilter.addAction(Constant.ACTION_GROUP_CHANAGED);
//        intentFilter.addAction(RPConstant.REFRESH_GROUP_RED_PACKET_ACTION);
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
//                updateUnreadLabel();
//                updateUnreadAddressLable();
                // refresh conversation list
                if (mConversationListFragment != null) {
                    mConversationListFragment.refresh();
                }

                String action = intent.getAction();
                if (action.equals(Constant.ACTION_GROUP_CHANAGED)) {
                    if (EaseCommonUtils.getTopActivity(getActivity()).equals(GroupsActivity.class.getName())) {
                        GroupFragment.instance.onResume();
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
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (ChatActivity.activityInstance != null && ChatActivity.activityInstance.toChatUsername != null &&
                            username.equals(ChatActivity.activityInstance.toChatUsername)) {
                        String st10 = getResources().getString(R.string.have_you_removed);
                        Toast.makeText(getActivity().getApplicationContext(), ChatActivity.activityInstance.getToChatUsername() + st10, Toast.LENGTH_LONG)
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

    private void unregisterBroadcastReceiver() {
        broadcastManager.unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcastReceiver();
    }


    //6.0权限
    @TargetApi(23)
    private void requestPermissions() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(getActivity(), new PermissionsResultAction() {
            @Override
            public void onGranted() {
            }

            @Override
            public void onDenied(String permission) {
            }
        });
    }

    @OnClick(R.id.ll_back)
    public void onClick() {
        getActivity().finish();
    }
}
