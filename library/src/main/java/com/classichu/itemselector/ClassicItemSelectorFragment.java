package com.classichu.itemselector;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.classichu.adapter.recyclerview.ClassicRVHeaderFooterAdapter;
import com.classichu.classichu.classic.ClassicFragment;
import com.classichu.itemselector.adapter.ItemSelectRVHeaderFooterAdapter;
import com.classichu.itemselector.bean.ItemSelectBean;
import com.classichu.itemselector.bean.ItemSelectDataWrapper;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassicItemSelectorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassicItemSelectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassicItemSelectorFragment extends ClassicFragment {
    private OnFragmentInteractionListener mInteractionListener;
    private static final String ARG_PARAM4 = "param4";
    private Serializable mParam4;
    private ItemSelectDataWrapper mItemSelectDataWrapper;

    public ClassicItemSelectorFragment() {
        // Required empty public constructor
    }

    private List<ItemSelectBean> mItemSelectBeanList = new ArrayList<>();
    private int mSelectItemCount;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClassicItemSelectorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassicItemSelectorFragment newInstance(String param1, String param2, int param3, Serializable serializable) {
        ClassicItemSelectorFragment fragment = new ClassicItemSelectorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3, param3);
        args.putSerializable(ARG_PARAM4, serializable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
            mParam4 = getArguments().getSerializable(ARG_PARAM4);
        }
        mSelectItemCount = mParam3;
        mItemSelectDataWrapper = (ItemSelectDataWrapper) mParam4;
    }


    @Override
    protected int setupLayoutResId() {
        return R.layout.fragment_classic_item_selector;
    }

    @Override
    protected void initView(View view) {
        if (mItemSelectDataWrapper != null) {
            mClassicRVHeaderFooterAdapter.refreshDataList(mItemSelectDataWrapper.getItemSelectBeanList());
        }
    }

    @Override
    protected void initListener() {

    }

    public void onListItemSelected() {
        if (mInteractionListener != null) {
            ItemSelectDataWrapper itemSelectDataWrapper = new ItemSelectDataWrapper();
            itemSelectDataWrapper.setItemSelectBeanList(mItemSelectBeanList);
            mInteractionListener.onFragmentInteraction(itemSelectDataWrapper);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mInteractionListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(ItemSelectDataWrapper itemSelectDataWrapper);
    }

    @Override
    protected int configRecyclerViewResId() {
        return R.id.id_recycler_view;
    }

    @Override
    protected ClassicRVHeaderFooterAdapter configClassicRVHeaderFooterAdapter() {
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
        ItemSelectRVHeaderFooterAdapter adapter = new ItemSelectRVHeaderFooterAdapter(getActivity(), mItemSelectBeanList, R.layout.item_list_selector);
        adapter.setOnItemClickListener(new ClassicRVHeaderFooterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                super.onItemClick(itemView, position);
                ItemSelectBean itemSelectBean = mItemSelectBeanList.get(position);

                if (mSelectItemCount > 1) {
                    //多选状态下
                    if (getNowSelectItemCount() >= mSelectItemCount) {
                        if (!itemSelectBean.isSelected()) {//超过数量选择 未选的  直接返回
                            Toast.makeText(mContext, "最多只能选择" + mSelectItemCount + "个", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    //设置当前选中
                    mItemSelectBeanList.get(position).setSelected(!itemSelectBean.isSelected());
                    //再次判断
                    if (getNowSelectItemCount() < mSelectItemCount) {
                        //继续选择
                        mClassicRVHeaderFooterAdapter.notifyDataSetChanged();
                    } else {
                        //结束选择
                        onListItemSelected();
                    }

                } else {
                    //单选
                    // 清除原有所有
                    resetAllSelectedItem();
                    //设置当前选中
                    mItemSelectBeanList.get(position).setSelected(!itemSelectBean.isSelected());
                    //结束选择
                    onListItemSelected();
                }

            }
        });
        return adapter;
    }

    public void resetAllSelectedItem() {
        for (ItemSelectBean isb : mItemSelectBeanList
                ) {
            if (isb.isSelected()) {
                isb.setSelected(false);
            }
        }
    }

    public int getNowSelectItemCount() {
        int nowSelectedCount = 0;
        for (ItemSelectBean isb : mItemSelectBeanList
                ) {
            if (isb.isSelected()) {
                nowSelectedCount++;
            }
        }
        return nowSelectedCount;
    }

    @Override
    protected int configSwipeRefreshLayoutResId() {
        return R.id.id_swipe_refresh_layout;
    }
}
