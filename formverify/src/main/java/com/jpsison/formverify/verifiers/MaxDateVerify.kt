package com.jpsison.formverify.verifiers

import com.jpsison.formverify.interfaces.IFieldVerify
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

class MaxDateVerify(var maxDate: Date, val format: String, override var message: String) : IFieldVerify {

    override var value: String? = null

    override fun isValid(): Boolean {
        val input = value ?: return false
        val date = SimpleDateFormat(format, Locale.getDefault()).parse(input) ?: return false
        return date <= maxDate
    }
}