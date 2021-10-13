package com.djkj.myapplication

import java.lang.ref.WeakReference

/**
 * @author wz
 * @date 2021/10/8 14:44
 * @version 1.0
 * @description
 **/

interface ICodeHandler {

    fun handleResponse(response: FakeResponse): FakeResponse?

    class UnLoginHandler(private val view: WeakReference<out IView>) : ICodeHandler {
        override fun handleResponse(response: FakeResponse): FakeResponse? {
            if (response.code == FakeResponse.CODE_UN_LOGIN) {
                view.get()?.onUnLogin()
                return null
            }
            return response
        }
    }

    class LoginTimeoutHandler(private val view: WeakReference<out IView>) : ICodeHandler {
        override fun handleResponse(response: FakeResponse): FakeResponse? {
            if (response.code == FakeResponse.CODE_LOGIN_TIMEOUT) {
                view.get()?.onLoginTimeout()
                return null
            }
            return response
        }
    }

    class PermissionDeniedHandler(private val view: WeakReference<out IView>) : ICodeHandler {
        override fun handleResponse(response: FakeResponse): FakeResponse? {
            if (response.code == FakeResponse.CODE_PERMISSION_DENIED) {
                view.get()?.onPermissionDenied()
                return null
            }
            return response
        }
    }

    class UnknowHandler(private val view: WeakReference<out IView>) : ICodeHandler {
        override fun handleResponse(response: FakeResponse): FakeResponse? {
            view.get()?.onUnknow(response.msg)
            return null
        }
    }
}