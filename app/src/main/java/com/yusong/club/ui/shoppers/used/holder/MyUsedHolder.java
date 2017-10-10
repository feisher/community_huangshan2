package com.yusong.club.ui.shoppers.used.holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.community.photoview.PhotoViewActivity;
import com.yusong.club.ui.shoppers.used.bean.MyUsedBean;
import com.yusong.club.ui.shoppers.used.event.EventMyUsed;
import com.yusong.club.utils.glide.GlideImgManager;

import org.greenrobot.eventbus.EventBus;

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

public class MyUsedHolder extends BaseHolder<MyUsedBean> {

    private List<String> imageList;

    public MyUsedHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(final List<MyUsedBean> datas, final int position) {
        MyUsedBean bean = datas.get(position);
        this.imageList = bean.getImageList();
        usedContentTv.setText(bean.getIntroduction());
        nowPriceTv.setText("￥" + String.valueOf(bean.getPrice()));
        oldPriceTv.setText("原价 " + bean.getShowPrice());
        oldPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
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
        int status = bean.getItemStatus();
        if (status == 1 || status == 2) {
            editButton.setVisibility(View.VISIBLE);
            outButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.VISIBLE);
            timeAndSt.setText(bean.getItemStatusName());
            timeAndSt.setTextColor(Color.parseColor("#FF5E28"));
        } else if (status == 3) {
            editButton.setVisibility(View.GONE);
            outButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.GONE);
            long currentTimeMillis = System.currentTimeMillis();//获取当前时间毫秒值
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date d = dateFormat.parse(bean.getExpireTime());
                long time = d.getTime();
                long time_D_Value = time - currentTimeMillis;//时间差
                timeAndSt.setTextColor(Color.parseColor("#999999"));
                if (time_D_Value > 0) {
                    timeAndSt.setText((int) (time_D_Value / (1000 * 60 * 60 * 24)) + "天过期");
                } else {
                    timeAndSt.setText("即将过期");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // TODO: 2017/3/23
        } else if (status == 4||status == 5) {
            editButton.setVisibility(View.GONE);
            outButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.VISIBLE);
            timeAndSt.setText(bean.getItemStatusName());
            timeAndSt.setTextColor(Color.parseColor("#FF5E28"));
        }
        final EventMyUsed used = new EventMyUsed();
        used.setBean(datas.get(position));
        editButton.setOnClickListener(new View.OnClickListener() {//修改
            @Override
            public void onClick(View view) {
                used.setType(1);
                EventBus.getDefault().post(used);
            }
        });

        outButton.setOnClickListener(new View.OnClickListener() {//下架
            @Override
            public void onClick(View view) {
                used.setType(2);
                EventBus.getDefault().post(used);
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {//删除
            @Override
            public void onClick(View view) {
                used.setType(3);
                EventBus.getDefault().post(used);
            }
        });
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
    @InjectView(R.id.used_content_tv)
    TextView usedContentTv;
    @InjectView(R.id.now_price_tv)
    TextView nowPriceTv;
    @InjectView(R.id.old_price_tv)
    TextView oldPriceTv;
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
    @InjectView(R.id.time_and_st)
    TextView timeAndSt;
    @InjectView(R.id.edit_button)
    Button editButton;
    @InjectView(R.id.out_button)
    Button outButton;
    @InjectView(R.id.delete_button)
    Button deleteButton;
}
