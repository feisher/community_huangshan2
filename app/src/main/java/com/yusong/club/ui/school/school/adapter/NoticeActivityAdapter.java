package com.yusong.club.ui.school.school.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.mvp.entity.Notice;
import com.yusong.club.ui.school.school.holder.NoticeHolder;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/3/7.
 */
public class NoticeActivityAdapter extends DefaultAdapter<Notice> {
    @InjectView(R.id.item_delete)
    LinearLayout itemDelete;
    public NoticeHolder mHolder;
    private int isDelete;

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public NoticeActivityAdapter(List<Notice> noticeDatas, Context mContext) {
        super(noticeDatas, mContext);
    }

    @Override
    public BaseHolder<Notice> getHolder(View v) {
        mHolder = new NoticeHolder(v, mContext,isDelete);
        return mHolder;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_notice;
    }

}
