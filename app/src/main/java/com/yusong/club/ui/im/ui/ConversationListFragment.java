package com.yusong.club.ui.im.ui;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMConversation.EMConversationType;
import com.hyphenate.easeui.RemoveEvent;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.util.NetUtils;
import com.yusong.club.R;
import com.yusong.club.ui.im.Constant;
import com.yusong.club.ui.im.db.InviteMessgeDao;
import com.yusong.club.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ConversationListFragment extends EaseConversationListFragment {

    private TextView errorText;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void removeEvent(RemoveEvent removeEvent){
        LogUtils.e("删除成功  Event");
        EMConversation conversation = conversationListView.getItem(removeEvent.getPosition());
        // delete conversation
        EMClient.getInstance().chatManager().deleteConversation(conversation.conversationId(), true);
        InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
        inviteMessgeDao.deleteMessage(conversation.conversationId());
        refresh();

    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        super.initView();
        View errorView = (LinearLayout) View.inflate(getActivity(),R.layout.em_chat_neterror_item, null);
        errorItemContainer.addView(errorView);
        errorText = (TextView) errorView.findViewById(R.id.tv_connect_errormsg);
//        titleBar.setTitle("消息");
        //TODO 修改环信
        setConversationListItemClickListener(new EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                String username = conversation.conversationId();
                if (username.equals(EMClient.getInstance().getCurrentUser()))
                    Toast.makeText(getActivity(), R.string.Cant_chat_with_yourself, Toast.LENGTH_SHORT).show();
                else {
                    // start chat acitivity
                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                    if(conversation.isGroup()){
                        if(conversation.getType() == EMConversationType.ChatRoom){
                            // it's group chat
                            intent.putExtra(Constant.EXTRA_CHAT_TYPE, Constant.CHATTYPE_CHATROOM);
                        }else{
                            intent.putExtra(Constant.EXTRA_CHAT_TYPE, Constant.CHATTYPE_GROUP);
                        }

                    }
                    // it's single chat
                    intent.putExtra(Constant.EXTRA_USER_ID, username);
                    startActivity(intent);
                }

            }

        });
    }
    
    @Override
    protected void setUpView() {
        super.setUpView();
        // register context menu
        registerForContextMenu(conversationListView);

//        //red packet code : 红包回执消息在会话列表最后一条消息的展示
//        conversationListView.setConversationListHelper(new EaseConversationListHelper() {
//            @Override
//            public String onSetItemSecondaryText(EMMessage lastMessage) {
//                if (lastMessage.getBooleanAttribute(RPConstant.MESSAGE_ATTR_IS_RED_PACKET_ACK_MESSAGE, false)) {
//                    String sendNick = lastMessage.getStringAttribute(RPConstant.EXTRA_RED_PACKET_SENDER_NAME, "");
//                    String receiveNick = lastMessage.getStringAttribute(RPConstant.EXTRA_RED_PACKET_RECEIVER_NAME, "");
//                    String msg;
//                    if (lastMessage.direct() == EMMessage.Direct.RECEIVE) {
//                        msg = String.format(getResources().getString(R.string.msg_someone_take_red_packet), receiveNick);
//                    } else {
//                        if (sendNick.equals(receiveNick)) {
//                            msg = getResources().getString(R.string.msg_take_red_packet);
//                        } else {
//                            msg = String.format(getResources().getString(R.string.msg_take_someone_red_packet), sendNick);
//                        }
//                    }
//                    return msg;
//                } else if (lastMessage.getBooleanAttribute(RPConstant.MESSAGE_ATTR_IS_TRANSFER_PACKET_MESSAGE, false)) {
//                    String transferAmount = lastMessage.getStringAttribute(RPConstant.EXTRA_TRANSFER_AMOUNT, "");
//                    String msg;
//                    if (lastMessage.direct() == EMMessage.Direct.RECEIVE) {
//                        msg =  String.format(getResources().getString(R.string.msg_transfer_to_you), transferAmount);
//                    } else {
//                        msg =  String.format(getResources().getString(R.string.msg_transfer_from_you),transferAmount);
//                    }
//                    return msg;
//                }
//                return null;
//            }
//        });
        super.setUpView();
        //end of red packet code
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onConnectionDisconnected() {
        super.onConnectionDisconnected();
        if (NetUtils.hasNetwork(getActivity())){
         errorText.setText(R.string.can_not_connect_chat_server_connection);
        } else {
          errorText.setText(R.string.the_current_network);
        }
    }

}