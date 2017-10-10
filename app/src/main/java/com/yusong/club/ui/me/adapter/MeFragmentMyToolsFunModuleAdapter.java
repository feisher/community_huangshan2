package com.yusong.club.ui.me.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.me.holder.MeFragmentMyToolsFunModuleHolder;
import com.yusong.club.ui.me.mvp.entity.MyToolsFunModuleDatas;

import java.util.List;

/**
 * Created by feisher on 2017/1/17.
 */
public class MeFragmentMyToolsFunModuleAdapter extends DefaultAdapter<MyToolsFunModuleDatas> {



    public MeFragmentMyToolsFunModuleAdapter(List<MyToolsFunModuleDatas> mMyToolsFunModuleDatasList,Context mContext) {
        super(mMyToolsFunModuleDatasList, mContext);
    }

    /**
     * 子类实现提供holder
     *
     * @param v
     * @return
     */
    @Override
    public BaseHolder<MyToolsFunModuleDatas> getHolder(View v) {
        return new MeFragmentMyToolsFunModuleHolder(v,mContext);
    }

    /**
     * 提供Item的布局
     *
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.item_mefragment_fun_module;
    }
}
