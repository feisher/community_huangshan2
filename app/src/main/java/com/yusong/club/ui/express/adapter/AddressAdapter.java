package com.yusong.club.ui.express.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.express.activity.FillInfoActivity;
import com.yusong.club.ui.express.holder.AddressHolder;
import com.yusong.club.ui.express.mvp.entity.ContactGroup;
import com.yusong.club.utils.ActivityConstants;

import java.util.List;


/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class AddressAdapter extends SwipeMenuAdapter<AddressHolder> {

    private final Context mContext;
    private List<ContactGroup> mDatas;
    protected DefaultAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public AddressAdapter(List<ContactGroup> mDatas, Context context) {
        super();
        this.mDatas = mDatas;
        this.mContext = context;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_express_address, parent, false);
        return view;
    }

    @Override
    public AddressHolder onCompatCreateViewHolder(View realContentView, int viewType) {

        AddressHolder mAddressHolder = new AddressHolder(realContentView, mContext);
        mAddressHolder.setOnItemClickListener(new BaseHolder.OnViewClickListener() {//设置Item点击事件
            @Override
            public void onViewClick(View view, int position) {

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, mDatas.get(position), position);
                }
            }
        });

        return mAddressHolder;
    }

    @Override
    public void onBindViewHolder(AddressHolder holder, int position) {
        final ContactGroup contact = mDatas.get(position);

        int receiverflag = contact.getFavoriteReceiverFlag();
        int senderflag = contact.getFavoriteSenderFlag();
        int type = contact.getType();// 寄件或者收件
        if (receiverflag == 1) {
            holder.mIvIcon.setVisibility(View.VISIBLE);
            holder.mIvIcon.setImageResource(R.mipmap.getemail);
            holder.mBtnDefaultGet.setVisibility(View.GONE);
            holder.mBtnDefaultSender.setVisibility(View.GONE);
        } else if (senderflag == 1) {
            holder.mIvIcon.setVisibility(View.VISIBLE);
            holder.mIvIcon.setImageResource(R.mipmap.email);
            holder.mBtnDefaultSender.setVisibility(View.GONE);
            holder.mBtnDefaultGet.setVisibility(View.GONE);
        } else {
            if(type==1){
                holder.mIvIcon.setVisibility(View.GONE);
                holder.mBtnDefaultSender.setVisibility(View.VISIBLE);
                holder.mBtnDefaultGet.setVisibility(View.GONE);
            }else if(type==2){
                holder.mIvIcon.setVisibility(View.GONE);
                holder.mBtnDefaultSender.setVisibility(View.GONE);
                holder.mBtnDefaultGet.setVisibility(View.VISIBLE);
            }else{
                holder.mIvIcon.setVisibility(View.GONE);
                holder.mBtnDefaultSender.setVisibility(View.GONE);
                holder.mBtnDefaultGet.setVisibility(View.GONE);
            }
        }

        holder.mTvName.setText(contact.getContactName());
        holder.mTvPhone.setText(contact.getMobile());
        holder.mTvAddress.setText(contact.getProvinceName() + contact.getCityName() + contact.getDistrictName() + contact.getStreet());

        /**
         * 默认寄件人
         */

        holder.mBtnDefaultSender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShowDiaLogLisener.setDftDiaLog(contact, 1);
            }
        });

        /**
         * 默认收件人
         */
        holder.mBtnDefaultGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShowDiaLogLisener.setDftDiaLog(contact, 2);
            }
        });

        /**
         * 编辑
         */
        holder.mBtnEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MyApplication.getInstance(), FillInfoActivity.class);
                mIntent.putExtra(ActivityConstants.INFO_FILL, 4);
                mIntent.putExtra(ActivityConstants.INFO_NAME, contact.getContactName());
                mIntent.putExtra(ActivityConstants.INFO_ADDRESS, contact.getStreet());
                mIntent.putExtra(ActivityConstants.INFO_PHONE, contact.getMobile());
                mIntent.putExtra(ActivityConstants.INFO_CITY, contact.getProvinceName() + contact.getCityName() + contact.getDistrictName());
                mIntent.putExtra(ActivityConstants.CITY, contact.getCityName());
                mIntent.putExtra(ActivityConstants.PROVINCE, contact.getProvinceName());
                mIntent.putExtra(ActivityConstants.COUNY, contact.getDistrictName());
                mIntent.putExtra(ActivityConstants.TYPE, contact.getType());
                int id = contact.getId();
                mIntent.putExtra(ActivityConstants.CONTACT_ID, id);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getInstance().startActivity(mIntent);
            }
        });
    }

    public void setOnItemClickListener(DefaultAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public int getItemCount() {
        return mDatas.size();
    }


    //自定义回调
    protected ShowDiaLogLisener mShowDiaLogLisener;

    public interface ShowDiaLogLisener {

        void setDftDiaLog(ContactGroup contact, int i);
    }

    public void setOnShowDiaLogLisener(ShowDiaLogLisener mShowDiaLogLisener) {
        this.mShowDiaLogLisener = mShowDiaLogLisener;
    }

}
