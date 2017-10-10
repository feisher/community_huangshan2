package com.yusong.club.ui.school.school.fragment;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.school.school.adapter.MessageAdapter;
import com.yusong.club.ui.school.school.bean.Messages;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.bga.BGARefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * 消息页面
 */
public class MessageFragment extends BaseFragment {
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.et_search)
    EditText etSearch;
    @InjectView(R.id.swipe_recycler)
    SwipeMenuRecyclerView swipeRecycler;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.refresh_layout)
    BGARefreshLayout refreshLayout;
    private MessageAdapter adapter;
    private List<Messages> data;

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_school_msg, null);
    }

    @Override
    public void initData() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }

        tvBack.setVisibility(View.GONE);
        imMsg.setVisibility(View.GONE);
        tvTitle.setText("消息");

        initRecycler();
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        // 设置菜单创建器。
        swipeRecycler.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        swipeRecycler.setSwipeMenuItemClickListener(menuItemClickListener);
        adapter = new MessageAdapter(getData(),mContext);
        swipeRecycler.setAdapter(adapter);
        swipeRecycler.setLayoutManager(layoutManager);
    }


    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                    .setBackgroundDrawable(R.drawable.selector_red)
                    .setText("删除") // 文字。
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(16) // 文字大小。
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。.

            // 上面的菜单哪边不要菜单就不要添加。
        }
    };


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
//                mPresenter.deleteContact(adapterPosition);
                ToastUtils.showShort(getActivity(), "删除按钮被点击");
            }
        }
    };

    @Override
    public void initListener() {
        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }


    public List<Messages> getData() {
        data = new ArrayList<>();
        data.add(new Messages("张老师", "11:10", "家里就能吃饭", "3"));
        data.add(new Messages("张老师", "11:10", "家里就能吃饭", "3"));
        data.add(new Messages("张老师", "11:10", "家里就能吃饭", "3"));
        data.add(new Messages("张老师", "11:10", "家里就能吃饭", "3"));
        data.add(new Messages("张老师", "11:10", "家里就能吃饭", "3"));
        return data;
    }

}
