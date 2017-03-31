package com.wiggins.glide.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.wiggins.glide.R;
import com.wiggins.glide.utils.UIUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

public class TransformAdapter extends BaseAdapter {

    private Context mContext;
    private List<TransformType> mDataSet;
    private LayoutInflater inflater;

    public enum TransformType {
        Mask,
        NinePatchMask,
        CropTop,
        CropCenter,
        CropBottom,
        CropSquare,
        CropCircle,
        ColorFilter,
        Grayscale,
        RoundedCorners,
        Blur,
        Toon,
        Sepia,
        Contrast,
        Invert,
        Pixel,
        Sketch,
        Swirl,
        Brightness,
        Kuawahara,
        Vignette
    }

    public TransformAdapter(Context context, List<TransformType> dataSet) {
        this.mContext = context;
        this.mDataSet = dataSet;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_list_item, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.title = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.clear(holder);
        switch (mDataSet.get(position)) {
            case Mask: {// 遮罩掩饰（视图叠加处理）
                // 使用构造方法 MaskTransformation(Context context, int maskId)
                // maskId ：遮罩物文件ID
                int width = UIUtils.dip2px(mContext, 133.33f);
                int height = UIUtils.dip2px(mContext, 126.33f);
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(mContext),
                                new MaskTransformation(mContext, R.drawable.mask_starfish))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 遮罩掩饰之视图叠加处理");
                break;
            }
            case NinePatchMask: {// 遮罩掩饰（视图叠加处理）
                // 使用构造方法 MaskTransformation(Context context, int maskId)
                // maskId ：遮罩物文件ID
                int width = UIUtils.dip2px(mContext, 150.0f);
                int height = UIUtils.dip2px(mContext, 100.0f);
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(mContext),
                                new MaskTransformation(mContext, R.drawable.mask_chat_right))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 遮罩掩饰之视图叠加处理");
                break;
            }
            case CropTop:// 图片裁剪 - 自定义矩形剪裁-顶部裁剪
                // 使用构造方法 CropTransformation(Context context, int width, int height, CropType cropType)
                // width : 剪裁宽度
                // height : 剪裁高度
                // cropType : 剪裁类型（指定剪裁位置，可以选择上、中、下其中一种）
                // 如果使用CropTransformation一个参数的构造方法：只填入一个Context，后续会使用图片原本的宽高进行剪裁，这实际上和没有剪裁是一样的。
                Glide.with(mContext)
                        .load(R.drawable.image_example)
                        .bitmapTransform(
                                new CropTransformation(mContext, UIUtils.dip2px(mContext, 300.0f), UIUtils.dip2px(mContext, 100.0f), CropTransformation.CropType.TOP))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 图片裁剪之自定义矩形顶部裁剪");
                break;
            case CropCenter:// 图片裁剪 - 自定义矩形剪裁-中部裁剪
                Glide.with(mContext)
                        .load(R.drawable.image_example)
                        .bitmapTransform(new CropTransformation(mContext, UIUtils.dip2px(mContext, 300.0f), UIUtils.dip2px(mContext, 100.0f)))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 图片裁剪之自定义矩形中部裁剪");
                break;
            case CropBottom:// 图片裁剪 - 自定义矩形剪裁-底部裁剪
                Glide.with(mContext)
                        .load(R.drawable.image_example)
                        .bitmapTransform(
                                new CropTransformation(mContext, UIUtils.dip2px(mContext, 300.0f), UIUtils.dip2px(mContext, 100.0f), CropTransformation.CropType.BOTTOM))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 图片裁剪之自定义矩形底部裁剪");
                break;
            case CropSquare:// 图片裁剪 - 正方形裁剪
                // 使用构造方法 CropSquareTransformation(Context context)
                Glide.with(mContext)
                        .load(R.drawable.image_example)
                        .bitmapTransform(new CropSquareTransformation(mContext))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 图片裁剪之正方形裁剪");
                break;
            case CropCircle:// 图片裁剪 - 圆形裁剪
                // 使用构造方法 CropCircleTransformation(Context context)
                Glide.with(mContext)
                        .load(R.drawable.image_example)
                        .bitmapTransform(new CropCircleTransformation(mContext))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 图片裁剪之圆形裁剪");
                break;
            case ColorFilter:// 颜色转换 - 颜色滤镜
                // 使用构造方法 ColorFilterTransformation(Context context, int color)
                // Color ：蒙层颜色值
                Glide.with(mContext)
                        .load(R.drawable.image_example)
                        .bitmapTransform(new ColorFilterTransformation(mContext, Color.argb(80, 255, 0, 0)))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 颜色转换之颜色滤镜");
                break;
            case Grayscale:// 颜色转换 - 灰度级转换
                // 使用构造方法 GrayscaleTransformation(Context context)
                Glide.with(mContext)
                        .load(R.drawable.image_example)
                        .bitmapTransform(new GrayscaleTransformation(mContext))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 颜色转换之灰度级转换");
                break;
            case RoundedCorners:// 图片裁剪- 圆角裁剪
                // 使用构造方法 RoundedCornersTransformation(Context context, int radius, int margin, CornerType cornerType)
                // radius ：圆角半径
                // margin ：填充边界
                // cornerType ：边角类型（可以指定4个角中的哪几个角是圆角，哪几个不是）
                Glide.with(mContext)
                        .load(R.drawable.image_example)
                        .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0, RoundedCornersTransformation.CornerType.BOTTOM))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 图片裁剪之圆角裁剪");
                break;
            case Blur:// 模糊处理 - 毛玻璃
                // 模糊处理是做过兼容的，当API>=18时使用RenderScript，API<18时使用FastBlur。
                // radius:离散半径/模糊度（单参构造器 - 默认25）
                // sampling:取样（单参构造器 - 默认1） 如果取2，横向、纵向都会每两个像素点取一个像素点(即:图片宽高变为原来一半)
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .bitmapTransform(new BlurTransformation(mContext, 25))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 模糊处理之毛玻璃");
                break;
            case Toon:// GPU过滤（需要依赖GPUImage库）
                // 卡通滤波器
                // 使用构造方法 ToonFilterTransformation(Context context, float threshold, float quantizationLevels)
                // threshold ：阀值（单参构造器 - 默认0.2F）影响色块边界的描边效果
                // quantizationLevels ：量化等级（单参构造器 - 默认10.0F）影响色块色彩
                Glide.with(mContext)
                        .load(R.drawable.image_example)
                        .bitmapTransform(new ToonFilterTransformation(mContext))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 卡通滤波器");
                break;
            case Sepia:// GPU过滤（需要依赖GPUImage库）
                // 乌墨色滤波器
                // 使用构造方法 SepiaFilterTransformation(Context context, float intensity)
                // intensity 渲染强度（单参构造器 - 默认1.0F）
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .bitmapTransform(new SepiaFilterTransformation(mContext))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 乌墨色滤波器");
                break;
            case Contrast:// GPU过滤（需要依赖GPUImage库）
                // 对比度滤波器
                // 使用构造方法 ContrastFilterTransformation(Context context, float contrast)
                // contrast 对比度 （单参构造器 - 默认1.0F）
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .bitmapTransform(new ContrastFilterTransformation(mContext, 2.0f))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 对比度滤波器");
                break;
            case Invert:// GPU过滤（需要依赖GPUImage库）
                // 反转滤波器
                // 使用构造方法 InvertFilterTransformation(Context context)
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .bitmapTransform(new InvertFilterTransformation(mContext))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 反转滤波器");
                break;
            case Pixel:// GPU过滤（需要依赖GPUImage库）
                // 像素化滤波器
                // 使用构造方法 PixelationFilterTransformation(Context context, float pixel)
                // pixel 像素值（单参构造器 - 默认10F）数值越大，绘制出的像素点越大，图像越失真
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .bitmapTransform(new PixelationFilterTransformation(mContext, 20))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 像素化滤波器");
                break;
            case Sketch:// GPU过滤（需要依赖GPUImage库）
                // 素描滤波器
                // 使用构造方法 SketchFilterTransformation(Context context)
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .bitmapTransform(new SketchFilterTransformation(mContext))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 素描滤波器");
                break;
            case Swirl:// GPU过滤（需要依赖GPUImage库）
                // 旋转滤波器
                // 使用构造方法 SwirlFilterTransformation(Context context, float radius, float angle, PointF center)
                // radius 旋转半径[0.0F，1.0F] （单参构造器 - 默认0.5F）
                // angle 角度[0.0F,无穷大）（单参构造器 - 默认1.0F）视图表现为旋转圈数
                // center 旋转中心点 （单参构造器 - 默认new PointF(0.5F,0.5F)）
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .bitmapTransform(
                                new SwirlFilterTransformation(mContext, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 旋转滤波器");
                break;
            case Brightness:// GPU过滤（需要依赖GPUImage库）
                // 亮度滤波器
                // 使用构造方法 BrightnessFilterTransformation(Context context, float brightness)
                // brightness 光亮强度[-1F,1F]（单参构造器 - 默认0.0F）小于-1F纯黑色,大于1F纯白色
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .bitmapTransform(new BrightnessFilterTransformation(mContext, 0.5f))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 亮度滤波器");
                break;
            case Kuawahara:// GPU过滤（需要依赖GPUImage库）
                // Kuwahara滤波器
                // 使用构造方法 KuwaharaFilterTransformation(Context context, int radius)
                // radius 半径 （单参构造器 - 默认25）
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .bitmapTransform(new KuwaharaFilterTransformation(mContext, 25))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - Kuwahara滤波器");
                break;
            case Vignette:// GPU过滤（需要依赖GPUImage库）
                // 装饰图滤波器
                // 使用构造方法 VignetteFilterTransformation(Context context, PointF center, float[] color, float start, float end)
                // center 装饰中心 （单参构造器 - 默认new PointF(0.5F, 0.5F)）
                // color 颜色组合 （单参构造器 - 默认new float[0.0F,0.0F,0.0F]） 3个颜色值分别对应RGB3种颜色，取值范围都为[0.0F,1.0F]
                // start 起始点 （单参构造器 - 默认0.0F）
                // end 终止点 （单参构造器 - 默认0.75F）
                Glide.with(mContext)
                        .load(R.drawable.image_check)
                        .bitmapTransform(new VignetteFilterTransformation(mContext, new PointF(0.5f, 0.5f),
                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f))
                        .into(holder.image);
                holder.title.setText(mDataSet.get(position).name() + " - 装饰图滤波器");
                break;
        }
        return convertView;
    }

    private class ViewHolder {
        private ImageView image;
        private TextView title;

        public void clear(ViewHolder holder) {
            holder.title.setText("");
        }
    }
}
