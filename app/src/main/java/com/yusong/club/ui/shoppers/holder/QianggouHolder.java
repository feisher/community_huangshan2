package com.yusong.club.ui.shoppers.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.activity.RobShopDetailsActivity;
import com.yusong.club.ui.shoppers.adapter.QianggouItemAdapter;
import com.yusong.club.ui.shoppers.bean.QiangGouBean;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         created at 2017/3/3 15:14.
 */

public class QianggouHolder extends BaseHolder<QiangGouBean> {
    public QianggouHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(final List<QiangGouBean> datas, int position) {
        shopListTitleTv.setText(datas.get(position).getCategoryName());
        GlideImgManager.loadImage(mContext,datas.get(position).getIconPath(),titleImage);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        QianggouItemAdapter itemAdapter = new QianggouItemAdapter(datas.get(position).getCategoryList(), mContext);
        qianggouRecycerview.setAdapter(itemAdapter);
        qianggouRecycerview.addItemDecoration(new SpaceItemDecoration(1, 1));
        qianggouRecycerview.setLayoutManager(gridLayoutManager);
        itemAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(mContext, RobShopDetailsActivity.class);
                intent.putExtra("titleName", ((QiangGouBean.Categorys) data).getCategoryName());
                intent.putExtra("id", ((QiangGouBean.Categorys) data).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @InjectView(R.id.line_left)
    ImageView lineLeft;
    @InjectView(R.id.title_image)
    ImageView titleImage;
    @InjectView(R.id.shop_list_title_tv)
    TextView shopListTitleTv;
    @InjectView(R.id.line_right)
    ImageView lineRight;
    @InjectView(R.id.qianggou_recycerview)
    RecyclerView qianggouRecycerview;
}
