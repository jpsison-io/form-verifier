package com.jpsison.formverify

import com.google.android.material.textfield.TextInputLayout
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.text.SimpleDateFormat
import java.util.Locale

@RunWith(MockitoJUnitRunner::class)
class MaxDateVerifyUnitTest {

    @Mock
    private lateinit var textInputLayout: TextInputLayout

    val format = "MMMM dd, yyyy"

    @Test
    fun `maxDate should be valid`() {
        val fieldValue = "March 29, 2020"
        val maxDate = SimpleDateFormat(format, Locale.getDefault()).parse(fieldValue)

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .maxDate(maxDate, format, "Date should not be greater than $fieldValue")
                .build()

        val verifier = FormVerifier()
                .addField(
                    1,
                    verifiers
                )
                .register(textInputLayout)
        verifiers.forEach {
            it.value = "March 28, 2020"
        }

        verifier.updateFieldVerifier(1, verifiers)
        val value = verifier.validate()
        assert(value)
    }


    @Test
    fun `maxDate should be valid for same date`() {
        val fieldValue = "March 29, 2020"
        val maxDate = SimpleDateFormat(format, Locale.getDefault()).parse(fieldValue)

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .maxDate(maxDate, format, "Date should not be greater than $fieldValue")
                .build()

        val verifier = FormVerifier()
                .addField(
                        1,
                        verifiers
                )
                .register(textInputLayout)
        verifiers.forEach {
            it.value = "March 29, 2020"
        }

        verifier.updateFieldVerifier(1, verifiers)
        val value = verifier.validate()
        assert(value)
    }

    @Test
    fun `maxDate should NOT be valid`() {
        val fieldValue = "March 29, 2020"
        val maxDate = SimpleDateFormat(format, Locale.getDefault()).parse(fieldValue)

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .maxDate(maxDate, format, "Date should not be greater than $fieldValue")
                .build()

        val verifier = FormVerifier()
                .addField(
                        1,
                        verifiers
                )
                .register(textInputLayout)
        verifiers.forEach {
            it.value = "April 2, 2020"
        }

        verifier.updateFieldVerifier(1, verifiers)
        val value = verifier.validate()
        assert(!value)
    }
}
