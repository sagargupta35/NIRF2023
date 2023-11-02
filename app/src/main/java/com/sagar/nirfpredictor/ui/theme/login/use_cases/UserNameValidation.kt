package com.sagar.nirfpredictor.ui.theme.login.use_cases

import android.util.Patterns
import java.util.regex.Pattern

class UserNameValidation {
    fun execute(name: String?): ValidationEvent{
        if(name!= null){
            if(name.length <= 5){
                return ValidationEvent(false, "User name must have at least 5 characters")
            }
            return ValidationEvent(success = true, null)
        }
        return ValidationEvent(false, "Email cannot be null")
    }
}