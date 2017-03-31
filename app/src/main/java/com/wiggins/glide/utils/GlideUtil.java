package com.wiggins.glide.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wiggins.glide.R;
import com.wiggins.glide.listener.OnLoadCallback;

/**
 * @Description Glide图片加载工具类
 * @Author 一花一世界
 */
public class GlideUtil {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    public static void loadImage(Context context, String url, final ImageView view) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.image_load)//加载时显示的图片
                .error(R.drawable.image_error)//加载失败时显示的图片
                .into(view);
    }

    public static void loadImage(Context context, String url, final ImageView view, final OnLoadCallback callback) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.image_load)//加载时显示的图片
                .error(R.drawable.image_error)//加载失败时显示的图片
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        callback.onException();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        callback.onSuccess();
                        return false;
                    }
                }).into(view);
    }

    /***
     * 资源 id 转换成 Uri
     *
     * @param context
     * @param resourceId
     */
    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    /**
     * 清除缓存
     *
     * @param context
     */
    public static void clearCache(final Context context) {
        clearMemoryCache(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                clearDiskCache(context);
            }
        }).start();
    }

    /**
     * 清除内存缓存
     *
     * @param context
     */
    public static void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 清除磁盘缓存
     *
     * @param context
     */
    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }
}
