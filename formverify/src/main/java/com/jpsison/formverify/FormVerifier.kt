package com.jpsison.formverify

import com.jpsison.formverify.annotations.TestOpen
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.jpsison.formverify.interfaces.IFieldVerify
import com.jpsison.formverify.verifiers.MatchVerify
import java.lang.Exception

@TestOpen
class FormVerifier {

    val fields: MutableList<Pair<Int, List<IFieldVerify>>> = mutableListOf()
    var map = mutableMapOf<Any, List<IFieldVerify>>()
    var realtimeValidation = false

    fun addField(resId: Int, verifiers: List<IFieldVerify>) = apply {
        fields.add(Pair(resId, verifiers))
    }

    fun register(field: Any) = apply {
        if (field is EditText) {
            val list = fields.firstOrNull { (key) ->
                key == field.id
            }
            map[field] = list?.second ?: listOf()
            fields.removeAll { (resId) ->
                resId == field.id
            }
            return this@FormVerifier
        }
        if (field is TextInputLayout) {
            val list = fields.firstOrNull { (key) ->
                key == field.id
            }
            map[field] = list?.second ?: listOf()
            fields.removeAll { (resId) ->
                resId == field.id
            }
            return this@FormVerifier
        }
        throw Exception("Field not supported!")
    }

    fun findFieldVerifier(resId: Int): List<IFieldVerify> {
        map.toList().firstOrNull { (key) ->
            (key is EditText && key.id == resId || key is TextInputLayout && key.id == resId)
        }?.let {
            return it.second
        }
        return listOf()
    }

    fun updateFieldVerifier(resId: Int, verifiers: List<IFieldVerify>) {
        map.toList().firstOrNull { (key) ->
            (key is EditText && key.id == resId || key is TextInputLayout && key.id == resId)
        }?.let {
            map[it.first] = verifiers
        }
    }

    fun getValue(resId: Int): String {
        map.toList().firstOrNull { (key) ->
            (key is EditText && key.id == resId || key is TextInputLayout && key.id == resId)
        }?.let { (key) ->
            if (key is EditText) return key.text.toString()
            if (key is TextInputLayout) return key.editText?.text.toString()
        }
        return ""
    }

    fun setValue(resId: Int, value: String) {
        map.toList().firstOrNull { (key) ->
            (key is EditText && key.id == resId || key is TextInputLayout && key.id == resId)
        }?.let { (key) ->
            if (key is EditText) key.setText(value)
            if (key is TextInputLayout) key.editText?.setText(value)
        }
    }

    fun isValid(field: IFieldVerify): Boolean {
        return if (field is MatchVerify) {
            field.matchValue = getValue(field.matchResId)
            field.isValid()
        } else {
            field.isValid()
        }
    }

    fun validatedReferences(resId: Int) {
        val refs = map.toList().filter { (_, value) ->
            value.any {
                it is MatchVerify && it.matchResId == resId
            }
        }
        validate(refs.toMap())
    }

    fun validate(mapSet: Map<Any, List<IFieldVerify>>? = null): Boolean {
        var valid = true
        for (map in (mapSet ?: map)) {
            val field = map.value.firstOrNull {
                !isValid(it)
            }
            if (map.key is TextInputLayout) {
                val textInputLayout = map.key as TextInputLayout
                textInputLayout.error = field?.message
                if (valid && field != null) {
                    valid = false
                }
                textInputLayout.isErrorEnabled = field != null
            }
        }
        return valid
    }
}