package com.hyphenate.easeui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Scroller;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.adapter.EaseConversationAdapter;

import java.util.ArrayList;
import java.util.List;

public class EaseConversationList extends ListView {


    // -------------------------------------------添加侧滑删除start-----------------------------------------------
    /** 禁止侧滑模式 */
    public static int MODE_FORBID = 0;
    /** 从右向左滑出菜单模式 */
    public static int MODE_RIGHT = 1;
    /** 当前的模式 */
    private int mode = MODE_FORBID;
    /** 右侧菜单的长度 */
    private int rightLength = 0;

    /**
     * 当前滑动的ListView　position
     */
    private int slidePosition;
    /**
     * 手指按下X的坐标
     */
    private int downY;
    /**
     * 手指按下Y的坐标
     */
    private int downX;
    /**
     * ListView的item
     */
    private View itemView;
    /**
     * 滑动类
     */
    private Scroller scroller;
    /**
     * 认为是用户滑动的最小距离
     */
    private int mTouchSlop;

    /**
     * 判断是否可以侧向滑动
     */
    private boolean canMove = false;
    /**
     * 标示是否完成侧滑
     */
    private boolean isSlided = false;


    protected int primaryColor;
    protected int secondaryColor;
    protected int timeColor;
    protected int primarySize;
    protected int secondarySize;
    protected float timeSize;
    

    protected final int MSG_REFRESH_ADAPTER_DATA = 0;
    
