package com.jpsison.formverify.verifiers

import com.jpsison.formverify.interfaces.IFieldVerify

class RegexVerify(val regex: String, customMessage: String? = null) : IFieldVerify {

    override var value: String? = null

    override var message: String = customMessage ?: "Expression does not match"

    override fun isValid(): Boolean {
        return regex.toRegex().matches(value ?: "")
    }
}