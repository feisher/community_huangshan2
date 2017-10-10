package com.yusong.club.ui.repairs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.community.photoview.PhotoViewActivity;
import com.yusong.club.utils.glide.GlideImgManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-09-01.
 * @describe: null
 */

public class RepairsHistoryHolder extends BaseHolder<RepairsHistoryBean> {
    public RepairsHistoryHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<RepairsHistoryBean> datas, int position) {
        RepairsHistoryBean bean = datas.get(position);
        switch (bean.getCategoryId()) {
            case 1:
                leixingTv.setText("房屋维修");
                backgroundImage.setImageResource(R.mipmap.repairs_blackground_1);
                break;
            case 2:
                leixingTv.setText("园区维修");
                backgroundImage.setImageResource(R.mipmap.repairs_blackground_2);
                break;
            case 3:
                leixingTv.setText("园区保洁");
                backgroundImage.setImageResource(R.mipmap.repairs_blackground_3);
                break;
            case 4:
                leixingTv.setText("园区绿化");
                backgroundImage.setImageResource(R.mipmap.repairs_blackground_4);
                break;
        }
        if (bean.getRepairStatus() == 1) {
            statusTv.setTextColor(Color.parseColor("#ff0000"));
            statusTv.setText("未处理");
        } else {
            statusTv.setTextColor(Color.parseColor("#000000"));
            statusTv.setText("已处理");
        }

        contentTv.setText(bean.getContent());
        createTimeTv.setText(bean.getCreateTime());
        List<String> list = new ArrayList<String>();
        if (!TextUtils.isEmpty(bean.getImage1())) {
            list.add(bean.getImage1());
        }
        if (!TextUtils.isEmpty(bean.getImage2())) {
            list.add(bean.getImage2());
        }
        if (!TextUtils.isEmpty(bean.getImage3())) {
            list.add(bean.getImage3());
        }
        if (!TextUtils.isEmpty(bean.getImage4())) {
            list.add(bean.getImage4());
        }
        imageList = list;
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
    }

    private List<String> imageList = new ArrayList<String>();

    @OnClick({R.id.image_1_1, R.id.image_2_1, R.id.image_2_2, R.id.image_3_1, R.id.image_3_2, R.id.image_3_3,
            R.id.image_4_1, R.id.image_4_2, R.id.image_4_3, R.id.image_4_4})
    void imageClick(View v) {
        Intent intent = new Intent(mContext, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_URLS, (Serializable) imageList);
        switch (v.getId()) {
            case R.id.image_1_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX, 0);
                mContext.startActivity(intent);
                break;
            case R.id.image_2_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX, 0);
                mContext.startActivity(intent);
                break;
            case R.id.image_2_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX, 1);
                mContext.startActivity(intent);
                break;
            case R.id.image_3_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX, 0);
                mContext.startActivity(intent);
                break;
            case R.id.image_3_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX, 1);
                mContext.startActivity(intent);
                break;
            case R.id.image_3_3:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX, 2);
                mContext.startActivity(intent);
                break;
            case R.id.image_4_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX, 0);
                mContext.startActivity(intent);
                break;
            case R.id.image_4_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX, 1);
                mContext.startActivity(intent);
                break;
            case R.id.image_4_3:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX, 2);
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

    @InjectView(R.id.background_image)
    ImageView backgroundImage;
    @InjectView(R.id.leixing_tv)
    TextView leixingTv;
    @InjectView(R.id.status_tv)
    TextView statusTv;
    @InjectView(R.id.content_tv)
    TextView contentTv;
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
    @InjectView(R.id.create_time_tv)
    TextView createTimeTv;
}
