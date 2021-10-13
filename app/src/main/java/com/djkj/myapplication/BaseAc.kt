package com.djkj.myapplication

import android.app.Activity
import android.widget.Toast

/**
 * @author wz
 * @date 2021/10/8 14:58
 * @version 1.0
 * @description
 **/

abstract class BaseAc<P : BasePresenter<IView>> : Activity(), IView {

    var presenter: P? = null
    get() {
        if (field == null) {
            field = providePresenter()
        }
        return field
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onUnLogin() {
        Toast.makeText(this, "onUnLogin", Toast.LENGTH_SHORT).show()
    }

    override fun onLoginTimeout() {
        Toast.makeText(this, "onLoginTimeout", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionDenied() {
        Toast.makeText(this, "onPermissionDenied", Toast.LENGTH_SHORT).show()
    }

    override fun onUnknow(msg: String) {
        Toast.makeText(this, "onUnknow msg = $msg", Toast.LENGTH_SHORT).show()
    }

    abstract fun providePresenter(): P
}