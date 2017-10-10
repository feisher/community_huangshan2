package com.yusong.club.ui.evaluate.adpter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.evaluate.EvaluateBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-02.
 * @describe: null
 */

public class EvaluateAdapter extends DefaultAdapter<EvaluateBean> {

    public EvaluateAdapter(List<EvaluateBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<EvaluateBean> getHolder(View v) {
        return new EvaluateHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_evaluate_coment;
    }
}
