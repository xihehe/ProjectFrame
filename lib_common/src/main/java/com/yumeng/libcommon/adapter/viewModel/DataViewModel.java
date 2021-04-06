package com.yumeng.libcommon.adapter.viewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

/**
 * Created by Chauncey on 2018/12/10 14:56.
 * 自定义数据参数管理Model
 */
public class DataViewModel<T> extends ViewModel {

    public DataViewModel() {
        mMutableLiveData = new MutableLiveData<>();
    }

    public DataViewModel(T t) {
        mMutableLiveData = new MutableLiveData<>();
        mMutableLiveData.setValue(t);
    }

    private MutableLiveData<T> mMutableLiveData;

    public void setValue(@NonNull T t) {
        mMutableLiveData.setValue(t);
    }

    public void postValue(T t) {
        mMutableLiveData.postValue(t);
    }

    public T getValue() {
        return mMutableLiveData.getValue();
    }

    @Nullable
    public T getValueOrNull() {
        return mMutableLiveData.getValue();
    }

    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        mMutableLiveData.observe(owner, observer);
    }
}
