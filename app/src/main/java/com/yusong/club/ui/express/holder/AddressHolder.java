package com.yusong.club.ui.express.holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.express.mvp.entity.ContactGroup;

import java.util.List;

import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class AddressHolder extends BaseHolder<ContactGroup> {


    @InjectView(R.id.iv_icon)
    public ImageView mIvIcon;
    @InjectView(R.id.tv_name)
    public TextView mTvName;
    @InjectView(R.id.tv_phone)
    public TextView mTvPhone;
    @InjectView(R.id.tv_address)
    public TextView mTvAddress;
    @InjectView(R.id.btn_default_sender)
    public Button mBtnDefaultSender;
    @InjectView(R.id.btn_default_get)
    public Button mBtnDefaultGet;
    @InjectView(R.id.btn_editor)
    public Button mBtnEditor;

    public AddressHolder(View itemView, Context mContext) {
        super(itemView,  mContext);
    }

    @Override
    public void setData(List<ContactGroup> datas, int position) {

    }

}
