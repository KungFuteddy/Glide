package com.wiggins.glide;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.cache.DiskCache;
import com.wiggins.glide.app.GlideApplication;
import com.wiggins.glide.base.BaseActivity;
import com.wiggins.glide.bean.DialogList;
import com.wiggins.glide.listener.OnDataListPositionListener;
import com.wiggins.glide.listener.OnLoadCallback;
import com.wiggins.glide.utils.Constant;
import com.wiggins.glide.utils.DialogUtil;
import com.wiggins.glide.utils.GlideUtil;
import com.wiggins.glide.utils.ToastUtil;
import com.wiggins.glide.utils.UIUtils;
import com.wiggins.glide.widget.TitleView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {

    private MainActivity mActivity = null;
    private TitleView titleView;
    private TextView tv_cache;
    private ImageView iv_image;
    private List<DialogList> selectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;

        initData();
        initView();
        setListener();
        getCacheSize();
    }

    private void initData() {
        if (selectList == null) {
            selectList = new ArrayList<>();
        }
        selectList.add(new DialogList(UIUtils.getString(R.string.load_static), "1"));
        selectList.add(new DialogList(UIUtils.getString(R.string.load_dynamic), "2"));
        selectList.add(new DialogList(UIUtils.getString(R.string.image_processing), "2"));
        selectList.add(new DialogList(UIUtils.getString(R.string.clear_cache), "3"));
        selectList.add(new DialogList(UIUtils.getString(R.string.cancel), "4"));
    }

    private void initView() {
        titleView = (TitleView) findViewById(R.id.titleView);
        titleView.setAppTitle(UIUtils.getString(R.string.title));
        titleView.setLeftImageVisibility(View.GONE);
        titleView.setRightImageResource(R.drawable.option);
        tv_cache = (TextView) findViewById(R.id.tv_cache);
        iv_image = (ImageView) findViewById(R.id.iv_image);
    }

    private void setListener() {
        titleView.getRightImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDialog();
            }
        });
    }

    private void getCacheSize() {
        new GetDiskCacheSizeTask(tv_cache).execute(new File(getCacheDir(), DiskCache.Factory.DEFAULT_DISK_CACHE_DIR));
    }

    /**
     * @Description 获取缓存大小
     */
    private class GetDiskCacheSizeTask extends AsyncTask<File, Long, Long> {
        private final TextView resultView;

        public GetDiskCacheSizeTask(TextView resultView) {
            this.resultView = resultView;
            resultView.setText("");
        }

        @Override
        protected void onPreExecute() {
            resultView.setText("Calculating...");
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Long doInBackground(File... dirs) {
            try {
                long totalSize = 0;
                for (File dir : dirs) {
                    publishProgress(totalSize);
                    totalSize += calculateSize(dir);
                }
                return totalSize;
            } catch (RuntimeException ex) {
                final String message = String.format("Cannot get size of %s: %s", Arrays.toString(dirs), ex);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        resultView.setText("error");
                        DialogUtil.hideDialogLoading();
                        ToastUtil.showText(message);
                    }
                });
            }
            return 0L;
        }

        @Override
        protected void onPostExecute(Long size) {
            String sizeText = android.text.format.Formatter.formatFileSize(resultView.getContext(), size);
            resultView.setText(sizeText);
            DialogUtil.hideDialogLoading();
        }

        private long calculateSize(File dir) {
            if (dir == null) return 0;
            if (!dir.isDirectory()) return dir.length();
            long result = 0;
            File[] children = dir.listFiles();
            if (children != null)
                for (File child : children)
                    result += calculateSize(child);
            return result;
        }
    }

    private void selectDialog() {
        DialogUtil.showBottomListSelection(mActivity, selectList, new OnDataListPositionListener() {
            @Override
            public void onSelectItem(int position) {
                switch (position) {
                    case 0://静态图片
                        DialogUtil.showDialogLoading(mActivity, "");
                        GlideUtil.loadImage(mActivity, Constant.image_static, iv_image, new OnLoadCallback() {
                            @Override
                            public void onSuccess() {
                                getCacheSize();
                            }

                            @Override
                            public void onException() {
                                ToastUtil.showText(UIUtils.getString(R.string.loading_exception));
                            }
                        });
                        break;
                    case 1://动态图片
                        DialogUtil.showDialogLoading(mActivity, "");
                        GlideUtil.loadImage(mActivity, Constant.image_dynamic, iv_image, new OnLoadCallback() {
                            @Override
                            public void onSuccess() {
                                getCacheSize();
                            }

                            @Override
                            public void onException() {
                                ToastUtil.showText(UIUtils.getString(R.string.loading_exception));
                            }
                        });
                        break;
                    case 2://图片处理
                        startActivity(new Intent(mActivity, TransformActivity.class));
                        break;
                    case 3://清除缓存
                        mHandler.postDelayed(mRunnable, 3000);
                        DialogUtil.showDialogLoading(mActivity, "");
                        GlideUtil.clearCache(GlideApplication.getContext());
                        break;
                }
            }
        });
    }

    public Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            getCacheSize();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
    }
}
