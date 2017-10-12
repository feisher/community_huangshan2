package com.yusong.community.ui.shoppers.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.activity.CommodityDetailsActivity;
import com.yusong.community.ui.shoppers.adapter.TehuiItemAdapter;
import com.yusong.community.ui.shoppers.bean.TuiJianBean;
import com.yusong.community.utils.SpaceItemDecoration;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         created at 2017/3/6 8:38.
 */

public class TehuiHolder extends BaseHolder<TuiJianBean> {

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

    public TehuiHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<TuiJianBean> datas, int position) {
        TuiJianBean tuiJianBean = datas.get(position);
        shopListTitleTv.setText(tuiJianBean.getCategoryName());
        GlideImgManager.loadImage(mContext,tuiJianBean.getIconPath(),titleImage);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        TehuiItemAdapter itemAdapter = new TehuiItemAdapter(datas.get(position).getItemList(), mContext);
        qianggouRecycerview.setAdapter(itemAdapter);
        qianggouRecycerview.addItemDecoration(new SpaceItemDecoration(2, 2));
        qianggouRecycerview.setLayoutManager(gridLayoutManager);
        itemAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(mContext, CommodityDetailsActivity.class);
                intent.putExtra("itemId", ((TuiJianBean.Commodity) data).getId());
                intent.putExtra("isQG", 1);
                mContext.startActivity(intent);
            }
        });
    }
}
