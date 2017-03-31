package com.wiggins.glide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wiggins.glide.R;
import com.wiggins.glide.bean.DialogList;

import java.util.List;

/**
 * @Description 底部Dialog适配器
 * @Author 一花一世界
 */
public class DialogBottomListAdapter extends BaseAdapter {

    private static final int TYPE_COUNT = 5;//item类型的总数
    private static final int TYPE_ONLY_ONE = 0;//只有一项
    private static final int TYPE_TOP = 1;//第一项
    private static final int TYPE_CENTER = 2;//中间项
    private static final int TYPE_FINALLY = 3;//最后一项
    private static final int TYPE_BOTTOM = 4;//底部项

    private int currentType;//当前item类型

    private List<DialogList> data;
    private LayoutInflater inflater;

    public DialogBottomListAdapter(Context context, List<DialogList> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if ("0".equals(data.get(position).getType())) {
            return TYPE_ONLY_ONE;// 只有一项
        } else if ("1".equals(data.get(position).getType())) {
            return TYPE_TOP;// 第一项
        } else if ("2".equals(data.get(position).getType())) {
            return TYPE_CENTER;// 中间项
        } else if ("3".equals(data.get(position).getType())) {
            return TYPE_FINALLY;// 最后一项
        } else {
            return TYPE_BOTTOM;// 底部项
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        currentType = getItemViewType(position);
        if (currentType == TYPE_ONLY_ONE) {
            OnlyOneViewHolder onlyOneViewHolder = null;
            if (convertView == null) {
                onlyOneViewHolder = new OnlyOneViewHolder();
                convertView = inflater.inflate(R.layout.item_dialog_only_one_list, null);
                onlyOneViewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);

                convertView.setTag(onlyOneViewHolder);
            } else {
                onlyOneViewHolder = (OnlyOneViewHolder) convertView.getTag();
            }

            DialogList itemBean = data.get(position);
            onlyOneViewHolder.tv_name.setText(itemBean.getName());
        } else if (currentType == TYPE_TOP) {
            TopViewHolder topViewHolder = null;
            if (convertView == null) {
                topViewHolder = new TopViewHolder();
                convertView = inflater.inflate(R.layout.item_dialog_top_list, null);
                topViewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);

                convertView.setTag(topViewHolder);
            } else {
                topViewHolder = (TopViewHolder) convertView.getTag();
            }

            DialogList itemBean = data.get(position);
            topViewHolder.tv_name.setText(itemBean.getName());
        } else if (currentType == TYPE_CENTER) {
            CenterViewHolder centerViewHolder = null;
            if (convertView == null) {
                centerViewHolder = new CenterViewHolder();
                convertView = inflater.inflate(R.layout.item_dialog_center_list, null);
                centerViewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);

                convertView.setTag(centerViewHolder);
            } else {
                centerViewHolder = (CenterViewHolder) convertView.getTag();
            }

            DialogList itemBean = data.get(position);
            centerViewHolder.tv_name.setText(itemBean.getName());
        } else if (currentType == TYPE_FINALLY) {
            FinallyViewHolder finallyViewHolder = null;
            if (convertView == null) {
                finallyViewHolder = new FinallyViewHolder();
                convertView = inflater.inflate(R.layout.item_dialog_finally_list, null);
                finallyViewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);

                convertView.setTag(finallyViewHolder);
            } else {
                finallyViewHolder = (FinallyViewHolder) convertView.getTag();
            }

            DialogList itemBean = data.get(position);
            finallyViewHolder.tv_name.setText(itemBean.getName());
        } else if (currentType == TYPE_BOTTOM) {
            BottomViewHolder bottomViewHolder = null;
            if (convertView == null) {
                bottomViewHolder = new BottomViewHolder();
                convertView = inflater.inflate(R.layout.item_dialog_bottom_list, null);
                bottomViewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);

                convertView.setTag(bottomViewHolder);
            } else {
                bottomViewHolder = (BottomViewHolder) convertView.getTag();
            }

            DialogList itemBean = data.get(position);
            bottomViewHolder.tv_name.setText(itemBean.getName());
        }
        return convertView;
    }

    /**
     * @Description 只有一项
     */
    private class OnlyOneViewHolder {
        public TextView tv_name;
    }

    /**
     * @Description 第一项
     */
    private class TopViewHolder {
        public TextView tv_name;
    }

    /**
     * @Description 中间项
     */
    private class CenterViewHolder {
        public TextView tv_name;
    }

    /**
     * @Description 最后一项
     */
    private class FinallyViewHolder {
        public TextView tv_name;
    }

    /**
     * @Description 底部项
     */
    private class BottomViewHolder {
        public TextView tv_name;
    }
}
