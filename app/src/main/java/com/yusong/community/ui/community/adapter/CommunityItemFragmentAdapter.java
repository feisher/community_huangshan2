package com.yusong.community.ui.community.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.community.hoder.CommunityItemFragmentHodler;
import com.yusong.community.ui.community.mvp.entity.Posts;

import java.util.List;

/**
 * create by feisher on 2017/1/11 13:17
 * Email：458079442@qq.com
 */
public class CommunityItemFragmentAdapter extends DefaultAdapter<Posts> {
    private CommunityItemFragmentHodler.ShowInputView showInputView;

    public CommunityItemFragmentAdapter(List<Posts> mDatas, Context mContext, CommunityItemFragmentHodler.ShowInputView showInputView) {
        super(mDatas, mContext);
        this.showInputView=showInputView;
    }

    @Override
    public BaseHolder getHolder(View v) {
        return new CommunityItemFragmentHodler(v, mContext,showInputView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_community_item_fragment;
    }


}
