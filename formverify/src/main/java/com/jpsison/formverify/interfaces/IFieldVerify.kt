package com.jpsison.formverify.interfaces

interface IFieldVerify {

    var value: String?

    var message: String

    fun isValid(): Boolean
}