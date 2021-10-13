package com.djkj.myapplication

/**
 * @author wz
 * @date 2021/10/8 14:45
 * @version 1.0
 * @description
 **/

data class FakeResponse(
    var code: Int = 0,
    var msg: String = "",
    var data: MutableList<String> = mutableListOf()
) {
    companion object {
        const val CODE_SUCCESS = 1000000
        const val CODE_UN_LOGIN = 1000001
        const val CODE_LOGIN_TIMEOUT = 1000002
        const val CODE_PERMISSION_DENIED = 1000003
        const val CODE_UNKNOW = 1000004
    }
}