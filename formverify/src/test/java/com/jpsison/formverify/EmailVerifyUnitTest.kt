package com.jpsison.formverify

import com.google.android.material.textfield.TextInputLayout
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EmailVerifyUnitTest {

    @Mock
    private lateinit var textInputLayout: TextInputLayout

    @Test
    fun `email should be valid`() {
        val fieldValue = "simple@email.com"

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .email()
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
    fun `email should be NOT valid`() {
        val fieldValues = listOf(
            "#@%^%#$@#$@#.com",
            "@example.com",
            "Joe Smith <email@example.com>",
            "email.example.com",
            "email@example@example.com",
            "email..email@example.com",
            "email@example.com (Joe Smith)",
            "email@example",
            "email@-example.com",
            "email@example..com",
            "Abc..123@example.com"
        )

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .email()
                .build()

        val verifier = FormVerifier()
                .addField(
                        1,
                        verifiers
                )
                .register(textInputLayout)

        fieldValues.forEach { fieldValue ->
            verifiers.forEach {
                it.value = fieldValue
            }
            verifier.updateFieldVerifier(1, verifiers)
            val value = verifier.validate()
            assert(!value)
        }
    }

}
