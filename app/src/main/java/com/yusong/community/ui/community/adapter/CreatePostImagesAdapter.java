package com.yusong.community.ui.community.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.community.hoder.CreatePostImagesHodler;

import java.util.List;

/**
 * Created by feisher on 2017/1/19.
 */
public class CreatePostImagesAdapter extends DefaultAdapter<String> {


    public CreatePostImagesAdapter(List<String> mImagesDatasList, Context mContext) {
        super(mImagesDatasList,  mContext);
    }

    @Override
    public BaseHolder<String> getHolder(View v) {
        return new CreatePostImagesHodler(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_createpost_img;
    }


}
