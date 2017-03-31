package com.wiggins.glide.base;

import android.app.Activity;
import android.os.Bundle;

import com.wiggins.glide.app.GlideApplication;

/**
 * @Description 所有Activity的基类
 * @Author 一花一世界
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlideApplication.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlideApplication.finishActivity(this);
    }
}
