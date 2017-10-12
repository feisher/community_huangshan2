package com.yusong.community.ui.express.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.express.holder.SearchSuccessHolder;
import com.yusong.community.ui.express.mvp.entity.ScanOrder;
import com.yusong.community.utils.AppUtils;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class SearchSuccessAdapter extends DefaultAdapter<ScanOrder.ShipperInfo> {


    public SearchSuccessAdapter(List<ScanOrder.ShipperInfo> mDatas,Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public SearchSuccessHolder getHolder(View v) {
        return new SearchSuccessHolder(v,mContext);
    }

    @Override
    public void onBindViewHolder(BaseHolder<ScanOrder.ShipperInfo> holder, int position) {
        ScanOrder.ShipperInfo info = mDatas.get(position);
        SearchSuccessHolder hodler = (SearchSuccessHolder) holder;
        hodler.mTvName.setText(info.getShipperName());
        mShowInfoLisener.setOnShowInfoLisener(hodler.mTvNumber);
        String code = info.getShipperCode();
        AppUtils.setIcon(hodler.mIvIcon,code);
    }


    //自定义回调
    protected ShowInfoLisener mShowInfoLisener;

    public interface ShowInfoLisener {
        void setOnShowInfoLisener(TextView tv_number);
    }

    public void setOnShowInfoListener(ShowInfoLisener mShowInfoLisener) {
        this.mShowInfoLisener = mShowInfoLisener;
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_express_search_success;
    }
}
