package com.yumeng.libcommonview.view.index;

import com.yumeng.libcommonview.view.index.listener.IndexableEntity;

import java.util.Comparator;


/**
 * Created by YoKey on 16/10/14.
 */
class InitialComparator<T extends IndexableEntity> implements Comparator<EntityWrapper<T>> {
    @Override
    public int compare(EntityWrapper<T> lhs, EntityWrapper<T> rhs) {
        return lhs.getIndex().compareTo(rhs.getIndex());
    }
}
