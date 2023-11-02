package com.sagar.nirfpredictor.ui.theme.login.use_cases

import android.util.Patterns
import java.util.regex.Pattern

class EmailValidation {
    fun execute(email: String?): ValidationEvent{
        if(email!= null){
            if(email.isEmpty()){
                return ValidationEvent(false, "Email cannot be empty")
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                return ValidationEvent(false, "Email is invalid")
            }
            return ValidationEvent(success = true, null)
        }
        return ValidationEvent(false, "Email cannot be null")
    }
}