package com.classichu.itemselector.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.classichu.adapter.recyclerview.ClassicRVHeaderFooterAdapter;
import com.classichu.adapter.recyclerview.ClassicRVHeaderFooterViewHolder;
import com.classichu.itemselector.R;
import com.classichu.itemselector.bean.ItemSelectBean;

import java.util.List;


/**
 * Created by louisgeek on 2016/12/7.
 */

public class ItemSelectRVHeaderFooterAdapter extends ClassicRVHeaderFooterAdapter<ItemSelectBean> {


    public ItemSelectRVHeaderFooterAdapter(Context mContext, List<ItemSelectBean> mDataList, int mItemLayoutId) {
        super(mContext, mDataList, mItemLayoutId);
    }

    @Override
    public RVHeaderFooterAdapterDelegate setupDelegate() {
        return new RVHeaderFooterAdapterDelegate() {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public int getItemViewType(int pos) {
                return 0;
            }
        };
    }

    @Override
    public void findBindView(int pos, ClassicRVHeaderFooterViewHolder classicRVHeaderFooterViewHolder) {
        if (mDataList!=null&&mDataList.size()>0){
        TextView id_tv_item_title = classicRVHeaderFooterViewHolder.findBindView(R.id.id_tv_item_title);
        id_tv_item_title.setText(mDataList.get(pos).getItemTitle());

        ImageView id_tv_item_image = classicRVHeaderFooterViewHolder.findBindView(R.id.id_tv_item_image);
        id_tv_item_image.setSelected(mDataList.get(pos).isSelected());
    }}
}
