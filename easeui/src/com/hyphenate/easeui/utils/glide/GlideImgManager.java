package com.hyphenate.easeui.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.hyphenate.easeui.R;


public class GlideImgManager {

    public static final  float THUMBNAIL_LEVEL =0.2f;
    public GlideImgManager() {
    }

    public static void loadImage(Context context, String url, int erroImg, int emptyImg, ImageView iv) {
        //原生 API  
        Glide.with(context).load(url).placeholder(emptyImg).thumbnail(THUMBNAIL_LEVEL).error(erroImg).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
    }


    /**
     * 显示圆形图片
     * @param context
     * @param iv
     */
    public static void loadCircleImage(final Context context, final int resourceId, final ImageView iv) {

        Glide.with(context).load(resourceId).asBitmap().centerCrop().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.RESULT).into(new BitmapImageViewTarget(iv) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                iv.setImageDrawable(circularBitmapDrawable);
            }
        });

    }

    /**
     * 显示圆形图片
     * @param context
     * @param url
     * @param iv
     */
    public static void loadCircleImage(final Context context, String url, final ImageView iv) {

        Glide.with(context).load(url).asBitmap().centerCrop()
                .thumbnail(THUMBNAIL_LEVEL)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.default_user_icon)
                .into(new BitmapImageViewTarget(iv) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                iv.setImageDrawable(circularBitmapDrawable);
            }
        });

    }


    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        ImageView imageView=new ImageView(context);
        return imageView;
    }


}  