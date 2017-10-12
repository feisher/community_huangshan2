package com.yusong.community.ui.community.photoview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.utils.glide.GlideImgManager;

import butterknife.InjectView;


/**
 * 大图详情界面
 */
public class ImageDetailFragment extends BaseFragment {
    @InjectView(R.id.mBigImage)
    PhotoView mBigImage;
    @InjectView(R.id.image)
    ImageView image;
    private String mImageUrl;
    private Activity activity;
    private View view;
    public Info mInfo;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        context = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
    }


    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.fragment_big_image, null);
        activity = getActivity();
        return view;
    }

    @Override
    public void initData() {
        mBigImage.enable();
    }

    @Override
    public void onResume() {
        super.onResume();
//        image.setImageURI(Uri.parse(mImageUrl));
        mBigImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
        imageZom(image,mImageUrl);
    }

    public void imageZom(ImageView img, String url) {
        GlideImgManager.loadImageFitCenter(mContext, url, mBigImage);
//        mInfo = PhotoView.getImageViewInfo(img);
//        mBigImage.animaFrom(mInfo);
    }

    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment f = new ImageDetailFragment();
        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        f.setArguments(args);
        return f;
    }

}
