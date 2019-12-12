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
class MinDateVerifyUnitTest {

    @Mock
    private lateinit var textInputLayout: TextInputLayout

    val format = "MMMM dd, yyyy"

    @Test
    fun `minDate should be valid`() {
        val fieldValue = "March 29, 2020"
        val minDate = SimpleDateFormat(format, Locale.getDefault()).parse(fieldValue)

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .minDate(minDate, format, "Date should not be less than $fieldValue")
                .build()

        val verifier = FormVerifier()
                .addField(
                    1,
                    verifiers
                )
                .register(textInputLayout)

        verifiers.forEach {
            it.value = "March 30, 2020"
        }

        verifier.updateFieldVerifier(1, verifiers)
        val value = verifier.validate()
        assert(value)
    }


    @Test
    fun `minDate should be valid for same date`() {
        val fieldValue = "March 29, 2020"
        val minDate = SimpleDateFormat(format, Locale.getDefault()).parse(fieldValue)

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .minDate(minDate, format, "Date should not be greater than $fieldValue")
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
    fun `minDate should NOT be valid`() {
        val fieldValue = "March 29, 2020"
        val minDate = SimpleDateFormat(format, Locale.getDefault()).parse(fieldValue)

        Mockito.`when`(textInputLayout.id).thenReturn(1)

        val verifiers = Verify.Builder()
                .minDate(minDate, format, "Date should not be greater than $fieldValue")
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
        assert(!value)
    }

}
