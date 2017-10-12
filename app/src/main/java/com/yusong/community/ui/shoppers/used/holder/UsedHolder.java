package com.yusong.community.ui.shoppers.used.holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.community.photoview.PhotoViewActivity;
import com.yusong.community.ui.shoppers.used.bean.UsedBean;
import com.yusong.community.utils.glide.GlideImgManager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/20.
 *         描述: null
 */

public class UsedHolder extends BaseHolder<UsedBean> {
    public UsedHolder(View itemView, Context context) {
        super(itemView, context);
    }

    private List<String> imageList;

    @Override
    public void setData(List<UsedBean> datas, int position) {
        UsedBean bean = datas.get(position);
        this.imageList = bean.getImageList();
        if (!TextUtils.isEmpty(bean.getPassport())) {
            GlideImgManager.loadCircleImage(mContext, bean.getPassport(), headImage);
        }
        userNameTv.setText(bean.getNickName());
        long currentTimeMillis = System.currentTimeMillis();//获取当前时间毫秒值
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date d = dateFormat.parse(bean.getAuditTime());
            long time = d.getTime();
            long time_D_Value = currentTimeMillis - time;//时间差
            if (time_D_Value < 60 * 1000) {//一分钟内
                usedTime.setText("刚刚");
            } else if (time_D_Value < 60 * 60 * 1000) {//一小时内
                usedTime.setText((int) (time_D_Value / (1000 * 60)) + "分钟前");
            } else if (time_D_Value < 24 * 60 * 60 * 1000) {//一天内
                usedTime.setText((int) (time_D_Value / (1000 * 60 * 60)) + "小时前");
            } else if (time_D_Value < 7 * 24 * 60 * 60 * 1000) {//一周内
                usedTime.setText((int) (time_D_Value / (1000 * 60 * 60 * 24)) + "天前");
            } else {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM月dd日");
                String releaseDate = dateFormat2.format(d);
                usedTime.setText(releaseDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        nowPriceTv.setText("￥" + String.valueOf(bean.getPrice()));
        oldPriceTv.setText("原价" + String.valueOf(bean.getShowPrice()));
        oldPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        usedContentTv.setText(bean.getIntroduction());
        usedAddressTv.setText(bean.getAddress() + bean.getDistance() + "km");
        List<String> list = bean.getImageList();
        switch (list.size()) {
            case 1:
                itemTwo.setVisibility(View.GONE);
                itemThree.setVisibility(View.GONE);
                itemFour.setVisibility(View.GONE);
                image11.setVisibility(View.VISIBLE);
                GlideImgManager.loadImage(mContext, list.get(0), image11);
                break;
            case 2:
                itemTwo.setVisibility(View.VISIBLE);
                itemThree.setVisibility(View.GONE);
                itemFour.setVisibility(View.GONE);
                image11.setVisibility(View.GONE);
                GlideImgManager.loadImage(mContext, list.get(0), image21);
                GlideImgManager.loadImage(mContext, list.get(1), image22);
                break;
            case 3:
                itemTwo.setVisibility(View.GONE);
                itemThree.setVisibility(View.VISIBLE);
                itemFour.setVisibility(View.GONE);
                image11.setVisibility(View.GONE);
                GlideImgManager.loadImage(mContext, list.get(0), image31);
                GlideImgManager.loadImage(mContext, list.get(1), image32);
                GlideImgManager.loadImage(mContext, list.get(2), image33);
                break;
            case 4:
                itemTwo.setVisibility(View.GONE);
                itemThree.setVisibility(View.GONE);
                itemFour.setVisibility(View.VISIBLE);
                image11.setVisibility(View.GONE);
                GlideImgManager.loadImage(mContext, list.get(0), image41);
                GlideImgManager.loadImage(mContext, list.get(1), image42);
                GlideImgManager.loadImage(mContext, list.get(2), image43);
                GlideImgManager.loadImage(mContext, list.get(3), image44);
                break;
            default:
                itemTwo.setVisibility(View.GONE);
                itemThree.setVisibility(View.GONE);
                itemFour.setVisibility(View.GONE);
                image11.setVisibility(View.GONE);
                break;
        }
        if (bean.getReplayCount() > 0) {
            liuyanNumTv.setText(String.valueOf(bean.getReplayCount()));
        } else {
            liuyanNumTv.setText("留言");
        }
    }

    @OnClick({R.id.image_1_1, R.id.image_2_1, R.id.image_2_2, R.id.image_3_1, R.id.image_3_2, R.id.image_3_3,
            R.id.image_4_1, R.id.image_4_2, R.id.image_4_3, R.id.image_4_4})
    void imageClick(View v) {
        Intent intent = new Intent(mContext, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_URLS, (Serializable) imageList);
        switch (v.getId()) {
            case R.id.image_1_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                mContext.startActivity(intent);
                break;
            case R.id.image_2_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                mContext.startActivity(intent);
                break;
            case R.id.image_2_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,1);
                mContext.startActivity(intent);
                break;
            case R.id.image_3_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                mContext.startActivity(intent);
                break;
            case R.id.image_3_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,1);
                mContext.startActivity(intent);
                break;
            case R.id.image_3_3:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,2);
                mContext.startActivity(intent);
                break;
            case R.id.image_4_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                mContext.startActivity(intent);
                break;
            case R.id.image_4_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,1);
                mContext.startActivity(intent);
                break;
            case R.id.image_4_3:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,2);
                mContext.startActivity(intent);
                break;
            case R.id.image_4_4:
                intent = intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX, 3);
                mContext.startActivity(intent);
                break;
            default:
                break;
        }

    }

    @InjectView(R.id.head_image)
    ImageView headImage;
    @InjectView(R.id.user_name_tv)
    TextView userNameTv;
    @InjectView(R.id.used_time)
    TextView usedTime;
    @InjectView(R.id.image_1_1)
    ImageView image11;
    @InjectView(R.id.image_2_1)
    ImageView image21;
    @InjectView(R.id.image_2_2)
    ImageView image22;
    @InjectView(R.id.item_two)
    LinearLayout itemTwo;
    @InjectView(R.id.image_3_1)
    ImageView image31;
    @InjectView(R.id.image_3_2)
    ImageView image32;
    @InjectView(R.id.image_3_3)
    ImageView image33;
    @InjectView(R.id.item_three)
    LinearLayout itemThree;
    @InjectView(R.id.image_4_1)
    ImageView image41;
    @InjectView(R.id.image_4_2)
    ImageView image42;
    @InjectView(R.id.image_4_3)
    ImageView image43;
    @InjectView(R.id.image_4_4)
    ImageView image44;
    @InjectView(R.id.item_four)
    LinearLayout itemFour;
    @InjectView(R.id.now_price_tv)
    TextView nowPriceTv;
    @InjectView(R.id.old_price_tv)
    TextView oldPriceTv;
    @InjectView(R.id.used_content_tv)
    TextView usedContentTv;
    @InjectView(R.id.used_address_tv)
    TextView usedAddressTv;
    @InjectView(R.id.liuyan_num_tv)
    TextView liuyanNumTv;
}
