package com.jpsison.formverify.verifiers

import com.jpsison.formverify.interfaces.IFieldVerify

class MinCharVerify(var minimum: Int = 0, customMessage: String? = null) : IFieldVerify {

    override var value: String? = null

    override var message: String = customMessage ?: "Must be at least $minimum characters"

    override fun isValid(): Boolean {
        return (value?.length ?: 0) >= minimum
    }
}