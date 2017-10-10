package com.yusong.club.ui.community.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.community.hoder.CreatePostImagesHodler;

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
