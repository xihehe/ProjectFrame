package com.yumeng.libcommon.adapter

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Chauncey on 2017/10/28 11:12.
 */

abstract class DiffCallBack<in T> : DiffUtil.Callback() {

    private var mOldData: List<T>? = null
    private var mNewData: List<T>? = null

    fun setNewData(newData: List<T>) {
        mOldData = mNewData
        mNewData = newData
    }

    override fun getOldListSize(): Int {
        return mOldData?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return mNewData?.size ?: 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsTheSame(mOldData?.get(oldItemPosition), mNewData?.get(newItemPosition))
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentsTheSame(mOldData?.get(oldItemPosition), mNewData?.get(newItemPosition))
    }

    protected abstract fun areItemsTheSame(oldItem: T?, newItem: T?): Boolean

    protected abstract fun areContentsTheSame(oldItem: T?, newItem: T?): Boolean
}
