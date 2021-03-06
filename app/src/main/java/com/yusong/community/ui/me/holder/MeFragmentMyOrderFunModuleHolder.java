package com.yusong.community.ui.me.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.me.mvp.entity.MyOrderFunModuleDatas;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/1/17.
 */
public class MeFragmentMyOrderFunModuleHolder extends BaseHolder<MyOrderFunModuleDatas> {
    @InjectView(R.id.iv_itemImg)
    ImageView ivItemImg;
    @InjectView(R.id.tv_itemName)
    TextView tvItemName;
    @InjectView(R.id.ll_items)
    LinearLayout llItems;
    public MeFragmentMyOrderFunModuleHolder(View v,Context mContext) {
        super(v, mContext);
    }

    /**
     * 设置数据
     * 刷新界面
     *
     * @param datas
     * @param position
     */
    @Override
    public void setData(List<MyOrderFunModuleDatas> datas, int position) {
       ivItemImg.setBackgroundResource(datas.get(position).getImageId());
        tvItemName.setText(datas.get(position).getName());
    }
}
