package com.yusong.community.ui.community.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.community.hoder.CreatePostCategorysHodler;
import com.yusong.community.ui.community.mvp.entity.PostsCatogrey;

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
