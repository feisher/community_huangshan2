package com.yusong.club.ui.express.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.express.mvp.entity.GetOrderInfo;
import com.yusong.club.ui.express.mvp.entity.SaveOrderInfo;
import com.yusong.club.ui.express.mvp.entity.ScanOrder;
import com.yusong.club.ui.express.mvp.entity.SenderOrderInfo;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface IOrderView extends BaseView {
    void setSaveOrderAdapter(List<SaveOrderInfo> data);

    void setSenderOrderAdapter(List<SenderOrderInfo> data);

    void setGetOrderAdapter(List<GetOrderInfo> data);

    void hideRefresh();

    void jumpActivity(ScanOrder.ShipperInfo info, String number);

    void refreshList(int position);

    void jumpActivity(int charge, String id);
}
