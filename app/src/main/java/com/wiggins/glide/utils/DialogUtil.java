package com.wiggins.glide.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wiggins.glide.R;
import com.wiggins.glide.adapter.DialogBottomListAdapter;
import com.wiggins.glide.bean.DialogList;
import com.wiggins.glide.listener.OnDataListPositionListener;
import com.wiggins.glide.widget.loading.ShapeLoadingDialog;

import java.util.List;

/**
 * @Description Dialog工具类
 * @Author 一花一世界
 */
public class DialogUtil {

    private static ShapeLoadingDialog shapeLoadingDialog;

    /**
     * @Description 显示加载状态框
     */
    public static void showDialogLoading(Context context, String message) {
        shapeLoadingDialog = new ShapeLoadingDialog(context);
        if (!StringUtil.isEmpty(message)) {
            shapeLoadingDialog.setLoadingText(message);
        } else {
            shapeLoadingDialog.setLoadingText(UIUtils.getString(R.string.loading));
        }
        shapeLoadingDialog.setCanceledOnTouchOutside(false);
        shapeLoadingDialog.show();
    }

    /**
     * @Description 关闭加载框
     */
    public static void hideDialogLoading() {
        if (shapeLoadingDialog != null) {
            if (shapeLoadingDialog.isShowing()) {
                shapeLoadingDialog.dismiss();
            }
            shapeLoadingDialog = null;
        }
    }

    /**
     * @param context    上下文
     * @param dialogList 数据集合
     * @param listener   按键监听
     * @Description 底部List列表Dialog
     */
    public static void showBottomListSelection(Context context, List<DialogList> dialogList, final OnDataListPositionListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.theme_dialog);
        View view = UIUtils.inflate(R.layout.dialog_bottom_list_selection);
        ListView mClvList = (ListView) view.findViewById(R.id.lv_list);

        final AlertDialog dialog = builder.create();
        DialogBottomListAdapter dialogBottomList = null;
        if (dialogBottomList == null) {
            dialogBottomList = new DialogBottomListAdapter(context, dialogList);
            mClvList.setAdapter(dialogBottomList);
        }
        mClvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (listener != null) {
                    listener.onSelectItem(position);
                }
            }
        });

        dialog.getWindow().setWindowAnimations(R.style.dialogListWindowAnim);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

        dialog.setContentView(view);
    }
}
