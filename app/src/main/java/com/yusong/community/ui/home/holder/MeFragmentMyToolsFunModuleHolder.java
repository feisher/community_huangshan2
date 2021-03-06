//package com.yusong.community.ui.home.holder;
//
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.yusong.club.R;
//import com.yusong.club.ui.base.BaseHolder;
//import com.yusong.club.ui.me.mvp.entity.MyToolsFunModuleDatas;
//
//import java.util.List;
//
//import butterknife.InjectView;
//
///**
// * Created by feisher on 2017/1/17.
// */
//public class MeFragmentMyToolsFunModuleHolder extends BaseHolder<MyToolsFunModuleDatas> {
//    @InjectView(R.id.iv_itemImg)
//    ImageView ivItemImg;
//    @InjectView(R.id.tv_itemName)
//    TextView tvItemName;
//    @InjectView(R.id.ll_items)
//    LinearLayout llItems;
//    public MeFragmentMyToolsFunModuleHolder(View v) {
//        super(v);
//    }
//
//    /**
//     * 设置数据
//     * 刷新界面
//     *
//     * @param datas
//     * @param position
//     */
//    @Override
//    public void setData(List<MyToolsFunModuleDatas> datas, int position) {
//        ivItemImg.setBackgroundResource(datas.get(position).getImageId());
//        tvItemName.setText(datas.get(position).getName());
//    }
//}
