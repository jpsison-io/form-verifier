package com.jpsison.formverify.verifiers

import com.jpsison.formverify.interfaces.IFieldVerify

class RequiredVerify(customMessage: String? = null) : IFieldVerify {

    override var value: String? = null

    override var message: String = customMessage ?: "This field is required"

    override fun isValid(): Boolean {
        return value?.isNotBlank() == true
    }
}