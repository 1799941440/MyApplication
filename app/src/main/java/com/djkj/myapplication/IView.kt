package com.djkj.myapplication

/**
 * @author wz
 * @date 2021/10/8 16:20
 * @version 1.0
 * @description
 **/

interface IView {

    fun showToast(msg: String)
    fun onPermissionDenied()
    fun onUnknow(msg: String)
    fun onLoginTimeout()
    fun onUnLogin()
}