package com.djkj.myapplication

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

/**
 * @author wz
 * @date 2021/10/8 14:53
 * @version 1.0
 * @description
 **/

open class BasePresenter<T : IView>(ac : T) {

    var view: WeakReference<T> = WeakReference(ac)
    private val UN_LOGIN_HANDLER = ICodeHandler.UnLoginHandler(view)
    private val LOGIN_TIMEOUT_HANDLER = ICodeHandler.LoginTimeoutHandler(view)
    private val PERMISSION_HANDLER = ICodeHandler.PermissionDeniedHandler(view)
    private val UNKNOW_HANDLER = ICodeHandler.UnknowHandler(view)
    private val handlers = mutableListOf(UN_LOGIN_HANDLER, LOGIN_TIMEOUT_HANDLER, PERMISSION_HANDLER, UNKNOW_HANDLER)

    @SuppressLint("CheckResult")
    fun makeCall(observer: Observable<FakeResponse>, onSuccess: (FakeResponse) -> Unit) {
        observer.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            ExecuteChain(handlers, onSuccess).proceed(it)
        }, {
            it.printStackTrace()
        })
    }
}