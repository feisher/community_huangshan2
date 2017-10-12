package com.yusong.community.ui.community.hoder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.community.mvp.entity.PostsCatogrey;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/1/19.
 */
public class CreatePostCategorysHodler extends BaseHolder<PostsCatogrey> {
    @InjectView(R.id.community_classify_name_tv)
    TextView communityClassifyNameTv;
    @InjectView(R.id.select_image)
    ImageView selectImage;
    public CreatePostCategorysHodler(View v, Context mContext) {
        super(v, mContext);
    }

    @Override
    public void setData(List<PostsCatogrey> datas, int position) {
        if(DefaultAdapter.mPosition == position){
            selectImage.setImageResource(R.mipmap.selected);
        }else{
            selectImage.setImageResource(R.mipmap.not_select);
        }
        communityClassifyNameTv.setText(datas.get(position).getCategoryName());
    }
}
