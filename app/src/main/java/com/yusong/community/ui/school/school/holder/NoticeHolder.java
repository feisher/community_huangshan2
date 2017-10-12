package com.yusong.community.ui.school.school.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.school.mvp.entity.Notice;
import com.yusong.community.ui.school.school.activity.NoticeActivity;
import com.yusong.community.ui.school.school.activity.NoticeDetailActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/3/7.
 */
public class NoticeHolder extends BaseHolder<Notice> {

    @InjectView(R.id.item_img)
    ImageView itemImg;
    @InjectView(R.id.item_title)
    TextView itemTitle;
    @InjectView(R.id.item_time)
    TextView itemTime;
    @InjectView(R.id.item_content)
    TextView itemContent;
    @InjectView(R.id.item_count)
    TextView itemCount;
    @InjectView(R.id.item_delete)
    LinearLayout itemDelete;
    private int isDelete; //是否可以删除 为1不可删除

    public NoticeHolder(View v, Context mContext,int isDelete) {
        super(v, mContext);
        this.isDelete=isDelete;
    }

    @Override
    public void setData(final List<Notice> datas, final int position) {
        final NoticeActivity activity = (NoticeActivity) NoticeHolder.this.mContext;
        try {
            final Notice notice = datas.get(position);
            if (notice != null) {
                itemTitle.setText(notice.getTitle());
                itemContent.setText(notice.getContent());
                itemCount.setText("阅读" + notice.getReadCount() + "次");
//                itemCount.setText("");
                final String createTime = notice.getCreateTime();
                //时间显示需要特殊处理   2016-8-28  09:42:40
                long currentTimeMillis = System.currentTimeMillis();//获取当前时间毫秒值
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    Date d = dateFormat.parse(createTime);
                    long time = d.getTime();
                    long time_D_Value = currentTimeMillis - time;//时间差
                    if (time_D_Value < 60 * 1000) {//一分钟内
                        itemTime.setText("刚刚");
                    } else if (time_D_Value < 60 * 60 * 1000) {//一小时内
                        itemTime.setText((int) (time_D_Value / (1000 * 60)) + "分钟前");
                    } else if (time_D_Value < 24 * 60 * 60 * 1000) {//一天内
                        itemTime.setText((int) (time_D_Value / (1000 * 60 * 60)) + "小时前");
                    } else if (time_D_Value < 7 * 24 * 60 * 60 * 1000) {//一周内
                        itemTime.setText((int) (time_D_Value / (1000 * 60 * 60 * 24)) + "天前");
                    } else {
                        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM月dd日");
                        String releaseDate = dateFormat2.format(d);
                        itemTime.setText(releaseDate);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                final int noticeType = notice.getNoticeType();
                if (noticeType == 1) {//通知
                    itemImg.setImageResource(R.mipmap.inform);
                } else {//2公告
                    itemImg.setImageResource(R.mipmap.notice);
                }
                if (isDelete == 1) {
                    itemDelete.setVisibility(View.GONE);
                } else {
                    itemDelete.setVisibility(View.VISIBLE);
                }
                itemDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final BaseDialog baseDialog = new BaseDialog(mContext);
                        baseDialog.setTitle("提示")
                                .setMessage("真的要删除这条公告吗？")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        activity.deleteNoticeItem(datas.get(position).getId(), position);
                                        baseDialog.dismiss();
                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                baseDialog.dismiss();
                            }
                        });

                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent mIntent = new Intent(mContext, NoticeDetailActivity.class);
                        mIntent.putExtra("noticeType", noticeType);
                        mIntent.putExtra("noticeTitle", notice.getTitle());
                        mIntent.putExtra("noticeContent", notice.getContent());
                        mIntent.putExtra("noticeCreateTime", createTime);
                        mIntent.putExtra("noticeId", notice.getId());
                        mContext.startActivity(mIntent);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
