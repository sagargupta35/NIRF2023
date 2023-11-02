package com.sagar.nirfpredictor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sagar.nirfpredictor.ui.theme.NIRFPredictorTheme
import com.sagar.nirfpredictor.ui.theme.login.screens.LoginViewModel
import com.sagar.nirfpredictor.ui.theme.screens.NIRFComposable
import com.sagar.nirfpredictor.ui.theme.screens.NIRFViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NIRFPredictorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: NIRFViewModel = viewModel(factory = NIRFViewModel.factory)
                    val loginViewModel: LoginViewModel = viewModel()
                    NIRFComposable(viewModel = viewModel,
                        loginViewModel = loginViewModel
                        )

                }
            }
        }
    }
}
