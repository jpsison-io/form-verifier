package com.jpsison.formverify.verifiers

import com.jpsison.formverify.interfaces.IFieldVerify

class MatchVerify(var matchResId: Int, customMessage: String? = null) : IFieldVerify {

    lateinit var matchValue: String

    override var value: String? = null

    override var message: String = customMessage ?: "Field does not match"

    override fun isValid(): Boolean {
        return value == matchValue
    }
}