package com.classichu.itemselector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.classichu.itemselector.bean.ItemSelectBean;
import com.classichu.itemselector.bean.ItemSelectDataWrapper;

import java.util.List;

/**
 * Created by louisgeek on 2017/3/15.
 */

public class ClassicItemSelectorDataHelper {
    private static View mClickView;
    public static final int ITEM_SELECT_REQUEST_CODE = 1214;

    public static void setDataAndToItemSelect(String showTitle, View clickView, FragmentActivity fragmentActivity, List<ItemSelectBean> imageShowBeanList) {
        setDataAndToItemSelect(showTitle, clickView, fragmentActivity, imageShowBeanList, 1);
    }


    public static void setDataAndToItemSelect(String showTitle, View clickView, FragmentActivity fragmentActivity,
                                              List<ItemSelectBean> imageShowBeanList, int maxCount) {
        mClickView = clickView;
        //
        ItemSelectDataWrapper itemSelectDataWrapper = null;
        if (mClickView != null) {
            itemSelectDataWrapper = (ItemSelectDataWrapper) mClickView.getTag(R.id.hold_item_selected_data);
        }
        if (itemSelectDataWrapper == null) {
            itemSelectDataWrapper = new ItemSelectDataWrapper(imageShowBeanList);
        }

        Intent intent = new Intent(fragmentActivity, ClassicItemSelectorActivity.class);
        //
        Bundle bundle = new Bundle();
        bundle.putInt("bundleExtraKey1", maxCount);
        bundle.putSerializable("bundleExtraKey2", itemSelectDataWrapper);
        bundle.putSerializable("bundleExtraKey3", showTitle);
        intent.putExtras(bundle);
        //
        fragmentActivity.startActivityForResult(intent, ITEM_SELECT_REQUEST_CODE);
    }

    public static void callAtOnActivityResult(int requestCode, int resultCode, Intent data, ItemSelectBackData itemSelectBackData) {
        ItemSelectDataWrapper itemSelectDataWrapper;
        if (resultCode == Activity.RESULT_OK && requestCode == ITEM_SELECT_REQUEST_CODE) {
            itemSelectDataWrapper = (ItemSelectDataWrapper) data.getSerializableExtra("itemSelectDataWrapper");
            if (itemSelectBackData != null && itemSelectDataWrapper != null) {
                mClickView.setTag(R.id.hold_item_selected_data, itemSelectDataWrapper);
                itemSelectBackData.backData(mClickView, itemSelectDataWrapper.getItemSelectBeanList());
            }

        }
    }

    public interface ItemSelectBackData {
        void backData(View clickView, List<ItemSelectBean> itemSelectBeanList);
    }
}
