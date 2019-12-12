package com.jpsison.formverify

import android.text.Editable
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MatchVerifyUnitTest {

    @Mock
    private lateinit var passwordTextInputLayout: TextInputLayout

    @Mock
    private lateinit var passwordEditText: EditText

    @Mock
    private lateinit var passwordEditable: Editable

    @Mock
    private lateinit var confirmPasswordTextInputLayout: TextInputLayout

    @Test
    fun `match verify should be valid`() {

        Mockito.`when`(passwordTextInputLayout.id).thenReturn(1)
        Mockito.`when`(confirmPasswordTextInputLayout.id).thenReturn(2)

        Mockito.`when`(passwordTextInputLayout.editText).thenReturn(passwordEditText)
        Mockito.`when`(passwordEditText.text).thenReturn(passwordEditable)
        Mockito.`when`(passwordEditable.toString()).thenReturn("Password123")

        val passwordVerifiers = Verify.Builder()
                .match(1)
                .build()

        val confirmPasswordVerifiers = Verify.Builder()
                .match(1)
                .build()

        val verifier = FormVerifier()
                .addField(
                    1,
                    passwordVerifiers
                )
                .addField(
                    2,
                    confirmPasswordVerifiers
                )
                .register(passwordTextInputLayout)
                .register(confirmPasswordTextInputLayout)

        passwordVerifiers.forEach {
            it.value = "Password123"
        }

        confirmPasswordVerifiers.forEach {
            it.value = "Password123"
        }

        verifier.updateFieldVerifier(1, passwordVerifiers)
        verifier.updateFieldVerifier(2, confirmPasswordVerifiers)
        val value = verifier.validate()
        assert(value)
    }

    @Test
    fun `match verify should NOT be valid`() {

        Mockito.`when`(passwordTextInputLayout.id).thenReturn(1)
        Mockito.`when`(confirmPasswordTextInputLayout.id).thenReturn(2)

        Mockito.`when`(passwordTextInputLayout.editText).thenReturn(passwordEditText)
        Mockito.`when`(passwordEditText.text).thenReturn(passwordEditable)
        Mockito.`when`(passwordEditable.toString()).thenReturn("Password123456")

        val passwordVerifiers = Verify.Builder()
                .match(1)
                .build()

        val confirmPasswordVerifiers = Verify.Builder()
                .match(1)
                .build()

        val verifier = FormVerifier()
                .addField(
                        1,
                        passwordVerifiers
                )
                .addField(
                        2,
                        confirmPasswordVerifiers
                )
                .register(passwordTextInputLayout)
                .register(confirmPasswordTextInputLayout)

        passwordVerifiers.forEach {
            it.value = "Password123456"
        }

        confirmPasswordVerifiers.forEach {
            it.value = "Password123"
        }

        verifier.updateFieldVerifier(1, passwordVerifiers)
        verifier.updateFieldVerifier(2, confirmPasswordVerifiers)
        val value = verifier.validate()
        assert(!value)
    }

    @Test
    fun `callers must be notified and validated`() {

        Mockito.`when`(passwordTextInputLayout.id).thenReturn(1)
        Mockito.`when`(confirmPasswordTextInputLayout.id).thenReturn(2)

        Mockito.`when`(passwordTextInputLayout.editText).thenReturn(passwordEditText)
        Mockito.`when`(passwordEditText.text).thenReturn(passwordEditable)
        Mockito.`when`(passwordEditable.toString()).thenReturn("Password123456")

        val passwordVerifiers = Verify.Builder()
                .match(1)
                .build()

        val confirmPasswordVerifiers = Verify.Builder()
                .match(1)
                .build()

        val verifier = FormVerifier()
                .addField(
                        1,
                        passwordVerifiers
                )
                .addField(
                        2,
                        confirmPasswordVerifiers
                )
                .register(passwordTextInputLayout)
                .register(confirmPasswordTextInputLayout)

        passwordVerifiers.forEach {
            it.value = "Password123456"
        }

        confirmPasswordVerifiers.forEach {
            it.value = "Password123"
        }

        verifier.updateFieldVerifier(1, passwordVerifiers)
        verifier.updateFieldVerifier(2, confirmPasswordVerifiers)
        val value = verifier.validate()
        assert(!value)
    }

}
