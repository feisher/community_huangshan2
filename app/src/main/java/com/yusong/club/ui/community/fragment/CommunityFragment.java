package com.yusong.club.ui.community.fragment;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bm.library.Info;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.MainActivity;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.community.event.CreatePostEvent;
import com.yusong.club.ui.community.event.CreatePostRefashEvent;
import com.yusong.club.ui.community.mvp.entity.PostsCatogrey;
import com.yusong.club.ui.community.mvp.implView.IPostCatogreyView;
import com.yusong.club.ui.community.mvp.presenter.impl.CommunityFragmentPresenterImpl;
import com.yusong.club.utils.CacheUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


/**
 * 社区模块主界面碎片
 * create by feisher on 2017/1/10 12:48
 * Email：458079442@qq.com
 */
public class CommunityFragment extends BaseFragment implements IPostCatogreyView {

//

    public CommunityFragmentPresenterImpl mPresenter;
    @InjectView(R.id.tl_community_fragment)
    TabLayout tlCommunityFragment;
    @InjectView(R.id.vp_community_fragment)
    ViewPager vpCommunityFragment;
    public Info mInfo;
//    @InjectView(R.id.photoview)
//    PhotoView photoView;
    private List<CommunityItemFragment> mAllCommunityItemFragments = new ArrayList<>();
    public List<PostsCatogrey> data = new ArrayList<>();
    public ImageView imageView;

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_community, null);
    }

    @Override
    public void initData() {
//        photoView.enable();//设置可缩放
        mPresenter = new CommunityFragmentPresenterImpl(this, mContext);
        mPresenter.queryPostCatogrey(CacheUtils.getToken(mContext));
        EventBus.getDefault().register(this);
    }


    @Override
    public void initListener() {

    }

    /**
     * 社区列表界面帖子点赞回调
     * @param id
     */
    public void postsLike(int id){
        mPresenter.commitLikePosts(id);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void setViewPagerAdapter(List<PostsCatogrey> data) {
        this.data.addAll(data);
        if (data.size() > 3) {
            tlCommunityFragment.setLayoutMode(TabLayout.MODE_SCROLLABLE);
        } else {
            tlCommunityFragment.setLayoutMode(TabLayout.MODE_FIXED);
        }
        MainActivity activity = (MainActivity) mContext;
        activity.getPostCatagory(data);
        initFragment();
        initViewPager();
    }

    private void initViewPager() {
        vpCommunityFragment.setOffscreenPageLimit(0);
        //设置adapter
        vpCommunityFragment.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mAllCommunityItemFragments.get(position);
            }

            @Override
            public int getCount() {
                return mAllCommunityItemFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
//                String[] title = null;
//                title = new String[]{"谈天说地", "房屋租赁", "二手交易"};
//                return title[position];
                return data.get(position).getCategoryName();
            }
        });
        //关联页签与viewpager
        tlCommunityFragment.setupWithViewPager(vpCommunityFragment);

    }
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void getCreatePostEvent(CreatePostEvent mCreatePostEvent){
//        MyApplication.showMessage("接收到发帖");
       vpCommunityFragment.setCurrentItem(mCreatePostEvent.getPostTypeIndex());
        EventBus.getDefault().post(new CreatePostRefashEvent(mCreatePostEvent.getCatogreyId()));
    }
    private void initFragment() {
        for (PostsCatogrey postsCatogrey : data) {
            mAllCommunityItemFragments.add(CommunityItemFragment.newInstance(
                    postsCatogrey.getCategoryName(), postsCatogrey.getId()));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        EventBus.getDefault().unregister(this);
    }

//    @OnClick(R.id.photoview)
//    public void onClick() {
////        photoView.animaTo(mInfo, new Runnable() {
////            @Override
////            public void run() {
////                photoView.setVisibility(View.GONE);
////            }
////        });
//    }

//    public void imageZom(ImageView img, String url) {
//        photoView.setVisibility(View.VISIBLE);
//        GlideImgManager.loadImageFitCenter(mContext,url,photoView);
//        mInfo = PhotoView.getImageViewInfo(img);
////        img.setVisibility(View.GONE);
////        Glide.with(this).load(url).fitCenter().into(photoView);
//        photoView.animaFrom(mInfo);
//    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(getActivity());
    }
}
