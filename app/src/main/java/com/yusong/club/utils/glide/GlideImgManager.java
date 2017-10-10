package com.yusong.club.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.youth.banner.loader.ImageLoaderInterface;
import com.yusong.club.R;

import java.io.ByteArrayOutputStream;
import java.io.File;



public class GlideImgManager implements ImageLoaderInterface {

//    public static final  float THUMBNAIL_LEVEL =0.2f;
    public GlideImgManager() {
    }

    public static void loadImage(Context context, String url, int erroImg, int emptyImg, ImageView iv) {
        //原生 API  
        Glide.with(context).load(url).placeholder(emptyImg).error(erroImg).diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
    }

    public static void loadImage(Context context, String url, ImageView iv) {
        //原生 API
        Glide.with(context).load(url).placeholder(R.mipmap.empty_photo).error(R.mipmap.empty_photo).diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
    }
    public static void loadVideoImage(Context context, String url, ImageView iv) {
        //原生 API
        Glide.with(context).load(url).placeholder(R.mipmap.xxx1).error(R.mipmap.xxx1).diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
    }
    public static void loadImageFitCenter(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).fitCenter().diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
    }
    public static void loadBitmapFitCenter(Context context, Bitmap mBitmap, ImageView iv) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes=baos.toByteArray();
        Glide.with(context).load(bytes).fitCenter().error(R.mipmap.empty_photo).diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
}
    public static void loadImageCenterCrop(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).centerCrop().placeholder(R.mipmap.empty_photo).diskCacheStrategy(DiskCacheStrategy.RESULT).error(R.mipmap.empty_photo).into(iv);
    }

    public static void loadBitmap(Context context, final Bitmap mBitmap, final ImageView imageView) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes=baos.toByteArray();
        Glide.with(context)
                .load(bytes)
                .error(R.mipmap.empty_photo)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);


    }

    public static void loadImage(Context context, final int resourceId, final ImageView imageView) {
        Glide.with(context)
                .load(resourceId)
                .error(R.mipmap.empty_photo)
                .into(imageView);
    }

    public static void loadImageCenterCrop(Context context, final File file, final ImageView imageView) {
        Glide.with(context)
                .load(file)
                .error(R.mipmap.empty_photo)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .centerCrop()
                .into(imageView);


    }
    public static void loadImageFitCenter(Context context, final File file, final ImageView imageView) {
        Glide.with(context)
                .load(file)
                .error(R.mipmap.empty_photo)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .fitCenter()
                .into(imageView);


    }

    public static void loadImageCenterCrop(Context context, final int resourceId, final ImageView imageView) {
        Glide.with(context)
                .load(resourceId)
                .error(R.mipmap.empty_photo)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .centerCrop()
                .into(imageView);
    }
    public static void loadImageFitCenter(Context context, final int resourceId, final ImageView imageView) {
        Glide.with(context)
                .load(resourceId)
                .error(R.mipmap.empty_photo)
                .fitCenter()
                .into(imageView);
    }
    /**
     * 显示gif图
     * @param context
     * @param url
     * @param iv
     */
    public static void loadGifImage(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.mipmap.empty_photo).error(R.mipmap.empty_photo).into(iv);
    }

    /**
     * 显示圆形图片
     * @param context
     * @param iv
     */
    public static void loadCircleImage(final Context context, final int resourceId, final ImageView iv) {

        Glide.with(context).load(resourceId).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv) {
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
                .placeholder(R.mipmap.default_user_icon)
                .error(R.mipmap.default_user_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
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

    /**
     * 显示圆角图片
     * @param context
     * @param url
     * @param iv
     */
    public static void loadRoundCornerImage(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).centerCrop().placeholder(R.mipmap.empty_photo).
                error(R.mipmap.empty_photo).transform(new GlideRoundTransform(context, 10)).diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
    }


    @Override
    public void displayImage(Context context, Object path, View imageView) {
        Glide.with(context).load(path).placeholder(R.mipmap.empty_photo).
                error(R.mipmap.empty_photo).diskCacheStrategy(DiskCacheStrategy.RESULT).into((ImageView) imageView);
    }

    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        ImageView imageView=new ImageView(context);
        return imageView;
    }


}  