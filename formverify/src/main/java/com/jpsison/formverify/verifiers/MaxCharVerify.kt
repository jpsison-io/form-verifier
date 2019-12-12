package com.jpsison.formverify.verifiers

import com.jpsison.formverify.interfaces.IFieldVerify

class MaxCharVerify(var max: Int = 0, customMessage: String? = null) : IFieldVerify {

    override var value: String? = null

    override var message: String = customMessage ?: "Maximum of $max characters"

    override fun isValid(): Boolean {
        return (value?.length ?: 0) <= max
    }
}