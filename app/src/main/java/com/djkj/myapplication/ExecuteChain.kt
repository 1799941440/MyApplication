package com.djkj.myapplication

import com.djkj.myapplication.FakeResponse.Companion.CODE_SUCCESS

/**
 * @author wz
 * @date 2021/10/8 14:43
 * @version 1.0
 * @description
 **/

class ExecuteChain(
    private val codeExecutors: MutableList<ICodeHandler>,
    private val onSuccess: (FakeResponse) -> Unit
) {
    private var index = 0

    fun proceed(response: FakeResponse) {
        codeExecutors.add(0, transfer(onSuccess))
        while (index != codeExecutors.size) {
            if (codeExecutors[index].handleResponse(response) != null) {
                index++
            } else {
                break
            }
        }
    }

    private fun transfer(onSuccess: (FakeResponse) -> Unit): ICodeHandler {
        return object : ICodeHandler {
            override fun handleResponse(response: FakeResponse): FakeResponse? {
                return if (response.code == CODE_SUCCESS) {
                    onSuccess(response)
                    null
                } else {
                    response
                }
            }
        }
    }
}