package com.jpsison.formverify.adapters

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.jpsison.formverify.FormVerifier

@BindingAdapter("android:verifier")
fun setVerifier(editText: EditText, formVerifier: FormVerifier) {
    formVerifier.register(editText)
    setupEditTextChange(editText, editText.id, formVerifier) {
        println("Error!")
    }
}

@BindingAdapter("android:verifier")
fun setVerifier(textInputLayout: TextInputLayout, formVerifier: FormVerifier) {
    formVerifier.register(textInputLayout)
    val resId = textInputLayout.id
    textInputLayout.editText?.let {
        setupEditTextChange(it, resId, formVerifier) { error ->
            textInputLayout.error = error
            textInputLayout.isErrorEnabled = error != null
        }
    }
}

private fun setupEditTextChange(
    editText: EditText,
    resId: Int,
    formVerifier: FormVerifier,
    onError: (String?) -> Unit
) {
    editText.doAfterTextChanged { value ->
        val verifications = formVerifier.findFieldVerifier(resId)
        verifications.forEach {
            it.value = value.toString()
        }
        if (!formVerifier.realtimeValidation) return@doAfterTextChanged
        formVerifier.updateFieldVerifier(resId, verifications)
        formVerifier.validatedReferences(resId)
        verifications.forEach {
            if (!formVerifier.isValid(it)) {
                onError.invoke(it.message)
                return@doAfterTextChanged
            }
        }
        onError.invoke(null)
    }
}