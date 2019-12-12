package com.jpsison.formverify

import com.google.android.material.textfield.TextInputLayout
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegexVerifyUnitTest {

    @Mock
    private lateinit var textInputLayout: TextInputLayout

    @Test
    fun `regex should accept numbers only`() {
        val fieldValue = "123456789"

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
            .regex("^[0-9]*\$", "Must contain numbers only!")
            .build()

        val verifier = FormVerifier()
            .addField(
                1,
                verifiers
            )
            .register(textInputLayout)

        verifiers.forEach {
            it.value = fieldValue
        }

        verifier.updateFieldVerifier(1, verifiers)
        val value = verifier.validate()
        assert(value)
    }

    @Test
    fun `regex should NOT be valid for alpha characters`() {
        val fieldValue = "Hi!"

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .regex("^[0-9]*\$", "Must contain numbers only!")
                .build()

        val verifier = FormVerifier()
                .addField(
                    1,
                    verifiers
                )
                .register(textInputLayout)

        verifiers.forEach {
            it.value = fieldValue
        }

        verifier.updateFieldVerifier(1, verifiers)
        val value = verifier.validate()
        assert(!value)
    }

    @Test
    fun `regex should NOT be valid for alpha-numeric characters`() {
        val fieldValue = "123456abcde"

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .regex("^[0-9]*\$", "Must contain numbers only!")
                .build()

        val verifier = FormVerifier()
                .addField(
                        1,
                        verifiers
                )
                .register(textInputLayout)

        verifiers.forEach {
            it.value = fieldValue
        }

        verifier.updateFieldVerifier(1, verifiers)
        val value = verifier.validate()
        assert(!value)
    }
}
