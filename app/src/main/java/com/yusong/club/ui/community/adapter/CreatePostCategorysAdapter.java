package com.yusong.club.ui.community.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.community.hoder.CreatePostCategorysHodler;
import com.yusong.club.ui.community.mvp.entity.PostsCatogrey;

import java.util.List;

/**
 * Created by feisher on 2017/1/19.
 */
public class CreatePostCategorysAdapter extends DefaultAdapter<PostsCatogrey> {



    public CreatePostCategorysAdapter(List<PostsCatogrey> mImagesDatasList, Context mContext) {
        super(mImagesDatasList, mContext);

    }

    @Override
    public BaseHolder<PostsCatogrey> getHolder(View v) {
        return new CreatePostCategorysHodler(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_community_classify;
    }
}
