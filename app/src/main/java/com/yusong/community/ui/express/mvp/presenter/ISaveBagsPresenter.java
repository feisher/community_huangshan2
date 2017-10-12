package com.yusong.community.ui.express.mvp.presenter;

import android.widget.EditText;

import com.yusong.community.mvp.BasePresenter;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface ISaveBagsPresenter extends BasePresenter {
    void commitOrder(EditText phone, EditText mome);

    void queryDisclaimer();
}
