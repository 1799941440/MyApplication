package com.djkj.myapplication

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * @author wz
 * @date 2021/10/8 16:12
 * @version 1.0
 * @description
 **/

class MainPresenter<T : IView>(ac: T) : BasePresenter<T>(ac) {

    fun getOrg(uId: String) {
        makeCall(Observable.just(FakeResponse(code = 1000004, msg = "success")).delay(2, TimeUnit.SECONDS)) {
            view.get()?.showToast(it.msg)
        }
    }
}