    protected Context context;
    protected EaseConversationAdapter adapter;
    protected List<EMConversation> conversations = new ArrayList<EMConversation>();
    protected List<EMConversation> passedListRef = null;
    
    
    public EaseConversationList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }
    
    public EaseConversationList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    
    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseConversationList);
        primaryColor = ta.getColor(R.styleable.EaseConversationList_cvsListPrimaryTextColor, R.color.list_itease_primary_color);
        secondaryColor = ta.getColor(R.styleable.EaseConversationList_cvsListSecondaryTextColor, R.color.list_itease_secondary_color);
        timeColor = ta.getColor(R.styleable.EaseConversationList_cvsListTimeTextColor, R.color.list_itease_secondary_color);
        primarySize = ta.getDimensionPixelSize(R.styleable.EaseConversationList_cvsListPrimaryTextSize, 0);
        secondarySize = ta.getDimensionPixelSize(R.styleable.EaseConversationList_cvsListSecondaryTextSize, 0);
        timeSize = ta.getDimension(R.styleable.EaseConversationList_cvsListTimeTextSize, 0);
        mode = ta.getInt(R.styleable.EaseConversationList_mode, 0);
        scroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        ta.recycle();
        
    }


    /**
     * 处理我们拖动ListView item的逻辑
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        final int action = ev.getAction();
        int lastX = (int) ev.getX();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("touch-->" + "down");

			/* 默认不处理当前View的事件，即没有侧滑菜单 */
                if (this.mode == MODE_FORBID) {
                    return super.onTouchEvent(ev);
                }

                // 侧滑状态判断
                if (isSlided) {
                    scrollBack();
                    return false;
                }
                // 滚动是否结束
                if (!scroller.isFinished()) {
                    return false;
                }
                downX = (int) ev.getX();
                downY = (int) ev.getY();

                slidePosition = pointToPosition(downX, downY);

                // 无效的position
                if (slidePosition == AdapterView.INVALID_POSITION) {
                    return super.onTouchEvent(ev);
                }

                itemView = getChildAt(slidePosition - getFirstVisiblePosition());

			/* 右侧菜单的长度 */
                if (this.mode == MODE_RIGHT) {
                    this.rightLength = -itemView.getPaddingRight();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("touch-->" + "move");

                if (!canMove
                        && slidePosition != AdapterView.INVALID_POSITION
                        && (Math.abs(ev.getX() - downX) > mTouchSlop && Math.abs(ev
                        .getY() - downY) < mTouchSlop)) {
                    int offsetX = downX - lastX;
                    if (offsetX > 0 && this.mode == MODE_RIGHT) {
					/* 从右向左滑 */
                        canMove = true;
                    } else {
                        canMove = false;
                    }
				/* 侧滑时ListView的OnItemClickListener事件的屏蔽 */
                    MotionEvent cancelEvent = MotionEvent.obtain(ev);
                    cancelEvent
                            .setAction(MotionEvent.ACTION_CANCEL
                                    | (ev.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                    onTouchEvent(cancelEvent);
                }
                if (canMove) {
				/* 侧滑动时，ListView不上下滚动 */
                    requestDisallowInterceptTouchEvent(true);

                    // 根据X坐标的差可以得到手指滑动方向，本例子可以根据自己的需要去灵活修改（左边划出菜单，右边划出菜单，或者左右均可）
                    int deltaX = downX - lastX;
                    if (deltaX > 0 && this.mode == MODE_RIGHT) {
					/* X坐标差大于0手指向右滑动 */
                        itemView.scrollTo(deltaX, 0);
                    } else {
                        itemView.scrollTo(0, 0);
                    }
                    return true;
                }
            case MotionEvent.ACTION_UP:
                System.out.println("touch-->" + "up");
                if (canMove) {
                    canMove = false;
                    scrollByDistanceX();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 根据手指滚动itemView的距离来判断是滚动到开始位置还是向左或者向右滚动
     */
    private void scrollByDistanceX() {
		/* 当前模式不允许滑动，则直接返回 */
        if (this.mode == MODE_FORBID) {
            return;
        }
        if (itemView.getScrollX() > 0 && this.mode == MODE_RIGHT) {
			/* 从右向左滑 */
            if (itemView.getScrollX() >= rightLength / 2) {
                scrollLeft();
            } else {
                // 滚回原始位置
                scrollBack();
            }
        } else {
            // 滚回原始位置
            scrollBack();
        }

    }

    /**
     * 向左滑动
     */
    private void scrollLeft() {
        isSlided = true;
        final int delta = (rightLength - itemView.getScrollX());
        // 调用startScroll方法来设置一些滚动的参数，我们在computeScroll()方法中调用scrollTo来滚动item
        scroller.startScroll(itemView.getScrollX(), 0, delta, 0,
                Math.abs(delta));
        postInvalidate(); // 刷新itemView
    }

    /**
     * 侧滑菜单复原
     */
    private void scrollBack() {
        isSlided = false;
        scroller.startScroll(itemView.getScrollX(), 0, -itemView.getScrollX(),
                0, Math.abs(itemView.getScrollX()));
        postInvalidate(); // 刷新itemView
    }

    @Override
    public void computeScroll() {
        // 调用startScroll的时候scroller.computeScrollOffset()返回true，
        if (scroller.computeScrollOffset()) {
            // 让ListView item根据当前的滚动偏移量进行滚动
            itemView.scrollTo(scroller.getCurrX(), scroller.getCurrY());

            postInvalidate();
        }
    }

    /**
     * 复原
     */
    public void slideBack() {
        this.scrollBack();
    }


    // -------------------------------------------添加侧滑删除end-----------------------------------------------

    public void init(List<EMConversation> conversationList){
        this.init(conversationList, null);
    }

    public void init(List<EMConversation> conversationList, EaseConversationListHelper helper){
        conversations = conversationList;
        if(helper != null){
            this.conversationListHelper = helper;
        }
        adapter = new EaseConversationAdapter(context, 0, conversationList);
        adapter.setCvsListHelper(conversationListHelper);
        adapter.setPrimaryColor(primaryColor);
        adapter.setPrimarySize(primarySize);
        adapter.setSecondaryColor(secondaryColor);
        adapter.setSecondarySize(secondarySize);
        adapter.setTimeColor(timeColor);
        adapter.setTimeSize(timeSize);
        setAdapter(adapter);
    }
    
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
            case MSG_REFRESH_ADAPTER_DATA:
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                    setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
            }
        }
    };

    public EMConversation getItem(int position) {
        return (EMConversation)adapter.getItem(position);
    }
    
    public void refresh() {
    	if(!handler.hasMessages(MSG_REFRESH_ADAPTER_DATA)){
    		handler.sendEmptyMessage(MSG_REFRESH_ADAPTER_DATA);
    	}
    }
    
    public void filter(CharSequence str) {
        adapter.getFilter().filter(str);
    }


    private EaseConversationListHelper conversationListHelper;

    public interface EaseConversationListHelper{
        /**
         * set content of second line
         * @param lastMessage
         * @return
         */
        String onSetItemSecondaryText(EMMessage lastMessage);
    }
    public void setConversationListHelper(EaseConversationListHelper helper){
        conversationListHelper = helper;
    }

}
