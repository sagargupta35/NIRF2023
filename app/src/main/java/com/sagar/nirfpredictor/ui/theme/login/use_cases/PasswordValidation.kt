package com.sagar.nirfpredictor.ui.theme.login.use_cases

import android.util.Patterns
import java.util.regex.Pattern

class PasswordValidation {
    fun execute(password: String?): ValidationEvent{
        if(password!= null){
            if(password.length <= 5){
                return ValidationEvent(false, "Password must atleast have 6 characters")
            }
            return ValidationEvent(success = true, null)
        }
        return ValidationEvent(false, "Password cannot be null")
    }
}