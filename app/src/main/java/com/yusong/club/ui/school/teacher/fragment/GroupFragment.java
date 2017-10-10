package com.yusong.club.ui.school.teacher.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.home.mvp.cache.TokenInfo;
import com.yusong.club.ui.im.Constant;
import com.yusong.club.ui.im.adapter.GroupAdapter;
import com.yusong.club.ui.im.ui.ChatActivity;
import com.yusong.club.ui.im.ui.NewGroupActivity;
import com.yusong.club.ui.im.ui.PublicGroupsActivity;
import com.yusong.club.utils.AppUtils;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SPUtils;

import java.util.List;

import butterknife.InjectView;

/**
 * 群组
 */
public class GroupFragment extends BaseFragment {


    @InjectView(R.id.list)
    ListView groupListView;
    @InjectView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    protected List<EMGroup> grouplist;
    private GroupAdapter groupAdapter;
    private InputMethodManager inputMethodManager;
    public static GroupFragment instance;

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.em_fragment_groups, null);
    }

    @Override
    public void initData() {

        instance = this;
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        grouplist = EMClient.getInstance().groupManager().getAllGroups();
        //show group list
        groupAdapter = new GroupAdapter(getActivity(), 1, grouplist);
        groupListView.setAdapter(groupAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.holo_blue_bright, R.color.holo_green_light,
                R.color.holo_orange_light, R.color.holo_red_light);
        //pull down to refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().groupManager().getJoinedGroupsFromServer();
                            handler.sendEmptyMessage(0);
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            handler.sendEmptyMessage(1);
                        }
                    }
                }.start();
            }
        });

        groupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    int groups = CacheUtils.getCreateGroups(getActivity());
                    if (groups < 5) {
                        // create a new group
                        startActivityForResult(new Intent(getActivity(), NewGroupActivity.class), 0);
                    } else {
                        MyApplication.showMessage("创建群组已达到上限");
                    }
                } else if (position == 1) {
                    // join a public group
                    startActivityForResult(new Intent(getActivity(), PublicGroupsActivity.class), 0);
                } else {
                    // enter group chat
                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                    // it is group chat
                    intent.putExtra("chatType", Constant.CHATTYPE_GROUP);
                    intent.putExtra("userId", groupAdapter.getItem(position - 2).getGroupId());
                    startActivityForResult(intent, 0);
                }
            }

        });
        groupListView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
                    if (getActivity().getCurrentFocus() != null)
                        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });
    }


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            swipeRefreshLayout.setRefreshing(false);
            switch (msg.what) {
                case 0:
                    refresh();
                    break;
                case 1:
                    Toast.makeText(getActivity(), R.string.Failed_to_get_group_chat_information, Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        refresh();
        super.onResume();
    }

    int groups = 0;

    private void refresh() {
        groups = 0;
        SPUtils.remove(getActivity(), "groups");
        grouplist = EMClient.getInstance().groupManager().getAllGroups();
        if (!AppUtils.listIsEmpty(grouplist)) {
            for (int i = 0; i < grouplist.size(); i++) {
                EMGroup group = grouplist.get(i);
                EMGroup server = EMClient.getInstance().groupManager().getGroup(group.getGroupId());
                String owner = server.getOwner();
                TokenInfo info = CacheUtils.getTokenInfo(getActivity());
                if (info != null && info.getImAccount() != null && owner != null)
                    if (info.getImAccount().equals(owner)) {
                        groups++;
                        CacheUtils.savaCreateGroups(getActivity(), groups);
                    }
            }
        }
        groupAdapter = new GroupAdapter(getActivity(), 1, grouplist);
        groupListView.setAdapter(groupAdapter);
        groupAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }

}
