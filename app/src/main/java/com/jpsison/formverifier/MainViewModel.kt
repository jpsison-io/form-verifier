package com.jpsison.formverifier

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.jpsison.formverify.FormVerifier
import com.jpsison.formverify.Verify
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val birthdayFormat = "MMMM dd, yyyy"

    var verifier = FormVerifier()
        .addField(
            R.id.til_first_name,
            Verify.Builder()
                .required()
                .minChar(3)
                .build()
        )
        .addField(
            R.id.til_last_name,
            Verify.Builder()
                .required()
                .minChar(3)
                .build()
        )
        .addField(
            R.id.til_gender,
            Verify.Builder()
                .required()
                .build()
        )
        .addField(
            R.id.til_birthday,
            Verify.Builder()
                .required()
                .maxDate(
                    Date(),
                    birthdayFormat,
                    application.getString(R.string.error_birthday)
                )
                .build()
        )
        .addField(
            R.id.til_email,
            Verify.Builder()
                .required()
                .email()
                .build()
        )
        .addField(
            R.id.til_email_optional,
            Verify.Builder()
                .email()
                .build()
        )
        .addField(
            R.id.til_password,
            Verify.Builder()
                .required()
                .minChar(8)
                .maxChar(20)
                .regex(
                    "((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*\$",
                    application.getString(R.string.error_password_weak)
                )
                .build()
        )
        .addField(
            R.id.til_confirm_password,
            Verify.Builder()
                .required()
                .match(
                    R.id.til_password,
                    application.getString(R.string.error_password_not_match)
                )
                .build()
        )

    fun onSignUpClick() {
        val valid = verifier.validate()
        if (valid) {
            val name = verifier.getValue(R.id.til_first_name)
            val email = verifier.getValue(R.id.til_email)
            val password = verifier.getValue(R.id.til_password)
            val confirmPassword = verifier.getValue(R.id.til_confirm_password)
        }
        verifier.realtimeValidation = true
    }

    fun onBirthdayClick() {

    }

    fun onGenderClick() {

    }

    fun signIn() {
        val email = verifier.getValue(R.id.til_email)
        val password = verifier.getValue(R.id.til_password)
    }
}