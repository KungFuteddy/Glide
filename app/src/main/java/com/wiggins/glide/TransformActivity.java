package com.wiggins.glide;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.wiggins.glide.adapter.TransformAdapter;
import com.wiggins.glide.adapter.TransformAdapter.TransformType;
import com.wiggins.glide.base.BaseActivity;
import com.wiggins.glide.utils.UIUtils;
import com.wiggins.glide.widget.TitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description Transform
 * @Author 一花一世界
 */

public class TransformActivity extends BaseActivity {

    private TransformActivity mActivity = null;
    private TitleView titleView;
    private ListView lv_list;
    private List<TransformType> dataSet;
    private TransformAdapter transformAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);
        mActivity = this;

        initView();
        initData();
        setListener();
    }

    private void initData() {
        if (dataSet == null) {
            dataSet = new ArrayList<>();
        }
        dataSet.add(TransformType.Mask);
        dataSet.add(TransformType.NinePatchMask);
        dataSet.add(TransformType.CropTop);
        dataSet.add(TransformType.CropCenter);
        dataSet.add(TransformType.CropBottom);
        dataSet.add(TransformType.CropSquare);
        dataSet.add(TransformType.CropCircle);
        dataSet.add(TransformType.ColorFilter);
        dataSet.add(TransformType.Grayscale);
        dataSet.add(TransformType.RoundedCorners);
        dataSet.add(TransformType.Blur);
        dataSet.add(TransformType.Toon);
        dataSet.add(TransformType.Sepia);
        dataSet.add(TransformType.Contrast);
        dataSet.add(TransformType.Invert);
        dataSet.add(TransformType.Pixel);
        dataSet.add(TransformType.Sketch);
        dataSet.add(TransformType.Swirl);
        dataSet.add(TransformType.Brightness);
        dataSet.add(TransformType.Kuawahara);
        dataSet.add(TransformType.Vignette);
        if (transformAdapter == null) {
            transformAdapter = new TransformAdapter(mActivity, dataSet);
            lv_list.setAdapter(transformAdapter);
        } else {
            transformAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        titleView = (TitleView) findViewById(R.id.titleView);
        titleView.setAppTitle(UIUtils.getString(R.string.title));
        titleView.setLeftImgOnClickListener();
        lv_list = (ListView) findViewById(R.id.lv_list);
    }

    private void setListener() {
        // ListView滑动时触发的事件
        lv_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        // 当ListView处于滑动状态时，停止加载图片，保证操作界面流畅
                        Glide.with(mActivity).pauseRequests();
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 当ListView处于静止状态时，继续加载图片
                        Glide.with(mActivity).resumeRequests();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }
}
