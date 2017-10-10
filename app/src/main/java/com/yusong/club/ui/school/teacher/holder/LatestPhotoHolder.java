package com.yusong.club.ui.school.teacher.holder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.community.photoview.PhotoViewActivity;
import com.yusong.club.ui.school.mvp.entity.LatestPhoto;
import com.yusong.club.ui.school.teacher.adapter.LatestImageAdapter;
import com.yusong.club.ui.school.teacher.view.MyGridView;
import com.yusong.club.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/3/7.
 */
public class LatestPhotoHolder extends BaseHolder<LatestPhoto> {
    @InjectView(R.id.gv_List)
    MyGridView gvList;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    public LatestPhotoHolder(View v, Context mContext) {
        super(v, mContext);
    }

    @Override
    public void setData(final List<LatestPhoto> datas, final int position) {
        LatestPhoto latestPhoto = datas.get(position);
        tvTitle.setText(DateUtil.getHidate(latestPhoto.getCreateTime()));
        LatestImageAdapter latestImageAdapter = new LatestImageAdapter(mContext, datas.get(position).getImageList());
        gvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<String> pothos = new ArrayList<String>();
                for (LatestPhoto.ImageListBean pho : datas.get(position).getImageList()) {
                    if (pho.getImagePath() != null && !pho.getImagePath().equals("")) {
                        pothos.add(pho.getImagePath());
                    }
                }
                Intent intent = new Intent(mContext, PhotoViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(PhotoViewActivity.EXTRA_IMAGE_INDEX, i);
                bundle.putStringArrayList(PhotoViewActivity.EXTRA_IMAGE_URLS, (ArrayList<String>) pothos);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        gvList.setAdapter(latestImageAdapter);
    }

}
