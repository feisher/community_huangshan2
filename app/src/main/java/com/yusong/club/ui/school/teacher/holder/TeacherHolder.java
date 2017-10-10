package com.yusong.club.ui.school.teacher.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.school.teacher.bean.TelBook;
import com.yusong.club.utils.glide.GlideImgManager;

import org.apache.commons.lang.StringUtils;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by admin on 2017/1/13.
 */
public class TeacherHolder extends BaseHolder<TelBook.TeacherListBean> {
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_state)
    TextView tvState;
    @InjectView(R.id.tv_job)
    TextView tvJob;
    @InjectView(R.id.iv_icon)
    ImageView ivIcon;
    private TelBook.TeacherListBean mTeacherListBean;

    public TeacherHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<TelBook.TeacherListBean> datas, int position) {
        tvName.setText(datas.get(position).getTeacherName());
        mTeacherListBean = datas.get(position);
        if (mTeacherListBean.getIsManager() == 1) {
            tvState.setText("(班主任)");
        } else {
            if (mTeacherListBean.getIsDuty() == 1) {
                tvState.setText("(但前值班)");
            } else {
                tvState.setText("(没有值班)");
            }
        }

        if (!StringUtils.isEmpty(mTeacherListBean.getPortrait())){
            GlideImgManager.loadCircleImage(mContext,mTeacherListBean.getPortrait(),ivIcon);
        }

        tvJob.setText(mTeacherListBean.getCourseName());
    }
}
