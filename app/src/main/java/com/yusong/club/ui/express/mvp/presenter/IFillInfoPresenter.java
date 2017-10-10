package com.yusong.club.ui.express.mvp.presenter;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.yusong.club.mvp.BasePresenter;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface IFillInfoPresenter extends BasePresenter {

    void commit(EditText name, EditText phone, TextView tv_city, EditText address, CheckBox cbDftSender, CheckBox cbSava,
                int info_fill, String province, String city, String couny, String provinceCode, String cityCode, String districtCode, int contact_id
                ,int request_id);
}
