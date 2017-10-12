package com.yusong.community.ui.charge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.charge.bean.MoveSosBean;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr_Peng on 2017/1/3.
 */

public class SosItemAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<MoveSosBean> moveSosList = new ArrayList<MoveSosBean>();

    public SosItemAdapter(Context context, List<MoveSosBean> moveSosList) {
        this.context = context;
        this.moveSosList = moveSosList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return moveSosList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_charge_sos, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        MoveSosBean sosBean = moveSosList.get(i);
        GlideImgManager.loadImage(context, sosBean.getImagePath(), viewHolder.headPictureImage);
        viewHolder.sosUserNameTv.setText(sosBean.getRescuerName());
        viewHolder.pingfenRatingbar.setRating(sosBean.getStar());
        viewHolder.priceTv.setText("¥ " + sosBean.getPrice());
        viewHolder.shanghangAddressTv.setText(sosBean.getShopName());
        viewHolder.tellNumberTv.setText(sosBean.getTel());
        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.head_picture_image)
        ImageView headPictureImage;
        @InjectView(R.id.sos_user_name_tv)
        TextView sosUserNameTv;
        @InjectView(R.id.pingfen_ratingbar)
        RatingBar pingfenRatingbar;
        @InjectView(R.id.price_tv)
        TextView priceTv;
        @InjectView(R.id.shanghang_address_tv)
        TextView shanghangAddressTv;
        @InjectView(R.id.textView)
        TextView textView;
        @InjectView(R.id.tell_number_tv)
        TextView tellNumberTv;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
