package com.jpsison.formverify

import com.google.android.material.textfield.TextInputLayout
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MaxCharVerifyUnitTest {

    @Mock
    private lateinit var textInputLayout: TextInputLayout

    @Test
    fun `max char should be valid`() {
        val fieldValue = "A"

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .maxChar(2)
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
    fun `max char should be NOT valid`() {
        val fieldValue = "Hi Android!"

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .maxChar(2)
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
