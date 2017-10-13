package com.yusong.community.ui.home.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.easeui.model.DataBean;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.home.adapter.NotificationAdapter;
import com.yusong.community.ui.home.mvp.cache.TokenInfo;
import com.yusong.community.ui.im.NotifyDetailsActivity;
import com.yusong.community.utils.AppUtils;
import com.yusong.community.utils.CacheUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import io.objectbox.Box;

public class NotificationListFragment extends BaseFragment {

    @InjectView(R.id.rl_notification)
    SwipeMenuRecyclerView mRlNotification;

    List<DataBean> mData = new ArrayList<>();

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            if (menuPosition == 0) {// 删除按钮被点击。
                final DataBean msgBean = mData.get(adapterPosition);
                mData.remove(msgBean);
                //todo  更换框架
//                DataBeanDao dataBeanDao = GreenDaoManager.getInstance().getSession().getDataBeanDao();
//                dataBeanDao.deleteByKey(msgBean.getId());
                Box<DataBean> dataBeanBox = MyApplication.boxStore.boxFor(DataBean.class);
                dataBeanBox.remove(msgBean.getId());
                mAdapter.notifyDataSetChanged();
            }
        }
    };

    private NotificationAdapter mAdapter;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgEvent(DataBean msgBean) {
        if (!mData.contains(msgBean)) {
            mData.add(0, msgBean);
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            } else {
                setAdapter();
            }
        }
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        //todo  更换框架
//        DataBeanDao dataBeanDao = GreenDaoManager.getInstance().getSession().getDataBeanDao();
//        List<DataBean> dataBeen = dataBeanDao.loadAll();
        Box<DataBean> dataBeanBox = MyApplication.boxStore.boxFor(DataBean.class);
        List<DataBean> dataBeen = dataBeanBox.getAll();
        TokenInfo tokenInfo = CacheUtils.getTokenInfo(getActivity());
        if (!AppUtils.listIsEmpty(dataBeen)) {
            for (int i = 0; i < dataBeen.size(); i++) {
                DataBean dataBean = dataBeen.get(i);
                if (tokenInfo.getName().equals(dataBean.getUserName())) {
                    mData.add(0, dataBean);
                }
            }
            setAdapter();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void setAdapter() {

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new NotificationAdapter(mData, getActivity());
        mRlNotification.setAdapter(mAdapter);
        mRlNotification.setLayoutManager(mLinearLayoutManager);
        mRlNotification.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                DataBean msg = (DataBean) data;
                msg.setFlag(true);
                //todo 更换框架
//                DataBeanDao dataBeanDao = GreenDaoManager.getInstance().getSession().getDataBeanDao();
//                dataBeanDao.update(msg);
                Box<DataBean> dataBeanBox = MyApplication.boxStore.boxFor(DataBean.class);
                dataBeanBox.put(msg);
                startActivity(new Intent(getActivity(), NotifyDetailsActivity.class).putExtra("msg", msg));
            }
        });
        // 设置菜单创建器。
        mRlNotification.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        mRlNotification.setSwipeMenuItemClickListener(menuItemClickListener);

    }

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity())
                    .setBackgroundDrawable(R.drawable.selector_red)
                    .setText("删除") // 文字。
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(16) // 文字大小。
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单
        }
    };


    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_home_msglist, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
