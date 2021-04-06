package com.yumeng.libcommon.utils

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Chauncey on 2017/9/14.
 * 定时器
 */

object Timer {
    /**
     * @param time     单位秒
     * @param listener 倒计时监听
     */
    fun countDown(time: Long, listener: CountDownListener/*, activityClass: Class<out RxAppCompatActivity>*/, scheduler: Scheduler? = Schedulers.io()): Disposable? {

        var disposable: Disposable? = null
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(time + 1)
//                .compose(RxLifecycleHelper.bindToLifecycle(activityClass))
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(aLong: Long) {
                        listener.onNext(time - aLong)
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {
                        listener.onCompleted()
                    }
                })
        return disposable
    }

//    fun <T : RxAppCompatActivity> countDown(time: Long, listener: CountDownListener, activity: T, scheduler: Scheduler? = Schedulers.io()): Disposable? {
//
//        var disposable: Disposable? = null
//        Observable.interval(0, 1, TimeUnit.SECONDS)
//                .take(time + 1)
//                .compose(activity.bindToLifecycle())
//                .subscribeOn(scheduler)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Observer<Long> {
//
//                    override fun onSubscribe(d: Disposable) {
//                        disposable = d
//                        listener.onStart()
//                    }
//
//                    override fun onNext(aLong: Long) {
//                        listener.onNext(time - aLong)
//                    }
//
//                    override fun onError(e: Throwable) {
//
//                    }
//
//                    override fun onComplete() {
//                        listener.onCompleted()
//                    }
//                })
//        return disposable
//    }

    interface CountDownListener {

        fun onStart()

        fun onNext(time: Long)

        fun onCompleted()
    }
}
