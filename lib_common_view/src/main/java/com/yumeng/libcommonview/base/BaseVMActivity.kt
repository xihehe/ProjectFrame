package com.yumeng.libcommonview.base

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yumeng.libcommonview.activity.CommonToolbarActivity
import com.yumeng.libcommonview.viewmodel.BaseViewModel

abstract class BaseVMActivity<VM : BaseViewModel> : CommonToolbarActivity(), LifecycleObserver {

    lateinit var mViewModel: VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbarBase()
        initVM()
        startObserve()
        setContentView(getLayoutResId())
        initView()
        initData()
    }


    private fun initToolbarBase() {
        setTitleTextColor(ContextCompat.getColor(this, android.R.color.black))
        setNavigationBarColor(ContextCompat.getColor(this, android.R.color.black))
        if (isFullScreenAndBlackTextModeEnabled()) {
            setFullScreenAndBlackTextMode()
        }

    }

    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProvider(this).get(it)
            mViewModel.let(lifecycle::addObserver)
        }
    }

    protected open fun isFullScreenAndBlackTextModeEnabled(): Boolean = true


    open fun providerVMClass(): Class<VM>? = null


    abstract fun getLayoutResId(): Int
    abstract fun initData()

    open fun startObserve() {
        mViewModel.mException.observe(this, Observer { it?.let { onError(it) } })
    }

    open fun onError(e: Throwable) {}

    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }
}