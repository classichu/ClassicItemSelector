package com.classichu.itemselector.model;

import com.classichu.itemselector.bean.ItemSelectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by louisgeek on 2017/3/14.
 */

public class ItemSelectorModel {
    public List<ItemSelectBean> gainData(String key) {
        List<ItemSelectBean> itemSelectBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ItemSelectBean itemSelectBean = new ItemSelectBean();
            itemSelectBean.setItemid(i);
            itemSelectBean.setItemTitle(key + i);
          /*  if (i % 2 == 0) {
                itemSelectBean.setSelected(true);
            }*/
            itemSelectBeanList.add(itemSelectBean);
        }
       // itemSelectBeanList.clear();
        return itemSelectBeanList;
    }
}
