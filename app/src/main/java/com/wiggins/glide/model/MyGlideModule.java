package com.wiggins.glide.model;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * @Description GlideModule
 * @Explanation 在Glide源码中有一个DiskCache接口，里面的Factory类定义了默认的磁盘缓存大小为：250M，缓存路径为：image_manager_disk_cache目录下
 * @Author 一花一世界
 */

public class MyGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置Glide内存缓存大小
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
        int memoryCacheSize = maxMemory / 8;//设置图片内存缓存占用八分之一
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));//设置内存缓存大小

        //设置BitmapPool缓存内存大小
        builder.setBitmapPool(new LruBitmapPool(memoryCacheSize));

        //设置Glide磁盘缓存大小及缓存路径
        String diskCachePath;
        int diskCacheSize = 1024 * 1024 * 30;//最多可以缓存多少字节的数据30M
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            diskCachePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/glideCache";
        } else {
            diskCachePath = context.getExternalCacheDir().getPath();
        }
        builder.setDiskCache(new DiskLruCacheFactory(diskCachePath, "glide", diskCacheSize));//设置磁盘缓存大小
        //builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "glide", diskCacheSize)); //sd卡中
        //builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSize));//内存中

        //设置图片解码格式
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
