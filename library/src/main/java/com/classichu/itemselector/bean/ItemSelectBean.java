package com.classichu.itemselector.bean;

import java.io.Serializable;

/**
 * Created by louisgeek on 2016/12/10.
 */

public class ItemSelectBean implements Serializable {
    private int itemid;
    private String itemTitle;

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    private String itemKey;
    private boolean isSelected;

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
