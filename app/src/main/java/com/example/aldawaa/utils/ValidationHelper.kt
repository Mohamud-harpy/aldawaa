package com.example.aldawaa.utils

import android.util.Patterns


class ValidationHelper {

    fun emailorphonevalidation(emailorphone: String ): Boolean {
        if(emailorphone.isBlank()) {
            return false
        }
       else if(!Patterns.EMAIL_ADDRESS.matcher(emailorphone).matches()&&!Patterns.PHONE.matcher(emailorphone).matches()) {
            return false
        }
        return true
    }


    fun emailvalidation(email: String): Boolean {
        if(email.isBlank()) {
            return false
        }
       else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }
        return true
    }

    fun phonenumvalidation(Phone: String): Boolean {
        if(Phone.isBlank()) {
            return false
        }
        if(Phone.length < 11 ||Phone.length>11) {
            return false
        }
        if(!Patterns.PHONE.matcher(Phone).matches()) {
            return false
        }
        if(!Phone.startsWith("010")) {
            return false
        }
        return true
    }


    fun passwordlvalidation (password: String): Boolean {
        if(password.length < 8) {
            return false
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if(!containsLettersAndDigits) {
            return false
        }
        return true
    }


    fun confirmpasswordvalidation(password: String, repeatedPassword: String): Boolean {
        if(password != repeatedPassword) {
            return false
        }
        return true
    }


    fun emptyvalidation(field: String ): Boolean {
        if(field.isBlank()) {
            return false
        }

        return true
    }


   /* fun birthdayvalidation(field: String ): Boolean {
        if(field.isBlank()) {
            return false
        }
        if(!Patterns.) {
            return false
        }
        return true
    }*/



}