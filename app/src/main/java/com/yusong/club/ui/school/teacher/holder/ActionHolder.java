package com.yusong.club.ui.school.teacher.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.school.school.bean.ActivityBean;
import com.yusong.club.utils.DateUtil;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by admin on 2017/1/13.
 */
public class ActionHolder extends BaseHolder<ActivityBean> {
    @InjectView(R.id.item_name)
    TextView itemName;
    @InjectView(R.id.item_state)
    TextView itemState;
    @InjectView(R.id.item_time)
    TextView itemTime;
    @InjectView(R.id.item_surplus)
    TextView itemSurplus;
    @InjectView(R.id.item_action_name)
    TextView itemActionName;
    @InjectView(R.id.item_action_time)
    TextView itemActionTime;
    @InjectView(R.id.item_action_content)
    TextView itemActionContent;
    @InjectView(R.id.item_good)
    TextView itemGood;
    @InjectView(R.id.linear_good)
    LinearLayout linearGood;
    @InjectView(R.id.item_talk)
    TextView itemTalk;
    @InjectView(R.id.linear_talk)
    LinearLayout linearTalk;
    @InjectView(R.id.iv_header)
    ImageView ivHeader;
    @InjectView(R.id.iv_action_photon)
    ImageView ivActionPhoton;
    @InjectView(R.id.tv_zan)
    ImageView tvZan;
    private ActionDo actionDo;

    public ActionDo getActionDo() {
        return actionDo;
    }

    public void setActionDo(ActionDo actionDo) {
        this.actionDo = actionDo;
    }

    public ActionHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<ActivityBean> datas, final int position) {

        switch (datas.get(position).getStatus()) {
            case 1:
                itemState.setText("未开始");
                itemSurplus.setVisibility(View.VISIBLE);
                itemSurplus.setText("剩余:" + DateUtil.getMinusToString(datas.get(position).getBeginTime()));
                break;
            case 2:
                itemState.setText("进行中");
                itemSurplus.setVisibility(View.VISIBLE);
                itemSurplus.setText("");
//              itemSurplus.setText("剩余:" + DateUtil.getMinusToString(datas.get(position).getBeginTime()));
                break;
            case 3:
                itemSurplus.setVisibility(View.INVISIBLE);
                itemSurplus.setText("");
                itemState.setText("已结束");
                break;
        }
        linearGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionDo != null) {
                    actionDo.giveGood(position);
                }
            }
        });
        linearTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionDo != null) {
                    actionDo.giveComment(position);
                }
            }
        });
        if (datas.get(position)!=null){
            if(datas.get(position).getSupportAmount()!=null&&!datas.get(position).getSupportAmount().equals("0")){
                itemGood.setText("赞 (" + datas.get(position).getSupportAmount() + ")");
                itemGood.setTextColor(mContext.getResources().getColor(R.color.font_color_orange));
                tvZan.setImageResource(R.mipmap.ic_dian_zan);
            }else {
                itemGood.setTextColor(mContext.getResources().getColor(R.color.font_color_4));
                tvZan.setImageResource(R.mipmap.ic_undian_zan);
                itemGood.setText("赞 (" + 0 + ")");
            }
        }

        itemTalk.setText("评论 (" + datas.get(position).getCommentAmount() + ")");
        itemName.setText(datas.get(position).getCreateUserName());
        itemTime.setText(datas.get(position).getCreateTime());
        itemActionName.setText(datas.get(position).getActivityName());
        itemActionTime.setText(DateUtil.getDateYearNewToString(datas.get(position).getBeginTime()) + "-" + DateUtil.getDateYearNewToString(datas.get(position).getEndTime()));
        itemActionContent.setText(datas.get(position).getMemo());
        if (datas.get(position).getCreateUserIconPath() != null && !(datas.get(position).getCreateUserIconPath().equals(""))) {
            GlideImgManager.loadCircleImage(mContext, datas.get(position).getCreateUserIconPath(), ivHeader);
        } else {
            ivActionPhoton.setImageResource(R.mipmap.school_four);
        }
        if (datas.get(position).getImagePath() != null && !(datas.get(position).getImagePath().equals(""))) {
            GlideImgManager.loadImage(mContext, datas.get(position).getImagePath(), ivActionPhoton);
        }
    }

    public interface ActionDo {
        public void giveGood(int pos);

        public void giveComment(int pos);

    }

}
