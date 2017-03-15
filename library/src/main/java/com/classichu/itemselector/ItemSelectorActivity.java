package com.classichu.itemselector;

import android.content.Intent;

import com.classichu.classichu.classic.ClassicActivity;
import com.classichu.itemselector.bean.ItemSelectDataWrapper;

public class ItemSelectorActivity extends ClassicActivity
        implements ItemSelectorFragment.OnFragmentInteractionListener {

    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_item_selector;
    }

    int mSelectItemCount;
    ItemSelectDataWrapper mItemSelectDataWrapper;

    @Override
    protected void initView() {

        mSelectItemCount = getBundleExtraInt1();

        mItemSelectDataWrapper = (ItemSelectDataWrapper) getBundleExtra().getSerializable("bundleExtraKey2");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_frame_layout_content, ItemSelectorFragment.newInstance("", "", mSelectItemCount, mItemSelectDataWrapper))
                .commitAllowingStateLoss();

    }

  /*  @Override
    protected int configMenuResId() {
        //1个时候 不需要按钮
        return mSelectItemCount > 1 ? R.menu.menu_finish : super.configMenuResId();
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.id_menu_item_ok) {
           *//* if (!mIsMultiSelect) {
                item.setVisible(false);
            }*//*
           //### finishSelectOperator();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void finishSelectOperator() {
        Intent intent = new Intent();
        intent.putExtra("itemSelectDataWrapper", mItemSelectDataWrapper);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected AppBarStyle configAppBarStyle() {
        return AppBarStyle.ClassicTitleBar;
    }


    @Override
    public void onFragmentInteraction(ItemSelectDataWrapper itemSelectDataWrapper) {
        mItemSelectDataWrapper = itemSelectDataWrapper;
        finishSelectOperator();
    }
}
