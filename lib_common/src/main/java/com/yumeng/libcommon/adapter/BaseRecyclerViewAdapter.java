package com.yumeng.libcommon.adapter;


import android.view.View;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chauncey on 2018/2/1 11:48.
 */
public abstract class BaseRecyclerViewAdapter<T, VH extends BaseRecyclerViewAdapter.BaseViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> mData = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    protected OnItemChildClickListener mOnItemChildClickListener;
//    private List<View> mFooterViewList;
//
//    protected static final int ITEM = 0;
//    protected static final int FOOTER = 1;

    private DiffCallBack<T> mDiffCallBack = new DiffCallBack<T>() {
        @Override
        protected boolean areItemsTheSame(T oldItem, T newItem) {
            return BaseRecyclerViewAdapter.this.areItemsTheSame(oldItem, newItem);
        }

        @Override
        protected boolean areContentsTheSame(T oldItem, T newItem) {
            return BaseRecyclerViewAdapter.this.areContentsTheSame(oldItem, newItem);
        }
    };

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void addData(T t) {
        addData(t, mData.size() - 1);
    }

    public void addData(T t, int position) {
        if (t == null) {
            return;
        }
        mData.add(position, t);
        notifyItemInserted(position);
    }

    public void addData(List<T> data) {
        if (data == null) {
            return;
        }

        mData.addAll(data);
        notifyItemRangeInserted(mData.size() - data.size(), data.size());
    }

    public void changeData(T t, int position) {
        mData.set(position, t);
        notifyItemChanged(position);
    }

    /**
     * 根据条目移除数据
     */
    public void removeItem(T model) {
        removeData(mData.indexOf(model));
    }

    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        if (data == null) {
            data = new ArrayList<>();
        } else {
            data = new ArrayList<>(data);
        }
        mDiffCallBack.setNewData(data);
        DiffUtil.calculateDiff(mDiffCallBack).dispatchUpdatesTo(this);
        mData = data;
    }

    @Override
    public int getItemCount() {
        return (mData == null ? 0 : mData.size()) /*+ getFooterViewList().size()*/;
    }

//    @Override
//    public int getItemViewType(int position) {
//        return mData.size() - 1 < position ? ITEM : FOOTER;
//    }

//    public void addFooterView(View view) {
//        getFooterViewList().add(view);
//        notifyItemInserted(mData.size() + getFooterViewList().size());
//    }
//
//    @NonNull
//    @Override
//    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }

//    private List<View> getFooterViewList() {
//        if (mFooterViewList == null) {
//            mFooterViewList = new ArrayList<>();
//        }
//        return mFooterViewList;
//    }

    private void onItemClicked(View view, int position) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onClicked(view, position);
        }
    }

    private void onItemLongClicked(View view, int position) {
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onLongClicked(view, position);
        }
    }

    public void setOnItemChildClickListener(OnItemChildClickListener mOnItemChildClickListener) {
        this.mOnItemChildClickListener = mOnItemChildClickListener;
    }

    protected abstract boolean areItemsTheSame(T oldItem, T newItem);

    protected abstract boolean areContentsTheSame(T oldItem, T newItem);

    public interface OnItemClickListener {
        void onClicked(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onLongClicked(View view, int position);
    }

    public interface OnItemChildClickListener {
        void onItemChildClick(View view, int position);
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int adapterPosition = getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        onItemClicked(v, adapterPosition);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int adapterPosition = getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        onItemLongClicked(v, adapterPosition);
                    }
                    return true;
                }
            });
        }
    }
}
