package com.yumeng.libcommon.adapter

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yumeng.libcommon.adapter.viewModel.DataViewModel

/**
 * Created by Chauncey on 2018/12/17 09:42.
 * 用于通用Recycler 的Adapter
 */
abstract class ViewModelRecyclerViewAdapter<T, VH : ViewModelRecyclerViewAdapter<T, VH>.ViewModelViewHolder>(
    protected val owner: LifecycleOwner? = null
) : RecyclerView.Adapter<VH>() {

    private var mViewModelData: MutableList<DataViewModel<T>> = ArrayList()

    private var mData: MutableList<T> = ArrayList()
        set(value) {
            mViewModelData = value.mapTo(ArrayList()) {
                DataViewModel(it)
            }
            field = value
        }

    var data: MutableList<T>
        get() = mData
        set(data) {
            val list = ArrayList(data)
            mDiffCallBack.setNewData(list)
            DiffUtil.calculateDiff(mDiffCallBack).dispatchUpdatesTo(this)
            mData = list
        }

    private val mDiffCallBack = object : DiffCallBack<T>() {
        override fun areItemsTheSame(oldItem: T?, newItem: T?): Boolean {
            return this@ViewModelRecyclerViewAdapter.areItemsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: T?, newItem: T?): Boolean {
            return this@ViewModelRecyclerViewAdapter.areContentsTheSame(oldItem, newItem)
        }
    }

    protected abstract fun areItemsTheSame(oldItem: T?, newItem: T?): Boolean

    protected abstract fun areContentsTheSame(oldItem: T?, newItem: T?): Boolean

    private var mObserver: Observer<T> = object : Observer<T> {
        override fun onChanged(view: View, t: T?) {
            onDataChanged(view, t)
        }
    }

    open protected fun onDataChanged(view: View, t: T?) {

    }

    var mOnItemClickListener: OnItemClickListener? = null
    var mOnItemLongClickListener: OnItemLongClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener
    }

    fun setOnItemClickListener(method: (view: View, position: Int) -> Unit) {
        mOnItemClickListener = object : OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                method.invoke(view, position)
            }
        }
    }

    fun setOnItemLongClickListener(method: (view: View, position: Int) -> Boolean) {
        mOnItemLongClickListener = object : OnItemLongClickListener {
            override fun onLongClick(view: View, position: Int): Boolean =
                method.invoke(view, position)
        }
    }

    open fun addData(t: T, position: Int = mData.size) {
        doAddData(t, position)
        notifyItemInserted(position)
    }

    protected fun doAddData(t: T, position: Int) {
        mData.add(position, t)
        mViewModelData.add(position, DataViewModel(t))
    }

    fun addAllData(data: MutableList<T>, position: Int = mData.size) {
        mData.addAll(position, data)
        mViewModelData.addAll(position, data.mapTo(ArrayList()) {
            DataViewModel(it)
        })
        notifyItemRangeInserted(position, data.size)
    }

    fun changeData(t: T, position: Int) {
        mData[position] = t
        mViewModelData[position].value = t
    }

    open fun removeDataAt(position: Int) {
        doRemoveDataAt(position)
        notifyItemRemoved(position)
    }

    protected fun doRemoveDataAt(position: Int) {
        mData.removeAt(position)
        mViewModelData.removeAt(position)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    open inner class ViewModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            //            owner?.let { lo ->
            //                layoutPosition.let { p ->
            //                    if (p != RecyclerView.NO_POSITION) {
            //                        mViewModelData[p].observe(lo, Observer { t ->
            //                            mObserver.onChanged(itemView, t)
            //                        })
            //                    }
            //                }
            //            }

            itemView.setOnClickListener {
                adapterPosition.let { position ->
                    if (position != RecyclerView.NO_POSITION) {
                        mOnItemClickListener?.onClick(it, position)
                    }
                }
            }

            itemView.setOnLongClickListener { v ->
                adapterPosition.let { position ->
                    if (position != RecyclerView.NO_POSITION) {
                        mOnItemLongClickListener?.let {
                            return@setOnLongClickListener it.onLongClick(v, position)
                        }
                    }
                }
                false
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onLongClick(view: View, position: Int): Boolean
    }

    interface Observer<T> {
        fun onChanged(view: View, t: T?)
    }
}
