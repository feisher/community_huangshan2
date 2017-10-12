package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.school.bean.AddressBean;

import java.util.List;

/**
 * @author Mr_Peng
 *         crated at on 2017/6/1.
 *         描述: null
 */

public interface ISchoolAddressView extends BaseView {
    void queryAddressViewSucced(List<AddressBean> datas);
    void listSizeNull();
}
