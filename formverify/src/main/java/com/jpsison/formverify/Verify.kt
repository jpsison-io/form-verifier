package com.jpsison.formverify

import com.jpsison.formverify.interfaces.IFieldVerify
import com.jpsison.formverify.verifiers.*
import java.util.*

class Verify {

    class Builder {

        private val list: MutableList<IFieldVerify> = mutableListOf()

        fun required(customMessage: String? = null) = apply {
            list.add(RequiredVerify(customMessage))
        }

        fun email(customMessage: String? = null) = apply {
            list.add(EmailVerify(customMessage))
        }

        fun match(resId: Int, customMessage: String? = null) = apply {
            list.add(MatchVerify(resId, customMessage))
        }

        fun minChar(min: Int, customMessage: String? = null) = apply {
            list.add(MinCharVerify(min, customMessage))
        }

        fun maxChar(max: Int, customMessage: String? = null) = apply {
            list.add(MaxCharVerify(max, customMessage))
        }

        fun maxDate(max: Date, format: String, customMessage: String) = apply {
            list.add(MaxDateVerify(max, format, customMessage))
        }

        fun minDate(max: Date, format: String, customMessage: String) = apply {
            list.add(MinDateVerify(max, format, customMessage))
        }

        fun regex(regex: String, customMessage: String? = null) = apply {
            list.add(RegexVerify(regex, customMessage))
        }

        fun custom(fieldVerify: IFieldVerify) = apply {
            list.add(fieldVerify)
        }

        fun build() = (list)
    }
}